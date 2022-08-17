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
    LOGOUT_STATUS_TRUE(200, "로그아웃 성공" ),
    WORK_CHOICE_STATUS_TRUE(200, "업무 신청 성공" ),
    EDU_CHECK_STATUS_TRUE(200, "교육 수행 성공" ),
    CON_CHECK_STATUS_TRUE(200, "근로 계약 성공" ),
    START_WORK(200, "출근 완료" ),
    END_WORK(200, "퇴근 완료" ),

    NOTICE_ADD_STATUS_TRUE(200, "공지사항 등록 성공" ),
    UPDATE_ADD_STATUS_TRUE(200, "공지사항 수정 성공" ),
    DELETE_ADD_STATUS_TRUE(200, "공지사항 삭제 성공" );

    private final Integer status;
    private final String statusMessage;
}
