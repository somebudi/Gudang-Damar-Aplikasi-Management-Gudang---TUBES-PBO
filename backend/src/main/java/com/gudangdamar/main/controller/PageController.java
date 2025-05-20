package com.gudangdamar.main.controller;

import com.gudangdamar.main.model.Servis;
import com.gudangdamar.main.repository.ServisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private ServisRepository servisRepository;

    @GetMapping("/")
    public String showLoginPage() {
        return "pages/login"; // templates/pages/login.html
    }

    @GetMapping("/halamanGudangBeranda")
    public String showGudangBeranda() {
        return "pages/halamanGudangBeranda";
    }

    @GetMapping("/halamanGudangDetail")
    public String showGudangDetail() {
        return "pages/halamanGudangDetail";
    }

    @GetMapping("/halamanGudangPesanan")
    public String showGudangPesanan() {
        return "pages/halamanGudangPesanan";
    }

    @GetMapping("/halamanGrafik")
    public String showGudangGrafik() {
        return "pages/halamanGrafik";
    }

    @GetMapping("/halamanGudangServis")
    public String showGudangServis(Model model) {
        List<Servis> servisList = servisRepository.findAll();
        model.addAttribute("servisList", servisList);
        return "pages/halamanGudangServis";
    }
}
