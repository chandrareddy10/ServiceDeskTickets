package com.customer.controller;

import com.customer.api.Attachment;
import com.customer.api.Ticket;
import com.customer.api.TicketFilter;
import com.customer.api.TicketLog;
import com.customer.service.EmailService;
import com.customer.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value= "/serviceDesk", produces={MediaType.APPLICATION_JSON_VALUE})
@RestController
public class ServiceDeskController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(method = RequestMethod.POST, value= "/search")
    @ResponseBody
    public List<Ticket> getServiceTicketsById(@RequestBody TicketFilter ticketFilter) {
        return ticketService.getTicketsBy(ticketFilter);
    }

    @RequestMapping(method = RequestMethod.GET, value= "/{incident}")
    @ResponseBody
    public  Ticket getTicketByIncident(@PathVariable(value ="incident") Integer incident) {
        return ticketService.getServiceTicketByIncident(incident);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Integer createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicketApi(ticket);
    }

    @RequestMapping(method = RequestMethod.GET, value= "/findAll/{dealerId}")
    @ResponseBody
    public List<Ticket> getAllTickets(@PathVariable(value="dealerId") String dealerId) {
        return ticketService.getAllTickets(dealerId);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/addTicketLog/{incident}")
    @ResponseBody
    public Ticket addTicketLog(@PathVariable(value ="incident") Integer incident, @RequestBody TicketLog ticketLog) {
        final Integer integer = ticketService.addTicketLogApi(incident, ticketLog);
        final Ticket serviceTicketByIncident = ticketService.getServiceTicketByIncident(incident);
        emailService.sendSupportEmail(serviceTicketByIncident);
        return serviceTicketByIncident;
    }

    @RequestMapping(method = RequestMethod.PUT, value="/addAttachment/{incident}")
    @ResponseBody
    public Ticket addAttachment(@PathVariable(value ="incident") Integer incident, @RequestBody Attachment attachment) {
        return ticketService.addAttachment(incident, attachment);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/update")
    @ResponseBody
    public Ticket updateTicket(@RequestBody Ticket ticket) {
        return ticketService.updateTicket(ticket);
    }

    //Remove Attachment
}