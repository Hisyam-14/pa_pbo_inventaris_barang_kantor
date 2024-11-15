/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author TUF
 */
public class CrudPemasok extends Menu {
    //Atribut idpemasok
    private int idPemasok;
    //Atribut final alamat dan kontak
    private final String alamat;
    private final String kontak;
    
    //Getter ID Pemasok, Alamat, dan Kontak
    public int getIdPemasok(){
        return idPemasok;
    }
    

    public String getAlamat() {
        return alamat;
    }

    public String getKontak() {
        return kontak;
    }
    
    //Setter untuk idpemasok
    public void setIdPemasok(int idPemasok) {
        this.idPemasok = idPemasok;
    }

    //Constructor
    public CrudPemasok(String nama, String alamat, String kontak) {
        super(nama); //Memanggil constructor superclass
        this.alamat = alamat;
        this.kontak = kontak;
    }
    
    public void pemasokInsert(){
        try {      
            //Query untuk memasukkan data pemasok
            String query = "INSERT INTO pemasok (nama, alamat, kontak) VALUES (?, ?, ?)";
            
            Database.preparedStatement = Database.connection.prepareStatement(query);
            
            //Mengatur Parameter Query
            Database.preparedStatement.setString(1, getNama());
            Database.preparedStatement.setString(2, getAlamat());
            Database.preparedStatement.setString(3, getKontak());
            
            //Menjalankan query
            Database.preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil menambah pemasok!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terdapat Kesalahan: " + e.getMessage());
        }
    }
    
    public static List<CrudPemasok> getAllPemasok(){
        List<CrudPemasok> pemasokList = new ArrayList<>();
        try{
            //Query untuk mengambil semua data pemasok
            String query = "SELECT * FROM pemasok";
            ResultSet rs = Database.connection.createStatement().executeQuery(query);
            
            //Megambil data dari result set dan menambahkan ke list
            while(rs.next()){
                int idPemasok = rs.getInt("id_pemasok");
                String nama = rs.getString("nama");
                String alamat = rs.getString("alamat");
                String kontak = rs.getString("kontak");
                
                CrudPemasok pemasok = new CrudPemasok(nama, alamat, kontak);
                pemasok.idPemasok = idPemasok;
                pemasokList.add(pemasok);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            
        }
        return pemasokList;
    }
    
    public void pemasokUpdate(){
        try {
            //Query untuk update pemasok
            String query = "UPDATE pemasok set nama = ?, alamat = ?, kontak = ? WHERE id_pemasok = ?";
            
            Database.preparedStatement = Database.connection.prepareStatement(query);
            
            Database.preparedStatement.setString(1, getNama());
            Database.preparedStatement.setString(2, getAlamat());
            Database.preparedStatement.setString(3, getKontak());
            Database.preparedStatement.setInt(4, this.idPemasok);
            
            Database.preparedStatement.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        
    }    
    
    public void pemasokDelete(){
        try{
            //Query untuk menghapus pemasok
            String query = "DELETE FROM pemasok WHERE id_pemasok = ?";
            Database.preparedStatement = Database.connection.prepareStatement(query);
            Database.preparedStatement.setInt(1, this.idPemasok);
            int row = Database.preparedStatement.executeUpdate();
            if(row > 0){
                JOptionPane.showMessageDialog(null, "Pemasok berhasil dihapus!");
            }else{
                JOptionPane.showMessageDialog(null, "Pemasok dengan ID tersebut tidak ditemukan");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
