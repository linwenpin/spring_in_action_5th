package tacos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class Order {
	
	private Long id;
	
	private Date placedAt;
    
	@NotBlank(message="Name is required")
	private String deliveryName;
	
	@NotBlank(message="Street is required")
	private String deliveryStreet;
	
	@NotBlank(message="city is required")
	private String deliveryCity;
	
	@NotBlank(message="State is required")
	@Size(max=2, message="State isn't more than 2 characters")
	private String deliveryState;
	
	@NotBlank(message="Zip code is required")
	private String deliveryZip;
	
	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	
	@Pattern(regexp="^(0[0-9]|1[1-2])(\\/)([1-9][0-9])$", 
			 message="Must be formatted MM/YY")
	private String ccExpiration;
	
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
	
	private List<Taco> tacos = new ArrayList<>();
	
	public void addDesign(Taco design) {
		this.tacos.add(design);
	}
}