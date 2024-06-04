package riwi.filtro.api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import riwi.filtro.utils.enums.StatusEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OptionQuestionResponse {
    private int id;
    private String text;
    private StatusEnum status;
}
