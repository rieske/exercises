package exercises.queue;

public interface Queue<T> {

	void enqueue(T element);

	T dequeue();

}
