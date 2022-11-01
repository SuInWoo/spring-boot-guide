package com.springboot.hello.parser;

import com.springboot.hello.dao.HospitalDao;
import com.springboot.hello.domain.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalParserTest {


    String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\"," +
            "\"\",\"1\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\"," +
            "\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\"," +
            "\"2.02111E+13\",\"U\",\"2021.11.17 2:40\",\"치과의원\",\"192630.7351\",\"185314.6176\",\"치과의원\"," +
            "\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\"";

    @Autowired
    ReadLineContext<Hospital> hospitalReadLineContext;

    @Autowired
    HospitalDao hospitalDao;

    @Test
    @DisplayName("Hospital이 insert 잘 되는지")
    void add(){
        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);
        hospitalDao.add(hospital);
    }

    @Test
    @DisplayName("저장되어있는 컬럼 갯수 출력")
    void getCount(){
        hospitalDao.deleteAll();
        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);
        hospitalDao.add(hospital);
        assertEquals(1,hospitalDao.getCount());
    }

    @Test
    @DisplayName("전체 삭제")
    void deleteAll() {
        hospitalDao.deleteAll();
        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);
        hospitalDao.add(hospital);
        assertEquals(1,hospitalDao.getCount());

        hospitalDao.deleteAll();
        assertEquals(0, hospitalDao.getCount());
    }

    @Test
    @DisplayName("id로 검색")
    void findById() {
        hospitalDao.deleteAll();
        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);
        hospitalDao.add(hospital);

        Hospital hospitalTest = hospitalDao.findById(1);
        assertThat(hospitalTest.getId())    //int Test
                .isEqualTo(hospital.getId());

        assertThat(hospitalTest.getHospitalName())  //String Test
                .isEqualTo(hospital.getHospitalName());

        assertThat(hospitalTest.getLicenseDate())   //localDateTime Test
                .isEqualTo(hospital.getLicenseDate());

        assertThat(hospitalTest.getTotalAreaSize()) //float Test
                .isEqualTo(hospital.getTotalAreaSize());
    }



    @Test
    @DisplayName("10만건 이상 데이터가 파싱 되는지")
    void oneHundredParserTest() throws IOException {
        String filename = "/Users/suin/Downloads/수업 데이터 파일/fulldata_01_01_02_P_의원.csv";
        List<Hospital> hospitalList = hospitalReadLineContext.readByLine(filename);
        assertTrue(hospitalList.size() > 1000);
        assertTrue(hospitalList.size() > 100000);
    }

    @Test
    @DisplayName("csv 1줄 -> Hospital")
    void convertToHospital() {

        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);

        assertEquals(1, hospital.getId());
        assertEquals("의원", hospital.getOpenServiceName());
        assertEquals(3620000,hospital.getOpenLocalGovernmentCode());
        assertEquals("PHMA119993620020041100004",hospital.getManagementNumber());
        assertEquals(LocalDateTime.of(1999, 6, 12, 0, 0),
                hospital.getLicenseDate());
        assertEquals(1, hospital.getBusinessStatus());
        assertEquals(13, hospital.getBusinessStatusCode());
        assertEquals("062-515-2875", hospital.getPhone());
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress());
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress());
        assertEquals("효치과의원", hospital.getHospitalName());
        assertEquals("치과의원", hospital.getBusinessTypeName());
        assertEquals(1, hospital.getHealthcareProviderCount());
        assertEquals(0, hospital.getPatientRoomCount());
        assertEquals(0, hospital.getTotalNumberOfBeds());
        assertEquals(52.29f, hospital.getTotalAreaSize());

    }
}