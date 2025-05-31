package com.gudangdamar.main.repository;

import com.gudangdamar.main.model.Barang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BarangRepository extends JpaRepository<Barang, Integer> {}