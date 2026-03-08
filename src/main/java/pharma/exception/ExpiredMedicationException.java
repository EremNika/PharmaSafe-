package pharma.exception;

import java.time.LocalDate;

public class ExpiredMedicationException extends Exception {
    private final String batchNumber;
    private final LocalDate expiryDate;
    private final long daysOverdue;

    public ExpiredMedicationException(String batchNumber,
                                      LocalDate expiryDate,
                                      long daysOverdue) {
        super(String.format("Партия %s просрочена на %d дней (срок: %s)",
                batchNumber, daysOverdue, expiryDate));
        this.batchNumber = batchNumber;
        this.expiryDate = expiryDate;
        this.daysOverdue = daysOverdue;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public long getDaysOverdue() {
        return daysOverdue;
    }
}
