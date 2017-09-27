package hawk.springframweork.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;

import hawk.springframweork.spring5recipeapp.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
