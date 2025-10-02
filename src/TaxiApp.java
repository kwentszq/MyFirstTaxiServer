import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaxiApp extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    // Компоненты главного экрана
    private JPanel welcomePanel;
    private JButton driverBtn;
    private JButton passengerBtn;
    private JButton exitBtn;

    // Компоненты регистрации водителя
    private JPanel driverPanel;
    private JTextField carBrandField;
    private JTextField carNumberField;
    private JTextField driverNameField;
    private JComboBox<String> carClassCombo;
    private JButton driverSubmitBtn;
    private JButton driverBackBtn;

    // Компоненты заказа такси
    private JPanel passengerPanel;
    private JComboBox<String> passengerClassCombo;
    private JTextField addressField;
    private JButton passengerSubmitBtn;
    private JButton passengerBackBtn;

    // Компоненты расчета стоимости
    private JPanel pricePanel;
    private JSpinner distanceSpinner;
    private JButton calculateBtn;
    private JButton priceBackBtn;
    private JLabel priceLabel;

    public TaxiApp() {
        initializeApp();
        createWelcomeScreen();
        createDriverScreen();
        createPassengerScreen();
        createPriceScreen();

        setupMainPanel();
    }

    private void initializeApp() {
        setTitle(" Taxi App - Сервис заказа такси");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
    }

    private void createWelcomeScreen() {
        welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBackground(new Color(240, 240, 240));

        // Заголовок
        JLabel titleLabel = new JLabel(" ДОБРО ПОЖАЛОВАТЬ В TAXI APP!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Панель кнопок
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));

        driverBtn = createStyledButton("Я водитель", new Color(166, 148, 25));
        passengerBtn = createStyledButton(" Я пассажир", new Color(0, 236, 100));
        exitBtn = createStyledButton(" Выйти", new Color(220, 20, 60));

        driverBtn.addActionListener(this);
        passengerBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        buttonPanel.add(driverBtn);
        buttonPanel.add(passengerBtn);
        buttonPanel.add(exitBtn);

        welcomePanel.add(titleLabel, BorderLayout.NORTH);
        welcomePanel.add(buttonPanel, BorderLayout.CENTER);

        mainPanel.add(welcomePanel, "WELCOME");
    }

    private void createDriverScreen() {
        driverPanel = new JPanel(new GridBagLayout());
        driverPanel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel(" РЕГИСТРАЦИЯ ВОДИТЕЛЯ", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        driverPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; gbc.gridx = 0;
        driverPanel.add(new JLabel("Марка автомобиля:"), gbc);

        gbc.gridx = 1;
        carBrandField = new JTextField(20);
        driverPanel.add(carBrandField, gbc);

        gbc.gridy = 2; gbc.gridx = 0;
        driverPanel.add(new JLabel("Гос. номер:"), gbc);

        gbc.gridx = 1;
        carNumberField = new JTextField(20);
        driverPanel.add(carNumberField, gbc);

        gbc.gridy = 3; gbc.gridx = 0;
        driverPanel.add(new JLabel("Ваше имя:"), gbc);

        gbc.gridx = 1;
        driverNameField = new JTextField(20);
        driverPanel.add(driverNameField, gbc);

        gbc.gridy = 4; gbc.gridx = 0;
        driverPanel.add(new JLabel("Класс авто:"), gbc);

        gbc.gridx = 1;
        String[] classes = {"Эконом", "Комфорт", "Бизнес", "Минивен"};
        carClassCombo = new JComboBox<>(classes);
        driverPanel.add(carClassCombo, gbc);

        // Кнопки
        JPanel driverButtonPanel = new JPanel(new FlowLayout());
        driverSubmitBtn = createStyledButton(" Подтвердить", new Color(60, 179, 113));
        driverBackBtn = createStyledButton(" Назад", new Color(169, 169, 169));

        driverSubmitBtn.addActionListener(this);
        driverBackBtn.addActionListener(this);

        driverButtonPanel.add(driverSubmitBtn);
        driverButtonPanel.add(driverBackBtn);

        gbc.gridy = 5; gbc.gridx = 0; gbc.gridwidth = 2;
        driverPanel.add(driverButtonPanel, gbc);

        mainPanel.add(driverPanel, "DRIVER");
    }

    private void createPassengerScreen() {
        passengerPanel = new JPanel(new GridBagLayout());
        passengerPanel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel(" ЗАКАЗ ТАКСИ", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        passengerPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; gbc.gridx = 0;
        passengerPanel.add(new JLabel("Класс авто:"), gbc);

        gbc.gridx = 1;
        String[] classes = {"Эконом", "Комфорт", "Бизнес", "Минивен"};
        passengerClassCombo = new JComboBox<>(classes);
        passengerPanel.add(passengerClassCombo, gbc);

        gbc.gridy = 2; gbc.gridx = 0;
        passengerPanel.add(new JLabel("Адрес подачи:"), gbc);

        gbc.gridx = 1;
        addressField = new JTextField(20);
        passengerPanel.add(addressField, gbc);

        // Кнопки
        JPanel passengerButtonPanel = new JPanel(new FlowLayout());
        passengerSubmitBtn = createStyledButton(" Найти такси", new Color(70, 130, 180));
        passengerBackBtn = createStyledButton(" Назад", new Color(169, 169, 169));

        passengerSubmitBtn.addActionListener(this);
        passengerBackBtn.addActionListener(this);

        passengerButtonPanel.add(passengerSubmitBtn);
        passengerButtonPanel.add(passengerBackBtn);

        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2;
        passengerPanel.add(passengerButtonPanel, gbc);

        mainPanel.add(passengerPanel, "PASSENGER");
    }

    private void createPriceScreen() {
        pricePanel = new JPanel(new GridBagLayout());
        pricePanel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel(" РАСЧЕТ СТОИМОСТИ", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        pricePanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; gbc.gridx = 0;
        pricePanel.add(new JLabel("Расстояние (км):"), gbc);

        gbc.gridx = 1;
        SpinnerModel spinnerModel = new SpinnerNumberModel(10, 1, 500, 1);
        distanceSpinner = new JSpinner(spinnerModel);
        pricePanel.add(distanceSpinner, gbc);

        gbc.gridy = 2; gbc.gridx = 0; gbc.gridwidth = 2;
        calculateBtn = createStyledButton(" Рассчитать стоимость", new Color(255, 140, 0));
        calculateBtn.addActionListener(this);
        pricePanel.add(calculateBtn, gbc);

        gbc.gridy = 3;
        priceLabel = new JLabel("Стоимость: - руб.", JLabel.CENTER);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        priceLabel.setForeground(new Color(0, 100, 0));
        pricePanel.add(priceLabel, gbc);

        gbc.gridy = 4;
        priceBackBtn = createStyledButton("🏠 Главное меню", new Color(169, 169, 169));
        priceBackBtn.addActionListener(this);
        pricePanel.add(priceBackBtn, gbc);

        mainPanel.add(pricePanel, "PRICE");
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        return button;
    }

    private void setupMainPanel() {
        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == driverBtn) {
            cardLayout.show(mainPanel, "DRIVER");
        } else if (e.getSource() == passengerBtn) {
            cardLayout.show(mainPanel, "PASSENGER");
        } else if (e.getSource() == exitBtn) {
            System.exit(0);
        } else if (e.getSource() == driverBackBtn || e.getSource() == passengerBackBtn) {
            cardLayout.show(mainPanel, "WELCOME");
        } else if (e.getSource() == driverSubmitBtn) {
            registerDriver();
        } else if (e.getSource() == passengerSubmitBtn) {
            orderTaxi();
        } else if (e.getSource() == calculateBtn) {
            calculatePrice();
        } else if (e.getSource() == priceBackBtn) {
            cardLayout.show(mainPanel, "WELCOME");
        }
    }

    private void registerDriver() {
        if (carBrandField.getText().trim().isEmpty() ||
                carNumberField.getText().trim().isEmpty() ||
                driverNameField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Пожалуйста, заполните все поля!",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String message = "✅ Регистрация успешна!\n\n" +
                "Марка: " + carBrandField.getText() + "\n" +
                "Номер: " + carNumberField.getText() + "\n" +
                "Имя: " + driverNameField.getText() + "\n" +
                "Класс: " + carClassCombo.getSelectedItem();

        JOptionPane.showMessageDialog(this, message, "Успех", JOptionPane.INFORMATION_MESSAGE);
        cardLayout.show(mainPanel, "PRICE");
    }

    private void orderTaxi() {
        if (addressField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Пожалуйста, введите адрес!",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String message = "🚖 Такси заказано!\n\n" +
                "Класс: " + passengerClassCombo.getSelectedItem() + "\n" +
                "Адрес: " + addressField.getText() + "\n\n" +
                "Водитель будет через 5-10 минут!";

        JOptionPane.showMessageDialog(this, message, "Заказ принят", JOptionPane.INFORMATION_MESSAGE);
        cardLayout.show(mainPanel, "PRICE");
    }

    private void calculatePrice() {
        int distance = (Integer) distanceSpinner.getValue();
        int price = calculatePriceLogic(distance);

        priceLabel.setText("Стоимость: " + price + " руб.");
        priceLabel.setForeground(new Color(0, 100, 0));
    }

    private int calculatePriceLogic(int distance) {
        if (distance > 250) return 3000;
        else if (distance > 200) return 2500;
        else if (distance > 180) return 2000;
        else if (distance > 150) return 1500;
        else if (distance > 100) return 1100;
        else if (distance > 95) return 950;
        else if (distance > 70) return 750;
        else if (distance > 50) return 500;
        else if (distance > 35) return 350;
        else if (distance > 20) return 200;
        else if (distance > 10) return 100;
        else return 50;
    }

    public static void main(String[] args) {
        // Установка современного вида для Swing (исправленная версия)
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Запуск в потоке обработки событий
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaxiApp();
            }
        });
    }
}
