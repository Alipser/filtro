package riwi.filtro.api.dtos.reqest;


import riwi.filtro.utils.enums.QusetionType;
import riwi.filtro.utils.enums.StatusEnum;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionRequest {

    private int id;
    @NotBlank(message = "Content text can't be null")
    private String text;
    @NotBlank(message = "Content type can't be null")
    private QusetionType type;
    @NotNull(message = "Content Status can't be null")
    private StatusEnum status;
    @NotNull(message = "SurveyId can't be null")
    private int surveyId;
    
    private List<OptionQuestionRequest> options; 
    
}
