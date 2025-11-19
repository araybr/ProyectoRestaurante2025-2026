import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(private auth: AuthService, private router: Router) {}

  canActivate(): boolean {
    const usuario = this.auth.getUsuarioActual();
    console.log('Usuario actual en guard:', usuario);

    if (usuario?.rol === 'admin') {
      return true;
    }

    this.router.navigate(['/']);
    return false;
  }
}
