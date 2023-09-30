import { Component, OnDestroy, OnInit } from '@angular/core';
import { SearchRequest } from '../../model/search';
import { DeparturesService } from '../../services/departures/departures.service';
import { Subscription } from 'rxjs';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { Departure } from '../../model/departure';
import { Router } from '@angular/router';

@Component({
  selector: 'app-departures-timetable',
  templateUrl: './departures-timetable.component.html',
  styleUrls: ['./departures-timetable.component.scss']
})
export class DeparturesTimetableComponent implements OnDestroy {
  page: number = 0
  pageSize: number = 5
  dataSource: Departure[] = []
  searchRequest: SearchRequest
  totalElements: number

  searchSubscription: Subscription

  constructor(private departureService: DeparturesService,
              private toast: ToastrService,
              private router: Router
  ) {}

  pageChanged(event: any) {
    this.page = event.pageIndex
    this.pageSize = event.pageSize
    this.searchRequest.page = this.page
    this.searchRequest.pageSize = this.pageSize

    this.loadData()
  }

  search(searchRequest: SearchRequest): void {
    this.searchRequest = searchRequest
    this.searchRequest.page = this.page
    this.searchRequest.pageSize = this.pageSize

    this.loadData()
  }

  loadData(): void {
    this.searchSubscription = this.departureService.searchDepartures(this.searchRequest).subscribe(
      res => {
        this.dataSource = res.content
        this.totalElements = res.totalElements
      },
      error => {
        this.toast.error(error.error, 'Error happened!')
      }
    )
  }

  goToDetails(element: Departure): void {
      this.router.navigate([`/railway-system/shared/departure-details/${element.id}/${this.searchRequest.startingStationId}/${this.searchRequest.destinationStationId}`])
  }

  ngOnDestroy(): void {
      if (this.searchSubscription) {
        this.searchSubscription.unsubscribe()
      }
  }

}
