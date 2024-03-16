package autobotzi.project.dto;

import autobotzi.project.utils.Period;
import autobotzi.project.utils.Status;
import autobotzi.user.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ProjectsDto {

    private String name;
    private String description;
    private Period period;
    private Status projectStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadLine;

    private String technology;



}
