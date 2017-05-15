package f1resultsparser.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import f1resultsparser.Controller.JSONBuilder;
import f1resultsparser.Model.Driver;

public class JSONBuilderTest {
    private List<Driver> list; 
    private JSONBuilder jb;
	@Before
	public void setUp() throws Exception {
		list = new ArrayList<Driver>();
		jb = new JSONBuilder();
		Driver dummyDriver1 = new Driver("name1", 1);
		Driver dummyDriver2 = new Driver("name2", 2);
		Driver dummyDriver3 = new Driver("name3", 3);
		
		list.add(dummyDriver1);
		list.add(dummyDriver2);
		list.add(dummyDriver3);
	}

	@Test
	public void testBuildJson() {
		String resultTest = jb.buildJson(list);
		String resultExpected = "[{\"name\":\"name1\",\"points\":1}," 
		                        + "{\"name\":\"name2\",\"points\":2},"
		                        + "{\"name\":\"name3\",\"points\":3}]";
		assertEquals(resultTest, resultExpected);
	}

}
