package com.gudangdamar.main.repository;

import com.gudangdamar.main.model.Servis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServisRepository extends JpaRepository<Servis, Long> {
}
