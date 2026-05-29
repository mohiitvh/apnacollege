package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admission;
import com.example.demo.repo.AdmissionRepo;

@Service
public class StudentService {

    @Autowired
    private AdmissionRepo admissionRepo;

    public Admission saveAdmission(Admission admission) {
        return admissionRepo.save(admission);
    }

    public Admission getAdmissionById(int id) {
        return admissionRepo.findById(id).orElse(null);
    }
}