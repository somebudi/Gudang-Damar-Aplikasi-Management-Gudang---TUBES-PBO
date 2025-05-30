package com.gudangdamar.main.model;

import jakarta.persistence.*;

@Embeddable
public class Kategori {

    @Column(name = "Ukuran")
    private int ukuran;

    @Column(name = "Bentuk")
    private String bentuk;

    @Column(name = "Ketebalan")
    private String ketebalan;

    @Column(name = "Bahan")
    private String bahan;

    @Column(name = "Guna_Merek")
    private String merek;

    public Kategori() {}

    public Kategori(int ukuran, String bentuk, String ketebalan, String bahan, String merek) {
        this.ukuran = ukuran;
        this.bentuk = bentuk;
        this.ketebalan = ketebalan;
        this.bahan = bahan;
        this.merek = merek;
    }

    // Getter & Setter
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
