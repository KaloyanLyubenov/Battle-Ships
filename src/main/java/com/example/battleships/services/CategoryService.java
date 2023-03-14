package com.example.battleships.services;

import com.example.battleships.domain.entities.Category;
import com.example.battleships.domain.enums.CategoryType;
import com.example.battleships.domain.models.CategoryModel;
import com.example.battleships.repos.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    //Starts right after the app is started
    @PostConstruct
    private void postConstruct(){
        if(this.categoryRepository.count() == 0){
            this.categoryRepository.saveAllAndFlush(Arrays.stream((CategoryType.values()))
                    .map(enumValue -> CategoryModel.builder()
                            .name(enumValue)
                            .description("fight me")
                            .build())
                    .map(catModel -> this.modelMapper.map(catModel, Category.class))
                    .toList());
        }
    }

    public CategoryModel findByName(CategoryType name) {
        return this.modelMapper.map(this.categoryRepository.findByName(name).orElse(new Category()), CategoryModel.class);
    }
}
