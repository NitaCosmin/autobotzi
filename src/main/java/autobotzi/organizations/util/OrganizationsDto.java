package autobotzi.organizations.util;

import autobotzi.organizations.Organizations;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class OrganizationsDto  {

    private String name;

    private String address;

}
