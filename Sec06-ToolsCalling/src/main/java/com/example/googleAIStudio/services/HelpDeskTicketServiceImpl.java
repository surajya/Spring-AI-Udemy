package com.example.googleAIStudio.services;

import com.example.googleAIStudio.HelpDeskTicketRepository;
import com.example.googleAIStudio.entity.HelpDeskTicket;
import com.example.googleAIStudio.model.TicketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HelpDeskTicketServiceImpl implements HelpDeskTicketService {

    private final HelpDeskTicketRepository helpDeskTicketRepository;

    @Override
    public HelpDeskTicket createHelpDeskTicket(TicketRequest ticketInput, String username) {

        HelpDeskTicket ticket = HelpDeskTicket.builder()
                .username(username)
                .issue(ticketInput.issue())
                .status("OPEN")
                .createdAt(LocalDateTime.now())
                .eta(LocalDateTime.now().plusDays(7))
                .build();
        return helpDeskTicketRepository.save(ticket);
    }

    @Override
    public List<HelpDeskTicket> findTicketsByUsername(String username) {
        return helpDeskTicketRepository.findByUsername(username);
    }
}
