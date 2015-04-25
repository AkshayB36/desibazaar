package com.desibazaar.rest.dao;

import java.util.List;

import com.desibazaar.rest.entity.EBid;
import com.desibazaar.rest.entity.EItem;

/**
 * @author Varda Laud
 *
 */
public interface IItemDao {

	public void createAuction(EItem eItem);

	public void updateAuction(EItem eItem);

	public void deleteAuction(Long itemId);

	public EItem getAuction(Long itemId);

	public List<EItem> getAuctions(String email);

	public void createBid(EBid eBid);
}
