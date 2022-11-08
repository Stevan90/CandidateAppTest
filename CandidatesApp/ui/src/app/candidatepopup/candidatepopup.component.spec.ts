import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidatepopupComponent } from './candidatepopup.component';

describe('CandidatepopupComponent', () => {
  let component: CandidatepopupComponent;
  let fixture: ComponentFixture<CandidatepopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CandidatepopupComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CandidatepopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
