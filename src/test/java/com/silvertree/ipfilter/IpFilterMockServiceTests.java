package com.silvertree.ipfilter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.silvertree.ipfilter.dao.IIPFilterDao;
import com.silvertree.ipfilter.model.IPFilter;

/**
 * Test for checking the functions of IpFilter service.
 * @author graski
 *
 */

@ContextConfiguration({"classpath:com/silvertree/ipfilter/IpFilterServiceTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class IpFilterMockServiceTests extends IpFilterServiceTestCases {

	private IIpFilterServiceImpl mockService;

	@Before
	public void setMockervice(){
		mockService = new IIpFilterServiceImpl();
		mockService.setIpFilterDao(new IIPFilterDao(){
			public List<IPFilter> selectAllFilters() {
				List<IPFilter> list = new ArrayList<IPFilter>();
				list.add(new IPFilter(IpFilterServiceTestCases.FILTER_PATTERN_01));
				list.add(new IPFilter(IpFilterServiceTestCases.FILTER_PATTERN_02));
				list.add(new IPFilter(IpFilterServiceTestCases.FILTER_PATTERN_03));
				return list;
			};
			public void addFilter(IPFilter newIpfilter) {
				// unimplemented
			};
			public void removeFilter(IPFilter newIpfilter) {
				// unimplemented
			};
		});
		this.service = mockService;
		assertNotNull(service);
	}

}
