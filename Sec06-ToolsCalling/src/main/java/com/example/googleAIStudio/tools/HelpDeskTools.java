package com.example.googleAIStudio.tools;

import com.example.googleAIStudio.entity.HelpDeskTicket;
import com.example.googleAIStudio.model.TicketRequest;
import com.example.googleAIStudio.services.HelpDeskTicketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HelpDeskTools {

    private final HelpDeskTicketServiceImpl service;

    private static final Logger LOGGER = LoggerFactory.getLogger(HelpDeskTools.class);

    @Tool(name = "createTicket", description = "Help to create a support ticket")
    public HelpDeskTicket createTicket(@ToolParam(description = "value represent the ticketRequest") TicketRequest ticketRequest, ToolContext toolContext) {
        String username = (toolContext.getContext().get("username")).toString();
        LOGGER.info("Creating a support ticket for user {}", username);
        return service.createHelpDeskTicket(ticketRequest, username);
    }

    //create tool method for get the ticket

    @Tool(name = "getTicket", description = "Help to find the ticket based on username")
    public List<HelpDeskTicket> getTicket(ToolContext toolContext) {
        String username = (toolContext.getContext().get("username")).toString();
        LOGGER.info("Getting help desk tools for user {}", username);
        return service.findTicketsByUsername(username);
    }
}
