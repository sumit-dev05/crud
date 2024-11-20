package demojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
 import java.util.Scanner;

public class home {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/batch1192";
    static final String USER = "root";
	static final String PASS = "password";
	public static void main(String[] args) throws Exception {

	
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/batch1192", "root", "root");
		Statement stmt= c.createStatement();
		Scanner scanner = new Scanner(System.in);
		while (true) {
                System.out.println("Choose operation: 1. Create 2. Read 3. Update 4. Delete 5. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1: // Create
                        System.out.println("Enter name:");
                        String name = scanner.nextLine();
                        System.out.println("Enter email:");
                        String email = scanner.nextLine();
                        String sqlInsert = "INSERT INTO users (name, email) VALUES ('" + name + "', '" + email + "')";
                        stmt.executeUpdate(sqlInsert);
                        System.out.println("Record inserted successfully.");
                        break;

                    case 2: // Read
                        String sqlSelect = "SELECT * FROM users";
                        ResultSet rs = stmt.executeQuery(sqlSelect);
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String userName = rs.getString("name");
                            String userEmail = rs.getString("email");
                            System.out.println("ID: " + id + ", Name: " + userName + ", Email: " + userEmail);
                        }
                        rs.close();
                        break;

                    case 3: // Update
                        System.out.println("Enter user ID to update:");
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter new name:");
                        String newName = scanner.nextLine();
                        System.out.println("Enter new email:");
                        String newEmail = scanner.nextLine();
                        String sqlUpdate = "UPDATE users SET name = '" + newName + "', email = '" + newEmail + "' WHERE id = " + updateId;
                        stmt.executeUpdate(sqlUpdate);
                        System.out.println("Record updated successfully.");
                        break;

                    case 4: // Delete
                        System.out.println("Enter user ID to delete:");
                        int deleteId = scanner.nextInt();
                        String sqlDelete = "DELETE FROM users WHERE id = " + deleteId;
                        stmt.executeUpdate(sqlDelete);
                        System.out.println("Record deleted successfully.");
                        break;

                    case 5: // Exit
                        scanner.close();
                        stmt.close();
                        c.close();
                        System.out.println("EXITED");
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } 
		}
		
	


