package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    private int first;
    private int last;
    private T[] rb;

    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = last = fillCount = 0;
        this.capacity = capacity;
    }

    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        fillCount++;
        last = (last + 1) % capacity;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T val = rb[first];
        fillCount--;
        first = (first + 1) % capacity;
        return val;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        return rb[first];
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int pos; //position
        private int curNum; //current number

        public ArrayRingBufferIterator() {
            pos = first;
            curNum = 0;
        }

        public boolean hasNext() {
            return curNum < fillCount;
        }

        public T next() {
            T val = rb[pos];
            pos = (pos + 1) % capacity;
            curNum++;
            return val;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }
}
