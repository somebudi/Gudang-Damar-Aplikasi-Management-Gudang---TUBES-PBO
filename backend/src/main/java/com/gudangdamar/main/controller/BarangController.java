package com.gudangdamar.main.controller;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gudangdamar.main.model.Barang;
import com.gudangdamar.main.model.Harga;
import com.gudangdamar.main.model.Kategori;
import com.gudangdamar.main.model.User;
import com.gudangdamar.main.repository.BarangRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class BarangController {

    @Autowired
    private BarangRepository barangRepository;

    @GetMapping("barang/tambah")
    public String formTambahBarang(Model model) {
        Barang barang = new Barang();
        barang.setHarga(new Harga());
        barang.setKategori(new Kategori());
        model.addAttribute("barang", barang);
        return "pages/tambahBarang";
    }

    @PostMapping("/barang/save")
public String saveBarang(@ModelAttribute Barang barang, RedirectAttributes redirectAttributes) {
    if (barang.getNama() == null || barang.getNama().isBlank()) {
        redirectAttributes.addFlashAttribute("error", "Nama barang tidak boleh kosong");
        return "redirect:/barang/tambah";
    }

    if (barang.getHarga() == null) {
        barang.setHarga(new Harga());
    }

    if (barang.getKategori() == null) {
        barang.setKategori(new Kategori());
    }

    if (barang.getHarga().getHarga() <= 0 || barang.getHarga().getJumlah() <= 0) {
        redirectAttributes.addFlashAttribute("error", "Harga dan jumlah harus lebih dari 0");
        return "redirect:/barang/tambah";
    }

    barang.getHarga().hitungTotalHarga();

    if (barang.getWaktuMasuk() == null) {
        barang.setWaktuMasuk(LocalDateTime.now());
    }

    barang.setWaktuPendataan(LocalDateTime.now());

    Barang saved = barangRepository.save(barang);
    redirectAttributes.addFlashAttribute("success", "Barang berhasil disimpan dengan ID: " + saved.getIdBarang());
    return "redirect:/halamanGudangDetail/" + saved.getIdBarang();
}
 @PostMapping("barang/delete/{id}")
    public String deleteBarang(@PathVariable int id, RedirectAttributes redirectAttributes) {
        if (barangRepository.existsById(id)) {
            barangRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Barang berhasil dihapus.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Barang tidak ditemukan.");
        }
        return "redirect:/halamanGudangBeranda";
    }

    @GetMapping("barang/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        Barang barang = barangRepository.findById(id).orElse(null);
        if (barang == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Data barang tidak ditemukan.");
            return "redirect:/halamanGudangBeranda";
        }
        model.addAttribute("barang", barang);
        return "pages/editBarang";
    }

    @PostMapping("barang/update/{id}")
    public String updatedBarang(@PathVariable int id, @ModelAttribute Barang barangForm, RedirectAttributes redirectAttributes) {
        Optional<Barang> optionalBarang = barangRepository.findById(id);
        if (optionalBarang.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Data barang tidak ditemukan.");
            return "redirect:/halamanGudangBeranda";
        }

        Barang barang = optionalBarang.get();

        barang.setNama(barangForm.getNama());

        if (barang.getKategori() == null) {
            barang.setKategori(new Kategori());
        }
        barang.getKategori().setUkuran(barangForm.getKategori().getUkuran());
        barang.getKategori().setKetebalan(barangForm.getKategori().getKetebalan());
        barang.getKategori().setBahan(barangForm.getKategori().getBahan());

        if (barang.getHarga() == null) {
            barang.setHarga(new Harga());
        }
        barang.getHarga().setHarga(barangForm.getHarga().getHarga());
        barang.getHarga().setJumlah(barangForm.getHarga().getJumlah());
        barang.getHarga().hitungTotalHarga();

        barangRepository.save(barang);

        redirectAttributes.addFlashAttribute("success", "Data barang berhasil diperbarui.");
        return "redirect:/halamanGudangDetail/" + barang.getIdBarang();
    }
    @PostMapping("/barang/updateJumlah/{id}")
    public String updateJumlah(@PathVariable int id, @RequestParam int jumlah, RedirectAttributes redirectAttributes) {
        Optional<Barang> optionalBarang = barangRepository.findById(id);
        if (optionalBarang.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Data barang tidak ditemukan.");
            return "redirect:/halamanGudangBeranda";
        }

        Barang barang = optionalBarang.get();
        if (barang.getHarga() == null) {
            barang.setHarga(new Harga());
        }

        barang.getHarga().setJumlah(jumlah);
        barang.getHarga().hitungTotalHarga();

        barangRepository.save(barang);

        redirectAttributes.addFlashAttribute("success", "Jumlah barang berhasil diperbarui.");
        return "redirect:/halamanGudangDetail/" + id;
    }
    // @GetMapping("/barang/cari")
    // public String cariBarang(@RequestParam("nama") String nama, Model model) {
    //     if (nama == null || nama.isBlank()) {
    //         model.addAttribute("error", "Nama barang tidak boleh kosong");
    //         return "pages/halamanGudangBeranda";
    //     }

    //     model.addAttribute("barangList", barangRepository.findByNamaContainingIgnoreCase(nama));
    //     return "pages/halamanGudangBeranda";
    // }
    // @GetMapping("/barang/ukuran")
    // public String sortBarangbyUkuran(@RequestParam("ukuran") int ukuran, Model model) {
    //     // You can directly query and add the filtered list to the model
    //     model.addAttribute("barangList", barangRepository.findByUkuranContainingIgnoreCase(ukuran));
    //     return "pages/halamanGudangBeranda";
    // }
    @GetMapping("/search/result")
    public String searchBarang(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String filter,
            Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || (!"kasir".equalsIgnoreCase(user.getRole()) && !"admin".equalsIgnoreCase(user.getRole()))) {
            return "redirect:/login";
        }

        List<Barang> barangList;

        // Jika ada query pencarian
        if (query != null && !query.trim().isEmpty()) {
            barangList = barangRepository.findByNamaContainingIgnoreCase(query);
        } else {
            barangList = barangRepository.findAll();
        }

        // Filter tambahan berdasarkan kategori atau properti
        if (filter != null) {
            barangList = barangList.stream().filter(b -> {
                Kategori k = b.getKategori();
                if (k == null) return false;
                switch (filter) {
                    case "ukuran": return k.getUkuran() != 0;
                    case "bentuk": return k.getBentuk() != null && !k.getBentuk().isEmpty();
                    case "material": return k.getBahan() != null && !k.getBahan().isEmpty();
                    case "brand": return b.getMerek() != null && !b.getMerek().isEmpty();
                    case "ketebalan": return k.getKetebalan() != null;
                    default: return true;
                }
            }).collect(Collectors.toList());
        }

        // Sorting
        if (sort != null) {
            switch (sort) {
                case "az":
                    barangList.sort(Comparator.comparing(Barang::getNama, String.CASE_INSENSITIVE_ORDER));
                    break;
                case "za":
                    barangList.sort(Comparator.comparing(Barang::getNama, String.CASE_INSENSITIVE_ORDER).reversed());
                    break;
                case "expensive":
                    barangList.sort(Comparator.comparing(
                        b -> b.getHarga() != null ? b.getHarga().getHarga() : 0,
                        Comparator.nullsLast(Comparator.reverseOrder())
                    ));
                    break;
                case "cheap":
                    barangList.sort(Comparator.comparing(
                        b -> b.getHarga() != null ? b.getHarga().getHarga() : 0,
                        Comparator.nullsLast(Integer::compareTo)
                    ));
                    break;
            }
        }

        model.addAttribute("barangList", barangList);
        model.addAttribute("query", query);
        model.addAttribute("sort", sort);
        model.addAttribute("filter", filter);
        model.addAttribute("showSearch", false); // popup tampil setelah pencarian

        return "pages/halamangudangberanda"; // Ganti dengan nama file HTML yang sesuai
    }
    // @GetMapping("/search/open")
    // public String openSearchPopup(Model model, HttpSession session) {
    //     User user = (User) session.getAttribute("loggedInUser");
    //     if (user == null || (!"kasir".equalsIgnoreCase(user.getRole()) && !"admin".equalsIgnoreCase(user.getRole()))) {
    //         return "redirect:/login";
    //     }
    //     model.addAttribute("showSearch", true); // popup terbuka
    //     // Tambahkan data lain jika perlu
    //     return "pages/halamangudangberanda";
    // }


}
