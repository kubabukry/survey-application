package com.example.survey.service;

import com.example.survey.dto.SurveyTemplateCreationDto;
import com.example.survey.dto.SurveyTemplateDto;
import com.example.survey.exception.NoSuchCategoryExistsException;
import com.example.survey.exception.NoSuchQuestionExistsException;
import com.example.survey.exception.NoSuchSurveyTemplateExistsException;
import com.example.survey.exception.TitleAlreadyExistsException;
import com.example.survey.model.Category;
import com.example.survey.model.SurveyTemplate;
import com.example.survey.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyAnswerRepository surveyAnswerRepository;

    private final CategoryRepository categoryRepository;

    private CompanyService companyService;

    private final QuestionRepository questionRepository;

    private QuestionService questionService;

    //CompanySurveyTemplateRepository na diagramie?
    private final CompanySurveyRepository companySurveyRepository;

    private RegisteredUserService registeredUserService;

    private final SurveyTemplateRepository surveyTemplateRepository;

    private CategoryService categoryService;

//    //hardcodedUid do answerQuestion() dopóki nie ma logowania
//    Long hardcodedUid = Long.valueOf(65);

    public List<SurveyTemplate> getSurveyTemplates(){
        return surveyTemplateRepository.findAll();
    }

    public SurveyTemplate createSurveyTemplate(SurveyTemplateCreationDto surveyTemplateCreationDto) {
        Boolean titleExists = surveyTemplateRepository.existsByTitle(surveyTemplateCreationDto.title());
        if(titleExists)
            throw new TitleAlreadyExistsException(
                    "Title: "+surveyTemplateCreationDto.title()+" already exists");
        
        Category category = categoryRepository.findById(surveyTemplateCreationDto.idCategory())
                .orElseThrow(() -> new NoSuchCategoryExistsException(
                        "No such category with id = "+surveyTemplateCreationDto.idCategory()+" exists"));

        SurveyTemplate surveyTemplate = new SurveyTemplate();
        return surveyTemplateMapper(
                surveyTemplate,
                category,
                surveyTemplateCreationDto.title(),
                surveyTemplateCreationDto.description(),
                surveyTemplateCreationDto.idQuestionList());
    }

    public SurveyTemplate updateSurveyTemplate(SurveyTemplateDto surveyTemplateDto) {
        SurveyTemplate surveyTemplate = surveyTemplateRepository.findById(surveyTemplateDto.id())
                .orElseThrow(() -> new NoSuchSurveyTemplateExistsException(
                        "No such survey template with id = "+surveyTemplateDto.id()+" exists"));

        Boolean titleExists = surveyTemplateRepository.existsByTitle(surveyTemplateDto.title());
        String thisTitle = surveyTemplate.getTitle();
        if(titleExists&&!thisTitle.equalsIgnoreCase(surveyTemplateDto.title()))
            throw new TitleAlreadyExistsException(
                    "Title: "+surveyTemplateDto.title()+" already exists");

        Category category = categoryRepository.findById(surveyTemplateDto.idCategory())
                .orElseThrow(() -> new NoSuchCategoryExistsException(
                        "No such category with id = "+surveyTemplateDto.idCategory()+" exists"));

        return surveyTemplateMapper(surveyTemplate,
                category,
                surveyTemplateDto.title(),
                surveyTemplateDto.description(),
                surveyTemplateDto.idQuestionList());
    }

    private SurveyTemplate surveyTemplateMapper(SurveyTemplate surveyTemplate,
                                                Category category,
                                                String title,
                                                String description,
                                                List<Long> longs) {
        surveyTemplate.setTitle(title);
        surveyTemplate.setDescription(description);
        surveyTemplate.setCategory(category);

        surveyTemplate.setQuestionList(longs
                .stream()
                .map(questionId -> questionRepository.findById(questionId)
                        .orElseThrow(()-> new NoSuchQuestionExistsException(
                                "No such question with id = "+questionId+" exists")))
                .collect(Collectors.toList()));

        return surveyTemplateRepository.save(surveyTemplate);
    }

//    public SurveyAnswer answerQuestion(AnswerQuestionDto answerQuestionDto) {
//        Question question =  questionRepository.findById(answerQuestionDto.idQuestion())
//                .orElseThrow(() -> new NoSuchQuestionExistsException("" +
//                        "No such question with id = "+answerQuestionDto.idQuestion()+" exists"));
//        SurveyAnswer surveyAnswer = new SurveyAnswer();
//        surveyAnswer.setIdUser(registeredUserService.getRegisteredUserById(hardcodedUid));
//        surveyAnswer.setQuestion(question);
//        //todo brakuje polaczenia miedzy company_survey a czym?
//        //surveyAnswer.setCompanySurvey();
//    }

    //createSurveyTemplate() +
    //bedzie to robil admin/moderator?

    //updateSurveyTemplate() +
    //bedzie to robil admin/moderator?

    //deleteSurveyTemplate()
    //exception jesli zawarte w CompanySurvey

    //surveyTemplateMapper()+

    //surveyTemplateMapper() (List)

    //addSurveyTemplate()
    //jaka roznica miedzy createSurveyTemplate()?

    //getSurveyAnswer()

    //addSurveyToCompany(): addSurveyTemplateToCompanySurvey()

    //setCompanySurveyVisibility()
    //bedzie to robil user-wlasciciel firmy?

    //dodatkowo
    //addQuestionToSurveyTemplate() ??
    //answerQuestion()

}
