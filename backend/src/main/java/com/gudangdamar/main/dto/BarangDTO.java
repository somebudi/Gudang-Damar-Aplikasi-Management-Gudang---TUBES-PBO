package com.gudangdamar.main.dto;

public class BarangDTO {
    private String nama;
    private int jumlah;
    private int harga;  // atau double jika AVG bisa pecahan

    public BarangDTO(String nama, int jumlah, int harga) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.harga = harga;
    }

    // Getters
    public String getNama() {
        return nama;
    }

    public int getJumlah() {
        return jumlah;
    }

    public int getHarga() {
        return harga;
    }
}