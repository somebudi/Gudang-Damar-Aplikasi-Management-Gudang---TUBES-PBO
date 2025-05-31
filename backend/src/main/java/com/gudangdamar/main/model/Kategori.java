package com.gudangdamar.main.model;

import jakarta.persistence.*;

@Embeddable
public class Kategori {

    @Column(name = "ukuran")
    private int ukuran;

    @Column(name = "bentuk")
    private String bentuk;

    @Column(name = "ketebalan")
    private String ketebalan;

    @Column(name = "bahan")
    private String bahan;

    @Column(name = "guna_merek")
    private String merek;

    public Kategori() {}

    public Kategori(int ukuran, String bentuk, String ketebalan, String bahan, String merek) {
        this.ukuran = ukuran;
        this.bentuk = bentuk;
        this.ketebalan = ketebalan;
        this.bahan = bahan;
        this.merek = merek;
    }

    public int getUkuran() {
        return ukuran;
    }

    public void setUkuran(int ukuran) {
        this.ukuran = ukuran;
    }

    public String getBentuk() {
        return bentuk;
    }

    public void setBentuk(String bentuk) {
        this.bentuk = bentuk;
    }

    public String getKetebalan() {
        return ketebalan;
    }

    public void setKetebalan(String ketebalan) {
        this.ketebalan = ketebalan;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }
}
