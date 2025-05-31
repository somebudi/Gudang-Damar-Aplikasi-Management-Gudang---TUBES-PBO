package com.gudangdamar.main.controller;

import com.gudangdamar.main.repository.BarangRepository;
import com.gudangdamar.main.model.Servis;
import com.gudangdamar.main.model.Barang;
import com.gudangdamar.main.model.Kategori;
import com.gudangdamar.main.repository.ServisRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.gudangdamar.main.model.Pemesanan;
import com.gudangdamar.main.repository.PemesananRepository;
import java.util.List;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

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
        return "pages/login"; 
    }

    @GetMapping("/halamanGudangBeranda")
public String showGudangBeranda(Model model) {
    List<Barang> barangList = barangRepository.findAll();

    Map<String, Barang> map = new HashMap<>();

    for (Barang b : barangList) {
        String key = b.getNama() + "|" +
                     b.getKategori().getUkuran() + "|" +
                     b.getKategori().getKetebalan() + "|" +
                     b.getKategori().getBentuk() + "|" +
                     b.getKategori().getBahan() + "|" +
                     b.getKategori().getMerek();

        if (map.containsKey(key)) {
            Barang existing = map.get(key);

            int jumlahLama = existing.getHarga().getJumlah();
            int jumlahBaru = b.getHarga().getJumlah();

            existing.getHarga().setJumlah(jumlahLama + jumlahBaru);
            existing.getHarga().hitungTotalHarga();
        } else {
            b.getHarga().hitungTotalHarga();
            map.put(key, b);
        }
    }

    List<Barang> barangListGrouped = new ArrayList<>(map.values());

    model.addAttribute("barangList", barangListGrouped);

    return "pages/halamanGudangBeranda";
}

   @GetMapping("/halamanGudangDetail/{id}")
public String detailBarang(@PathVariable("id") int id, Model model) {
    Barang barangDetail = barangRepository.findById(id).orElse(null);
    if (barangDetail == null) {
        return "redirect:/error";
    }

    List<Barang> barangList = barangRepository.findAll();

    // Map dengan key gabungan dari nama, ukuran, ketebalan, bentuk, bahan, merek
    Map<String, Barang> map = new HashMap<>();

    for (Barang b : barangList) {
        // Buat key unik berdasarkan kombinasi atribut
        String key = b.getNama() + "|" +
                     b.getKategori().getUkuran() + "|" +
                     b.getKategori().getKetebalan() + "|" +
                     b.getKategori().getBentuk() + "|" +
                     b.getKategori().getBahan() + "|" +
                     b.getKategori().getMerek();

        if (map.containsKey(key)) {
            Barang existing = map.get(key);

            int jumlahLama = existing.getHarga().getJumlah();
            int jumlahBaru = b.getHarga().getJumlah();

            existing.getHarga().setJumlah(jumlahLama + jumlahBaru);
            existing.getHarga().hitungTotalHarga();
        } else {
            b.getHarga().hitungTotalHarga();
            map.put(key, b);
        }
    }

    List<Barang> barangListGrouped = new ArrayList<>(map.values());

    model.addAttribute("barang", barangDetail);          // detail per id
    model.addAttribute("barangList", barangListGrouped); // list gabungan berdasarkan kombinasi unik

    return "pages/halamanGudangDetail";
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
