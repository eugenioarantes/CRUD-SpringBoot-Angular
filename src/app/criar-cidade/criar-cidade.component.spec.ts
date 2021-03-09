import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CriarCidadeComponent } from './criar-cidade.component';

describe('CriarCidadeComponent', () => {
  let component: CriarCidadeComponent;
  let fixture: ComponentFixture<CriarCidadeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CriarCidadeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CriarCidadeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
