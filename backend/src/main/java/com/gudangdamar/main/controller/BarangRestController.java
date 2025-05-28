package com.gudangdamar.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gudangdamar.main.model.Barang;
import com.gudangdamar.main.model.Harga;
import com.gudangdamar.main.model.Kategori;
import com.gudangdamar.main.repository.BarangRepository;

@RestController
@RequestMapping("/api/barang")
@CrossOrigin(origins = "*")
public class BarangRestController {

    @Autowired
    private BarangRepository barangRepo;

    // ✅ Ambil semua barang
    @GetMapping
    public List<Barang> getAllBarang() {
        return barangRepo.findAll();
    }

    // ✅ Ambil satu barang berdasarkan ID
    @GetMapping("/{id}")
    public Barang getBarangById(@PathVariable int id) {
        return barangRepo.findById(id).orElse(null);
    }

    // ✅ Tambah barang
    @PostMapping
    public Barang createBarang(@RequestBody Barang barang) {
        return barangRepo.save(barang);
    }

    // ✅ Update barang
    @PutMapping("/{id}")
    public Barang updateBarang(@PathVariable int id, @RequestBody Barang updatedBarang) {
        Optional<Barang> optionalBarang = barangRepo.findById(id);
        if (optionalBarang.isPresent()) {
            Barang existing = optionalBarang.get();

            // Set data dasar barang
            existing.setNama(updatedBarang.getNama());
            

            // Set data kategori jika tersedia
            Kategori updatedKategori = updatedBarang.getKategori();
            if (updatedKategori != null) {
                if (existing.getKategori() == null) {
                    existing.setKategori(new Kategori());
                }

                Kategori existingKategori = existing.getKategori();
                existingKategori.setUkuran(updatedKategori.getUkuran());
                existingKategori.setKetebalan(updatedKategori.getKetebalan());
                existingKategori.setBentuk(updatedKategori.getBentuk());
                existingKategori.setBahan(updatedKategori.getBahan());
                existingKategori.setMerek(updatedKategori.getMerek());
            }
            Harga updatedHarga = updatedBarang.getHarga();
            if (updatedHarga != null) {
                if (existing.getHarga() == null) {
                    existing.setHarga(new Harga());
                }

                Harga existingHarga = existing.getHarga();
                existingHarga.setHarga(updatedHarga.getHarga());
                existingHarga.setJumlah(updatedHarga.getJumlah());
                existingHarga.hitungTotalHarga(); // Hitung total harga
            }
            return barangRepo.save(existing);
        }

        return null;
    }

    // ✅ Hapus barang
    @DeleteMapping("/{id}")
    public void deleteBarang(@PathVariable int id) {
        barangRepo.deleteById(id);
    }
    
}
