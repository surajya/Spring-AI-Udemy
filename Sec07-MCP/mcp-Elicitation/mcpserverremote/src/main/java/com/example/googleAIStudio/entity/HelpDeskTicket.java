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

    @NonNull
    String username;

    @NonNull
    String issue;

    @NonNull
    String status; //OPEN, IN_PROGESS, CLOSE

    @NonNull
    LocalDateTime createdAt;

    @NonNull
    LocalDateTime eta;
}
