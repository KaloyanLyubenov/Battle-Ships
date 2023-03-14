package com.example.battleships.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ships")
public class Ship extends BaseEntity{

    @Column(unique = true, nullable = false)
    @Size(min = 2, max = 10)
    private String name;

    @Column(nullable = false)
    @Min(0)
    private Long health;

    @Column(nullable = false)
    @Min(0)
    private Long power;

    @Column(nullable = false)
    private Date created;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    @Override
    public String toString() {
        String s = "| %s | %d | %d |";
        return String.format(s, name, health, power);
    }
}
