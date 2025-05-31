package com.gudangdamar.main.model;

import jakarta.persistence.*;

@Entity
@Table(name = "barang")
public class Barang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Barang")
    private int idBarang;

    @Column(name = "nama")
    private String nama;

    @Embedded
    private Harga harga;

    @Embedded
    private Kategori kategori;

    public Barang() {}

    public Barang(int idBarang, String nama, Harga harga, Kategori kategori) {
        this.idBarang = idBarang;
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Harga getHarga() {
        return harga;
    }

    public void setHarga(Harga harga) {
        this.harga = harga;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }
}
