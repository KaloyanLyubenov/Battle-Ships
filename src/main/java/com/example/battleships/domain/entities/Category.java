package com.example.battleships.domain.entities;

import com.example.battleships.domain.enums.CategoryType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "categories")
public class Category extends BaseEntity{

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryType name;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

}
