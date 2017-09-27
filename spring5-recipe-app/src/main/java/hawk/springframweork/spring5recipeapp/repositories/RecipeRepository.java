package hawk.springframweork.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;

import hawk.springframweork.spring5recipeapp.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
