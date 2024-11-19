# PROJECT AKHIR PEMROGRAMAN BERORIENTASI OBJEK
## INVENTARIS BARANG KANTOR 
-----  
### DESKRIPSI PROJECT
_Inventa Office_ adalah aplikasi pengelola inventaris barang yang dirancang untuk membantu dalam proses mengelola data barang secara efisien dan terstruktur. Aplikasi ini memiliki fitur utama seperti menambah, mengedit, menghapus, mencari, dan mengurutkan data barang. Dengan sistem berbasis login untuk menjaga keamanan, _Inventa Office_ mempermudah manajemen inventaris serta memberikan fleksibilitas bagi pengguna untuk mengelola barang sesuai kebutuhan.

Fitur Aplikasi:
- Menu login: Untuk menjaga keamanan data pengguna harus login terlebih dahulu.
- Menu edit barang: Pengguna dapat menambah, menghapus, memperbarui data barang kantor sesuai dengan kebutuhan, serta tambahan fitur clear apabila user salah menginput data.
- Menu utama: Pengguna dapat melihat daftar barang yang terdata dalam aplikasi. Pilihan untuk sorting dan searching yang memudahkan pengguna dalam mencari dan menampilkan data barang sesuai dengan keinginan pengguna.
- Menu Pemasok: Pengguna dapat menambah, menghapus, memperbarui data pemasok barang kantor, serta tambahan fitur clear apabila user salah menginput data.

