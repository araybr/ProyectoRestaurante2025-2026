import { Component, HostListener, OnInit } from '@angular/core';
import { AuthService } from '../../../core/services/auth';
import { NgClass, NgIf } from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import { Usuario } from '../../../shared/models/usuario.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navbar',
  standalone: true,
  templateUrl: './navbar.html',
  imports: [NgIf, NgClass, RouterLink],
  styleUrls: ['./navbar.css']
})
export class Navbar {
  menuOpen = false;
  scrolled = false;
  usuarioLogueado: Usuario | null = null;

  constructor(public auth: AuthService, private router: Router) {
    this.auth.usuario$.subscribe(user => {
      this.usuarioLogueado = user;
    });
  }

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
