package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusTrue {

    LOGIN_STATUS_TURE(200, "로그인 성공"),
    REGISTER_STATUS_TRUE(200, "회원가입 성공"),
    UPDATE_STATUS_TRUE(200, "업데이트 성공"),
    PASSWORD_CHANGE_STATUS_TRUE(200, "비밀번호 변경 성공"),
    DELETE_STATUS_TRUE(200, "회원탈퇴 성공"),
    LOGOUT_STATUS_TRUE(200, "로그아웃 성공" );

    private final Integer status;
    private final String statusMessage;
}
