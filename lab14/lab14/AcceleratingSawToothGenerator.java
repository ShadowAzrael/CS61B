package lab14;

public class AcceleratingSawToothGenerator {
    private int state;
    private int period;
    private double factor;

    public AcceleratingSawToothGenerator(int period, double factor) {
        state = 0;
        this.period = period;
        this.factor = factor;
    }

    public double next() {
        state++;
        if (state == period) {
            period *= factor;
            state = 0;
        }
        return normalize(state);
    }

    private double normalize(int x) {
        return (double) x * 2 / period - 1;
    }
}
