package hawk.springframweork.spring5recipeapp.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hawk.springframweork.spring5recipeapp.domain.Category;
import hawk.springframweork.spring5recipeapp.domain.UnitOfMeasure;
import hawk.springframweork.spring5recipeapp.repositories.CategoryRepository;
import hawk.springframweork.spring5recipeapp.repositories.UnitOfMeasureRepository;

@Controller
public class IndexController {

	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;
	
	
	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}



	@RequestMapping({"","/","/index"})
	public String getIndexPage() {
		Optional <Category> optionalCategory = categoryRepository.findByDescription("American");
		Optional <UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");
		
//		System.out.println("Category id : " + optionalCategory.get().getId());
//		System.out.println("Unit of measure id : "+ optionalUnitOfMeasure.get().getId());
		return "index";
	}
}
