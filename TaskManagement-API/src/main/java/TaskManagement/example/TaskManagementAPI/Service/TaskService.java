package TaskManagement.example.TaskManagementAPI.Service;

import TaskManagement.example.TaskManagementAPI.Exceptions.ResourceNotFoundException;
import TaskManagement.example.TaskManagementAPI.Repository.TaskRepository;
import TaskManagement.example.TaskManagementAPI.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasksByCategoryId(Long categoryId) {
        return taskRepository.findByCategoryId(categoryId);
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long taskId, Task task) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));

        // Update task properties
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setCategory(task.getCategory());
        existingTask.setStatus(task.getStatus());

        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}