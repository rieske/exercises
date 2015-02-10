package exercises.queue;

public class QueueWrapper<T> implements Queue<T> {

	private final java.util.Queue<T> delegate;

	public QueueWrapper(java.util.Queue<T> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void enqueue(T element) {
		delegate.offer(element);
	}

	@Override
	public T dequeue() {
		return delegate.poll();
	}
}
