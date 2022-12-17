package com.example.survey.service;

import com.example.survey.dto.CategoryCreationDto;
import com.example.survey.dto.CategoryDto;
import com.example.survey.model.Category;
import com.example.survey.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(CategoryCreationDto categoryCreationDto){
        Category category = new Category();
        category.setDescription(categoryCreationDto.description());
        category.setName(categoryCreationDto.name());

        return categoryRepository.save(category);
    }

    public Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow();
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category updateCategory(CategoryDto categoryDto) {
        Category category = new Category();

        category.setId(categoryDto.id());
        category.setDescription(categoryDto.description());
        category.setName(categoryDto.name());

        return categoryRepository.save(category);
    }



    //getCategory() +
    //createCategory() +
    //deleteCategory() +
    //updateCategory() +

    //dodatkowo
    //getCategories() +

    //todo dopisac obsluge wyjatkow
}
