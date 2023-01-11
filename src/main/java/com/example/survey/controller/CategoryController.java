package com.example.survey.controller;

import static com.example.survey.mapper.CategoryMapper.mapCategoryListToCategoryDtoList;
import static com.example.survey.mapper.CategoryMapper.mapCategoryToCategoryCreationDto;
import static com.example.survey.mapper.CategoryMapper.mapCategoryToCategoryDto;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.survey.dto.CategoryCreationDto;
import com.example.survey.dto.CategoryDto;
import com.example.survey.service.CategoryService;

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
        return mapCategoryToCategoryDto(categoryService.getCategoryById(id));
    }

    @PostMapping("/categories")
    public CategoryCreationDto createCategory(@Valid @RequestBody CategoryCreationDto categoryCreationDto){
        return mapCategoryToCategoryCreationDto(categoryService.createCategory(categoryCreationDto));
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }

    @PutMapping("/categories/{id}")
    public CategoryDto updateCategory(@Valid @RequestBody CategoryDto categoryDto){
        return mapCategoryToCategoryDto(categoryService.updateCategory(categoryDto));
    }
}
