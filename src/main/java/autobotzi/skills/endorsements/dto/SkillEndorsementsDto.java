package autobotzi.skills.endorsements.dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class SkillEndorsementsDto {

        private String title;
        @Lob
        private String description;
        private String projectLink;

}
