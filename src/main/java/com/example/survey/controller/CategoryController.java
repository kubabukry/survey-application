package com.example.survey.controller;

import com.example.survey.dto.CategoryCreationDto;
import com.example.survey.dto.CategoryDto;
import com.example.survey.exception.CategoryAlreadyExistsException;
import com.example.survey.exception.ErrorResponse;
import com.example.survey.exception.NoSuchCategoryExistsException;
import com.example.survey.model.Category;
import com.example.survey.service.CategoryService;
import org.springframework.http.HttpStatus;
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
        return mapCategoryToCategoryDto(categoryService.getCategoryById(id));
    }

    @PostMapping("/categories")
    public CategoryCreationDto createCategory(@RequestBody CategoryCreationDto categoryCreationDto){
        return mapCategoryToCategoryCreationDto(categoryService.createCategory(categoryCreationDto));
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }

    @PutMapping("/categories/{id}")
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto){
        return mapCategoryToCategoryDto(categoryService.updateCategory(categoryDto));
    }

    @ExceptionHandler(value = CategoryAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCategoryAlreadyExistsException(CategoryAlreadyExistsException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(value = NoSuchCategoryExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoSuchCategoryExistsException(NoSuchCategoryExistsException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
