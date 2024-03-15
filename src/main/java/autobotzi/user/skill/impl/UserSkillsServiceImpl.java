package autobotzi.user.skill.impl;

import autobotzi.skills.Skills;
import autobotzi.skills.SkillsRepository;
import autobotzi.user.UserRepository;
import autobotzi.user.Users;
import autobotzi.user.skill.UserSkills;
import autobotzi.user.skill.UserSkillsRepository;
import autobotzi.user.skill.UserSkillsService;
import autobotzi.user.skill.dto.UserSkillsDto;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
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

        @CacheEvict(value = "user-skills", allEntries = true)
        public List<UserSkillsDto> getAllUserSkills() {
            return userSkillsRepository.findAll().stream()
                    .map(userSkills -> UserSkillsDto.builder()
                            .level(userSkills.getLevel())
                            .experience(userSkills.getExperience())
                            .endorsements(userSkills.getEndorsements())
                            .build())
                    .collect(Collectors.toList());
        }
        public UserSkills addSkillToUser(String email, String skillName){
            Users user = userRepository.findByEmail(email).orElseThrow();
            Skills skill = skillsRepository.findByName(skillName).orElseThrow();

            UserSkills userSkills = UserSkills.builder()
                    .user(user)
                    .skill(skill)
                    .build();
            return userSkillsRepository.save(userSkills);
        }
    @Transactional
    @CacheEvict(value = "user-skills", allEntries = true)
    public UserSkills updateSkillsByUserEmail(String email, UserSkillsDto userSkillsUpdate){
        Users user = userRepository.findByEmail(email).orElseThrow();
        UserSkills userSkills = userSkillsRepository.findByUser(user).orElseThrow();
        userSkills.setLevel(userSkillsUpdate.getLevel());
        userSkills.setExperience(userSkillsUpdate.getExperience());
        userSkills.setEndorsements(userSkillsUpdate.getEndorsements());


        userSkills = userSkillsRepository.save(userSkills);

        return userSkills;
    }
    @Transactional
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEPARTMENT_MANAGER')")
    public UserSkills addValidationToUserSkill(String email){
        Users user = userRepository.findByEmail(email).orElseThrow();
        UserSkills userSkills = userSkillsRepository.findByUser(user).orElseThrow();
        userSkills.setValidated(true);
        userSkills = userSkillsRepository.save(userSkills);
        return userSkills;
    }
    @Transactional
    @CacheEvict(value = "user-skills", allEntries = true)
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
    @CacheEvict(value = "user-skills", allEntries = true)
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
