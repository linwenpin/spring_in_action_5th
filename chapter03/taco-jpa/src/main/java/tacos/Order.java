package tacos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
@Entity
@Table(name="Taco_Order")
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="placedat")
	private Date placedAt;
    
	@Column(name="deliveryname")
	@NotBlank(message="Name is required")
	private String deliveryName;

	@Column(name="deliverystreet")
	@NotBlank(message="Street is required")
	private String deliveryStreet;

	@Column(name="deliverycity")
	@NotBlank(message="city is required")
	private String deliveryCity;

	@Column(name="deliverystate")
	@NotBlank(message="State is required")
	@Size(max=2, message="State isn't more than 2 characters")
	private String deliveryState;

	@Column(name="deliveryzip")
	@NotBlank(message="Zip code is required")
	private String deliveryZip;

	@Column(name="ccnumber")
	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;

	@Column(name="ccexpiration")
	@Pattern(regexp="^(0[0-9]|1[1-2])(\\/)([1-9][0-9])$", 
			 message="Must be formatted MM/YY")
	private String ccExpiration;

	@Column(name="cccvv")
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
	
	@ManyToMany(targetEntity=Taco.class)
	@JoinTable(
		joinColumns=@JoinColumn(name="tacoorder"), 
		inverseJoinColumns=@JoinColumn(name="taco"))
	private List<Taco> tacos = new ArrayList<>();
	
	public void addDesign(Taco design) {
		this.tacos.add(design);
	}
	
	@PrePersist
	void platedAt() {
		this.placedAt = new Date();
	}
}