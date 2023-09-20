package com.ftn.railwayapp.model.ticket;

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
    private User user;

    @Column(nullable = false)
    private String departureId;

    @Column(nullable = false)
    private String additionalUsers;

    @Column(nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private String qrCode;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.EAGER)
    private List<ReservedSeats> reservedSeats = new ArrayList<>();

}
