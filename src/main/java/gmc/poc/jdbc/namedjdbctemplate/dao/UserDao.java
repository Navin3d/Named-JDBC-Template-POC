package gmc.poc.jdbc.namedjdbctemplate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import gmc.poc.jdbc.namedjdbctemplate.entities.UserEntity;

@Repository
public class UserDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<UserEntity> execute(String sql, MapSqlParameterSource paramsMap) {
		List<UserEntity> returnedValue = jdbcTemplate.query(sql, paramsMap, new ResultSetExtractor<List<UserEntity>>() {
			@Override
			public List<UserEntity> extractData(ResultSet rs) throws SQLException {
				List<UserEntity> returnValue = new ArrayList<>();
				
				while(rs.next()) {
					UserEntity foundUser = new UserEntity();
					foundUser.setId(rs.getString("id"));
					foundUser.setEmail(rs.getString("email"));
					foundUser.setName(rs.getString("name"));
					foundUser.setPassword(rs.getString("password"));
					returnValue.add(foundUser);
				}
				
				return returnValue;
			}
		});
		return returnedValue;
	}

}
