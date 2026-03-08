package pharma.validation;

import pharma.exception.InvalidPrescriptionException;

import java.time.LocalDate;

public class PrescriptionValidator {

    public void validatePrescriptionData(String number,
                                         String doctor,
                                         LocalDate date) throws InvalidPrescriptionException {
        // TODO: занятие 4 - проверка формата номера, дата не в будущем
    }

    public void validatePrescriptionDate(LocalDate date) throws InvalidPrescriptionException {
        // TODO: занятие 4 - рецепт действителен 60 дней
    }
}
