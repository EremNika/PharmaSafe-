package pharma.coldchain;

import pharma.logger.Logger;
import pharma.model.PharmacyStorage;

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
        // TODO: занятие 6 ДЗ-6 - уведомление при температурном нарушении
    }
}
