import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../core/services/auth';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  email = '';
  password = '';
  error = '';

  constructor(private auth: AuthService) {}

  login() {
    const ok = this.auth.login(this.email, this.password);
    if (!ok) this.error = 'Credenciales incorrectas';
  }
}
