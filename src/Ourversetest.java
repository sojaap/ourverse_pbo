import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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

public class Ourversetest implements ActionListener {
    private JButton buttonstaff;
    private JButton buttonbuyer;
    private JPanel cardPanel;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField merchListField;
    private JTextField merchqtyField;
    private JTextField paymentMethodField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextArea orderHistoryTextArea;
    private ArrayList<Merchandise> merchandiseList = new ArrayList<>();

    // Tambahkan deklarasi untuk priceField dan stockField di tingkat kelas
    private JTextField priceField;
    private JTextField stockField;

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

            if (username.equals("staff") && password.equals("staff123")) {
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

    // Staff Menu Session

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
            // Panggil metode untuk mengedit stok merchandise
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardPanel.add(createEditStockPanel(), "Edit Stock Merch");
            cardLayout.show(cardPanel, "Edit Stock Merch");
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

    private JPanel createMerchListPanel() {
        JPanel merchListPanel = new JPanel();
        merchListPanel.setLayout(new BoxLayout(merchListPanel, BoxLayout.Y_AXIS));
        merchListPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel merchListLabel = new JLabel("Daftar List Merchandise");
        merchListLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        merchListLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Data untuk tabel merchandise
        String[][] merchData = new String[merchandiseList.size()][4];
        for (int i = 0; i < merchandiseList.size(); i++) {
            Merchandise merch = merchandiseList.get(i);
            merchData[i][0] = merch.code;
            merchData[i][1] = merch.name;
            merchData[i][2] = merch.price;
            merchData[i][3] = merch.stock;
        }

        // Nama kolom untuk tabel
        String[] columnNames = { "Kode Merch", "Nama Merch", "Harga Merch", "Jumlah Stok Merch" };

        // Membuat DefaultTableModel
        DefaultTableModel tableModel = new DefaultTableModel(merchData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Mengatur agar sel tidak dapat diedit
            }
        };

        // Membuat JTable
        JTable merchTable = new JTable(tableModel);
        merchTable.setFillsViewportHeight(true);
        merchTable.setPreferredScrollableViewportSize(new Dimension(350, 200)); // Set preferred size for scrolling

        JScrollPane scrollPane = new JScrollPane(merchTable);
        scrollPane.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);

