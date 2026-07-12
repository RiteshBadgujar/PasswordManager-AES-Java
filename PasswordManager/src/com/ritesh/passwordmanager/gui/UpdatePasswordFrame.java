package com.ritesh.passwordmanager.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ritesh.passwordmanager.dao.PasswordDAO;
import com.ritesh.passwordmanager.model.Password;
import com.ritesh.passwordmanager.encryption.AESUtil;

public class UpdatePasswordFrame extends JFrame {

    private JLabel titleLabel;
    private JLabel websiteLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private JTextField websiteField;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton searchButton;
    private JButton updateButton;
    private JButton backButton;
    private JButton showButton;

    public UpdatePasswordFrame() {

        setTitle("Update Password");
        setSize(600,420);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initializeComponents();
        addComponents();
        addEvents();

    }

    private void initializeComponents() {

        titleLabel = new JLabel("UPDATE PASSWORD");
        titleLabel.setFont(new Font("Arial", Font.BOLD,22));

        websiteLabel = new JLabel("Website");
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");

        websiteField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        searchButton = new JButton("Search");
        updateButton = new JButton("Update");
        backButton = new JButton("Back");
        showButton = new JButton("Show");

    }

    private void addComponents() {

        titleLabel.setBounds(150,20,250,30);

        websiteLabel.setBounds(40,80,100,25);
        websiteField.setBounds(150,80,220,25);

        searchButton.setBounds(390,80,100,25);

        usernameLabel.setBounds(40,140,100,25);
        usernameField.setBounds(150,140,340,25);

        passwordLabel.setBounds(40,190,100,25);
        passwordField.setBounds(150,190,340,25);

        updateButton.setBounds(120,280,120,35);
        backButton.setBounds(290,280,120,35);
        showButton.setBounds(480, 190, 80, 25);

       

        add(titleLabel);

        add(websiteLabel);
        add(usernameLabel);
        add(passwordLabel);

        add(websiteField);
        add(usernameField);
        add(passwordField);

        add(searchButton);
        add(updateButton);
        add(backButton);
        add(showButton);

    }

    private void addEvents() {

        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
            

        });
     // Search Button
        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // Read Website
                String website = websiteField.getText().trim();

                // Validation
                if (website.isEmpty()) {

                    JOptionPane.showMessageDialog(UpdatePasswordFrame.this,
                            "Enter Website!");

                    websiteField.requestFocus();
                    return;
                }

                // Search in Database
                PasswordDAO dao = new PasswordDAO();

                java.util.ArrayList<Password> list = dao.searchPassword(website);

                if (list.isEmpty()) {

                    JOptionPane.showMessageDialog(UpdatePasswordFrame.this,
                            "Password Not Found!");

                    return;
                }

                // Get First Record
                Password password = list.get(0);

                // Fill Fields
                usernameField.setText(password.getUsername());

                // Show encrypted password (for now)
                passwordField.setText(
                        AESUtil.decrypt(password.getPassword())
                );

            }

        });
        
     // Update Button
        updateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // Read Data
                String website = websiteField.getText().trim();
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());

                // Validation
                if (website.isEmpty()) {

                    JOptionPane.showMessageDialog(UpdatePasswordFrame.this,
                            "Website is required!");

                    websiteField.requestFocus();
                    return;
                }

                if (username.isEmpty()) {

                    JOptionPane.showMessageDialog(UpdatePasswordFrame.this,
                            "Username is required!");

                    usernameField.requestFocus();
                    return;
                }

                if (password.isEmpty()) {

                    JOptionPane.showMessageDialog(UpdatePasswordFrame.this,
                            "Password is required!");

                    passwordField.requestFocus();
                    return;
                }

                // Encrypt Password
                String encryptedPassword = AESUtil.encrypt(password);

                // Create Password Object
                Password passwordObj = new Password();

                passwordObj.setWebsite(website);
                passwordObj.setUsername(username);
                passwordObj.setPassword(encryptedPassword);

                // Update Database
                PasswordDAO dao = new PasswordDAO();
                System.out.println("Website : " + website);
                System.out.println("Username : " + username);
                System.out.println("Original Password : " + password);
                System.out.println("Encrypted Password : " + encryptedPassword);

                boolean status = dao.updatePassword(passwordObj);

                if (status) {

                    JOptionPane.showMessageDialog(UpdatePasswordFrame.this,
                            "Password Updated Successfully!");

                } else {

                    JOptionPane.showMessageDialog(UpdatePasswordFrame.this,
                            "Update Failed!");

                }

            }

        });
        
        //Password show & Hide logic code
        showButton.addActionListener(new ActionListener() {

            private boolean visible = false;

            @Override
            public void actionPerformed(ActionEvent e) {

                if (!visible) {

                    passwordField.setEchoChar((char) 0);

                    showButton.setText("Hide");

                    visible = true;

                } else {

                    passwordField.setEchoChar('*');

                    showButton.setText("Show");

                    visible = false;

                }

            }

        });
       
    }

}