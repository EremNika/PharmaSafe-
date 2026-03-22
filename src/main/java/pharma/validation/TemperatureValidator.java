package pharma.validation;

import java.time.LocalDate;

import pharma.exception.TemperatureViolationException;
import pharma.model.Medication;
import pharma.model.PharmacyStorage;

public class TemperatureValidator {

    public void validateStorageConditions(Medication medication,
                                          PharmacyStorage storage)
            throws TemperatureViolationException {
        // TODO: занятие 4 - проверка canStore и диапазона температур
        if(! storage.canStore(medication) && storage.isTemperatureViolation()){
            throw new TemperatureViolationException(storage.getCurrentTemperature(), storage.getMinTemp(),storage.getMaxTemp());
        }
       
    }
}
