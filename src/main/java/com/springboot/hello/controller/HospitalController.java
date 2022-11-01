package com.springboot.hello.controller;

import com.springboot.hello.dao.HospitalDao;
import com.springboot.hello.dao.UserDao;
import com.springboot.hello.domain.Hospital;
import com.springboot.hello.domain.User;
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
    public String add() {
        hospitalDao.add(new Hospital(1, "의원",
                3620000, "PHMA119993620020041100004",
                LocalDateTime.of(1999, 06, 12, 0, 0),
                1, 13, "062-515-2875",
                "광주광역시 북구 풍향동 565번지 4호 3층", "광주광역시 북구 동문대로 24, 3층 (풍향동)",
                "효치과의원", "치과의원", 1,
                0, 0, 52.29f));
        return "추가완료";
    }
}
