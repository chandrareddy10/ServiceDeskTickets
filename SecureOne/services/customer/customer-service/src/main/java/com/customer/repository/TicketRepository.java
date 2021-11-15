package com.customer.repository;

import com.customer.api.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TicketRepository extends MongoRepository<Ticket, String> {

    public List<Ticket> findByDealerId(String dealerId);
    public List<Ticket> findByUserId(String userId);
    public List<Ticket> findByIncident(Integer incident);
    public List<Ticket> findByCategory(String category);
    public List<Ticket> findByPriority(String priority);
    public List<Ticket> findAllBy();
    public List<Ticket> findByStatus(String status);
    public List<Ticket> findBySummary(String summary);

    public void removeAllBy();

}