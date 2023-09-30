import { Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-departures-table',
  templateUrl: './departures-table.component.html',
  styleUrls: ['./departures-table.component.scss']
})
export class DeparturesTableComponent {
    @Input() page: number
    @Input() pageSize: number
    @Input() dataSource: any[]
    @Input() totalElements: number
    @Output() pageChangedEvent = new EventEmitter()

    displayedColumns: string[] = ['trainName', 'startTime', 'leavingTime', 'arrivalTime', 'delayed', 'cancelled', 'trainBenefits', 'details']
    
    onPageChange(event: any) {
      this.pageChangedEvent.emit(event)
    }
}
