package com.customer.controller;

import com.customer.api.Attachment;
import com.customer.api.Ticket;
import com.customer.api.TicketFilter;
import com.customer.api.TicketLog;
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

    @RequestMapping(method = RequestMethod.POST, value= "/search")
    @ResponseBody
    public List<Ticket> getServiceTicketsById(@RequestBody TicketFilter ticketFilter) {
        return ticketService.getTicketsBy(ticketFilter);
    }

    @RequestMapping(method = RequestMethod.GET, value= "/{incident}")
    @ResponseBody
    public  Ticket getTicketByIncident(@PathVariable(value ="incident") Integer incident) {
        return ticketService.getServiceTicketBy(incident);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

    @RequestMapping(method = RequestMethod.GET, value= "/findAll")
    @ResponseBody
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @RequestMapping(method = RequestMethod.PUT, value="/addTicketLog/{incident}")
    @ResponseBody
    public Ticket addTicketLog(@PathVariable(value ="incident") Integer incident, @RequestBody TicketLog ticketLog) {
        return ticketService.addTicketLog(incident, ticketLog);
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
}