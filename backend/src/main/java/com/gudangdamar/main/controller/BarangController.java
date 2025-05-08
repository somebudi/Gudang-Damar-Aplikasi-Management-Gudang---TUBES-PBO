// package com.gudangdamar.main.controller;

// import com.gudangdamar.main.model.Barang;
// import com.gudangdamar.main.model.Kategori;
// import com.gudangdamar.main.service.BarangService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/barang")
// public class BarangController {

//     @Autowired
//     private BarangService barangService;

//     @GetMapping
//     public List<Barang> getAllBarang() {
//         return barangService.getAllBarang();
//     }

//     @GetMapping("/{id}")
//     public Barang getBarangById(@PathVariable int id) {
//         return barangService.getBarangById(id);
//     }

//     @PostMapping
//     public Barang addBarang(@RequestBody Barang barang) {
//         return barangService.saveBarang(barang);
//     }

//     @PutMapping("/{id}")
//     public Barang updateBarang(@PathVariable int id, @RequestBody Barang barang) {
//         return barangService.updateBarang(id, barang);
//     }

//     @DeleteMapping("/{id}")
//     public void deleteBarang(@PathVariable int id) {
//         barangService.deleteBarang(id);
//     }
// }
