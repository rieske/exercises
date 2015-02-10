package exercises.queue;

import java.util.Stack;

public class DoubleStackQueue<T> implements Queue<T> {

	private Stack<T> input = new Stack<>();
	private Stack<T> output = new Stack<>();

	@Override
	public void enqueue(T element) {
		input.push(element);
	}

	@Override
	public T dequeue() {
		if (output.isEmpty()) {
			while (!input.isEmpty()) {
				output.push(input.pop());
			}
		}
		return output.pop();
	}

}
