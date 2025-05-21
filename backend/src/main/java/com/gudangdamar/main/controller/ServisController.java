package com.gudangdamar.main.controller;

import com.gudangdamar.main.model.Servis;
import com.gudangdamar.main.repository.ServisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servis") // Prefix untuk endpoint REST API
public class ServisController {

    @Autowired
    private ServisRepository servisRepository;

   
    @PostMapping
    public Servis tambahServis(@RequestBody Servis request) {
        return servisRepository.save(request);
    }

 
    @GetMapping
    public List<Servis> getSemuaServis() {
        return servisRepository.findAll();
    }

  
    @GetMapping("/{id}")
    public Servis getServisById(@PathVariable Long id) {
        return servisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servis dengan ID " + id + " tidak ditemukan"));
    }


    @PutMapping("/{id}")
    public Servis updateServis(@PathVariable Long id, @RequestBody Servis request) {
        return servisRepository.findById(id)
                .map(servis -> {
                    servis.setTanggalMulaiServis(request.getTanggalMulaiServis());
                    servis.setTanggalSelesaiServis(request.getTanggalSelesaiServis());
                    servis.setCatatanPemesanan(request.getCatatanPemesanan());
                    return servisRepository.save(servis);
                })
                .orElseThrow(() -> new RuntimeException("Servis dengan ID " + id + " tidak ditemukan"));
    }

   
    @DeleteMapping("/{id}")
    public String hapusServis(@PathVariable Long id) {
        if (servisRepository.existsById(id)) {
            servisRepository.deleteById(id);
            return "Servis dengan ID " + id + " berhasil dihapus.";
        } else {
            return "Servis dengan ID " + id + " tidak ditemukan.";
        }
    }
}
