
//importing the required java util classes

import java.util.LinkedHashMap;

// Interface for service implementation
interface GroceryService {
    
    // Method to insert a new product 
    public void insertProduct(LinkedHashMap<String, Object> productDetails);
    
    // Method to display the product details by displayID
    public void displayByID(int displayId);
    
    // Method to display all the products 
    public void displayAllProduct();
    
    // Method to delete the product by deleteId
    public void deleteProduct(int deleteID);
    
    // Method to update the product name by productId
    public void updateProductName(int productId, String newName, String modifiedName);
    
    // Method to update the price by priceID
    public void updatePrice(int priceID, int newPrice, String modifiedName);
    
    // Method to update the category by CategoryID
    public void updateCategory(int categoryID, String newCategory, String modifiedName);
    
    // Method to update the status of the product (whether available or not) by availableID
    public void updateStatus(int availableID, boolean hasAvailable, String modifiedName);
}
