package riwi.filtro.infraestructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import riwi.filtro.api.dtos.reqest.OptionQuestionRequest;
import riwi.filtro.api.dtos.reqest.QuestionRequest;
import riwi.filtro.api.dtos.reqest.SurveyRequest;
import riwi.filtro.api.dtos.reqest.UserRequest;
import riwi.filtro.api.dtos.response.OptionQuestionResponse;
import riwi.filtro.api.dtos.response.QuestionResponse;
import riwi.filtro.api.dtos.response.SurveyResponse;
import riwi.filtro.api.dtos.response.UserResponse;
import riwi.filtro.domain.entities.OptionQuestion;
import riwi.filtro.domain.entities.Question;
import riwi.filtro.domain.entities.Survey;
import riwi.filtro.domain.entities.User;
import riwi.filtro.domain.repositories.SurveyRespository;
import riwi.filtro.infraestructure.abstractservices.ISurveyService;
import riwi.filtro.utils.exceptcions.ExistingException;
import riwi.filtro.utils.exceptcions.IdNotFoundException;


@Service
@Transactional
@AllArgsConstructor
public class SurveyService implements ISurveyService {

    @Autowired
    private final SurveyRespository surveyRespository;

    @Override
    public Page<SurveyResponse> getAll(int page, int size) {
        PageRequest pagination = PageRequest.of(page, size);
        Page<SurveyResponse> response = surveyRespository.findAll(pagination).map(survey ->{
            SurveyResponse surveyResponse = convertSurveyToSurveyResponse(survey);
            surveyResponse.setCreator(convertUserToUserResponse(survey.getCreator()));
            surveyResponse.setQuestions(survey.getQuestions().stream().map(
                question -> {
                    QuestionResponse questionResponse = convertQuestionToQuestionResponse(question);
                    questionResponse.setOptions(question.getOptions().stream().map(e -> convertOptionToOptionQuestionResponse(e)).toList());
                    return questionResponse;
                }
            ).toList());
            return surveyResponse;
        } );

        return response;
    }

    @Override
    public SurveyResponse create(SurveyRequest request) {
        Survey existing = surveyRespository.findByTitle(request.getTitle());
        Survey survey =  convertSurveyRequestToEntity(request);
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public SurveyResponse update(SurveyRequest request, Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public SurveyResponse getById(Integer id) {
        Survey survey = surveyRespository.findById(id).orElseThrow(()-> new IdNotFoundException("Survey"));
        SurveyResponse surveyResponse = convertSurveyToSurveyResponse(survey);
            surveyResponse.setCreator(convertUserToUserResponse(survey.getCreator()));
            surveyResponse.setQuestions(survey.getQuestions().stream().map(
                question -> {
                    QuestionResponse questionResponse = convertQuestionToQuestionResponse(question);
                    questionResponse.setOptions(question.getOptions().stream().map(e -> convertOptionToOptionQuestionResponse(e)).toList());
                    return questionResponse;
                }
            ).toList());
            return surveyResponse;
    }

     // PARSERS

     public UserResponse convertUserToUserResponse(User entity) {
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(entity, response);
        if (entity.getCreatedSurveys() != null) {
            response.setCreatedSurveys(
                    entity.getCreatedSurveys().stream().map(e -> convertSurveyToSurveyResponse(e)).toList());
        }
        return response;
    }

    public User convertUserRequestToEntity(UserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return user;
    }

    public QuestionResponse convertQuestionToQuestionResponse(Question entity) {
        QuestionResponse response = new QuestionResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public Question convertQuestionRequestToEntity(QuestionRequest request) {
        Question question = new Question();
        Survey survey = surveyRespository.findById(request.getSurveyId())
                .orElseThrow(() -> new IdNotFoundException("Survey"));
        BeanUtils.copyProperties(request, question);
        question.setSurvey(survey);
        return question;
    }

    public SurveyResponse convertSurveyToSurveyResponse(Survey entity) {
        SurveyResponse response = new SurveyResponse();
        BeanUtils.copyProperties(entity, response);
        response.setQuestions(entity.getQuestions().stream().map(e -> convertQuestionToQuestionResponse(e)).toList());
        response.setQuestions(null);
        return response;
    }

    public Survey convertSurveyRequestToEntity(SurveyRequest request) {
        Survey question = new Survey();
        BeanUtils.copyProperties(request, question);
        return question;
    }

    public OptionQuestion convertOptionReqToOption(OptionQuestionRequest request, Question question) {
        OptionQuestion optionQuestion = new OptionQuestion();
        BeanUtils.copyProperties(request, optionQuestion);
        optionQuestion.setQuestion(question);
        return optionQuestion;
    }

    public OptionQuestionResponse convertOptionToOptionQuestionResponse(OptionQuestion entity) {
        OptionQuestionResponse optionQuestionResponse = new OptionQuestionResponse();
        BeanUtils.copyProperties(entity, optionQuestionResponse);
        return optionQuestionResponse;
    }
   

    
}
