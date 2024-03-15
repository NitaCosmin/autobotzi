package autobotzi.skills.impl;

import autobotzi.skills.Skills;
import autobotzi.skills.SkillsRepository;
import autobotzi.skills.SkillsService;
import autobotzi.skills.dto.SkillsDto;
import autobotzi.user.UserRepository;
import autobotzi.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillsServiceImpl implements SkillsService {

        private final SkillsRepository skillsRepository;
        private final UserRepository usersRepository;

       public Skills addSkill(SkillsDto skillsDto, String email) {
               Users user = usersRepository.findByEmail(email)
                       .orElseThrow(() -> new IllegalArgumentException("User not found"));
               Skills skill = Skills.builder()
                .name(skillsDto.getName())
                .description(skillsDto.getDescription())
                .category(skillsDto.getCategory())
                .user(user)
                .build();

        return skillsRepository.save(skill);
       }
       public List<SkillsDto>getSkills() {
               return skillsRepository.findAll().stream()
                       .map(skill -> SkillsDto.builder()
                               .name(skill.getName())
                               .description(skill.getDescription())
                               .category(skill.getCategory())
                               .build())
                       .toList();
       }
    public SkillsDto getSkill(String name) {
        Skills skill = skillsRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));

        return SkillsDto.builder()
                .name(skill.getName())
                .description(skill.getDescription())
                .category(skill.getCategory())
                .build();
    }
       public Skills updateSkill(SkillsDto skillsDto,String name) {
               Skills skill = skillsRepository.findByName(name)
                       .orElseThrow(() -> new IllegalArgumentException("Skill not found"));
               skill.setName(skillsDto.getName());
               skill.setDescription(skillsDto.getDescription());
               skill.setCategory(skillsDto.getCategory());
               return skillsRepository.save(skill);

       }
         public void deleteSkill(String name,String email) {
                 Users user = usersRepository.findByEmail(email)
                         .orElseThrow(() -> new IllegalArgumentException("User not found"));
                 Skills skill = skillsRepository.findByName(name)
                         .orElseThrow(() -> new IllegalArgumentException("Skill not found"));
                 skillsRepository.delete(skill);
         }

}
