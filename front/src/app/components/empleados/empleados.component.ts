import { Component, OnInit } from '@angular/core';
import { of } from 'rxjs';
import { EmpleadoService } from 'src/app/services/empleado.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-empleados',
  templateUrl: './empleados.component.html',
  styleUrls: ['./empleados.component.css']
})
export class EmpleadosComponent implements OnInit {
  empleados: any[] = [];
  selectedItem: any = {};

  isShowForm: boolean = false;
  form: any = {};

  constructor(private empleadoService: EmpleadoService) {
  }

  ngOnInit(): void {
    this.loadEmpleados();
  }

  onRowSelect(event: any): void {

  }

  loadEmpleados() {
    this.empleadoService.getAll().subscribe({
      next: response => {
        this.empleados = JSON.parse(JSON.stringify(response));
      },
      error: err => {
        console.log('Error al listar empleados:', err);
      }
    });
  }

  showForm() {
    this.form = {
      empId: null,
      cedula: '',
      nombres: '',
      apellidos: '',
      email: ''
    }
    this.isShowForm = true;
  }

  cancel() {
    this.isShowForm = false;
  }

  eliminar(empleado: any) {
    this.empleadoService.delete(empleado.empId).subscribe({
      next: response => {
        Swal.fire({ text: 'Eliminado exitoso', icon: 'success' });
        this.loadEmpleados();
      },
      error: err => {
        let errorMessage = '';
        if (err.error.errors) {
          errorMessage = err.error.errors.join(',')
        }
        Swal.fire({ text: `Ocurrio un error ${errorMessage}`, icon: 'error' });
        console.log('Error al eliminar empleado', err);
      }
    });
  }

  editar(empleado: any) {
    this.form = empleado;
    this.isShowForm = true;
  }

  save() {
    if (this.form.empId) {
      this.empleadoService.update(this.form.empId, this.form).subscribe({
        next: response => {
          Swal.fire({ text: 'Actualizado exitoso', icon: 'success' });
          this.cancel();
          this.loadEmpleados();
        },
        error: err => {
          let errorMessage = '';
          if (err.error.errors) {
            errorMessage = err.error.errors.join(',')
          }
          Swal.fire({ text: `Ocurrio un error ${errorMessage}`, icon: 'error' });
          console.log('Error al actualizar empleado', err);
        }
      });

    }
    else {
      this.empleadoService.save(this.form).subscribe({
        next: response => {
          Swal.fire({ text: 'Registro exitoso', icon: 'success' });
          this.cancel();
          this.loadEmpleados();
        },
        error: err => {
          let errorMessage = '';
          if (err.error.errors) {
            errorMessage = err.error.errors.join(',')
          }
          Swal.fire({ text: `Ocurrio un error ${errorMessage}`, icon: 'error' });
          console.log('Error al guardar empleado', err);
        }
      });
    }

  }

}
