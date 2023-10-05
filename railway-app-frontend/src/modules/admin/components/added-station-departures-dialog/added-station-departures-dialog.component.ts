import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { StationDepartureRequest } from 'src/modules/shared/model/departure';

@Component({
  selector: 'app-added-station-departures-dialog',
  templateUrl: './added-station-departures-dialog.component.html',
  styleUrls: ['./added-station-departures-dialog.component.scss']
})
export class AddedStationDeparturesDialogComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: StationDepartureRequest[]) {}

  

}
