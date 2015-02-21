package com.silvertree.ipfilter.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.silvertree.ipfilter.model.IPFilter;

/**
 * Data Access Object implementation for getting IpFilter configuration data from relational database.
 * @author graski
 *
 */

@Component
public class IpFilterJDBCDAO implements IpFilterDao {

	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<IPFilter> selectAllFilters() {

		List<IPFilter> filterList = jdbcTemplate.query(
				"select PATTERN from IP_FILTER", new Object[] {}, new IPFilterMapper());
		return filterList;
	}
	
	public void addFilter(IPFilter newIpfilter){
		jdbcTemplate.update("INSERT INTO IP_FILTER (PATTERN) VALUES(?)",
	        new Object[] { newIpfilter.getPattern() });
	}
	
    private static final class IPFilterMapper implements RowMapper<IPFilter> {
        public IPFilter mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			IPFilter filter = new IPFilter(rs.getString("PATTERN"));
			return filter;
		}
    }

	public void removeFilter(IPFilter newIpfilter) {
		jdbcTemplate.update("DELETE FROM IP_FILTER WHERE PATTERN = ?",
		        new Object[] { newIpfilter.getPattern() });
	}	
}
