package pharma.coldchain;

import pharma.logger.Logger;
import pharma.model.PharmacyStorage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TemperatureMonitor {

    private final List<PharmacyStorage> storages;
    private final Logger logger;

    public TemperatureMonitor(List<PharmacyStorage> storages, Logger logger) {
        this.storages = storages;
        this.logger = logger;
    }

    public void checkAll() {
        
        // TODO: занятие 3 ДЗ-3 - периодическая проверка isTemperatureViolation()
        if(storages==null||storages.isEmpty()){
            logger.log("Нет хранилищ для проверки");
            return;
        }

        logger.log(String.format("Периодическая проверка температуры: %s", LocalDateTime.now()));
        
        int violationsCount=0;
        for(PharmacyStorage storage:storages){
            String storageInfo=String.format("Хранилище:%s - %s", storage.getName(),storage.getId());
            if(storage.isTemperatureViolation()){
                violationsCount++;
                String violationMsg=String.format("Нарушение! %s, текущая температура: %s, диапазон [%s... %s]",storageInfo,storage.getCurrentTemperature(),storage.getMinTemp(),storage.getMaxTemp());
                logger.log(violationMsg);
            }
            else{
                String okMsg=String.format("Допустимо! температура %s в диапазоне [%s...%s]",storage.getCurrentTemperature(),storage.getMinTemp(),storage.getMaxTemp());
                logger.log(okMsg);
            }
        }
        // TODO: занятие 6 ДЗ-6 - уведомление при температурном нарушении
    }
}
