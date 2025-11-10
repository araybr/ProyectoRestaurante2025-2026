import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { AuthService } from '../../core/services/auth';
import { Usuario } from '../../shared/models/usuario.model';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})

export class Login {
  loginForm: FormGroup;
  registerForm: FormGroup;
  isRegistering: boolean = false;
  loading: boolean = false;
  errorMessage: string = '';
  successMessage: string = '';
  usuarioLogueado?: Usuario;

  constructor(private fb: FormBuilder, private authService: AuthService) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });

    this.registerForm = this.fb.group({
      nombre: ['', Validators.required],
      apellidos: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      telefono: ['']
    });
  }

  toggleForm(): void {
    this.isRegistering = !this.isRegistering;
    this.errorMessage = '';
    this.successMessage = '';
  }

  onLogin(): void {
    if (this.loginForm.invalid) return;
    this.loading = true;
    const usuario: Usuario = this.loginForm.value;
    this.authService.login(usuario).subscribe({
      next: (res) => {
        this.loading = false;
        this.usuarioLogueado = res;
        this.successMessage = `Bienvenido, ${res.nombre}`;
      },
      error: () => {
        this.loading = false;
        this.errorMessage = 'Credenciales incorrectas o usuario no encontrado.';
      }
    });
  }

  onRegister(): void {
    if (this.registerForm.invalid) return;
    this.loading = true;
    const nuevoUsuario: Usuario = this.registerForm.value;
    this.authService.register(nuevoUsuario).subscribe({
      next: (res) => {
        this.loading = false;
        this.successMessage = `Usuario ${res.nombre} creado correctamente.`;
        this.isRegistering = false;
      },
      error: (err) => {
        this.loading = false;
        if (err.status === 409) {
          this.errorMessage = 'Ya existe un usuario con ese correo.';
        } else {
          this.errorMessage = 'Error al registrar el usuario. Intenta de nuevo.';
        }
        this.successMessage = '';
      }
    });
  }
}
