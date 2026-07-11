package com.ritesh.passwordmanager.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class LoginFrame extends JFrame {

    private JLabel titleLabel;
    private JLabel passwordLabel;

    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton exitButton;

    // Constructor
    public LoginFrame() {

        setTitle("Password Manager");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        initializeComponents();
        addComponents();
        addEvents();
    }

    // Initialize Components
    private void initializeComponents() {

        titleLabel = new JLabel("Password Manager");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        passwordLabel = new JLabel("Master Password");

        passwordField = new JPasswordField();

        loginButton = new JButton("Login");

        exitButton = new JButton("Exit");
    }

    // Add Components to JFrame
    private void addComponents() {

        titleLabel.setBounds(100, 20, 250, 40);

        passwordLabel.setBounds(50, 90, 120, 25);

        passwordField.setBounds(180, 90, 180, 25);

        loginButton.setBounds(90, 170, 100, 35);

        exitButton.setBounds(230, 170, 100, 35);

        add(titleLabel);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(exitButton);
    }

    // Button Events
    private void addEvents() {

        // Login Button
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                login();

            }

        });

        // Exit Button
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }

        });

    }

    // Login Method
    
    private void login() {

        String password = new String(passwordField.getPassword());

        if (password.equals("admin123")) {

            JOptionPane.showMessageDialog(this, "Login Successful!");

            DashboardFrame dashboard = new DashboardFrame();
            dashboard.setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(this, "Invalid Password!");

        }
    }
}