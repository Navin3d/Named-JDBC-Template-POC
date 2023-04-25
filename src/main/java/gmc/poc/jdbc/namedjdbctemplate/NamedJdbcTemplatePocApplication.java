package gmc.poc.jdbc.namedjdbctemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import gmc.poc.jdbc.namedjdbctemplate.dao.UserDao;
import gmc.poc.jdbc.namedjdbctemplate.entities.UserEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class NamedJdbcTemplatePocApplication implements CommandLineRunner {
	
	@Autowired
	private UserDao userDao;

	public static void main(String[] args) {
		SpringApplication.run(NamedJdbcTemplatePocApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MapSqlParameterSource querryParams = new MapSqlParameterSource();
//		querryParams.addValue("from", "from");
		querryParams.addValue("user", "users");
		
		String sql = "select * from users where name=:user;";
				
		List<UserEntity> users = userDao.execute(sql, querryParams);
		
		log.error(users.toString());
	}

}
