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
