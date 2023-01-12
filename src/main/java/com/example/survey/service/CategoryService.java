package com.example.survey.service;

import com.example.survey.dto.CategoryCreationDto;
import com.example.survey.dto.CategoryDto;
import com.example.survey.exception.CategoryAlreadyExistsException;
import com.example.survey.exception.NoSuchCategoryExistsException;
import com.example.survey.model.Category;
import com.example.survey.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    //brak potrzeby obslugi wyjatkow
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }


    public Category createCategory(CategoryCreationDto categoryCreationDto){
        Boolean categoryExists = categoryRepository.existsByName(categoryCreationDto.name());

        if(!categoryExists){
            Category category = new Category();
            category.setDescription(categoryCreationDto.description());
            category.setName(categoryCreationDto.name());
            return categoryRepository.save(category);
        }
        else
            throw new CategoryAlreadyExistsException("Category already exists");
    }


    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchCategoryExistsException("No category present with id = "+id));
    }

    public void deleteCategory(Long id) {
        if(categoryRepository.existsById(id))
            categoryRepository.deleteById(id);
    }

    public Category updateCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryDto.id())
                        .orElseThrow(() -> new NoSuchCategoryExistsException("No category present with id = "+categoryDto.id()));

        category.setId(categoryDto.id());
        category.setDescription(categoryDto.description());
        category.setName(categoryDto.name());
        return categoryRepository.save(category);
    }



    //getCategoryById() +
    //createCategory() +
    //deleteCategory() +
    //updateCategory() +

    //dodatkowo
    //getCategories() +
}
