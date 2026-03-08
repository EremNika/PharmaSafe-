package pharma.exception;

public class TemperatureViolationException extends Exception {
    private final double current;
    private final double min;
    private final double max;

    public TemperatureViolationException(double current, double min, double max) {
        super(String.format("Температурное нарушение: текущая %.2f, допустимый диапазон [%.2f, %.2f]",
                current, min, max));
        this.current = current;
        this.min = min;
        this.max = max;
    }

    public double getCurrent() {
        return current;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
