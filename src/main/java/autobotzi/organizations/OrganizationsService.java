package autobotzi.organizations;

import autobotzi.organizations.util.OrganizationsDto;

import java.util.List;

public interface OrganizationsService {
    List<OrganizationsDto> getAllOrganizations();

    void deleteOrganization(Long id);

    Organizations addOrganization(OrganizationsDto organizationsDto);
}
