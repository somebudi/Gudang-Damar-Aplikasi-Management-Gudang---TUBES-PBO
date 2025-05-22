package com.gudangdamar.main.controller;

import com.gudangdamar.main.repository.BarangRepository;
import com.gudangdamar.main.model.Servis;
import com.gudangdamar.main.model.Barang;
import com.gudangdamar.main.model.Pemesanan;
import com.gudangdamar.main.repository.ServisRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gudangdamar.main.repository.PemesananRepository;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private ServisRepository servisRepository;

    @Autowired
    private BarangRepository barangRepository;

    @Autowired
    private PemesananRepository pemesananRepository;

    @GetMapping("/")
    public String showLoginPage() {
        return "pages/login"; // templates/pages/login.html
    }

    @GetMapping("/halamanGudangBeranda")
    public String showGudangBeranda(Model model) {
    List<Barang> barangList = barangRepository.findAll(); // ✅ Benar
    model.addAttribute("barangList", barangList);
        return "pages/halamanGudangBeranda";
}

    @GetMapping("/halamanGudangDetail/{id}")
    public String detailBarang(@PathVariable("id") int id, Model model) {
    Barang barang = barangRepository.findById(id).orElse(null);
    if (barang == null) {
        return "redirect:/error";
    }

    model.addAttribute("barang", barang);
    return "pages/halamanGudangDetail";
}
    @GetMapping("/halamanGrafik")
    public String showGudangGrafik() {
        return "pages/halamanGrafik";
    }

    @GetMapping("/halamanGudangServis")
    public String showGudangServis(Model model) {
        List<Servis> servisList = servisRepository.findAll();
        model.addAttribute("servisList", servisList);
        model.addAttribute("servisBaru", new Servis());
        return "pages/halamanGudangServis";
    }
    @GetMapping("/halamanGudangPesanan")
    public String showGudangPemesanan(Model model) {
        List<Pemesanan> pemesananList = pemesananRepository.findAll();
        model.addAttribute("pemesananList", pemesananList);
         model.addAttribute("pemesananBaru", new Pemesanan());
        return "pages/halamanGudangPesanan";
    }
    
}
