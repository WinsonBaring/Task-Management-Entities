package TaskManagement.example.TaskManagementAPI.Repository;


import TaskManagement.example.TaskManagementAPI.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
