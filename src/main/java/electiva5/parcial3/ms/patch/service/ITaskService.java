package electiva5.parcial3.ms.patch.service;

import java.util.Optional;

import electiva5.parcial3.ms.patch.dto.TaskUpdateRequest;
import electiva5.parcial3.ms.patch.model.Task;

public interface ITaskService {
    Optional<Task> getTaskById(Long id);

    Task updateTask(Long id, TaskUpdateRequest taskrequest);
}
