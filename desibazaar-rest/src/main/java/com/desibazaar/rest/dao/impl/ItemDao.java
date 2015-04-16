package com.desibazaar.rest.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.desibazaar.rest.dao.AbstractDao;
import com.desibazaar.rest.dao.IItemDao;
import com.desibazaar.rest.entity.EBid;
import com.desibazaar.rest.entity.EItem;

/**
 * @author Varda Laud
 *
 */
@Repository
public class ItemDao extends AbstractDao implements IItemDao {

	@Override
	public void createAuction(EItem eItem) {
		save(eItem);
	}

	@Override
	public void updateAuction(EItem eItem) {
		update(eItem);
	}

	@Override
	public void deleteAuction(Long itemId) {
		delete(itemId, EItem.class);
	}

	@Override
	public EItem getAuction(Long itemId) {
		return get(itemId, EItem.class);
	}

	@Override
	public List<EItem> getAuctions() {
		return getAll(EItem.class);
	}

	@Override
	public void createBid(EBid eBid) {
		save(eBid);
	}

}
