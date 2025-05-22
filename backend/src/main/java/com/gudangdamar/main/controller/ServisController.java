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

import java.time.LocalDateTime;

@Controller
public class ServisController {

    @Autowired
    private ServisRepository servisRepository;

    @PostMapping("servis/tambah")
    public String tambahServis(@ModelAttribute("servisBaru") Servis servisBaru,
                               RedirectAttributes redirectAttributes) {
        servisBaru.setTanggalMulaiServis(LocalDateTime.now());
        servisRepository.save(servisBaru);
        redirectAttributes.addFlashAttribute("success", "Servis berhasil ditambahkan!");
        return "redirect:/halamanGudangServis";
    }

    @PostMapping("servis/selesai/{id}")
    public String selesaiServis(@PathVariable Long id,
                                RedirectAttributes redirectAttributes) {
        Servis servis = servisRepository.findById(id).orElse(null);
        if (servis != null && servis.getTanggalSelesaiServis() == null) {
            servis.setTanggalSelesaiServis(LocalDateTime.now());
            servisRepository.save(servis);
            redirectAttributes.addFlashAttribute("success", "Servis ditandai selesai.");
        }
        return "redirect:/halamanGudangServis";
    }

    

    @PostMapping("servis/delete/{id}")
    public String hapusPemesanan(@PathVariable Long id,
                                 RedirectAttributes redirectAttributes) {
        if (servisRepository.existsById(id)) {
            servisRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Servis berhasil dihapus!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Data servis tidak ditemukan.");
        }
        return "redirect:/halamanGudangServis";
    }

    @GetMapping("servis/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Servis servis = servisRepository.findById(id).orElse(null);
        if (servis == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Data servis tidak ditemukan.");
            return "redirect:/halamanGudangServis";
        }

        model.addAttribute("servis", servis);
        return "pages/formEditServis";
    }

    @PostMapping("/servis/update/{id}")
    public String updateServis(@PathVariable Long id, @ModelAttribute("servis") Servis servisData,
                               RedirectAttributes redirectAttributes) {
        Servis existing = servisRepository.findById(id).orElse(null);
        if (existing == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Gagal memperbarui: data tidak ditemukan.");
            return "redirect:/halamanGudangServis";
        }

        existing.setCatatanPemesanan(servisData.getCatatanPemesanan());
        servisRepository.save(existing);

        redirectAttributes.addFlashAttribute("successMessage", "Data servis berhasil diperbarui!");
        return "redirect:/halamanGudangServis";
    }
}
