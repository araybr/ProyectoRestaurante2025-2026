import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private isLoggedIn = false;

  login(email: string, password: string): boolean {
    // Ejemplo simple
    if (email === 'admin@restaurante.com' && password === '1234') {
      this.isLoggedIn = true;
      return true;
    }
    return false;
  }

  logout() {
    this.isLoggedIn = false;
  }

  get authenticated() {
    return this.isLoggedIn;
  }
}

