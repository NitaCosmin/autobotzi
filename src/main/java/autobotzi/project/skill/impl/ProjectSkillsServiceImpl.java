package autobotzi.project.skill.impl;

import autobotzi.project.ProjectsRepository;
import autobotzi.project.skill.ProjectSkills;
import autobotzi.project.skill.ProjectSkillsRepository;
import autobotzi.project.skill.ProjectSkillsService;
import autobotzi.project.skill.dto.ProjectSkillsDto;
import autobotzi.project.skill.dto.ProjectSkillsResponseSkills;
import autobotzi.skills.SkillsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectSkillsServiceImpl implements ProjectSkillsService {

    private final ProjectSkillsRepository projectSkillsRepository;
    private final SkillsRepository skillsRepository;
    private final ProjectsRepository projectsRepository;

    public ProjectSkills addSkillToProject(ProjectSkillsDto projectSkillsDto) {
        return projectSkillsRepository.save(skillsRepository
                .findByName(projectSkillsDto.getSkillName())
                .map(skill -> ProjectSkills.builder()
                        .skill(skill)
                        .level(projectSkillsDto.getLevel())
                        .project(projectsRepository.findByName(projectSkillsDto.getProjectName())
                                .orElseThrow(() -> new IllegalArgumentException("Project not found")))
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("Skill not found")
                ));
    }

    public ProjectSkills updateSkillInProject(ProjectSkillsDto projectSkillsDto) {
        return projectSkillsRepository.save(skillsRepository
                .findByName(projectSkillsDto.getSkillName())
                .map(skill -> projectSkillsRepository.findBySkill(skill)
                        .map(projectSkills -> ProjectSkills.builder()
                                .skill(skill)
                                .level(projectSkillsDto.getLevel())
                                .project(projectsRepository.findByName(projectSkillsDto.getProjectName())
                                        .orElseThrow(() -> new IllegalArgumentException("Project not found")))
                                .build())
                        .orElseThrow(() -> new IllegalArgumentException("Project skill not found")))
                .orElseThrow(() -> new IllegalArgumentException("Skill not found")
                ));
    }

    public void getSkillsByProject(ProjectSkillsDto projectSkillsDto) {
        projectSkillsRepository.findByProject(projectsRepository.findByName(projectSkillsDto.getProjectName())
                        .orElseThrow(() -> new IllegalArgumentException("Project not found")))
                .stream()
                .map(projectSkills -> ProjectSkillsResponseSkills.builder()
                        .skillName(projectSkills.getSkill().getName())
                        .level(projectSkills.getLevel())
                        .build())
                .collect(Collectors.toList());
    }

    public void deleteSkillFromProject(String skills) {
        projectSkillsRepository.delete(skillsRepository.findByName(skills)
                .map(skill -> projectSkillsRepository.findBySkill(skill)
                        .orElseThrow(() -> new IllegalArgumentException("Project skill not found")))
                .orElseThrow(() -> new IllegalArgumentException("Skill not found")
                ));


    }


}
