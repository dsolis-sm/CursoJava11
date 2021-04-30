package labs.pm.app;

import java.math.BigDecimal;

import labs.pm.data.ProductManager;
import labs.pm.data.ProductManagerException;
import labs.pm.data.Rating;

public class Shop {

	public static void main(String[] args) throws ProductManagerException {
		
		ProductManager pm = new ProductManager("en-GB");
		
//		pm.parseProduct("D,101,Tea,1.99,0,2021-03-31");
//		pm.parseReview("101,4,Nice hot cup of tea");
//		pm.parseReview("101,2,Rather weak tea");
//		pm.parseReview("101,4,Good tea");
//		pm.parseReview("101,5,Perfect tea");
//		pm.parseReview("101,3,Just add some lemon");
		pm.printProductReport(101);
//		pm.parseProduct("F,103,Cake,3.99,0,2021-03-31");
		pm.printProductReport(103);
		
		pm.printProducts(p -> p.getPrice().floatValue() < 2, (p1, p2) -> p2.getRating().ordinal()-p1.getRating().ordinal()) ;
		
		pm.createProduct(164, "Kombucha", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
		pm.reviewProduct(164, Rating.TWO_STAR, "Looks like tea but is it?");
		pm.reviewProduct(164, Rating.FOUR_STAR, "Fine tea");
		pm.reviewProduct(164, Rating.FOUR_STAR, "This is not tea");
		pm.reviewProduct(164, Rating.FIVE_STAR, "Perfect!");
		pm.printProductReport(164);
		
		pm.dumpData();
		pm.restoreData();
		
		pm.printProductReport(101);
		pm.printProductReport(164);


	}

}
