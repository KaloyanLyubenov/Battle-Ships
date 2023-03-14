package com.example.battleships.repos;

import com.example.battleships.domain.entities.Category;
import com.example.battleships.domain.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(CategoryType category);
}
