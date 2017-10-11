package hawk.springframweork.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import hawk.springframweork.spring5recipeapp.commands.CategoryCommand;
import hawk.springframweork.spring5recipeapp.domain.Category;
import lombok.Synchronized;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand>{

	@Synchronized
	@Nullable
	@Override
	public CategoryCommand convert(Category source) {
		if (source == null) {
		return null;
		}
		
		final CategoryCommand category = new CategoryCommand();
		category.setId(source.getId());
		category.setDescription(source.getDescription());
		
		return category;
	}
}
