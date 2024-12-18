import java.awt.Font;
import java.awt.Image;
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
    // Declaration of instance variables
    private JButton buttonstaff;
    private JButton buttonbuyer;
    private JPanel cardPanel; // Declare the cardPanel
    private JTextField usernameField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        // Create an instance of the class and run the GUI
        Ourversetest app = new Ourversetest();
        app.createAndShowGUI(); // Run the GUI
    }

    // Method to create and show the GUI
    private void createAndShowGUI() {
        // Original image for the icon
        ImageIcon originalImage = new ImageIcon(
                "D:\\XN_DATA\\Collage\\Ourverse_PBO_JavaSwing\\Ourverse-Project\\OurLogo.png");

        // Resize the image
        Image resizedImage = originalImage.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);

        // Label with the resized image
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(resizedImageIcon);
        imageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT); // Center the image horizontally

        // Text label
        JLabel textLabel = new JLabel("Welcome To Ourverse");
        textLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        textLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT); // Center the text horizontally

        // Button 1: Staff Session
        buttonstaff = new JButton("Masuk Sebagai Staff");
        buttonstaff.setFont(new Font("SansSerif", Font.PLAIN, 14));
        buttonstaff.setFocusable(false);
        buttonstaff.setAlignmentX(JButton.CENTER_ALIGNMENT); // Center the button horizontally
        buttonstaff.addActionListener(this); // Add action listener

        // Button 2: Buyer Session
        buttonbuyer = new JButton("Masuk Sebagai Pembeli");
        buttonbuyer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        buttonbuyer.setFocusable(false);
        buttonbuyer.setAlignmentX(JButton.CENTER_ALIGNMENT); // Center the button horizontally
        buttonbuyer.addActionListener(this); // Add action listener

        // Create the card panel with CardLayout
        cardPanel = new JPanel(new CardLayout());

        // Create the main menu panel
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel, BoxLayout.Y_AXIS));
        mainMenuPanel.add(Box.createVerticalGlue()); // Add flexibility for the top
        mainMenuPanel.add(imageLabel); // Add image
        mainMenuPanel.add(Box.createVerticalStrut(10)); // Add spacing between elements
        mainMenuPanel.add(textLabel); // Add text
        mainMenuPanel.add(Box.createVerticalStrut(20)); // Add spacing between elements
        mainMenuPanel.add(buttonstaff); // Add staff button
        mainMenuPanel.add(Box.createVerticalStrut(10)); // Add spacing between elements
        mainMenuPanel.add(buttonbuyer); // Add buyer button
        mainMenuPanel.add(Box.createVerticalGlue()); // Add flexibility for the bottom

        // Add the main menu panel to the card panel
        cardPanel.add(mainMenuPanel, "Main Menu");

        // Create and add the Staff Session and Buyer Session panels
        cardPanel.add(createStaffSessionPanel(), "Staff Session");
        // cardPanel.add(createBuyerSessionPanel(), "Buyer Session");

        // JFrame setup
        JFrame frame = new JFrame("Ourverse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400, 400); // Set the frame size
        frame.setIconImage(originalImage.getImage()); // Set the frame icon
        frame.add(cardPanel); // Add the card panel to the frame
        frame.setVisible(true);
    }

    // Method to handle button actions
    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();

        if (e.getSource() == buttonstaff) {
            // Switch to the Staff Session panel
            System.out.println("Enter Staff Session");
            cardLayout.show(cardPanel, "Staff Session");
        } else if (e.getSource() == buttonbuyer) {
            // Switch to the Buyer Session panel
            System.out.println("Enter Buyer Session");
            cardLayout.show(cardPanel, "Buyer Session");
        }
    }

    // Method to create the Staff Session panel with login form
    private JPanel createStaffSessionPanel() {
        JPanel staffPanel = new JPanel();
        staffPanel.setLayout(new BoxLayout(staffPanel, BoxLayout.Y_AXIS));
        staffPanel.setBorder(new EmptyBorder(20, 30, 20, 30)); // Top, Left, Bottom, Right margins

        // Label for Login
        JLabel textLabel = new JLabel("Login As Staff");
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
            JOptionPane.showMessageDialog(null, "Login Button Clicked", "Information", JOptionPane.INFORMATION_MESSAGE);
        });

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
}