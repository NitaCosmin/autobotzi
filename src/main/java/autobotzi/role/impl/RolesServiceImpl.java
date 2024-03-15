package autobotzi.role.impl;

import autobotzi.role.Roles;
import autobotzi.role.RolesRepository;
import autobotzi.role.RolesService;
import autobotzi.role.dto.RolesDto;
import autobotzi.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final RolesRepository rolesRepository;
    @PreAuthorize("hasRole('ADMIN')")
    public Roles addRole(RolesDto role) {
        return rolesRepository.save(Roles.builder()
                .name(role.getName())
                .build());
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<RolesDto> getRoles() {
        return rolesRepository.findAll().stream()
                .map(role -> RolesDto.builder()
                        .name(role.getName())
                        .build())
                .toList();
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void updateRole(RolesDto role, String name) {
        Roles roles = rolesRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
        roles.setName(role.getName());
        rolesRepository.save(roles);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRole(String name) {

        Roles roles = rolesRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        rolesRepository.delete(roles);
    }

}
