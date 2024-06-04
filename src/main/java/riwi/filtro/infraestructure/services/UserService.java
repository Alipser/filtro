package riwi.filtro.infraestructure.services;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import riwi.filtro.api.dtos.reqest.UserRequest;
import riwi.filtro.api.dtos.response.UserResponse;
import riwi.filtro.domain.entities.User;
import riwi.filtro.domain.repositories.UserRespository;
import riwi.filtro.infraestructure.abstractservices.IUserService;
import riwi.filtro.utils.exceptcions.IdNotFoundException;
import static riwi.filtro.utils.parsers.ParserRoma.*;


@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private final UserRespository userRespository;

    @Override
    public Page<UserResponse> getAll(int page, int size) {
        PageRequest pagination = PageRequest.of(page, size);
        Page<UserResponse> response = userRespository.findAll(pagination).map(a -> convertUserToUserResponse(a));
        return response;
    }

    @Override
    public UserResponse create(UserRequest request) {
     
        User entityforsaving = convertUserRequestToEntity(request);
        UserResponse response = convertUserToUserResponse(userRespository.save(entityforsaving));
        return response;
    }

    @Override
    public UserResponse update(UserRequest request, Integer id) {
     
        User entityforsaving = convertUserRequestToEntity(request);
        UserResponse response = convertUserToUserResponse(userRespository.save(entityforsaving));
        return response;
    }

    @Override
    public void delete(Integer id) {
        userRespository.findById(id).orElseThrow(()->new IdNotFoundException("User"));
        userRespository.deleteById(id);
    }

    @Override
    public UserResponse getById(Integer id) {
        User userEntity = userRespository.findById(id).orElseThrow(()->new IdNotFoundException("User"));
        return convertUserToUserResponse(userEntity);
    }


    

    

    
}

