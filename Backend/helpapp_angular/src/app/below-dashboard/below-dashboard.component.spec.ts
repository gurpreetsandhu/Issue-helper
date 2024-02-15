import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BelowDashboardComponent } from './below-dashboard.component';

describe('BelowDashboardComponent', () => {
  let component: BelowDashboardComponent;
  let fixture: ComponentFixture<BelowDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BelowDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BelowDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
