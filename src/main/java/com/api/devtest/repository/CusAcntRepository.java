package com.api.devtest.repository;

import com.api.devtest.entity.CusAcnt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CusAcntRepository extends JpaRepository<CusAcnt, String> {
    //Optional<CusAcnt> findById(String id);
}
