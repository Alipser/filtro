package riwi.filtro.api.dtos.reqest;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import riwi.filtro.utils.enums.StatusEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SurveyRequest {
    private int id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private StatusEnum status;
    private int creatorId;
    
}
