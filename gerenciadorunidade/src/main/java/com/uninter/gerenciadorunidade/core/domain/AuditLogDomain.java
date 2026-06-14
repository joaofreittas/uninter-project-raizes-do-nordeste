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
public class AuditLogDomain {

    private Long id;
    private String userEmail;
    private AuditAction action;
    private String entity;
    private Long entityId;
    private LocalDateTime occurredAt;

}
