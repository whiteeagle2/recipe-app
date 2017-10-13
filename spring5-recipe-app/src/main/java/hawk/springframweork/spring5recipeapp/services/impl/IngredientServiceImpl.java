package hawk.springframweork.spring5recipeapp.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hawk.springframweork.spring5recipeapp.commands.IngredientCommand;
import hawk.springframweork.spring5recipeapp.converters.IngredientToIngredientCommand;
import hawk.springframweork.spring5recipeapp.domain.Recipe;
import hawk.springframweork.spring5recipeapp.repositories.RecipeRepository;
import hawk.springframweork.spring5recipeapp.services.IngredientService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final RecipeRepository recipeRepository;
	
	@Autowired
	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
			RecipeRepository recipeRepository) {
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.recipeRepository = recipeRepository;
	}


	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		if (!recipeOptional.isPresent()) {
			log.debug("Recipe id not found in igredientService, id= " +recipeId);
			// TODO error handling
		}
		Recipe recipe = recipeOptional.get();
		Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
		
		if(!ingredientCommandOptional.isPresent()) {
			log.debug("Ingredient not found in ingredientService id=" +ingredientId);
			// TODO error handling
		}
		
		return ingredientCommandOptional.get();

	}


}
