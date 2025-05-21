package com.gudangdamar.main.controller;
import com.gudangdamar.main.model.Kategori;
import com.gudangdamar.main.model.Barang;
import com.gudangdamar.main.repository.BarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model; 
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/barang")
@CrossOrigin(origins = "*")
public class BarangController {

    @Autowired
    private BarangRepository barangRepo;

    @GetMapping("/listBarang")
    public String getAllBarang(Model model) {
        List<Barang> barangList = barangRepo.findAll();
        model.addAttribute("barangList", barangList);
        return "listBarang";  
    }

    @GetMapping("/{id}")
    public Barang getBarangById(@PathVariable int id) {
        Optional<Barang> barang = barangRepo.findById(id);
        return barang.orElse(null);
    }

    @PostMapping
    public Barang createBarang(@RequestBody Barang barang) {
        return barangRepo.save(barang);
    }

    @PutMapping("/{id}")
    public Barang updateBarang(@PathVariable int id, @RequestBody Barang updatedBarang) {
        Optional<Barang> optionalBarang = barangRepo.findById(id);

        if (optionalBarang.isPresent()) {
            Barang existingBarang = optionalBarang.get();

            existingBarang.setNamaBarang(updatedBarang.getNamaBarang());
            existingBarang.setStok(updatedBarang.getStok());
            existingBarang.setKategori(updatedBarang.getKategori());
            existingBarang.setWaktuMasuk(updatedBarang.getWaktuMasuk());
            existingBarang.setWaktuKeluar(updatedBarang.getWaktuKeluar());
            existingBarang.setWaktuPendataan(updatedBarang.getWaktuPendataan());
            existingBarang.setNamaSupplier(updatedBarang.getNamaSupplier());

            return barangRepo.save(existingBarang);
        } else {
            return null; // Atau bisa lempar exception NotFound
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBarang(@PathVariable int id) {
        barangRepo.deleteById(id);
    }
}
