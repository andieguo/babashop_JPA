package junit.test;

import org.junit.Test;


public class FileTest {

	@Test
	public void test(){//»ñÈ¡ºó×ºjpg
		String logoFileName = "°®.jpg";
		System.out.println(logoFileName);//°®.jpg
		System.out.println(logoFileName.substring(logoFileName.lastIndexOf('.')+1).toLowerCase());;
	}
}
