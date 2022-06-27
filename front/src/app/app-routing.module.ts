import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DatosempleadoComponent } from './components/datosempleado/datosempleado.component';
import { EmpleadosComponent } from './components/empleados/empleados.component';
import { ForbiddenComponent } from './components/forbidden/forbidden.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { StartComponent } from './components/start/start.component';
import { AuthGuard } from './shared/auth.guard';
import { RoleGuard } from './shared/role.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  {
    path: 'empleyees', component: EmpleadosComponent,
    canActivate: [RoleGuard],
    data: {
      role: 'admin'
    }
  },
  {
    path: 'employedetails', component: DatosempleadoComponent,
    canActivate: [RoleGuard],
    data: {
      role: 'empleado'
    }
  },
  { path: 'forbidden', component: ForbiddenComponent },
  { path: '**', component: StartComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
