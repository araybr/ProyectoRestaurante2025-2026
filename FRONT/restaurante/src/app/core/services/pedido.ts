import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class Pedido {
  private apiUrl = 'http://localhost:8080/api/pedidos';

  constructor(private http: HttpClient) {}

  obtenerCarrito(usuarioId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/carrito/${usuarioId}`);
  }

  agregarAlCarrito(usuarioId: number, idProducto: number, tipo: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/carrito/${usuarioId}/agregar`, null, {
      params: { idProducto, tipo }
    });
  }

  eliminarDetalle(idDetalle: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/detalle/${idDetalle}`);
  }

  finalizarPedido(usuarioId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/carrito/${usuarioId}/finalizar`, {});
  }
}
