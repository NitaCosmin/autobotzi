package autobotzi.skills.endorsements.impl;

import autobotzi.skills.endorsements.SkillEndorsements;
import autobotzi.skills.endorsements.SkillEndorsementsRepository;
import autobotzi.skills.endorsements.SkillEndorsementsService;
import autobotzi.skills.endorsements.dto.SkillEndorsementsDto;
import autobotzi.user.UserRepository;
import autobotzi.user.skill.UserSkillsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillEndorsementsServiceImpl implements SkillEndorsementsService {

    private final SkillEndorsementsRepository skillEndorsementsRepository;
    private final UserRepository userRepository;
    private final UserSkillsRepository userSkillsRepository;

    @Transactional
    public SkillEndorsements addEndorsementToUserSkill(String email, SkillEndorsementsDto skillEndorsementsDto) {
        return skillEndorsementsRepository.save(SkillEndorsements.builder()
                .title(skillEndorsementsDto.getTitle())
                .description(skillEndorsementsDto.getDescription())
                .project(skillEndorsementsDto.getProjectLink())
                .userSkill(userSkillsRepository
                        .findByUser(userRepository
                                .findByEmail(email).orElseThrow())
                        .orElseThrow(() ->
                                new IllegalArgumentException("userskill not found")))
                .build());
    }

    @Transactional
    public List<SkillEndorsementsDto> getAllEndorsementsOfUser(String email) {
        return skillEndorsementsRepository.findAll().stream()
                .filter(skillEndorsements -> skillEndorsements.getUserSkill().getUser().getEmail().equals(email))
                .map(skillEndorsements -> SkillEndorsementsDto.builder()
                        .title(skillEndorsements.getTitle())
                        .description(skillEndorsements.getDescription())
                        .projectLink(skillEndorsements.getProject())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public SkillEndorsements updateSkillEndorsementsByTitle(String email, String title, SkillEndorsementsDto skillEndorsementsDto) {
        return skillEndorsementsRepository.findByUserSkill(userSkillsRepository
                        .findByUser(userRepository
                                .findByEmail(email).orElseThrow())
                        .orElseThrow(() ->
                                new IllegalArgumentException("userskill not found")))
                .map(skillEndorsements -> {
                    skillEndorsements.setTitle(skillEndorsementsDto.getTitle());
                    skillEndorsements.setDescription(skillEndorsementsDto.getDescription());
                    skillEndorsements.setProject(skillEndorsementsDto.getProjectLink());
                    return skillEndorsementsRepository.save(skillEndorsements);
                }).orElseThrow(() -> new IllegalArgumentException("userskill not found"));
    }

    @Transactional
    public void deleteSkillEndorsementsByTitle(String email, String title) {
        skillEndorsementsRepository.delete(SkillEndorsements.builder()
                .title(title)
                .userSkill(userSkillsRepository
                        .findByUser(userRepository
                                .findByEmail(email).orElseThrow())
                        .orElseThrow(() ->
                                new IllegalArgumentException("userskill not found")))
                .build());
    }

}
