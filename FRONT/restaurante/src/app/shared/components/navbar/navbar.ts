import { Component } from '@angular/core';
import { AuthService } from '../../../core/services/auth';
import {NgIf} from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  templateUrl: './navbar.html',
  imports: [
    NgIf
  ],
  styleUrls: ['./navbar.css']
})
export class Navbar {
  menuOpen = false;

  constructor(public auth: AuthService, private router: Router) {}

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}

