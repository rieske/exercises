package exercises.queue;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.collect.Range;

@RunWith(Theories.class)
public class QueueTest {

	@SuppressWarnings("unchecked")
	@DataPoints("queueImplementations")
	public static List<Queue<Integer>> queueImplementations = Lists.<Queue<Integer>> newArrayList(
			new QueueWrapper<Integer>(Queues.<Integer> newArrayDeque()),
			new DoubleStackQueue<Integer>(),
			new SingleStackQueue<Integer>());

	@Theory
	public void implementsFIFOqueueSemantics(@FromDataPoints("queueImplementations") Queue<Integer> queue) {
		Set<Integer> elements = ContiguousSet.create(Range.closed(0, 1000), DiscreteDomain.integers());

		for (Integer element : elements) {
			queue.enqueue(element);
		}
		for (Integer element : elements) {
			assertThat(queue.dequeue(), equalTo(element));
		}
	}

	@Theory
	public void maintainsFIFOsemanticsAfterDequeueing(@FromDataPoints("queueImplementations") Queue<Integer> queue) {
		Set<Integer> firstSet = ContiguousSet.create(Range.closed(1, 10), DiscreteDomain.integers());
		for (Integer element : firstSet) {
			queue.enqueue(element);
		}
		Iterator<Integer> firstSetIterator = firstSet.iterator();
		assertThat(queue.dequeue(), equalTo(firstSetIterator.next()));
		Set<Integer> secondSet = ContiguousSet.create(Range.closed(20, 30), DiscreteDomain.integers());
		for (Integer element : secondSet) {
			queue.enqueue(element);
		}
		assertThat(queue.dequeue(), equalTo(firstSetIterator.next()));
		while (firstSetIterator.hasNext()) {
			assertThat(queue.dequeue(), equalTo(firstSetIterator.next()));
		}
		for (Integer element : secondSet) {
			assertThat(queue.dequeue(), equalTo(element));
		}
	}

}
