package com.paf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.paf.model.Registration;

public class RegistrationDao {

	@Autowired
	JdbcTemplate template;

	@Autowired
	DataSource datasource;
	
	public int addRegistration(Registration registration) {
		
		String sql = "insert into registration (email, username, role, password, addedDate) values ("
				+ " '"+registration.getEmail()+"', '"+registration.getUsername()+"', '"+registration.getRole()+"',"
						+ " '"+registration.getPassword()+"', NOW())";
		
		return template.update(sql);
		
	}
	
	public int editRegistration(Registration registration) {
		
		String sql = "update registration set email = '"+registration.getEmail()+"', username = '"+registration.getUsername()+"',"
				+ "	role = '"+registration.getRole()+"', password = '"+registration.getPassword()+"' where userID = '"+registration.getUserID()+"' ";
		
		return template.update(sql);
		
	}
	
	public int getCount(String un, String pw) {
		String sql = "select count(*) from registration where email = '"+un+"' AND password = '"+pw+"' ";
		int cnt = template.queryForObject(sql, Integer.class);
		return cnt;
	}
	
	public List<Registration> getAllRegistration() {
		return template.query("select * from registration", new RowMapper<Registration>() {
			public Registration mapRow(ResultSet rs, int row) throws SQLException {
				Registration c = new Registration();
				c.setUserID(rs.getInt("userID"));
				c.setEmail(rs.getString("email"));
				c.setUsername(rs.getString("username"));
				c.setRole(rs.getString("role"));
				c.setAddedDate(rs.getString("addedDate"));
				c.setStatus(rs.getString("status"));
				return c;
			}
		});
	}
	
	
	
	public List searchRegistration(String srch) {
		String search = "%" + srch + "%";
		String sql = " select * from registration where userID like '"+search+"' OR email like '"+search+"' OR username like '"+search+"' OR role  like '"+search+"' OR addedDate like '"+search+"' OR status like '"+search+"' ";
		return template.queryForList(sql);
	}
	
	public int setStatus(int userID, String command) {
		String sql = " update registration set status = '"+command+"' where userID = '"+userID+"' ";
		return template.update(sql);
	}
	
	public Registration getRegistration(int userID) {
		String sql = "select * from registration where userID = ?";
		return template.queryForObject(sql, new Object[] { userID },
				new BeanPropertyRowMapper<Registration>(Registration.class));
	}
	
}
