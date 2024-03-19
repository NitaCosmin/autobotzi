package autobotzi.skills;

import autobotzi.skills.dto.SkillsDto;
import autobotzi.skills.dto.SkillsDtoDelete;
import autobotzi.skills.endorsements.SkillEndorsements;
import autobotzi.skills.endorsements.SkillEndorsementsService;
import autobotzi.skills.endorsements.dto.SkillEndorsementsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"
        , "http://atc-2024-autobotzi-fe-linux-web-app.azurewebsites.net/"
        , "https://frontend-jf48yfydc-eduard-ionel-eduards-projects.vercel.app/"
        , "https://front-autobotzi-c55123365842.herokuapp.com/"})
public class SkillsController {

    private final SkillsService skillsService;
    private final SkillEndorsementsService skillEndorsementsService;

    @GetMapping("/all")
    public List<SkillsDto> getAllSkills() {
        return skillsService.getSkills();
    }

    @GetMapping("/get")
    public SkillsDto getSkill(@RequestParam String name) {
        return skillsService.getSkill(name);
    }

    @PostMapping("/add")
    public Skills addSkill(@AuthenticationPrincipal UserDetails userDetails, SkillsDto skillsDto) {
        return skillsService.addSkill(skillsDto, userDetails.getUsername());
    }

    @PutMapping("/update")
    public Skills updateSkill(@RequestParam String name, SkillsDto skillsDto, @AuthenticationPrincipal UserDetails userDetails) {
        return skillsService.updateSkill(skillsDto, userDetails.getUsername(), name);
    }

    @DeleteMapping("/delete")
    public void deleteSkill(@RequestBody SkillsDtoDelete skillsDtoDelete) {
        skillsService.deleteSkill(skillsDtoDelete);
    }

    //================================================
    // Endorsements
    //================================================
    @PostMapping("/endorsements-add")
    public SkillEndorsements addEndorsementToUserSkill(@AuthenticationPrincipal UserDetails userDetails, @RequestBody SkillEndorsementsDto skillEndorsementsDto) {

        return skillEndorsementsService.addEndorsementToUserSkill(userDetails.getUsername(), skillEndorsementsDto);
    }

    @GetMapping("/endorsements-all")
    public List<SkillEndorsementsDto> getAllEndorsementsOfUser(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        return skillEndorsementsService.getAllEndorsementsOfUser(email);
    }

    @PutMapping("/endorsements-update/")
    public SkillEndorsements updateSkillEndorsementsByTitle(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String title, @RequestBody SkillEndorsementsDto skillEndorsementsDto) {
        String email = userDetails.getUsername();
        return skillEndorsementsService.updateSkillEndorsementsByTitle(email, title, skillEndorsementsDto);
    }

    @DeleteMapping("/endorsements-delete/")
    public void deleteSkillEndorsementsByTitle(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String title) {
        String email = userDetails.getUsername();
        skillEndorsementsService.deleteSkillEndorsementsByTitle(email, title);
    }
}
