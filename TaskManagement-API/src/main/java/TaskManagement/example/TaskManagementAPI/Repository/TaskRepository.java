package TaskManagement.example.TaskManagementAPI.Repository;

import TaskManagement.example.TaskManagementAPI.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.taskId = :taskId")
    List<Task> findByCategoryId(Long taskId);
}