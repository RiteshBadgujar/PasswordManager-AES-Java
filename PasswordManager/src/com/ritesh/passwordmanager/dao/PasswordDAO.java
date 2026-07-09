package com.ritesh.passwordmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ritesh.passwordmanager.database.DBConnection;
import com.ritesh.passwordmanager.model.Password;

public class PasswordDAO {

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
}