package com.gudangdamar.main.controller;
import com.gudangdamar.main.model.Servis;
import com.gudangdamar.main.repository.ServisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servis")
public class ServisController {

    @Autowired
    private ServisRepository servisRepository;
    
    @PostMapping
    public Servis buatPemesanan(@RequestBody Servis request) {
        return servisRepository.save(request);
    }

    @GetMapping("/list")
    public List<Servis> listServis() {
        return servisRepository.findAll();
    }
}
