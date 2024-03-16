package autobotzi.user.skill.impl;

import autobotzi.skills.SkillsRepository;
import autobotzi.user.UserRepository;
import autobotzi.user.skill.UserSkills;
import autobotzi.user.skill.UserSkillsRepository;
import autobotzi.user.skill.UserSkillsService;
import autobotzi.user.skill.dto.UserSkillsAssign;
import autobotzi.user.skill.dto.UserSkillsDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserSkillsServiceImpl implements UserSkillsService {

    private final UserSkillsRepository userSkillsRepository;
    private final UserRepository userRepository;
    private final SkillsRepository skillsRepository;


    @Transactional
    public List<UserSkillsDto> getAllUserSkills() {
        return userSkillsRepository.findAll().stream()
                .map(userSkills -> UserSkillsDto.builder()
                        .level(userSkills.getLevel())
                        .experience(userSkills.getExperience())
                        .endorsements(userSkills.getEndorsements())
                        .build())
                .collect(Collectors.toList());
    }
    @Transactional
    public UserSkills addSkillToUser(UserSkillsAssign userSkillsAssign) {

        return userSkillsRepository.save(UserSkills.builder()
                .user(userRepository.findByEmail(userSkillsAssign.getUser()).orElseThrow(() ->
                        new RuntimeException("User not found")))
                .skill(skillsRepository.findByName(userSkillsAssign.getSkill()).orElseThrow(() ->
                        new RuntimeException("Skill not found")))
                .build());
    }

    @Transactional
    public UserSkills updateSkillsByUserEmail(String email, UserSkillsDto userSkillsUpdate) {
        return userSkillsRepository.save(UserSkills.builder()
                .user(userRepository.findByEmail(email).orElseThrow(() ->
                        new RuntimeException("User not found")))
                .level(userSkillsUpdate.getLevel())
                .experience(userSkillsUpdate.getExperience())
                .endorsements(userSkillsUpdate.getEndorsements())
                .build());
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEPARTMENT_MANAGER')")
    public UserSkills addValidationToUserSkill(String email) {
        return userSkillsRepository.save(UserSkills.builder()
                .user(userRepository.findByEmail(email).orElseThrow(() ->
                        new RuntimeException("User not found")))
                .skill(skillsRepository.findByName(email).orElseThrow(() ->
                        new RuntimeException("Skill not found")))
                .validated(true)
                .build());
    }

    @Transactional

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEPARTMENT_MANAGER')")
    public List<UserSkillsDto> getAllValidatedSkills() {
        return userSkillsRepository.findByValidatedTrue().stream()
                .map(userSkills -> UserSkillsDto.builder()
                        .level(userSkills.getLevel())
                        .experience(userSkills.getExperience())
                        .endorsements(userSkills.getEndorsements())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEPARTMENT_MANAGER')")
    public List<UserSkillsDto> getAllNonValidatedSkills() {
        return userSkillsRepository.findByValidatedFalse().stream()
                .map(userSkills -> UserSkillsDto.builder()
                        .level(userSkills.getLevel())
                        .experience(userSkills.getExperience())
                        .endorsements(userSkills.getEndorsements())
                        .build())
                .collect(Collectors.toList());
    }

}
