import { MatDialog } from "@angular/material/dialog";
import { StationDeparture } from "./departure";


export interface TicketDialogData {
    departureId: string,
    startingStation: StationDeparture,
    destinationStation: StationDeparture,
    dialogRef: MatDialog
}

export interface TicketRequest {
    userId: number,
    departureId: string,
    startStationId: string,
    destinationStationId: string,
    passengers: string[]
}