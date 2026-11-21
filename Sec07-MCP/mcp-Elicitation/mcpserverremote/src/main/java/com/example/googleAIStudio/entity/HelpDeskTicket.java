package com.example.googleAIStudio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Table(name = "helpdesk_ticket")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class HelpDeskTicket {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    long id;

    String username;

    String issue;

    String status; //OPEN, IN_PROGESS, CLOSE

    private String priority; // e.g., LOW, MEDIUM, HIGH, URGENT

    private String contactPhone; // collected from the user via MCP elicitation

    LocalDateTime createdAt;

    LocalDateTime eta;
}
