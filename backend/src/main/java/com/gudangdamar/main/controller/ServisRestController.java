package com.gudangdamar.main.controller;

import com.gudangdamar.main.model.Servis;
import com.gudangdamar.main.repository.ServisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servis") 
public class ServisRestController {

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
