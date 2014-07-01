package junit.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import com.utils.ImageSizer;

public class ImageTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 ImageSizer.resize(new File("d:\\a.jpg"), new File("d:\\c.jpg"), 100, "jpg");
	}

	@Test
	public void test() {
		
	}

}
