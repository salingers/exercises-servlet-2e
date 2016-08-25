package cc.openhome.model;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcTemplate {
	private DataSource dataSource;
	
	public JdbcTemplate(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	class DefaultPreparedStatementSetter implements PreparedStatementSetter {
		private Object[] params;
		
		DefaultPreparedStatementSetter(Object[] params) {
			this.params = params;
		}
		
		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			for(int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
		}
	}
	
	public int update(String sql, final Object[] params) {
		return update(sql, new DefaultPreparedStatementSetter(params));
	}
	
	public int update(String sql, PreparedStatementSetter psst) {
		return ((Integer) execute(sql, psst)).intValue();
	}
	
	public List<Map> queryForList(String sql) {
		return queryForList(sql, new Object[] {});
	}
	
	public List<Map> queryForList(String sql, final Object[] params) {
		return queryForList(sql, new DefaultPreparedStatementSetter(params));
	}
	
	public List<Map> queryForList(String sql, PreparedStatementSetter psst) {
		return (List<Map>) execute(sql, psst);
	}
	
	public Object execute(String sql, PreparedStatementSetter psst) {
		Connection conn = null;
		PreparedStatement stmt = null;
		Object result = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			psst.setValues(stmt);
			if(stmt.execute()) {
				ResultSet rs = stmt.getResultSet();
			    ResultSetMetaData meta = rs.getMetaData();
			    int columnCount = meta.getColumnCount();
			    List<String> columnNames = new ArrayList<String>();
			    for(int i = 1; i <= columnCount; i++) {
			    	columnNames.add(meta.getColumnName(i));
			    }
			    List<Map> results = new ArrayList<Map>();
			    while(rs.next()) {
			    	Map row = new HashMap();
			    	for(String columnName : columnNames) {
			    		row.put(columnName, rs.getObject(columnName));
			    	}
			    	results.add(row);
			    }
			    result = results; 
			}
			else {
				result = Integer.valueOf(stmt.getUpdateCount());
			}
			
		    
		} catch (SQLException e) {
		    throw new RuntimeException(e);
		}
		finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}				
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return result;
	}
}
 