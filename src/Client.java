import java.util.Scanner;

public class Client implements TaxiAppInterface {
    private static String[] carClass = new String[1];
    private static String[] address = new String[1];
    private boolean dataEntered = false;

    public static void clients() {
        Client client = new Client();
        client.runApp();
    }

    public void runApp() {
        showWelcomeMessage();
        processUserInput();
        displayResults();
    }

    @Override
    public void showWelcomeMessage() {
        System.out.println("\n🚖 СЕРВИС ЗАКАЗА ТАКСИ");
        System.out.println("========================");
    }

    @Override
    public void processUserInput() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Введите класс автомобиля:");
        System.out.println("(эконом, комфорт, бизнес, минивен)");
        System.out.print("Класс: ");
        carClass[0] = scan.nextLine();

        System.out.println("\nВведите адрес подачи:");
        System.out.print("Адрес: ");
        address[0] = scan.nextLine();

        dataEntered = true;

        // Переходим к выбору заказа
        proceedToOrder();
    }

    @Override
    public void displayResults() {
        if (dataEntered) {
            System.out.println("\n========================");
            System.out.println("✅ Данные сохранены!");
            System.out.println("Класс: " + carClass[0]);
            System.out.println("Адрес: " + address[0]);
            System.out.println("========================");
        }
    }

    @Override
    public boolean isValidInput(String input) {
        // Простая валидация для демонстрации
        return input != null && !input.trim().isEmpty();
    }

    private void proceedToOrder() {
        System.out.println("\n🔜 Переход к выбору заказа...");
        zaka();
    }

    public static void zaka() {
        Scanner scan = new Scanner(System.in);

        System.out.println("\n📋 ДОСТУПНЫЕ ЗАКАЗЫ:");
        System.out.println("========================");
        for (int i = 0; i < carClass.length; i++) {
            System.out.println("Заказ #" + (i + 1));
            System.out.println("Класс: " + carClass[i]);
            System.out.println("Адрес: " + address[i]);
            System.out.println("========================");
        }

        System.out.print("Введите номер заказа: ");
        int orderNumber = scan.nextInt();

        System.out.print("Введите расстояние (км): ");
        int distance = scan.nextInt();

        calculatePrice(distance);
    }

    public static void calculatePrice(int distance) {
        int price = 0;

        if (distance > 250) price = 3000;
        else if (distance > 200) price = 2500;
        else if (distance > 180) price = 2000;
        else if (distance > 150) price = 1500;
        else if (distance > 100) price = 1100;
        else if (distance > 95) price = 950;
        else if (distance > 70) price = 750;
        else if (distance > 50) price = 500;
        else if (distance > 35) price = 350;
        else if (distance > 20) price = 200;
        else if (distance > 10) price = 100;
        else price = 50; // минимальная цена

        System.out.println("\n💰 РАСЧЕТ СТОИМОСТИ:");
        System.out.println("========================");
        System.out.println("Расстояние: " + distance + " км");
        System.out.println("Стоимость поездки: " + price + " руб.");
        System.out.println("========================");
    }
}
