import { Component, EventEmitter, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { enviroments } from 'src/enviroments/enviroments';

@Component({
  selector: 'app-paypal-buying-form',
  templateUrl: './paypal-buying-form.component.html',
  styleUrls: ['./paypal-buying-form.component.scss']
})
export class PaypalBuyingFormComponent {
  @Output() onBuyTokens = new EventEmitter()

  pricePerToken: number = enviroments.pricePerToken

  tokenForm = new FormGroup({
      numOfTokens: new FormControl(0, [
        Validators.required,
        Validators.pattern('[1-9][0-9]*')
      ]),
  })

  calculateValue(): number {
    const numOfTokens = this.tokenForm.get('numOfTokens')?.value
    return (numOfTokens) ? (numOfTokens * this.pricePerToken)
                        : 0
  }

  buyTokens(): void {
    const numOfTokens = this.tokenForm.get('numOfTokens')?.value
    if (numOfTokens) {
      this.onBuyTokens.emit(numOfTokens)
    } 
  }

}
