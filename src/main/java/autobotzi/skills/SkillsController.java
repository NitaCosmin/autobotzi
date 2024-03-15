package autobotzi.skills;

import autobotzi.skills.dto.SkillsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
@RequiredArgsConstructor
public class SkillsController {

    private final SkillsService skillsService;

    @GetMapping("/all")
    public List<SkillsDto> getAllSkills() {
        return skillsService.getSkills();
    }
    @PostMapping("/add")
    public Skills addSkill(SkillsDto skillsDto, String email) {
        return skillsService.addSkill(skillsDto, email);
    }
    @PutMapping("/update")
    public Skills updateSkill(SkillsDto skillsDto, String name) {
        return skillsService.updateSkill(skillsDto, name);
    }
    @DeleteMapping("/delete")
    public void deleteSkill(String name, String email) {
        skillsService.deleteSkill(name, email);
    }

}
