package junit.test;

import static org.junit.Assert.*;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.bean.product.Brand;
import com.service.product.BrandService;



public class BrandServiceTest {
	private static  ApplicationContext cxt;
	private static 	BrandService bs;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		  try {
			  cxt = new ClassPathXmlApplicationContext("applicationContext.xml");
			  
			  bs = (BrandService)cxt.getBean("brandServiceImpl");
		} catch (Exception e) {		
			e.printStackTrace();
		}	  
	}

	@Test
	public void test() {
		System.out.println(cxt);
		bs.save(new Brand("Ô¶Ñôè¤Ù¤","/images/brand/2013/1/17/10/yangyang.jpg"));
	}

}
