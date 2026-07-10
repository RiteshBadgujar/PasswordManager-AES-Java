package com.ritesh.passwordmanager.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.ritesh.passwordmanager.dao.PasswordDAO;
import com.ritesh.passwordmanager.model.Password;

public class ViewPasswordsFrame extends JFrame {

    private JTable passwordTable;
    private JScrollPane scrollPane;
    private JButton backButton;

    // Constructor Created
    public ViewPasswordsFrame() {

        setTitle("View Passwords");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initializeComponents();
        addComponents();
        addEvents();
        loadPasswords();
    }

    // Initialize Components
    private void initializeComponents() {

        passwordTable = new JTable();

        scrollPane = new JScrollPane(passwordTable);

        backButton = new JButton("Back");
    }

    // Add Components
    private void addComponents() {

        scrollPane.setBounds(20, 20, 650, 300);

        backButton.setBounds(280, 340, 120, 35);

        add(scrollPane);
        add(backButton);
    }

    // Events
    private void addEvents() {

        backButton.addActionListener(new ActionListener() {
        	

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
            

        });
     
    }
    
    private void loadPasswords() {

        PasswordDAO dao = new PasswordDAO();

        ArrayList<Password> list = dao.getAllPasswords();

        // Create Table Model
        DefaultTableModel model = new DefaultTableModel();

        // Add Column Names
        model.addColumn("Website");
        model.addColumn("Username");
        model.addColumn("Password");

        // Add Rows
        for (Password password : list) {

            Object[] row = {

                password.getWebsite(),
                password.getUsername(),
                password.getPassword()

            };

            model.addRow(row);

        }

        // Set Model to JTable
        passwordTable.setModel(model);

    }
}