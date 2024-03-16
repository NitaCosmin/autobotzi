package autobotzi.skills.endorsements;

import autobotzi.user.skill.UserSkills;
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
@Table(name = "skill_endorsements")
public class SkillEndorsements  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    @Lob
    @Column(columnDefinition = "text")
    private String description;

    private String project;

    @ManyToOne
    @JoinColumn(name = "user_skill_id")
    private UserSkills userSkill;
}
