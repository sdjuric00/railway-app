import { Component, EventEmitter, Output } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

const maxNumOfPassengers: number = 3


@Component({
  selector: 'app-adding-people-inputs',
  templateUrl: './adding-people-inputs.component.html',
  styleUrls: ['./adding-people-inputs.component.scss']
})
export class AddingPeopleInputsComponent {
  @Output() addedPassengersEvent = new EventEmitter()

  ticketRequestForm: FormGroup
  passengers: string[] = []

  newPerson(): FormGroup {  
    return this.fb.group({  
      fullName: new FormControl('', [Validators.required ])
    })  
  }  

  constructor(private fb:FormBuilder,
              private toastService: ToastrService  
  ) {
    this.ticketRequestForm = this.fb.group({  
      fullName: new FormControl('', [Validators.required ]),
      numOfPeople: this.fb.array([]),  
    });  
  }

  numOfPeople() : FormArray {  
    return this.ticketRequestForm.get("numOfPeople") as FormArray  
  }  

  addPerson() {  
    if (this.numOfPeople().length < maxNumOfPassengers) {
      this.numOfPeople().push(this.newPerson());  
    } else {
      this.toastService.error(`You cannot add more than ${maxNumOfPassengers + 1} people per transaction.`, 'Error happened!')
    }
  }  
     
  removePerson(i:number) {  
    this.numOfPeople().removeAt(i);  
  }  
     
  onSubmit() {  
    if (this.ticketRequestForm.valid) {
      this.passengers = []
      this.passengers.push(this.ticketRequestForm.get('fullName')?.value)
      for (let element of this.ticketRequestForm.get('numOfPeople')?.value) {
        this.passengers.push(element['fullName'])
      } 

      console.log(this.passengers)
      this.addedPassengersEvent.emit(this.passengers)
    } else {
      this.toastService.error('Form is invalid, check all fields.', 'Error happened!')
    }
  }  


}
