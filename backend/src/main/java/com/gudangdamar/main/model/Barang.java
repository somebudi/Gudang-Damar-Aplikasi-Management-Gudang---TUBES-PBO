package com.gudangdamar.main.model;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="barang")
public class Barang{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBarang;
    private String namaBarang;
    //private Harga hargaBarang;
    private int stok;
    @Embedded
    private Kategori kategori;
    private Timestamp waktuMasuk;
    private Timestamp waktuKeluar;
    private Timestamp waktuPendataan;
    private String namaSupplier;

    public Barang(int idBarang,String namaBarang, int stok, Kategori kategori, Timestamp waktuMasuk, Timestamp waktuKeluar, Timestamp waktuPendataan, String namaSupplier){
        this.idBarang=idBarang;
        this.namaBarang=namaBarang;
        //this.hargaBarang=hargaBarang;
        this.stok=stok;
        this.kategori = kategori;
        this.waktuMasuk = waktuMasuk;
        this.waktuKeluar = waktuKeluar;
        this.waktuPendataan = waktuPendataan;
        this.namaSupplier = namaSupplier;
    }

    public void setIdBarang(int idBarang){
        this.idBarang=idBarang;
    }

    public int getIdBarang(){
        return idBarang;
    }

    public void setNamaBarang(String namaBarang){
        this.namaBarang=namaBarang;
    }

    public String getNamaBarang(){
        return namaBarang;
    }

    //public void setHargaBarang(Harga hargaBarang){
        //this.hargaBarang=hargaBarang;
    //}

    //public Harga getHargaBarang(){
        //return hargaBarang;
    //}

    public void setStok(int stok){
        this.stok=stok;
    }

    public int getStok(){
        return stok;
    }

    public void setKategori(Kategori kategori){
        this.kategori=kategori;
    }

    public Kategori getKategori(){
        return kategori;
    }

    public void setWaktuMasuk(Timestamp waktuMasuk){
        this.waktuMasuk=waktuMasuk;
    }

    public Timestamp getWaktuMasuk(){
        return waktuMasuk;
    }

    public void setWaktuKeluar(Timestamp waktuKeluar){
        this.waktuKeluar=waktuKeluar;
    }

    public Timestamp getWaktuKeluar(){
        return waktuKeluar;
    }

    public void setWaktuPendataan(Timestamp waktuPendataan){
        this.waktuPendataan=waktuPendataan;
    }

    public Timestamp getWaktuPendataan(){
        return waktuPendataan;
    }

    public void setNamaSupplier(String namaSupplier){
        this.namaSupplier=namaSupplier;
    }

    public String getNamaSupplier(){
        return namaSupplier;
    }
}