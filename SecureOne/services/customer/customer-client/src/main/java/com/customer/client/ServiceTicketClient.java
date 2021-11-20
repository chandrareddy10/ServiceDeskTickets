package com.customer.client;

import com.customer.api.Attachment;
import com.customer.api.Ticket;
import com.customer.api.TicketFilter;
import com.customer.api.TicketLog;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class ServiceTicketClient {

	private RestTemplate restTemplate = new RestTemplate();
	private String baseURL;

	public ServiceTicketClient(final String host, final String port) {
		this.baseURL = "http://" + host + ":" + port+"/serviceDesk";
		this.restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(this.baseURL));
	}

	public Integer createTicket(Ticket ticket){
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL+"/");
		HttpEntity<Ticket> request = new HttpEntity<Ticket>(ticket, null);

		ResponseEntity<Integer> ticketResponseEntity = restTemplate.exchange(builder.toUriString(),
				HttpMethod.POST, request, new ParameterizedTypeReference<Integer>() {
				});

		return ticketResponseEntity.getBody();
	}

	public List<Ticket> getTicketById(TicketFilter ticketFilter) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL+"/search/");
		HttpEntity<TicketFilter> request = new HttpEntity<TicketFilter>(ticketFilter, null);

		ResponseEntity<List<Ticket>> ticketResponse = restTemplate.exchange(builder.toUriString(),
				HttpMethod.POST, request , new ParameterizedTypeReference<List<Ticket>>() {
				});

		return ticketResponse.getBody();
	}

	public List<Ticket> getAllTickets() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL+"/findAll/");

		ResponseEntity<List<Ticket>> ticketResponse = restTemplate.exchange(builder.toUriString(),
				HttpMethod.GET, null , new ParameterizedTypeReference<List<Ticket>>() {
				});

		return ticketResponse.getBody();
	}



	public Ticket getTicketById(Integer incident) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL+"/"+incident);

		ResponseEntity<Ticket> ticketResponse = restTemplate.exchange(builder.toUriString(),
				HttpMethod.GET, null , new ParameterizedTypeReference<Ticket>() {
				});

		return ticketResponse.getBody();
	}

	public Ticket addTicketLog(Integer incident, TicketLog ticketLog){
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL+"/addTicketLog/"+incident);
		HttpEntity<TicketLog> request = new HttpEntity<TicketLog>(ticketLog, null);

		ResponseEntity<Ticket> ticketResponseEntity = restTemplate.exchange(builder.toUriString(),
				HttpMethod.PUT, request, new ParameterizedTypeReference<Ticket>() {
				});

		return ticketResponseEntity.getBody();
	}

	public Ticket addAttachment(Integer incident, Attachment attachment){
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL+"/addAttachment/"+incident);
		HttpEntity<Attachment> request = new HttpEntity<Attachment>(attachment, null);

		ResponseEntity<Ticket> ticketResponseEntity = restTemplate.exchange(builder.toUriString(),
				HttpMethod.PUT, request, new ParameterizedTypeReference<Ticket>() {
				});

		return ticketResponseEntity.getBody();
	}

	public Ticket updateTicket(Ticket ticket){
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL+"/update");
		      HttpEntity<Ticket> request = new HttpEntity<Ticket>(ticket, null);

		ResponseEntity<Ticket> ticketResponseEntity = restTemplate.exchange(builder.toUriString(),
				HttpMethod.PUT, request, new ParameterizedTypeReference<Ticket>() {
				});

		return ticketResponseEntity.getBody();
	}
}
