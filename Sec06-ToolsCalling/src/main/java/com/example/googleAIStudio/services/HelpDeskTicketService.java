package com.example.googleAIStudio.services;

import com.example.googleAIStudio.entity.HelpDeskTicket;
import com.example.googleAIStudio.model.TicketRequest;

import java.util.List;

public interface HelpDeskTicketService {

    HelpDeskTicket createHelpDeskTicket(TicketRequest ticketInput, String username);

    List<HelpDeskTicket> findTicketsByUsername(String username);
}
