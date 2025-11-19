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

  crearMenu(menu: Partial<Menu>): Observable<Menu> {
    return this.http.post<Menu>(`${this.apiUrl}/menus`, menu);
  }

  crearPostre(postre: Partial<Postre>): Observable<Postre> {
    return this.http.post<Postre>(`${this.apiUrl}/postres`, postre);
  }

  crearBebida(bebida: Partial<Bebida>): Observable<Bebida> {
    return this.http.post<Bebida>(`${this.apiUrl}/bebidas`, bebida);
  }

  eliminarProducto(tipo: 'menu' | 'postre' | 'bebida', id: number): Observable<void> {
    let url = '';
    switch (tipo) {
      case 'menu':
        url = `${this.apiUrl}/menus/${id}`;
        break;
      case 'postre':
        url = `${this.apiUrl}/postres/${id}`;
        break;
      case 'bebida':
        url = `${this.apiUrl}/bebidas/${id}`;
        break;
    }
    return this.http.delete<void>(url);
  }

}
