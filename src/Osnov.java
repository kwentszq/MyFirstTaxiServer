import java.util.Scanner;

public class Osnov implements TaxiAppInterface {
    static String[] driverData = new String[4];
    private boolean registrationComplete = false;

    public static void userr() {
        Osnov driver = new Osnov();
        driver.runApp();
    }

    public void runApp() {
        showWelcomeMessage();
        processUserInput();
        displayResults();
    }

    @Override
    public void showWelcomeMessage() {
        System.out.println("\n👤 РЕГИСТРАЦИЯ ВОДИТЕЛЯ");
        System.out.println("========================");
    }

    @Override
    public void processUserInput() {
        Scanner scan = new Scanner(System.in);

        String[] questions = {
                "Марка автомобиля:",
                "Государственный номер:",
                "Ваше имя:",
                "Класс обслуживания:"
        };

        for (int i = 0; i < questions.length; i++) {
            System.out.print(questions[i] + " ");
            driverData[i] = scan.nextLine();
        }

        // Проверка данных
        if (validateDriverData()) {
            registrationComplete = true;
            showConfirmation();
        }
    }

    @Override
    public void displayResults() {
        if (registrationComplete) {
            System.out.println("\n========================");
            System.out.println("✅ Регистрация завершена!");
            System.out.println("Теперь вы можете принимать заказы");
            System.out.println("========================");
        }
    }

    @Override
    public boolean isValidInput(String input) {
        return input != null && !input.trim().isEmpty();
    }

    private boolean validateDriverData() {
        for (String data : driverData) {
            if (data == null || data.trim().isEmpty()) {
                System.out.println("❌ Ошибка: Все поля должны быть заполнены!");
                return false;
            }
        }
        return true;
    }

    private void showConfirmation() {
        Scanner scan = new Scanner(System.in);

        System.out.println("\n📋 ПРОВЕРЬТЕ ВАШИ ДАННЫЕ:");
        System.out.println("========================");
        for (int i = 0; i < driverData.length; i++) {
            System.out.println((i + 1) + ". " + driverData[i]);
        }

        System.out.println("\nВведите 'yes' для подтверждения или 'no' для изменения:");
        String confirmation = scan.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            System.out.println("✅ Данные подтверждены!");
            // Переход к заказам
            Client.zaka();
        } else {
            System.out.println("🔄 Повторение регистрации...");
            processUserInput();
        }
    }
}
