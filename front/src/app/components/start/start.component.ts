import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-start',
  template: `<div>
    App Vacunas Krugger
  </div>`
})
export class StartComponent implements OnInit {

  constructor(private autService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.checkUserStatus();
  }

  checkUserStatus(): void {
    if (this.autService.isLoggedIn()) {
      this.router.navigate(['home']);
    }
    else {
      this.router.navigate(['login']);
    }
  }

}
