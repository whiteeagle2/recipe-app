package hawk.springframweork.spring5recipeapp.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import hawk.springframweork.spring5recipeapp.domain.Recipe;
import hawk.springframweork.spring5recipeapp.repositories.RecipeRepository;
import hawk.springframweork.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes() {
		log.debug("I'm in the service");
		Set <Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

}
