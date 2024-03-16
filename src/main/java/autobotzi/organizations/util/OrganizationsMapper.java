package autobotzi.organizations.util;

import autobotzi.organizations.Organizations;

public class OrganizationsMapper {

    public static OrganizationsDto mapToDto(Organizations organizations) {
        return OrganizationsDto.builder()
                .name(organizations.getName())
                .address(organizations.getAddress())
                .build();
    }

    public static Organizations mapToEntity(OrganizationsDto organizationsDto) {
        return Organizations.builder()
                .name(organizationsDto.getName())
                .address(organizationsDto.getAddress())
                .build();
    }

    public static Organizations mapToEntity(Organizations organizations, OrganizationsDto organizationsDto) {
        organizations.setName(organizationsDto.getName());
        organizations.setAddress(organizationsDto.getAddress());
        return organizations;
    }
}
