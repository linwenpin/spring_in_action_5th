package tacos.data;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Taco;

@SpringBootTest
public class OrderRepositoryTest {
    
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired 
	TacoRepository tacoRepo;
	
	@Test
	public void testSaveOrder() {
		Order unsaved = new Order();
		unsaved.setTacos(getTacos());
		unsaved.setCcCVV("111");
		unsaved.setCcExpiration("01/15");
		unsaved.setCcNumber("6227612145830440");
		unsaved.setDeliveryCity("NY");
		unsaved.setDeliveryName("libai");
		unsaved.setDeliveryState("RM");
		unsaved.setDeliveryStreet("LuckyEast");
		unsaved.setDeliveryZip("8888");
		
		Order saved = orderRepo.save(unsaved);
		
		assertNotNull(saved.getId());
		assertNotNull(saved.getPlacedAt());
	}
	
	private List<Taco> getTacos() {
		Taco taco1 = taco(
			"taco1",
		    Arrays.asList(
				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP), 
				new Ingredient("SRCR", "Sour Cream", Type.SAUCE)));
		
		Taco taco2 = taco(
			"taco2", 
			Arrays.asList(
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
				new Ingredient("SLSA", "Salsa", Type.SAUCE)));
		
		return Arrays.asList(taco1, taco2);
	}
	
	private Taco taco(String name, List<Ingredient> ingredients) {
		Taco taco = new Taco();
		taco.setName(name);
		taco.setIngredients(ingredients);
		return tacoRepo.save(taco);
	}
}
