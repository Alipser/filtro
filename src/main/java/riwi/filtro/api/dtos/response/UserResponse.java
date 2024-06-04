package riwi.filtro.api.dtos.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import riwi.filtro.domain.entities.Survey;
import riwi.filtro.utils.enums.StatusEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private int id;
    private String userName;
    private String password;
    private String email;
    private StatusEnum status;
    private List<Survey> createdSurveys;
}
