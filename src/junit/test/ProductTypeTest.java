package junit.test;

import static org.junit.Assert.*;


import java.util.LinkedHashMap;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bean.QueryResult;
import com.bean.product.Board;
import com.service.product.ProductTypeService;

public class ProductTypeTest {

	private static  ApplicationContext cxt;
	private static 	ProductTypeService ps;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		  try {
			  cxt = new ClassPathXmlApplicationContext("applicationContext.xml");
			  System.out.println(cxt);
			  ps = (ProductTypeService)cxt.getBean("productTypeServiceImpl");
			  System.out.println(ps);
		} catch (Exception e) {		
			e.printStackTrace();
		}	  
	}

	@Test
	public void testSave() {
				
		
		for(int i=0;i<20;i++){
			Board type =new Board();//这个很关键必须在循环里面
			type.setName(i+"远洋瑜伽");
			type.setNote(i+"好的远洋瑜伽");
			//type.setParent(ps.find(ProductType.class, 3));
			ps.save(type);
		
		}
	}
	
	@Test
	public void testFind() {
				
		Board type = ps.find(Board.class, 1);
		assertNotNull("shiofucunzai",type);
		
	}
	
	@Test
	public void testUpdate() {
		Integer i=4;
		while(i<10){
			
			Board type = ps.find(Board.class, i);
			type.setVisible(false);
			ps.update(type);	 //执行更新这一步很重要；	
			i=i+2;
		}
	}
	
	@Test
	public void testDelete() {
					
		ps.delete(Board.class, 1);//可以级联删除
	}
	@Test
	public void testMove(){
		
		Integer[] s = {1,2,3,4};//数组的申明
		ps.delete(Board.class, s);
	}

	@Test
	public void testGetScrollData(){
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("typeid", "asc");
		
//		QueryResult<ProductType> qr = ps.getScrollData(ProductType.class, 0, 5,"o.visible=?1",new Boolean[]{true},orderby);
//		QueryResult<ProductType> qr = ps.getScrollData(ProductType.class, -1, -1);
//		QueryResult<ProductType> qr = ps.getScrollData(ProductType.class, 0, 5,"o.visible=?1",new Boolean[]{true});
//		QueryResult<ProductType> qr = ps.getScrollData(ProductType.class, 0, 5);
		QueryResult<Board> qr = ps.getScrollData(Board.class, 0, 5,orderby);
		
		for(Board pt:qr.getResultlist()){
			System.out.println(pt.getTypeid()+pt.getName());
		}
	}
}
