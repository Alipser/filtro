package riwi.filtro.utils.parsers;

import org.springframework.beans.BeanUtils;

import riwi.filtro.api.dtos.reqest.QuestionRequest;
import riwi.filtro.api.dtos.reqest.SurveyRequest;
import riwi.filtro.api.dtos.reqest.UserRequest;
import riwi.filtro.api.dtos.response.QuestionResponse;
import riwi.filtro.api.dtos.response.SurveyResponse;
import riwi.filtro.api.dtos.response.UserResponse;
import riwi.filtro.domain.entities.Question;
import riwi.filtro.domain.entities.Survey;
import riwi.filtro.domain.entities.User;

public class ParserRoma {

    public static UserResponse convertUserToUserResponse(User entity){
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(entity, response);
        response.setCreatedSurveys(entity.getCreatedSurveys().stream().map(e -> convertSurveyToSurveyResponse(e)).toList());
        return response;
    }

    
    public static User convertUserRequestToEntity(UserRequest request){
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return user;
    }

    public static QuestionResponse convertQuestionToQuestionResponse(Question entity){
        QuestionResponse response = new QuestionResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public static Question convertQuestionRequestToEntity(QuestionRequest request){
        Question question = new Question();
        BeanUtils.copyProperties(request, question);
        return question;
    }

    public static SurveyResponse convertSurveyToSurveyResponse(Survey entity){
        SurveyResponse response = new SurveyResponse();
        BeanUtils.copyProperties(entity, response);
        response.setQuestions(entity.getQuestions().stream().map(e->convertQuestionToQuestionResponse(e)).toList());
        return response;
    }

    public static Survey convertSurveyRequestToEntity(SurveyRequest request){
        Survey question = new Survey();
        BeanUtils.copyProperties(request, question);
        return question;
    }
}
