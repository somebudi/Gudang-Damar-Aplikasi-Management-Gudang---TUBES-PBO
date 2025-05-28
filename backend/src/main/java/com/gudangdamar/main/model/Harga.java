package com.gudangdamar.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Harga {

    @Column(name = "Harga")
    private int harga;

    @Column(name = "Jumlah")
    private int jumlah;

    @Column(name = "Total")
    private int total;

    public Harga() {}

    public Harga(int harga, int jumlah) {
        this.harga = harga;
        this.jumlah = jumlah;
        
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
        
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
        
    }

    public int getTotal() {
        return total;
    }

    public void hitungTotalHarga() {
        this.total = harga * jumlah;
    }
}
