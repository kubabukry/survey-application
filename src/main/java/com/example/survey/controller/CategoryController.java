package com.example.survey.controller;

import com.example.survey.dto.CategoryCreationDto;
import com.example.survey.dto.CategoryDto;
import com.example.survey.model.Category;
import com.example.survey.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.survey.mapper.CategoryMapper.*;

@RestController
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<CategoryDto> getCategories(){
        return mapCategoryListToCategoryDtoList(categoryService.getCategories());
    }

    @GetMapping("/categories/{id}")
    public CategoryDto getSingleCategory(@PathVariable Long id){
        return mapCategoryToCategoryDto(categoryService.getCategory(id));
    }

    @PostMapping("/categories")
    public CategoryCreationDto createCategory(@RequestBody CategoryCreationDto categoryCreationDto){
        return mapCategoryToCategoryCreationDto(categoryService.createCategory(categoryCreationDto));
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }

    @PutMapping("/category/{id}")
    public Category updateCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.updateCategory(categoryDto);
    }
}
