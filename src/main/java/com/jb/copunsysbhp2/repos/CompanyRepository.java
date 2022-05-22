package com.jb.copunsysbhp2.repos;

import com.jb.copunsysbhp2.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByEmail(String email);

    boolean existsByName(String name);

    boolean existsByPassword(String password);

    Company findByEmailAndPassword(String email, String password);

}