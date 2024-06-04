package riwi.filtro.infraestructure.abstractservices;

import riwi.filtro.api.dtos.reqest.SurveyRequest;
import riwi.filtro.api.dtos.response.SurveyResponse;

public interface ISurveyService extends CrudAbstractService<SurveyRequest, SurveyResponse, Integer> {
}
