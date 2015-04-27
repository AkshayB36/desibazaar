package com.desibazaar.rest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;



import com.desibazaar.rest.dao.IItemDao;
import com.desibazaar.rest.entity.EBid;
import com.desibazaar.rest.entity.ECategory;
import com.desibazaar.rest.entity.EItem;
import com.desibazaar.rest.entity.EUser;
import com.desibazaar.rest.enums.Status;

/**
 * @author Sai Sarath Kuchipudi
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
public class ItemDaoTest {

	@Autowired
	private IItemDao itemDao;

	@Test
	public void getAuctions() throws Exception {
		itemDao.getAuctions("");
		List<EItem> auctions = itemDao.getAuctions("");
		auctions.size();
		assertEquals(4, auctions.size());
		EItem eitem1 = auctions.get(2);
		assertEquals("Abacus Toy", eitem1.getName());
		assertEquals("Recommended age: 2 to 4 years Standard Abacus 1 to 10", eitem1.getDescription());
		assertEquals(767f, eitem1.getBasePrice(), 0);
		assertEquals("Toy", eitem1.getCategory().getName());
		assertEquals(new Timestamp(115,03,30,18,30,30,0),eitem1.getEndsAt());
		assertEquals("img/abacus.jpg", eitem1.getImage());
		assertEquals(4, eitem1.getRating(), 0);
		assertEquals("Worth every dollar", eitem1.getReview());
		assertEquals(new Timestamp(115,03,27,13,30,30,0),eitem1.getStartsAt());
		assertEquals(Status.ToStart, eitem1.getStatus());
	}

	@Test
	public void createAuction() {
		EItem eitem2 = new EItem();
		EUser user = new EUser();
		user.setEmail("ss8990@gmail.com");
		ECategory ecat = new ECategory();
		ecat.setName("Tablets");
		eitem2.setName("Ipad2");
		eitem2.setDescription("The second generation of the iPad, the iPad 2, is a tablet computer designed, developed and marketed by Apple Inc.");
		eitem2.setBasePrice(300.0F);
		eitem2.setImage("img/ipad2.jpg");
		eitem2.setRating(4);
		eitem2.setReview("Good");
		eitem2.setStatus(Status.Sold);
		eitem2.setSellingPrice(750F);
		eitem2.setEndsAt(new java.util.Date(2015, 04, 30, 12, 00, 00));
		eitem2.setStartsAt(new java.util.Date(2015, 04, 15, 12, 00, 00));
		eitem2.setCategory(ecat);
		eitem2.setSeller(user);
		itemDao.createAuction(eitem2);
		assertNotEquals(eitem2.getItemId(),null);
	}

	@Test
	public void updateAuction() {
		EItem eitem3=new EItem();
		eitem3=itemDao.getAuction(1L);
		eitem3.setName("Apple");
		itemDao.updateAuction(eitem3);
		assertEquals("Apple" ,eitem3.getName());
	}
	
	@Test
	public void deleteAuction() {
		itemDao.deleteAuction(1L);
		assertEquals(null,itemDao.getAuction(1L));
	}
	
	@Test
	public void getAuction() {
		EItem eitem4=itemDao.getAuction(1L);
		assertEquals("Prestige Cooker",eitem4.getName());
		assertEquals(100f,eitem4.getBasePrice(),0);
		}

	@Test
	public void createBid() {
		EBid ebid=new EBid();
		EItem eitem5=new EItem();
		EUser user=new EUser();
		ebid.setBid(150f);
		ebid.setItem(eitem5);
		ebid.setTime(new Date(0));
		ebid.setUser(user);
		itemDao.createBid(ebid);
	}
}