package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto;

import lombok.*;

import javax.validation.constraints.NotNull;

public class NoticeDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class addNoticeRequest{
        private String title;
        private String text;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class updateNoticeRequest{
        private Long id;
        private String title;
        private String text;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class deleteNoticeRequest{
        private Long id;
    }
}
