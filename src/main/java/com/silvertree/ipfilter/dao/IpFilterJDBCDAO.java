package com.silvertree.ipfilter.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

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
public class IpFilterJDBCDAO implements IIPFilterDao, IpFilterRelationalDBDao {

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	public List<IPFilter> selectAllFilters() {

		List<IPFilter> filterList = jdbcTemplate.query(
				"select PATTERN from IP_FILTER", new Object[] {}, new IPFilterMapper());
		return filterList;
	}
	
    private static final class IPFilterMapper implements RowMapper<IPFilter> {
        public IPFilter mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			IPFilter filter = new IPFilter(rs.getString("PATTERN"));
			return filter;
		}
    }


}
