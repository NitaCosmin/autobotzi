package autobotzi.project.skill;

import autobotzi.project.skill.dto.ProjectSkillsDto;
import autobotzi.project.skill.dto.ProjectSkillsResponseSkills;

import java.util.List;

public interface ProjectSkillsService {
    ProjectSkills addSkillToProject(ProjectSkillsDto projectSkillsDto);
    ProjectSkills updateSkillInProject(ProjectSkillsDto projectSkillsDto);
    void getSkillsByProject(ProjectSkillsDto projectSkillsDto);
    void deleteSkillFromProject(String skills);
}
