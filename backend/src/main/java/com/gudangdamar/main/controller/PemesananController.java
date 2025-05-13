package com.gudangdamar.main.controller;

import com.gudangdamar.main.model.Pemesanan;
import com.gudangdamar.main.model.Barang;

import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController

public class PemesananController {

    @PostMapping("/pesanan")
    public Pemesanan buatPemesanan(@RequestBody Pemesanan request) {
        Barang barang = request.getBarang();
        Timestamp tanggalPemesanan = request.getTanggalPemesanan();
        Timestamp tanggalTerkirim = request.getTanggalTerkirim();
        String catatan = request.getCatatanPemesanan();

        Pemesanan pemesanan = new Pemesanan(barang, tanggalPemesanan, tanggalTerkirim, catatan);

        return pemesanan;
    }
}
