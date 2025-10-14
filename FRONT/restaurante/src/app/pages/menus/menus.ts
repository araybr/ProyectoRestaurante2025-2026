import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../core/services/api';
import { Menu } from '../../shared/models/menu.model';
import {CurrencyPipe} from '@angular/common';

@Component({
  selector: 'app-menus',
  imports: [
    CurrencyPipe
  ],
  templateUrl: './menus.html',
  styleUrl: './menus.css'
})
export class Menus implements OnInit {
  menus: Menu[] = [];

  constructor(private api: ApiService) {}

  ngOnInit() {
    this.api.getMenus().subscribe({
      next: (data) => this.menus = data,
      error: (err) => console.error('Error cargando menús:', err)
    });
  }
}
