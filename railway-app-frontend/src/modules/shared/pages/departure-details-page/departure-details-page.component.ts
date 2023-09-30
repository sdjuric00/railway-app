import { Component, OnDestroy, OnInit } from '@angular/core';
import { DeparturesService } from '../../services/departures/departures.service';
import { TicketService } from '../../services/ticket/ticket.service';
import { Subscription } from 'rxjs';
import { DepartureDetails } from '../../model/departure';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-departure-details-page',
  templateUrl: './departure-details-page.component.html',
  styleUrls: ['./departure-details-page.component.scss']
})
export class DepartureDetailsPageComponent implements OnInit, OnDestroy {

  departureSubscription: Subscription
  ticketSubscription: Subscription

  departureDetails: DepartureDetails
  numOfSoldTickets: number = 0

  departureId: string | null
  startingStationId: string | null
  destinationStationId: string | null

  constructor(private departureService: DeparturesService,
              private ticketService: TicketService,
              private route: ActivatedRoute,
              private toast: ToastrService
  ) {}

  ngOnInit(): void {
      this.departureId = this.route.snapshot.paramMap.get('departureId')
      this.startingStationId = this.route.snapshot.paramMap.get('startingStationId')
      this.destinationStationId = this.route.snapshot.paramMap.get('destinationStationId')

      this.loadData()
  }

  loadData(): void {
    this.loadDepartureData()
  }

  loadSoldTickets(): void {
    if (this.departureId) {
      this.ticketSubscription = this.ticketService.getNumOfSoldTickets(this.departureId).subscribe(
        res => {
          this.numOfSoldTickets = res
        },
        error => {
          this.toast.error(error.error, 'Error happened!')
        }
      )
    }
  }

  loadDepartureData(): void {
    if (this.departureId && this.startingStationId && this.destinationStationId) {
      this.departureSubscription = this.departureService.getDepartureDetails(this.departureId, this.startingStationId, this.destinationStationId)
      .subscribe(
        res => {
          this.departureDetails = res
        },
        error => {
          this.toast.error(error.error, 'Error happened!')
        }
      )
    }
  }

  ngOnDestroy(): void {
      if (this.departureSubscription) {
        this.departureSubscription.unsubscribe()
      }

      if (this.ticketSubscription) {
        this.ticketSubscription.unsubscribe()
      }
  }

}
