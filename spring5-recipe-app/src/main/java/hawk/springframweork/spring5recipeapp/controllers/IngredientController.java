package hawk.springframweork.spring5recipeapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import hawk.springframweork.spring5recipeapp.services.RecipeService;

@Controller
public class IngredientController {

	private RecipeService recipeService;

	@Autowired
	public IngredientController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@GetMapping
	@RequestMapping("/recipe/{id}/ingredients")
	public String listIngredients(@PathVariable Long id, Model model) {
		
		model.addAttribute("recipe", recipeService.findCommandById(id));
		return "recipe/ingredient/list";
	}
	
}
