import { Component, Input } from '@angular/core';
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

  constructor(private dialogRef: MatDialog,
  ) {}

  openBuyTicketDialog(): void {
    this.dialogRef.open(BuyTicketDialogComponent, {
      data: {
        departureId: this.departureId,
        startingStation: this.startingStation,
        destinationStation: this.destinationStation
      } as TicketDialogData,
    })
  }

}
