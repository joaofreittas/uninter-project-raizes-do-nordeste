package com.uninter.gerenciadorunidade.core.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDomain {

    private Long id;
    private String name;
    private String document;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private Boolean lgpdAccepted;
    private Boolean marketingAccepted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
