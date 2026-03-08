package pharma.validation;

import pharma.exception.ExpiredMedicationException;
import pharma.model.BatchRecord;

public class ExpiryValidator {

    public void validateBatchExpiry(BatchRecord batch) throws ExpiredMedicationException {
        // TODO: занятие 4 - блокировка за 30 дней до срока
    }
}
