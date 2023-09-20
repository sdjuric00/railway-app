package com.ftn.railwayapp.model.ticket;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reserved_seats")
public class ReservedSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int seatNum;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int wagonNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ticket_id", nullable=false)
    private Ticket ticket;


}
