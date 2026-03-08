package pharma.coldchain;

import pharma.logger.Logger;
import pharma.model.PharmacyStorage;

import java.util.List;

public class ColdChainService {

    private final List<PharmacyStorage> storages;
    private final Logger logger;

    public ColdChainService(List<PharmacyStorage> storages, Logger logger) {
        this.storages = storages;
        this.logger = logger;
    }

    public void monitorTemperature() {
        // TODO: занятие 5 - интеграция TemperatureMonitor, логирование нарушений
    }

    public void alertIfViolation(String storageId) {
        // TODO: занятие 6 ДЗ-6 - поиск склада и уведомление при нарушении
    }
}
