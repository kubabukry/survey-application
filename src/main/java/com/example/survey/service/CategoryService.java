package com.example.survey.service;

import com.example.survey.dto.CategoryCreationDto;
import com.example.survey.dto.CategoryDto;
import com.example.survey.dto.CategoryScoreDto;
import com.example.survey.exception.CategoryAlreadyExistsException;
import com.example.survey.exception.NoSuchCategoryExistsException;
import com.example.survey.model.Category;
import com.example.survey.model.CompanySurvey;
import com.example.survey.model.SurveyTemplate;
import com.example.survey.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final StatisticsService statisticsService;

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

    public List<CategoryScoreDto> getCategoryScore(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->new NoSuchCategoryExistsException("No such category with id: "+id+" exists"));

        List<SurveyTemplate> surveyTemplateList = category.getSurveyTemplateList();
        List<CompanySurvey> companySurveyList = new ArrayList<>();
        surveyTemplateList
                .stream()
                .forEach(surveyTemplate -> companySurveyList.addAll(surveyTemplate.getCompanySurvey()));

        return companySurveyList
                .stream()
                .map(companySurvey -> CategoryScoreDto.builder()
                        .companyName(companySurvey.getCompany().getName())
                        .companySurveyId(companySurvey.getId())
                        .score(statisticsService.getAverageScoreForCompanySurvey(companySurvey.getId()))
                        .build())
                .collect(Collectors.toList());
    }


    //getCategoryById() +
    //createCategory() +
    //deleteCategory() +
    //updateCategory() +

    //dodatkowo
    //getCategories() +
}
