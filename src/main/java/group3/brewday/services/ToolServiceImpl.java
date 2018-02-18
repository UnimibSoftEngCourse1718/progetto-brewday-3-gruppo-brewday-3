package group3.brewday.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group3.brewday.models.Tool;
import group3.brewday.repositories.ToolRepository;

@Service
public class ToolServiceImpl implements ToolService {
    private ToolRepository toolRepository;

    @Autowired
    public void setToolRepository(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    @Override
    public List<Tool> listAllTools() {
        return toolRepository.findAll();
    }

    @Override
    public Tool getToolById(Long id) {
        return toolRepository.findOne(id);
    }

    @Override
    public Tool saveTool(Tool tool) {
        return toolRepository.save(tool);
    }
    
    @Override
    public void deleteTool(Long id) {
         toolRepository.delete(id);
         return;
    }
    
}