package directory.implementation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DirectoryStructureImplementationTest {




	DirectoryStructureImplementation dl=null;

	@Before
	public void setUp() throws Exception {
		dl=new DirectoryStructureImplementation();
	}

	@After
	public void tearDown() throws Exception {
		dl=null;
	}

	@Test
	public void testprintTree() {
		assertEquals(dl.calculateSizeOfFolder(11), 36);
	
	}

}
