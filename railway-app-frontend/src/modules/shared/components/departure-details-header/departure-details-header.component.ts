import { Component, Input } from '@angular/core';
import { StationDeparture } from '../../model/departure';

@Component({
  selector: 'app-departure-details-header',
  templateUrl: './departure-details-header.component.html',
  styleUrls: ['./departure-details-header.component.scss']
})
export class DepartureDetailsHeaderComponent {
  @Input() startingStation: StationDeparture
  @Input() destinationStation: StationDeparture

}
