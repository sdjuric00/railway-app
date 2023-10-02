import { AfterViewInit, Component, Input, OnInit } from '@angular/core';
import * as L from 'leaflet';
import { StationDeparture } from '../../model/departure';
import { Station } from '../../model/station';


@Component({
  selector: 'app-map-container',
  templateUrl: './map-container.component.html',
  styleUrls: ['./map-container.component.scss']
})
export class MapContainerComponent implements AfterViewInit {
  @Input() stationDepartures: StationDeparture[] = []
  @Input() startingStation: StationDeparture
  @Input() destinationStation: StationDeparture

  private map: any;

  private initMap(): void {
    this.map = L.map('map', {
      center: [ Number(this.startingStation.station.coordinateX), Number(this.startingStation.station.coordinateY) ],
      zoom: 7,
      attributionControl: false
    });

    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 3,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    tiles.addTo(this.map);
    this.addMarkers()
  }

  addMarkers(): void {
    let numOfStations: number = 0
    for (let stationDeparture of this.stationDepartures) {
      if (stationDeparture.stationOrder >= this.startingStation.stationOrder &&
          stationDeparture.stationOrder <= this.destinationStation.stationOrder) {
            numOfStations += 1

            const icon = {
            icon: L.icon({
              iconSize: [ 25, 30 ],
              iconAnchor: [ 10, 0 ],
              iconUrl: `../../../../assets/images/number_${numOfStations}.png`,
            })};
      
            const marker = L.marker([Number(stationDeparture.station.coordinateX), Number(stationDeparture.station.coordinateY)], icon)
            marker.addTo(this.map)
            marker.bindPopup(`${stationDeparture.station.name} station<br>Leaves at ${stationDeparture.leavingTime}`);
            marker.bindTooltip(`${stationDeparture.station.name} Leaves at ${stationDeparture.leavingTime}`)
          }
    }
  }

  constructor() { }

  ngAfterViewInit(): void {
    this.initMap();
  }

}
