package com.customer.util;

import com.customer.api.Ticket;
import com.customer.api.TicketLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Component
public class EmailTemplateBuilder {

    @Autowired
    private TemplateEngine templateEngine;

    public  String build(Ticket ticket, String comment) {
        String ticketLog = "";
        Context context = new Context();
        context.setVariable("incidentId", ticket.getIncident());
        context.setVariable("dealerId", ticket.getDealerId());
        context.setVariable("dealerName", ticket.getDealerName());
        context.setVariable("description", ticket.getDescription());
        context.setVariable("summary", ticket.getSummary());
        context.setVariable("status", ticket.getStatus());
        context.setVariable("priority", ticket.getPriority());
        context.setVariable("category", ticket.getCategory());
        context.setVariable("comment", comment);
        context.setVariable("reportedBy", ticket.getUserName());

        final List<TicketLog> ticketLogList = ticket.getTicketLogList();
        if(!CollectionUtils.isEmpty(ticketLogList) ){
             ticketLog  = ticketLogList.get(0).getDescription();
        }
        context.setVariable("logComment", ticketLog);
        return templateEngine.process("mailTemplate", context);
    }


}
