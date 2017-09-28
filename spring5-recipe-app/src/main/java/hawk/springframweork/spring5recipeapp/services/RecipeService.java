package hawk.springframweork.spring5recipeapp.services;

import java.util.Set;

import hawk.springframweork.spring5recipeapp.domain.Recipe;

public interface RecipeService {
	Set <Recipe> getRecipes();
}
