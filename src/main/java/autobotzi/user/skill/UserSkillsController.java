package autobotzi.user.skill;

import autobotzi.user.skill.dto.UserSkillsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-skills")
@RequiredArgsConstructor
public class UserSkillsController {

    private final UserSkillsService userSkillsService;

    @GetMapping
    public List<UserSkillsDto> getAllUserSkills() {
        return userSkillsService.getAllUserSkills();
    }
    @PutMapping
    public UserSkills addSkillToUser(@RequestParam String email, @RequestParam String skillName){
        return userSkillsService.addSkillToUser(email, skillName);
    }
    @PutMapping("update")
    public UserSkills updateSkillsByUserEmail(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UserSkillsDto userSkills){
        String email = userDetails.getUsername();
        return userSkillsService.updateSkillsByUserEmail(email, userSkills);
    }
    @PostMapping("validate")
    public UserSkills addValidationToUserSkill(@RequestParam String email){
        return userSkillsService.addValidationToUserSkill(email);
    }
    @GetMapping("validated")
    public List<UserSkillsDto> getAllValidatedSkills(){
        return userSkillsService.getAllValidatedSkills();
    }
    @GetMapping("non-validated")
    public List<UserSkillsDto> getAllNonValidatedSkills(){
        return userSkillsService.getAllNonValidatedSkills();
    }

}
