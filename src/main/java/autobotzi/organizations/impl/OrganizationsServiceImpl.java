package autobotzi.organizations.impl;

import autobotzi.organizations.Organizations;
import autobotzi.organizations.OrganizationsRepository;
import autobotzi.organizations.OrganizationsService;
import autobotzi.organizations.util.OrganizationsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationsServiceImpl implements OrganizationsService {

    private final OrganizationsRepository organizationsRepository;

    public List<OrganizationsDto> getAllOrganizations() {
        return organizationsRepository.findAll().stream()
                .map(organization -> new OrganizationsDto(organization.getName(), organization.getAddress()))
                .toList();
    }

    public Organizations addOrganization(OrganizationsDto organizationsDto) {
        Organizations organization = Organizations.builder()
                .name(organizationsDto.getName())
                .address(organizationsDto.getAddress())
                .build();
        return organizationsRepository.save(organization);
    }

    public void deleteOrganization(Long id) {
        organizationsRepository.deleteById(id);
    }
}
