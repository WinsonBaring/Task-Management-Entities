package TaskManagement.example.TaskManagementAPI.Controller;

import TaskManagement.example.TaskManagementAPI.Exceptions.ResourceNotFoundException;
import TaskManagement.example.TaskManagementAPI.Service.CategoryService;
import TaskManagement.example.TaskManagementAPI.Service.TaskService;
import TaskManagement.example.TaskManagementAPI.model.Category;
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
    private TaskService taskService;

//    @Autowired
//    private CategoryService categoryService;

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Task>> getTasksByCategoryId(@PathVariable Long categoryId) {
        List<Task> tasks = taskService.getTasksByCategoryId(categoryId);
        return ResponseEntity.ok().body(tasks);
    }

//    @GetMapping
//    public List<Category> getAllCategories() {
//        return categoryService.getAllCategories();
//    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task savedTask = taskService.addTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        try {
            Task updatedTask = taskService.updateTask(taskId, task);
            return ResponseEntity.ok().body(updatedTask);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        try {
            taskService.deleteTask(taskId);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
