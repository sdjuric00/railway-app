import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainBenefitsIconsComponent } from './train-benefits-icons.component';

describe('TrainBenefitsIconsComponent', () => {
  let component: TrainBenefitsIconsComponent;
  let fixture: ComponentFixture<TrainBenefitsIconsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TrainBenefitsIconsComponent]
    });
    fixture = TestBed.createComponent(TrainBenefitsIconsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
