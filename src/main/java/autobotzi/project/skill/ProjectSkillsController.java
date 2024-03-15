package autobotzi.project.skill;

import autobotzi.project.skill.dto.ProjectSkillsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project-skills")
@RequiredArgsConstructor
public class ProjectSkillsController {

    private final ProjectSkillsService projectSkillsService;

    @PostMapping
    public ProjectSkills addSkillToProject(@RequestBody ProjectSkillsDto projectSkillsDto) {
        return projectSkillsService.addSkillToProject(projectSkillsDto);
    }
    @PutMapping
    public ProjectSkills updateSkillInProject(@RequestBody ProjectSkillsDto projectSkillsDto) {
        return projectSkillsService.updateSkillInProject(projectSkillsDto);
    }
    @GetMapping
    public void getSkillsByProject(@RequestBody ProjectSkillsDto projectSkillsDto) {
        projectSkillsService.getSkillsByProject(projectSkillsDto);
    }
    @DeleteMapping
    public void deleteSkillFromProject(@RequestParam String skills) {
        projectSkillsService.deleteSkillFromProject(skills);
    }
}
