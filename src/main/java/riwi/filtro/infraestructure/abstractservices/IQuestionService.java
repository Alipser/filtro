package riwi.filtro.infraestructure.abstractservices;

import riwi.filtro.api.dtos.reqest.QuestionRequest;
import riwi.filtro.api.dtos.response.QuestionResponse;

public interface IQuestionService extends CrudAbstractService<QuestionRequest, QuestionResponse, Integer> {
    public QuestionResponse patchById(Integer id, String descripcion);
}
