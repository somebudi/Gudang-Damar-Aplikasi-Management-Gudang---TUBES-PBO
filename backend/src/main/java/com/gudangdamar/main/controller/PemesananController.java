package com.gudangdamar.main.controller;

import com.gudangdamar.main.model.Pemesanan;
import com.gudangdamar.main.repository.PemesananRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class PemesananController {

    @Autowired
    private PemesananRepository pemesananRepository;

    @PostMapping("pemesanan/tambah")
    public String tambahPemesanan(@ModelAttribute("pemesananBaru") Pemesanan pemesananBaru,
                                  RedirectAttributes redirectAttributes) {
        pemesananBaru.setTanggalPemesanan(LocalDateTime.now());
        pemesananRepository.save(pemesananBaru);
        redirectAttributes.addFlashAttribute("success", "Pemesanan berhasil ditambahkan!");
        return "redirect:/halamanGudangPesanan";
    }

    @PostMapping("pemesanan/selesai/{id}")
    public String selesaiPemesanan(@PathVariable Long id,
                                   RedirectAttributes redirectAttributes) {
        Pemesanan pemesanan = pemesananRepository.findById(id).orElse(null);
        if (pemesanan != null && pemesanan.getTanggalTerkirim() == null) {
            pemesanan.setTanggalTerkirim(LocalDateTime.now());
            pemesananRepository.save(pemesanan);
            redirectAttributes.addFlashAttribute("success", "Pemesanan ditandai selesai.");
        }
        return "redirect:/halamanGudangPesanan";
    }

    
    @PostMapping("pemesanan/delete/{id}")
    public String hapusPemesanan(@PathVariable Long id,
                                 RedirectAttributes redirectAttributes) {
        if (pemesananRepository.existsById(id)) {
            pemesananRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Pemesanan berhasil dihapus!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Data pemesanan tidak ditemukan.");
        }
        return "redirect:/halamanGudangPesanan";
    }

    @GetMapping("pemesanan/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Pemesanan pemesanan = pemesananRepository.findById(id).orElse(null);
        if (pemesanan == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Data pemesanan tidak ditemukan.");
            return "redirect:/halamanGudangPesanan";
        }

        model.addAttribute("pemesanan", pemesanan);
        return "pages/formEditPesanan";
    }

    @PostMapping("/pemesanan/update/{id}")
    public String updatePemesanan(@PathVariable Long id, @ModelAttribute("pemesanan") Pemesanan pemesananData,
                                  RedirectAttributes redirectAttributes) {
        Pemesanan existing = pemesananRepository.findById(id).orElse(null);
        if (existing == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Gagal memperbarui: data tidak ditemukan.");
            return "redirect:/halamanGudangPesanan";
        }

        existing.setCatatanPemesanan(pemesananData.getCatatanPemesanan());
        pemesananRepository.save(existing);

        redirectAttributes.addFlashAttribute("successMessage", "Data pemesanan berhasil diperbarui!");
        return "redirect:/halamanGudangPesanan";
    }
}
