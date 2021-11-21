package com.customer.service;

import com.customer.api.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void sendSimpleMessage() {
        final Ticket ticket = createTicket();

        emailService.sendSupportEmail(ticket, "Able to successful login in to the system");
        //assertThat(ticket, is(notNullValue()));
    }

    private Ticket createTicket() {
        Ticket ticket = new Ticket();
        ticket.setIncident(123123);
        ticket.setDealerId("YO4Wl");
        ticket.setSummary("Not getting any questioners. ");
        ticket.setUserName("Chandra Reddy");
        ticket.setUserId("Chandra Reddy");
        ticket.setCategory("Compliance");
        ticket.setStatus("Acknowledged");
        ticket.setPriority("4");
        ticket.setDescription("What is occurring that should not be? Caller is trying to pull a quiz because the customer failed IDV but she does not get any questioners. \n" +
                "\n" +
                "To be more specific she gets a message stating quiz available and when she clicks on that blue message she gets a blank pop up screen. \n" +
                "\n" +
                "Solution: Had the user clear out her C&C and restart the browser.  That did not resolve the issue ad rerunning IDV did not help either. Caller already tried another browser with no success either. This is happening to multiple deals.  Escalation once I get screenshots\n");
        return ticket;
    }


}