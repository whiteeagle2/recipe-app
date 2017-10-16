package hawk.springframweork.spring5recipeapp.services.impl;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hawk.springframweork.spring5recipeapp.commands.UnitOfMeasureCommand;
import hawk.springframweork.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import hawk.springframweork.spring5recipeapp.repositories.UnitOfMeasureRepository;
import hawk.springframweork.spring5recipeapp.services.UnitOfMeasureService;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

	private UnitOfMeasureRepository uomRepository;
	private UnitOfMeasureToUnitOfMeasureCommand uomToUomCommand;
	
	@Autowired
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository uomRepository,
			UnitOfMeasureToUnitOfMeasureCommand uomToUomCommand) {
		this.uomRepository = uomRepository;
		this.uomToUomCommand = uomToUomCommand;
	}
	
	@Override
	public Set<UnitOfMeasureCommand> listAllUoms() {
		
		return StreamSupport.stream(uomRepository.findAll()
					.spliterator(), false)
					.map(uomToUomCommand::convert)
					.collect(Collectors.toSet());
	}



}
