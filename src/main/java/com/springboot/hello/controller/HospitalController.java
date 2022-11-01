package com.springboot.hello.controller;

import com.springboot.hello.dao.HospitalDao;
import com.springboot.hello.dao.UserDao;
import com.springboot.hello.domain.Hospital;
import com.springboot.hello.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalController {

    private final HospitalDao hospitalDao;

    public HospitalController(HospitalDao hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(Hospital hospital) {
        hospitalDao.add(hospital);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("추가완료");
    }
}
