package com.customer.caServiceDesk.client;

import com.customer.caServiceDesk.api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
public class CADeskClient {

    private static final String REST_ACCESS = "https://routeone-dev.stefanini.com:6443/caisd-rest/rest_access";
    private static final String GET_TICKETS_BY_DEALERID = "https://routeone-dev.stefanini.com:6443/caisd-rest/in?WC={dealerId}&SORT=last_mod_dt DESC";
    private static final String GET_ACTIVITY_LOG = "https://routeone-dev.stefanini.com:6443/caisd-rest/in/{incidentId}/act_log";
    private static final String GET_TICKET_BY_INCIDENT = "https://routeone-dev.stefanini.com:6443/caisd-rest/in";
    private static final String CREATE_TICKET = "https://routeone-dev.stefanini.com:6443/caisd-rest/in";
    private static final String ADD_ACTIVITY_LOG = "https://routeone-dev.stefanini.com:6443/caisd-rest/cr/{incident}";
    private static final String GET_ATTACHMENTS = "https://routeone-dev.stefanini.com:6443/caisd-rest/attmnt/402693";
    private RestTemplate restTemplate = new RestTemplate();

    private String baseURL;

    public CADeskClient() {
        this.baseURL = GET_TICKETS_BY_DEALERID;
        this.restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(this.baseURL));
    }

    /*public String createAccessKey( ) {
        ResponseEntity<AccessKey> response = null;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL);
        HttpHeaders httpHeaders  =  new HttpHeaders();
        httpHeaders.set("Ocp-Apim-Subscription-Key",API_KEY);
        httpHeaders.set("Content-Type","application/json");

        HttpEntity<CADeskRequest> request = new HttpEntity<CADeskRequest>(CADeskRequest, httpHeaders);

            response = restTemplate.exchange(builder.toUriString(),
                    HttpMethod.POST, request, new ParameterizedTypeReference<AccessKey>() {
                    });



            return response.getBody().getRest_access().;

    }*/

    public CADeskResponse getTickets(String dealerId)  {
        ResponseEntity<CADeskResponse> response = null;
        CADeskRequest CADeskRequest = new CADeskRequest();


        /*String urlTemplate = UriComponentsBuilder.fromHttpUrl(GET_TICKETS_BY_DEALERID)
                             .queryParam("WC", "{dealerId}")
                .toUriString();*/
        String urlTemplate =  GET_TICKETS_BY_DEALERID;
        Map<String, String> params = new HashMap<String, String>();
        params.put("dealerId", "z_dealer_id='"+dealerId+"'");

        HttpHeaders httpHeaders  =  new HttpHeaders();
        httpHeaders.set("X-AccessKey","1676509414");
        httpHeaders.set("Content-Type","application/json");
        httpHeaders.set("Accept","application/json");
        httpHeaders.set("X-Obj-Attrs","attachments,category,close_date,customer,description,group,last_mod_dt,open_date,priority,status,summary,z_dealer_id, z_dms,z_finance_source,zreporting_method,act_log");

        HttpEntity<CADeskRequest> request = new HttpEntity<CADeskRequest>(CADeskRequest, httpHeaders);

            response = restTemplate.exchange(urlTemplate,
                    HttpMethod.GET, request, new ParameterizedTypeReference<CADeskResponse>() {
                    }, params);


       return response.getBody();
    }
    public CreateResponse createTicket(CreateIncidentRequest createIncidentRequest)  {
        ResponseEntity<CreateResponse> response = null;
        String json = null;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(CREATE_TICKET);

        HttpHeaders httpHeaders  =  new HttpHeaders();
        httpHeaders.set("X-AccessKey","1676509414");
        httpHeaders.set("Content-Type","application/json");
        httpHeaders.set("Accept","application/json");
        httpHeaders.set("X-Obj-Attrs","ref_num");

        HttpEntity<CreateIncidentRequest> request = new HttpEntity<CreateIncidentRequest>(createIncidentRequest, httpHeaders);
        ObjectMapper mapper = new ObjectMapper();
        try {
             json = mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String requestObj  = json;

        response = restTemplate.exchange(builder.toUriString(),
                HttpMethod.POST, request, new ParameterizedTypeReference<CreateResponse>() {
                });


        return response.getBody();
    }

    public CreateLogResponse addActivityLog(CreateLogRequest createLogRequest, Integer incident)  {
        ResponseEntity<CreateLogResponse> response = null;
        String json = null;
        HttpHeaders httpHeaders  =  new HttpHeaders();

        Map<String, String> params = new HashMap<String, String>();
        params.put("incident", incident.toString());

        httpHeaders.set("X-AccessKey","1676509414");
        httpHeaders.set("Content-Type","application/json");
        httpHeaders.set("Accept","application/json");
        httpHeaders.set("X-Obj-Attrs","persid");

        HttpEntity<CreateLogRequest> request = new HttpEntity<CreateLogRequest>(createLogRequest, httpHeaders);
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String requestObj  = json;

        response = restTemplate.exchange(ADD_ACTIVITY_LOG,
                HttpMethod.PUT, request, new ParameterizedTypeReference<CreateLogResponse>() {
                }, params);


        return response.getBody();
    }

    public IncidentEntity getTicket_ByTicketId(Integer incident)  {
        ResponseEntity<IncidentEntity> response = null;
        CADeskRequest CADeskRequest = new CADeskRequest();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GET_TICKET_BY_INCIDENT+"/"+incident);

        HttpHeaders httpHeaders  =  new HttpHeaders();
        httpHeaders.set("X-AccessKey","1676509414");
        httpHeaders.set("Content-Type","application/json");
        httpHeaders.set("Accept","application/json");
        httpHeaders.set("X-Obj-Attrs","attachments,category,close_date,customer,description,group,last_mod_dt,open_date,priority,status,summary,z_dealer_id, z_dms,z_finance_source,zreporting_method,act_log");

        HttpEntity<CADeskRequest> request = new HttpEntity<CADeskRequest>(CADeskRequest, httpHeaders);

        response = restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET, request, new ParameterizedTypeReference<IncidentEntity>() {
                });

        return response.getBody();
    }

    public ActivityLogResponse getActivityLogs(Integer incident) {
        ResponseEntity<ActivityLogResponse> response = null;
        CADeskRequest CADeskRequest = new CADeskRequest();
        String urlTemplate =  GET_ACTIVITY_LOG;
        Map<String, String> params = new HashMap<String, String>();
        params.put("incidentId", incident.toString());

        HttpHeaders httpHeaders  =  new HttpHeaders();
        httpHeaders.set("X-AccessKey","1676509414");
        httpHeaders.set("Content-Type","application/json");
        httpHeaders.set("Accept","application/json");
        httpHeaders.set("X-Obj-Attrs","*");

        HttpEntity<CADeskRequest> request = new HttpEntity<CADeskRequest>(CADeskRequest, httpHeaders);

        response = restTemplate.exchange(urlTemplate,
                HttpMethod.GET, request, new ParameterizedTypeReference<ActivityLogResponse>() {
                }, params);


        return response.getBody();
    }


    public AttachmentResponse getAttachments(Integer incident) {
        ResponseEntity<AttachmentResponse> response = null;
        CADeskRequest CADeskRequest = new CADeskRequest();
        String urlTemplate = GET_ATTACHMENTS;
        Map<String, String> params = new HashMap<String, String>();
        params.put("incidentId", incident.toString());

        HttpHeaders httpHeaders  =  new HttpHeaders();
        httpHeaders.set("X-AccessKey","1676509414");
        httpHeaders.set("Content-Type","application/json");
        httpHeaders.set("Accept","application/json");
        httpHeaders.set("X-Obj-Attrs","*");

        HttpEntity<CADeskRequest> request = new HttpEntity<CADeskRequest>(CADeskRequest, httpHeaders);

        response = restTemplate.exchange(urlTemplate,
                HttpMethod.GET, request, new ParameterizedTypeReference<AttachmentResponse>() {
                });


        return response.getBody();
    }


}
