
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