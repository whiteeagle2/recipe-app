package hawk.springframweork.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import hawk.springframweork.spring5recipeapp.commands.UnitOfMeasureCommand;
import hawk.springframweork.spring5recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure source) {
		if(source == null) {
		return null;
		}
		
		final UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
		uom.setId(source.getId());
		uom.setDescription(source.getDescription());
		
		return uom;
	}
}
