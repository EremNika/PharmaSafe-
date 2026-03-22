package pharma.service;

import pharma.exception.InvalidPrescriptionException;
import pharma.logger.Logger;
import pharma.model.Medication;
import pharma.model.PrescriptionOrder;
import pharma.model.PrescriptionStatus;
import pharma.validation.PrescriptionValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispensingService {

    private final Map<String, PrescriptionOrder> orders;
    private final InventoryService inventoryService;
    private final PrescriptionValidator prescriptionValidator;
    private final Logger logger;

    public DispensingService(InventoryService inventoryService,
                             PrescriptionValidator prescriptionValidator,
                             Logger logger) {
        this.orders = new HashMap<>();
        this.inventoryService = inventoryService;
        this.prescriptionValidator = prescriptionValidator;
        this.logger = logger;
    }

    public PrescriptionOrder createPrescriptionOrder(String customerName,
                                                     String prescriptionNumber,
                                                     String doctorName,
                                                     LocalDate prescriptionDate)
            throws InvalidPrescriptionException {
        // TODO: занятие 5 - рецептурный заказ, валидация рецепта
        return null;
    }

    public PrescriptionOrder createOTCOrder(String customerName) {
        // TODO: занятие 5 - безрецептурный заказ
        return null;
    }

    public void addItemToOrder(String orderId,
                               String medicationId,
                               int quantity) {
        // TODO: занятие 5 - добавить позицию, зафиксировать цену на момент добавления
    }

    public void verifyPrescription(String orderId,
                                   String pharmacistName) throws InvalidPrescriptionException {
        // TODO: занятие 5 / 4 ДЗ-4 - интеграция PrescriptionValidator, смена статуса VERIFIED
        prescriptionValidator.validatePrescriptionData(orderId, pharmacistName, null);
        
    }

    public void assembleOrder(String orderId) {
        // TODO: занятие 5 - для каждой позиции заказа найти подходящие BatchRecord
        // TODO: сортировать по expiryDate (FEFO), зарезервировать в порядке срока годности
    }

    public void pharmacistCheck(String orderId,
                                String pharmacistName) {
        // TODO: занятие 5 - статус CHECKED, логирование проверки
    }

    public void assignChecker(String orderId,
                              String checkerName) {
        // TODO: занятие 5 / 4 ДЗ-4 - двойная проверка для наркотических
    }

    public void issueMedication(String orderId) {
        // TODO: занятие 6 - проверка: все позиции собраны, CHECKED пройден
        // TODO: для наркотических — checkerName не null, списание remaining
    }

    public void rejectPrescription(String orderId, String reason) {
        // TODO: занятие 6 - любой статус до ISSUED, освобождение резервов
    }

    public PrescriptionOrder getOrderById(String orderId) {
        // TODO: занятие 2 - поиск в orders
        for(PrescriptionOrder order : orders.values()){
            if(order.getId().equals(orderId)){
                return order;
            }
        }

        return null;
    }

    public List<PrescriptionOrder> getOrdersByStatus(PrescriptionStatus status) {
        // TODO: занятие 5 - фильтрация по статусу
        return new ArrayList<>();
    }
}
