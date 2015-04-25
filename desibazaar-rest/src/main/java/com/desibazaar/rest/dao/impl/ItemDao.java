package com.desibazaar.rest.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.desibazaar.rest.dao.AbstractDao;
import com.desibazaar.rest.dao.IItemDao;
import com.desibazaar.rest.entity.EBid;
import com.desibazaar.rest.entity.EItem;
import com.desibazaar.rest.enums.Status;

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
	public List<EItem> getAuctions(String email) {
		Query query;
		if (email == null) {
			query = getSession().createQuery(
					"from EItem where status = :toStart or status = :active");
		} else {
			query = getSession()
					.createQuery(
							"from EItem where seller.email != :email and (status = :toStart or status = :active)");
			query.setParameter("email", email);
		}
		query.setParameter("toStart", Status.ToStart);
		query.setParameter("active", Status.Active);
		List<EItem> eItems = query.list();
		return eItems;
	}

	@Override
	public void createBid(EBid eBid) {
		save(eBid);
	}

}
