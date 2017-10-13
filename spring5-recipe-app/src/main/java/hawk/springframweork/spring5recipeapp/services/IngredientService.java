package hawk.springframweork.spring5recipeapp.services;

import hawk.springframweork.spring5recipeapp.commands.IngredientCommand;

public interface IngredientService {

	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
