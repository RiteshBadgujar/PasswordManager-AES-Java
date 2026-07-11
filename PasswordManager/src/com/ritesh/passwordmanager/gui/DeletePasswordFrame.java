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

public class DeletePasswordFrame extends JFrame {

    private JLabel titleLabel;
    private JLabel websiteLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private JTextField websiteField;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton searchButton;
    private JButton deleteButton;
    private JButton backButton;
    private JButton showButton;

    public DeletePasswordFrame() {

        setTitle("Delete Password");
        setSize(600, 420);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initializeComponents();
        addComponents();
        addEvents();
    }

    private void initializeComponents() {

        titleLabel = new JLabel("DELETE PASSWORD");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        websiteLabel = new JLabel("Website");
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");

        websiteField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        usernameField.setEditable(false);
        passwordField.setEditable(false);

        searchButton = new JButton("Search");
        deleteButton = new JButton("Delete");
        backButton = new JButton("Back");
        showButton = new JButton("Show");
    }

    private void addComponents() {

        titleLabel.setBounds(150, 20, 250, 30);

        websiteLabel.setBounds(40, 80, 100, 25);
        websiteField.setBounds(150, 80, 220, 25);

        searchButton.setBounds(390, 80, 100, 25);

        usernameLabel.setBounds(40, 140, 100, 25);
        usernameField.setBounds(150, 140, 340, 25);

        passwordLabel.setBounds(40, 190, 100, 25);
        passwordField.setBounds(150, 190, 340, 25);

        deleteButton.setBounds(120, 280, 120, 35);
        backButton.setBounds(290, 280, 120, 35);
        showButton.setBounds(500, 190, 80, 25);

        

        add(titleLabel);
        add(websiteLabel);
        add(usernameLabel);
        add(passwordLabel);

        add(websiteField);
        add(usernameField);
        add(passwordField);

        add(searchButton);
        add(deleteButton);
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
        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String website = websiteField.getText().trim();

                if (website.isEmpty()) {

                    JOptionPane.showMessageDialog(DeletePasswordFrame.this,
                            "Enter Website!");

                    websiteField.requestFocus();
                    return;
                }

                PasswordDAO dao = new PasswordDAO();

                java.util.ArrayList<com.ritesh.passwordmanager.model.Password> list =
                        dao.searchPassword(website);

                if (list.isEmpty()) {

                    JOptionPane.showMessageDialog(DeletePasswordFrame.this,
                            "Password Not Found!");

                    usernameField.setText("");
                    passwordField.setText("");

                    return;
                }

                com.ritesh.passwordmanager.model.Password password = list.get(0);

                usernameField.setText(password.getUsername());
                passwordField.setText(password.getPassword());

            }

        });
     // Delete Button
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // Read Website
                String website = websiteField.getText().trim();

                // Validation
                if (website.isEmpty()) {

                    JOptionPane.showMessageDialog(DeletePasswordFrame.this,
                            "Website is required!");

                    websiteField.requestFocus();
                    return;
                }

                // Confirmation Dialog
                int choice = JOptionPane.showConfirmDialog(
                        DeletePasswordFrame.this,
                        "Are you sure you want to delete this password?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);

                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }

                // Delete from Database
                PasswordDAO dao = new PasswordDAO();

                boolean status = dao.deletePassword(website);

                if (status) {

                    JOptionPane.showMessageDialog(DeletePasswordFrame.this,
                            "Password Deleted Successfully!");

                    // Clear Fields
                    websiteField.setText("");
                    usernameField.setText("");
                    passwordField.setText("");

                    websiteField.requestFocus();

                } else {

                    JOptionPane.showMessageDialog(DeletePasswordFrame.this,
                            "Password Not Found!");

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