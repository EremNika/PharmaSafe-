package pharma.validation;

import pharma.exception.InvalidPrescriptionException;
import pharma.model.PrescriptionOrder;

import java.time.LocalDate;
import java.time.Period;

public class PrescriptionValidator {

    public void validatePrescriptionData(String number,
                                         String doctor,
                                         LocalDate date) throws InvalidPrescriptionException {
        // TODO: занятие 4 - проверка формата номера, дата не в будущем
        if(number==null){
            throw new InvalidPrescriptionException("Номер не может быть пустым", number, date);
        }
        if(date==null){
            throw new InvalidPrescriptionException("Дата не может быть пустой", number, date);
        }else if(date.isAfter(LocalDate.now())){
            throw new InvalidPrescriptionException("Указана дата в будущем", number, date);
        }
    }

    public void validatePrescriptionDate(LocalDate date) throws InvalidPrescriptionException {
        // TODO: занятие 4 - рецепт действителен 60 дней
        if(Period.between(date, LocalDate.now()).getDays()>60){
            throw new InvalidPrescriptionException("Рецепт не действителен",null,date);
        }
    }
}
