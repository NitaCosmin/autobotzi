package autobotzi.skills.impl;

import autobotzi.skills.Skills;
import autobotzi.skills.SkillsRepository;
import autobotzi.skills.SkillsService;
import autobotzi.skills.dto.SkillsDto;
import autobotzi.skills.dto.SkillsDtoDelete;
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
        return skillsRepository.save(Skills.builder()
                .name(skillsDto.getName())
                .description(skillsDto.getDescription())
                .category(skillsDto.getCategory())
                .user(usersRepository.findByEmail(email)
                        .orElseThrow(() -> new IllegalArgumentException("User not found")))
                .build());
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
        return skillsRepository.findByName(name)
                .map(skill -> SkillsDto.builder()
                        .name(skill.getName())
                        .description(skill.getDescription())
                        .category(skill.getCategory())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));
    }
       public Skills updateSkill(SkillsDto skillsDto,String email) {
               return skillsRepository.save(Skills.builder()
                       .name(skillsDto.getName())
                       .description(skillsDto.getDescription())
                       .category(skillsDto.getCategory())
                       .user(usersRepository.findByEmail(email)
                               .orElseThrow(() -> new IllegalArgumentException("User not found")))
                       .build());

       }
         public void deleteSkill(SkillsDtoDelete skillsDtoDelete) {
                    skillsRepository.delete(Skills.builder()
                            .name(skillsDtoDelete.getName())
                            .user(usersRepository.findByEmail(skillsDtoDelete.getUser())
                                    .orElseThrow(() -> new IllegalArgumentException("User not found")))
                            .build());

         }

}
