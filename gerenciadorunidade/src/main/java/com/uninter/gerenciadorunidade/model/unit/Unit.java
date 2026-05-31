package com.uninter.gerenciadorunidade.model.unit;

import com.uninter.gerenciadorunidade.model.auth.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnitStatus status;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "unit")
    private List<Employee> employees;

    @OneToMany(mappedBy = "unit")
    private List<Promotion> promotions;

    @OneToMany(mappedBy = "unit")
    private List<Report> reports;

    @OneToMany(mappedBy = "unit")
    private List<InventoryItem> inventoryItems;

    @OneToMany(mappedBy = "unit")
    private List<Menu> menus;

    @OneToMany(mappedBy = "unit")
    private List<User> users;

    public void deactivate() {
        this.status = UnitStatus.INACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

}
