package group3.brewday.services;

import group3.brewday.models.Tool;

public interface ToolService {
    Iterable<Tool> listAllProducts();

    Tool getToolById(Integer id);

    Tool saveTool(Tool tool); 
    
    void deleteTool(Integer id);
    
    
}
