package com.gudangdamar.main.controller;
import com.gudangdamar.main.model.User;
import com.gudangdamar.main.controller.RiwayatController.RiwayatItem;
import com.gudangdamar.main.model.Barang;
import com.gudangdamar.main.model.Kategori;
import com.gudangdamar.main.model.Pemesanan;
import com.gudangdamar.main.model.Servis;
import java.util.Collections;
import com.gudangdamar.main.repository.PemesananRepository;
import com.gudangdamar.main.repository.ServisRepository;
import com.gudangdamar.main.repository.BarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.HttpSession;

@Controller
public class GrafikController {

@Autowired
private BarangRepository barangRepository;

@Autowired
private ServisRepository servisRepository;

@Autowired
private PemesananRepository pemesananRepository;

@GetMapping("/halamanGrafik")
public String halamanGrafik(@RequestParam(defaultValue = "barang") String filter, HttpSession session, Model model) {
    User user = (User) session.getAttribute("loggedInUser");
    if (user == null || (!"kasir".equalsIgnoreCase(user.getRole()) && !"admin".equalsIgnoreCase(user.getRole()))) {
        return "redirect:/login";  
    }

    List<?> grafikList = new ArrayList<>();
    List<RiwayatItem> riwayatList = new ArrayList<>();

    if (filter.equals("barang")) {
        List<Barang> barangList = barangRepository.findAll();   
        grafikList = barangList;
        for (Barang b : barangList) {
        String kategoriDesc = "Kategori tidak diketahui";
        if (b.getKategori() != null) {
            Kategori k = b.getKategori();
            kategoriDesc = String.format("Ukuran: %d, Bentuk: %s, Bahan: %s",
                k.getUkuran(),
                k.getBentuk() != null ? k.getBentuk() : "-",
                k.getBahan() != null ? k.getBahan() : "-"
            );
        }

        String namaBarang = Optional.ofNullable(b.getNamaBarang()).orElse(b.getNama());
        String deskripsi = "Barang: " + namaBarang + " (" + kategoriDesc + ")";
        riwayatList.add(new RiwayatItem(deskripsi, b.getWaktuMasuk(), b.getWaktuKeluar()));
    }
        model.addAttribute("barangList", barangList);
    } else {
     model.addAttribute("barangList", Collections.emptyList());
    }
  
 
    if (filter.equals("servis")) {
        List<Servis> servisList = servisRepository.findAll();
        grafikList = servisList;
       for (Servis s : servisList) {
            String deskripsi = "Servis: " + Optional.ofNullable(s.getCatatanPemesanan()).orElse("Tanpa catatan");
            riwayatList.add(new RiwayatItem(deskripsi, s.getTanggalMulaiServis(), s.getTanggalSelesaiServis()));
        }
    }else {
 
    List<Barang> barangList = barangRepository.findAll();
    model.addAttribute("barangList", barangList);
    }     
    
    if (filter.equals("pemesanan")) {
        List<Pemesanan> pemesananList = pemesananRepository.findAll();
        grafikList = pemesananList;    
        grafikList = pemesananList;
        for (Pemesanan p : pemesananList) {
            String deskripsi = "Pemesanan: " + Optional.ofNullable(p.getCatatanPemesanan()).orElse("Tanpa catatan");
            riwayatList.add(new RiwayatItem(deskripsi, p.getTanggalPemesanan(), p.getTanggalTerkirim()));
        }
    }else {

    List<Barang> barangList = barangRepository.findAll();
    model.addAttribute("barangList", barangList);
}

    model.addAttribute("riwayatList", riwayatList);
    model.addAttribute("grafikList", grafikList);
    model.addAttribute("filter", filter);

    return "pages/halamanGrafik";
}
}