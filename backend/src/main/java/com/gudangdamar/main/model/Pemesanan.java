package com.gudangdamar.main.model;

import java.sql.Timestamp;

public class Pemesanan {
    
    private Barang barang; 
    private Timestamp tanggalPemesanan;
    private Timestamp tanggalTerkirim;
    private String catatanPemesanan;
    public Pemesanan( Barang barang, Timestamp tanggalPemesanan,Timestamp tanggalTerkirim,String catatanPemesanan) {
       
        this.barang = barang;
        this.tanggalPemesanan = tanggalPemesanan;
        this.tanggalTerkirim = tanggalTerkirim;
        this.catatanPemesanan = catatanPemesanan;
        int idBarangBaru = Integer.parseInt(String.valueOf(barang.getIdBarang()) + "25");
        this.barang.setIdBarang(idBarangBaru);
    }

    public Barang getBarang() {
        return barang;
    }

    public String getCatatanPemesanan() {
        return catatanPemesanan;
    }

   

    public Timestamp getTanggalPemesanan() {
        return tanggalPemesanan;
    }

    public Timestamp getTanggalTerkirim() {
        return tanggalTerkirim;
    }

    public void setTanggalTerkirim(Timestamp tanggalTerkirim) {
        this.tanggalTerkirim = tanggalTerkirim;
    }
    public void setTanggalPemesanan(Timestamp tanggalPemesanan) {
        this.tanggalPemesanan = tanggalPemesanan;
    }
    public void setCatatanPemesanan(String catatanPemesanan) {
        this.catatanPemesanan = catatanPemesanan;
    }
   
}