        JButton backButton = new JButton("Kembali ke Menu Pegawai");
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        backButton.setBackground(Color.GRAY); // Mengatur warna latar belakang tombol staff
        backButton.setForeground(Color.WHITE); // Mengatur warna teks tombol staff menjadi putih
        backButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "Staff Menu");
        });

        merchListPanel.add(Box.createVerticalGlue());
        merchListPanel.add(merchListLabel);
        merchListPanel.add(Box.createVerticalStrut(20));
        merchListPanel.add(scrollPane);
        merchListPanel.add(Box.createVerticalStrut(20));
        merchListPanel.add(backButton);
        merchListPanel.add(Box.createVerticalGlue());

        return merchListPanel;
    }

    // Kelas untuk tombol di tabel
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
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
            String code = codeField.getText().trim();
            String name = nameField.getText().trim();
            String price = priceField.getText().trim();
            String stock = stockField.getText().trim();

            // Validasi input
            if (code.isEmpty() || name.isEmpty() || price.isEmpty() || stock.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Anda harus memasukkan data!", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Kembali ke panel tanpa berpindah
            }

            // Simpan data ke dalam ArrayList
            merchandiseList.add(new Merchandise(code, name, price, stock));

            // Simpan data ke sistem (di sini hanya ditampilkan dalam dialog)
            JOptionPane.showMessageDialog(null, "Data Merch Ditambahkan:\n" +
                    "Kode: " + code + "\n" +
                    "Nama: " + name + "\n" +
                    "Harga: " + price + "\n" +
                    "Stok: " + stock, "Informasi", JOptionPane.INFORMATION_MESSAGE);

            // Kembali ke menu staff setelah selesai
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

    // Menu Menambahkan stock Merch
    private JPanel createEditStockPanel() {
        JPanel editStockPanel = new JPanel();
        editStockPanel.setLayout(new BoxLayout(editStockPanel, BoxLayout.Y_AXIS));
        editStockPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel editLabel = new JLabel("Edit Stok Merchandise");
        editLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        editLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // ComboBox untuk memilih kode merchandise
        JComboBox<String> codeComboBox = new JComboBox<>();
        for (Merchandise merch : merchandiseList) {
            codeComboBox.addItem(merch.code); // Tambahkan semua kode merchandise ke ComboBox
        }
        codeComboBox.addActionListener(e -> {
            String selectedCode = (String) codeComboBox.getSelectedItem();
            for (Merchandise merch : merchandiseList) {
                if (merch.code.equals(selectedCode)) {
                    // Tampilkan data merchandise yang dipilih
                    nameField.setText(merch.name);
                    priceField.setText(merch.price);
                    stockField.setText(merch.stock);
                    break;
                }
            }
        });

        // Input fields
        nameField = new JTextField();
        priceField = new JTextField();
        stockField = new JTextField();

        // Menambahkan field input ke panel
        addLabeledField(editStockPanel, "Kode Merch:", codeComboBox);
        addLabeledField(editStockPanel, "Nama Merch:", nameField);
        addLabeledField(editStockPanel, "Harga Merch:", priceField);
        addLabeledField(editStockPanel, "Jumlah Stok Merch:", stockField);

        // Tombol Simpan
        JButton saveButton = new JButton("Simpan");
        saveButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        saveButton.setBackground(Color.GREEN);
        saveButton.setOpaque(true);
        saveButton.addActionListener(e -> {
            String selectedCode = (String) codeComboBox.getSelectedItem();
            String name = nameField.getText().trim();
            String price = priceField.getText().trim();
            String stock = stockField.getText().trim();

            // Validasi input
            if (name.isEmpty() || price.isEmpty() || stock.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Anda harus memasukkan data!", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Kembali ke panel tanpa berpindah
            }

            // Cari merchandise berdasarkan kode
            for (Merchandise merch : merchandiseList) {
                if (merch.code.equals(selectedCode)) {
                    // Update data merchandise
                    merch.name = name;
                    merch.price = price;
                    merch.stock = stock;

                    JOptionPane.showMessageDialog(null, "Data Merch telah diperbarui:\n" +
                            "Kode: " + selectedCode + "\n" +
                            "Nama: " + name + "\n" +
                            "Harga: " + price + "\n" +
                            "Stok: " + stock, "Informasi", JOptionPane.INFORMATION_MESSAGE);

                    // Kembali ke menu staff setelah selesai
                    CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                    cardLayout.show(cardPanel, "Staff Menu");
                    return;
                }
            }

            // Jika kode tidak ditemukan
            JOptionPane.showMessageDialog(null, "Kode merchandise tidak ditemukan!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        });

        // Tombol Batalkan
        JButton cancelButton = new JButton("Batalkan");
        cancelButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        cancelButton.setBackground(Color.RED);
        cancelButton.setOpaque(true);
        cancelButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "Staff Menu"); // Kembali ke menu staff
        });

        // Menambahkan semua komponen ke panel
        editStockPanel.add(Box.createVerticalGlue());
        editStockPanel.add(editLabel);
        editStockPanel.add(Box.createVerticalStrut(20));
        editStockPanel.add(codeComboBox);
        editStockPanel.add(Box.createVerticalStrut(10));
        editStockPanel.add(nameField);
        editStockPanel.add(Box.createVerticalStrut(10));
        editStockPanel.add(priceField);
        editStockPanel.add(Box.createVerticalStrut(10));
        editStockPanel.add(stockField);
        editStockPanel.add(Box.createVerticalStrut(20));
        editStockPanel.add(saveButton);
        editStockPanel.add(Box.createVerticalStrut(10));
        editStockPanel.add(cancelButton);
        editStockPanel.add(Box.createVerticalGlue());

        return editStockPanel;
    }

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

        // Taking data available merch
        JComboBox<String> merchDropdown = new JComboBox<>();
        for (Merchandise merch : merchandiseList) {
            merchDropdown.addItem(merch.name + " - " + merch.code);
        }

        JTextField merchqtyField = new JTextField();
        paymentMethodField = new JTextField();

        // merchListField = new JTextField();
        // paymentMethodField = new JTextField();

        // Dropdown List
        merchDropdown.addActionListener(e -> {
            String selectedMerch = (String) merchDropdown.getSelectedItem();
            if (selectedMerch != null) {
                System.out.println("Merchandise terpilih: " + selectedMerch);
            }
        });

        addLabeledField(orderFormPanel, "Nama Pemesan:", nameField);
        addLabeledField(orderFormPanel, "Alamat:", addressField);
        // dropdown choosing merch
        orderFormPanel.add(new JLabel("Daftar Merch:"));
        orderFormPanel.add(merchDropdown);

        addLabeledField(orderFormPanel, "Quantity:", merchqtyField);
        addLabeledField(orderFormPanel, "Metode Pembayaran:", paymentMethodField);

        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String address = addressField.getText();
            String selectedMerch = (String) merchDropdown.getSelectedItem();
            String quantity = merchqtyField.getText();
            String paymentMethod = paymentMethodField.getText();

            // Showing Order History
            orderHistoryTextArea.append("Pesanan Baru:\n" +
                    "Nama: " + name + "\n" +
                    "Alamat: " + address + "\n" +
                    "Merch: " + selectedMerch + "\n" +
                    "Quantity: " + quantity + "\n" +
                    "Pembayaran: " + paymentMethod + "\n\n");

            JOptionPane.showMessageDialog(null,
                    "Pesanan Berhasil:\n" +
                            "Nama: " + name + "\n" +
                            "Alamat: " + address + "\n" +
                            "Merch: " + selectedMerch + "\n" +
                            "Quantity: " + quantity + "\n" +
                            "Pembayaran: " + paymentMethod,
                    "Konfirmasi Pesanan",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        JButton backButton = new JButton("Kembali ke Menu Pembeli");
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

    // private String[] getMerchDataFromStaff() {
    // return new string[] {};
    // }

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

        JButton backButton = new JButton("Kembali ke Menu Pembeli");
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