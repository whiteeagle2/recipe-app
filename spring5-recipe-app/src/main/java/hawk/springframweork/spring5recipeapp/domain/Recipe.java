package hawk.springframweork.spring5recipeapp.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;


/* Recipe is the main thing - that is why we have cascade type. 
 * 
 * @Lob - Large object
 * We have cascade type here. If we delete Recipe we also want to delete notes and everything related
 * With that object
 * 
 * 39 * Each ingredient will have a column recipe which it belongs
 */
@Data
@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	
	@Lob
	private String directions;
	@Lob
	private Byte [] image;	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="recipe")
	private Set<Ingredient> ingredients = new HashSet<>();

	@Enumerated(value=EnumType.STRING)
	private Difficulty difficulty;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy="recipe")
	private Notes notes;
	
	@ManyToMany
	@JoinTable(name="recipe_category", 
		joinColumns= @JoinColumn(name="recipe_id"),
		inverseJoinColumns= @JoinColumn(name="category_id"))
	private Set<Category> categories = new HashSet<>();

	
    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
	
	public void setNotes(Notes notes) {
		notes.setRecipe(this);  // bidirectional
		this.notes = notes;
	}
}
