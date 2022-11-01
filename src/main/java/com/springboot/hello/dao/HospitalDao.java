package com.springboot.hello.dao;

import com.springboot.hello.domain.Hospital;
import com.springboot.hello.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HospitalDao {

    private final JdbcTemplate jdbcTemplate;

    public HospitalDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    private final RowMapper<Hospital> rowMapper = new RowMapper<>() {
//        @Override
//        public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return new Hospital(rs.getInt("id"),
//                    rs.getString("openServiceName"), rs.getInt("openLocalGovernmentCode"),
//                    rs.getString("managementNumber"), rs.getTimestamp("licenseDate"),
//                    rs.getInt("businessStatus"), rs.getInt("businessStatusCode"),
//                    rs.getString("phone"), rs.getString("fullAddress"),
//                    rs.getString("roadNameAddress"), rs.getString("hospitalName"),
//                    rs.getString("businessTypeName"), rs.getInt("healthcareProviderCount"),
//                    rs.getInt("patientRoomCount"), rs.getInt("totalNumberOfBeds"),
//                    rs.getFloat("totalAreaSize"));
//        }
//    };

//    public Hospital findById(int id) {
//        Hospital hospital = null;
//        String sql = "SELECT * FROM nation_wide_hospitals WHERE id = ?;";
//        hospital = this.jdbcTemplate.queryForObject(sql, rowMapper, id);
//        return hospital;
//    }

    public int getCount() {
        return this.jdbcTemplate.queryForObject(
                "SELECT COUNT(id) from nation_wide_hospitals;", Integer.class);
    }

    public int deleteAll() {
        return this.jdbcTemplate.update("delete from nation_wide_hospitals;");
    }

    public void add(Hospital hospital){
        String sql = "INSERT INTO `hospital_db`.`nation_wide_hospitals`  " +
                "(`id`, `open_service_name`, `open_local_government_code`, " +
                "`management_number`, `license_date`, `business_status`, " +
                "`business_status_code`, `phone`, `full_address`, " +
                "`road_name_address`, `hospital_name`, `business_type_name`, " +
                "`healthcare_provider_count`, `patient_room_count`, " +
                "`total_number_of_beds`, `total_area_size`) " +
                "VALUES (?, ?, " +
                "?, ?, " +
                "?, ?, " +
                "?, ?, " +
                "?, ?, " +
                "?, ?, " +
                "?, ?, " +
                "?, ? );";
        this.jdbcTemplate.update(sql, hospital.getId(), hospital.getOpenServiceName(),
                hospital.getOpenLocalGovernmentCode(), hospital.getManagementNumber(),
                hospital.getLicenseDate(), hospital.getBusinessStatus(),
                hospital.getBusinessStatusCode(), hospital.getPhone(),
                hospital.getFullAddress(), hospital.getRoadNameAddress(),
                hospital.getHospitalName(), hospital.getBusinessTypeName(),
                hospital.getHealthcareProviderCount(), hospital.getPatientRoomCount(),
                hospital.getTotalNumberOfBeds(), hospital.getTotalAreaSize());
    }
}
