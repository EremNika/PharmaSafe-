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
import java.util.stream.Collectors;

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
        
        if (!storage.canStore(medication)) {
            throw new IllegalArgumentException("Хранилище не подходит для данного препарата");
        }

        // TODO: занятие 2 ДЗ-2 - проверка уникальности serialNumber (особенно для наркотических)
       if(findBatchBySerial(serialNumber)!=null){
        throw new IllegalArgumentException(String.format("Партия с серийным номером %s уже существует",serialNumber));
       };
    }

    public List<BatchRecord> getBatchesByMedication(String medicationId) {
        // TODO: занятие 2 - вернуть все партии препарата
        List<BatchRecord> batchRecords=batchesByMedication.get(medicationId);
        if(batchRecords==null){
            return new ArrayList<>();
        }
        return batchRecords;
    }

    public List<BatchRecord> getBatchesExpiringBefore(LocalDate date) {
        // TODO: занятие 2 - фильтрация по expiryDate
        
        return batchesByMedication.values()
                                .stream()
                                .flatMap(List::stream)
                                .filter(b->b.getExpiryDate().isBefore(date))
                                .collect(Collectors.toList());        
    }

    public int getTotalStock(String medicationId) {
        // TODO: занятие 2 - сумма remaining
        List<BatchRecord>batches=batchesByMedication.get(medicationId);
        
        int sum=0;

        for(BatchRecord batch:batches){
            sum+=batch.getRemaining();
        }     
        return sum ;
    }

    public BatchRecord findBatchBySerial(String serialNumber) {
        // TODO: занятие 2 - поиск по серийному номеру
        List<BatchRecord>batches=batchesByMedication.get(serialNumber);
        for(BatchRecord batch:batches){
            if(batch.getSerialNumber()==serialNumber)return batch;
        }
        return null;
    }

    public void transferBatch(String batchId, String toStorageId) {
        // TODO: занятие 2 ДЗ-2 - проверка canStore в новом помещении
        BatchRecord batch=findBatchBySerial(batchId);
        PharmacyStorage newStorage=batch.getStorage();
        if(!newStorage.canStore(batch.getMedication())){
            throw new IllegalArgumentException(
                String.format("Новое хранилище %s не подходит для препарата %s (требуется режим %s)",
                    newStorage.getName(), 
                    batch.getMedication().getName(),
                    batch.getMedication().getTemperatureMode())
            );
        }

    }

    public int blockExpiredBatches() {
        // TODO: занятие 2 ДЗ-2 - автоматическая блокировка isExpired()
        int blockedCount=0;
        for(List<BatchRecord> batches:batchesByMedication.values()){
            for(BatchRecord batch:batches){
                if(!batch.isExpired()){
                    batch.block("Истёк срок годности");
                }
                blockedCount++;
            }
        }
        return blockedCount;
    }

    public void writeOff(String batchId, int quantity, String reason) {
        // TODO: занятие 5 - списание (брак, просрочка)
    }

    public String getStockReport(String medicationId) {
        // TODO: занятие 5 - остатки по партиям, возвращать форматированный отчёт
        return "";
    }
}
