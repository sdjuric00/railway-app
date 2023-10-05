import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { StationDepartureRequest } from 'src/modules/shared/model/departure';
import { Station } from 'src/modules/shared/model/station';
import { AddedStationDeparturesDialogComponent } from '../added-station-departures-dialog/added-station-departures-dialog.component';

@Component({
  selector: 'app-station-departures-form',
  templateUrl: './station-departures-form.component.html',
  styleUrls: ['./station-departures-form.component.scss']
})
export class StationDeparturesFormComponent {
  @Input() filteredStations: Observable<Station[]>
  @Output() addedStationDepartureEvent = new EventEmitter()

  stationDepartures: StationDepartureRequest[] = []
  selectedStation: Station | null

  stationDepartureForm = new FormGroup({
    stationName: new FormControl('', Validators.required),
    leavingTime: new FormControl('00:00', [Validators.required, Validators.pattern('^([01]\\d|2[0-3]):([0-5]\\d)$')]),
    startingStation: new FormControl(false, Validators.required),
    stationOrder: new FormControl(this.stationDepartures.length + 1,[ Validators.required, Validators.pattern("[1-6]")]),
    price: new FormControl(0, [Validators.required]),
    discountIfNotStarting: new FormControl(0, Validators.required)
  })

  constructor(private toast: ToastrService,
              private dialogRef: MatDialog
    ) {}

  selectStation(station: Station): void {
    this.selectedStation = station
  }  

  add(): void {
    if (this.stationDepartures.length === 6) {
      this.toast.error('You cannot add more than 6 stations.', 'Error happened!')
    }

    const leavingTime = this.stationDepartureForm.get("leavingTime")?.value
    const startingStation = this.stationDepartureForm.get("startingStation")?.value
    const stationOrder = this.stationDepartureForm.get("stationOrder")?.value
    const price = this.stationDepartureForm.get("price")?.value
    const discountIfNotStarting = this.stationDepartureForm.get("discountIfNotStarting")?.value
    if (this.selectedStation && leavingTime && stationOrder) {
      this.stationDepartures.push({
        stationId: this.selectedStation.id,
        leavingTime: leavingTime,
        startingStation: startingStation ? startingStation : false,
        stationOrder: stationOrder,
        price: price ? price : 0,
        discountIfNotStarting: discountIfNotStarting ? discountIfNotStarting : 0,
        station: this.selectedStation
      })

      this.stationDepartureForm.reset()
      this.selectedStation = null
      this.addedStationDepartureEvent.emit(this.stationDepartures)
    } else {
      this.toast.error("Your form is invalid, check all fields!", 'Error happened!')
    }
  }

  openDialog(): void {
    const addedStDepartures = this.dialogRef.open(AddedStationDeparturesDialogComponent, {
      data: this.stationDepartures
    })

    addedStDepartures.afterClosed().subscribe(
      res => {
        if (res) {
          this.stationDepartures = []
          this.selectedStation = null
          this.addedStationDepartureEvent.emit([])
        }
      })
  }

}
