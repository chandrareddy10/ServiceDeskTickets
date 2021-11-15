package com.customer;

import com.customer.api.Attachment;
import com.customer.api.Ticket;
import com.customer.api.TicketFilter;
import com.customer.api.TicketLog;
import com.customer.client.ServiceTicketClient;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class ServiceDeskIT {

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

	private ServiceTicketClient serviceTicketClient = new ServiceTicketClient("localhost", "8080");
	private Ticket ticket;
	
	@Before
	public void setup(){

	}

	@Test
	public void getServiceTicket_byIncidentId() {
		Ticket ticketEntity = createTicketEntity(INCIDENT, CATEGORY, SUMMARY, PRIORITY, nowDate, STATUS, RTE_ONE_DLR_ID, DEALER_NAME, USER_ID, ASSIGNED_TO);
		final Ticket savedTicket = serviceTicketClient.createTicket(ticketEntity);
		Ticket actual = serviceTicketClient.getTicketById(savedTicket.getIncident());

		MatcherAssert.assertThat(actual.getDealerId(), is(RTE_ONE_DLR_ID));
		MatcherAssert.assertThat(actual.getCategory(), is(CATEGORY));
		MatcherAssert.assertThat(actual.getUserId(), is(USER_ID));
		MatcherAssert.assertThat(actual.getDealerName(), is(DEALER_NAME));
		MatcherAssert.assertThat(actual.getAssignedTo(), is(ASSIGNED_TO));
		MatcherAssert.assertThat(actual.getIncident(), is(INCIDENT));
		MatcherAssert.assertThat(actual.getPriority(), is(PRIORITY));
		MatcherAssert.assertThat(actual.getStatus(), is(STATUS));
		MatcherAssert.assertThat(actual.getSummary(), is(SUMMARY));
	}

	@Test
	public void updateServiceTicket_byIncident() {
		Ticket ticketEntity = createTicketEntity(INCIDENT, CATEGORY, SUMMARY, PRIORITY, nowDate, STATUS, RTE_ONE_DLR_ID, DEALER_NAME, USER_ID, ASSIGNED_TO);
		final Ticket savedTicket = serviceTicketClient.createTicket(ticketEntity);

		Ticket getTicket = serviceTicketClient.getTicketById(savedTicket.getIncident());

		getTicket.setPriority("Level3");
		getTicket.setCategory("EContractingTest");
		getTicket.setSummary("Econtracting Summary");

		final Ticket actual = serviceTicketClient.updateTicket(getTicket);

		MatcherAssert.assertThat(actual.getPriority(),  is("Level3"));
		MatcherAssert.assertThat(actual.getCategory(),  is("EContractingTest"));
		MatcherAssert.assertThat(actual.getSummary(),  is("Econtracting Summary"));
	}

	@Test
	public void getAllTickets_findAll() {
		List<Ticket> actual = serviceTicketClient.getAllTickets();

		MatcherAssert.assertThat(actual.size(), not(0));
	}

	@Test
	public void getServiceTicket_byTicketFilter() {
		Ticket ticket = createTicketEntity(1, "eContracting", "JVM error","3",nowDate,"Esclated","HH3HK", "CCRegression", "QAUSER", "Level3");
		Ticket ticket2 = createTicketEntity(2, "CAS", "JVM error","3",nowDate,"Esclated","HH3HK", "CCRegression", "QAUSER", "Level3");
		Ticket ticket3 = createTicketEntity(3, "CAS", "Print","1",nowDate,"InProgress","YO4WL", "Bondo", "QAUSER", "Level3");
		serviceTicketClient.createTicket(ticket);
		serviceTicketClient.createTicket(ticket2);
		serviceTicketClient.createTicket(ticket3);
		TicketFilter ticketFilter = new TicketFilter();
		ticketFilter.setSearchByCategory("CAS");
		List<Ticket> actual = serviceTicketClient.getTicketById(ticketFilter);
		MatcherAssert.assertThat(actual.size(), is(2));
	}

	@Test
	public void getServiceTicket_withTicketLog() {
		Ticket ticket = createTicketEntity(98412, "eContracting", "JVM error","3",nowDate,"Esclated","HH3HK", "CCRegression", "QAUSER", "Level3");
		ticket.setTicketLogList(new ArrayList<>());
		Ticket savedTicket = serviceTicketClient.createTicket(ticket);

		final Ticket actual = serviceTicketClient.addTicketLog(savedTicket.getIncident(), createTicketLog());

		MatcherAssert.assertThat(actual.getTicketLogList().size(), is(1));
	}

	@Test
	public void getServiceTicket_withAttachment() {
		Ticket ticket = createTicketEntity(98445, "eContracting", "JVM error","3",nowDate,"Esclated","HH3HK", "CCRegression", "QAUSER", "Level3");
		ticket.setAttachments(new ArrayList<>());
		Ticket savedTicket = serviceTicketClient.createTicket(ticket);
		Ticket actual = serviceTicketClient.addAttachment(savedTicket.getIncident(), createAttachment());

		MatcherAssert.assertThat(actual.getAttachments().size(), is(1));
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
		ticket.setTicketLogList(addTickets());
		ticket.setAttachments(addAttachment());
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

	private List addTickets() {
		List ticketLogList = new ArrayList();
		ticketLogList.add(createTicketLog());
		return ticketLogList;
	}

	private List addAttachment() {
		List attachments = new ArrayList();
		attachments.add(createAttachment());
		return attachments;
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
