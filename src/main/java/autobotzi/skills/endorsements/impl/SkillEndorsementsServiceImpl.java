package autobotzi.skills.endorsements.impl;

import autobotzi.skills.endorsements.SkillEndorsements;
import autobotzi.skills.endorsements.SkillEndorsementsRepository;
import autobotzi.skills.endorsements.SkillEndorsementsService;
import autobotzi.skills.endorsements.dto.SkillEndorsementsDto;
import autobotzi.user.UserRepository;
import autobotzi.user.Users;
import autobotzi.user.skill.UserSkills;
import autobotzi.user.skill.UserSkillsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillEndorsementsServiceImpl implements SkillEndorsementsService {

    private final SkillEndorsementsRepository skillEndorsementsRepository;
    private final UserRepository userRepository;
    private final UserSkillsRepository userSkillsRepository;
    @Transactional
    public SkillEndorsementsDto addEndorsementToUserSkill(String email, SkillEndorsementsDto skillEndorsementsDto) {
        Users user = userRepository.findByEmail(email).orElseThrow(()->
                new IllegalArgumentException("user not found"));
        UserSkills userSkill = userSkillsRepository.findByUser(user).orElseThrow(()->
                new IllegalArgumentException("userskill not found"));

        SkillEndorsements skillEndorsements = SkillEndorsements.builder()
                .title(skillEndorsementsDto.getTitle())
                .description(skillEndorsementsDto.getDescription())
                .project(skillEndorsementsDto.getProjectLink())
                .userSkill(userSkill)
                .build();

        skillEndorsementsRepository.save(skillEndorsements);

        return skillEndorsementsDto;
    }
    @Transactional
    public List<SkillEndorsementsDto> getAllEndorsementsOfUser(String email) {
        Users user = userRepository.findByEmail(email).orElseThrow();
        UserSkills userSkill = userSkillsRepository.findByUser(user).orElseThrow();
        Optional<SkillEndorsements> skillEndorsements = skillEndorsementsRepository
                .findByUserSkill(userSkill);
        return skillEndorsements.stream()
                .map(skillEndorsement -> SkillEndorsementsDto.builder()
                        .title(skillEndorsement.getTitle())
                        .description(skillEndorsement.getDescription())
                        .projectLink(skillEndorsement.getProject())
                        .build())
                .collect(Collectors.toList());
    }
    @Transactional
    public SkillEndorsements updateSkillEndorsementsByTitle(String email, String title, SkillEndorsementsDto skillEndorsementsDto) {
        Users user = userRepository.findByEmail(email).orElseThrow(()->
                new IllegalArgumentException("user not found"));
        UserSkills userSkill = userSkillsRepository.findByUser(user).orElseThrow(()->
                new IllegalArgumentException("userskill not found"));
        SkillEndorsements skillEndorsements = skillEndorsementsRepository.findByUserSkill(userSkill).orElseThrow();
        skillEndorsements.setTitle(skillEndorsementsDto.getTitle());
        skillEndorsements.setDescription(skillEndorsementsDto.getDescription());
        skillEndorsements.setProject(skillEndorsementsDto.getProjectLink());
        return skillEndorsementsRepository.save(skillEndorsements);
    }
    @Transactional
    public void deleteSkillEndorsementsByTitle(String email, String title) {
       Users user= userRepository.findByEmail(email).orElseThrow(()
               ->new IllegalArgumentException("user not found"));
       UserSkills userSkills=userSkillsRepository.findByUser(user)
               .orElseThrow(()->new IllegalArgumentException("userskill not found"));
       SkillEndorsements skillEndorsement= skillEndorsementsRepository
               .findByUserSkill(userSkills).orElseThrow(()
               ->new IllegalArgumentException("userskill not found"));

       skillEndorsementsRepository.delete(skillEndorsement);
    }

}
