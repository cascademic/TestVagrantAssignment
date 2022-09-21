package com.assignment.base;


import com.assignment.utilities.logs.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
	
	 @BeforeClass
	    @Parameters({"test.env"})
	    public void testInit(String env) {
	    	
}

	 @AfterClass
	    public void teardown() {
	        Log.info("Tests are ending!");
	    }
}
