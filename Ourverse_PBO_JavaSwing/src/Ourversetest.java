/// Komen Bagian Ditaruh untuk bagian presentasi saja ///
/// Ourverse Project ///

// Bagian Irma 1
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.awt.event.KeyAdapter;
import javax.swing.text.DocumentFilter.FilterBypass;
import javax.swing.text.JTextComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Locale;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Ourversetest implements ActionListener {
    private JButton buttonstaff;
    private JButton buttonbuyer;
    private JPanel cardPanel;
    private JTextField nameField;
    private JTextField addressField;
    private JComboBox<Merchandise> merchDropdown;
    private JTextField merchqtyField;
    private JLabel priceLabel;
    private JLabel totalLabel;
    private JTextField paymentMethodField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextArea orderHistoryTextArea;
    private ArrayList<Merchandise> merchandiseList = new ArrayList<>();
    private JTextField priceField;
    private JTextField stockField;

    // Bagian Selsa 1
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ourversetest app = new Ourversetest();
            app.merchandiseList.add(new Merchandise("1", "Seventeen Necklace", "150,000", "100"));
            app.merchandiseList.add(new Merchandise("2", "Seventeen Shoulder Strap", "100,000", "100"));
            app.merchandiseList.add(new Merchandise("3", "Seventeen Photo Card", "50,000", "100"));
            app.merchandiseList.add(new Merchandise("4", "Seventeen Film Keyring", "140,000", "100"));
            app.merchandiseList.add(new Merchandise("5", "Seventeen Acrylic Stand", "150,000", "100"));
            app.merchandiseList.add(new Merchandise("6", "Seventeen Mini Picket Keyring", "200,000", "100"));
            app.merchandiseList.add(new Merchandise("7", "BONGBONGEE Water Ball Keyring", "225,000", "100"));
            app.merchandiseList.add(new Merchandise("8", "OFFICIAL LIGHT STICK VER.3", "550,000", "100"));
            app.merchandiseList.add(new Merchandise("9", "Seventeen Long Glass Cup", "150,000", "100"));
            app.merchandiseList.add(new Merchandise("10", "SEVENTEEN 2025 SEASON'S GREETING", "225,000", "100"));

            app.createAndShowGUI();
        });
    }

    // Bagian Sonia 1
    // bantu harga
    private double parsePrice(String priceStr) {
        try {
            // Hapus semua karakter koma dan titik
            String cleanPrice = priceStr.replace(",", "").replace(".", "");
            // Parse string ke double
            return Double.parseDouble(cleanPrice);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private String formatPrice(double price) {
        // Format angka ke format mata uang Indonesia
        NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("id", "ID"));
        return formatter.format(price);
    }

    // Bagian Soja 1
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

        // Navigation Panel
        cardPanel.add(mainMenuPanel, "Main Menu");
        cardPanel.add(createStaffSessionPanel(), "Staff Session");
        cardPanel.add(createBuyerSessionPanel(), "Buyer Session");
        cardPanel.add(createOrderFormPanel(), "Order Form");
        cardPanel.add(createStaffMenuSessionPanel(), "Staff Menu");
        cardPanel.add(createOrderHistoryPanel("buyer"), "Order History");

        JFrame frame = new JFrame("Ourverse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(670, 500);
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

    // Bagian Sonia sama Selsa
    static class Merchandise {
        String code;
        String name;
        String price;
        String stock;
        String buyerName = null; // Nama pembeli (null jika merchandise belum dipesan)
        String address = null; // Alamat pembeli
        int quantity = 0; // Jumlah yang dipesan
        String paymentMethod = null; // Metode pembayaran
        boolean isOrdered;

        Merchandise(String code, String name, String price, String stock) {
            this.code = code;
            this.name = name;
            this.price = price;
            this.stock = stock;
            this.isOrdered = false;
        }

        // Punya Order
        // menambahkan supaya merchandis gak error
        @Override
        public String toString() {
            return name + " - Rp " + price;
        }

        public String getOrderInfo() {
            if (isOrdered) {
                return String.format("Kode: %s\nNama: %s\nAlamat: %s\nMerch: %s\n" +
                        "Quantity: %d\nPembayaran: %s\n",
                        code, buyerName, address, name, quantity, paymentMethod);
            }
            return null;
        }
    }

    // Bagian Soja 2
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

            if (username.equals("staff") && password.equals("staff123")) {
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "Staff Menu");
            } else {
                JOptionPane.showMessageDialog(null, "Nama atau Sandi salah! Mohon cek kembali.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

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

    // Staff Menu Session
    private JPanel createStaffMenuSessionPanel() {
        JPanel StaffPanelM = new JPanel();
        StaffPanelM.setName("Staff Menu");
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
            // Panggil metode untuk mengedit stok merchandise
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardPanel.add(createEditStockPanel(), "Edit Stock Merch");
            cardLayout.show(cardPanel, "Edit Stock Merch");
        });

        JButton StaffViewMerchButton = new JButton("Melihat Daftar Merch");
        StaffViewMerchButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        StaffViewMerchButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardPanel.add(createMerchListPanel("staff"), "Merch List");
            cardLayout.show(cardPanel, "Merch List");
        });

        JButton StaffOrderMerchViewButton = new JButton("Melihat Daftar Pesanan Merch");
        StaffOrderMerchViewButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        StaffOrderMerchViewButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardPanel.add(createOrderHistoryPanel("staff"), "Order History");
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

    // Bagian Irma 2
    private JPanel createMerchListPanel(String source) {
        JPanel merchListPanel = new JPanel();
        merchListPanel.setLayout(new BoxLayout(merchListPanel, BoxLayout.Y_AXIS));
        merchListPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel merchListLabel = new JLabel("Daftar Merchandise");
        merchListLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        merchListLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Create table model with refreshable data
        DefaultTableModel tableModel = new DefaultTableModel(
                new String[] { "Kode Merch", "Nama Merch", "Harga Merch", "Jumlah Stok Merch" },
                0 // Start with 0 rows
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Create table with the model
        JTable merchTable = new JTable(tableModel);
        merchTable.setFillsViewportHeight(true);
        merchTable.setPreferredScrollableViewportSize(new Dimension(350, 200));

        // Method to refresh table data
        refreshTableData(tableModel);

        JScrollPane scrollPane = new JScrollPane(merchTable);
        scrollPane.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);

        // Button text and target based on source
        String buttonText = source.equals("staff") ? "Kembali ke Menu Pegawai" : "Kembali ke Menu Pembeli";
        String targetCard = source.equals("staff") ? "Staff Menu" : "Buyer Session";

        JButton backButton = new JButton(buttonText);
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, targetCard);
        });

        // Add refresh button for real-time updates
        JButton refreshButton = new JButton("Refresh Daftar");
        refreshButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        refreshButton.addActionListener(e -> refreshTableData(tableModel));

        merchListPanel.add(Box.createVerticalGlue());
        merchListPanel.add(merchListLabel);
        merchListPanel.add(Box.createVerticalStrut(20));
        merchListPanel.add(scrollPane);
        merchListPanel.add(Box.createVerticalStrut(10));
        merchListPanel.add(refreshButton);
        merchListPanel.add(Box.createVerticalStrut(10));
        merchListPanel.add(backButton);
        merchListPanel.add(Box.createVerticalGlue());

        return merchListPanel;
    }

    // Add this new method to refresh table data
    private void refreshTableData(DefaultTableModel model) {
        // Clear existing rows
        model.setRowCount(0);

        // Add current data from merchandiseList
        for (Merchandise merch : merchandiseList) {
            model.addRow(new Object[] {
                    merch.code,
                    merch.name,
                    merch.price,
                    merch.stock
            });
        }
    }

    // Kelas untuk editor tombol
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isPushed;
        private JTable table; // Tambahkan referensi ke JTable

        public ButtonEditor(JCheckBox checkBox, JTable table) {
            super(checkBox);
            this.table = table; // Simpan referensi ke JTable
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    // Ambil data dari baris yang dipilih
                    int row = table.getSelectedRow();
                    if (row != -1) { // Pastikan ada baris yang dipilih
                        Merchandise merch = merchandiseList.get(row);
                        String newStock = JOptionPane.showInputDialog("Masukkan stok baru untuk " + merch.name);
                        if (newStock != null && !newStock.trim().isEmpty()) {
                            // Update stok merchandise
                            merch.stock = newStock;
                            JOptionPane.showMessageDialog(button,
                                    "Stok untuk " + merch.name + " telah diperbarui menjadi " + newStock);
                        } else {
                            JOptionPane.showMessageDialog(button, "Stok tidak boleh kosong!", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                // Kembali ke nilai default
            }
            isPushed = false;
            return label;
        }
    }

    // Bagian Irma 3
    // Menu Menambahkan Daftar Merch
    private JPanel createAddMerchPanel() {
        JPanel addMerchPanel = new JPanel();
        addMerchPanel.setLayout(new BoxLayout(addMerchPanel, BoxLayout.Y_AXIS));
        addMerchPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Label untuk judul
        JLabel AddMerchLabel = new JLabel("Menambahkan Daftar Merch");
        AddMerchLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        AddMerchLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Input fields
        JTextField codeField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField stockField = new JTextField();

        // Menambahkan field input ke panel
        addLabeledField(addMerchPanel, "Kode Merch:", codeField);
        addLabeledField(addMerchPanel, "Nama Merch:", nameField);
        addLabeledField(addMerchPanel, "Harga Merch:", priceField);
        addLabeledField(addMerchPanel, "Jumlah Stok Merch:", stockField);

        // Tombol Selesai
        JButton doneButton = new JButton("Simpan");
        doneButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        doneButton.setBackground(Color.GREEN); // Mengatur warna latar belakang tombol Selesai
        doneButton.setOpaque(true); // Agar warna latar belakang terlihat

        doneButton.addActionListener(e -> {
            // 1. Mengambil nilai dari semua field input dan membersihkan spasi di ujung
            String code = codeField.getText().trim();
            String name = nameField.getText().trim();
            String price = priceField.getText().trim();
            String stock = stockField.getText().trim();

            // 2. Validasi input - memastikan semua field terisi
            if (code.isEmpty() || name.isEmpty() || price.isEmpty() || stock.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Anda harus memasukkan data!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return; // Keluar dari method jika ada yang kosong
            }

            // 3. Validasi kode merchandise yang unik
            for (Merchandise existingMerch : merchandiseList) {
                if (existingMerch.code.equals(code)) {
                    JOptionPane.showMessageDialog(null,
                            "Kode merchandise sudah digunakan!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // 4. Membuat objek Merchandise baru dan menambahkannya ke list
            Merchandise newMerch = new Merchandise(code, name, price, stock);
            merchandiseList.add(newMerch);

            // 5. Memperbarui dropdown di form pemesanan
            refreshOrderFormDropdown(merchDropdown);

            // 6. Menampilkan pesan sukses dengan detail merchandise yang ditambahkan
            JOptionPane.showMessageDialog(null,
                    "Data Merch Ditambahkan:\n" +
                            "Kode: " + code + "\n" +
                            "Nama: " + name + "\n" +
                            "Harga: " + price + "\n" +
                            "Stok: " + stock,
                    "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);

            // 7. Mengosongkan semua field input
            codeField.setText("");
            nameField.setText("");
            priceField.setText("");
            stockField.setText("");

            // 8. Kembali ke menu staff
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "Staff Menu");
        });

        // Tombol Cancel
        JButton cancelButton = new JButton("Batalkan");
        cancelButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        cancelButton.setBackground(Color.RED); // Mengatur warna latar belakang tombol Cancel
        cancelButton.setOpaque(true); // Agar warna latar belakang terlihat
        cancelButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "Staff Menu"); // Kembali ke menu staff
        });

        // Menambahkan semua komponen ke panel
        addMerchPanel.add(Box.createVerticalGlue());
        addMerchPanel.add(Box.createVerticalStrut(10));
        addMerchPanel.add(doneButton);
        addMerchPanel.add(Box.createVerticalStrut(10));
        addMerchPanel.add(cancelButton);
        addMerchPanel.add(Box.createVerticalGlue());

        return addMerchPanel;
    }

    // Bagian Soja 3
    // Menu Menambahkan stock Merch
    private JPanel createEditStockPanel() {
        JPanel editStockPanel = new JPanel();
        editStockPanel.setLayout(new BoxLayout(editStockPanel, BoxLayout.Y_AXIS)); // Komponen disusun vertikal
        editStockPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel editLabel = new JLabel("Edit Stok Merchandise");
        editLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        editLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        editStockPanel.add(editLabel);
        editStockPanel.add(Box.createVerticalStrut(20)); // Spasi antar komponen

        // ComboBox untuk memilih kode merchandise
        JComboBox<String> codeComboBox = new JComboBox<>();
        for (Merchandise merch : merchandiseList) {
            codeComboBox.addItem(merch.code); // Tambahkan semua kode merchandise ke ComboBox
        }
        codeComboBox.addActionListener(e -> {
            String selectedCode = (String) codeComboBox.getSelectedItem();
            if (selectedCode != null) {
                for (Merchandise merch : merchandiseList) {
                    if (merch.code.equals(selectedCode)) {
                        // Isi field dengan data merchandise yang sesuai
                        nameField.setText(merch.name);
                        priceField.setText(merch.price);
                        stockField.setText(merch.stock);
                        break;
                    }
                }
            }
        });

        // Input fields
        nameField = new JTextField(10);
        priceField = new JTextField(10);
        stockField = new JTextField(10);

        nameField.setEditable(false); // Hanya untuk display
        priceField.setEditable(false); // Hanya untuk display

        // Tambahkan semua komponen dengan label
        editStockPanel.add(createLabeledFieldPanel("Kode Merch:", codeComboBox));
        editStockPanel.add(Box.createVerticalStrut(30));
        editStockPanel.add(createLabeledFieldPanel("Nama Merch:", nameField));
        editStockPanel.add(Box.createVerticalStrut(30));
        editStockPanel.add(createLabeledFieldPanel("Harga Merch:", priceField));
        editStockPanel.add(Box.createVerticalStrut(30));
        editStockPanel.add(createLabeledFieldPanel("Stok Merch:", stockField));
        editStockPanel.add(Box.createVerticalStrut(30));
        editStockPanel.add(Box.createVerticalStrut(20)); // Spasi antar field dan tombol

        // Tombol Simpan
        JButton saveButton = new JButton("Simpan");
        saveButton.setBackground(Color.GREEN);
        saveButton.setOpaque(true);
        saveButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        saveButton.addActionListener(e -> {
            String selectedCode = (String) codeComboBox.getSelectedItem();
            String stock = stockField.getText().trim();

            // Validasi input
            if (stock.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Jumlah stok tidak boleh kosong!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update stok merchandise
            for (Merchandise merch : merchandiseList) {
                if (merch.code.equals(selectedCode)) {
                    merch.stock = stock;

                    JOptionPane.showMessageDialog(null, "Stok merchandise berhasil diperbarui:\n" +
                            "Kode: " + selectedCode + "\n" +
                            "Nama: " + merch.name + "\n" +
                            "Harga: " + merch.price + "\n" +
                            "Stok: " + stock, "Informasi", JOptionPane.INFORMATION_MESSAGE); // Pop Up Notification
                    return;
                }
            }

            // JOptionPane.showMessageDialog(null, "Kode merchandise tidak ditemukan!",
            // "Error",
            // JOptionPane.ERROR_MESSAGE);
        });

        // Tombol Batalkan
        JButton cancelButton = new JButton("Batalkan");
        cancelButton.setBackground(Color.RED);
        cancelButton.setOpaque(true);
        cancelButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        cancelButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "Staff Menu"); // Kembali ke menu staff
        });

        // Panel tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(saveButton);
        buttonPanel.add(Box.createHorizontalStrut(10)); // Spasi antar tombol
        buttonPanel.add(cancelButton);
        editStockPanel.add(buttonPanel);

        return editStockPanel;
    }

    // Untuk layout
    // Fungsi untuk membuat panel berisi label dan field di satu baris
    private JPanel createLabeledFieldPanel(String labelText, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS)); // Horizontal layout
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(100, 10)); // Lebar tetap untuk label
        panel.add(label);
        panel.add(field);
        return panel;
    }

    // Bagian Kezia 1
    // Menu Utama Pembeli
    private JPanel createBuyerSessionPanel() {
        JPanel buyerPanel = new JPanel();
        buyerPanel.setLayout(new BoxLayout(buyerPanel, BoxLayout.Y_AXIS));
        buyerPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("Masuk Menu Pembeli");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JButton viewMerchButton = new JButton("Melihat Daftar Merch");
        viewMerchButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        viewMerchButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardPanel.add(createMerchListPanel("buyer"), "Merch List"); // Menambahkan panel merchandise
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
            cardPanel.add(createOrderHistoryPanel("buyer"), "Order History"); // Menambahkan panel riwayat pemesanan
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

    // Bagian Sonia sama Selsa
    public JPanel createOrderFormPanel() {
        JPanel orderFormPanel = new JPanel();
        orderFormPanel.setLayout(new BoxLayout(orderFormPanel, BoxLayout.Y_AXIS));
        orderFormPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Title labelnya
        JLabel formLabel = new JLabel("Form Pemesanan Merch");
        formLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        formLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        orderFormPanel.add(formLabel);

        nameField = new JTextField();
        addressField = new JTextField();
        merchDropdown = new JComboBox<>();
        merchqtyField = new JTextField();
        paymentMethodField = new JTextField();
        priceLabel = new JLabel("Rp 0");
        totalLabel = new JLabel("Rp 0");

        // Pastikan merchandiseList sudah terdefinisi dan berisi data
        if (merchandiseList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Daftar merchandise kosong", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        // supaya pas ngisi quantity cuma bisa terima angka
        merchqtyField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume();
                }
            }
        });

        // Tambahkan DocumentListener untuk update total harga secara real-time
        merchqtyField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateTotal();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateTotal();
            }

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateTotal();
            }

            private void updateTotal() {
                Merchandise selectedMerch = (Merchandise) merchDropdown.getSelectedItem();
                if (selectedMerch != null) {
                    try {
                        double price = parsePrice(selectedMerch.price);
                        String qtyText = merchqtyField.getText();
                        if (!qtyText.isEmpty()) {
                            int qty = Integer.parseInt(qtyText);
                            double total = price * qty;
                            totalLabel.setText("Rp " + formatPrice(total));
                        } else {
                            totalLabel.setText("Rp 0");
                        }
                    } catch (NumberFormatException ex) {
                        totalLabel.setText("Rp 0");
                    }
                }
            }
        });

        // Format untuk mata uang Indonesia
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        // Tambahkan data ke dropdown
        for (Merchandise merch : merchandiseList) {
            merchDropdown.addItem(merch); // Tambahkan objek Merchandise
        }

        // Action Listener untuk update harga saat merchandise dipilih
        merchDropdown.addActionListener(e -> {
            Merchandise selectedMerch = (Merchandise) merchDropdown.getSelectedItem();
            if (selectedMerch != null) {
                // Parse dan format harga
                double price = parsePrice(selectedMerch.price);
                priceLabel.setText("Rp " + formatPrice(price));

                // Update total jika quantity sudah diisi
                String qtyText = merchqtyField.getText();
                if (!qtyText.isEmpty()) {
                    try {
                        int qty = Integer.parseInt(qtyText);
                        double total = price * qty;
                        totalLabel.setText("Rp " + formatPrice(total));
                    } catch (NumberFormatException ex) {
                        totalLabel.setText("Rp 0");
                    }
                }
            }
        });

        // Penambahan komponen ke panel
        addLabeledField(orderFormPanel, "Nama Pemesan:", nameField);
        addLabeledField(orderFormPanel, "Alamat:", addressField);
        addLabeledField(orderFormPanel, "Daftar Merch:", merchDropdown);
        addLabeledField(orderFormPanel, "Harga:", priceLabel);
        addLabeledField(orderFormPanel, "Quantity:", merchqtyField);
        addLabeledField(orderFormPanel, "Total Harga:", totalLabel);
        addLabeledField(orderFormPanel, "Metode Pembayaran:", paymentMethodField);

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        submitButton.setBackground(Color.GREEN);

        // In your createOrderFormPanel() method, replace the submit button action
        // listener with this:
        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String address = addressField.getText().trim();
            Merchandise selectedMerch = (Merchandise) merchDropdown.getSelectedItem();
            String paymentMethod = paymentMethodField.getText().trim();

            // Validation
            if (name.isEmpty() || address.isEmpty() || paymentMethod.isEmpty() || merchqtyField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int quantity = Integer.parseInt(merchqtyField.getText());
                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(null, "Quantity harus lebih dari 0!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int currentStock = Integer.parseInt(selectedMerch.stock);
                if (quantity > currentStock) {
                    JOptionPane.showMessageDialog(null, "Stok tidak mencukupi!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Calculate total price
                double price = Double.parseDouble(selectedMerch.price.replace(",", ""));
                double totalPrice = price * quantity;

                // Update merchandise data
                selectedMerch.stock = String.valueOf(currentStock - quantity);
                selectedMerch.buyerName = name;
                selectedMerch.address = address;
                selectedMerch.quantity = quantity;
                selectedMerch.paymentMethod = paymentMethod;
                selectedMerch.isOrdered = true;

                // Show success message
                String orderInfo = String.format(
                        "Pesanan Berhasil:\n" +
                                "Kode: %s\n" +
                                "Nama: %s\n" +
                                "Alamat: %s\n" +
                                "Merch: %s\n" +
                                "Quantity: %d\n" +
                                "Pembayaran: %s",
                        selectedMerch.code,
                        name,
                        address,
                        selectedMerch.name,
                        quantity,
                        paymentMethod);

                // Show success message with complete order details
                JOptionPane.showMessageDialog(null, "Pesanan Berhasil:\n" + selectedMerch.getOrderInfo(),
                        "Konfirmasi Pesanan", JOptionPane.INFORMATION_MESSAGE);

                // Reset form
                nameField.setText("");
                addressField.setText("");
                merchqtyField.setText("");
                paymentMethodField.setText("");
                merchDropdown.setSelectedIndex(0);

                // Return to buyer menu
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "Buyer Session");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Quantity harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Tombol Kembali
        JButton backButton = new JButton("Kembali ke Menu Pembeli");
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            String name = nameField.getText();
            String address = addressField.getText();
            String selectedMerch = (String) merchDropdown.getSelectedItem();
            int quantity;
            try {
                quantity = Integer.parseInt(merchqtyField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Jumlah harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String paymentMethod = paymentMethodField.getText();

            for (Merchandise merch : merchandiseList) {
                if ((merch.name + " - " + merch.price).equals(selectedMerch)) {
                    int currentStock = Integer.parseInt(merch.stock);
                    if (quantity > 0 && quantity <= currentStock) {
                        // Kurangi stok dan tambahkan data pesanan
                        merch.stock = String.valueOf(currentStock - quantity);
                        merch.buyerName = name;
                        merch.address = address;
                        merch.quantity = quantity;
                        merch.paymentMethod = paymentMethod;

                        updateOrderHistoryTextArea();

                        JOptionPane.showMessageDialog(null, "Pesanan Berhasil:\n" + merch.getOrderInfo(),
                                "Konfirmasi Pesanan", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    } else {
                        JOptionPane.showMessageDialog(null, "Stok tidak mencukupi!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            JOptionPane.showMessageDialog(null, "Merchandise tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
        });

        // Cancel button
        JButton cancelButton = new JButton("Batalkan Pesanan");
        cancelButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        cancelButton.setBackground(Color.RED);
        cancelButton.addActionListener(e -> {
            nameField.setText("");
            addressField.setText("");
            merchqtyField.setText("");
            paymentMethodField.setText("");
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "Buyer Session");
        });

        orderFormPanel.add(Box.createVerticalStrut(20));
        orderFormPanel.add(submitButton);
        orderFormPanel.add(Box.createVerticalStrut(10));
        orderFormPanel.add(cancelButton);
        orderFormPanel.add(Box.createVerticalGlue());

        return orderFormPanel;
    }

    // Bagian Kezia 2
    // Menu Riwayat Pemesanan
    public JPanel createOrderHistoryPanel(String source) {
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

        updateOrderHistoryTextArea();

        JButton deleteButton = new JButton("Hapus Pesanan");
        deleteButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        deleteButton.setBackground(Color.RED);
        deleteButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Masukkan kode merchandise yang ingin dihapus:");
            if (input != null) {
                for (Merchandise merch : merchandiseList) {
                    if (merch.code.equals(input) && merch.buyerName != null) {
                        // Kembalikan stok dan hapus data pesanan
                        int restoredStock = Integer.parseInt(merch.stock) + merch.quantity;
                        merch.stock = String.valueOf(restoredStock);
                        merch.buyerName = null;
                        merch.address = null;
                        merch.quantity = 0;
                        merch.paymentMethod = null;

                        JOptionPane.showMessageDialog(null, "Pesanan berhasil dihapus!", "Informasi",
                                JOptionPane.INFORMATION_MESSAGE);
                        updateOrderHistoryTextArea();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Pesanan tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Buat tombol kembali berdasarkan source
        JButton backButton = new JButton(
                source.equals("staff") ? "Kembali ke Menu Pegawai" : "Kembali ke Menu Pembeli");
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, source.equals("staff") ? "Staff Menu" : "Buyer Session");
        });

        orderHistoryPanel.add(Box.createVerticalGlue());
        orderHistoryPanel.add(orderHistoryLabel);
        orderHistoryPanel.add(Box.createVerticalStrut(20));
        orderHistoryPanel.add(scrollPane);
        orderHistoryPanel.add(Box.createVerticalStrut(10));
        orderHistoryPanel.add(deleteButton);
        orderHistoryPanel.add(Box.createVerticalStrut(10));
        orderHistoryPanel.add(backButton);
        orderHistoryPanel.add(Box.createVerticalGlue());

        return orderHistoryPanel;
    }

    //
    // Versi untuk JTextField
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

    // Versi untuk Component lainnya (JComboBox, JLabel, dll)
    private void addLabeledField(JPanel panel, String labelText, Component component) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));

        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new java.awt.Dimension(120, 20));

        fieldPanel.add(label);
        fieldPanel.add(Box.createHorizontalStrut(10));
        fieldPanel.add(component);

        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(10));
    }

    // Bagian Soja 4
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

    // Bagian Kezia 3
    private void updateOrderHistoryTextArea() {
        StringBuilder history = new StringBuilder();
        int orderNumber = 1;

        for (Merchandise merch : merchandiseList) {
            if (merch.isOrdered && merch.quantity > 0) {
                history.append(String.format(
                        "Pesanan #%d\n" +
                                "Kode: %s\n" +
                                "Nama: %s\n" +
                                "Alamat: %s\n" +
                                "Merch: %s\n" +
                                "Quantity: %d\n" +
                                "Pembayaran: %s\n" +
                                "------------------------\n",
                        orderNumber++,
                        merch.code,
                        merch.buyerName,
                        merch.address,
                        merch.name,
                        merch.quantity,
                        merch.paymentMethod));
            }
        }

        if (history.length() == 0) {
            history.append("Belum ada pesanan.");
        }

        orderHistoryTextArea.setText(history.toString());
    }

    // Bagian Sonia 3
    // Method untuk memperbarui dropdown merchandise di form pemesanan
    private void refreshOrderFormDropdown(JComboBox<Merchandise> dropdown) {
        dropdown.removeAllItems(); // Menghapus semua item yang ada
        for (Merchandise merch : merchandiseList) {
            dropdown.addItem(merch); // Menambahkan setiap merchandise ke dropdown
        }
    }
}

// Selesai