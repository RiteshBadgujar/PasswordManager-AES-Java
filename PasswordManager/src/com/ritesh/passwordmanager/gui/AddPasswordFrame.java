package com.ritesh.passwordmanager.gui;
import com.ritesh.passwordmanager.encryption.AESUtil;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AddPasswordFrame extends JFrame {

    private JLabel titleLabel;
    private JLabel websiteLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private JTextField websiteField;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton saveButton;
    private JButton clearButton;
    private JButton backButton;

    // Constructor
    public AddPasswordFrame() {

        setTitle("Add Password");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initializeComponents();
        addComponents();
        addEvents();
    }

    // Initialize Components
    private void initializeComponents() {

        titleLabel = new JLabel("ADD PASSWORD");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        websiteLabel = new JLabel("Website");
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");

        websiteField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        saveButton = new JButton("Save");
        clearButton = new JButton("Clear");
        backButton = new JButton("Back");
    }

    // Add Components
    private void addComponents() {

        titleLabel.setBounds(150, 20, 250, 30);

        websiteLabel.setBounds(50, 80, 100, 25);
        websiteField.setBounds(170, 80, 250, 25);

        usernameLabel.setBounds(50, 130, 100, 25);
        usernameField.setBounds(170, 130, 250, 25);

        passwordLabel.setBounds(50, 180, 100, 25);
        passwordField.setBounds(170, 180, 250, 25);

        saveButton.setBounds(50, 260, 100, 35);
        clearButton.setBounds(190, 260, 100, 35);
        backButton.setBounds(330, 260, 100, 35);

        add(titleLabel);

        add(websiteLabel);
        add(usernameLabel);
        add(passwordLabel);

        add(websiteField);
        add(usernameField);
        add(passwordField);

        add(saveButton);
        add(clearButton);
        add(backButton);
    }

    // Events
    private void addEvents() {

        // Save Button
    	saveButton.addActionListener(new ActionListener() {

    	    @Override
    	    public void actionPerformed(ActionEvent e) {

    	        // Read Website
    	        String website = websiteField.getText().trim();

    	        // Read Username
    	        String username = usernameField.getText().trim();

    	        // Read Password
    	        String password = new String(passwordField.getPassword());

    	        // Validation
    	        if (website.isEmpty()) {

    	            JOptionPane.showMessageDialog(null,
    	                    "Website is required!");

    	            websiteField.requestFocus();
    	            return;
    	        }

    	        if (username.isEmpty()) {

    	            JOptionPane.showMessageDialog(null,
    	                    "Username is required!");

    	            usernameField.requestFocus();
    	            return;
    	        }

    	        if (password.isEmpty()) {

    	            JOptionPane.showMessageDialog(null,
    	                    "Password is required!");

    	            passwordField.requestFocus();
    	            return;
    	        }

    	        
    	        // AES Encryption 
    	       

    	        String encryptedPassword = AESUtil.encrypt(password);

    	        System.out.println("Original Password : " + password);
    	        System.out.println("Encrypted Password : " + encryptedPassword);

    	    }

    	});

        // Clear Button
        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                websiteField.setText("");
                usernameField.setText("");
                passwordField.setText("");

            }

        });

        // Back Button
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }

        });

    }

}