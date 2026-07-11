package com.ritesh.passwordmanager.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DashboardFrame extends JFrame {

    private JLabel titleLabel;
    private JLabel welcomeLabel;

    private JButton addButton;
    private JButton viewButton;
    private JButton searchButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton logoutButton;

    // Constructor
    public DashboardFrame() {

        setTitle("Password Manager Dashboard");
        setSize(500, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initializeComponents();
        addComponents();
        addEvents();
    }

    // Initialize Components
    private void initializeComponents() {

        titleLabel = new JLabel("PASSWORD MANAGER");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        welcomeLabel = new JLabel("Welcome, Admin");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        addButton = new JButton("Add Password");
        viewButton = new JButton("View Passwords");
        searchButton = new JButton("Search Password");
        updateButton = new JButton("Update Password");
        deleteButton = new JButton("Delete Password");
        logoutButton = new JButton("Logout");
    }

    // Add Components
    private void addComponents() {

        titleLabel.setBounds(120, 20, 300, 30);
        welcomeLabel.setBounds(170, 60, 200, 25);

        addButton.setBounds(150, 100, 180, 35);
        viewButton.setBounds(150, 145, 180, 35);
        searchButton.setBounds(150, 190, 180, 35);
        updateButton.setBounds(150, 235, 180, 35);
        deleteButton.setBounds(150, 280, 180, 35);
        logoutButton.setBounds(150, 325, 180, 35);

        add(titleLabel);
        add(welcomeLabel);

        add(addButton);
        add(viewButton);
        add(searchButton);
        add(updateButton);
        add(deleteButton);
        add(logoutButton);
    }

    // Button Events
    private void addEvents() {

        // Add Password Button
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                AddPasswordFrame addPasswordFrame = new AddPasswordFrame();
                addPasswordFrame.setVisible(true);

            }

        });
        

        // Logout Button
        logoutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                LoginFrame login = new LoginFrame();
                login.setVisible(true);

                dispose();

            }

        });

    
        // View Password Button
       viewButton.addActionListener(new ActionListener() {

    	   public void actionPerformed(ActionEvent e) {

            ViewPasswordsFrame viewPasswordsFrame = new ViewPasswordsFrame();
            viewPasswordsFrame.setVisible(true);

        }

       });
       
    //Search Password Button
       searchButton.addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e) {

               SearchPasswordFrame searchPasswordFrame = new SearchPasswordFrame();
               searchPasswordFrame.setVisible(true);

           }

       });
       
       //Update Password Button
       updateButton.addActionListener(new ActionListener() {

    	    @Override
    	    public void actionPerformed(ActionEvent e) {

    	        UpdatePasswordFrame frame = new UpdatePasswordFrame();
    	        frame.setVisible(true);

    	    }

    	});
       deleteButton.addActionListener(new ActionListener() {

    	    @Override
    	    public void actionPerformed(ActionEvent e) {

    	        DeletePasswordFrame frame = new DeletePasswordFrame();
    	        frame.setVisible(true);

    	    }

    	});
       
  }
   
}