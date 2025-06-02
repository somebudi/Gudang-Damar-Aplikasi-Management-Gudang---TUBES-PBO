package com.gudangdamar.main.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gudangdamar.main.model.Barang;
import com.gudangdamar.main.repository.BarangRepository;

@Controller
public class SearchController {

    @Autowired
    private BarangRepository barangRepository;

    @GetMapping
    public String searchBarang(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "bentuk", required = false) String bentuk,
            @RequestParam(name = "ukuran", required = false) String ukuran,
            @RequestParam(name = "ketebalan", required = false) String ketebalan,
            @RequestParam(name = "material", required = false) String material,
            @RequestParam(name = "brand", required = false) String brand,
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "order", required = false) String order,
            Model model
    ) {
        List<Barang> allBarang = barangRepository.findAll();
        List<Barang> filtered = new ArrayList<>(allBarang);

        // Filter by keyword
        if (keyword != null && !keyword.isEmpty()) {
            String lowerKeyword = keyword.toLowerCase();
            filtered = filtered.stream()
                    .filter(b -> b.getNama().toLowerCase().contains(lowerKeyword))
                    .collect(Collectors.toList());
        }

        // Filter by bentuk
        if (bentuk != null && !bentuk.isEmpty()) {
            filtered = filtered.stream()
                    .filter(b -> b.getKategori().getBentuk().equalsIgnoreCase(bentuk))
                    .collect(Collectors.toList());
        }

        // Filter by ukuran
        //if (ukuran != null && !ukuran.isEmpty()) {
          //  filtered = filtered.stream()
            //        .filter(b -> b.getKategori().getUkuran().equalsIgnoreCase(ukuran))
               //     .collect(Collectors.toList());
       // }

        // Filter by ketebalan
        //if (ketebalan != null && !ketebalan.isEmpty()) {
            //filtered = filtered.stream()
                    //.filter(b -> b.getKategori().getKetebalan().equalsIgnoreCase(ketebalan))
                    //.collect(Collectors.toList());
       // }

        // Filter by material
        if (material != null && !material.isEmpty()) {
            filtered = filtered.stream()
                    .filter(b -> b.getKategori().getBahan().equalsIgnoreCase(material))
                    .collect(Collectors.toList());
        }

        // Filter by brand
        if (brand != null && !brand.isEmpty()) {
            filtered = filtered.stream()
                    .filter(b -> b.getKategori().getMerek().equalsIgnoreCase(brand))
                    .collect(Collectors.toList());
        }

        // Sort logic
        // if (sortBy != null) {
        //     Comparator<Barang> comparator = switch (sortBy) {
        //         case "nama" -> Comparator.comparing(Barang::getNama, String.CASE_INSENSITIVE_ORDER);
        //         case "harga" -> Comparator.comparing(b -> b.getHarga().getHarga());
        //         default -> null;
        //     };

        //     if (comparator != null) {
        //         if ("desc".equalsIgnoreCase(order)) {
        //             comparator = comparator.reversed();
        //         }
        //         filtered.sort(comparator);
        //     }
        if (sortBy != null) {
            switch (sortBy) {
                case "az":
                    filtered.sort(Comparator.comparing(Barang::getNama, String.CASE_INSENSITIVE_ORDER));
                    break;
                case "za":
                    filtered.sort(Comparator.comparing(Barang::getNama, String.CASE_INSENSITIVE_ORDER).reversed());
                    break;
                case "expensive":
                    filtered.sort(Comparator.comparing(
                        b -> b.getHarga() != null ? b.getHarga().getHarga() : 0,
                        Comparator.nullsLast(Comparator.reverseOrder())
                    ));
                    break;
                case "cheap":
                    filtered.sort(Comparator.comparing(
                        b -> b.getHarga() != null ? b.getHarga().getHarga() : 0,
                        Comparator.nullsLast(Integer::compareTo)
                    ));
                    break;
            }
        }


        model.addAttribute("barangList", filtered);
        return "pages/halamanGudangBeranda";
    }
}
