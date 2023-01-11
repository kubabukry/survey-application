package com.example.survey.service;

import static com.example.survey.mapper.QuestionMapper.mapQuestionToQuestionDto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.survey.dto.AnswerQuestionDto;
import com.example.survey.dto.AnswerSurveyDto;
import com.example.survey.dto.CompanySurveyVisibilityDto;
import com.example.survey.dto.CreateCompanySurveyDto;
import com.example.survey.dto.QuestionDto;
import com.example.survey.dto.SurveyDto;
import com.example.survey.dto.SurveyTemplateAddQuestionDto;
import com.example.survey.dto.SurveyTemplateCreationDto;
import com.example.survey.dto.SurveyTemplateDto;
import com.example.survey.exception.CompanyAlreadyUsedTemplateException;
import com.example.survey.exception.NoSuchCategoryExistsException;
import com.example.survey.exception.NoSuchCompanyExistsException;
import com.example.survey.exception.NoSuchCompanySurveyExistsException;
import com.example.survey.exception.NoSuchQuestionExistsException;
import com.example.survey.exception.NoSuchRegisteredUserException;
import com.example.survey.exception.NoSuchSurveySurveyAnswerExistsException;
import com.example.survey.exception.NoSuchSurveyTemplateExistsException;
import com.example.survey.exception.QuestionAlreadyAnsweredException;
import com.example.survey.exception.QuestionAlreadyExistsException;
import com.example.survey.exception.TitleAlreadyExistsException;
import com.example.survey.model.Category;
import com.example.survey.model.Company;
import com.example.survey.model.CompanySurvey;
import com.example.survey.model.Question;
import com.example.survey.model.RegisteredUser;
import com.example.survey.model.SurveyAnswer;
import com.example.survey.model.SurveyTemplate;
import com.example.survey.repository.CategoryRepository;
import com.example.survey.repository.CompanyRepository;
import com.example.survey.repository.CompanySurveyRepository;
import com.example.survey.repository.QuestionRepository;
import com.example.survey.repository.RegisteredUserRepository;
import com.example.survey.repository.SurveyAnswerRepository;
import com.example.survey.repository.SurveyTemplateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyAnswerRepository surveyAnswerRepository;

    private final CategoryRepository categoryRepository;

    private final RegisteredUserRepository registeredUserRepository;

    private final CompanyRepository companyRepository;

    private final QuestionRepository questionRepository;


    private final CompanySurveyRepository companySurveyRepository;

    private final SurveyTemplateRepository surveyTemplateRepository;

    //todo zmienić to po zaimplementowaniu logowania - ma pobierać
//  hardcodedUid do answerQuestion() dopóki nie ma logowania
//    private Long hardcodedUid = Long.valueOf(65);

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

    public CompanySurvey setCompanySurveyVisibility(CompanySurveyVisibilityDto companySurveyVisibilityDto) {
        CompanySurvey companySurvey = companySurveyRepository.findById(companySurveyVisibilityDto.id())
                .orElseThrow(() -> new NoSuchCompanySurveyExistsException("" +
                        "No such company survey with id = "+companySurveyVisibilityDto.id()+" exists"));
        companySurvey.setIsHidden(companySurveyVisibilityDto.isHidden());
        return companySurveyRepository.save(companySurvey);
    }

    public SurveyAnswer answerQuestion(AnswerQuestionDto answerQuestionDto) {
        Question question =  questionRepository.findById(answerQuestionDto.idQuestion())
                .orElseThrow(() -> new NoSuchQuestionExistsException("" +
                        "No such question with id = "+answerQuestionDto.idQuestion()+" exists"));
        RegisteredUser registeredUser = registeredUserRepository.findById(answerQuestionDto.idUser())
                .orElseThrow(() -> new NoSuchRegisteredUserException(
                        "No registered user present with id: "+answerQuestionDto.idUser()));
        CompanySurvey companySurvey = companySurveyRepository.findById(answerQuestionDto.idCompanySurvey())
                .orElseThrow(() -> new NoSuchCompanySurveyExistsException(
                        "No such company survey with id = "+answerQuestionDto.idCompanySurvey()+" exists"));
        Boolean alreadyAnswered = surveyAnswerRepository
                .existsByCompanySurveyAndIdUserAndQuestion(companySurvey, registeredUser, question);
        if(alreadyAnswered)
            throw new QuestionAlreadyAnsweredException(
                    "User id = "+registeredUser.getId()+" already answered this question for company survey id = "+companySurvey.getId());

        SurveyAnswer surveyAnswer = new SurveyAnswer();
        surveyAnswer.setCompanySurvey(companySurvey);
        surveyAnswer.setIdUser(registeredUser);
        surveyAnswer.setQuestion(question);
        surveyAnswer.setRating(answerQuestionDto.rating());

        return surveyAnswerRepository.save(surveyAnswer);
    }

    public SurveyAnswer getSingleSurveyAnswer(Long id) {
        return surveyAnswerRepository.findById(id)
                .orElseThrow(() -> new NoSuchSurveySurveyAnswerExistsException(
                        "No such survey answer with id = "+id+" exists"));
    }

    //todo get data with one query
    public SurveyDto getCompanySurvey(Long id) {
        CompanySurvey companySurvey = companySurveyRepository.findById(id)
                .orElseThrow(() -> new NoSuchCompanySurveyExistsException(
                        "No such company survey with id = "+id+" exists"));
        List<QuestionDto> questionDtoList = companySurvey
                .getSurveyTemplate()
                .getQuestionList()
                .stream()
                .map(question -> mapQuestionToQuestionDto(question))
                .collect(Collectors.toList());

            return new SurveyDto(
            companySurvey.getId(),
            companySurvey.getCompany().getName(),
            companySurvey.getSurveyTemplate().getCategory().getName(),
            questionDtoList);

    }

    //todo dodac wyjatki (user juz odpowiedzial na pytanie) i moze inne?
    public void answerSurvey(AnswerSurveyDto answerSurveyDto) {
        CompanySurvey companySurvey = companySurveyRepository.findById(answerSurveyDto.idCompanySurvey())
                .orElseThrow(() -> new NoSuchCompanySurveyExistsException(
                        "No such company survey with id = "+answerSurveyDto.idCompanySurvey()+" exists"));
        RegisteredUser registeredUser = registeredUserRepository.findById(answerSurveyDto.idUser())
                .orElseThrow(() -> new NoSuchRegisteredUserException(
                        "No registered user present with id: "+answerSurveyDto.idUser()));
        answerSurveyDto
                .answers()
                .stream()
                .forEach(answerBodyDto -> {
                    SurveyAnswer surveyAnswer = new SurveyAnswer();
                    surveyAnswer.setCompanySurvey(companySurvey);
                    surveyAnswer.setIdUser(registeredUser);
                    surveyAnswer.setQuestion(questionRepository.findById(answerBodyDto.idQuestion())
                            .orElseThrow(() -> new NoSuchQuestionExistsException(
                                    "No such question with id = " + answerBodyDto.idQuestion() + " exists")));
                    surveyAnswer.setRating(answerBodyDto.rating());
                    surveyAnswerRepository.save(surveyAnswer);
                });
    }

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

    //getSurveyAnswer(): getSingleSurveyAnswer() +

    //addSurveyToCompany(): createCompanySurvey() +

    //setCompanySurveyVisibility() +

    //dodatkowo
    //addQuestionToSurveyTemplate() +
    //answerQuestion() +
    //getSingleSurveyTemplate() +
    //getSurveyAnswersForCompanySurvey()?

}
