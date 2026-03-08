package pharma.model;

public class DispenseLine {
    private Medication medication;
    private int quantity;
    private double priceAtMoment;
    private String batchNumber;

    public DispenseLine(Medication medication,
                        int quantity,
                        double priceAtMoment,
                        String batchNumber) {
        this.medication = medication;
        this.quantity = quantity;
        this.priceAtMoment = priceAtMoment;
        this.batchNumber = batchNumber;
    }

    public Medication getMedication() {
        return medication;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPriceAtMoment() {
        return priceAtMoment;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public double getLineTotal() {
        // TODO: занятие 1 ДЗ-1 - вернуть сумму по позиции
        return 0.0;
    }

    @Override
    public String toString() {
        // TODO: занятие 1 ДЗ-1 - форматировать строку
        return "DispenseLine{" + medication.getName() + ", qty=" + quantity + "}";
    }
}
