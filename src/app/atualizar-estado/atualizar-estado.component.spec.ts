import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtualizarEstadoComponent } from './atualizar-estado.component';

describe('AtualizarEstadoComponent', () => {
  let component: AtualizarEstadoComponent;
  let fixture: ComponentFixture<AtualizarEstadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtualizarEstadoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AtualizarEstadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
