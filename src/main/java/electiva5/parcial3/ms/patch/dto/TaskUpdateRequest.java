package electiva5.parcial3.ms.patch.dto;

import electiva5.parcial3.ms.patch.model.TaskStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskUpdateRequest {
    private String title;
    private String description;
    private TaskStatus status;
}