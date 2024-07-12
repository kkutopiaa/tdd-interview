package com.kuan.tddinterview.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class MockTest {

    @MockBean
    OrderClient orderClient;

    @MockBean
    TicketRepository ticketRepository;

    @Resource
    CustomTicketService customTicketService;

    long expectedId = 1L;
    Ticket expectedTicket = new Ticket(expectedId, "test-dest");

    @Test
    public void should_get_orders_from_third_part_api() {
        String targetUrl = "test-url";
        String expectedOrder = "test-order";

        Mockito.when(orderClient.getOrder(targetUrl)).thenReturn(expectedOrder);
        String remoteOrder = customTicketService.getRemoteOrder(targetUrl);

        Assertions.assertEquals(expectedOrder, remoteOrder);
    }

    @Test
    public void should_get_ticket_from_service() {
        Mockito.when(ticketRepository.getReferenceById(expectedId)).thenReturn(expectedTicket);

        Assertions.assertEquals(expectedTicket, customTicketService.getTicket(expectedId));
    }

    @Test
    public void should_get_ticket_from_repository() {
        Mockito.when(ticketRepository.findById(expectedId)).thenReturn(Optional.of(expectedTicket));

        Assertions.assertEquals(expectedTicket, ticketRepository.findById(expectedId).get());
    }



}
