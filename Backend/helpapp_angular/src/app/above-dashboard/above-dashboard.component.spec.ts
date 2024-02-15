import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AboveDashboardComponent } from './above-dashboard.component';

describe('AboveDashboardComponent', () => {
  let component: AboveDashboardComponent;
  let fixture: ComponentFixture<AboveDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AboveDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AboveDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
