/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.sql.*;
/**
 *
 * @author TUF
 */
public class Database {
    public static Connection connection = null; //Koneksi ke database
    
    protected static Statement statement; //Statement untuk menjalankan query
    protected static PreparedStatement preparedStatement; //PreparedStatement untuk menjalankan query yang disiapkan
    protected static ResultSet resultSet; //Untuk menyimpan hasil dari query
    
    //Konfigurasi database
    private final static String DB_HOST = "localhost"; //Host database
    private final static String DB_NAME = "db_inventaris_barang_kantor2"; //Nama database
    private final static String DB_USERNAME = "root"; //Username untuk koneksi
    private final static String DB_PASSWORD = ""; //Password untuk koneksi
    
    //Contructor untuk JDBC
    public Database(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Driver MYSQL
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load driver!");
        }
    }
    
    public final static Connection connect(){
        try {
            //URL koneksi Database
            String url = "jdbc:mysql://" + DB_HOST + "/" + DB_NAME;
            connection = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD); //Menghubungkan ke Database
            System.out.println("Database connected!");
        } catch (SQLException e) {
            System.out.println("Failed to connect database!");
        }
        return connection;
    }

    public final static void disconnect() {
        try {
            connection.close(); //Menutup koneksi
            System.out.println("Database disconnected!");
        } catch (SQLException e) {
            System.out.println("Failed to disconnect database!");
        }
    }
}
