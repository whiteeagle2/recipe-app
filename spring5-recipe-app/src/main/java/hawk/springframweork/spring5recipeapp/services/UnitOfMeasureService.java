package hawk.springframweork.spring5recipeapp.services;

import java.util.Set;

import hawk.springframweork.spring5recipeapp.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {
	Set<UnitOfMeasureCommand> listAllUoms();
}
