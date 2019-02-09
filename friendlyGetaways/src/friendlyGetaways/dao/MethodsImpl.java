package friendlyGetaways.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import friendlyGetaways.dto.Property;
import friendlyGetaways.dto.UserLogin;
import friendlyGetaways.dto.UserRegisteration;
import friendlyGetaways.factory.DBConnectionFactory;

public class MethodsImpl implements Imethods{
private Connection connection = null;

	@Override
	public boolean validateUser(UserLogin u) {
		String query ="select * from users where name = '"+u.getName()+"' and password='"+u.getPassword()+"'";
		Statement stmt;
		try {
			stmt = DBConnectionFactory.getConnection().createStatement();
			//execute Query
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public ArrayList<Property> searchProperty(Property p) {
		// TODO Auto-generated method stub
		int count = 0;
		ArrayList<Property> propertyList = new ArrayList<Property>();
		StringBuilder whereQuery= new StringBuilder();
		StringBuilder finalQuery = new StringBuilder("select * from temp_property ");
		if(p==null){
			
		}
		else{
			if(p.getProperty_type()!=null && !p.getProperty_type().equals("")) 
			{
				if(count == 0)
				{
				   whereQuery.append("property_type = '");
			       whereQuery.append(p.getProperty_type());
			       whereQuery.append("'");
			       count++;
				}
				
				else
				{
					whereQuery.append(" and ");
					whereQuery.append("property_type = '");
					whereQuery.append(p.getProperty_type());
					whereQuery.append("'");
					count++;
				}
				
			}
			
			if(p.getBedrooms()!=null && !p.getBedrooms().equals("")) 
			{
				if(count == 0)
				{
				   whereQuery.append("bedrooms = '");
			       whereQuery.append(p.getBedrooms());
			       whereQuery.append("'");
			       count++;
				}
				
				else
				{
					whereQuery.append(" and ");
					whereQuery.append(" bedrooms = '");
					whereQuery.append(p.getBedrooms());
					whereQuery.append("'");
					count++;
				}
				
			}
			
			if(p.getReview_scores_rating()!=null && !p.getReview_scores_rating().equals("")) 
			{
				if(count == 0)
				{
				   whereQuery.append("review_scores_rating > '");
			       whereQuery.append(p.getReview_scores_rating());
			       whereQuery.append("'");
			       count++;
				}
				
				else
				{
					whereQuery.append(" and ");
					whereQuery.append("review_scores_rating > '");
				    whereQuery.append(p.getReview_scores_rating());
				    whereQuery.append("'");
					count++;
				}
				
			}
			
			if(p.getMax_price()!=null && !p.getMax_price().equals("") && p.getMin_price()!=null && p.getMin_price().equals("")) 
			{
				if(count == 0)
				{
				   whereQuery.append("price > '");
			       whereQuery.append(p.getMin_price());
			       whereQuery.append("'");
			       whereQuery.append(" and price < '");
			       whereQuery.append(p.getMax_price());
			       whereQuery.append("'");
			       count++;
				}
				
				else
				{
					whereQuery.append(" and ");
					whereQuery.append("price > '");
				    whereQuery.append(p.getMin_price());
				    whereQuery.append("'");
					whereQuery.append("and price < '");
				    whereQuery.append(p.getMax_price());
				    whereQuery.append("'");
					count++;
				}
				
			}
		}
		
		if(whereQuery.length() != 0)
		{
			finalQuery.append("where ");
			finalQuery.append(whereQuery);
		}
		
		try {
			connection = DBConnectionFactory.getConnection();
			Statement stmt = connection.createStatement();
			System.out.println("final query:---> "+finalQuery.toString());
			ResultSet rs = stmt.executeQuery(finalQuery.toString());
			
			while(rs.next()){
				Property temp_property = new Property();
				temp_property.setProperty_id(rs.getString(1));
				temp_property.setDescription(rs.getString(2));
				temp_property.setHouse_rules(rs.getString(3));
				temp_property.setHost_id(rs.getString(4));
				temp_property.setHost_url(rs.getString(5));
				temp_property.setHost_name(rs.getString(6));
				temp_property.setCity(rs.getString(7));
				temp_property.setState(rs.getString(8));
				temp_property.setZipcode(rs.getString(9));
				temp_property.setCountry(rs.getString(10));
				temp_property.setProperty_type(rs.getString(11));
				temp_property.setBedrooms(rs.getString(12));
				temp_property.setBathrooms(rs.getString(13));
				temp_property.setAmenities(rs.getString(14));
				temp_property.setPrice(rs.getString(15));
				temp_property.setReview_scores_rating(rs.getString(16));
				
				propertyList.add(temp_property);
				
			}
			
			connection.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return propertyList;
		
		
		
	}
	


	@Override
	public boolean insertNewUser(UserRegisteration u) {
		return true;
	}
//		try {
//			return true;
//			connection = DBConnectionFactory.getConnection();
//			System.out.println("connection found!: "+connection);
//			String query = "insert into users (name,email,password,phone_no,interests) values(?,?,?,?,?)";
//			PreparedStatement pstmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
//			pstmt.setString(1, u.getUsername());
//			pstmt.setString(2, u.getEmail());
//			pstmt.setString(3,u.getPassword());
//			pstmt.setString(4, u.getPhone());
//			pstmt.setString(5, u.getInterests());
//			pstmt.executeUpdate();
////			ResultSet tableKeys =pstmt.getGeneratedKeys();
////			tableKeys.next();
////			int autoGeneratedID = tableKeys.getInt(1);
//			connection.close();
//			return true;
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
			
	}



