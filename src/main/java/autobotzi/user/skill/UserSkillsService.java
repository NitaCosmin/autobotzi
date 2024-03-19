package autobotzi.user.skill;

import autobotzi.user.skill.dto.UserSkillsAssign;
import autobotzi.user.skill.dto.UserSkillsDto;

import java.util.List;

public interface UserSkillsService {


    List<UserSkillsDto> getAllUserSkills();

    UserSkills addSkillToUser(UserSkillsAssign userSkillsAssign);
    UserSkills updateSkillsByUserEmail(String email, UserSkillsDto userSkillsDto);
    UserSkills addValidationToUserSkill(String email);
    List<UserSkillsDto> getAllValidatedSkills();
    List<UserSkillsDto> getAllNonValidatedSkills();
    List<UserSkillsDto> getSkillsByUserEmail(String email);
}
