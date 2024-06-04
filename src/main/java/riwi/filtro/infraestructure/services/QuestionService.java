package riwi.filtro.infraestructure.services;

import java.util.List;

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
import riwi.filtro.domain.repositories.OptionQuestionRepository;
import riwi.filtro.domain.repositories.QuestionRepository;
import riwi.filtro.domain.repositories.SurveyRespository;
import riwi.filtro.infraestructure.abstractservices.IQuestionService;
import riwi.filtro.utils.enums.QusetionType;
import riwi.filtro.utils.exceptcions.IdNotFoundException;
import riwi.filtro.utils.exceptcions.OptionNotFoundException;

@Service
@Transactional
@AllArgsConstructor
public class QuestionService implements IQuestionService {

    @Autowired
    private final QuestionRepository questionRespository;

    @Autowired
    private final SurveyRespository surveyRespository;

    @Autowired
    private final OptionQuestionRepository optionQuestionRepository;

    @Override
    public Page<QuestionResponse> getAll(int page, int size) {
        PageRequest pagination = PageRequest.of(page, size);
        Page<QuestionResponse> response = questionRespository.findAll(pagination)
                .map(questionEntity -> {
                    QuestionResponse questionresponse = convertQuestionToQuestionResponse(questionEntity);
                    questionresponse.setSurvey(convertSurveyToSurveyResponse(questionEntity.getSurvey()));
                    if(questionEntity.getType() == QusetionType.CLOSED ){
                        questionresponse.setOptions(null);
                    }else{
                       List<OptionQuestion> options = questionEntity.getOptions();
                       questionresponse.setOptions(options.stream().map(e -> convertOptionToOptionQuestionResponse(e)).toList());
                    }
                    return questionresponse;
                });
        return response;
    }

    @Override
    public QuestionResponse create(QuestionRequest request) {
        boolean IsEmpty = true;
        if(request.getOptions() == null){
            IsEmpty = true;
        }else if(request.getOptions().isEmpty()){
            IsEmpty = true;
        }else{
            IsEmpty = false;
        }        
        Question entityforsaving = convertQuestionRequestToEntity(request);
        if (request.getType() == QusetionType.CLOSED) {
            Question savedEntity = questionRespository.save(entityforsaving);
            QuestionResponse response = convertQuestionToQuestionResponse(savedEntity);
            response.setSurvey(convertSurveyToSurveyResponse(savedEntity.getSurvey()));
            return response;              
        } else {
            if(request.getOptions() !=null || !IsEmpty){
                Question savedEntity = questionRespository.save(entityforsaving);
                QuestionResponse response = convertQuestionToQuestionResponse(savedEntity);
                response.setSurvey(convertSurveyToSurveyResponse(savedEntity.getSurvey()));
                List<OptionQuestionRequest> options = request.getOptions();
                List<OptionQuestion> optionsForSave = options.stream()
                        .map(e -> convertOptionReqToOption(e, savedEntity))
                        .toList();
                List<OptionQuestion> saved = optionQuestionRepository.saveAll(optionsForSave);
                response.setOptions(saved.stream().map(e -> convertOptionToOptionQuestionResponse(e)).toList());
                return response;
            }else{
                throw new OptionNotFoundException("Question");
            }
        }
    }

    @Override
    public QuestionResponse update(QuestionRequest request, Integer id) {
        questionRespository.findById(id).orElseThrow(()-> new IdNotFoundException("Question"));
        boolean IsEmpty = true;
        if(request.getOptions() == null){
            IsEmpty = true;
        }else if(request.getOptions().isEmpty()){
            IsEmpty = true;
        }else{
            IsEmpty = false;
        }    
        Question entityforsaving = convertQuestionRequestToEntity(request);
        if (request.getType() == QusetionType.CLOSED) {
            Question savedEntity = questionRespository.save(entityforsaving);
            QuestionResponse response = convertQuestionToQuestionResponse(savedEntity);
            response.setSurvey(convertSurveyToSurveyResponse(savedEntity.getSurvey()));
            return response;              
        } else {
            if(request.getOptions() !=null || !IsEmpty){
                Question savedEntity = questionRespository.save(entityforsaving);
                QuestionResponse response = convertQuestionToQuestionResponse(savedEntity);
                response.setSurvey(convertSurveyToSurveyResponse(savedEntity.getSurvey()));
                List<OptionQuestionRequest> options = request.getOptions();
                List<OptionQuestion> optionsForSave = options.stream()
                        .map(e -> convertOptionReqToOption(e, savedEntity))
                        .toList();
                List<OptionQuestion> saved = optionQuestionRepository.saveAll(optionsForSave);
                response.setOptions(saved.stream().map(e -> convertOptionToOptionQuestionResponse(e)).toList());
                return response;
            }else{
                throw new OptionNotFoundException("Question");
            }
        }
    }

    @Override
    public void delete(Integer id) {
        questionRespository.findById(id).orElseThrow(() -> new IdNotFoundException("Question"));
        questionRespository.deleteById(id);
    }

    @Override
    public QuestionResponse getById(Integer id) {
        Question questionEntity = questionRespository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Question"));
         QuestionResponse response = convertQuestionToQuestionResponse(questionEntity);
         response.setSurvey(convertSurveyToSurveyResponse(questionEntity.getSurvey()));
         if(questionEntity.getType() == QusetionType.CLOSED ){
            response.setOptions(null);
         }else{
            List<OptionQuestion> options = questionEntity.getOptions();
            response.setOptions(options.stream().map(e -> convertOptionToOptionQuestionResponse(e)).toList());
         }
         return response;
    }


    @Override
    public QuestionResponse patchById(Integer id, String descripcion){
        Question responeInit = questionRespository.findById(id).orElseThrow(()-> new IdNotFoundException("Question"));
        responeInit.setText(descripcion);
        Question result = questionRespository.save(responeInit);
        QuestionResponse response = convertQuestionToQuestionResponse(result);
        response.setSurvey(convertSurveyToSurveyResponse(result.getSurvey()));
        if(result.getType() == QusetionType.CLOSED ){
           response.setOptions(null);
        }else{
           List<OptionQuestion> options = result.getOptions();
           response.setOptions(options.stream().map(e -> convertOptionToOptionQuestionResponse(e)).toList());
        }
        return response;
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
