package autobotzi.user.skill;

import autobotzi.user.skill.dto.UserSkillsDto;

import java.util.List;

public interface UserSkillsService {


    List<UserSkillsDto> getAllUserSkills();

    UserSkills addSkillToUser(String email, String skillName);
    UserSkills updateSkillsByUserEmail(String email, UserSkillsDto userSkillsDto);
    UserSkills addValidationToUserSkill(String email);
    List<UserSkillsDto> getAllValidatedSkills();
    List<UserSkillsDto> getAllNonValidatedSkills();
}
