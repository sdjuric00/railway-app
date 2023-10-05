import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription, map, startWith } from 'rxjs';
import { Station } from 'src/modules/shared/model/station';
import { TrainBasic } from 'src/modules/shared/model/train';
import { StationService } from 'src/modules/shared/services/station/station.service';
import { TrainService } from '../../services/train/train.service';
import { ToastrService } from 'ngx-toastr';
import { StationDepartureRequest } from 'src/modules/shared/model/departure';
import { DatePipe } from '@angular/common';
import { DeparturesService } from 'src/modules/shared/services/departures/departures.service';

@Component({
  selector: 'app-departure-creation-page',
  templateUrl: './departure-creation-page.component.html',
  styleUrls: ['./departure-creation-page.component.scss'],
  providers: [DatePipe]
})
export class DepartureCreationPageComponent implements OnInit, OnDestroy {

  allStations: Station[]
  filteredStations: Observable<Station[]>
  allTrains: TrainBasic[]
  filteredTrains: Observable<TrainBasic[]>
  selectedTrain: TrainBasic | null

  trainSubscription: Subscription
  stationSubscription: Subscription
  departureSubscription: Subscription

  stationDepartures: StationDepartureRequest[]

  myFormControl = new FormControl()
  departureForm = new FormGroup({
    date: new FormControl('', Validators.required),
    time: new FormControl('00:00', [Validators.required, Validators.pattern('^([01]\\d|2[0-3]):([0-5]\\d)$')]),
    durationInMinutes: new FormControl('', Validators.required),
    trainId: new FormControl('', Validators.required),
  })

  constructor(private stationService: StationService,
              private trainService: TrainService,
              private toast: ToastrService,
              private datePipe: DatePipe,
              private departureService: DeparturesService
  ) {}

  ngOnInit(): void {
      this.loadTrains()
      this.loadStations()
  }

  onSelectTrain(train: TrainBasic): void {
    this.selectedTrain = train
  }

  onAddedStationDepartures(stationDepartures: StationDepartureRequest[]): void {
    this.stationDepartures = stationDepartures
  }

  createDeparture(): void {
    const date = this.departureForm.get("date")?.value
    const formattedDate = this.datePipe.transform(date, 'yyyy-MM-dd');
    const time = this.departureForm.get("time")?.value
    const formattedDateTime = `${formattedDate}T${time}`
    const durationInMinutes = this.departureForm.get("durationInMinutes")?.value
    if (this.selectedTrain && this.stationDepartures.length >= 2 && durationInMinutes && formattedDateTime) {
      this.departureSubscription = this.departureService.createDeparture({
        trainId: this.selectedTrain.id, durationInMinutes: durationInMinutes,
        startTime: formattedDateTime, stationDepartures: this.stationDepartures
      }).subscribe(
        res => {
          this.toast.success("Your departure is successfully created!", "Success!")
          this.departureForm.reset()
        },
        error => {
          this.toast.error(error.error, "Error happened!")
        }
      )

    } else {
      this.toast.error("Your request is invalid, you need to add at least 2 stations. Check all fields!", 'Error happened!')
    }
  }

  loadTrains(): void {
    this.trainSubscription = this.trainService.getAll().subscribe(
      res => {
        this.allTrains = res
        this.filteredTrains = this.myFormControl.valueChanges
        .pipe(
          startWith(''),
          map(value => typeof value === 'string' ? value : value.name),
          map(name => name ? this._filterTrains(name) : this.allTrains.slice())
        );
      }
    )
  }

  loadStations(): void {
    this.stationSubscription = this.stationService.getAll().subscribe(
      res => {
        this.allStations = res
        this.filteredStations = this.myFormControl.valueChanges
        .pipe(
          startWith(''),
          map(value => typeof value === 'string' ? value : value.name),
          map(name => name ? this._filterStations(name) : this.allStations.slice())
        );
      }
    )
  }

  private _filterStations(name: string): Station[] {
    const filterValue = name.toLowerCase();
    
    return this.allStations.filter(station => station.name.toLowerCase().includes(filterValue));
  }
  
  private _filterTrains(name: string): TrainBasic[] {
    const filterValue = name.toLowerCase();
    
    return this.allTrains.filter(train => train.name.toLowerCase().includes(filterValue));
  }

  ngOnDestroy(): void {
      if (this.trainSubscription) {
        this.trainSubscription.unsubscribe()
      }

      if (this.stationSubscription) {
        this.stationSubscription.unsubscribe()
      }

      if (this.departureSubscription) {
        this.departureSubscription.unsubscribe()
      }
  }

}
