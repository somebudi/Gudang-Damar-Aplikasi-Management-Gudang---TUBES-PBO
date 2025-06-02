package com.gudangdamar.main.controller;
import com.gudangdamar.main.model.User;
import com.gudangdamar.main.controller.RiwayatController.RiwayatItem;
import com.gudangdamar.main.model.Barang;
import com.gudangdamar.main.model.Kategori;
import com.gudangdamar.main.model.Servis;
import com.gudangdamar.main.model.Pemesanan;
import com.gudangdamar.main.repository.BarangRepository;
import com.gudangdamar.main.repository.ServisRepository;
import com.gudangdamar.main.repository.PemesananRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.*;

@Controller
public class RiwayatController {

    private final BarangRepository barangRepository;
    private final ServisRepository servisRepository;
    private final PemesananRepository pemesananRepository;

    public RiwayatController(BarangRepository barangRepository,
                             ServisRepository servisRepository,
                             PemesananRepository pemesananRepository) {
        this.barangRepository = barangRepository;
        this.servisRepository = servisRepository;
        this.pemesananRepository = pemesananRepository;
    }

   @GetMapping("/riwayat")
public String riwayat(@RequestParam(defaultValue = "all") String filter, Model model, HttpSession session) {
    User user = (User) session.getAttribute("loggedInUser");
    if (user == null || (!"kasir".equalsIgnoreCase(user.getRole()) && !"admin".equalsIgnoreCase(user.getRole()))) {
        return "redirect:/login"; // atau halaman 403
    
    }

    List<RiwayatItem> riwayatList = new ArrayList<>();

   if (filter.equals("all") || filter.equals("barang")) {
    List<Barang> barangList = barangRepository.findAll();
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


    if (filter.equals("all") || filter.equals("servis")) {
        List<Servis> servisList = servisRepository.findAll();
        for (Servis s : servisList) {
            String deskripsi = "Servis: " + Optional.ofNullable(s.getCatatanPemesanan()).orElse("Tanpa catatan");
            riwayatList.add(new RiwayatItem(deskripsi, s.getTanggalMulaiServis(), s.getTanggalSelesaiServis()));
        }
    }else {
 
    List<Barang> barangList = barangRepository.findAll();
    model.addAttribute("barangList", barangList);
}

    if (filter.equals("all") || filter.equals("pemesanan")) {
        List<Pemesanan> pemesananList = pemesananRepository.findAll();
        for (Pemesanan p : pemesananList) {
            String deskripsi = "Pemesanan: " + Optional.ofNullable(p.getCatatanPemesanan()).orElse("Tanpa catatan");
            riwayatList.add(new RiwayatItem(deskripsi, p.getTanggalPemesanan(), p.getTanggalTerkirim()));
        }
    }else {

    List<Barang> barangList = barangRepository.findAll();
    model.addAttribute("barangList", barangList);
}

    List<RiwayatItem> withDate = new ArrayList<>();
    List<RiwayatItem> withoutDate = new ArrayList<>();

    for (RiwayatItem item : riwayatList) {
        if (item.getWaktuMulai() != null) {
            withDate.add(item);
        } else {
            withoutDate.add(item);
        }
    }

    withDate.sort(Comparator.comparing(RiwayatItem::getWaktuMulai, Comparator.reverseOrder()));
    withDate.addAll(withoutDate);

    model.addAttribute("riwayatList", withDate);
    model.addAttribute("filter", filter);

    return "pages/halamanGrafik";
}




    public static class RiwayatItem {
        private final String deskripsi;
        private final Date waktuMulai;
        private final Date waktuSelesai;

        public RiwayatItem(String deskripsi, LocalDateTime mulai, LocalDateTime selesai) {
            this.deskripsi = deskripsi;
            this.waktuMulai = mulai != null ? java.sql.Timestamp.valueOf(mulai) : null;
            this.waktuSelesai = selesai != null ? java.sql.Timestamp.valueOf(selesai) : null;
        }

        public String getDeskripsi() {
            return deskripsi;
        }

        public Date getWaktuMulai() {
            return waktuMulai;
        }

        public Date getWaktuSelesai() {
            return waktuSelesai;
        }
    }
}
