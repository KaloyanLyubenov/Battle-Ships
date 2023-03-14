package com.example.battleships.domain.models.binding;

import com.example.battleships.domain.models.CategoryModel;
import com.example.battleships.validations.checkShipExistence.ValidateShipExistence;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddShipModel {

    @Size(min = 2, max = 10)
    @NotNull
    @ValidateShipExistence
    private String name;

    @Min(1)
    @NotNull
    private Long health;

    @Min(1)
    @NotNull
    private Long power;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date created;

    @NotNull
    private String category;

}
