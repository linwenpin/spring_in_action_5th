package tacos.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
	
	private IngredientRepository ingredientRepo;
	
	private TacoRepository tacoRepo;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo) {
		this.ingredientRepo = ingredientRepo;
		this.tacoRepo = tacoRepo;
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
    
	@GetMapping
	public String showDesignForm(Model model) {
		model.addAllAttributes(ingredientsMappedByType());
		
		return "design";
	}
	
	@PostMapping
	public String processDesign(
			@Valid Taco design, Errors errors, 
			@ModelAttribute Order order, 
			Model model) {

		if (errors.hasErrors()) {
			model.addAllAttributes(ingredientsMappedByType());
		    return "design";
		}
        
		tacoRepo.save(design);
		
		log.info("Taco submiited: " + design);
		
		order.addDesign(design);
		
	    return "redirect:/orders/current";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		// TODO Auto-generated method stub
		return ingredients
				.stream()
				.filter(x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}
	
	private Map<String, List<Ingredient>> ingredientsMappedByType() {
		Map<String, List<Ingredient>> ingredientsMap = new HashMap<>();
		
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(x -> ingredients.add(x));
        
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			ingredientsMap.put(type.toString().toLowerCase(), 
					           filterByType(ingredients, type));
		}		
		
		return ingredientsMap;
	}
}