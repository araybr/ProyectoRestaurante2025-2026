import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { AuthService } from './auth';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class Pedido {
  private apiUrl = 'http://localhost:8080/api/pedidos';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  /** 🧾 Obtiene el carrito del usuario actual */
  obtenerCarrito(): Observable<any> {
    const usuario = this.authService.getUsuarioActual();
    if (!usuario) return throwError(() => new Error('Debes iniciar sesión'));

    return this.http.get(`${this.apiUrl}/carrito/${usuario.id}`).pipe(
      catchError(err => {
        console.error('Error obteniendo carrito', err);
        return throwError(() => err);
      })
    );
  }

  /** 🛒 Agrega un menú, bebida o postre al carrito */
  agregarAlCarrito(idProducto: number, tipo: string): Observable<any> {
    const usuario = this.authService.getUsuarioActual();
    if (!usuario) return throwError(() => new Error('Debes iniciar sesión'));

    const params = new HttpParams()
      .set('idProducto', idProducto.toString())
      .set('tipo', tipo);

    return this.http.post(`${this.apiUrl}/carrito/${usuario.id}/agregar`, null, { params }).pipe(
      catchError(err => {
        console.error('Error al agregar al carrito', err);
        return throwError(() => err);
      })
    );
  }

  /** ❌ Elimina un detalle concreto del carrito */
  eliminarDetalle(idDetalle: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/detalle/${idDetalle}`).pipe(
      catchError(err => {
        console.error('Error al eliminar detalle', err);
        return throwError(() => err);
      })
    );
  }

  /** ✅ Finaliza el pedido (por ejemplo al hacer checkout) */
  finalizarPedido(): Observable<any> {
    const usuario = this.authService.getUsuarioActual();
    if (!usuario) return throwError(() => new Error('Debes iniciar sesión'));

    return this.http.post(`${this.apiUrl}/carrito/${usuario.id}/finalizar`, {}).pipe(
      catchError(err => {
        console.error('Error al finalizar pedido', err);
        return throwError(() => err);
      })
    );
  }
}
