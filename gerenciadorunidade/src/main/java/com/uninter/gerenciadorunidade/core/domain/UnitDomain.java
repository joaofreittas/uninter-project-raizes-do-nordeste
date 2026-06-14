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
public class UnitDomain {

    private Long id;
    private String name;
    private String address;
    private UnitStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UnitDomain deactivate() {
        return UnitDomain.builder()
            .id(this.id)
            .name(this.name)
            .address(this.address)
            .status(UnitStatus.INACTIVE)
            .createdAt(this.createdAt)
            .updatedAt(LocalDateTime.now())
            .build();
    }

}
