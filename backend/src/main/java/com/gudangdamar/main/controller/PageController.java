package com.gudangdamar.main.controller;

import com.gudangdamar.main.model.Servis;
import com.gudangdamar.main.repository.ServisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("servisBaru", new Servis());
        return "pages/halamanGudangServis";
    }

    @PostMapping
    public String buatPemesanan(@ModelAttribute("servisBaru") Servis request,
                                RedirectAttributes redirectAttributes) {
        servisRepository.save(request);
        redirectAttributes.addFlashAttribute("successMessage", "Servis berhasil ditambahkan!");
        return "redirect:/halamanGudangServis";
    }

    @PostMapping("/delete/{id}")
    public String hapusPemesanan(@PathVariable Long id,
                                 RedirectAttributes redirectAttributes) {
        servisRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Servis berhasil dihapus!");
        return "redirect:/halamanGudangServis";
    }
}
