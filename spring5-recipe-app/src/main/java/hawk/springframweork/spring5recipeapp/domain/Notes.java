package hawk.springframweork.spring5recipeapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

/*
 * @Lob - Large object. Create a BLOB object to store more than 255 characters
 * No CascadeType - we don't want to delete etc Recipe deleting Notes
 */

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@OneToOne(mappedBy="notes")
	private Recipe recipe;
	@Lob
	private String recipeNotes;
}
