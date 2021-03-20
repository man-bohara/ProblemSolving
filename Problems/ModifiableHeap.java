public class ModifiableHeap {

	int CAPACITY;
	int size;
	
	Movie[] heap;
	int[] index;
	

	public ModifiableHeap(int capacity) {
		this.CAPACITY = capacity;
		this.size = 0;
		heap = new Movie[CAPACITY];
		index = new int[CAPACITY];
	}
	
	void modifyHeapValue(Movie value) {
		int current = index[value.id];
		heap[current] = value;
		heapifyUp(current);
		heapifyDown(current);
	}

	void push(Movie value) {
		if (size + 1 > CAPACITY) {
			return;
		}

		heap[size] = value;
		index[value.id] = size;

		heapifyUp(size);
		size = size + 1;
	}

	void heapifyUp(int current) {
		while (current > 0 && isMorePopular(heap[current], heap[(current - 1) / 2])) {
			Movie temp = heap[(current - 1) / 2];
			heap[(current - 1) / 2] = heap[current];
			index[heap[current].id] = (current - 1) / 2;

			heap[current] = temp;
			index[temp.id] = current;

			current = (current - 1) / 2;
		}
	}

	Movie pop() {
		if (size <= 0) {
			return null;
		}

		Movie value = heap[0];
		size = size - 1;

		heap[0] = heap[size];
		index[heap[size].id] = 0;

		heapifyDown(0);

		return value;
	}

	void heapifyDown(int current) {
		while (current < size && current * 2 + 1 < size) {
			int child;
			if (current * 2 + 2 >= size) {
				child = current * 2 + 1;
			} else {
				child = isMorePopular(heap[current * 2 + 1], heap[current * 2 + 2]) ? current * 2 + 1 : current * 2 + 2;
			}

			if (isMorePopular(heap[current], heap[child])) {
				break;
			}

			Movie temp = heap[current];
			heap[current] = heap[child];
			index[heap[child].id] = current;

			heap[child] = temp;
			index[temp.id] = child;

			current = child;
		}
	}

	private boolean isMorePopular(Movie node1, Movie node2) {

		if (node1 == null)
			return false;

		if (node2 == null)
			return true;

		if (node1.popularity > node2.popularity)
			return true;

		if (node1.popularity < node2.popularity)
			return false;

		if (node1.id < node2.id)
			return true;

		return false;
	}

	public static void main(String arg[]) throws Exception {

		ModifiableHeap heap = new ModifiableHeap(1000);
		heap.push(new Movie(0, 5));
		heap.push(new Movie(1, 10));
		heap.push(new Movie(2, 20));
		heap.push(new Movie(3, 30));
		heap.push(new Movie(4, 40));

		// heap.heapPrint(heap.heap, heap.heapSize);

		System.out.printf("%d ", heap.pop().id);
		System.out.printf("%d ", heap.pop().id);
		System.out.printf("%d ", heap.pop().id);
		System.out.printf("%d ", heap.pop().id);
		System.out.printf("%d ", heap.pop().id);

		heap.push(new Movie(0, 5));
		heap.push(new Movie(1, 10));
		heap.push(new Movie(2, 20));
		heap.push(new Movie(3, 30));
		heap.push(new Movie(4, 40));

		heap.modifyHeapValue(new Movie(0, 500));

		Movie[] values = new Movie[heap.size];

		System.out.println();
		for (int i = 0; i < values.length; i++) {
			values[i] = heap.pop();
			System.out.printf("%d ", values[i].id);
		}

		for (int i = 0; i < values.length; i++) {
			heap.push(values[i]);
		}

		heap.modifyHeapValue(new Movie(1, 400));

		values = new Movie[heap.size];

		System.out.println();
		for (int i = 0; i < values.length; i++) {
			values[i] = heap.pop();
			System.out.printf("%d ", values[i].id);
		}
		
		for (int i = 0; i < values.length; i++) {
			heap.push(values[i]);
		}
	}
}

class Movie {
	int id;
	int popularity;

	public Movie(int id, int popularity) {
		this.id = id;
		this.popularity = popularity;
	}
}
