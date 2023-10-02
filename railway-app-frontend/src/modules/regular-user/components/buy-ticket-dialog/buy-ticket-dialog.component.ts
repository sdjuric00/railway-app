import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { TicketDialogData } from 'src/modules/shared/model/ticket';

@Component({
  selector: 'app-buy-ticket-dialog',
  templateUrl: './buy-ticket-dialog.component.html',
  styleUrls: ['./buy-ticket-dialog.component.scss']
})
export class BuyTicketDialogComponent {

  passengers: string[] = []
  pricePerPerson: number = 0

  constructor(@Inject(MAT_DIALOG_DATA) public data: TicketDialogData,
              private toast: ToastrService
  ) {
    this.pricePerPerson = data.destinationStation.price - data.startingStation.discountIfNotStarting
  }

  addedPassengers(passengers: string[]): void {
    this.passengers = passengers
  }

  buyTicket(): void {
    if (this.addedPassengers.length > 0) {
      this.toast.success('Success', 'Success')
    } else {
      this.toast.error('You need to add at least one passenger.', 'Error happened!')
    }
  }

}
