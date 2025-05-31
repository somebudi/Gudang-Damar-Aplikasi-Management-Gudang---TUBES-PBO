package com.gudangdamar.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gudangdamar.main.model.Barang;


public interface BarangRepository extends JpaRepository<Barang, Integer> {
    List<Barang> findByNamaContainingIgnoreCase(String nama);
}   