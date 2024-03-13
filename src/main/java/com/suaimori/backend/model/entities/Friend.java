package com.suaimori.backend.model.entities;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "friends")
@Data
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date requestDate;

    @Column
    private Boolean isAccepted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_sender_id")
    private User requestSender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_receiver_id")
    private User requestReceiver;
}