### FLOWCHART
Login Aplikasi
![flowchart login](https://github.com/user-attachments/assets/7134f515-0880-40eb-bb3f-d20e19773a1f)

Menu Edit
![flowchart Edit](https://github.com/user-attachments/assets/4166e816-b52f-482e-bc15-dc9f2db44ee3)

Menu Utama
![flowchart utama](https://github.com/user-attachments/assets/9273ef43-6019-4654-aac6-fa5ef70f0af3)

### ERD
Logical ERD
![Logical ERD](https://github.com/user-attachments/assets/9c60e267-d8a3-4f38-8147-b2fe0a06f4eb)
Relational ERD
![Relational ERD](https://github.com/user-attachments/assets/46fff2ce-fdb9-474e-9137-b3a74503e487)

### ACTIVITY DIAGRAM
![activity diagram](https://github.com/user-attachments/assets/331b05f7-90e4-431e-9a67-5f4b862d0ed3)


### USE CASE DIAGRAM
![use case pa drawio](https://github.com/user-attachments/assets/cf704426-836f-4b23-8f8b-0077ed7b1a59)

### STRUKTUR PROJECT
![struktur project](https://github.com/user-attachments/assets/06a29d63-0e47-40f3-ab5e-96b0496d5c4e)

Project inventaris barang kantor ini terdiri dari 2 package yaitu:
- GUI (Grapichal User Interface)
  Package ini menangani antarmuka pengguna (GUI) dengan menyediakan elemen-elemen grafis dan komponen tampilan lainnya yang memungkinkan        pengguna berinteraksi dengan aplikasi.
- Models
  Package ini menangani proses-proses yang berhubungan dengan logika dan interaksi data, input, struktur database, validasi data, dan juga      alur control dari program secara keseluruhan.

### SOURCE CODE DAN PENJELASAN
#### PACKAGE MODEL
- CrudEdit.java : Class ini berisikan code untuk menangani operasi CRUD (Create, Read, Update, Delete) yang berkaitan dengan data barang yang perlu diedit.
```java
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
```


- CrudPemasok.java : Class ini berisikan code untuk mengelola operasi CRUD yang berkaitan dengan pemasok, seperti menambah, mengedit, menghapus, atau melihat data pemasok.
```java
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
            JOptionPane.showMessageDialog(null, "Pemasok berhasil diupdate");
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
```


- Database.javaa : Class ini berisikan code yang berfungsi sebagai sebagai konektor ke database, menangani koneksi, query, dan pengelolaan data secara umum.
```java
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
```


- Login.java : Class ini berisikan code untuk mengelola proses autentikasi pengguna, seperti memverifikasi username dan password, serta memvalidasi akses ke aplikasi
```java
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
```


- Menu.java : Class ini berisikan code yang berfungsi untuk menyimpan data atau struktur menu utama yang mungkin digunakan oleh berbagai fitur aplikasi
```java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package models;
/**
 *
 * @author TUF
 */
abstract class Menu {
    // Nama menu yang bersifat final
    final private String nama;
    
    // Constructor
    public Menu(String nama) {
        this.nama = nama;
    }
    
    // Getter nama
    public String getNama() {
        return nama;
    }
}
```


- Utama.java : Class ini berisikan code logika utama aplikasi atau fungsi-fungsi dasar yang menghubungkan model dengan GUI, serta mengelola jalannya aplikasi secara umum.
```java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author TUF
 */
public class Utama {
    // Method untuk sort ID Barang terkecil
    public List<CrudEdit> sortByIdBrgTerkecil(List<CrudEdit> brgList) {
        return brgList.stream()
                .sorted(Comparator.comparing(CrudEdit::getIdBrg))
                .collect(Collectors.toList());
    }
    
    // Method untuk sort ID Barang terbesar
    public List<CrudEdit> sortByIdBrgTerbesar(List<CrudEdit> brgList) {
        return brgList.stream()
                .sorted(Comparator.comparing(CrudEdit::getIdBrg).reversed())
                .collect(Collectors.toList());
    }
    
    // Method untuk sort nama barang dari A ke Z
    public List<CrudEdit> sortByNamaBrgAZ(List<CrudEdit> brgList) {
        return brgList.stream()
                .sorted(Comparator.comparing(CrudEdit::getNama).reversed())
                .collect(Collectors.toList());
    }
    
    // Method untuk sort nama barang dari Z ke A
    public List<CrudEdit> sortByNamaBrgZA(List<CrudEdit> brgList) {
        return brgList.stream()
                .sorted(Comparator.comparing(CrudEdit::getNama))
                .collect(Collectors.toList());
    }

    // Method untuk sort jumlah barang dari yang terkecil
    public List<CrudEdit> sortByJumlahBrgTerkecil(List<CrudEdit> brgList) {
        return brgList.stream()
                .sorted(Comparator.comparing(CrudEdit::getJml))
                .collect(Collectors.toList());
    }
    
    // Method untuk sort jumlah barang dari yang terbanyak
    public List<CrudEdit> sortByJumlahBrgTerbesar(List<CrudEdit> brgList) {
        return brgList.stream()
                .sorted(Comparator.comparing(CrudEdit::getJml).reversed())
                .collect(Collectors.toList());
    }
    
    // Method untuk sort barang dari yang baru masuk
    public List<CrudEdit> sortByTglMasukTerbaru(List<CrudEdit> brgList) {
        return brgList.stream()
                .sorted(Comparator.comparing(CrudEdit::getTgl).reversed())
                .collect(Collectors.toList());
    }
    
    // Method untuk sort barang dari yang lama masuk
    public List<CrudEdit> sortByTglMasukTerlama(List<CrudEdit> brgList) {
        return brgList.stream()
                .sorted(Comparator.comparing(CrudEdit::getTgl))
                .collect(Collectors.toList());
    }
    
    // Method untuk mengambil kategori pada tabel barang
    public List<String> getKtr(List<CrudEdit> brgList) {
        return brgList.stream()
                .map(CrudEdit::getKategori)
                .distinct()
                .collect(Collectors.toList());
    }

    // Method untuk memfilter barang berdasarkan kategori yang dipilih
    public List<CrudEdit> filterKtr(List<CrudEdit> brgList, String kategori) {
        return brgList.stream()
                .filter(brg -> brg.getKategori().equalsIgnoreCase(kategori))
                .collect(Collectors.toList());
    }
}
```
### OUTPUT PROGRAM
- Login
  Berikut adalah tampilan dari login aplikasi, user perlu memasukkan username dan password yang benar untuk mengakses aplikasi.
  ![login](https://github.com/user-attachments/assets/531f7501-c056-4758-ae99-85b5e54c3214)


  Ketika user salah memasukkan username atau password, program akan memunculkan pop-up seperti berikut.
  ![Gagal Login](https://github.com/user-attachments/assets/f502be6d-d410-419b-989e-fc9eb4803291)



- Menu Utama
  Setelah login user akan langsung masuk ke dalam menu utama. Di dalam menu utama user dapat melihat daftar barang yang ada didalam inventaris beserta detail barangnya. Pada menu utama ini user juga dapat melakukan sorting dan searching berdasarkan beberapa kriteria. Berikut tampilan menu utama.
  ![Menu utama](https://github.com/user-attachments/assets/67bd2916-6092-439b-a801-82e3e8871e72)

  Berikut contoh tampilan saat sorting berdasarkan kriteria jumlah barang terkecil.
  ![sorting](https://github.com/user-attachments/assets/c942278b-8831-4ed4-9cca-b7747dbd6572)

  Berikut contoh ketika user memilih filter barang berdasarkan kategori keamanan.
  ![filter kategori](https://github.com/user-attachments/assets/624a46d2-1d12-4f40-a17f-5748be1e3044)

  Berikut contoh tampilan saat user melakukan searching berdasarkan nama barang.
  ![searching](https://github.com/user-attachments/assets/8dc4cfa4-db84-45e9-bd67-e68858a61e45)

- Menu Edit
  Dari menu utama user dapat memilih untuk masuk ke dalam barang atau pemasok untuk mengedit data barang maupun pemasok.Berikut tampilan beberapa fitur daalam menu barang dan pemasok.

  Berikut tampilan ketika user masuk ke dalam pemasok. Terdapat beberapa fitur yang dapat dipilih yaitu insert data, update, delete dan clear apabila user salah menginputkan data.
  ![image](https://github.com/user-attachments/assets/034ec616-505c-4060-b2bd-ff2e91987897)

  Berikut contoh tampilan menggunakan fitur insert data barang. Dimana user perlu memasukkan ID, nama, tanggal, jumlahh, kategori, dan id pemasok barang.

  ![image](https://github.com/user-attachments/assets/51e211ef-bd22-4924-90f8-d66e979b3bdd)

  Berikut contoh tampilan menggunakan fitur update data barang. Update data barang dengan cara mengklik baris data dalam tabel yang ingin di update setelahnya user dapat mengganti data yang ingin di update.
  ![image](https://github.com/user-attachments/assets/d589f651-0010-47ba-a2ce-aabaa48651d2)

  Berikut contoh tampilan menggunakan fitur delete barang. Menghapus data barang, dengan cara mengklik data yang ingin dihapus. Sebelum berhasil dihapus akan keluar pilihan apakah yakin ingin menghapus data barang atau tidak, jika memilih YES maka data telah berhasil dihapus dari table.

  ![image](https://github.com/user-attachments/assets/ec8f1fa2-3155-445e-b08f-8a44923027e9)





