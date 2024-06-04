package riwi.filtro.utils.parsers;

import org.springframework.beans.BeanUtils;

import riwi.filtro.api.dtos.reqest.UserRequest;
import riwi.filtro.api.dtos.response.UserResponse;
import riwi.filtro.domain.entities.User;

public class ParserRoma {

    public static UserResponse convertUserToUserResponse(User entity){
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    
    public static User convertUserRequestToEntity(UserRequest request){
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return user;
    }
}
