package labs.pm.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Locale;
import java.util.function.Predicate;

import labs.pm.data.Product;
import labs.pm.data.ProductManager;
import labs.pm.data.Rating;

public class Shop {

	public static void main(String[] args) {
		
		ProductManager pm = new ProductManager(Locale.UK);

		pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
//		pm.printProductReport(101);
		pm.reviewProduct(101, Rating.FOUR_STAR, "Nice hot cup of tea");
		pm.reviewProduct(101, Rating.TWO_STAR, "Rather weak tea");
		pm.reviewProduct(101, Rating.FOUR_STAR, "Fine tea");
		pm.reviewProduct(101, Rating.FOUR_STAR, "Godd tea");
		pm.reviewProduct(101, Rating.FIVE_STAR, "Pefect tea");
		pm.reviewProduct(101, Rating.THREE_STAR, "Just add some lemon");
//		pm.printProductReport(101);
//		pm.changeLocale("ru-RU");
		
		
		pm.createProduct(102, "Coffee", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
		pm.reviewProduct(102, Rating.THREE_STAR, "Coffee was ok");
		pm.reviewProduct(102, Rating.ONE_STAR, "Where is the milk?!");
		pm.reviewProduct(102, Rating.FIVE_STAR, "It's perfect with ten spoons of sugar");
//		pm.printProductReport(102);
		
		pm.createProduct(103, "Cake", BigDecimal.valueOf(3.99), Rating.NOT_RATED, LocalDate.now().plusDays(2));
		pm.reviewProduct(103, Rating.FIVE_STAR, "Very nice cake");
		pm.reviewProduct(103, Rating.FOUR_STAR, "It good, but I've expected more chocolate");
		pm.reviewProduct(103, Rating.FIVE_STAR, "This Cake is perfect!");
//		pm.printProductReport(103);
		
		pm.createProduct(104, "Cookie", BigDecimal.valueOf(2.99), Rating.NOT_RATED, LocalDate.now());
		pm.reviewProduct(104, Rating.THREE_STAR, "Just another cookie");
		pm.reviewProduct(104, Rating.THREE_STAR, "OK");
//		pm.printProductReport(104);
		
		pm.createProduct(105, "Hot Chocolate", BigDecimal.valueOf(2.50), Rating.NOT_RATED);
		pm.reviewProduct(105, Rating.FOUR_STAR, "Tasty!");
		pm.reviewProduct(105, Rating.FOUR_STAR, "No bad at all");
//		pm.printProductReport(105);
		
		pm.createProduct(106, "Chocolate", BigDecimal.valueOf(2.50), Rating.NOT_RATED, LocalDate.now().plusDays(3));
		pm.reviewProduct(106, Rating.TWO_STAR, "Too sweet");
		pm.reviewProduct(106, Rating.THREE_STAR, "Better than cookie");
		pm.reviewProduct(106, Rating.TWO_STAR, "Too bitter");
		pm.reviewProduct(106, Rating.ONE_STAR, "I don't get it");
//		pm.printProductReport(106);
		
		
		Comparator<Product> ratingSorter = (p1, p2) -> p2.getRating().ordinal() - p1.getRating().ordinal();
		Comparator<Product> priceSorter = (p1, p2) -> p2.getPrice().compareTo(p1.getPrice());
		Predicate<Product> priceFilter = p -> p.getPrice().floatValue() < 2;
		
//		pm.printProducts((p1, p2) -> p2.getRating().ordinal() - p1.getRating().ordinal());
//		pm.printProducts(ratingSorter);
		//pm.printProducts((p1, p2) -> p2.getPrice().compareTo(p1.getPrice()));
//		pm.printProducts(priceSorter);
		pm.printProducts(priceFilter, ratingSorter.thenComparing(priceSorter));
		pm.printProducts(priceFilter, ratingSorter.thenComparing(priceSorter).reversed());
		
		pm.getDiscounts().forEach((rating, discount) -> System.out.println(rating + "\t" + discount));


	}

}
