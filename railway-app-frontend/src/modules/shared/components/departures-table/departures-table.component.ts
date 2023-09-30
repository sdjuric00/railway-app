import { Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges } from '@angular/core';
import { Departure } from '../../model/departure';

@Component({
  selector: 'app-departures-table',
  templateUrl: './departures-table.component.html',
  styleUrls: ['./departures-table.component.scss']
})
export class DeparturesTableComponent {
    @Input() page: number
    @Input() pageSize: number
    @Input() dataSource: Departure[]
    @Input() totalElements: number
    @Output() pageChangedEvent = new EventEmitter()
    @Output() goToDetailsPageEvent = new EventEmitter()

    displayedColumns: string[] = ['trainName', 'startTime', 'leavingTime', 'arrivalTime', 'delayed', 'cancelled', 'trainBenefits', 'details']
    
    constructor() {}

    onPageChange(event: any) {
      this.pageChangedEvent.emit(event)
    }

    goToDetailsPage(element: Departure): void {
      this.goToDetailsPageEvent.emit(element)
    }
    
}
