package com.customer.service;

import com.customer.api.Attachment;
import com.customer.api.Ticket;
import com.customer.api.TicketFilter;
import com.customer.api.TicketLog;
import com.customer.caServiceDesk.api.*;
import com.customer.caServiceDesk.client.CADeskClient;
import com.customer.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class TicketService {

    public static final String MICHIGAN_AUTO_DEALER = "Southfield Avis Ford ";
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CADeskClient caDeskClient;

    protected static HashMap<String, String> categoryMap = new HashMap<>();

    static
    {
        categoryMap.put("Password Reset", "pcat:400162");
        categoryMap.put("R1 Application Functioned Unexpectedly", "pcat:400135");
        categoryMap.put("Credit Report.Error Message", "pcat:400250");
    }

    public Ticket createTicket(Ticket ticket) {
        ticket.setIncident(getIncident());
        ticket  = ticketRepository.insert(ticket);

        return ticket;
    }

    public Integer createTicketApi(Ticket ticket) {
        CreateIncidentRequest createIncidentRequest = new CreateIncidentRequest();
        CreateIncident createIncident = getCreateIncident(ticket);
        createIncidentRequest.setCreateIncident(createIncident);

        final CreateResponse response = caDeskClient.createTicket(createIncidentRequest);

        return response.getCreateIncidentResponse().getIncident();
    }

    private CreateIncident getCreateIncident(Ticket ticket) {
        String contractConversationId = ticket.getContractConversationId();
        String dealId = ticket.getDealId();
        String newCreditAppCvrsId = ticket.getNewCreditAppCvrsId();
        String oldCreditAppCvrsId = ticket.getOldCreditAppCvrsId();

        CreateIncident createIncident = new CreateIncident();
        createIncident.setDealerId(ticket.getDealerId());
        createIncident.setContractConversationId(StringUtils.isEmpty(contractConversationId) ?"01-3-1000001":contractConversationId);
        createIncident.setOldCreditAppCvrsId(StringUtils.isEmpty(oldCreditAppCvrsId)?"01-1-1000001":oldCreditAppCvrsId);
        createIncident.setNewCreditAppCvrsId(StringUtils.isEmpty(newCreditAppCvrsId)?"01-1-1000002":newCreditAppCvrsId);
        createIncident.setDealNumber(StringUtils.isEmpty(dealId)?"DJ-1-10001":dealId);
        createIncident.setDescription(ticket.getDescription());
        createIncident.setSummary(ticket.getSummary());

        CACategoryRequest caCategoryRequest = new CACategoryRequest();
        caCategoryRequest.setRelativeAttribute(ticket.getCategory()==null?"pcat:400162":ticket.getCategory());
        createIncident.setCaCategory(caCategoryRequest);

        CAPriorityRequest caPriorityRequest = new CAPriorityRequest();
        caPriorityRequest.setRelativeAttribute("2");
        createIncident.setCaPriority(caPriorityRequest);

        CAStatusRequest caStatusRequest = new CAStatusRequest();
        caStatusRequest.setRelativeAttribute("OP");
        createIncident.setCaStatus(caStatusRequest);

        CAGroupRequest caGroupRequest = new CAGroupRequest();
        caGroupRequest.setRelativeAttribute("812455410B6E9948804F76E59C64E0D3");
        createIncident.setCaGroup(caGroupRequest);

        CAReportingRequest caReportingRequest = new CAReportingRequest();
        caReportingRequest.setRelativeAttribute("7302");
        createIncident.setCaReporting(caReportingRequest);

        CARequested caRequested = new CARequested();
        caRequested.setRelativeAttribute("45B8912687C6F14D838D9FE23CB20A23");
        createIncident.setCaRequested(caRequested);

        CustomerRequest customerRequest = new CustomerRequest();
         customerRequest.setRelativeAttribute(ticket.getUserId());
        createIncident.setCustomer(customerRequest);

        return createIncident;
    }

    private Integer getIncident() {
        Integer max = 10000;
        Integer min = 99999;
        return (int)Math.random()*(max-min+1)+min;
    }

    public List<Ticket> getAllTickets(String dealerId){
        return translateToTickets(caDeskClient.getTickets(dealerId));
    }

    private List<Ticket> translateToTickets(CADeskResponse response) {
        List<Ticket> ticketList =  new ArrayList<>();
        List<Incident> incidents = response.getCollectionIn().getIncident();
        for (Incident incident: incidents) {
            ticketList.add(translateTicket(incident));
        }
        return ticketList;
    }

    private Ticket translateTicket(Incident incident) {
        Ticket ticket = new Ticket();

        ticket.setIncident(incident.getId());
        if(incident.getCaCategory() !=  null){
            ticket.setCategory(incident.getCaCategory().getCategory());
        }
        if(incident.getCaPriority() != null) {
            ticket.setPriority(incident.getCaPriority().getPriority());
        }
        ticket.setSummary(incident.getSummary());
        ticket.setDealerId(incident.getDealerId());
        ticket.setDealerName(MICHIGAN_AUTO_DEALER);
        if(incident.getCustomer() != null) {
            ticket.setUserName(incident.getCustomer().getCustomerName());
            ticket.setUserId(incident.getCustomer().getCustomerName());
        }
        if(incident.getCaStatus() != null) {
            ticket.setStatus(incident.getCaStatus().getStatus());
        }
        ticket.setDescription(incident.getDescription());
        ticket.setLastModificationDate(convertDate(incident.getLastModifiedDate()));
        if(incident.getCaReporting() !=  null){
            ticket.setReportingMethod(incident.getCaReporting().getReportingMethod());
        }
        if(incident.getCaGroup() != null) {
            ticket.setGroupName(incident.getCaGroup().getGroupName());
        }
        ticket.setOpenDate(convertDate(incident.getOpenDate()));

        return ticket;
    }

    private Ticket translateSingleTicket(Incident incident) {
        Ticket ticket = new Ticket();

        ticket.setIncident(incident.getId());
        if(incident.getCaCategory() !=  null){
            ticket.setCategory(incident.getCaCategory().getCategory());
        }
        if(incident.getCaPriority() != null) {
            ticket.setPriority(incident.getCaPriority().getPriority());
        }
        ticket.setSummary(incident.getSummary());
        ticket.setDealerId(incident.getDealerId());
        ticket.setDealerName(MICHIGAN_AUTO_DEALER);
        if(incident.getCustomer() != null) {
            ticket.setUserName(incident.getCustomer().getCustomerName());
        }
        if(incident.getCaStatus() != null) {
            ticket.setStatus(incident.getCaStatus().getStatus());
        }
        ticket.setDescription(incident.getDescription());
        ticket.setLastModificationDate(convertDate(incident.getLastModifiedDate()));
        if(incident.getCaReporting() !=  null){
            ticket.setReportingMethod(incident.getCaReporting().getReportingMethod());
        }
        if(incident.getCaGroup() != null) {
            ticket.setGroupName(incident.getCaGroup().getGroupName());
        }
        if(incident.getActivityLog() != null) {
            ticket.getTicketLogList().addAll(getActivityLink(incident.getActivityLog(), incident.getId()));
        }
        //if(incident.getAttachmentLink() !=null) {
            ticket.getAttachments().addAll(getAttachments(incident.getAttachmentLink(), 403728));
       // }
        ticket.setOpenDate(convertDate(incident.getOpenDate()));

        return ticket;
    }

    private List<Attachment> getAttachments(AttachmentLink attachmentLink, Integer incident) {
        List<Attachment> attachments = new ArrayList<>();
        if(attachmentLink.getLink() != null) {
            final AttachmentResponse attachmentResponse = caDeskClient.getAttachments(incident);
            if(attachmentResponse !=null && attachmentResponse.getAttachmentEntity() != null){
                final AttachmentEntity attachmentEntity = attachmentResponse.getAttachmentEntity();
                Attachment attachment = new Attachment();
                attachment.setDocName(attachmentEntity.getFileName());
                attachment.setDescription(attachmentEntity.getAttachmentDesc());
                if(attachmentEntity.getCreatedBy() != null){

                    attachment.setAttachedBy(attachmentEntity.getCreatedBy().getCreatedName());
                }
                attachment.setAttachedTimeStamp(convertDate(attachmentEntity.getCreatedDate()));
                attachments.add(attachment);
            }
        }
        return attachments;
    }

    private List<TicketLog> getActivityLink(ActivityLog activityLog, Integer incidentId) {
        List<TicketLog> ticketLogs  = new ArrayList<>();

        if(activityLog.getLink() != null && activityLog.getLink().getHref() != null) {
             ActivityLogResponse activityLogResponse = caDeskClient.getActivityLogs(incidentId);
             if(activityLogResponse == null || activityLogResponse.getActivityCollection() == null
                            || CollectionUtils.isEmpty(activityLogResponse.getActivityCollection().getActivityEntityList())) {
                 return ticketLogs;
             }
            List<ActivityEntity> activityEntityList = activityLogResponse.getActivityCollection().getActivityEntityList();
            for (ActivityEntity activityEntity: activityEntityList) {
                TicketLog ticketLog = new TicketLog();
                ticketLog.setLogType("Log Comment");
                ticketLog.setDescription(activityEntity.getLogComment());
                if(activityEntity.getAnalyst() != null) {
                    ticketLog.setCreatedBy(activityEntity.getAnalyst().getAnalystName());
                }
                ticketLog.setCreatedOn(convertDate(activityEntity.getLastModifiedDate()));
                ticketLogs.add(ticketLog);
            }
        }
        return ticketLogs;
    }

    private String convertDate(Date openDate) {
        if(openDate == null) {
            return null;
        }
        Long milliSeconds = openDate.getTime()*1000;

        Format formatter = new SimpleDateFormat("MMM dd yyyy hh.mm aa");
        String date = formatter.format(milliSeconds);
        return date;
    }

    public void removeAll(){
        ticketRepository.removeAllBy();
    }

    public Ticket getServiceTicketBy(Integer incident) {
        List<Ticket> ticketList = ticketRepository.findByIncident(incident);
        if(ticketList != null) {
            return ticketList.get(0);
        }
        return null;
    }

    public Ticket getServiceTicketByIncident(Integer incident) {
        final IncidentEntity incidentEntity = caDeskClient.getTicket_ByTicketId(incident);
        return translateSingleTicket(incidentEntity.getIncident());
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
        } else if(ticketFilter.getSearchBySummary() != null) {
            return ticketRepository.findBySummary(ticketFilter.getSearchBySummary());
        } else if(ticketFilter.getSearchByDescription() != null) {
            return ticketRepository.findByDescription(ticketFilter.getSearchByDescription());
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

    public Integer addTicketLogApi(Integer incident, TicketLog ticketLog) {
        CreateLogRequest createLogRequest = getCreateLogRequest(incident, ticketLog);
        CreateLogResponse createLogResponse = caDeskClient.addActivityLog(createLogRequest, incident);
        if(createLogResponse != null && createLogResponse.getLogResponseEntity() != null) {
            return createLogResponse.getLogResponseEntity().getId();
        }
        return incident;
    }


    private CreateLogRequest getCreateLogRequest(Integer incident, TicketLog ticketLog) {
        CreateLogRequest createLogRequest = new CreateLogRequest();
        CreateLog createLog = new CreateLog();
        createLog.setId(incident.toString());
        CreateLogStatus createLogStatus  = new CreateLogStatus();
        createLogStatus.setRelativeAttribute(ticketLog.getStatusRequested());
        createLogStatus.setDescription(ticketLog.getDescription());
        createLog.setCreateLogStatus(createLogStatus);
        createLogRequest.setCreateLog(createLog);

        return createLogRequest;
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

        if(ticket.getOpenDate() != null) {
            currentTicket.setOpenDate(ticket.getOpenDate());
        }
        if(ticket.getLastModificationDate() != null) {
            currentTicket.setLastModificationDate(ticket.getLastModificationDate());
        }
    }
}
