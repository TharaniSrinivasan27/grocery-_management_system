//import the required java util classes 

import java.util.Scanner;
import java.util.LinkedHashMap;

class MainPage {
    public static void main(String args[]) {
        
        Scanner scan = new Scanner(System.in);
		
		//creating object for GroceryService class
        GroceryService service = new GroceryServiceImpl();
        System.out.println("********************************Welcome to Grocery****************************************");
        int selection = 1;
		
		// Current user name
	    String currentUser = "Tharani";
		String modifiedName="Varshini";
		
        while (true) {
            System.out.println("Enter your choice number ");

            System.out.println("1. Add product");
            System.out.println("2. Display");
			System.out.println("3. Delete");
            System.out.println("4. Update");
            System.out.println("5. Exit"); 
            selection = scan.nextInt();
            scan.nextLine();
            switch (selection) {
				
                // Inserting the  new product details
                case 1:
                    LinkedHashMap<String, Object> productDetails = new LinkedHashMap<>();

                    // Entering Product id
                    System.out.println("Enter product ID:");
                    int productID = scan.nextInt();

                    // Getting product name
                    System.out.println("Enter product Name:");
                    String productName = scan.next();

                    // Getting price
                    System.out.println("Enter Price:");
                    int price = scan.nextInt();

                    // Getting Category
                    System.out.println("Enter Category:");
                    String category = scan.next();

                    // Getting boolean value whether the product is available or not
                    System.out.println("Enter the product is available or not(Yes/No):");
                    boolean isAvailable ;
					char availableChoice = scan.next().charAt(0);
					if (availableChoice == 'y' ||availableChoice == 'Y') {
						isAvailable = true;
					} else {
						isAvailable = false;
					}

                    // Inserting the values by hashmap
                    productDetails.put("id", productID);
                    productDetails.put("product Name", productName);
                    productDetails.put("price", price);
                    productDetails.put("category", category);
                    productDetails.put("isAvailable", isAvailable);
					productDetails.put("login user", currentUser);

                    // Call GroceryService for inserting the product
                    service.insertProduct(productDetails);
                    break;

                // Displaying details
                case 2:
                    int choose;
                    System.out.println("1. Display The Product By ID");
                    System.out.println("2. Display All The Products");
                    System.out.println("3. Exit");
                    System.out.println("Enter your choice:");
                    choose = scan.nextInt();
                    
                    switch (choose) {
                        case 1:
                            // Displaying the details of product by product ID
                            System.out.println("Enter the product ID:");
                            int displayId = scan.nextInt();
                
							
							//call GroceryService for displaying the product by productID
                            service.displayByID(displayId);
                            break;
                        case 2:
                            // Displaying all the products
                            System.out.println("Display all products");
							
							//GroceryService call for displaying all the products in the list
                            service.displayAllProduct();
                            break;
                        case 3:
                            System.out.println("Exiting");
                            System.exit(0);
							break;
                    }
                    break;
				case 3:
				//delete the product by productID
				System.out.println("Enter product ID: ");
				int deleteID = scan.nextInt();
	
	
				//calling GroceryService for deletion by ID		
				service.deleteProduct(deleteID);
				break;
			
				case 4:
				//updating the details of products	
					int option;
					LinkedHashMap<String,Object> updateDetails = new LinkedHashMap<>();
					System.out.println("1.Update product name");
					System.out.println("2.Update Price");
					System.out.println("3.Update Category");
					System.out.println("4.Update Available Status");

					System.out.println("Enter your choice");
					
					option=scan.nextInt();
					switch (option) {
						case 1:
						
							//updating the Item name  
							System.out.println("Enter Product ID:");
							int productId = scan.nextInt();
							
							//Getting the new product name from the user
							System.out.println("Enter the new product name :");
							String newName = scan.next();
						
							//GroceryService call for updating the product name 
							service.updateProductName(productId,newName, modifiedName);
							break;
						case 2:
						
							//updating the Price
							System.out.println("Enter Product ID");
							int priceID = scan.nextInt();
							
							//Getting the new product price from the user
							System.out.println("Enter new price:"); 
							int newPrice = scan.nextInt();
					
							//groceryservice call for upadting the price
							service.updatePrice(priceID,newPrice, modifiedName);
							break;
						case 3:
							//updating the Category
							System.out.println("Enter Product ID");
							int categoryID = scan.nextInt();
							
							//Getting the new product category from the user
							System.out.println("Enter new category");
							String newCategory = scan.next();
							
							//GroceryService call for upadating the category of the product
							service.updateCategory(categoryID,newCategory, modifiedName);
							break;
						case 4:
							// update whether the product available status
							System.out.println("Enter productId");
							int availableID = scan.nextInt();
							
							//getting the input for updating the product status
							System.out.println("Enter product is Available or not(true/false):");
							boolean hasAvailable = scan.nextBoolean();
							
							//GroceryService call for updating the product status of the product
							service.updateStatus(availableID,hasAvailable, modifiedName);
							break;
							
						default:
							System.out.println("invalid input");
					}
					break;
					
			case 5:
				System.out.println("Exitting");
				System.exit(0);
				break;
			default:
				System.out.println("invalid input");
    }
}
}
}