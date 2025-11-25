package com.theopenbank.customer.dto;

import com.theopenbank.customer.model.Gender;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CustomerRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private Gender gender;
    private LocalDate dateOfBirth;
}

