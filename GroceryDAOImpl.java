// Importing the required Java util classes and SQL related classes

import java.util.LinkedHashMap;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Timestamp;

// Implementation of DAO interface
class GroceryDAOImpl implements GroceryDAO {
	
	// Method for inserting grocery details into the database
	public int insertProduct(int productID, String productName, int price, String category, boolean isAvailable, String currentUser, Timestamp timestamp, String modifiedUserName, Timestamp time) {
		Connection connection = null;
		Statement stmt = null;
		int result = 0;
		try {
			connection = DBConnection.createConnection();
			stmt = connection.createStatement();
			
			// SQL query to insert the details of the product
			String query = "INSERT INTO product_details (product_id, product_name, price, category, is_available, created_at, created_by, modified_by, modified_at) VALUES (" +  
       			  "'" + productID + "'," +
				  "'" + productName + "'," +
				  "'" + price + "'," +
				  "'" + category + "'," +
				  "'" + (isAvailable ? 1 : 0) + "'," +
				  "'" + timestamp + "'," +
				  "'" + currentUser + "'," +
				  "'" + modifiedUserName + "'," +
				  "'" + time + "')";
				  
			// Executing the query
			result = stmt.executeUpdate(query);
			System.out.println(result + " rows inserted successfully");

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error closing statement: " + e.getMessage());
                }
            }
			if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return result; // Returning the number of rows affected
    }
	
	// Method to display the product details by product ID
	public LinkedHashMap<String, Object> displayByID(int displayId) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		
		LinkedHashMap<String, Object> productDetails = new LinkedHashMap<>();
		try {
			connection = DBConnection.createConnection();
			stmt = connection.createStatement();
			
			// SQL query to retrieve product details
			String query = "SELECT * FROM product_details WHERE product_id=" + displayId;
			resultSet = stmt.executeQuery(query);
			
			if (resultSet.next()) {
				
				// Storing product details in the LinkedHashMap
				productDetails.put("productID", resultSet.getInt("product_id"));
				productDetails.put("productName", resultSet.getString("product_name"));
				productDetails.put("price", resultSet.getInt("price"));
				productDetails.put("category", resultSet.getString("category"));
				productDetails.put("IsAvailable", resultSet.getBoolean("is_available"));
				productDetails.put("Created At", resultSet.getTimestamp("created_at"));
				productDetails.put("Modified By", resultSet.getString("modified_by"));
				productDetails.put("Modified At", resultSet.getTimestamp("modified_at"));
			}
		} catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error closing statement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return productDetails;
    }
	
	// Method to display all products
	public ArrayList<LinkedHashMap<String, Object>> displayAllProduct() {
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		ArrayList<LinkedHashMap<String, Object>> allProducts = new ArrayList<>();

		try {
			connection = DBConnection.createConnection();
			stmt = connection.createStatement();
			String query = "SELECT * FROM product_details";
			resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				LinkedHashMap<String, Object> productDetails = new LinkedHashMap<>();
				productDetails.put("productID", resultSet.getInt("product_id"));
				productDetails.put("productName", resultSet.getString("product_name"));
				productDetails.put("price", resultSet.getInt("price"));
				productDetails.put("category", resultSet.getString("category"));
				productDetails.put("isAvailable", resultSet.getBoolean("is_available"));
				productDetails.put("createdBy", resultSet.getString("created_by"));
				productDetails.put("createdAt", resultSet.getTimestamp("created_at"));
				productDetails.put("modifiedBy", resultSet.getString("modified_by"));
				productDetails.put("modifiedAt", resultSet.getTimestamp("modified_at"));
				allProducts.add(productDetails);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				if (resultSet != null) resultSet.close();
				if (stmt != null) stmt.close();
				if (connection != null) connection.close();
			} catch (SQLException e) {
				System.out.println("Error closing resources: " + e.getMessage());
			}
		}
		return allProducts;
	}

	// Method to delete a product by ID
	public int deleteProduct(int deleteId) {
		Connection connection = null;
		Statement stmt = null;
		int result = 0;
		try {
			connection = DBConnection.createConnection();
			stmt = connection.createStatement();
			
			// Delete the product details by product ID
			String query = "DELETE FROM product_details WHERE product_id = " + deleteId;
			result = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (connection != null) connection.close();
			} catch (SQLException e) {
				System.out.println("Error closing resources: " + e.getMessage());
			}
		}
		return result;
	}

	// Method to update the product name
	public int updateProductName(int productId, String newName, String  modifiedName, Timestamp time) {
		Connection connection = null;
		Statement stmt = null;
		int result = 0;
		try {
			connection = DBConnection.createConnection();
			stmt = connection.createStatement();
			
			// Query to update the product name
			String query = "UPDATE product_details SET product_name = '" + newName + "', modified_by = '" +  modifiedName + "', modified_at = '" + time + "' WHERE product_id ='" + productId + "'";

			result = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Error closing statement: " + e.getMessage());
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("Error closing connection: " + e.getMessage());
				}
			}
		}
		return result;
	}
	
	// Method to update the product price

	public int updatePrice(int productId, int newPrice, String  modifiedName, Timestamp time) {
		Connection connection = null;
		Statement stmt = null;
		int result = 0;
		try {
			connection = DBConnection.createConnection();
			stmt = connection.createStatement();
			
			// Query to update the price
			String query = "UPDATE product_details SET price = " + newPrice + ", modified_by = '" + modifiedName + "', modified_at = '" + time + "' WHERE product_id = '" + productId + "'";
			result = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Error closing statement: " + e.getMessage());
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("Error closing connection: " + e.getMessage());
				}
			}
		}
		return result;
	}
	
	// Method to update the category of a product
	public int updateCategory(int productId, String newCategory, String modifiedName, Timestamp time) {
		Connection connection = null;
		Statement stmt = null;
		int result = 0;
		try {
			connection = DBConnection.createConnection();
			stmt = connection.createStatement();
			
			// Query to update the category of the product 
			String query = "UPDATE product_details SET category = '" + newCategory + "', modified_by = '" + modifiedName + "', modified_at = '" + time + "' WHERE product_id = '" + productId + "'";
			result = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Error closing statement: " + e.getMessage());
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("Error closing connection: " + e.getMessage());
				}
			}
		}
		return result;
	}
	
	// Method to update the availability status of a product
	public int updateStatus(int productId, boolean hasAvailable, String modifiedName, Timestamp time) {
		Connection connection = null;
		Statement stmt = null;
		int result = 0;
		try {
			connection = DBConnection.createConnection();
			stmt = connection.createStatement();
			
			// Query to update the product available status of the product
			String query = "UPDATE product_details SET is_available = " + (hasAvailable? 1 : 0) + ", modified_by = '" + modifiedName + "', modified_at = '" + time + "' WHERE product_id = '" + productId + "'";
			result = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Error closing statement: " + e.getMessage());
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("Error closing connection: " + e.getMessage());
				}
			}
		}
		return result;
	}
}


