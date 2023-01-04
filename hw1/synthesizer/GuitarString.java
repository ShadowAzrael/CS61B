package synthesizer;

public class GuitarString {
    private static final int SR = 44180;
    private static final double DECAY = .996;

    private BoundedQueue<Double> buffer;

    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<Double>(capacity);
        for (int i = 0; i < capacity; i++) {
            buffer.enqueue(0.0);
        }
    }

    public void pluck() {
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        while (!buffer.isFull()) {
            buffer.enqueue(Math.random() - 0.5);
        }
    }

    public void tic() {
        double dqVal = buffer.dequeue();
        buffer.enqueue(DECAY * (dqVal + buffer.peek()) * 0.5);
    }

    public double sample() {
        return buffer.peek();
    }
}
