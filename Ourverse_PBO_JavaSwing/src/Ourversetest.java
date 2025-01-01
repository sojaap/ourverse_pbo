import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Statement;
import java.awt.event.KeyAdapter;
import javax.swing.text.DocumentFilter.FilterBypass;
import javax.swing.text.JTextComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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
    private JTextArea orderHistoryTextArea;
    private ArrayList<Merchandise> merchandiseList = new ArrayList<>();

    // Tambahkan deklarasi untuk priceField dan stockField di tingkat kelas
    private JTextField priceField;
    private JTextField stockField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ourversetest app = new Ourversetest();
            app.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        ImageIcon originalImage = new ImageIcon("OurLogo.png");
        Image resizedImage = originalImage.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(resizedImageIcon);
        imageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JLabel textLabel = new JLabel("Selamat Datang di Ourverse");
        textLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        textLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        buttonstaff = new JButton("Masuk Sebagai Pegawai");
        buttonstaff.setFont(new Font("SansSerif", Font.PLAIN, 14));
        buttonstaff.setFocusable(false);
        buttonstaff.setAlignmentX(JButton.CENTER_ALIGNMENT);
        buttonstaff.setBackground(Color.BLUE); // Mengatur warna latar belakang tombol staff
        buttonstaff.setForeground(Color.WHITE); // Mengatur warna teks tombol staff menjadi putih
        buttonstaff.addActionListener(this);

        buttonbuyer = new JButton("Masuk Sebagai Pembeli");
        buttonbuyer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        buttonbuyer.setFocusable(false);
        buttonbuyer.setAlignmentX(JButton.CENTER_ALIGNMENT);
        buttonbuyer.setBackground(Color.ORANGE); // Mengatur warna latar belakang tombol buyer
        buttonbuyer.setForeground(Color.BLACK); // Mengatur warna teks tombol buyer menjadi hitam
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
        cardPanel.add(createOrderHistoryPanel(), "Order History");
        cardPanel.add(StaffAddStockMerchPanel(), "Staff AddStock");

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

    class Merchandise {
        String code;
        String name;
        String price;
        String stock;

        Merchandise(String code, String name, String price, String stock) {
            this.code = code;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }
    }

    private JPanel createStaffSessionPanel() {
        JPanel staffPanel = new JPanel();
        staffPanel.setLayout(new BoxLayout(staffPanel, BoxLayout.Y_AXIS));
        staffPanel.setBorder(new EmptyBorder(20, 30, 20, 30)); // Top, Left, Bottom, Right margins

        // Label for Login
        JLabel textLabel = new JLabel("Masuk sebagai Pegawai");
        textLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        textLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT); // Center the text horizontally

        // Username and Password labels and fields
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS)); // Horizontal layout
        JLabel usernameLabel = new JLabel("Nama Pengguna:");
        usernameLabel.setPreferredSize(new java.awt.Dimension(100, 20)); // Fixed size for the label
        usernameField = new JTextField();
        usernameField.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 30)); // Allow full width resizing
        usernamePanel.add(usernameLabel);
        setCharacterLimit(usernameField, 20);
        usernamePanel.add(Box.createHorizontalStrut(10)); // Add space between label and field
        usernamePanel.add(usernameField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS)); // Horizontal layout
        JLabel passwordLabel = new JLabel("Kata Sandi:");
        passwordLabel.setPreferredSize(new java.awt.Dimension(100, 20)); // Fixed size for the label
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 30)); // Allow full width resizing
        passwordPanel.add(passwordLabel);
        setCharacterLimit(passwordField, 14);
        passwordPanel.add(Box.createHorizontalStrut(10)); // Add space between label and field
        passwordPanel.add(passwordField);

        // Login button
        JButton loginButton = new JButton("Masuk");
        loginButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        loginButton.setBackground(Color.GREEN); // Mengatur warna latar belakang tombol staff
        loginButton.setForeground(Color.WHITE); // Mengatur warna teks tombol staff menjadi putih
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
        backButton.setBackground(Color.GRAY); // Mengatur warna latar belakang tombol staff
        backButton.setForeground(Color.WHITE); // Mengatur warna teks tombol staff menjadi putih
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

        JLabel MenuLabel = new JLabel("Menu Khusus Pegawai");
        MenuLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        MenuLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JButton StaffAddMerchButton = new JButton("Menambahkan Daftar Merch");
        StaffAddMerchButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        StaffAddMerchButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardPanel.add(createAddMerchPanel(), "Add Merch");
            cardLayout.show(cardPanel, "Add Merch");
        });

        JButton StaffAddStockMerchButton = new JButton("Menambahkan Stok Merch");
        StaffAddStockMerchButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        StaffAddStockMerchButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "Staff AddStock");
        });

        JButton StaffViewMerchButton = new JButton("Melihat Daftar Merch");
        StaffViewMerchButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        StaffViewMerchButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardPanel.add(createMerchListPanel(), "Merch List");
            cardLayout.show(cardPanel, "Merch List");
        });

        JButton StaffOrderMerchViewButton = new JButton("Melihat Daftar Pesanan Merch");
        StaffOrderMerchViewButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        StaffOrderMerchViewButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "Order History");
        });

        JButton backButton = new JButton("Kembali ke Menu Utama");
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "Main Menu");
        });

        // Menambahkan semua komponen ke panel staff
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
        StaffPanelM.add(backButton);
        StaffPanelM.add(Box.createVerticalGlue());

        return StaffPanelM;
    }

    private JPanel StaffAddStockMerchPanel() {
        JPanel StaffAddSPanel = new JPanel();
        StaffAddSPanel.setLayout(new BoxLayout(StaffAddSPanel, BoxLayout.Y_AXIS));
        StaffAddSPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel LabelStaffStock = new JLabel("Menu Tambah Stock");
        LabelStaffStock.setFont(new Font("SansSerif", Font.BOLD, 20));
        LabelStaffStock.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        StaffAddSPanel.add(LabelStaffStock);
        StaffAddSPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JComboBox<String> comboBoxItems = new JComboBox<>();
        StaffAddSPanel.add(new JLabel("Pilih Barang:"));
        StaffAddSPanel.add(comboBoxItems);

        JTextField stockInputField = new JTextField(10);
        StaffAddSPanel.add(new JLabel("Tambah Jumlah Stok:"));
        StaffAddSPanel.add(stockInputField);

        JButton addButton = new JButton("Tambah Stok");
        StaffAddSPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        StaffAddSPanel.add(addButton);

        JTable stockTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(
                new String[] { "Nama Merch", "Deskripsi", "ID Merch", "Stok", "Harga" }, 0);
        stockTable.setModel(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(stockTable);
        tableScrollPane.setPreferredSize(new Dimension(500, 200));
        StaffAddSPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        StaffAddSPanel.add(tableScrollPane);

        // Load data from database
        loadStockData(comboBoxItems, tableModel);

        // AddListener for adding stock
        addButton.addActionListener(e -> {
            String selectedItem = (String) comboBoxItems.getSelectedItem();
            String additionalStockStr = stockInputField.getText();

            if (selectedItem == null || additionalStockStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Pilih barang dan masukkan jumlah stok!");
                return;
            }

            try {
                int additionalStock = Integer.parseInt(additionalStockStr);
                updateStock(selectedItem, additionalStock, tableModel);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Masukkan jumlah stok yang valid!");
            }
        });

        return StaffAddSPanel;
    }

    private void loadStockData(JComboBox<String> comboBox, DefaultTableModel tableModel) {
        koneksi dbKoneksi = new koneksi();
        try (Connection conn = dbKoneksi.connect();
                Statement state = conn.createStatement();
                ResultSet rs = state.executeQuery("SELECT * FROM products")) {

            while (rs.next()) {
                String name = rs.getString("nama_merch");
                String desc = rs.getString("deskripsi_barang");
                String id = rs.getString("id_merch");
                int stok = rs.getInt("stok");
                double price = rs.getDouble("harga");

                tableModel.addRow(new Object[] { name, desc, id, stok, price });
                comboBox.addItem(name);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching data: " + e.getMessage());
        }
    }

    private void updateStock(String selectedItem, int additionalStock, DefaultTableModel tableModel) {
        koneksi dbKoneksi = new koneksi();
        try (Connection conn = dbKoneksi.connect()) {
            String selectQuery = "SELECT id_merch, stok FROM products WHERE nama_merch = ?";
            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
                selectStmt.setString(1, selectedItem);
                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("id_merch");
                        int currentStock = rs.getInt("stok");
                        int newStock = currentStock + additionalStock;

                        String updateQuery = "UPDATE products SET stok = ? WHERE id_merch = ?";
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                            updateStmt.setInt(1, newStock);
                            updateStmt.setInt(2, id);
                            updateStmt.executeUpdate();

                            // Update tabel
                            for (int i = 0; i < tableModel.getRowCount(); i++) {
                                if (tableModel.getValueAt(i, 0).equals(selectedItem)) {
                                    tableModel.setValueAt(newStock, i, 3);
                                    break;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Stok berhasil diperbarui!");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating stock: " + e.getMessage());
        }
    }

    // Buyer Session
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
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardPanel.add(createMerchListPanel(), "Merch List"); // Menambahkan panel merchandise
            cardLayout.show(cardPanel, "Merch List"); // Menampilkan panel merchandise
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
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "Order History");
        });

        JButton backButton = new JButton("Kembali ke Menu Utama");
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        backButton.setBackground(Color.GRAY); // Mengatur warna latar belakang tombol staff
        backButton.setForeground(Color.WHITE); // Mengatur warna teks tombol staff menjadi putih
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

    public JPanel createOrderFormPanel() {
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

            // Menampilkan riwayat pesanan
            orderHistoryTextArea.append("Pesanan Baru:\n" +
                    "Nama: " + name + "\n" +
                    "Alamat: " + address + "\n" +
                    "Merch: " + merchList + "\n" +
                    "Pembayaran: " + paymentMethod + "\n\n");

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

    public JPanel createOrderHistoryPanel() {
        JPanel orderHistoryPanel = new JPanel();
        orderHistoryPanel.setLayout(new BoxLayout(orderHistoryPanel, BoxLayout.Y_AXIS));
        orderHistoryPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel orderHistoryLabel = new JLabel("Riwayat Pemesanan");
        orderHistoryLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        orderHistoryLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // TextArea untuk menampilkan riwayat pemesanan
        orderHistoryTextArea = new JTextArea(10, 30);
        orderHistoryTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderHistoryTextArea);
        scrollPane.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);

        JButton backButton = new JButton("Kembali ke Sesi Pembeli");
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "Buyer Session");
        });

        orderHistoryPanel.add(Box.createVerticalGlue());
        orderHistoryPanel.add(orderHistoryLabel);
        orderHistoryPanel.add(Box.createVerticalStrut(20));
        orderHistoryPanel.add(scrollPane);
        orderHistoryPanel.add(Box.createVerticalStrut(10));
        orderHistoryPanel.add(backButton);
        orderHistoryPanel.add(Box.createVerticalGlue());

        return orderHistoryPanel;
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

    private void addLabeledField(JPanel panel, String labelText, JComboBox<String> comboBox) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new java.awt.Dimension(120, 20));
        fieldPanel.add(label);
        fieldPanel.add(Box.createHorizontalStrut(10));
        fieldPanel.add(comboBox);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(10));
    }

    // untuk set character limit untuk username (masing-masing 20)
    public static void setCharacterLimit(JTextField textField, int limit) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Jika panjang teks sudah mencapai limit, tombol tidak akan berfungsi
                if (textField.getText().length() >= limit) {
                    e.consume(); // Menghentikan input lebih lanjut
                }
            }
        });
    }

    // Overload untuk password karena menggunakan char[]
    public static void setCharacterLimit(JPasswordField passwordField, int limit) {
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Jika panjang teks sudah mencapai limit, tombol tidak akan berfungsi
                if (passwordField.getPassword().length >= limit) {
                    e.consume(); // Menghentikan input lebih lanjut
                }
            }
        });
    }
}