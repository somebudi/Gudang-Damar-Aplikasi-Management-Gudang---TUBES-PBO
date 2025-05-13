package com.gudangdamar.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

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
}

