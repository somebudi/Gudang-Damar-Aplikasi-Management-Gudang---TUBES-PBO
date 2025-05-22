package com.gudangdamar.main.controller;

import com.gudangdamar.main.model.Servis;
import com.gudangdamar.main.repository.ServisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.gudangdamar.main.model.Pemesanan;
import com.gudangdamar.main.repository.PemesananRepository;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private ServisRepository servisRepository;
    @Autowired
    private PemesananRepository pemesananRepository;
    @GetMapping("/")
    public String showLoginPage() {
        return "pages/login"; // templates/pages/login.html
    }

    @GetMapping("/halamanGudangBeranda")
    public String showGudangBeranda(Model model) {
        List<Servis> servisList = servisRepository.findAll();
        model.addAttribute("servisList", servisList);
        return "pages/halamanGudangBeranda";
    }

    @GetMapping("/halamanGudangDetail")
    public String showGudangDetail() {
        return "pages/halamanGudangDetail";
    }


    @GetMapping("/halamanGrafik")
    public String showGudangGrafik() {
        return "pages/halamanGrafik";
    }

    @GetMapping("/halamanGudangServis")
public String showGudangServis(Model model) {
    model.addAttribute("servisBaru", new Servis());
    model.addAttribute("servisList", servisRepository.findAll());
    return "pages/halamanGudangServis";
}

@GetMapping("/halamanGudangPesanan")
public String showGudangPemesanan(Model model) {
    model.addAttribute("pemesananBaru", new Pemesanan());
    model.addAttribute("pemesananList", pemesananRepository.findAll());
    return "pages/halamanGudangPesanan";
}

}
