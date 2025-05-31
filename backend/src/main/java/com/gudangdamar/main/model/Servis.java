package com.gudangdamar.main.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "servis")
public class Servis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "tanggal_mulai_servis")
    private LocalDateTime tanggalMulaiServis;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "tanggal_selesai_servis")
    private LocalDateTime tanggalSelesaiServis;

    @Column(name = "catatan_pemesanan", length = 255)
    private String catatanPemesanan;

    public Servis() {}

    public Servis(LocalDateTime tanggalMulaiServis, LocalDateTime tanggalSelesaiServis, String catatanPemesanan) {
        this.tanggalMulaiServis = tanggalMulaiServis;
        this.tanggalSelesaiServis = tanggalSelesaiServis;
        this.catatanPemesanan = catatanPemesanan;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTanggalMulaiServis() {
        return tanggalMulaiServis;
    }

    public void setTanggalMulaiServis(LocalDateTime tanggalMulaiServis) {
        this.tanggalMulaiServis = tanggalMulaiServis;
    }

    public LocalDateTime getTanggalSelesaiServis() {
        return tanggalSelesaiServis;
    }

    public void setTanggalSelesaiServis(LocalDateTime tanggalSelesaiServis) {
        this.tanggalSelesaiServis = tanggalSelesaiServis;
    }

    public String getCatatanPemesanan() {
        return catatanPemesanan;
    }

    public void setCatatanPemesanan(String catatanPemesanan) {
        this.catatanPemesanan = catatanPemesanan;
    }
}
