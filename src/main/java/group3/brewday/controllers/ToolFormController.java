package group3.brewday.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import group3.brewday.models.Tool;
import group3.brewday.services.ToolService;

@Controller
public class ToolFormController {
   
	@Autowired
	ToolService toolService;
	
    @GetMapping("/toolform")
    public String newTool(Model model, Authentication auth){
        model.addAttribute("tool", new Tool());
        model.addAttribute("username",auth.getName());
        return "toolform";
    }
	
	@PostMapping(value = "tool")
    public String saveProduct(Tool tool, Authentication auth){

		tool.setEmailUser(auth.getName());
        toolService.saveTool(tool);

        return "redirect:/tools";
    }
	
    @GetMapping(value = "/tools")
    public String list(Model model, Authentication auth){
    	List<Tool> allTools = toolService.listAllTools();
        model.addAttribute("tools", allTools.stream().filter(tool -> auth.getName().equals(tool.getEmailUser())).collect(Collectors.toList()));
        return "tools";
    }	
	
    @GetMapping("tool/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("tool", toolService.getToolById(id));
        return "toolform";
    }
    
    @DeleteMapping("tool/delete/{id}")
    public String deleteTool(@PathVariable Long id){
            toolService.deleteTool(id);
            return "redirect:/tools";
    }
    
}

    
