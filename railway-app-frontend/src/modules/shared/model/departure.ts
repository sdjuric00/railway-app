import { Station } from "./station"
import { TrainBasic } from "./train"

export interface Departure {
    id: string,
    trainName: string,
    startTime: string,
    leavingTime: string,
    arrivalTime: string,
    delayed: boolean,
    cancelled: boolean,
    trainBenefits: string[],
    startingStationId: string,
    destinationStationId: string
}

export interface StationDeparture {
    station: Station,
    startingStation: boolean,
    price: number,
    discountIfNotStarting: number,
    stationOrder: number,
    leavingTime: string
}

export interface DepartureDetails {
    id: string,
    train: TrainBasic,
    startingStation: StationDeparture,
    destinationStation: StationDeparture,
    allStations: StationDeparture[],
    totalNumOfSeats: number
}

export interface StationDepartureRequest {
    station?: Station,
    stationId: string,
    startingStation: boolean,
    price: number,
    discountIfNotStarting: number,
    stationOrder: number,
    leavingTime: string
}

export interface DepartureRequest {
    startTime: string,
    durationInMinutes: string,
    trainId: string,
    stationDepartures: StationDepartureRequest[]
}