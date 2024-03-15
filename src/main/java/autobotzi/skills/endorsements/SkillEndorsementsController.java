package autobotzi.skills.endorsements;

import autobotzi.skills.endorsements.dto.SkillEndorsementsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill-endorsements")
@RequiredArgsConstructor
public class SkillEndorsementsController {

    private final SkillEndorsementsService skillEndorsementsService;

    @PostMapping("/add")
    public SkillEndorsementsDto addEndorsementToUserSkill(@AuthenticationPrincipal UserDetails userDetails, @RequestBody SkillEndorsementsDto skillEndorsementsDto) {
        String email = userDetails.getUsername();
        return skillEndorsementsService.addEndorsementToUserSkill(email, skillEndorsementsDto);
    }

    @GetMapping("/all")
    public List<SkillEndorsementsDto> getAllEndorsementsOfUser(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        return skillEndorsementsService.getAllEndorsementsOfUser(email);
    }

    @PutMapping("/update/")
    public SkillEndorsements updateSkillEndorsementsByTitle(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String title, @RequestBody SkillEndorsementsDto skillEndorsementsDto) {
        String email = userDetails.getUsername();
        return skillEndorsementsService.updateSkillEndorsementsByTitle(email, title, skillEndorsementsDto);
    }
    @DeleteMapping("/delete/")
    public void deleteSkillEndorsementsByTitle(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String title) {
        String email = userDetails.getUsername();
        skillEndorsementsService.deleteSkillEndorsementsByTitle(email, title);
    }
}
