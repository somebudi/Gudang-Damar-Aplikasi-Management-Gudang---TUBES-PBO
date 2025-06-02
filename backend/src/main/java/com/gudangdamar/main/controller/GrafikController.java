package com.gudangdamar.main.controller;
import com.gudangdamar.main.model.User;
import com.gudangdamar.main.controller.RiwayatController.RiwayatItem;
import com.gudangdamar.main.model.Barang;
import com.gudangdamar.main.repository.BarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.HttpSession;

@Controller
public class GrafikController {

    @Autowired
    private BarangRepository barangRepository;

@GetMapping("/halamanGrafik")
public String halamanGrafik(HttpSession session, Model model) {
    User user = (User) session.getAttribute("loggedInUser");
    if (user == null || (!"kasir".equalsIgnoreCase(user.getRole()) && !"admin".equalsIgnoreCase(user.getRole()))) {
        return "redirect:/login";  
    }

    List<Barang> barangList = barangRepository.findAll();
    List<RiwayatItem> riwayatList = new ArrayList<>();

    for (Barang b : barangList) {
        String kategoriDesc = "Kategori tidak diketahui";
        if (b.getKategori() != null) {
            kategoriDesc = String.format("Ukuran: %d, Bentuk: %s, Ketebalan: %s, Bahan: %s, Merek: %s",
                b.getKategori().getUkuran(),
                Optional.ofNullable(b.getKategori().getBentuk()).orElse("-"),
                Optional.ofNullable(b.getKategori().getKetebalan()).orElse("-"),
                Optional.ofNullable(b.getKategori().getBahan()).orElse("-"),
                Optional.ofNullable(b.getKategori().getMerek()).orElse("-")
            );
        }

        String namaBarang = Optional.ofNullable(b.getNamaBarang()).orElse(b.getNama());
        String deskripsi = "Barang: " + namaBarang + " (" + kategoriDesc + ")";
        riwayatList.add(new RiwayatItem(deskripsi, b.getWaktuMasuk(), b.getWaktuKeluar()));
    }

    model.addAttribute("barangList", barangList);
    model.addAttribute("riwayatList", riwayatList);
    model.addAttribute("filter", "barang");

    return "pages/halamanGrafik";
}
}