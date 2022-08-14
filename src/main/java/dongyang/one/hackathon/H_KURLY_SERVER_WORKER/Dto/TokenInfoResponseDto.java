package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.Worker;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenInfoResponseDto {
    @JsonIgnore
    private Long id;
    private String userId;
    private String pw;
    private String name;
    private String pnum;
    private Character gender;
    private String hnum;

    public static TokenInfoResponseDto Response(@NotNull Worker worker) {
        return TokenInfoResponseDto.builder()
                .id(worker.getId())
                .userId(worker.getUserId())
                .pw(worker.getPw())
                .name(worker.getName())
                .pnum(worker.getPnum())
                .gender(worker.getGender())
                .hnum(worker.getHnum())
                .build();
    }
}
