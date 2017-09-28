package hawk.springframweork.spring5recipeapp.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import hawk.springframweork.spring5recipeapp.domain.Recipe;
import hawk.springframweork.spring5recipeapp.repositories.RecipeRepository;
import hawk.springframweork.spring5recipeapp.services.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes() {
		Set <Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

}
