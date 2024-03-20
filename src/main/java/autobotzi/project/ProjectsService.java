package autobotzi.project;

import autobotzi.project.dto.ProjectDepartmentDto;
import autobotzi.project.dto.ProjectUpdate;
import autobotzi.project.dto.ProjectsDateDto;
import autobotzi.project.dto.ProjectsDto;
import autobotzi.project.utils.Period;
import autobotzi.project.utils.Status;


import java.util.List;

public interface ProjectsService {
    Projects createProject(String email, ProjectsDto projectsDto);
    Projects updateProjectStatusByProjectName(String email, String name
            , ProjectUpdate projectUpdateDto);
    Projects addProjectManagerToProjectByEmail(String admin,String email, String name);
    Projects updateProjectDateByProjectName(String email, String name, ProjectsDateDto projectsDto);
//    List<ProjectDepartmentDto> getAllProjectsByDepartmentName(String departmentName);
    ProjectsDto findProjectByName(String name);
    List<ProjectsDto> findAllProjects();
    List<ProjectsDto> findAllProjectsByStatus(Status status);
    List<ProjectsDto> findAllProjectsByPeriod(Period period);
    List<Status> getAllStatus();
    List<Period> getAllPeriods();
    Projects deleteProject(String name);
    List<ProjectsDto> getAllProjectsFromOrganization(String email);

}
