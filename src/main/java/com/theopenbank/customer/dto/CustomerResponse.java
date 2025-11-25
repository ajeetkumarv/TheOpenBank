package com.theopenbank.customer.dto;

import com.theopenbank.customer.model.Gender;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CustomerResponse {
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;
}
