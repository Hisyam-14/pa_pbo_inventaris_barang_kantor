package models;
import java.sql.*;
import javax.swing.*;

public class Login{
    // Getter username dan password
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    // Contstructor
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
    // Atribut username dan password yang bersifat final
    final private String username;
    final private String password;
    
    final public static boolean loginAccount(String username, String password){
        try {
            // Query untuk periksa apakah username dan password sesuai
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";

            Database.preparedStatement = Database.connection.prepareStatement(query);
            Database.preparedStatement.setString(1, username);
            Database.preparedStatement.setString(2, password);

            Database.resultSet = Database.preparedStatement.executeQuery();
            if (Database.resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e){
            System.out.println("Error mzz: " + e.getMessage());
        }
        return false;
    }
    
    final public static Integer getUserID(String username, String password){
        try {
            // Query untuk mendapatkan ID User berdasarkan username dan password
            String query = "SELECT id_user FROM user WHERE username = ? AND password = ?";
            
            Database.preparedStatement = Database.connection.prepareStatement(query);
            Database.preparedStatement.setString(1, username);
            Database.preparedStatement.setString(2, password);
            Database.resultSet = Database.preparedStatement.executeQuery();
            
            if (Database.resultSet.next()) {
                int userID = Database.resultSet.getInt("id_user");
                return userID;
            } else {
                return null;
            }
            
        } catch (SQLException e) {
        }
        return null;
    }
}
