package com.example.survey.mapper;

import com.example.survey.dto.CategoryCreationDto;
import com.example.survey.dto.CategoryDto;
import com.example.survey.model.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryCreationDto mapCategoryToCategoryCreationDto(Category category){
        return new CategoryCreationDto(
                category.getDescription(),
                category.getName()
        );
    }

    public static List<CategoryDto> mapCategoryListToCategoryDtoList(List<Category> categoryList){
        return categoryList.stream()
                .map(category -> new CategoryDto(
                        category.getId(),
                        category.getDescription(),
                        category.getName()
                )).collect(Collectors.toList());
    }

    public static CategoryDto mapCategoryToCategoryDto(Category category){
        return new CategoryDto(
                category.getId(),
                category.getDescription(),
                category.getName()
        );
    }
}
