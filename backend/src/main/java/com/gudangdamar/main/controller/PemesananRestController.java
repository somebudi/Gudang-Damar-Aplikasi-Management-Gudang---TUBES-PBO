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

    @PostMapping("/pesanan")
    public Pemesanan buatPemesanan(@RequestBody Pemesanan request) {
        return pemesananRepository.save(request);
    }


    @GetMapping
    public List<Pemesanan> getSemuaPesan() {
        return pemesananRepository.findAll();
    }


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
