package riwi.filtro.api.dtos.reqest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import riwi.filtro.utils.enums.StatusEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OptionQuestionRequest {
    private int id;
    private String text;
    private StatusEnum status; 
}
