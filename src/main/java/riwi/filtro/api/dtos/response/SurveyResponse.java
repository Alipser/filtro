package riwi.filtro.api.dtos.response;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import riwi.filtro.utils.enums.StatusEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SurveyResponse {

    private int id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private StatusEnum status;
    private UserResponse creator;
    private List<QuestionResponse> questions;
}
