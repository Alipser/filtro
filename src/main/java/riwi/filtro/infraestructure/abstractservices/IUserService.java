package riwi.filtro.infraestructure.abstractservices;

import riwi.filtro.api.dtos.reqest.UserRequest;
import riwi.filtro.api.dtos.response.UserResponse;

public interface IUserService extends CrudAbstractService<UserRequest, UserResponse, Integer> {
}
