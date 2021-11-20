package com.customer.service;

import com.customer.api.*;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TicketServiceTest {

    private static final int INCIDENT = 1359315;
    private static final String CATEGORY = "Credit and Compliance Page";
    private static final String SUMMARY = "Compliance - JVM error";
    private static final String PRIORITY = "2-Moderate";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private String nowDate = null;
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

        ticket = createTicketEntity(INCIDENT, CATEGORY, SUMMARY, PRIORITY, nowDate, STATUS, RTE_ONE_DLR_ID, DEALER_NAME, USER_ID, ASSIGNED_TO, "description");
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
        assertThat(actual.getIncident(), is(ticket.getIncident()));
      //  assertThat(actual.getPriority(), is(PRIORITY));
        assertThat(actual.getStatus(), is(STATUS));
        assertThat(actual.getSummary(), is(SUMMARY));
//        assertThat(actual.getUserName(),  is(LAST_NAME));
        assertThat(actual.getTicketLogList().get(0).getCreatedOn(), is(nowDate));
        assertThat(actual.getTicketLogList().get(0).getCreatedBy(), is(USER_ID));
        assertThat(actual.getTicketLogList().get(0).getDescription(), is(LOG_DESCRIPTION));
        assertThat(actual.getTicketLogList().get(0).getLogType(), is(LOG_TYPE));
        assertThat(actual.getAttachments().get(0).getAttachedBy(), is(USER_ID));
        assertThat(actual.getAttachments().get(0).getDescription(), is(DOC_DESCRIPTION));
        assertThat(actual.getAttachments().get(0).getDocName(), is(DOC_NAME));
    }

    @Test
    public void createTicket_callsApi() {

        final Integer ticket = subject.createTicketApi(createTicketEntity(5463, CATEGORY, SUMMARY, PRIORITY, nowDate, STATUS, RTE_ONE_DLR_ID, DEALER_NAME, USER_ID, ASSIGNED_TO,"description" ));

        assertThat(ticket, notNullValue());
    }

    @Test
    public void addTicketLog_returnsTicket() {
        Ticket ticket = createTicketEntity(5463, CATEGORY, SUMMARY, PRIORITY, nowDate, STATUS, RTE_ONE_DLR_ID, DEALER_NAME, USER_ID, ASSIGNED_TO, "description");
        ticket.setTicketLogList(new ArrayList<>());
        subject.createTicket(ticket);
        Ticket serviceTicketBy = subject.getServiceTicketBy(ticket.getIncident());
        assertThat(serviceTicketBy.getTicketLogList().size(), is(0));

        Ticket actual = subject.addTicketLog(ticket.getIncident(), createTicketLog());

        assertThat(actual.getTicketLogList().size(), is(1));
        assertThat(actual.getTicketLogList().get(0).getLogType(), is(LOG_TYPE));
        assertThat(actual.getTicketLogList().get(0).getDescription(), is(LOG_DESCRIPTION));
    }

    @Test
    public void updateTicket_returnsTicket() {
        subject.createTicket(ticket);
        Ticket serviceTicketBy = subject.getServiceTicketBy(ticket.getIncident());
        serviceTicketBy.setCategory("EContracting Service");
        serviceTicketBy.setSummary("EContracting -JVM Error");
        serviceTicketBy.setPriority("Priority3");

        final Ticket actual = subject.updateTicket(serviceTicketBy);

        assertThat(actual.getCategory(), is("EContracting Service"));
        assertThat(actual.getSummary(), is("EContracting -JVM Error"));
        assertThat(actual.getPriority(), is("Priority3"));
    }

    @Test
    public void getServiceTickets_byTicketFilter () {
        Ticket ticket = createTicketEntity(1, "eContracting", "JVM error","3",nowDate,"Esclated","HH3HK", "CCRegression", "QAUSER", "Level3", "description");
        Ticket ticket2 = createTicketEntity(2, "CAS", "JVM error","3",nowDate,"Esclated","HH3HK", "CCRegression", "QAUSER", "Level3", "description provided");
        Ticket ticket3 = createTicketEntity(3, "CAS", "Print","1",nowDate,"InProgress","YO4WL", "Bondo", "QAUSER", "Level3", "description");
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
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void getAllTickets () {
        List<Ticket> actual = subject.getAllTickets("YO4WL");
        assertThat(actual, is(notNullValue()));
    }

    @Test
    public void getTicket_byIncident () {
        Integer incident = 519136;
        final Ticket serviceTicketBy = subject.getServiceTicketByIncident(incident);
        assertThat(serviceTicketBy, is(notNullValue()));
    }

    @Test
    public void addActivityLog() {
        Integer incident = 519136;
        TicketLog ticketLog = new TicketLog();
        ticketLog.setDescription("Ticket Has been created to log the status");
        ticketLog.setStatusRequested("ACK");
        subject.addTicketLogApi(incident, ticketLog);
        final Ticket serviceTicketBy = subject.getServiceTicketByIncident(incident);
        assertThat(serviceTicketBy, is(notNullValue()));
    }

   /* @Test
    public void removeAllTickets() {
        subject.createTicket(ticket);

        final List<Ticket> result = subject.getAllTickets();
        assertThat(result.size(), is(1));
        subject.removeAll();
        final List<Ticket> actual = subject.getAllTickets();
        assertThat(actual.size(), is(0));
    }*/

    private Ticket createTicketEntity(int incident, String category, String summary, String priority, String nowDate, String status, String rteOneDlrId, String dealerName, String userId, String assignedTo, String description) {
        Ticket ticket = new Ticket();
        ticket.setIncident(incident);
        ticket.setCategory(category);
        ticket.setSummary(summary);
        ticket.setPriority("3");
        ticket.setStatus(status);
        ticket.setDealerId(rteOneDlrId);
        ticket.setDescription(description);
        ticket.setTicketLogList(Lists.newArrayList(createTicketLog()));
        ticket.setAttachments(Lists.newArrayList(createAttachment()));
        return ticket;
    }

    private User createUser(String firstName, String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
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
        //attachment.setAttachedTimeStamp(new Date());
        return attachment;
    }


}
