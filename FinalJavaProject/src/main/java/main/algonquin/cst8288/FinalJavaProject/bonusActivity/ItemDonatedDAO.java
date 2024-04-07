package main.algonquin.cst8288.FinalJavaProject.bonusActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDonatedDAO {

	private Connection con;

	public ItemDonatedDAO(Connection con) {
		this.con = con;
	}

	public boolean addItemDonated(ItemDonated ItemDonated) {
		String query = "INSERT INTO FoodItemsForExchange (UserId, Title, Description, Quantity, PickupLocation, ExpirationDate, Status) VALUES (?, ?, ?, ?, ?, ?, 'available')";

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, ItemDonated.getUserId());
			ps.setString(2, ItemDonated.getTitle());
			ps.setString(3, ItemDonated.getDescription());
			ps.setInt(4, ItemDonated.getQuantity());
			ps.setString(5, ItemDonated.getPickupLocation());
			ps.setString(6, ItemDonated.getExpirationDate());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateItemDonated(ItemDonated ItemDonated) {
		String query = "UPDATE FoodItemsForExchange SET Title = ?, Description = ?, Quantity = ?, PickupLocation = ?, ExpirationDate = ?, Status = ? WHERE ItemId = ? AND UserId = ?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, ItemDonated.getTitle());
			ps.setString(2, ItemDonated.getDescription());
			ps.setInt(3, ItemDonated.getQuantity());
			ps.setString(4, ItemDonated.getPickupLocation());
			ps.setString(5, ItemDonated.getExpirationDate());
			ps.setString(6, ItemDonated.getStatus());
			ps.setInt(7, ItemDonated.getItemId());
			ps.setInt(8, ItemDonated.getUserId());
			int affectedRows = ps.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteItemDonated(int itemId, int userId) {
		String query = "DELETE FROM FoodItemsForExchange WHERE ItemId = ? AND UserId = ?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, itemId);
			ps.setInt(2, userId);
			int affectedRows = ps.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<String> getUniqueLocations() {
		List<String> locations = new ArrayList<>();
		String query = "SELECT DISTINCT PickupLocation FROM FoodItemsForExchange ORDER BY PickupLocation ASC";
		try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				locations.add(rs.getString("PickupLocation"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Number of unique locations found: " + locations.size()); // Debugging line
		return locations;
	}

	public List<ItemDonated> getItemsByLocation(String location) {
		List<ItemDonated> items = new ArrayList<>();
		String query = "SELECT * FROM FoodItemsForExchange WHERE PickupLocation = ?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, location);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ItemDonated item = new ItemDonated();
				item.setItemId(rs.getInt("ItemId")); // Asume que tienes un campo ItemId en tu tabla
				item.setUserId(rs.getInt("UserId")); // Asume que tienes un campo UserId en tu tabla
				item.setTitle(rs.getString("Title"));
				item.setDescription(rs.getString("Description"));
				item.setQuantity(rs.getInt("Quantity"));
				item.setPickupLocation(rs.getString("PickupLocation"));
				item.setExpirationDate(rs.getString("ExpirationDate")); // Asegúrate de que este formato coincida con tu
																		// JSP
				item.setStatus(rs.getString("Status"));
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}

	public List<ItemDonated> getItemsByUserId(int userId) {
		List<ItemDonated> items = new ArrayList<>();
		String query = "SELECT * FROM FoodItemsForExchange WHERE UserId = ?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ItemDonated item = new ItemDonated();
				item.setItemId(rs.getInt("ItemId"));
				item.setUserId(rs.getInt("UserId"));
				item.setTitle(rs.getString("Title"));
				item.setDescription(rs.getString("Description"));
				item.setQuantity(rs.getInt("Quantity"));
				item.setPickupLocation(rs.getString("PickupLocation"));
				item.setExpirationDate(rs.getString("ExpirationDate"));
				item.setStatus(rs.getString("Status"));
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}

	public ItemDonated getItemById(int itemId) {
		String query = "SELECT * FROM FoodItemsForExchange WHERE ItemId = ?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, itemId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					ItemDonated item = new ItemDonated();
					item.setItemId(rs.getInt("ItemId"));
					item.setUserId(rs.getInt("UserId"));
					item.setTitle(rs.getString("Title"));
					item.setDescription(rs.getString("Description"));
					item.setQuantity(rs.getInt("Quantity"));
					item.setPickupLocation(rs.getString("PickupLocation"));
					item.setExpirationDate(rs.getString("ExpirationDate"));
					item.setStatus(rs.getString("Status"));
					return item;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // Or throw an exception
	}

}
