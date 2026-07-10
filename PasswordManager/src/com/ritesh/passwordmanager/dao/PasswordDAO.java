package com.ritesh.passwordmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ritesh.passwordmanager.database.DBConnection;
import com.ritesh.passwordmanager.model.Password;

public class PasswordDAO {

    // Save Password
    public boolean savePassword(Password password) {

        String sql = "INSERT INTO passwords (website, username, password) VALUES (?, ?, ?)";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, password.getWebsite());
            statement.setString(2, password.getUsername());
            statement.setString(3, password.getPassword());

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }
    }

    // Get All Passwords
    public ArrayList<Password> getAllPasswords() {

        ArrayList<Password> passwordList = new ArrayList<>();

        String sql = "SELECT * FROM passwords";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
        ) {

            while (resultSet.next()) {

                Password password = new Password();

                password.setWebsite(resultSet.getString("website"));
                password.setUsername(resultSet.getString("username"));
                password.setPassword(resultSet.getString("password"));

                passwordList.add(password);
            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return passwordList;
    }

}