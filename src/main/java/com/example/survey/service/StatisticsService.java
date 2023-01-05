package com.example.survey.service;

import com.example.survey.dto.AverageScoreForSurveyDto;
import com.example.survey.exception.NoSuchCompanyExistsException;
import com.example.survey.exception.NoSuchCompanySurveyExistsException;
import com.example.survey.exception.NoSuchRegisteredUserException;
import com.example.survey.model.Company;
import com.example.survey.model.CompanySurvey;
import com.example.survey.model.RegisteredUser;
import com.example.survey.model.SurveyAnswer;
import com.example.survey.repository.CompanyRepository;
import com.example.survey.repository.CompanySurveyRepository;
import com.example.survey.repository.RegisteredUserRepository;
import com.example.survey.repository.SurveyAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private SurveyService surveyService;

    private final CompanySurveyRepository companySurveyRepository;

    private final CompanyRepository companyRepository;

    private final RegisteredUserRepository registeredUserRepository;

    private final SurveyAnswerRepository surveyAnswerRepository;

    private CompanyService companyService;

    public String getAverageScoreForSurvey(AverageScoreForSurveyDto averageScoreForSurveyDto) {
        RegisteredUser registeredUser = registeredUserRepository.findById(averageScoreForSurveyDto.idUser())
                .orElseThrow(() -> new NoSuchRegisteredUserException(
                        "No registered user present with id: "+averageScoreForSurveyDto.idUser()));
        CompanySurvey companySurvey = companySurveyRepository.findById(averageScoreForSurveyDto.idCompanySurvey())
                .orElseThrow(() -> new NoSuchCompanySurveyExistsException(
                        "No such company survey with id = "+averageScoreForSurveyDto.idCompanySurvey()+" exists"));

        Double averageScore = surveyAnswerRepository.findAll().stream()
                .filter(surveyAnswer -> surveyAnswer.getCompanySurvey().equals(companySurvey)
                        && surveyAnswer.getIdUser().equals(registeredUser))
                .collect(Collectors.toSet())
                .stream()
                .mapToDouble(surveyAnswer -> surveyAnswer.getRating())
                .average()
                .orElse(0.0);

        DecimalFormat df = new DecimalFormat("#.#");
        return df.format(averageScore);

        //podajemy idUser i idCompanySurvey
        //zapisujemy pasujące odpowiedzi do setu
        //(lepiej niż do listy bo stała złożoność dodawania, teoretycznie nasza lista będzie mała ale tak czy inaczej)
        //wyciągamy rating i liczymy średnią
        //zwracamy jako Double z jednym miejscem po przecinku

        //zwrócone jako String
    }

    public String getAverageScoreForCompanySurvey(Long id) {
        CompanySurvey companySurvey = companySurveyRepository.findById(id)
                .orElseThrow(() -> new NoSuchCompanySurveyExistsException(
                        "No such company survey with id = "+id+" exists"));

        Double averageScore = surveyAnswerRepository.findAll().stream()
                .filter(surveyAnswer -> surveyAnswer.getCompanySurvey().equals(companySurvey))
                .collect(Collectors.toSet())
                .stream()
                .mapToDouble(surveyAnswer -> surveyAnswer.getRating())
                .average()
                .orElse(0.0);

        DecimalFormat df = new DecimalFormat("#.#");
        return df.format(averageScore);

        //podajemy idCompanySurvey
        //zapisujemy pasujące odpowiedzi do setu
        //wyciągamy rating i liczymy średnią
        //zwracamy jako Double z jednym miejscem po przecinku

        //zwróceone jako String
    }

    public String getAverageScoreForCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new NoSuchCompanyExistsException(
                        "No company with id = "+id+" exists"));

        Double averageScore = surveyAnswerRepository.findAll().stream()
                .filter(surveyAnswer -> surveyAnswer.getCompanySurvey().getCompany().equals(company))
                .collect(Collectors.toSet())
                .stream()
                .mapToDouble(surveyAnswer -> surveyAnswer.getRating())
                .average()
                .orElse(0.0);

        DecimalFormat df = new DecimalFormat("#.0");
        return df.format(averageScore);
    }


    //getCompanySurveyScore() +
    //getAverageScoreForSurvey() +
    //getAverageScoreForCompany()
}
