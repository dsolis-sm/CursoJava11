package labs.pm.data;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Food extends Product {

//	public Food() {
//		super();
//	}

	private LocalDate bestBefore;

	/**
	 * Gets the best before.
	 *
	 * @return the best before
	 */
	public LocalDate getBestBefore() {
		return bestBefore;
	}

	Food(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
		super(id, name, price, rating);
		this.bestBefore = bestBefore;
	}

	@Override
	public String toString() {
		return super.toString() + " " + bestBefore;
	}

	@Override
	public BigDecimal getDiscount() {
		return (bestBefore.isEqual(LocalDate.now())) ? super.getDiscount() : BigDecimal.ZERO;
	}

	@Override
	public Product applyRating(Rating newRating) {
		return new Food(getId(), getName(), getPrice(), newRating, bestBefore);
	}

}
