/**
 * 
 */
package com.yukon.service.monitor.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import com.yukon.service.monitor.dto.CallerServiceDTO;
import com.yukon.service.monitor.dto.ServiceDTO;
import com.yukon.service.monitor.dto.ServiceMessageDTO;
import com.yukon.service.monitor.service.CallerServiceService;

/**
 * @author Nuwan
 *
 */


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TestSchedulerRestController {
	
	@Autowired
	private CallerServiceService callerServiceService;
	
	
	private static CallerServiceDTO callerServiceDTO;
	
    private final String WEBSOCKET_URI = "ws://127.0.0.1:" + 8080 + "/service-monitor";
    private final String WEBSOCKET_TOPIC = "/topic/service-status/";
    private CompletableFuture<ServiceMessageDTO> completableFuture;
	

	
    /**
	 * Get Service message DTO by Run caller service
	 * @return 
	 */
	ServiceMessageDTO getServiceMessageByRunCallerServices() {
		
		callerServiceDTO = new CallerServiceDTO();
		callerServiceDTO.setCallerId(0l);
		callerServiceDTO.setServiceId(0l);
		callerServiceDTO.setPollingFrequency(2);
		callerServiceDTO.setGraceTime(5);
		
		ServiceDTO serviceDTO = new ServiceDTO();
		serviceDTO.setHost("localhost");
		serviceDTO.setPort(8080);
		callerServiceDTO.setServiceDTO(serviceDTO);
		ServiceMessageDTO serviceMessageDTO = callerServiceService.runCallerServices(callerServiceDTO);
		
		return serviceMessageDTO;

	}


    @Test
    @Order(1)
    public void shouldReceiveAMessageFromTheServer() throws Exception {
    	
    	ServiceMessageDTO serviceMessageDTO = getServiceMessageByRunCallerServices();
    	
    	completableFuture = new CompletableFuture<>();

         WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient(createTransportClient()));
         stompClient.setMessageConverter(new MappingJackson2MessageConverter());

         StompSession stompSession = stompClient.connect(WEBSOCKET_URI, new StompSessionHandlerAdapter() {
         }).get(1, TimeUnit.SECONDS);

         stompSession.subscribe(WEBSOCKET_TOPIC+callerServiceDTO.getCallerId(), new CreateGameStompFrameHandler());

         stompSession.send(WEBSOCKET_TOPIC+callerServiceDTO.getCallerId(), serviceMessageDTO);

         serviceMessageDTO = completableFuture.get(10, TimeUnit.SECONDS);

         assertNotNull(serviceMessageDTO);
    	

    }
    
    private List<Transport> createTransportClient() {
        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        return transports;
    }

    private class CreateGameStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return ServiceMessageDTO.class;
        }

        @Override
        public void handleFrame(StompHeaders stompHeaders, Object o) {
            completableFuture.complete((ServiceMessageDTO) o);
        }
    }

	

}
