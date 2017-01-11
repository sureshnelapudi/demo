package directory.model;


import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DirectoryModelTest {
	DirectoryModel dm=null;
	@Before
	public void setUp() throws Exception {
		dm=new DirectoryModel();
	}

	@After
	public void tearDown() throws Exception {
		dm=null;
	}
	@Test
	public void testGetId() {
		dm.setId(2);
		assertTrue(dm.getId()== 3);}
		
		@Test
		public void testParentId() {
			dm.setParentId(2);
			assertTrue(dm.getParentId()== 3);}
		
		@Test
		public void testGetName() {
			dm.setName("2");
			assertTrue(dm.getName()== "3");}
		
		
		@Test
		public void testGetType() {
			dm.setType("2");
			assertTrue(dm.getType()== "2");}

		@Test
		public void testGetSize() {
			dm.setSize(2);
			assertTrue(dm.getSize()== 2);}
		
		@Test
		public void testGetClassification() {
			dm.setClassification("2");
			assertTrue(dm.getClassification()== "3");}
		
		@Test
		public void testGetChecksum() {
			dm.setChecksum(2);
			assertTrue(dm.getChecksum()== 3);}
		
	

}
