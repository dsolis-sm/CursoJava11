package labs.pm.app;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import labs.pm.data.Product;
import labs.pm.data.ProductManager;
import labs.pm.data.ProductManagerException;
import labs.pm.data.Rating;

public class Shop {

	public static void main(String[] args) throws ProductManagerException {
		
		AtomicInteger clientCount = new AtomicInteger(0);
		
		ProductManager pm = ProductManager.getInstance();
		
		Callable<String> client = () -> {
			
			String clientId = "Client: " + clientCount.incrementAndGet();
			String threadName = Thread.currentThread().getName();
			
			int productId = ThreadLocalRandom.current().nextInt(63)+101;
			
			String languageTag = ProductManager.getSupportedLocales()
					.stream()
					.skip(ThreadLocalRandom.current().nextInt(4))
					.findFirst()
					.get();
			
			StringBuilder log = new StringBuilder();
			
			log.append(clientId + " " + threadName + "\n-\tstart of log-\n ");
			log.append("\n-tend of log\t\n");
			log.append(pm.getDiscounts(languageTag).entrySet()
					.stream()
					.map(entry -> entry.getKey() + "\t" + entry.getValue())
					.collect(Collectors.joining("\n")));
			
			Product product = pm.reviewProduct(productId, Rating.FOUR_STAR, "Yet another review");
			
			log.append(product != null ? "\nProduct " + productId + "reviewd\n" : "\nProduct " + productId + " not reviewed\n");
			pm.printProductReport(productId, languageTag, clientId);
			log.append(clientId + " generated report for " + productId + " product");
			
			return log.toString();
		};
		
		List<Callable<String>> clients = Stream.generate(() -> client).limit(5).collect(Collectors.toList());
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		try {
			List<Future<String>> results = executorService.invokeAll(clients);
			executorService.shutdown();
			results.stream().forEach(result -> {
				try {
					System.out.println(result.get());
				} catch (InterruptedException | ExecutionException e) {
					Logger.getLogger(Shop.class.getName()).log(Level.SEVERE, "Error retrieving client log", e);
				}
			});
		} catch (InterruptedException e) {
			Logger.getLogger(Shop.class.getName()).log(Level.SEVERE, "Error invoking client", e);
		}
	}

}
