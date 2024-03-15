package autobotzi.skills;

import autobotzi.skills.dto.SkillsDto;

import java.util.List;

public interface SkillsService {

     Skills updateSkill(SkillsDto skillsDto,String name);
     Skills addSkill(SkillsDto skillsDto, String email);
     SkillsDto getSkill(String name);
     List<SkillsDto> getSkills();
     void deleteSkill(String name,String email);
}
