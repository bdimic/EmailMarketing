/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bdimic
 */
@Repository
@Transactional
@Component("broadcastClientsDao")
public class BroadcastClientsDao {
    @Autowired
    private SessionFactory sessionFactory;
    
    public Session session() {
		return sessionFactory.getCurrentSession();
    }
    
    public List<BroadcastClients> getAllBroadcastClients(String broadcast_id) {
        long b_id = Long.parseLong(broadcast_id);
        Criteria crit = session().createCriteria(BroadcastClients.class);
        crit.add(Restrictions.eq("broadcast_id", b_id));
        return crit.list();
    }
    
    public void saveOrUpdate(BroadcastClients broadcastClients) {
	session().saveOrUpdate(broadcastClients);
    }
    
    public boolean delete(String id) {
        long b_id = Long.parseLong(id);
        Query query = session().createQuery("delete from BroadcastClients where broadcast_id=:id");
        query.setLong("id", b_id);
        return query.executeUpdate() == 1;
    }
}
