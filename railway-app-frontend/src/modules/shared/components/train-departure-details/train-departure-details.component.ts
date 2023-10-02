import { Component, Input } from '@angular/core';
import { TrainBasic } from '../../model/train';

@Component({
  selector: 'app-train-departure-details',
  templateUrl: './train-departure-details.component.html',
  styleUrls: ['./train-departure-details.component.scss']
})
export class TrainDepartureDetailsComponent {
  @Input() train: TrainBasic
  @Input() numOfSoldTickets: number
  @Input() totalNumOfSeats: number
}
