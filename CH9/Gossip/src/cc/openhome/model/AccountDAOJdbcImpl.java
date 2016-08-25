package cc.openhome.model;

import java.util.*;
import javax.sql.DataSource;

public class AccountDAOJdbcImpl implements AccountDAO {
    private JdbcTemplate jdbcTemplate;
    
    public AccountDAOJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean isUserExisted(Account account) {
        Map row = jdbcTemplate.queryForList("SELECT COUNT(1) FROM t_account WHERE name = ?", 
                new Object[] { account.getName() }).get(0);
        long count = (Long) row.get("COUNT(1)");
        return count == 1;
    }

    @Override
    public void addAccount(Account account) {
        jdbcTemplate.update(
                "INSERT INTO t_account(name, password, email) VALUES(?, ?, ?)", 
                new Object[] {account.getName(), account.getPassword(), account.getEmail()});
    }

    @Override
    public Account getAccount(Account account) {
        List<Map> rows = jdbcTemplate.queryForList(
                "SELECT password, email FROM t_account WHERE name = ?",
                new Object[] { account.getName() });
        if(rows.size() == 1) {
            Map row = rows.get(0);
            String email = (String) row.get("email");
            String password = (String) row.get("password");
            return new Account(account.getName(), password, email);
        }
        return null;
    }

}
