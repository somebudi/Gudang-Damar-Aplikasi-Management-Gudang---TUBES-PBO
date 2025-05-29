package com.gudangdamar.main.repository;

import com.gudangdamar.main.model.Barang;
import com.gudangdamar.main.dto.BarangDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BarangRepository extends JpaRepository<Barang, Integer> {

    //@Query("SELECT NEW com.gudangdamar.main.dto.BarangDTO(b.nama, SUM(b.harga.jumlah), AVG(b.harga.harga)) " +
       //"FROM Barang b GROUP BY b.nama")
    //List<BarangDTO> countBarangByNama();
}