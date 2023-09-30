import { Component, Input } from '@angular/core';
import { StationDeparture } from '../../model/departure';

@Component({
  selector: 'app-station-row',
  templateUrl: './station-row.component.html',
  styleUrls: ['./station-row.component.scss']
})
export class StationRowComponent {
  @Input() stationDeparture: StationDeparture
}
