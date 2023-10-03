import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserProfileManagementPageComponent } from './user-profile-management-page.component';

describe('UserProfileManagementPageComponent', () => {
  let component: UserProfileManagementPageComponent;
  let fixture: ComponentFixture<UserProfileManagementPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserProfileManagementPageComponent]
    });
    fixture = TestBed.createComponent(UserProfileManagementPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
