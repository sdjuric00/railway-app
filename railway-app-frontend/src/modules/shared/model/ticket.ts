import { StationDeparture } from "./departure";


export interface TicketDialogData {
    departureId: string,
    startingStation: StationDeparture,
    destinationStation: StationDeparture
}

export interface TicketRequest {

}