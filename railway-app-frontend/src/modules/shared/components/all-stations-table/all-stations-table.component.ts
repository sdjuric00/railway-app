import { Component, Input } from '@angular/core';
import { StationDeparture } from '../../model/departure';

@Component({
  selector: 'app-all-stations-table',
  templateUrl: './all-stations-table.component.html',
  styleUrls: ['./all-stations-table.component.scss']
})
export class AllStationsTableComponent {
  @Input() allStations: StationDeparture[]
}
