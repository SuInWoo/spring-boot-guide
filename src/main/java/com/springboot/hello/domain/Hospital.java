package com.springboot.hello.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Hospital {
    private int id;
    private String openServiceName;
    private int openLocalGovernmentCode;
    private String managementNumber;
    private LocalDateTime licenseDate;
    private int businessStatus;
    private int businessStatusCode;
    private String phone;
    private String fullAddress;
    private String roadNameAddress;
    private String hospitalName;
    private String businessTypeName;
    private int healthcareProviderCount;
    private int patientRoomCount;
    private int totalNumberOfBeds;
    private float totalAreaSize;

    public String getBusinessStatusCode() {
        String str = "확인되지 않음";
        if (businessStatusCode == 2) str = "휴업";
        else if (businessStatusCode == 3) str = "폐업";
        else if (businessStatusCode == 13) str = "영업중";
        return str;
    }
}
