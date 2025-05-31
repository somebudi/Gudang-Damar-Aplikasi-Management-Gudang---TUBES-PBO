package com.gudangdamar.main.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    @Column(name = "merek")
    private String merek;

    @Column(name = "nama_barang")
    private String namaBarang;

    @Column(name = "nama_supplier")
    private String namaSupplier;

    @Column(name = "stok")
    private int stok;
     @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "waktu_keluar")
    private LocalDateTime waktuKeluar;

     @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "waktu_masuk")
    private LocalDateTime waktuMasuk;

     @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "waktu_pendataan")
    private LocalDateTime waktuPendataan;

    @Column(name = "pesanan_id")
    private Integer pesananId; // karena bisa null

    public Barang() {}

    // Getter & Setter
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

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public void setNamaSupplier(String namaSupplier) {
        this.namaSupplier = namaSupplier;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public LocalDateTime getWaktuKeluar() {
        return waktuKeluar;
    }

    public void setWaktuKeluar(LocalDateTime waktuKeluar) {
        this.waktuKeluar = waktuKeluar;
    }

    public LocalDateTime getWaktuMasuk() {
        return waktuMasuk;
    }

    public void setWaktuMasuk(LocalDateTime waktuMasuk) {
        this.waktuMasuk = waktuMasuk;
    }

    public LocalDateTime getWaktuPendataan() {
        return waktuPendataan;
    }

    public void setWaktuPendataan(LocalDateTime waktuPendataan) {
        this.waktuPendataan = waktuPendataan;
    }

    public Integer getPesananId() {
        return pesananId;
    }

    public void setPesananId(Integer pesananId) {
        this.pesananId = pesananId;
    }
}
