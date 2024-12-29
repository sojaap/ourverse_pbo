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
        cardPanel.add(createOrderHistoryPanel(), "Order History");
        cardPanel.add(StaffAddStockMerchPanel(), "Staff AddStock");
        cardPanel.add(createMerchList(), "Merch List");

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
        setCharacterLimit(usernameField, 20);
        usernamePanel.add(Box.createHorizontalStrut(10)); // Add space between label and field
        usernamePanel.add(usernameField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS)); // Horizontal layout
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setPreferredSize(new java.awt.Dimension(100, 20)); // Fixed size for the label
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 30)); // Allow full width resizing
        passwordPanel.add(passwordLabel);
        setCharacterLimit(passwordField, 14);
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
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "Staff AddStock");
        });

        JButton StaffViewMerchButton = new JButton("Melihat Daftar List Merch");
        StaffViewMerchButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        StaffViewMerchButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
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
            cardLayout.show(cardPanel, "Merch List");
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

    public JPanel createMerchList() {
        JPanel merchListJPanel = new JPanel();
        merchListJPanel.setLayout(new BoxLayout(merchListJPanel, BoxLayout.Y_AXIS));
        merchListJPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel merchListLabel = new JLabel("Daftar Merch");
        merchListLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        merchListLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        String[] categories = {"Doll", "Album", "Photocard"};
        JComboBox<String> dropdown = new JComboBox<>(categories);

        JButton backButton = new JButton("Kembali ke Buyer Session");
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "Buyer Session");
        });

        String[] kolom = {"Nama", "Harga"};
        String[][] doll = {
            {"Doll 1", "Rp 60,000"},
            {"Doll 2", "Rp 200,000"},
            {"Doll 3", "Rp 150,500"},
            {"Doll 4", "Rp 70,000"},
            {"Doll 5", "Rp 100,000"}
        };
        
        String[][] album = {
            {"Album 1", "Rp 60,000"},
            {"Album 2", "Rp 200,000"},
            {"Album 3", "Rp 150,500"},
            {"Album 4", "Rp 70,000"},
            {"Album 5", "Rp 100,000"}
        };
        
        String[][] Photocard = {
            {"Photocard 1", "Rp 60,000"},
            {"Photocard 2", "Rp 200,000"},
            {"Photocard 3", "Rp 150,500"},
            {"Photocard 4", "Rp 70,000"},
            {"Photocard 5", "Rp 100,000"}
        };

        // class untuk modifikasi agar tabel tidak bisa diedit pembeli
        class NonEditableTableModel extends DefaultTableModel {
            public NonEditableTableModel(Object[][] data, Object[] kolom) {
                super(data, kolom); // memanggil dari induk inisial data dan kolom yg ada
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabel tidak bisa diedit
            }
        }

        // tampilan table
        JTable table = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(table);

        // buat interaktif sama dropdown nya
        ActionListener dropdownActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) dropdown.getSelectedItem();
                if (selectedCategory != null) {
                    switch (selectedCategory) {
                        case "Doll":
                            table.setModel(new NonEditableTableModel(doll, kolom));
                            break;
                        case "Album":
                            table.setModel(new NonEditableTableModel(album, kolom));
                            break;
                        case "Photocard":
                            table.setModel(new NonEditableTableModel(Photocard, kolom));
                            break;
                    }
                }
            }
        };
        
        // tambah actionlistener ke dropdown
        dropdown.addActionListener(dropdownActionListener);


        // masukin ke panel
        merchListJPanel.add(Box.createVerticalGlue());
        merchListJPanel.add(merchListLabel);
        merchListJPanel.add(Box.createVerticalStrut(20));
        merchListJPanel.add(dropdown);
        merchListJPanel.add(tableScrollPane);
        merchListJPanel.add(Box.createVerticalStrut(10));
        merchListJPanel.add(backButton);
        merchListJPanel.add(Box.createVerticalGlue());
        
        // table default
        table.setModel(new NonEditableTableModel(doll, kolom));

        return merchListJPanel;
    
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