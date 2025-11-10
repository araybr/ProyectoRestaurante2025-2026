import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../core/services/auth';
import { Pedido } from '../../core/services/pedido';
import { Usuario } from '../../shared/models/usuario.model';


@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './perfil.html',
  styleUrls: ['./perfil.css']
})
export class Perfil implements OnInit {
  usuario: Usuario | null = null;
  carrito: any = null;
  loading: boolean = false;

  constructor(private auth: AuthService, private pedidoService: Pedido) {}

  ngOnInit(): void {
    this.usuario = this.auth.getUsuarioActual();
    if (this.usuario) {
      this.cargarCarrito();
    }
  }

  cargarCarrito(): void {
    this.pedidoService.obtenerCarrito(this.usuario!.id_usuario).subscribe({
      next: (res) => this.carrito = res,
      error: (err) => console.error(err)
    });
  }

  eliminarDetalle(idDetalle: number) {
    this.pedidoService.eliminarDetalle(idDetalle).subscribe({
      next: () => this.cargarCarrito(),
      error: (err) => console.error(err)
    });
  }

  finalizarPedido() {
    this.pedidoService.finalizarPedido(this.usuario!.id_usuario).subscribe({
      next: () => {
        alert('Pedido finalizado');
        this.cargarCarrito();
      },
      error: (err) => console.error(err)
    });
  }
}
