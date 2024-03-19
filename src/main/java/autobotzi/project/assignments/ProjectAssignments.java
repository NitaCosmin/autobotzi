package autobotzi.project.assignments;

import autobotzi.project.Projects;
import autobotzi.project.assignments.utils.StatusAssignments;
import autobotzi.role.Roles;
import autobotzi.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "project_assignments")
public class ProjectAssignments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private StatusAssignments statusAssignments;

    @ManyToOne
    @JoinColumn(name = "project_id")

    private Projects project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;
}
