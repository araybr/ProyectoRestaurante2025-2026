import { Component } from '@angular/core';
import { AuthService } from '../../core/services/auth';

@Component({
  selector: 'app-perfil',
  imports: [],
  templateUrl: './perfil.html',
  styleUrl: './perfil.css'
})
export class Perfil {
  constructor(public auth: AuthService) {}
}
