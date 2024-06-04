package riwi.filtro.api.dtos.reqest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import riwi.filtro.utils.enums.StatusEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private int id;
    
    @NotBlank(message = "username must be not null")
    @Size(min =4 , max =35 , message = "username size must be mayor than 4 and less than 35 chars")
    private String userName;
    @NotBlank(message = "password  must be not null")
    @Size(min =4 , max =64 , message = "username size must be mayor than 4 and less than 64 chars")
    private String password;
    @NotBlank(message = "email  must be not null")
    @Size(min =4 , max =100 , message = "email size must be mayor than 4 and less than 100 chars")
    private String email;
    @NotNull(message = "Select a Roll")
    private StatusEnum status;
    
}
