package cc.openhome.model;

import java.sql.Timestamp;
import java.util.*;

import javax.sql.DataSource;

public class BlahDAOJdbcImpl implements BlahDAO {
    private JdbcTemplate jdbcTemplate;
        
    public BlahDAOJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Blah> getBlahs(Blah blah) {
        List<Map> rows = jdbcTemplate.queryForList(
                "SELECT date, txt FROM t_blah WHERE name = ?", 
                new Object[] {blah.getUsername()});
        List<Blah> blahs = new ArrayList<Blah>();
        for(Map row : rows) {
            Timestamp date = (Timestamp) row.get("date");
            String txt = (String) row.get("txt");
            blahs.add(new Blah(blah.getUsername(), date, txt));
        }
        return blahs;
    }

    @Override
    public void addBlah(Blah blah) {
        jdbcTemplate.update(
                "INSERT INTO t_blah(name, date, txt) VALUES(?, ?, ?)", 
                new Object[] {blah.getUsername(), new Timestamp(blah.getDate().getTime()), blah.getTxt()});
    }

    @Override
    public void deleteBlah(Blah blah) {
        jdbcTemplate.update("DELETE FROM t_blah WHERE date = ?", 
                new Object[] {new Timestamp(blah.getDate().getTime())});
    }
}
