package com.desibazaar.rest.test.service;

/**
 * @author Sai Sarath Kuchipudi
 *
 */

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.desibazaar.rest.dao.ICategoryDao;
import com.desibazaar.rest.dao.IItemDao;
import com.desibazaar.rest.dao.IUserDao;
import com.desibazaar.rest.entity.EBid;
import com.desibazaar.rest.entity.ECategory;
import com.desibazaar.rest.entity.EItem;
import com.desibazaar.rest.entity.EUser;
import com.desibazaar.rest.service.IBiddingService;
import com.desibazaar.rest.service.ISchedulingService;
import com.desibazaar.rest.service.impl.AuctionService;
import com.desibazaar.rest.vo.Category;
import com.desibazaar.rest.vo.Item;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
public class AuctionServiceTest {

	@InjectMocks
	private AuctionService auctionservice;

	@Mock
	private IUserDao userDao;

	@Mock
	private ICategoryDao catDao;

	@Mock
	private IItemDao itemDao;

	@Mock
	private ISchedulingService schedulingService;

	@Mock
	private IBiddingService biddingService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createAuction() {
		Item item = new Item();
		item.setBasePrice(250f);
		item.setDescription("The second generation of the iPad, the iPad 2, is a tablet computer designed, developed and marketed by Apple Inc. "
				+ "It serves as a platform for audio-visual media including books, news, movies, music, games, presentations and web content.");
		item.setEndsAt(new Date(115, 03, 30, 15, 30, 30));
		item.setName("IPAD2");

		auctionservice.createAuction(item);
		verify(itemDao, times(1)).createAuction(any(EItem.class));
		verifyNoMoreInteractions(itemDao);
	}

	public void updateAuction() {
		Item item = new Item();
		item.setBasePrice(250f);
		item.setDescription("The second generation of the iPad, the iPad 2, is a tablet computer designed, developed and marketed by Apple Inc. "
				+ "It serves as a platform for audio-visual media including books, news, movies, music, games, presentations and web content.");
		item.setEndsAt(new Date(115, 03, 30, 15, 30, 30));
		item.setName("IPAD2");
		item.setItemId(1L);
		long id = item.getItemId();
		EItem eitem = new EItem();
		when(itemDao.getAuction(id)).thenReturn(eitem);
		auctionservice.updateAuction(item);
		verify(itemDao, times(1)).updateAuction(any(EItem.class));
		verify(itemDao, times(1)).getAuction(id);
		verifyNoMoreInteractions(itemDao);

	}

	@Test
	public void deleteAuction() {
		Item item = new Item();
		item.setItemId(1L);
		long id = item.getItemId();
		auctionservice.deleteAuction(id);
		verify(itemDao, times(1)).deleteAuction(id);
		verifyNoMoreInteractions(itemDao);
	}

	@Test
	public void getAuction() {
		EItem eitem = new EItem();
		eitem.setBasePrice(300f);
		eitem.setDescription("The second generation of the iPad, the iPad 2, is a tablet computer designed, developed and marketed by Apple Inc. "
				+ "It serves as a platform for audio-visual media including books, news, movies, music, games, presentations and web content.");
		eitem.setName("IPAD2");
		eitem.setItemId(1L);
		long id = eitem.getItemId();
		when(itemDao.getAuction(id)).thenReturn(eitem);
		Item item = auctionservice.getAuction(id);

		assertEquals(300f, item.getBasePrice(), 0);
		assertEquals(
				"The second generation of the iPad, the iPad 2, is a tablet computer designed, developed and marketed by Apple Inc. "
						+ "It serves as a platform for audio-visual media including books, news, movies, music, games, presentations and web content.",
				item.getDescription());
		assertEquals("IPAD2", item.getName());
		assertEquals(1L, item.getItemId(), 0);

	}

	@Test
	public void getAuctions() {
		List<EItem> eitems = new ArrayList<EItem>();
		EItem first = new EItem();
		first.setItemId(1L);
		first.setName("Samsung Galaxy");

		EItem second = new EItem();
		second.setItemId(2L);
		second.setName("Micromax Canvas");
		eitems.add(first);
		eitems.add(second);

		when(itemDao.getAuctions("ss8990@gmail.com")).thenReturn(eitems);
		List<Item> items = auctionservice.getAuctions("ss8990@gmail.com");
		verify(itemDao, times(1)).getAuctions("ss8990@gmail.com");
		verifyNoMoreInteractions(itemDao);
		assertEquals(1L, items.get(0).getItemId(), 0);
		assertEquals("Samsung Galaxy", items.get(0).getName());
		assertEquals(2L, items.get(1).getItemId(), 0);
		assertEquals("Micromax Canvas", items.get(1).getName());

	}

