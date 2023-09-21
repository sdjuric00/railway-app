import { NgModule } from "@angular/core";
import {MatToolbarModule} from "@angular/material/toolbar"
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import { MatOptionModule } from '@angular/material/core';
import {MatAutocompleteModule} from '@angular/material/autocomplete';

const MaterialConstants = [
    MatToolbarModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    MatOptionModule,
    MatAutocompleteModule
]

@NgModule({
  imports: [MaterialConstants],
  exports: [MaterialConstants]
})
export class MaterialModule { }