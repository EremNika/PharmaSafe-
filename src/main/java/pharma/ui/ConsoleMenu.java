package pharma.ui;

import pharma.model.BaseEntity;
import pharma.model.BatchRecord;
import pharma.model.Medication;
import pharma.model.PharmacyStorage;
import pharma.model.StorageType;
import pharma.model.TemperatureMode;
import pharma.service.DispensingService;
import pharma.service.ExpiryControlService;
import pharma.service.InventoryService;

import java.io.Console;
import java.lang.runtime.TemplateRuntime;
import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleMenu {

    private final Scanner scanner;
    private final InventoryService inventoryService;
    private final DispensingService dispensingService;
    private final ExpiryControlService expiryControlService;

    public ConsoleMenu(InventoryService inventoryService,
                       DispensingService dispensingService,
                       ExpiryControlService expiryControlService) {
        this.scanner = new Scanner(System.in);
        this.inventoryService = inventoryService;
        this.dispensingService = dispensingService;
        this.expiryControlService = expiryControlService;
    }

    public void start() {
        while (true) {
            printMenu();
            int choice = readInt("Выберите пункт: ");

            switch (choice) {
                case 1:
                    // TODO: занятие 3 ДЗ-3 - Добавить препарат
                    break;
                case 2:
                    // TODO: занятие 3 ДЗ-3 - Создать помещение
                    break;
                case 3:
                    // TODO: занятие 2 ДЗ-2 - Принять партию
                    System.out.println("\n Ввод информации о лекарстве");
                    String nameMed = readString("Введите название лекарства: ");
                    String innMed = readString("Введите ИНН: ");
                    String dosageMed = readString("Введите дозировку: ");
                    String formMed = readString("Введите лекарственную форму: ");
                    
                    boolean isPrescription = Boolean.getBoolean("Нужен ли рецепт? (true/false): ");
                    boolean isNarcotic = Boolean.getBoolean("Относится ли к наркотическим? (true/false): ");
                    boolean isPsychotropic = Boolean.getBoolean("Относится ли к психотропным? (true/false): ");
                    
                    System.out.println("Выберите температуру хранения:");
                    System.out.println("1 - комнатная");
                    System.out.println("2 - прохладная");
                    System.out.println("3 - холодильник");
                    System.out.println("4 - морозильная камера");
                    System.out.println("5 - сверхнизкая (-50)");
                    int temperatureChoice = readInt("Ваш выбор (номер): ");
                    
                    TemperatureMode temperatureMode;
                    switch (temperatureChoice) {
                        case 1:
                            temperatureMode = TemperatureMode.ROOM_TEMP;
                            break;
                        case 2:
                            temperatureMode = TemperatureMode.COOL;
                            break;
                        case 3:
                            temperatureMode = TemperatureMode.REFRIGERATOR;
                            break;
                        case 4:
                            temperatureMode = TemperatureMode.FROZEN;
                            break;
                        case 5:
                            temperatureMode = TemperatureMode.MINUS_50;
                            break;
                        default:
                            System.out.println("Неверный выбор, установлена комнатная температура");
                            temperatureMode = TemperatureMode.ROOM_TEMP;
                    }
                    
                    int shelfLifeDays = readInt("Введите срок хранения (в днях): ");
                     
                    String medicationId = "MED_" + System.currentTimeMillis();
                    Medication medication = new Medication(
                        medicationId, nameMed, innMed, dosageMed, formMed,
                        isPrescription, isNarcotic, isPsychotropic, temperatureMode, shelfLifeDays
                    );
                     
                    System.out.println("\n Ввод информации о складе");
                    String storageId = "STR_" + System.currentTimeMillis();
                    String storageName = readString("Введите название склада: ");                    
                    System.out.println("Выберите тип хранилища:");
                    System.out.println("1 - торговый зал");
                    System.out.println("2 - холодильник");
                    System.out.println("3 - морозильная камера");
                    System.out.println("4 - сейф (безопасное хранение)");
                    System.out.println("5 - складское помещение");
                    int storageType = readInt("Ваш выбор: ");
                    
                    System.out.println("Выберите температуру в хранилище:");
                    System.out.println("1 - комнатная");
                    System.out.println("2 - прохладная");
                    System.out.println("3 - холодильник");
                    System.out.println("4 - морозильная камера");
                    System.out.println("5 - сверхнизкая (-50)");
                    int storageTempChoice = readInt("Ваш выбор: ");
                    
                    StorageType storageTypes;
                    switch (storageType) {
                        case 1: storageTypes = StorageType.TRADE_HALL; break;
                        case 2: storageTypes = StorageType.REFRIGERATOR_UNIT; break;
                        case 3: storageTypes = StorageType.FREEZER; break;
                        case 4: storageTypes = StorageType.SAFE; break;
                        case 5: storageTypes = StorageType.WAREHOUSE; break;
                        default: storageTypes = StorageType.TRADE_HALL;
                    }
                    
                    TemperatureMode storageTemperature;
                    switch (storageTempChoice) {
                        case 1: storageTemperature = TemperatureMode.ROOM_TEMP; break;
                        case 2: storageTemperature = TemperatureMode.COOL; break;
                        case 3: storageTemperature = TemperatureMode.REFRIGERATOR; break;
                        case 4: storageTemperature = TemperatureMode.FROZEN; break;
                        case 5: storageTemperature = TemperatureMode.MINUS_50; break;
                        default: storageTemperature = TemperatureMode.ROOM_TEMP;
                    }
                    
                    double minTemp = Double.parseDouble("Введите минимальную температуру (°C): ");
                    double maxTemp = Double.parseDouble("Введите максимальную температуру (°C): ");
                    int capacity = readInt("Введите вместимость (количество упаковок): ");
                    
                    PharmacyStorage pharmacyStorage = new PharmacyStorage(
                        storageId, storageName, storageTypes, storageTemperature, 
                        minTemp, maxTemp, capacity
                    );
                     
                    System.out.println("\n Ввод информации о партии");
                    String batchId = "BATCH_" + System.currentTimeMillis();
                    int quantity = readInt("Введите количество упаковок в партии: ");
     
                    LocalDate manufactureDate = LocalDate.now();
                    LocalDate expiryDate = manufactureDate.plusDays(shelfLifeDays);
                    
                    BatchRecord batchRecord = new BatchRecord(
                        batchId, medication, storageId, storageName, quantity,
                        manufactureDate, expiryDate, pharmacyStorage
                    );
         
                    try {
                        inventoryService.getBatchesByMedication(batchId);
                        System.out.println(String.format("Партия %s успешно принята!",batchId));
                    } catch (Exception e) {
                        System.out.println("Ошибка при приеме партии: " + e.getMessage());
                    }
                    break;
                  
                case 4:
                    // TODO: занятие 5 - Создать рецептурный заказ
                    break;
                case 5:
                    // TODO: занятие 5 - Создать безрецептурный заказ
                    break;
                case 6:
                    // TODO: занятие 5 - Добавить позицию в заказ
                    break;
                case 7:
                    // TODO: занятие 5/6 - Верифицировать рецепт
                    break;
                case 8:
                    // TODO: занятие 5 - Собрать заказ (FEFO)
                    break;
                case 9:
                    // TODO: занятие 6 - Выдать лекарство
                    break;
                case 10:
                    // TODO: занятие 6 - Отклонить рецепт
                    break;
                case 11:
                    // TODO: занятие 5 - Отчет: истекающие сроки
                    break;
                case 0:
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== PharmaSafe ===");
        System.out.println("1. Добавить препарат");
        System.out.println("2. Создать помещение");
        System.out.println("3. Принять партию");
        System.out.println("4. Создать рецептурный заказ");
        System.out.println("5. Создать безрецептурный заказ");
        System.out.println("6. Добавить позицию в заказ");
        System.out.println("7. Верифицировать рецепт");
        System.out.println("8. Собрать заказ (FEFO)");
        System.out.println("9. Выдать лекарство");
        System.out.println("10. Отклонить рецепт");
        System.out.println("11. Отчёт: истекающие сроки");
        System.out.println("0. Выход");
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Введите число!");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }
}
