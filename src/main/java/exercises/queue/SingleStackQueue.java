package exercises.queue;

import java.util.Stack;

public class SingleStackQueue<T> implements Queue<T> {

	private Stack<T> backingStack = new Stack<>();

	@Override
	public void enqueue(T element) {
		backingStack.push(element);
	}

	@Override
	public T dequeue() {
		T top = backingStack.pop();
		if (backingStack.isEmpty()) {
			return top;
		}
		T result = dequeue();
		backingStack.push(top);
		return result;
	}

}
