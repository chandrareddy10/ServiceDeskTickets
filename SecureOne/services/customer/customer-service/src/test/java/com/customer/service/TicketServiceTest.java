package com.customer.service;

import com.customer.api.Attachment;
import com.customer.api.Ticket;
import com.customer.api.TicketFilter;
import com.customer.api.TicketLog;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TicketServiceTest {

    private static final int INCIDENT = 1359315;
    private static final String CATEGORY = "Credit and Compliance Page";
    private static final String SUMMARY = "Compliance - JVM error";
    private static final String PRIORITY = "2-Moderate";
    private Date nowDate = null;
    private static final String STATUS = "InProgress";
    private static final String RTE_ONE_DLR_ID = "YO4WL";
    private static final String DEALER_NAME = "Bondo Test Dlrship";
    private static final String USER_ID = "TESTUSER";
    private static final String ASSIGNED_TO = "Level2";
    private static final String LOG_TYPE = "Service Type";
    private static final String LOG_DESCRIPTION = "Awaiting Customer Response";
    private static final String DOC_NAME = "ComplianceError";
    private static final String DOC_DESCRIPTION = "Session";
    private Ticket ticket;

    @Autowired
    private TicketService subject;

    @Before
    public void setUp(){
        nowDate = new Date();
        ticket = createTicketEntity(INCIDENT, CATEGORY, SUMMARY, PRIORITY, nowDate, STATUS, RTE_ONE_DLR_ID, DEALER_NAME, USER_ID, ASSIGNED_TO);
    }
    @After
    public void after() {
        subject.removeAll();
    }

    @Test
    public void createTicket_returnsTicket() {
        subject.createTicket(ticket);
        Ticket actual = subject.getServiceTicketBy(ticket.getIncident());
        assertThat(actual.getDealerId(), is(RTE_ONE_DLR_ID));
        assertThat(actual.getCategory(), is(CATEGORY));
        assertThat(actual.getUserId(), is(USER_ID));
        assertThat(actual.getDealerName(), is(DEALER_NAME));
        assertThat(actual.getAssignedTo(), is(ASSIGNED_TO));
        assertThat(actual.getIncident(), is(INCIDENT));
        assertThat(actual.getPriority(), is(PRIORITY));
        assertThat(actual.getStatus(), is(STATUS));
        assertThat(actual.getSummary(), is(SUMMARY));
        //assertThat(actual.getOpenDate(), is(nowDate));
       // assertThat(actual.getTicketLogList().get(0).getCreatedOn(), is(nowDate));
        assertThat(actual.getTicketLogList().get(0).getCreatedBy(), is(USER_ID));
        assertThat(actual.getTicketLogList().get(0).getDescription(), is(LOG_DESCRIPTION));
        assertThat(actual.getTicketLogList().get(0).getLogType(), is(LOG_TYPE));
        assertThat(actual.getAttachments().get(0).getAttachedBy(), is(USER_ID));
        assertThat(actual.getAttachments().get(0).getDescription(), is(DOC_DESCRIPTION));
        assertThat(actual.getAttachments().get(0).getDocName(), is(DOC_NAME));
    }

    @Test
    public void addTicketLog_returnsTicket() {
        Ticket ticket = createTicketEntity(5463, CATEGORY, SUMMARY, PRIORITY, nowDate, STATUS, RTE_ONE_DLR_ID, DEALER_NAME, USER_ID, ASSIGNED_TO);
        ticket.setTicketLogList(new ArrayList<>());
        subject.createTicket(ticket);
        Ticket serviceTicketBy = subject.getServiceTicketBy(ticket.getIncident());
        assertThat(serviceTicketBy.getTicketLogList().size(), is(0));

        Ticket actual = subject.addTicketLog(5463, createTicketLog());

        assertThat(actual.getTicketLogList().size(), is(1));
        assertThat(actual.getTicketLogList().get(0).getLogType(), is(LOG_TYPE));
        assertThat(actual.getTicketLogList().get(0).getDescription(), is(LOG_DESCRIPTION));
    }

    @Test
    public void updateTicket_returnsTicket() {
        subject.createTicket(ticket);
        Ticket actual = subject.getServiceTicketBy(ticket.getIncident());

        assertThat(actual.getDealerId(), is(RTE_ONE_DLR_ID));
        assertThat(actual.getCategory(), is(CATEGORY));
        assertThat(actual.getUserId(), is(USER_ID));


        assertThat(actual.getDealerName(), is(DEALER_NAME));
        assertThat(actual.getAssignedTo(), is(ASSIGNED_TO));
        assertThat(actual.getIncident(), is(INCIDENT));
        assertThat(actual.getPriority(), is(PRIORITY));
        assertThat(actual.getStatus(), is(STATUS));
        assertThat(actual.getSummary(), is(SUMMARY));
        //assertThat(actual.getOpenDate(), is(nowDate));
        // assertThat(actual.getTicketLogList().get(0).getCreatedOn(), is(nowDate));
        assertThat(actual.getTicketLogList().get(0).getCreatedBy(), is(USER_ID));
        assertThat(actual.getTicketLogList().get(0).getDescription(), is(LOG_DESCRIPTION));
        assertThat(actual.getTicketLogList().get(0).getLogType(), is(LOG_TYPE));
        assertThat(actual.getAttachments().get(0).getAttachedBy(), is(USER_ID));
        assertThat(actual.getAttachments().get(0).getDescription(), is(DOC_DESCRIPTION));
        assertThat(actual.getAttachments().get(0).getDocName(), is(DOC_NAME));
    }

    @Test
    public void getServiceTickets_byTicketFilter () {
        Ticket ticket = createTicketEntity(1, "eContracting", "JVM error","3",nowDate,"Esclated","HH3HK", "CCRegression", "QAUSER", "Level3");
        Ticket ticket2 = createTicketEntity(2, "CAS", "JVM error","3",nowDate,"Esclated","HH3HK", "CCRegression", "QAUSER", "Level3");
        Ticket ticket3 = createTicketEntity(3, "CAS", "Print","1",nowDate,"InProgress","YO4WL", "Bondo", "QAUSER", "Level3");
        subject.createTicket(ticket);
        subject.createTicket(ticket2);
        subject.createTicket(ticket3);
        List<Ticket> actual = subject.getTicketsBy(getTicketFilter(null, "Esclated"));

        assertThat(actual.size(), is(2));
    }


    public TicketFilter getTicketFilter(String category, String status) {
        TicketFilter ticketFilter = new TicketFilter();
        ticketFilter.setSearchByCategory(category);
        ticketFilter.setSearchByStatus(status);
        return ticketFilter;
    }

    @Test
    public void getTickets_byTicketFilter () {
        subject.createTicket(ticket);
        Ticket actual = subject.getServiceTicketBy(ticket.getIncident());
    }

    @Test
    public void removeAllTickets() {
        subject.createTicket(ticket);

        final List<Ticket> result = subject.getAllTickets();
        assertThat(result.size(), is(1));
        subject.removeAll();
        final List<Ticket> actual = subject.getAllTickets();
        assertThat(actual.size(), is(0));
    }

    private Ticket createTicketEntity(int incident, String category, String summary, String priority, Date nowDate, String status, String rteOneDlrId, String dealerName, String userId, String assignedTo) {
        Ticket ticket = new Ticket();
        ticket.setIncident(incident);
        ticket.setCategory(category);
        ticket.setSummary(summary);
        ticket.setPriority(priority);
        ticket.setOpenDate(nowDate);
        ticket.setStatus(status);
        ticket.setDealerId(rteOneDlrId);
        ticket.setDealerName(dealerName);
        ticket.setUserId(userId);
        ticket.setAssignedTo(assignedTo);
        ticket.setTicketLogList(Lists.newArrayList(createTicketLog()));
        ticket.setAttachments(Lists.newArrayList(createAttachment()));
        ticket.setLastModificationDate(new Date());
        return ticket;
    }

    private TicketLog createTicketLog() {
        TicketLog ticketLog = new TicketLog();
        ticketLog.setCreatedBy(USER_ID);
        ticketLog.setCreatedOn(nowDate);
        ticketLog.setLogType(LOG_TYPE);
        ticketLog.setDescription(LOG_DESCRIPTION);
        return ticketLog;
    }

    private Attachment createAttachment() {
        Attachment attachment = new Attachment();
        attachment.setDocName(DOC_NAME);
        attachment.setDescription(DOC_DESCRIPTION);
        attachment.setAttachedBy(USER_ID);
        attachment.setAttachedTimeStamp(nowDate);
        return attachment;
    }


}
