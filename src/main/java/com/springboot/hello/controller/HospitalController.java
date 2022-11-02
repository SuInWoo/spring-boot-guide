package com.springboot.hello.controller;

import com.springboot.hello.dao.HospitalDao;
import com.springboot.hello.domain.Hospital;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/getCount")
    public ResponseEntity<Integer> getCount() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(hospitalDao.getCount());
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Integer> deleteAll() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(hospitalDao.deleteAll());
    }

    @GetMapping("/find")
    public ResponseEntity<Hospital> findById(int id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(hospitalDao.findById(id));
    }
}
