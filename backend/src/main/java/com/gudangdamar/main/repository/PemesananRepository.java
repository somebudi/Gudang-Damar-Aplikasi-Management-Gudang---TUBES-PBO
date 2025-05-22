package com.gudangdamar.main.repository;

import com.gudangdamar.main.model.Pemesanan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PemesananRepository extends JpaRepository<Pemesanan, Long> {
}
