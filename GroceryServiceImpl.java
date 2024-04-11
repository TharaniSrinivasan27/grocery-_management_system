//import the required java util classes

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.sql.Timestamp;
import java.time.LocalDateTime;

class GroceryServiceImpl implements GroceryService {
	
	// Scanner for user input
	Scanner scan = new Scanner(System.in);
	
	// DAO object
	GroceryDAO dao = new GroceryDAOImpl();
	
	// Current time
	LocalDateTime currentTime = LocalDateTime.now();
	String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	Timestamp timestamp = Timestamp.valueOf(formattedTime);

	// Method to insert a new product
	public void insertProduct(LinkedHashMap<String, Object> productDetails) {

		Integer productID = Integer.parseInt(String.valueOf(productDetails.get("id")));
		String productName = String.valueOf(productDetails.get("product Name"));
		Integer price = Integer.parseInt(String.valueOf(productDetails.get("price")));
		String category = String.valueOf(productDetails.get("category"));
		Boolean isAvailable = Boolean.parseBoolean(String.valueOf(productDetails.get("isAvailable")));
		String currentUser= String.valueOf(productDetails.get("login user"));

		// Adding creation and modification details to the HashMap
		productDetails.put("Created By", currentUser);
		productDetails.put("Created At", timestamp);
		productDetails.put("Modified By", currentUser);
		productDetails.put("Modified At", timestamp);

		// DAO method to insert the product details
		int row = dao.insertProduct(productID, productName, price, category, isAvailable, currentUser, timestamp, currentUser, timestamp);
		
		// Check insertion status
		if (row > 0) {
			System.out.println("Insertion is successful");
		} else {
			System.out.println("Insertion failed");
		}
	}

	// Method to display details of a product by ID
	public void displayByID(int displayId) {
		// Retrieve product details by ID
		LinkedHashMap<String, Object> productDetails = dao.displayByID(displayId);
	
		if (productDetails.isEmpty()) {
			System.out.println("No product found with ID: " + displayId);
			return;
		}
		
		System.out.println("Details of product " + displayId + " :");
		
		// Iterate through the details and format them for display
		for (Map.Entry<String, Object> detail : productDetails.entrySet()) {
			String detailKey = detail.getKey();
			Object detailValue = detail.getValue();
			
			// Format timestamps if necessary
			if (detailKey.equals("Created At") || detailKey.equals("Modified At")) {
				Timestamp timestamp = (Timestamp) detailValue;
				LocalDateTime dateTime = timestamp.toLocalDateTime();
				String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				System.out.printf("%-15s : %-20s%n", detailKey, formattedDateTime);
			} else {
				System.out.printf("%-15s : %-20s%n", detailKey, detailValue);
			}
		}
		System.out.println();
	}

	// Method to display all products
	public void displayAllProduct() {
		
		// Retrieve details of all products
		ArrayList<LinkedHashMap<String, Object>> productDetails = dao.displayAllProduct();
		if (productDetails.isEmpty()) {
			System.out.println("No products found");
			return;
		}
		System.out.println("Displaying all the Products");
		
		// Iterate through the list of products and format their details for display
		for (LinkedHashMap<String, Object> product : productDetails) {
			for (Map.Entry<String, Object> entry : product.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				
				// Format timestamps if necessary
				if (value instanceof Timestamp) {
					Timestamp timestamp = (Timestamp) value;
					LocalDateTime dateTime = timestamp.toLocalDateTime();
					String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
					System.out.printf("%-15s : %-20s%n", key, formattedDateTime);
				} else {
					System.out.printf("%-15s : %-20s%n", key, value);
				}
			}
			System.out.println();
		}
	}

	// Method to delete a product by ID
	public void deleteProduct(int deleteID) {
		int row = dao.deleteProduct(deleteID);
		
		//checking for deletion of product
		if (row > 0) {
			System.out.println("Product " + deleteID + " deletion is successful");
		} else {
			System.out.println("Product " + deleteID + " deletion failed");
		}
	}

	// Method to update the product name 
	public void updateProductName(int productId, String newName, String modifiedName) {
		Integer productID = Integer.parseInt(String.valueOf(productId));
		String productName = String.valueOf(newName);
		
		int row = dao.updateProductName(productID, productName, modifiedName, timestamp);
		
		//checking for updation of product name
		if (row > 0) {
			System.out.println("Product name update is successful");
		} else {
			System.out.println("Product name update failed");
		}
	}

	// Method to update the product price 
	public void updatePrice(int priceID, int newPrice, String modifiedName) {
		Integer productID = Integer.parseInt(String.valueOf(priceID));
		Integer price = Integer.valueOf(newPrice);
		
		int row = dao.updatePrice(productID, price, modifiedName, timestamp);
		
		//checking for updation of product price
		if (row > 0) {
			System.out.println("Product price update is successful");
		} else {
			System.out.println("Product price update failed");
		}
	}

	// Method to update the product category
	public void updateCategory(int CategoryID, String newCategory, String modifiedName) {
		Integer productID = Integer.parseInt(String.valueOf(CategoryID));
		String category = String.valueOf(newCategory);

		// Retrieve the current category of the product
		LinkedHashMap<String, Object> currentProductDetails = dao.displayByID(productID);
		String currentCategory = (String) currentProductDetails.get("category");

		// Check if the new category is different from the current category
		if (currentCategory.equalsIgnoreCase(newCategory)) {
			System.out.println("The new category is the same as the current category. No update performed.");
			return;
		} 
	
		// Proceed with the update if the new category is different
		int row = dao.updateCategory(productID, newCategory, modifiedName, timestamp);
		
		//checking for updation of category of product
		if (row > 0) {
			System.out.println("Product category update is successful");
		} else {
			System.out.println("Product category update failed");
		}
	}

	// Method to update the product availability
	public void updateStatus(int availableID, boolean hasAvailable, String modifiedName) {
		Integer productID = Integer.parseInt(String.valueOf(availableID));
	
			int row = dao.updateStatus(productID, hasAvailable, modifiedName, timestamp);
			
			//checking for updation of status of product
			if (row > 0) {
				System.out.println("Product availability update is successful");
			} else {
				System.out.println("Product availability update failed");
			}
	}

}
