package riwi.filtro.api.dtos.reqest;


import riwi.filtro.utils.enums.QusetionType;
import riwi.filtro.utils.enums.StatusEnum;

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
    private String text;
    private QusetionType type;
    private StatusEnum status;
    private int surveyId;
    
}
