package pharma.service;

import pharma.logger.Logger;
import pharma.model.BatchRecord;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ExpiryControlService {

    private final InventoryService inventoryService;
    private final Logger logger;

    public ExpiryControlService(InventoryService inventoryService, Logger logger) {
        this.inventoryService = inventoryService;
        this.logger = logger;
    }

    public List<BatchRecord> getExpiringBatches(int days) {
        // TODO: занятие 5 - партии с истекающим сроком
        return new ArrayList<>();
    }

    public int blockNearExpiry(int daysBeforeExpiry) {
        // TODO: занятие 5 - блокировка за daysBeforeExpiry до срока
        return 0;
    }

    public String generateExpiryReport(Period period) {
        // TODO: занятие 5 - отчёт по срокам годности
        return "";
    }

    public void checkOpenedExpiry() {
        // TODO: занятие 5 - проверка срока после вскрытия
    }

    public void autoBlockExpired() {
        // TODO: занятие 5 ДЗ-5 - авто блокировка при старте системы
    }
}
