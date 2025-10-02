import java.util.Scanner;

public class Main implements TaxiAppInterface {
    private String userType;
    private boolean operationSuccess;

    public static void main(String[] args) {
        Main app = new Main();
        app.runApp();
    }

    public void runApp() {
        showWelcomeMessage();
        processUserInput();
        displayResults();
    }

    @Override
    public void showWelcomeMessage() {
        System.out.println("🚕 ДОБРО ПОЖАЛОВАТЬ В TAXI APP! 🚕");
        System.out.println("=================================");
        System.out.println("Быстро • Удобно • Надежно");
        System.out.println("=================================");
    }

    @Override
    public void processUserInput() {
        Scanner scan1 = new Scanner(System.in);

        System.out.println("\nВыберите тип пользователя:");
        System.out.println("👤 /user - Я водитель");
        System.out.println("🚖 /taxi - Я пассажир");
        System.out.println("❌ /exit - Выйти из приложения");
        System.out.print("\nВаш выбор: ");

        String res = scan1.nextLine();
        this.userType = res;

        if (isValidInput(res)) {
            switch (res) {
                case "/user":
                    System.out.println("\n👤 Регистрация водителя...");
                    operationSuccess = true;
                    Osnov.userr();
                    break;
                case "/taxi":
                    System.out.println("\n🚖 Заказ такси...");
                    operationSuccess = true;
                    Client.clients();
                    break;
                case "/exit":
                    System.out.println("\n👋 До свидания!");
                    operationSuccess = true;
                    System.exit(0);
                    break;
                default:
                    operationSuccess = false;
                    System.out.println("\n❌ Ошибка ввода!");
            }
        } else {
            operationSuccess = false;
        }
    }

    @Override
    public void displayResults() {
        System.out.println("\n=================================");
        if (operationSuccess) {
            System.out.println("✅ Операция выполнена успешно!");
            switch (userType) {
                case "/user":
                    System.out.println("📋 Ваши данные сохранены в системе");
                    break;
                case "/taxi":
                    System.out.println("🎯 Ваш заказ принят в обработку");
                    break;
            }
        } else {
            System.out.println("❌ Произошла ошибка при выполнении операции");
        }
        System.out.println("=================================");
    }

    @Override
    public boolean isValidInput(String input) {
        return input.equals("/user") || input.equals("/taxi") || input.equals("/exit");
    }
}
