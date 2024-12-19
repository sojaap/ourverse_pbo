import java.awt.Font;
import java.awt.Image;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import java.awt.CardLayout;

public class Ourversetest implements ActionListener {
    private JButton buttonstaff;
    private JButton buttonbuyer;
    private JPanel cardPanel;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField merchListField;
    private JTextField paymentMethodField;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        Ourversetest app = new Ourversetest();
        app.createAndShowGUI();
    }

    private void createAndShowGUI() {
        ImageIcon originalImage = new ImageIcon("OurLogo.png");
        Image resizedImage = originalImage.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(resizedImageIcon);
        imageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JLabel textLabel = new JLabel("Welcome To Ourverse");
        textLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        textLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        buttonstaff = new JButton("Masuk Sebagai Staff");
        buttonstaff.setFont(new Font("SansSerif", Font.PLAIN, 14));
        buttonstaff.setFocusable(false);
        buttonstaff.setAlignmentX(JButton.CENTER_ALIGNMENT);
        buttonstaff.addActionListener(this);

        buttonbuyer = new JButton("Masuk Sebagai Pembeli");
        buttonbuyer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        buttonbuyer.setFocusable(false);
        buttonbuyer.setAlignmentX(JButton.CENTER_ALIGNMENT);
        buttonbuyer.addActionListener(this);

        cardPanel = new JPanel(new CardLayout());

        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel, BoxLayout.Y_AXIS));
        mainMenuPanel.add(Box.createVerticalGlue());
        mainMenuPanel.add(imageLabel);
        mainMenuPanel.add(Box.createVerticalStrut(10));
        mainMenuPanel.add(textLabel);
        mainMenuPanel.add(Box.createVerticalStrut(20));
        mainMenuPanel.add(buttonstaff);
        mainMenuPanel.add(Box.createVerticalStrut(10));
        mainMenuPanel.add(buttonbuyer);
        mainMenuPanel.add(Box.createVerticalGlue());

        cardPanel.add(mainMenuPanel, "Main Menu");
        cardPanel.add(createStaffSessionPanel(), "Staff Session");
        cardPanel.add(createBuyerSessionPanel(), "Buyer Session");
        cardPanel.add(createOrderFormPanel(), "Order Form");
        cardPanel.add(createStaffMenuSessionPanel(), "Staff Menu");

        JFrame frame = new JFrame("Ourverse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400, 400);
        frame.setIconImage(originalImage.getImage());
        frame.add(cardPanel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        if (e.getSource() == buttonstaff) {
            cardLayout.show(cardPanel, "Staff Session");
        } else if (e.getSource() == buttonbuyer) {
            cardLayout.show(cardPanel, "Buyer Session");
        }
    }

    private JPanel createStaffSessionPanel() {
        JPanel staffPanel = new JPanel();
        staffPanel.setLayout(new BoxLayout(staffPanel, BoxLayout.Y_AXIS));
        staffPanel.setBorder(new EmptyBorder(20, 30, 20, 30)); // Top, Left, Bottom, Right margins

        // Label for Login
        JLabel textLabel = new JLabel("Masuk sebagai Staff");
        textLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        textLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT); // Center the text horizontally

        // Username and Password labels and fields
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS)); // Horizontal layout
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setPreferredSize(new java.awt.Dimension(100, 20)); // Fixed size for the label
        usernameField = new JTextField();
        usernameField.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 30)); // Allow full width resizing
        usernamePanel.add(usernameLabel);
        usernamePanel.add(Box.createHorizontalStrut(10)); // Add space between label and field
        usernamePanel.add(usernameField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS)); // Horizontal layout
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setPreferredSize(new java.awt.Dimension(100, 20)); // Fixed size for the label
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 30)); // Allow full width resizing
        passwordPanel.add(passwordLabel);
        passwordPanel.add(Box.createHorizontalStrut(10)); // Add space between label and field
        passwordPanel.add(passwordField);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("staff") && password.equals("1234")) {
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "Staff Menu");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        // loginButton.addActionListener(e -> {
        // JOptionPane.showMessageDialog(null, "Login Button Clicked", "Information",
        // JOptionPane.INFORMATION_MESSAGE);
        // });

        // Back to main menu button
        JButton backButton = new JButton("Kembali ke Menu Utama");
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "Main Menu");
        });

        // Add components to staff panel
        staffPanel.add(Box.createVerticalGlue()); // Add flexibility for top
        staffPanel.add(textLabel);
        staffPanel.add(Box.createVerticalStrut(20)); // Space before username
        staffPanel.add(usernamePanel); // Add username panel (label and field)
        staffPanel.add(Box.createVerticalStrut(5)); // Space between username and password
        staffPanel.add(passwordPanel); // Add password panel (label and field)
        staffPanel.add(Box.createVerticalStrut(20)); // Space before buttons
        staffPanel.add(loginButton);
        staffPanel.add(Box.createVerticalStrut(10));
        staffPanel.add(backButton);
        staffPanel.add(Box.createVerticalGlue()); // Add flexibility for bottom

        return staffPanel;
    }

    private JPanel createStaffMenuSessionPanel() {
        JPanel StaffPanelM = new JPanel();
        StaffPanelM.setLayout(new BoxLayout(StaffPanelM, BoxLayout.Y_AXIS));
        StaffPanelM.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel MenuLabel = new JLabel("Menu Staff");
        MenuLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        MenuLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JButton StaffAddMerchButton = new JButton("Menambahkan Daftar Merch");
        StaffAddMerchButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        StaffAddMerchButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Daftar Merch Ditambahkan", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        JButton StaffAddStockMerchButton = new JButton("Menambahkan Stok Merch");
        StaffAddStockMerchButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        StaffAddStockMerchButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Stok Merch Ditambahkan", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        JButton StaffViewMerchButton = new JButton("Melihat Daftar List Merch");
        StaffViewMerchButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        StaffViewMerchButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Melihat Daftar Merch", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        JButton StaffOrderMerchViewButton = new JButton("Melihat Daftar Pesanan Merch");
        StaffOrderMerchViewButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        StaffOrderMerchViewButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Melihat Daftar Pesanan", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        JButton backButton = new JButton("Kembali ke Menu Utama");
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "Main Menu");
        });

        StaffPanelM.add(Box.createVerticalGlue());
        StaffPanelM.add(MenuLabel);
        StaffPanelM.add(Box.createVerticalStrut(20));
        StaffPanelM.add(StaffAddMerchButton);
        StaffPanelM.add(Box.createVerticalStrut(10));
        StaffPanelM.add(StaffAddStockMerchButton);
        StaffPanelM.add(Box.createVerticalStrut(10));
        StaffPanelM.add(StaffViewMerchButton);
        StaffPanelM.add(Box.createVerticalStrut(10));
        StaffPanelM.add(StaffOrderMerchViewButton);
        StaffPanelM.add(Box.createVerticalStrut(10));
        StaffPanelM.add(Box.createVerticalStrut(20));
        StaffPanelM.add(backButton);
        StaffPanelM.add(Box.createVerticalGlue());
        return StaffPanelM;
    }

    private JPanel createBuyerSessionPanel() {
        JPanel buyerPanel = new JPanel();
        buyerPanel.setLayout(new BoxLayout(buyerPanel, BoxLayout.Y_AXIS));
        buyerPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("Buyer Session");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JButton viewMerchButton = new JButton("Melihat Daftar Merch");
        viewMerchButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        viewMerchButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Daftar Merch Ditampilkan", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        JButton orderMerchButton = new JButton("Memesan Merch");
        orderMerchButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        orderMerchButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "Order Form");
        });

        JButton viewOrdersButton = new JButton("Melihat Daftar Pemesanan");
        viewOrdersButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        viewOrdersButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Daftar Pemesanan Ditampilkan", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        JButton backButton = new JButton("Kembali ke Menu Utama");
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "Main Menu");
        });

        buyerPanel.add(Box.createVerticalGlue());
        buyerPanel.add(titleLabel);
        buyerPanel.add(Box.createVerticalStrut(20));
        buyerPanel.add(viewMerchButton);
        buyerPanel.add(Box.createVerticalStrut(10));
        buyerPanel.add(orderMerchButton);
        buyerPanel.add(Box.createVerticalStrut(10));
        buyerPanel.add(viewOrdersButton);
        buyerPanel.add(Box.createVerticalStrut(20));
        buyerPanel.add(backButton);
        buyerPanel.add(Box.createVerticalGlue());

        return buyerPanel;
    }

    private JPanel createOrderFormPanel() {
        JPanel orderFormPanel = new JPanel();
        orderFormPanel.setLayout(new BoxLayout(orderFormPanel, BoxLayout.Y_AXIS));
        orderFormPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel formLabel = new JLabel("Form Pemesanan Merch");
        formLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        formLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        nameField = new JTextField();
        addressField = new JTextField();
        merchListField = new JTextField();
        paymentMethodField = new JTextField();

        addLabeledField(orderFormPanel, "Nama Pemesan:", nameField);
        addLabeledField(orderFormPanel, "Alamat:", addressField);
        addLabeledField(orderFormPanel, "Daftar Merch:", merchListField);
        addLabeledField(orderFormPanel, "Metode Pembayaran:", paymentMethodField);

        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String address = addressField.getText();
            String merchList = merchListField.getText();
            String paymentMethod = paymentMethodField.getText();

            JOptionPane.showMessageDialog(null,
                    "Pesanan Berhasil:\n" +
                            "Nama: " + name + "\n" +
                            "Alamat: " + address + "\n" +
                            "Merch: " + merchList + "\n" +
                            "Pembayaran: " + paymentMethod,
                    "Konfirmasi Pesanan",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        JButton backButton = new JButton("Kembali ke Buyer Session");
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "Buyer Session");
        });

        orderFormPanel.add(Box.createVerticalStrut(20));
        orderFormPanel.add(submitButton);
        orderFormPanel.add(Box.createVerticalStrut(10));
        orderFormPanel.add(backButton);
        orderFormPanel.add(Box.createVerticalGlue());

        return orderFormPanel;
    }

    private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new java.awt.Dimension(120, 20));
        textField.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 30));
        fieldPanel.add(label);
        fieldPanel.add(Box.createHorizontalStrut(10));
        fieldPanel.add(textField);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(10));
    }

}