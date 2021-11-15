package com.customer.service;

import com.customer.api.Attachment;
import com.customer.api.Ticket;
import com.customer.api.TicketFilter;
import com.customer.api.TicketLog;
import com.customer.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket) {
        ticket.setIncident(getIncident());
        ticket  = ticketRepository.insert(ticket);

        return ticket;
    }

    private Integer getIncident() {
        Integer max = 80001;
        Integer min = 99999;
        return (int)Math.random()*(max-min+1)+min;
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAllBy();
    }

    public void removeAll(){
        ticketRepository.removeAllBy();
    }

    public Ticket getServiceTicketBy(Integer incident) {
        List<Ticket> ticketList = ticketRepository.findByIncident(incident);
        if(ticketList != null){
            return ticketList.get(0);
        }
        return null;
    }


    public List<Ticket> getTicketsBy(TicketFilter ticketFilter) {
        if(ticketFilter == null) {
            return new ArrayList<>();
        }
        if(ticketFilter.getSearchByDealerId() != null) {
            return ticketRepository.findByDealerId(ticketFilter.getSearchByDealerId());
        } else if(ticketFilter.getSearchByIncident() != null) {
            return ticketRepository.findByIncident(ticketFilter.getSearchByIncident());
        } else if(ticketFilter.getSearchByCategory() != null) {
            return  ticketRepository.findByCategory(ticketFilter.getSearchByCategory());
        } else if(ticketFilter.getSearchByPriority() != null) {
            return ticketRepository.findByPriority(ticketFilter.getSearchByPriority());
        } else if(ticketFilter.getSearchByStatus() != null) {
            return ticketRepository.findByStatus(ticketFilter.getSearchByStatus());
        } else if(ticketFilter.getSearchByUserId() != null) {
            return ticketRepository.findByUserId(ticketFilter.getSearchByUserId());
        } else if(ticketFilter.getSearchBySummary() != null) {
            return ticketRepository.findBySummary(ticketFilter.getSearchBySummary());
        }

        return new ArrayList<>();
    }

    public Ticket addTicketLog(Integer incident, TicketLog ticketLog) {
        final List<Ticket> ticketList = ticketRepository.findByIncident(incident);
        if(CollectionUtils.isEmpty(ticketList)){
            return new Ticket();
        }
        final Ticket ticket = ticketList.get(0);
        ticket.getTicketLogList().add(ticketLog);

        return ticketRepository.save(ticket);
    }

    public Ticket addAttachment(Integer incident, Attachment attachment) {
        final List<Ticket> ticketList = ticketRepository.findByIncident(incident);
        if(CollectionUtils.isEmpty(ticketList)){
            return new Ticket();
        }
        final Ticket ticket = ticketList.get(0);
        ticket.getAttachments().add(attachment);

        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Ticket ticket) {
        final List<Ticket> byIncident = ticketRepository.findByIncident(ticket.getIncident());
        if(CollectionUtils.isEmpty(byIncident)) {
            return ticket;
        }
        final Ticket currentTicket = byIncident.get(0);
        translate(currentTicket, ticket);
        return ticketRepository.save(currentTicket);
    }

    private void translate(Ticket currentTicket, Ticket ticket) {
        if(ticket.getCategory() != null) {
            currentTicket.setCategory(ticket.getCategory());
        }
        if(ticket.getPriority() != null) {
            currentTicket.setPriority(ticket.getPriority());
        }
        if(ticket.getStatus() != null) {
            currentTicket.setStatus(ticket.getStatus());
        }
        if(ticket.getSummary() != null) {
            currentTicket.setSummary(ticket.getSummary());
        }
        if(ticket.getDealerId() != null) {
            currentTicket.setDealerId(ticket.getDealerId());
        }
        if(ticket.getDealerName() != null) {
            currentTicket.setDealerName(ticket.getDealerName());
        }
        if(ticket.getOpenDate() != null) {
            currentTicket.setOpenDate(ticket.getOpenDate());
        }
        if(ticket.getLastModificationDate() != null) {
            currentTicket.setLastModificationDate(ticket.getLastModificationDate());
        }
        if(ticket.getUserId() != null) {
            currentTicket.setUserId(ticket.getUserId());
        }
    }
}
