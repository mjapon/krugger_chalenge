import { Component, OnInit } from '@angular/core';
import { SelectItem } from 'primeng/api';
import { AuthService } from 'src/app/services/auth.service';
import { EmpleadoService } from 'src/app/services/empleado.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-datosempleado',
  templateUrl: './datosempleado.component.html',
  styleUrls: ['./datosempleado.component.css']
})
export class DatosempleadoComponent implements OnInit {

  empleado: any = {}
  isShowForm = false;
  value: Date = new Date();
  fechaNac: Date = new Date();
  fechaVacuna: Date = new Date();

  datosVacuna: any = {};


  constructor(private empleadoService: EmpleadoService,
    private authService: AuthService) { }

  ngOnInit(): void {
    this.loadDatosEmpleado();
    this.initDatosVacuna();
  }

  initDatosVacuna() {
    this.datosVacuna = {
      tipo: 0,
      fecha: null,
      ndosis: null,
    };
  }

  loadDatosEmpleado(): void {
    let empid = this.authService.getEmpId() || '0';
    this.empleadoService.getBydId(empid).subscribe({
      next: response => {
        this.empleado = response;
        if (this.empleado.datosVacuna) {
          this.datosVacuna = this.empleado.datosVacuna;
        }
      },
      error: err => {
        Swal.fire({ text: 'Ocurrio un error al obtener la información del empleado', icon: 'error' });
        console.log("Error al obtener lo datos del empleado ", err);
      }
    });
  }

  actualizar() {
    if (this.empleado.vacunado) {
      this.empleado.datosVacuna = this.datosVacuna;
    }
    else {
      this.empleado.datosVacuna = null;
    }

    this.empleadoService.update(this.empleado.empId, this.empleado).subscribe({
      next: response => {
        console.log('Valor de response es', response);
        Swal.fire({ text: 'Actualizado exitoso', icon: 'success' });
        this.loadDatosEmpleado();
        this.isShowForm = false;
      },
      error: err => {
        console.log("Error al actualizar:", err);
        let errorMessage = '';
        if (err && err.error && err.error.errors) {
          errorMessage = err.error.errors.join(',')
        }
        Swal.fire({ text: `Ocurrio un error ${errorMessage}`, icon: 'error' });
      }
    });
  }

  cancelar() {
    this.isShowForm = false;
  }

  showFormEdit() {
    this.isShowForm = true;

  }

}
