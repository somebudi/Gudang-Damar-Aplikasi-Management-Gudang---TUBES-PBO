package com.gudangdamar.main.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gudangdamar.main.model.Barang;


@Repository
public interface BarangRepository extends JpaRepository<Barang, Integer> {
    List<Barang> findByNamaContainingIgnoreCase(String nama);
    // List<Barang> findByKategori_UkuranContainingIgnoreCase(Kategori getategori.ukuran);
}
