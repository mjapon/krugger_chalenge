import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private autService: AuthService,
    private router: Router) {
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  ngOnInit(): void {

  }

  doLogin() {
    if (this.loginForm.valid) {
      this.autService
        .doLogin(this.loginForm.value)
        .subscribe({
          next: response => {
            let loginresp: any = response;
            this.autService.saveToken(loginresp.token, loginresp.empid);
            Swal.fire({ text: `Ingreso exitoso, Bienvenido ${loginresp.userinfo}!`, icon: 'success' });
            this.router.navigate(['home']);
          },
          error: e => {
            Swal.fire({ text: 'Credenciales incorrectas, verifique', icon: 'error' });
            console.log('Error:', e);
          }
        });
    }
    else {
      Swal.fire({ text: 'Ingrese su correo y su clave', icon: 'warning' });
    }
  }
}
