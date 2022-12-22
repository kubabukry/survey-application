package com.example.survey.service;

import com.example.survey.dto.CreateCompanySurveyDto;
import com.example.survey.dto.SurveyTemplateAddQuestionDto;
import com.example.survey.dto.SurveyTemplateCreationDto;
import com.example.survey.dto.SurveyTemplateDto;
import com.example.survey.exception.*;
import com.example.survey.model.*;
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

    private final CompanyRepository companyRepository;

    private CompanyService companyService;

    private final QuestionRepository questionRepository;

    private QuestionService questionService;

    private final CompanySurveyRepository companySurveyRepository;

    private RegisteredUserService registeredUserService;

    private final SurveyTemplateRepository surveyTemplateRepository;

    private CategoryService categoryService;

    //todo zmienić to po zaimplementowaniu logowania - ma pobierać
//  hardcodedUid do answerQuestion() dopóki nie ma logowania
    private Long hardcodedUid = Long.valueOf(65);

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

    //todo zaimplementować sprawdzanie czy dana templatka jest w użyciu
    public void deleteSurveyTemplate(Long id) {
        SurveyTemplate surveyTemplate = surveyTemplateRepository.findById(id)
                .orElseThrow(() -> new NoSuchSurveyTemplateExistsException(
                        "No such survey template with id = "+id+" exists"));
        surveyTemplateRepository.deleteById(id);
    }

    public SurveyTemplate getSingleSurveyTemplate(Long id) {
        return surveyTemplateRepository.findById(id)
                .orElseThrow(() -> new NoSuchSurveyTemplateExistsException(
                        "No such survey template with id = "+id+" exists"));
    }

    public SurveyTemplate addQuestionToSurveyTemplate(SurveyTemplateAddQuestionDto surveyTemplateAddQuestionDto) {
        SurveyTemplate surveyTemplate = surveyTemplateRepository.findById(surveyTemplateAddQuestionDto.idSurveyTemplate())
                .orElseThrow(() -> new NoSuchSurveyTemplateExistsException(
                        "No such survey template with id = "+surveyTemplateAddQuestionDto.idSurveyTemplate()+" exists"));

        Question question = questionRepository.findById(surveyTemplateAddQuestionDto.idQuestion())
                .orElseThrow(() -> new NoSuchQuestionExistsException(
                        "No such question with id = "+surveyTemplateAddQuestionDto.idQuestion()+" exists"));

        Boolean questionAlreadyPresent = surveyTemplate.getQuestionList().contains(question);
        if(questionAlreadyPresent)
            throw new QuestionAlreadyExistsException(
                    "Question with id = "+question.getId()+" already present in this template");

        surveyTemplate.getQuestionList().add(question);
        return surveyTemplateRepository.save(surveyTemplate);
    }

    public CompanySurvey createCompanySurvey(CreateCompanySurveyDto createCompanySurveyDto) {
        Company company = companyRepository.findById(createCompanySurveyDto.idCompany())
                .orElseThrow(() -> new NoSuchCompanyExistsException(
                        "No such company with id = "+createCompanySurveyDto.idCompany()));
        SurveyTemplate surveyTemplate = surveyTemplateRepository.findById(createCompanySurveyDto.idSurveyTemplate())
                .orElseThrow(() -> new NoSuchSurveyTemplateExistsException(
                        "No such survey template with id = "+createCompanySurveyDto.idSurveyTemplate()+" exists"));

        Boolean companyAlreadyUsedTemplate = companySurveyRepository.existsByCompanyAndAndSurveyTemplate(company, surveyTemplate);
        if(companyAlreadyUsedTemplate)
            throw new CompanyAlreadyUsedTemplateException(
                    "Company already used template with id = "+surveyTemplate.getId());

        CompanySurvey companySurvey = new CompanySurvey();
        companySurvey.setSurveyTemplate(surveyTemplate);
        companySurvey.setCompany(company);
        companySurvey.setIsHidden(createCompanySurveyDto.isHidden());

        return companySurveyRepository.save(companySurvey);
    }


//    private Boolean surveyTemplateInUse(Long id){
//        Set<CompanySurvey> companySurveysUsingSurveyTemplate = companySurveyRepository.getRegisteredUsers()
//                .stream()
//                .filter(registeredUser -> registeredUser.getRole().getId() == id)
//                .collect(Collectors.toSet());
//        return !registeredUsersUsingRole.isEmpty();

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

    //deleteSurveyTemplate() +
    //exception jesli zawarte w CompanySurvey?

    //surveyTemplateMapper()+

    //surveyTemplateMapper() (List) +

    //addSurveyTemplate() +
    //jaka roznica miedzy createSurveyTemplate()?

    //getSurveyAnswer()

    //addSurveyToCompany(): createCompanySurvey() +

    //setCompanySurveyVisibility()
    //bedzie to robil user-wlasciciel firmy?

    //dodatkowo
    //addQuestionToSurveyTemplate() +
    //answerQuestion()
    //getSingleSurveyTemplate() +

}
