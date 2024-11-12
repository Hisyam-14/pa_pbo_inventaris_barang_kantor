/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.sql.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author TUF
 */
public class CrudEdit extends Menu {
    // Atribut untuk menyimpan ID barang, tanggal, kategori, jumlah, dan ID pemasok
    private String idBrg;
    private final String tgl;
    private final String kategori;
    private final int jml;
    private final int idPmsk;
    
    //Setter untuk mengatur idbrg
    public void setIdBrg(String idBrg){
        this.idBrg = idBrg;
    }
    
    //Getter idbrg, tgl, kategori, jml, dan idpmsk
    public String getIdBrg() {
        return idBrg;
    }

    public String getTgl() {
        return tgl;
    }

    public String getKategori() {
        return kategori;
    }

    public int getJml() {
        return jml;
    }

    public int getIdPmsk() {
        return idPmsk;
    }
    
    //Contructor
    public CrudEdit(String idBrg, String nama, String tgl, String kategori, int jml, int idPmsk) {
        super(nama);
        this.idBrg = idBrg;
        this.tgl = tgl;
        this.kategori = kategori;
        this.jml = jml;
        this.idPmsk = idPmsk;
    }
    
    
    public static void comboPemasok(JComboBox<String> comboBox){
        String query = "SELECT id_pemasok, nama FROM pemasok";
        try{
            ResultSet rs = Database.connection.createStatement().executeQuery(query);
            
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            
             // Mengambil data pemasok dari ResultSet dan menambahkannya ke model JComboBox
            while(rs.next()){
                int idPemasok = rs.getInt("id_pemasok");
                String nama = rs.getString("nama");
                model.addElement(idPemasok + " - " + nama);
            }
            comboBox.setModel(model);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error: " + e.getMessage());
        }
    }
    
    
    public static List<CrudEdit> getAllBrg() {
        List<CrudEdit> brgList = new ArrayList<>();
        try {
            String query = "SELECT * FROM barang";
            ResultSet rs = Database.connection.createStatement().executeQuery(query);
            
            // Mengambil data barang dari ResultSet dan menambahkannya ke list
            while (rs.next()) {
                String idBrg = rs.getString("id_brg");
                String nama = rs.getString("nm_brg");
                String tgl = rs.getString("tgl_msk");
                String kategori = rs.getString("kategori");
                int jml = rs.getInt("jml");
                int idPmsk = rs.getInt("id_pemasok");

                CrudEdit edit = new CrudEdit(idBrg, nama, tgl, kategori, jml, idPmsk);
                brgList.add(edit);
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return brgList;
    }
    
    public void brgInsert() {
        try {
            String query = "INSERT INTO barang (id_brg, nm_brg, tgl_msk, kategori, jml, id_pemasok) VALUES (?, ?, ?, ?, ?, ?)";

            Database.preparedStatement = Database.connection.prepareStatement(query);

            Database.preparedStatement.setString(1, getIdBrg());
            Database.preparedStatement.setString(2, getNama());
            Database.preparedStatement.setString(3, getTgl());  // Menyimpan tanggal dalam format yyyy-MM-dd
            Database.preparedStatement.setString(4, getKategori());
            Database.preparedStatement.setInt(5, getJml());
            Database.preparedStatement.setInt(6, getIdPmsk());

            Database.preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil menambah barang!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terdapat Kesalahan: " + e.getMessage());
        }
    }
    
    public void brgUpdate(){
        try {
            String query = "UPDATE barang set nm_brg = ?, tgl_msk = ?, kategori = ?, jml = ?, id_pemasok = ? WHERE id_brg = ?";
            
            Database.preparedStatement = Database.connection.prepareStatement(query);
            
            Database.preparedStatement.setString(1, getNama());
            Database.preparedStatement.setString(2, getTgl());
            Database.preparedStatement.setString(3, getKategori());
            Database.preparedStatement.setInt(4, getJml());
            Database.preparedStatement.setInt(5, getIdPmsk());
            Database.preparedStatement.setString(6, getIdBrg());
            
            Database.preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Barang berhasil diupdate");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        
    }   

    public void brgDelete(){
        try{
            String query = "DELETE FROM barang WHERE id_brg = ?";
            Database.preparedStatement = Database.connection.prepareStatement(query);
            Database.preparedStatement.setString(1, getIdBrg());
            int row = Database.preparedStatement.executeUpdate();
            if(row > 0){
                JOptionPane.showMessageDialog(null, "Barang berhasil dihapus!");
            }else{
                JOptionPane.showMessageDialog(null, "Barang dengan ID tersebut tidak ditemukan");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
