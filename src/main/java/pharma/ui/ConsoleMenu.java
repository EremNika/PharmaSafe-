package pharma.ui;

import pharma.service.DispensingService;
import pharma.service.ExpiryControlService;
import pharma.service.InventoryService;

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
