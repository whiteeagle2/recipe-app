package hawk.springframweork.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import hawk.springframweork.spring5recipeapp.commands.NotesCommand;
import hawk.springframweork.spring5recipeapp.domain.Notes;
import lombok.Synchronized;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

	@Synchronized
	@Nullable
	@Override
	public NotesCommand convert(Notes source) {
		if(source==null) {
			return null;
		}
		
		final NotesCommand notes = new NotesCommand();
		notes.setId(source.getId());
		notes.setRecipeNotes(source.getRecipeNotes());
		
		return notes;
		
	}
}
