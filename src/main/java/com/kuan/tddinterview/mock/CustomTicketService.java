package com.kuan.tddinterview.mock;

import org.springframework.stereotype.Service;

@Service
public class CustomTicketService {

    OrderClient orderClient;

    TicketRepository ticketRepository;

    public CustomTicketService(OrderClient orderClient, TicketRepository ticketRepository) {
        this.orderClient = orderClient;
        this.ticketRepository = ticketRepository;
    }

    public String getRemoteOrder(String url) {
        return orderClient.getOrder(url);
    }

    public Ticket getTicket(Long id) {
        return ticketRepository.getReferenceById(id);
    }


}
