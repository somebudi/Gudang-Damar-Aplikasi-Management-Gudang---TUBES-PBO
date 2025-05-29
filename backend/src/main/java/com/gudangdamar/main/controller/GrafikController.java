package com.gudangdamar.main.controller;

import com.gudangdamar.main.model.Barang;
import com.gudangdamar.main.repository.BarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GrafikController {

    @Autowired
    private BarangRepository barangRepo;

    @GetMapping("/halamanGrafik")
    public String showGudangGrafik(Model model) {
        List<Barang> barangList = barangRepo.findAll();
        model.addAttribute("barangList", barangList);
        return "pages/halamanGrafik";
    }
}