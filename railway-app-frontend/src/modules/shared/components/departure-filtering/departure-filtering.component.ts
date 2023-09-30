import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { format } from 'date-fns';
import { Observable, Subscription, map, startWith } from 'rxjs';
import { Station } from '../../model/station';
import { StationService } from '../../services/station/station.service';
import { SearchRequest } from '../../model/search';
import { DatePipe } from '@angular/common';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-departure-filtering',
  templateUrl: './departure-filtering.component.html',
  styleUrls: ['./departure-filtering.component.scss'],
  providers: [DatePipe]
})
export class DepartureFilteringComponent implements OnInit, OnDestroy {
  @Output() searchEvent = new EventEmitter()

  selectedDate: Date
  formattedDate: String
  startingStationId: string
  destinationStationId: string

  allStations: Station[]
  filteredStations: Observable<Station[]>

  stationSubscription: Subscription

  myFormControl = new FormControl()
  filteringForm = new FormGroup({
    startingStation: new FormControl('', Validators.required),
    destinationStation: new FormControl('', Validators.required ),
    date: new FormControl('', Validators.required),
    time: new FormControl('00:00', [Validators.required, Validators.pattern('^([01]\\d|2[0-3]):([0-5]\\d)$')]),
    trainType: new FormControl('ALL'),
  })

  constructor(private stationService: StationService,
              private datePipe: DatePipe,
              private toast: ToastrService
  ) {
    this.selectedDate = new Date()
    this.formattedDate = format(this.selectedDate, 'dd.MM.yyyy.')
  }

  ngOnInit(): void {
    this.stationSubscription = this.stationService.getAll().subscribe(
      res => {
        this.allStations = res
        this.filteredStations = this.myFormControl.valueChanges
        .pipe(
          startWith(''),
          map(value => typeof value === 'string' ? value : value.name),
          map(name => name ? this._filter(name) : this.allStations.slice())
        );
      }
    )
  }

  changedDestinationStation(station: Station): void {
    this.destinationStationId = station.id
  }

  changedStartingStation(station: Station): void {
    this.startingStationId = station.id
  }

  search(): void {
    const searchRequest: SearchRequest = {
      page: 0,
      pageSize: 0,
      startingStationId: this.startingStationId,
      destinationStationId: this.destinationStationId,
      trainType: this.filteringForm.get('trainType')?.value,
      time: this.formatDateTime()
    }
    if (this.filteringForm.valid) {
      this.searchEvent.emit(searchRequest)
    } else {
      this.toast.error('Form is invalid, check all fields.', 'Error happened!')
    }
    
  }

  formatDateTime(): string {
    const dateTime: string = `${this.filteringForm.get('date')?.value}`
    const formattedDate = this.datePipe.transform(dateTime, 'yyyy-MM-dd');
    const formattedDateTime = `${formattedDate}T${this.filteringForm.get('time')?.value}`
    const titleDateFormatted = this.datePipe.transform(dateTime, 'dd.MM.yyyy.')
    if (titleDateFormatted) {
      this.formattedDate = titleDateFormatted
    }

    return formattedDateTime
  }

  private _filter(name: string): Station[] {
    const filterValue = name.toLowerCase();
    
    return this.allStations.filter(station => station.name.toLowerCase().includes(filterValue));
  }
  
  ngOnDestroy(): void {
    if (this.stationSubscription) {
      this.stationSubscription.unsubscribe()
    }
  }
}
