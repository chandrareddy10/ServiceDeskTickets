package com.customer.caServiceDesk.client;

import com.customer.caServiceDesk.api.CADeskResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CADeskClientTest {
    @Autowired
    private CADeskClient subject;

    @Test
    public void createTicket_returnsTicket() {
        final CADeskResponse response = subject.getTickets("YO4WL");
        assertThat(response, is(notNullValue()));
    }
}