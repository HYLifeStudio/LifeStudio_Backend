package lifestudio.backend.domain.studio.dto;

import lifestudio.backend.domain.studio.domain.OpeningTime;
import lifestudio.backend.domain.studio.domain.Option;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class OpeningTimeDto {

    @Data
    public static class Res {

        private Boolean monday;

        private Boolean tuesday;

        private Boolean wednesday;

        private Boolean thursday;

        private Boolean friday;

        private Boolean saturday;

        private Boolean sunday;

        private String openTime;

        private String closeTime;


        public Res(OpeningTime dto) {
            this.monday = dto.getMonday();
            this.tuesday = dto.getTuesday();
            this.wednesday = dto.getWednesday();
            this.thursday = dto.getThursday();
            this.friday = dto.getFriday();
            this.saturday = dto.getSaturday();
            this.sunday = dto.getSunday();
            this.openTime = dto.getOpenTime();
            this.closeTime = dto.getCloseTime();
        }
    }
}
