package pharma.exception;

import java.time.LocalDate;

public class InvalidPrescriptionException extends Exception {
    private final String prescriptionNumber;
    private final LocalDate prescriptionDate;

    public InvalidPrescriptionException(String message,
                                        String prescriptionNumber,
                                        LocalDate prescriptionDate) {
        super(message);
        this.prescriptionNumber = prescriptionNumber;
        this.prescriptionDate = prescriptionDate;
    }

    public String getPrescriptionNumber() {
        return prescriptionNumber;
    }

    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }
}
