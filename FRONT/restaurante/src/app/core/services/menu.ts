import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Menu, Postre, Bebida } from '../../shared/models/producto.model';

@Injectable({ providedIn: 'root' })
export class MenuService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getMenus(): Observable<Menu[]> {
    return this.http.get<Menu[]>(`${this.apiUrl}/menus`);
  }

  getPostres(): Observable<Postre[]> {
    return this.http.get<Postre[]>(`${this.apiUrl}/postres`);
  }

  getBebidas(): Observable<Bebida[]> {
    return this.http.get<Bebida[]>(`${this.apiUrl}/bebidas`);
  }
}
