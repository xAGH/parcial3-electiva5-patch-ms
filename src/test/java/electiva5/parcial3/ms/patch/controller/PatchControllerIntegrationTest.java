package electiva5.parcial3.ms.patch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import electiva5.parcial3.ms.patch.dto.TaskUpdateRequest;
import electiva5.parcial3.ms.patch.model.Task;
import electiva5.parcial3.ms.patch.repository.TaskRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PatchControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        taskRepository.deleteAll();
    }

    @Test
    void testUpdateTaskIntegration() throws Exception {
        Task task = new Task();
        task.setTitle("Tarea original");
        task.setDescription("Desc");
        Task saved = taskRepository.save(task);

        TaskUpdateRequest update = new TaskUpdateRequest();
        update.setTitle("Actualizada");

        mockMvc.perform(patch("/api/tasks/" + saved.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title", is("Actualizada")));
    }

    @Test
    void testUpdateTaskNotFoundIntegration() throws Exception {
        TaskUpdateRequest update = new TaskUpdateRequest();
        update.setTitle("No existe");

        mockMvc.perform(patch("/api/tasks/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isNotFound());
    }
}
