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
