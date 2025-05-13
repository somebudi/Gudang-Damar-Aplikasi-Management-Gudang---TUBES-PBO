package com.gudangdamar.main.controller;

import com.gudangdamar.main.model.Servis;
import com.gudangdamar.main.model.Barang;

import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController

public class ServisController {

    @PostMapping("/servis")
    public Servis buatPemesanan(@RequestBody Servis request) {
        Barang barang = request.getBarang();
        Timestamp tanggalMulaiServis = request.gettanggalMulaiServis();
        Timestamp tanggalSelesaiServis = request.gettanggalSelesaiServis();
        String catatan = request.getCatatanPemesanan();

        // Membuat objek pemesanan
        Servis servis = new Servis(barang, tanggalMulaiServis, tanggalSelesaiServis, catatan);

        // Jika kamu ingin menyimpan ke database, panggil repository di sini
        // misalnya: pemesananRepository.save(pemesanan);

        return servis;
    }
    
}
