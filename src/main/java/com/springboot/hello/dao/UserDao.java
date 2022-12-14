package com.springboot.hello.dao;

import com.springboot.hello.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> rowMapper = new RowMapper<>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
        }
    };

    public int deleteAll() throws SQLException {
        return this.jdbcTemplate.update("delete from users");
    }

    public void add(final User user) throws SQLException {
        this.jdbcTemplate.update
                ("INSERT INTO users(id, name, password) VALUES (?, ?, ?)",
                        user.getId(), user.getName(), user.getPassword());
    }

    public int getCount() throws SQLException {
        return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }

    public User findById(String id) {
        User user = null;
        String sql = "SELECT * FROM users WHERE id = ?";
        user = this.jdbcTemplate.queryForObject(sql, rowMapper, id);
        return user;
    }

    public List<User> getAll() {
        String sql = "SELECT * FROM users order by id";
        return this.jdbcTemplate.query(sql, rowMapper);
    }
}
