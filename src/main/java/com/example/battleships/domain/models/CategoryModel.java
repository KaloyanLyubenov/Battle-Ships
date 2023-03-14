package com.example.battleships.domain.models;

import com.example.battleships.domain.enums.CategoryType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryModel {

    private Long id;

    private CategoryType name;

    private String description;

}
