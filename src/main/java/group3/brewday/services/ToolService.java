package group3.brewday.services;

import java.util.List;

import group3.brewday.models.Tool;

public interface ToolService {
	
    List<Tool> listAllTools();

    Tool getToolById(Long id);

    Tool saveTool(Tool tool); 
    
    void deleteTool(Long id);
    
    
}
