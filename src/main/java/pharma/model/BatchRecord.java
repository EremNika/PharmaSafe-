package pharma.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.Logger;

public class BatchRecord extends BaseEntity {
    private static final Logger LOGGER = Logger.getLogger(BatchRecord.class.getName());

    private Medication medication;
    private String serialNumber;
    private String batchNumber;
    private int quantity;
    private int remaining;
    private LocalDate manufacturingDate;
    private LocalDate expiryDate;
    private PharmacyStorage storage;
    private LocalDate openedDate;
    private boolean isBlocked;

    public BatchRecord(String batchId,
                       Medication medication,
                       String serialNumber,
                       String batchNumber,
                       int quantity,
                       LocalDate manufacturingDate,
                       LocalDate expiryDate,
                       PharmacyStorage storage) {
        super(batchId);
        this.medication = medication;
        this.serialNumber = serialNumber;
        this.batchNumber = batchNumber;
        this.quantity = quantity;
        this.remaining = quantity;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
        this.storage = storage;
        this.isBlocked = false;
    }

    // TODO: занятие 1 ДЗ-1 - реализовать методы ниже

    public boolean isExpired() {
        // TODO: занятие 1 ДЗ-1 - проверить по expiryDate
        if(expiryDate.isBefore(LocalDate.now())){
            return true;
        }
        return false;
    }

    public boolean isOpenedExpired() {
        // TODO: занятие 1 ДЗ-1 - проверить срок после вскрытия openedDate
        long daysSinceOpened=ChronoUnit.DAYS.between(openedDate, LocalDate.now());

        if(daysSinceOpened>medication.getShelfLifeDays()){
            return true;
        }
        return false;
    }

    public long getDaysUntilExpiry() {
        // TODO: занятие 1 ДЗ-1 - вернуть количество дней до срока годности
        long quantity=ChronoUnit.DAYS.between(LocalDate.now(),expiryDate);
        return quantity;
    }

    public void openPackage() {
        // TODO: занятие 1 ДЗ-1 - установить openedDate = today
        openedDate=LocalDate.now();
    }

    public void block(String reason) {
        // TODO: занятие 1 ДЗ-1 - установить isBlocked = true, залогировать reason
        isBlocked=true;
        LOGGER.info("Блокировка");
    }

    public Medication getMedication() {
        return medication;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public PharmacyStorage getStorage() {
        return storage;
    }

    public void setStorage(PharmacyStorage storage) {
        this.storage = storage;
    }

    public LocalDate getOpenedDate() {
        return openedDate;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    @Override
    public String toString() {
        // TODO: занятие 1 ДЗ-1 - добавить больше информации
        String res= String.format("BatchRecord[ %s ] %s batch= %s, remaining= %s, expiryDate= %s, openedDate= %s",id,medication.getName(),batchNumber,remaining,expiryDate,openedDate);
        return res;
        // return "BatchRecord[" + id + "] " + medication.getName() +
        //         " batch=" + batchNumber + ", remaining=" + remaining;
    }
}
