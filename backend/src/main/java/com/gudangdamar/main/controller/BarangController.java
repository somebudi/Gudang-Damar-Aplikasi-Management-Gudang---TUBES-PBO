package com.gudangdamar.main.controller;

import com.gudangdamar.main.model.Barang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BarangController {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @GetMapping("/barang")
    public String tampilkanBarang(Model model) {
        List<Barang> list = entityManager.createQuery("SELECT b FROM Barang b", Barang.class).getResultList();
        model.addAttribute("cards", list);
        return "listBarang"; // templates/listBarang.html
    }
}
