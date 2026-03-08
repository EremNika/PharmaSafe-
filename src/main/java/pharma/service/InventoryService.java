package pharma.service;

import pharma.logger.Logger;
import pharma.model.BatchRecord;
import pharma.model.Medication;
import pharma.model.PharmacyStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryService {

    private final Map<String, List<BatchRecord>> batchesByMedication;
    private final Logger logger;

    public InventoryService(Logger logger) {
        this.batchesByMedication = new HashMap<>();
        this.logger = logger;
    }

    public void acceptBatch(Medication medication,
                            String serialNumber,
                            String batchNumber,
                            int quantity,
                            LocalDate expiryDate,
                            PharmacyStorage storage) {
        // TODO: занятие 2 - Map<String, List<BatchRecord>> по препаратам, проверка canStore
        // TODO: занятие 2 ДЗ-2 - проверка уникальности serialNumber (особенно для наркотических)
    }

    public List<BatchRecord> getBatchesByMedication(String medicationId) {
        // TODO: занятие 2 - вернуть все партии препарата
        return new ArrayList<>();
    }

    public List<BatchRecord> getBatchesExpiringBefore(LocalDate date) {
        // TODO: занятие 2 - фильтрация по expiryDate
        return new ArrayList<>();
    }

    public int getTotalStock(String medicationId) {
        // TODO: занятие 2 - сумма remaining
        return 0;
    }

    public BatchRecord findBatchBySerial(String serialNumber) {
        // TODO: занятие 2 - поиск по серийному номеру
        return null;
    }

    public void transferBatch(String batchId, String toStorageId) {
        // TODO: занятие 2 ДЗ-2 - проверка canStore в новом помещении
    }

    public int blockExpiredBatches() {
        // TODO: занятие 2 ДЗ-2 - автоматическая блокировка isExpired()
        return 0;
    }

    public void writeOff(String batchId, int quantity, String reason) {
        // TODO: занятие 5 - списание (брак, просрочка)
    }

    public String getStockReport(String medicationId) {
        // TODO: занятие 5 - остатки по партиям, возвращать форматированный отчёт
        return "";
    }
}