	@Test
	public void getCategories() {
		List<ECategory> ecats = new ArrayList<ECategory>();
		ECategory first = new ECategory();
		first.setDescription("A smartphone (or smart phone) is a mobile phone with an advanced mobile operating system.[1][2][3]"
				+ " They typically combine the features of a cell phone with those of other popular mobile devices");
		first.setName("SmartPhones");

		ECategory second = new ECategory();
		second.setDescription("A laptop or a notebook is a portable personal computer with a clamshell form factor, suitable for mobile use");
		second.setName("Laptops");
		ecats.add(first);
		ecats.add(second);

		when(catDao.getCategories()).thenReturn(ecats);
		List<Category> cats = auctionservice.getCategories();
		verify(catDao, times(1)).getCategories();
		verifyNoMoreInteractions(catDao);
		assertEquals(
				"A smartphone (or smart phone) is a mobile phone with an advanced mobile operating system.[1][2][3]"
						+ " They typically combine the features of a cell phone with those of other popular mobile devices",
				cats.get(0).getDescription());
		assertEquals("SmartPhones", cats.get(0).getName());
		assertEquals(
				"A laptop or a notebook is a portable personal computer with a clamshell form factor, suitable for mobile use",
				cats.get(1).getDescription());
		assertEquals("Laptops", cats.get(1).getName());

	}

	@Test
	public void subscribeAuction() {
		EItem eitem = new EItem();
		eitem.setBasePrice(300f);
		eitem.setDescription("The second generation of the iPad, the iPad 2, is a tablet computer designed, developed and marketed by Apple Inc. "
				+ "It serves as a platform for audio-visual media including books, news, movies, music, games, presentations and web content.");
		eitem.setName("IPAD2");
		eitem.setItemId(1L);
		eitem.setSubscribers(new ArrayList<EUser>());
		long id = eitem.getItemId();
		when(itemDao.getAuction(id)).thenReturn(eitem);
		auctionservice.subscribeAuction(id, "ss8990@gmail.com");
		verify(itemDao, times(1)).getAuction(id);
		verify(itemDao, times(1)).updateAuction(eitem);
		verifyNoMoreInteractions(itemDao);
	}

	@Test
	public void unsubscribeAuction() {
		EItem eitem = new EItem();
		eitem.setBasePrice(300f);
		eitem.setDescription("The second generation of the iPad, the iPad 2, is a tablet computer designed, developed and marketed by Apple Inc. "
				+ "It serves as a platform for audio-visual media including books, news, movies, music, games, presentations and web content.");
		eitem.setName("IPAD2");
		eitem.setItemId(1L);
		eitem.setSubscribers(new ArrayList<EUser>());
		long id = eitem.getItemId();
		when(itemDao.getAuction(id)).thenReturn(eitem);
		auctionservice.unsubscribeAuction(id, "ss8990@gmail.com");
		verify(itemDao, times(1)).getAuction(id);
		verify(itemDao, times(1)).updateAuction(eitem);
		verifyNoMoreInteractions(itemDao);
	}

	@Test
	public void createBid() {
		EItem item = new EItem();
		item.setItemId(1L);
		item.setBasePrice(300f);
		long id = item.getItemId();
		EBid bid = new EBid();
		bid.setBid(255f);
		float bamt = bid.getBid();
		when(itemDao.getAuction(id)).thenReturn(item);
		auctionservice.createBid(id, "ss8990@gmail.com", bamt);
		verify(itemDao, times(1)).getAuction(id);
		verifyNoMoreInteractions(itemDao);
	}

	@Test
	public void getBids() {
		List<EBid> ebids = new ArrayList<EBid>();
		EBid first = new EBid();
		EItem item1 = new EItem();
		item1.setItemId(1L);
		first.setBid(234f);
		first.setItem(item1);
		long id = item1.getItemId();
		EBid second = new EBid();
		second.setBid(355f);
		second.setItem(item1);
		ebids.add(first);
		ebids.add(second);
		auctionservice.getBids(id);
		assertEquals(234f, first.getBid(), 0);
		assertEquals(1L, item1.getItemId(), 0);
		assertEquals(item1, first.getItem());
		assertEquals(item1, second.getItem());
	}

}