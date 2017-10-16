package hawk.springframweork.spring5recipeapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hawk.springframweork.spring5recipeapp.commands.IngredientCommand;
import hawk.springframweork.spring5recipeapp.commands.RecipeCommand;
import hawk.springframweork.spring5recipeapp.commands.UnitOfMeasureCommand;
import hawk.springframweork.spring5recipeapp.services.IngredientService;
import hawk.springframweork.spring5recipeapp.services.RecipeService;
import hawk.springframweork.spring5recipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IngredientController {

	private RecipeService recipeService;
	private IngredientService ingredientService;
	private UnitOfMeasureService uomService;
	
	@Autowired
	public IngredientController(RecipeService recipeService, IngredientService ingredientService
			, UnitOfMeasureService uomService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.uomService = uomService;
	}

	@GetMapping
	@RequestMapping("/recipe/{id}/ingredients")
	public String listIngredients(@PathVariable Long id, Model model) {
		
		model.addAttribute("recipe", recipeService.findCommandById(id));
		return "recipe/ingredient/list";
	}
	
	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
	public String showRecipeIngredient(@PathVariable Long recipeId,
									@PathVariable Long ingredientId, Model model) {
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
		return "recipe/ingredient/show";
	}
	
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/new")
	public String newRecipeIngredient(@PathVariable Long recipeId, Model model) {
		RecipeCommand recipeCommand = recipeService.findCommandById(recipeId);
		// TODO exception if recipeCommand == null
		
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setRecipeId(recipeId);
		model.addAttribute("ingredient", ingredientCommand);
		
		ingredientCommand.setUom(new UnitOfMeasureCommand());
		model.addAttribute("uomList", uomService.listAllUoms());
		
		return "recipe/ingredient/ingredientform";
	}
	
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
	public String updateRecipeIngredient(@PathVariable Long recipeId,
										@PathVariable Long id, Model model) {
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));
		model.addAttribute("uomList", uomService.listAllUoms());
		return "recipe/ingredient/ingredientform";
	}
	
    @PostMapping
    @RequestMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved receipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }
    
    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable Long recipeId, @PathVariable Long id) {
    	ingredientService.deleteById(recipeId, id);
    	
    	return "redirect:/recipe/"+recipeId+"/ingredients";
    }
}
