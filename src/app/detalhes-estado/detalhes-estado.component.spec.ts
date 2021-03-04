import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalhesEstadoComponent } from './detalhes-estado.component';

describe('DetalhesEstadoComponent', () => {
  let component: DetalhesEstadoComponent;
  let fixture: ComponentFixture<DetalhesEstadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetalhesEstadoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalhesEstadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
