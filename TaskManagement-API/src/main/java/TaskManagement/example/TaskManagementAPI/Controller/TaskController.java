package TaskManagement.example.TaskManagementAPI.Controller;


import TaskManagement.example.TaskManagementAPI.Exceptions.ResourceNotFoundException;
import TaskManagement.example.TaskManagementAPI.Repository.TaskRepository;
import TaskManagement.example.TaskManagementAPI.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/category/{categoryId}")
    public List<Task> getTasksByCategoryId(@PathVariable Long categoryId) {
        return taskRepository.findByCategoryId(categoryId);
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task savedTask = taskRepository.save(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));

        // Update task properties
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setCategory(task.getCategory());
        existingTask.setStatus(task.getStatus());

        Task updatedTask = taskRepository.save(existingTask);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        taskRepository.deleteById(taskId);
        return ResponseEntity.ok().build();
    }
}