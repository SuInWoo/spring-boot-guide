package com.springboot.hello.dao;

import com.springboot.hello.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
       this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper rowMapper = new RowMapper() {
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getString("id"), rs.getString("name"),
                    rs.getString("password"));
            return user;
        }
    };

    public void deleteAll() throws SQLException {
        this.jdbcTemplate.update("delete from users");
   }

    public void add(final User user) throws SQLException {
        this.jdbcTemplate.update
                ("INSERT INTO users(id, name, password) VALUES (?, ?, ?)",
                        user.getId(), user.getName(), user.getPassword());
    }

    public int getCount() throws SQLException {
        return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }

    public void findById(String id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<User> getAll() {
        String sql = "SELECT * FROM users order by id";
        return this.jdbcTemplate.query(sql, rowMapper);
    }

}

