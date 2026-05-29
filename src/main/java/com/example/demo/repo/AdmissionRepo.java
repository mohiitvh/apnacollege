package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Admission;

public interface AdmissionRepo extends JpaRepository<Admission, Integer> {

}