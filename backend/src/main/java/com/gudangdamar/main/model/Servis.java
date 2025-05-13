package com.gudangdamar.main.model;

import java.sql.Timestamp;

public class Servis {
    
    private Barang barang; // ← relasi: Pemesanan memiliki Barang
    private Timestamp tanggalMulaiServis;
    private Timestamp tanggalSelesaiServis;
    private String catatanPemesanan;
    public Servis( Barang barang, Timestamp tanggalMulaiServis,Timestamp tanggalSelesaiServis,String catatanPemesanan) {
       
        this.barang = barang;
        this.tanggalMulaiServis = tanggalMulaiServis;
        this.tanggalSelesaiServis = tanggalSelesaiServis;
        this.catatanPemesanan = catatanPemesanan;
        int idBarangBaru = Integer.parseInt(String.valueOf(barang.getIdBarang()) + "50");
        this.barang.setIdBarang(idBarangBaru);
    }

    public Barang getBarang() {
        return barang;
    }

    public String getCatatanPemesanan() {
        return catatanPemesanan;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public Timestamp gettanggalMulaiServis() {
        return tanggalMulaiServis;
    }

    public Timestamp gettanggalSelesaiServis() {
        return tanggalSelesaiServis;
    }

    public void settanggalSelesaiServis(Timestamp tanggalTerkirim) {
        this.tanggalSelesaiServis = tanggalTerkirim;
    }
    public void setTanggalPemesanan(Timestamp tanggalMulaiServis) {
        this.tanggalMulaiServis = tanggalMulaiServis;
    }
    public void setCatatanPemesanan(String catatanPemesanan) {
        this.catatanPemesanan = catatanPemesanan;
    }
    
}
