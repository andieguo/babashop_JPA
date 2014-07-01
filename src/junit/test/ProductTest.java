package junit.test;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bean.product.Brand;
import com.bean.product.ProductInfo;
import com.bean.product.ProductStyle;
import com.bean.product.Board;
import com.bean.product.Sex;
import com.service.product.BrandService;
import com.service.product.ProductInfoService;


public class ProductTest {
	
	private static ApplicationContext cxt;
	private static ProductInfoService pis ; 
	private static BrandService bs;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 try {
			cxt = new ClassPathXmlApplicationContext("applicationContext.xml");
			pis = (ProductInfoService) cxt.getBean("productInfoServiceImpl");
			bs = (BrandService) cxt.getBean("brandServiceImpl");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void test() {
		for(int i=0;i<20;i++){
		ProductInfo product = new ProductInfo();
		product.setBrand(bs.find(Brand.class, "330751a8-9d1d-409a-916e-925a780bff38"));
		//System.out.println(bs.find(Brand.class, "330751a8-9d1d-409a-916e-925a780bff38").getName());
		product.setBaseprice(100f);
		product.setBuyexplain(i+"�������������ߵ�");
		product.setClickcount(100);
		product.setCode("1111");
		product.setCommend(true);
		product.setCreatedate(new Date());
		product.setDescription(i+"��˵�еĺ�����");
		product.setMarketprice(200f);
		product.setModel(i+"Բ��");
		product.setName(i+"�й�����");
		product.setSellcount(100);
		product.setSellprice(300f);
		product.setSexrequest(Sex.NONE);
		product.setWeight(100);
		product.setVisible(true);
		product.setProducttype(new Board(1));
		product.addProductStyle(new ProductStyle(i+"��ɫ",i+"��ɫ",true));
	
		pis.save(product);
		
		}
	}
	
	@Test
	public void testGetTopSell() {
		
		for(ProductInfo pt : pis.getTopSell(1, 5)){
			System.out.println(pt.getName());
		}
	}

}
