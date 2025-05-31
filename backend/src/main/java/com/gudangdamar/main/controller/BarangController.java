package com.gudangdamar.main.controller;

import com.gudangdamar.main.model.Barang;
import com.gudangdamar.main.model.Harga;
import com.gudangdamar.main.model.Kategori;
import com.gudangdamar.main.repository.BarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class BarangController {

    @Autowired
    private BarangRepository barangRepository;

    // Form tambah barang
    @GetMapping("barang/tambah")
    public String formTambahBarang(Model model) {
        Barang barang = new Barang();
        barang.setHarga(new Harga());
        barang.setKategori(new Kategori());
        model.addAttribute("barang", barang);
        return "pages/tambahBarang"; 
    }

    // Simpan barang
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

        Barang saved = barangRepository.save(barang);

        redirectAttributes.addFlashAttribute("success", "Barang berhasil disimpan dengan ID: " + saved.getIdBarang());
        return "redirect:/halamanGudangDetail/" + saved.getIdBarang();
    }

    // Hapus barang
    @PostMapping("barang/delete/{id}")
    public String deleteBarang(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if (barangRepository.existsById(id)) {
            barangRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Barang berhasil dihapus.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Barang tidak ditemukan.");
        }
        return "redirect:/halamanGudangDetail/" + id;
    }

    // Edit barang
    @PostMapping("barang/edit/{id}")
    public String editBarang(@PathVariable Integer id,
                             @ModelAttribute("barang") Barang updatedBarang,
                             RedirectAttributes redirectAttributes) {
        Optional<Barang> opt = barangRepository.findById(id);

        if (opt.isPresent()) {
            Barang existingBarang = opt.get();
            existingBarang.setNama(updatedBarang.getNama());

            // Update kategori
            if (existingBarang.getKategori() != null && updatedBarang.getKategori() != null) {
                Kategori ek = existingBarang.getKategori();
                Kategori uk = updatedBarang.getKategori();
                ek.setUkuran(uk.getUkuran());
                ek.setBentuk(uk.getBentuk());
                ek.setKetebalan(uk.getKetebalan());
                ek.setBahan(uk.getBahan());
                ek.setMerek(uk.getMerek());
            }

            // Update harga
            if (existingBarang.getHarga() != null && updatedBarang.getHarga() != null) {
                Harga eh = existingBarang.getHarga();
                Harga uh = updatedBarang.getHarga();
                eh.setHarga(uh.getHarga());
                eh.setJumlah(uh.getJumlah());
                eh.hitungTotalHarga();
            }

            barangRepository.save(existingBarang);
            redirectAttributes.addFlashAttribute("success", "Barang berhasil diupdate.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Barang tidak ditemukan.");
        }

        return "redirect:/halamanGudangDetail/" + id;
    }
}
