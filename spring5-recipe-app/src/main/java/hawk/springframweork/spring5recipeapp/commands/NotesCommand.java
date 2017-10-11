package hawk.springframweork.spring5recipeapp.commands;

import hawk.springframweork.spring5recipeapp.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
	private Long id;
	private String recipeNotes;


}
