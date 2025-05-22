package com.gudangdamar.main.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pesanan")
public class Pemesanan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "tanggal_pemesanan")
    private LocalDateTime tanggalPemesanan;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "tanggal_terkirim")
    private LocalDateTime tanggalTerkirim;

    @Column(name = "catatan_pemesanan", length = 255)
    private String catatanPemesanan;

    public Pemesanan() {}

    public Pemesanan(LocalDateTime tanggalPemesanan, LocalDateTime tanggalTerkirim, String catatanPemesanan) {
        this.tanggalPemesanan = tanggalPemesanan;
        this.tanggalTerkirim = tanggalTerkirim;
        this.catatanPemesanan = catatanPemesanan;
    }
   
    public Long getId() {
    return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatatanPemesanan() {
        return catatanPemesanan;
    }

   

    public LocalDateTime getTanggalPemesanan() {
        return tanggalPemesanan;
    }

    public LocalDateTime getTanggalTerkirim() {
        return tanggalTerkirim;
    }

    public void setTanggalTerkirim(LocalDateTime tanggalTerkirim) {
        this.tanggalTerkirim = tanggalTerkirim;
    }
    public void setTanggalPemesanan(LocalDateTime tanggalPemesanan) {
        this.tanggalPemesanan = tanggalPemesanan;
    }
    public void setCatatanPemesanan(String catatanPemesanan) {
        this.catatanPemesanan = catatanPemesanan;
    }
   
}
