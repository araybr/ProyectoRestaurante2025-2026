import {Component, HostListener} from '@angular/core';
import { AuthService } from '../../../core/services/auth';
import {NgClass, NgIf} from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  templateUrl: './navbar.html',
  imports: [
    NgIf,
    NgClass
  ],
  styleUrls: ['./navbar.css']
})
export class Navbar {
  menuOpen = false;
  scrolled = false;

  constructor(public auth: AuthService, private router: Router) {}

  @HostListener('window:scroll', [])
  onWindowScroll() {
    this.scrolled = window.scrollY > 50;
  }


  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}

