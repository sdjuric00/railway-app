import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { StationDeparture } from 'src/modules/shared/model/departure';
import { BuyTicketDialogComponent } from '../buy-ticket-dialog/buy-ticket-dialog.component';
import { TicketDialogData } from 'src/modules/shared/model/ticket';


@Component({
  selector: 'app-ticket-buy-button',
  templateUrl: './ticket-buy-button.component.html',
  styleUrls: ['./ticket-buy-button.component.scss']
})
export class TicketBuyButtonComponent {
  @Input() departureId: string
  @Input() startingStation: StationDeparture
  @Input() destinationStation: StationDeparture
  @Output() onDialogCloseEvent = new EventEmitter()

  constructor(private dialogRef: MatDialog,
  ) {}

  openBuyTicketDialog(): void {
    const ticketBuyDialog = this.dialogRef.open(BuyTicketDialogComponent, {
      data: {
        departureId: this.departureId,
        startingStation: this.startingStation,
        destinationStation: this.destinationStation,
        dialogRef: this.dialogRef 
      } as TicketDialogData
    })

    ticketBuyDialog.afterClosed().subscribe(
      res => {
        this.onDialogCloseEvent.emit(true)
      })
  }

}
