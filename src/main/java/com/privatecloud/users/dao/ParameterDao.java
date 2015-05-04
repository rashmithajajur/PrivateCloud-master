package com.privatecloud.users.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ParameterDao {
	public static Map<String, Long> getVMs() {
		Map<String, Long> vmNames = new HashMap<String, Long>();
		try {
			Connection connection = Mysqlconnection.getInstance().connection; 

				
				PreparedStatement statement = null;
				
				statement = connection.
						prepareStatement(
								"SELECT "
								+ "Id"
								+ ", vmname "
								+ "FROM "
								+ "vm"
								);
				ResultSet rs = statement.executeQuery();
				if(rs == null) {
					System.out.println("ParameterDao : getVMs : No result");
					return vmNames;
				}
				while(rs.next()){
					vmNames.put(rs.getString("vmname"), rs.getLong("Id"));
				}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return vmNames;
	}
	
	public static Map<String, Long> getVMPropertyThresholdValues(String vmName) {
		Map<String, Long> vmPropThresholdMap = new HashMap<String, Long>();
		try {
			Connection connection = Mysqlconnection.getInstance().connection; 
				PreparedStatement statement = null;
				
				statement = connection.
						prepareStatement(
								"SELECT "
								+ "name"
								+ ", threshold_value "
								+ "FROM "
								+ "alarm"
								+ ", params"
								+ ", vm "
								+ "WHERE "
								+ "alarm.property_id = params.paramsId "
								+ "AND vm.Id = alarm.vm_id "
								+ "AND vm.vmname = ?"
								);
				statement.setString(1, vmName);
				ResultSet rs = statement.executeQuery();
				if(rs == null) {
					System.out.println("ParameterDao : getVMPropertyThresholdValues : No result");
					return vmPropThresholdMap;
				}
				while(rs.next()){
					vmPropThresholdMap.put(rs.getString("name"), rs.getLong("threshold_value"));
				}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return vmPropThresholdMap;
	}
	
	public static boolean isVmPropertyLimitExceeded(long vmId, long propertyId) {
		boolean result = false;
		try {
			Connection connection = Mysqlconnection.getInstance().connection; 
				PreparedStatement statement = null;
				
				statement = connection.
						prepareStatement(
								"SELECT "
								+ "limit_exceed "
								+ "FROM "
								+ "alarm "
								+ "WHERE "
								+ "vm_id = ? "
								+ "AND property_id = ?"
								);
				statement.setLong(1, vmId);
				statement.setLong(2, propertyId);
				ResultSet rs = statement.executeQuery();
				if(rs == null) {
					System.out.println("ParameterDao : isVMPropertyLimitExceeded : No result");
					return result;
				}
				rs.next();
				if(rs.getInt("limit_exceed") == 1)
					result = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public static void setVmPropertyLimitExceeded(long vmId, long propertyId, boolean value) {
		int val;
		if(value) val = 1; 
		else val = 0;
		try {
			Connection connection = Mysqlconnection.getInstance().connection; 
				PreparedStatement statement = null;
				
				statement = connection.
						prepareStatement(
								"UPDATE alarm "
								+ "SET "
								+ "limit_exceed = ? "
								+ "WHERE "
								+ "vm_id = ? "
								+ "AND property_id = ?;"
								);
				statement.setLong(1, val);
				statement.setLong(2, vmId);
				statement.setLong(3, propertyId);
				int rs = statement.executeUpdate();
				
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void setVmPropertyThreshold(String vmName, String property, int value) {
		int vmId = getVmIdFromName(vmName);
		int propId = getPropertyIdFromName(property);
		if(vmId != -1 && propId != -1) {
			try {
				Connection connection = Mysqlconnection.getInstance().connection; 
					PreparedStatement statement = null;
					
					statement = connection.
							prepareStatement(
									"INSERT INTO "
									+ "alarm ("
									+ "vm_id"
									+ ", property_id"
									+ ", threshold_value"
									+ ", limit_exceed"
									+ ") VALUES ("
									+ "?, ?, ?, ?"
									+ ")"
									);
					statement.setInt(1, vmId);
					statement.setInt(2, propId);
					statement.setInt(3, value);
					statement.setInt(4, 0);
					int rs = statement.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println("ParameterDao : setVmPropertyThreshold : Invalid vmName or property");
		}
		
	}
	
	private static int getVmIdFromName(String vmName) {
		int result = -1;
		try {
			Connection connection = Mysqlconnection.getInstance().connection; 
				PreparedStatement statement = null;
				
				statement = connection.
						prepareStatement(
								"SELECT Id FROM vm WHERE vmname = ?"
								);
				statement.setString(1, vmName);
				ResultSet rs = statement.executeQuery();
				if(rs == null) {
					System.out.println("ParameterDao : getVmIdFromName : No result");
					return result;
				}
				rs.next();
				result = rs.getInt("id");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	private static int getPropertyIdFromName(String propName) {
		int result = -1;
		try {
			Connection connection = Mysqlconnection.getInstance().connection; 
				PreparedStatement statement = null;
				
				statement = connection.
						prepareStatement(
								"SELECT paramsId FROM params WHERE name = ?"
								);
				statement.setString(1, propName);
				ResultSet rs = statement.executeQuery();
				if(rs == null) {
					System.out.println("ParameterDao : getPropertyIdFromName : No result");
					return result;
				}
				rs.next();
				result = rs.getInt("id");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public static Map<String, Long> getVmStatProperties() {
		Map<String, Long> result = new HashMap<String, Long>();
		try {
			Connection connection = Mysqlconnection.getInstance().connection; 
				PreparedStatement statement = null;
				
				statement = connection.
						prepareStatement(
								"SELECT paramsId, name FROM params"
								);
				ResultSet rs = statement.executeQuery();
				if(rs == null) {
					System.out.println("ParameterDao : getPropertyIdFromName : No result");
					return result;
				}
				while(rs.next()){
					result.put(rs.getString("name"), rs.getLong("paramsId"));
					//System.out.println(rs.getString("name") + "---"+ rs.getLong("id"));
				}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public static Map<String, Long> getVmPropertyThresholdExceedStatus(String vmName) {
		Map<String, Long> result = new HashMap<String, Long>();
		try {
			Connection connection = Mysqlconnection.getInstance().connection; 
				PreparedStatement statement = null;
				
				statement = connection.
						prepareStatement(
								"SELECT "
								+ "params.name"
								+ ", alarm.limit_exceed "
								+ "FROM "
								+ "alarm a"
								+ ", params p"
								+ ", vm vm "
								+ "WHERE "
								+ "vm.Id = a.vm_id "
								+ "AND p.paramsId = a.property_id "
								+ "AND vm.vmname = ?"
								);
				statement.setString(1, vmName);
				ResultSet rs = statement.executeQuery();
				if(rs == null) {
					System.out.println("ParameterDao : getVmPropertyThresholdExceedStatus : No result");
					return result;
				}
				while(rs.next()){
					result.put(rs.getString("name"), rs.getLong("limit_exceed"));
					//System.out.println(rs.getString("name") + "---"+ rs.getLong("limit_exceed"));
				}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
