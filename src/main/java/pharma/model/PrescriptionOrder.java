package pharma.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionOrder extends BaseEntity {
    private PrescriptionStatus status;
    private LocalDateTime createdAt;
    private List<DispenseLine> items;
    private String customerName;
    private String prescriptionNumber;
    private String doctorName;
    private LocalDate prescriptionDate;
    private String pharmacistName;
    private String checkerName;
    private List<String> verificationLog; // ДЗ-6: история проверок

    public PrescriptionOrder(String orderId,
                             String customerName,
                             String prescriptionNumber,
                             String doctorName,
                             LocalDate prescriptionDate) {
        super(orderId);
        this.status = PrescriptionStatus.CREATED;
        this.createdAt = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.customerName = customerName;
        this.prescriptionNumber = prescriptionNumber;
        this.doctorName = doctorName;
        this.prescriptionDate = prescriptionDate;
        this.verificationLog = new ArrayList<>();
    }

    public void addItem(DispenseLine line) {
        // TODO: занятие 1 ДЗ-1 - добавить позицию к заказу
    }

    public boolean requiresPrescription() {
        // TODO: занятие 1 ДЗ-1 - проверить наличие контролируемых препаратов
        return false;
    }

    public boolean requiresDoubleCheck() {
        // TODO: занятие 4 ДЗ-4 - isNarcotic || isPsychotropic
        return false;
    }

    public boolean canChangeStatus(PrescriptionStatus newStatus) {
        // TODO: занятие 4 - делегировать в status.canTransitionTo(newStatus)
        return false;
    }

    public void changeStatus(PrescriptionStatus newStatus) {
        // TODO: занятие 4 - проверить canChangeStatus, обновить статус, залогировать
    }

    public void assignPharmacist(String pharmacistName) {
        // TODO: занятие 6 ДЗ-6 - проверить обязательность для контролируемых препаратов
        this.pharmacistName = pharmacistName;
    }

    public void assignChecker(String checkerName) {
        // TODO: занятие 4 ДЗ-4 - двойная проверка для наркотических
        this.checkerName = checkerName;
    }

    public List<DispenseLine> getItems() {
        return items;
    }

    public PrescriptionStatus getStatus() {
        return status;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPrescriptionNumber() {
        return prescriptionNumber;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }

    public String getPharmacistName() {
        return pharmacistName;
    }

    public String getCheckerName() {
        return checkerName;
    }

    public List<String> getVerificationLog() {
        return verificationLog;
    }

    @Override
    public String toString() {
        // TODO: занятие 1 ДЗ-1 - улучшить формат
        return "PrescriptionOrder[" + id + "] " + customerName + " status=" + status;
    }
}
