package com.ximalaya.android.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ScrollFeedList.class,FoundSearchTest.class  })
public class AllTests extends TestCase{
	
	//组织多个测试类测试
	public static Test suite(){
		TestSuite suite = new TestSuite();
		suite.addTestSuite(ScrollFeedList.class);
		suite.addTestSuite(FoundSearchTest.class);
		return suite;
	}
	
	//运行测试
	public void testAllCases(){
		junit.textui.TestRunner.run(suite());
	}

}
