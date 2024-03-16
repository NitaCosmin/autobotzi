package autobotzi.user.skill;

import autobotzi.skills.Skills;
import autobotzi.user.Users;
import autobotzi.user.skill.utils.Experience;
import autobotzi.user.skill.utils.Level;
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
@Table(name = "user_skills")

public class UserSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Level level;
    private Experience experience;
    @Lob
    @Column(columnDefinition = "text")
    private String endorsements;
    private Boolean validated;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skills skill;
}
