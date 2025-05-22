package com.gudangdamar.main.controller;

import com.gudangdamar.main.model.Pemesanan;
import com.gudangdamar.main.repository.PemesananRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pemesanan")
public class PemesananRestController {

    @Autowired
    private PemesananRepository pemesananRepository;

    // Buat pemesanan baru
    @PostMapping("/pesanan")
    public Pemesanan buatPemesanan(@RequestBody Pemesanan request) {
        return pemesananRepository.save(request);
    }

    // Ambil semua pemesanan
    @GetMapping
    public List<Pemesanan> getSemuaPesan() {
        return pemesananRepository.findAll();
    }

    // Ambil satu pemesanan berdasarkan ID
    @GetMapping("/{id}")
    public Pemesanan getPesanById(@PathVariable Long id) {
        return pemesananRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pemesanan dengan ID " + id + " tidak ditemukan"));
    }

    // Update data pemesanan
    @PutMapping("/{id}")
    public Pemesanan updatePesanan(@PathVariable Long id, @RequestBody Pemesanan request) {
        return pemesananRepository.findById(id)
                .map(pemesanan -> {
                    pemesanan.setTanggalPemesanan(request.getTanggalPemesanan());
                    pemesanan.setTanggalTerkirim(request.getTanggalTerkirim());
                    pemesanan.setCatatanPemesanan(request.getCatatanPemesanan());
                    return pemesananRepository.save(pemesanan);
                })
                .orElseThrow(() -> new RuntimeException("Pemesanan dengan ID " + id + " tidak ditemukan"));
    }

    // Hapus pemesanan
    @DeleteMapping("/{id}")
    public String hapusPesanan(@PathVariable Long id) {
        if (pemesananRepository.existsById(id)) {
            pemesananRepository.deleteById(id);
            return "Pemesanan dengan ID " + id + " berhasil dihapus.";
        } else {
            return "Pemesanan dengan ID " + id + " tidak ditemukan.";
        }
    }
}
