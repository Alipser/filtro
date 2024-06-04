package riwi.filtro.infraestructure.services;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import riwi.filtro.api.dtos.reqest.QuestionRequest;
import riwi.filtro.api.dtos.response.QuestionResponse;
import riwi.filtro.domain.entities.Question;
import riwi.filtro.domain.repositories.QuestionRepository;
import riwi.filtro.infraestructure.abstractservices.IQuestionService;
import riwi.filtro.utils.exceptcions.IdNotFoundException;
import static riwi.filtro.utils.parsers.ParserRoma.*;


@Service
@Transactional
@AllArgsConstructor
public class QuestionService implements IQuestionService {

    @Autowired
    private final QuestionRepository QuestionRespository;

    @Override
    public Page<QuestionResponse> getAll(int page, int size) {
        PageRequest pagination = PageRequest.of(page, size);
        Page<QuestionResponse> response = QuestionRespository.findAll(pagination).map(a -> convertQuestionToQuestionResponse(a));
        return response;
    }

    @Override
    public QuestionResponse create(QuestionRequest request) {
     
        Question entityforsaving = convertQuestionRequestToEntity(request);
        QuestionResponse response = convertQuestionToQuestionResponse(QuestionRespository.save(entityforsaving));
        return response;
    }

    @Override
    public QuestionResponse update(QuestionRequest request, Integer id) {
     
        Question entityforsaving = convertQuestionRequestToEntity(request);
        QuestionResponse response = convertQuestionToQuestionResponse(QuestionRespository.save(entityforsaving));
        return response;
    }

    @Override
    public void delete(Integer id) {
        QuestionRespository.findById(id).orElseThrow(()->new IdNotFoundException("Question"));
        QuestionRespository.deleteById(id);
    }

    @Override
    public QuestionResponse getById(Integer id) {
        Question QuestionEntity = QuestionRespository.findById(id).orElseThrow(()->new IdNotFoundException("Question"));
        return convertQuestionToQuestionResponse(QuestionEntity);
    }


    

    

    
}

