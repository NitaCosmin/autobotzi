package autobotzi.skills.endorsements;

import autobotzi.skills.endorsements.dto.SkillEndorsementsDto;

import java.util.List;

public interface SkillEndorsementsService {
    List<SkillEndorsementsDto> getAllEndorsementsOfUser(String email);
    SkillEndorsementsDto addEndorsementToUserSkill(String email
            , SkillEndorsementsDto skillEndorsementsDto);
    SkillEndorsements updateSkillEndorsementsByTitle(String email,String title
            ,SkillEndorsementsDto skillEndorsementsDto);
    void deleteSkillEndorsementsByTitle(String email,String title);
}
