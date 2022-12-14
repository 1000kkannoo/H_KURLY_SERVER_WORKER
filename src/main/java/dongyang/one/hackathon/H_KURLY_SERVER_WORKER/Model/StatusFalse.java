package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusFalse {
    JWT_CREDENTIALS_STATUS_FALSE(401,"로그인이 유효하지 않습니다."),
    PASSWORD_CHANGE_STATUS_FALSE(403, "비밀번호 변경 실패"),
    DELETE_STATUS_FALSE(403, "비밀번호가 일치하지 않습니다."),
    ERROR_CHOICE(403, "교육이 완료되지 않았거나 근로 계약서를 작성하지 않았습니다."),
    START_FAILURE(403, "현재 근무가 배치되지 않았습니다."),
    END_FAILURE(403, "출근을 하지 않았습니다.");

    private final Integer status;
    private final String statusMessage;
}
