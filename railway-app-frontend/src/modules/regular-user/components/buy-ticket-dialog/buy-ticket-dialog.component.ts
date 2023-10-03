import { Component, Inject, OnDestroy } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { TicketDialogData, TicketRequest } from 'src/modules/shared/model/ticket';
import { TicketService } from '../../services/ticket/ticket.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-buy-ticket-dialog',
  templateUrl: './buy-ticket-dialog.component.html',
  styleUrls: ['./buy-ticket-dialog.component.scss']
})
export class BuyTicketDialogComponent implements OnDestroy {

  passengers: string[] = []
  pricePerPerson: number = 0
  showSpinner: boolean = false

  ticketSubscription: Subscription

  constructor(@Inject(MAT_DIALOG_DATA) public data: TicketDialogData,
              private toast: ToastrService,
              private ticketService: TicketService
  ) {
    this.pricePerPerson = data.destinationStation.price - (data.startingStation.startingStation ? 0 : data.startingStation.discountIfNotStarting)
  }

  addedPassengers(passengers: string[]): void {
    this.passengers = passengers
  }

  buyTicket(): void {
    if (this.addedPassengers.length > 0) {
      this.showSpinner = true
      const ticketRequest: TicketRequest = this.ticketService.createTicketRequest(localStorage.getItem('user_id'), 
                                      this.data.departureId, this.data.startingStation.station.id,
                                      this.data.destinationStation.station.id, this.passengers)

      this.ticketSubscription = this.ticketService.createTicket(ticketRequest).subscribe(
        res => {
          if (res) {
            this.toast.success('Your payment was successful! Check your email.', 'Success!')
          } else {
            this.toast.success('Transaction failed!', 'Error happened!')
          }
          this.data.dialogRef.closeAll()
        }, error => {
          this.showSpinner = false
          this.toast.error(error.error, 'Error happened!')
        }
      )
      this.passengers = []
    } else {
      this.toast.error('You need to add at least one passenger.', 'Error happened!')
    }
  }

  ngOnDestroy(): void {
      if (this.ticketSubscription) {
        this.ticketSubscription.unsubscribe()
      }
  }

}
