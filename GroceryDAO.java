import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.ArrayList;

interface GroceryDAO {

    // Method to insert grocery product 
    public int insertProduct(int productID, String productName, int price, String category, boolean isAvailable, String currentUser, Timestamp timestamp, String modifiedUserName, Timestamp time);
    
    // Method to display the product details by product ID
    public LinkedHashMap<String, Object> displayByID(int displayId);
    
    // Method to display all the products
    public ArrayList<LinkedHashMap<String, Object>> displayAllProduct();
    
    // Method to delete the product by deleteID
    public int deleteProduct(int deleteID);
    
    // Method to update product name
    public int updateProductName(int productId, String newName, String  modifiedName, Timestamp time);
    
    // Method to update product price
    public int updatePrice(int priceID, int newPrice, String  modifiedName, Timestamp time);
    
    // Method to update category
    public int updateCategory(int productID, String newCategory, String  modifiedName, Timestamp time);
    
    // Method to update the status of product is available or not
    public int updateStatus(int availableID, boolean hasAvailable, String  modifiedName, Timestamp time);
}
