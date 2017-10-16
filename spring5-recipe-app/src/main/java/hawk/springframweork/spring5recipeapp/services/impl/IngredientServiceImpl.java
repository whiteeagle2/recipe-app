package hawk.springframweork.spring5recipeapp.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hawk.springframweork.spring5recipeapp.commands.IngredientCommand;
import hawk.springframweork.spring5recipeapp.converters.IngredientCommandToIngredient;
import hawk.springframweork.spring5recipeapp.converters.IngredientToIngredientCommand;
import hawk.springframweork.spring5recipeapp.domain.Ingredient;
import hawk.springframweork.spring5recipeapp.domain.Recipe;
import hawk.springframweork.spring5recipeapp.repositories.RecipeRepository;
import hawk.springframweork.spring5recipeapp.repositories.UnitOfMeasureRepository;
import hawk.springframweork.spring5recipeapp.services.IngredientService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository uomRepository;
	
	@Autowired
	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
			IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository,
			UnitOfMeasureRepository uomRepository) {
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		this.recipeRepository = recipeRepository;
		this.uomRepository = uomRepository;
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


	@Override
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {
		Optional <Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
		
		if(!recipeOptional.isPresent()) {
			// TODO error handling
			log.error("Recipe not found for id " + command.getRecipeId());
			return new IngredientCommand();
		} else {
			Recipe recipe = recipeOptional.get();
			Optional<Ingredient> ingredientOptional = recipe
					.getIngredients()
					.stream()
					.filter(ingredient -> ingredient.getId().equals(command.getId()))
					.findFirst();
			
			if(ingredientOptional.isPresent()) {
				// Update existing ingredient
				Ingredient ingredientFound = ingredientOptional.get();
				ingredientFound.setDescription(command.getDescription());
				ingredientFound.setAmount(command.getAmount());
				ingredientFound.setUom(uomRepository
						.findById(command.getUom().getId())
						.orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); // TODO error handling
				
			} else {
				// add NEW ingredient
				Ingredient ingredient = ingredientCommandToIngredient.convert(command);
				ingredient.setRecipe(recipe);
				recipe.addIngredient(ingredient);
				
			}
			
			Recipe savedRecipe = recipeRepository.save(recipe);
			
			Optional <Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
					.filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
					.findFirst();
			
			if(!savedIngredientOptional.isPresent()) {
				
				savedIngredientOptional = savedRecipe.getIngredients().stream()
						.filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
						.filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
						.filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUom().getId()))
						.findFirst();
			}
			
			return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
					
		}

	}

	@Override
	public void deleteById(Long recipeId, Long idToDelete) {
		Optional <Recipe> recipeOptional = recipeRepository.findById(recipeId);
		
		if(recipeOptional.isPresent()) {
			Recipe recipe = recipeOptional.get();
			Optional <Ingredient> ingredientOptional = recipe
					.getIngredients()
					.stream()
					.filter(ingredient -> ingredient.getId().equals(idToDelete))
					.findFirst();
			
			if(ingredientOptional.isPresent()) {
				Ingredient ingredientToDelete = ingredientOptional.get();
				ingredientToDelete.setRecipe(null);
				recipe.getIngredients().remove(ingredientOptional.get());
				recipeRepository.save(recipe);
			}
		} else {
			log.debug("recipe id not found id : " + recipeId);
		}
	}

}
