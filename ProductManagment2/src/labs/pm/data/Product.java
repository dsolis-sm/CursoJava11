package labs.pm.data;

import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;;

/**
 * The Class Product.
 */
public class Product {

	/** The Constant DISCOUNT_RATE. */
	public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);

	/** The id. */
	private int id;

	/** The name. */
	private String name;

	/** The price. */
	private BigDecimal price;

	private Rating rating;

	/**
	 * Instantiates a new product.
	 */
	public Product() {

		this(0, "no name", BigDecimal.ZERO);

	}

	/**
	 * Instantiates a new product.
	 *
	 * @param id    the id
	 * @param name  the name
	 * @param price the price
	 */
	public Product(int id, String name, BigDecimal price, Rating rating) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.rating = rating;
	}

	public Product(int id, String name, BigDecimal price) {
		this(id, name, price, Rating.NOT_RATED);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
//	public void setId(final int id) {
//		this.id = id;
//	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
//	public void setName(final String name) {
//		this.name = name;
//	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
//	public void setPrice(final BigDecimal price) {
//		//price = BigDecimal.ONE;
//		this.price = price;
//	}
//	
	/**
	 * Gets the discount.
	 *
	 * @return the discount
	 */
	public BigDecimal getDiscount() {
		return price.multiply(DISCOUNT_RATE).setScale(2, HALF_UP);

	}

	public Rating getRating() {
		return rating;
	}

	public Product applyRating(Rating newRating) {
		return new Product(this.id, this.name, this.price, newRating);
	}
}
