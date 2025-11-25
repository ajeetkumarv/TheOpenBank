package com.theopenbank.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.theopenbank.customer.model.Gender;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CustomerRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private Gender gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
}

