package autobotzi.project.roles.impl;

import autobotzi.project.roles.ProjectRolesRepository;
import autobotzi.project.roles.ProjectRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectRolesServiceImpl implements ProjectRolesService {

    private final ProjectRolesRepository projectRolesRepository;
}
