package autobotzi.project.roles;

import autobotzi.project.Projects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_roles")
public class ProjectRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Projects project;
}
