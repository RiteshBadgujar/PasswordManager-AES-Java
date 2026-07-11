package com.ritesh.passwordmanager.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.ritesh.passwordmanager.dao.PasswordDAO;
import com.ritesh.passwordmanager.model.Password;

public class SearchPasswordFrame extends JFrame {

    private JLabel titleLabel;
    private JLabel websiteLabel;

    private JTextField websiteField;

    private JButton searchButton;
    private JButton backButton;

    private JTable passwordTable;
    private JScrollPane scrollPane;

    public SearchPasswordFrame() {

        setTitle("Search Password");
        setSize(700, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initializeComponents();
        addComponents();
        addEvents();
    }

    private void initializeComponents() {

        titleLabel = new JLabel("SEARCH PASSWORD");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        websiteLabel = new JLabel("Website");

        websiteField = new JTextField();

        searchButton = new JButton("Search");
        backButton = new JButton("Back");

        passwordTable = new JTable();
        scrollPane = new JScrollPane(passwordTable);

    }

    private void addComponents() {

        titleLabel.setBounds(220, 20, 250, 30);

        websiteLabel.setBounds(40, 80, 100, 25);
        websiteField.setBounds(120, 80, 250, 25);

        searchButton.setBounds(400, 80, 100, 30);
        backButton.setBounds(520, 80, 100, 30);

        scrollPane.setBounds(20, 140, 650, 280);

        add(titleLabel);
        add(websiteLabel);
        add(websiteField);
        add(searchButton);
        add(backButton);
        add(scrollPane);

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

                PasswordDAO dao = new PasswordDAO();

                ArrayList<Password> list = dao.searchPassword(website);

                DefaultTableModel model = new DefaultTableModel();

                model.addColumn("Website");
                model.addColumn("Username");
                model.addColumn("Password");

                for (Password password : list) {

                    Object row[] = {

                            password.getWebsite(),
                            password.getUsername(),
                            password.getPassword()

                    };

                    model.addRow(row);

                }
                if (list.isEmpty()) {

                    JOptionPane.showMessageDialog(SearchPasswordFrame.this,
                            "No Password Found!");

                    return;
                }

                passwordTable.setModel(model);

            }

        });

    }

}