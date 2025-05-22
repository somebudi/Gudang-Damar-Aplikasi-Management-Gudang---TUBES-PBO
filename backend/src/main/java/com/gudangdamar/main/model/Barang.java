package com.gudangdamar.main.model;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="barang")
public class Barang{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "ID_Barang")
    private int idBarang;
    @Column(name = "nama")
    private String nama;
    //private Harga hargaBarang;
    @Column(name = "Harga")
    private int harga;
    @Column(name = "jumlah")
    private int jumlah;
    @Column(name = "Total")
    private int total;
    @Embedded
    private Kategori kategori;
    // private Timestamp waktuMasuk;
    // private Timestamp waktuKeluar;
    // private Timestamp waktuPendataan;
    // private String namaSupplier;
    public Barang() {}

    public Barang(int idBarang,String nama,int harga, int jumlah, Kategori kategori,int total){
        this.idBarang=idBarang;
        this.nama=nama;
        this.harga=harga;
        this.jumlah=jumlah;
       
        this.total=total;
        this.kategori = kategori;
        // this.waktuMasuk = waktuMasuk;
        // this.waktuKeluar = waktuKeluar;
        // this.waktuPendataan = waktuPendataan;
        // this.namaSupplier = namaSupplier;
    }

    public void setIdBarang(int idBarang){
        this.idBarang=idBarang;
    }

    public int getIdBarang(){
        return idBarang;
    }

    public void setNama(String nama){
        this.nama=nama;
    }

    public String getNama(){
        return nama;
    }

    public void setHargaBarang(int harga){
        this.harga=harga;
    }

    public int getHarga(){
        return harga;
    }

    public void setJumlah(int jumlah){
        this.jumlah=jumlah;
    }

    public int getJumlah() {
        return jumlah;
    }


    public void setKategori(Kategori kategori){
        this.kategori=kategori;
    }

    public Kategori getKategori(){
        return kategori;
    }

    // public void setWaktuMasuk(Timestamp waktuMasuk){
    //     this.waktuMasuk=waktuMasuk;
    // }

    // public Timestamp getWaktuMasuk(){
    //     return waktuMasuk;
    // }

    // public void setWaktuKeluar(Timestamp waktuKeluar){
    //     this.waktuKeluar=waktuKeluar;
    // }

    // public Timestamp getWaktuKeluar(){
    //     return waktuKeluar;
    // }

    // public void setWaktuPendataan(Timestamp waktuPendataan){
    //     this.waktuPendataan=waktuPendataan;
    // }

    // public Timestamp getWaktuPendataan(){
    //     return waktuPendataan;
    // }

    // public void setNamaSupplier(String namaSupplier){
    //     this.namaSupplier=namaSupplier;
    // }

    // public String getNamaSupplier(){
    //     return namaSupplier;
    // }
}