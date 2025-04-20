package com.gudangdamar.main.repository;

import com.gudangdamar.main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Kamu bisa menambahkan custom query di sini kalau perlu
}
