import { Departure } from "./departure"

export interface SearchRequest {
    page: number,
    pageSize: number,
    trainType: string | null | undefined,
    startingStationId: string,
    destinationStationId: string,
    time: string
}

export interface DepartureSearch {
    content: Departure[],
    totalElements: number 
}