import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuService } from '../../core/services/menu';
import { Pedido } from '../../core/services/pedido';
import { AuthService } from '../../core/services/auth';
import { Menu, Postre, Bebida } from '../../shared/models/producto.model';

@Component({
  selector: 'app-menus',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './menus.html',
  styleUrls: ['./menus.css']
})
export class Menus implements OnInit {
  menus: Menu[] = [];
  postres: Postre[] = [];
  bebidas: Bebida[] = [];
  usuarioId: number | null = null;

  constructor(
    private menuService: MenuService,
    private pedidoService: Pedido,
    private auth: AuthService
  ) {}

  ngOnInit(): void {
    const usuario = this.auth.getUsuarioActual();
    this.usuarioId = usuario?.id_usuario ?? null;


    this.menuService.getMenus().subscribe((data) => this.menus = data);
    this.menuService.getPostres().subscribe((data) => this.postres = data);
    this.menuService.getBebidas().subscribe((data) => this.bebidas = data);
  }

  agregarAlCarrito(tipo: 'menu' | 'postre' | 'bebida', producto: any) {
    if (!this.usuarioId) return alert('Debes iniciar sesión para añadir productos');

    this.pedidoService.agregarAlCarrito(this.usuarioId, producto, tipo).subscribe({
      next: () => alert(`${producto.nombre} añadido al carrito`),
      error: (err) => console.error(err)
    });
  }
}
