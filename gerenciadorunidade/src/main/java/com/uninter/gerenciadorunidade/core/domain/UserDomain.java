package com.uninter.gerenciadorunidade.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDomain {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private Long unitId;
    private Long employeeId;

}
