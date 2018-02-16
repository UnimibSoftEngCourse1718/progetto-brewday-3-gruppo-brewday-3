package group3.brewday.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import group3.brewday.models.Tool;
import group3.brewday.services.ToolService;

@Controller
public class ToolFormController {
   
	@Autowired
	ToolService toolService;
	
	@PostMapping(value = "tool")
    public String saveProduct(Tool tool){

        toolService.saveTool(tool);

        return "redirect:/tool/" + tool.getId();
    }
	
    @RequestMapping("product/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product", toolService.getToolById(id));
        return "productshow";
    }
}

    
