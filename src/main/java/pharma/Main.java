package pharma;

import pharma.logger.ConsoleLogger;
import pharma.logger.Logger;
import pharma.model.Medication;
import pharma.model.PharmacyStorage;
import pharma.model.PrescriptionOrder;
import pharma.model.StorageType;
import pharma.model.TemperatureMode;
import pharma.service.DispensingService;
import pharma.service.ExpiryControlService;
import pharma.service.InventoryService;
import pharma.ui.ConsoleMenu;
import pharma.validation.PrescriptionValidator;

public class Main {
    public static void main(String[] args) {
        Logger logger = new ConsoleLogger();

        InventoryService inventoryService = new InventoryService(logger);
        PrescriptionValidator prescriptionValidator = new PrescriptionValidator();
        DispensingService dispensingService =
                new DispensingService(inventoryService, prescriptionValidator, logger);
        ExpiryControlService expiryControlService =
                new ExpiryControlService(inventoryService, logger);

                // Занятие 1: создать 2 препарата (1 рецептурный, 1 безрецептурный), 2 помещения, протестировать canStore

                Medication amoxicillin = new Medication(
                    "MED001",
                    "Амоксициллин",
                    "Amoxicillin",
                    "500 мг",
                    "таблетки",
                    true, 
                    false, 
                    false,  
                    TemperatureMode.ROOM_TEMP,  
                    730    
                );

                Medication vitaminC = new Medication(
                    "MED002",
                    "Витамин С",
                    "Ascorbic acid",
                    "1000 мг",
                    "шипучие таблетки",
                    false, 
                    false,  
                    false,  
                    TemperatureMode.ROOM_TEMP,  
                    365    
                );

        

        ConsoleMenu menu = new ConsoleMenu(inventoryService, dispensingService, expiryControlService);
        // TODO: занятие 3 - запуск основного меню
    }
}
