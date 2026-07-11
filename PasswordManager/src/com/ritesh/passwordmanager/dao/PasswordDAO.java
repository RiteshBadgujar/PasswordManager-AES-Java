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
    public ArrayList<Password> searchPassword(String website) {

        ArrayList<Password> passwordList = new ArrayList<>();

        String sql = "SELECT * FROM passwords WHERE website LIKE ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, "%" + website + "%");

            ResultSet resultSet = statement.executeQuery();

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
    
     // Update Password
    public boolean updatePassword(Password password) {

        String sql = "UPDATE passwords SET username = ?, password = ? WHERE website = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, password.getUsername());
            statement.setString(2, password.getPassword());
            statement.setString(3, password.getWebsite());

            int rowsAffected = statement.executeUpdate();

            System.out.println("Rows Updated : " + rowsAffected);

            return rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
          }
        

    }
 // Delete Password
    public boolean deletePassword(String website) {

        String sql = "DELETE FROM passwords WHERE website = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            // Set Website
            statement.setString(1, website);

            // Execute Delete Query
            int rowsAffected = statement.executeUpdate();

            // Print for Debug
            System.out.println("Rows Deleted : " + rowsAffected);

            // Return Result
            return rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

}