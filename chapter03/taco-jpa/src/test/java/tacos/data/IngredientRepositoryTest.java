package tacos.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tacos.Ingredient;
import tacos.Ingredient.Type;

@SpringBootTest
public class IngredientRepositoryTest {
    
	private static List<Ingredient> ingredients;
	
	@BeforeAll
	public static void preloadIngredients() {
		ingredients = Arrays.asList(
				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Type.CHEESE),
				new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Type.SAUCE),
				new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
		    );
	}
	
	@Autowired
	private IngredientRepository ingredientRepo;
	
	@Test
	public void testFindAll() {
		List<Ingredient> actual = new ArrayList<>();
		ingredientRepo.findAll().forEach(x -> actual.add(x));
		
		assertTrue(actual.containsAll(ingredients));
	}
	
	@Test
	public void testFindById() {
		int index = (int) Math.floor(Math.random() * ingredients.size());
		Ingredient expected = ingredients.get(index);
		
		assertEquals(expected, ingredientRepo.findById(expected.getId()).orElse(null));
	}
	
}