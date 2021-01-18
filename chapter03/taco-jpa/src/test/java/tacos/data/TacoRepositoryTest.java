package tacos.data;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tacos.Ingredient;
import tacos.Taco;

@SpringBootTest
public class TacoRepositoryTest {
    
	@Autowired
	private TacoRepository tacoRepo;
	
	@Autowired
	private IngredientRepository ingredientRepo;
	
	@Test
	public void testSaveTaco() {
	    Taco unsaved = new Taco();
	    unsaved.setName("ForTest");
	    List<Ingredient> ingredients = new ArrayList<>();
	    ingredients.add(ingredientRepo.findById("COTO").get());
	    ingredients.add(ingredientRepo.findById("JACK").get());
	    unsaved.setIngredients(ingredients);
	    
	    Taco saved = tacoRepo.save(unsaved);

        assertNotNull(saved.getId());
	}
}
