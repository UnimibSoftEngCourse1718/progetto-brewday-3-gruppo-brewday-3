package group3.brewday.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import group3.brewday.models.Tool;

public interface ToolRepository extends JpaRepository<Tool, Integer>{
}
