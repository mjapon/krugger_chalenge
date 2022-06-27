import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'front';

  isAutenticated: boolean = false;

  constructor(private autService: AuthService,
    private router: Router) {
  }
  ngOnInit(): void {
    this.checkUserStatus();

  }

  checkUserStatus() {
    this.isAutenticated = this.autService.isLoggedIn();
  }

  doLogout() {
    this.autService.doLogout();
    this.checkUserStatus();
    this.router.navigate(['']);
  }

}
