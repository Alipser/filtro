package riwi.filtro.api.dtos.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import riwi.filtro.domain.entities.Survey;
import riwi.filtro.utils.enums.QusetionType;
import riwi.filtro.utils.enums.StatusEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionResponse { 
    private int id;
    private String text;
    private QusetionType type;
    private StatusEnum status;
    private SurveyResponse survey;
    private List<OptionQuestionResponse> options;
}
