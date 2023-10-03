package com.ftn.railwayapp.model.ticket;

import com.ftn.railwayapp.model.user.RegularUser;
import com.ftn.railwayapp.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private RegularUser user;

    @Column(nullable = false)
    private String departureId;

    @Column(nullable = false)
    private String startStationId;

    @Column(nullable = false)
    private String destinationStationId;

    @Column(nullable = false)
    private String passengers;

    @Column(nullable = false)
    private double totalPrice;

    @Column
    private String qrCode;

    @Column(nullable = false)
    private boolean used = false;

    public Ticket(RegularUser user,
                  String departureId,
                  String startStationId,
                  String destinationStationId,
                  String passengers,
                  double totalPrice
    ) {
        this.user = user;
        this.departureId = departureId;
        this.startStationId = startStationId;
        this.destinationStationId = destinationStationId;
        this.passengers = passengers;
        this.totalPrice = totalPrice;
        this.qrCode = null;
    }
}
