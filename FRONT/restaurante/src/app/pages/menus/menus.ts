import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuService } from '../../core/services/menu';
import { Pedido } from '../../core/services/pedido';
import { AuthService } from '../../core/services/auth';
import { Menu, Postre, Bebida } from '../../shared/models/producto.model';
import {data} from 'jquery';

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
  usuarioLogueado: any = null;

  constructor(
    private menuService: MenuService,
    private pedidoService: Pedido,
    private auth: AuthService
  ) {}

  ngOnInit(): void {
    const usuario = this.auth.getUsuarioActual();
    this.usuarioId = usuario?.id ?? null;
    this.usuarioLogueado = usuario;

    this.cargarProductos();
  }

  cargarProductos() {
    // Menús
    this.menuService.getMenus().subscribe((menusFromBackend: any[]) => {
      this.menus = menusFromBackend.map(menu => ({
        id: menu.id_menu,
        nombre: menu.nombre,
        descripcion: menu.descripcion,
        precio: menu.precio,
        imagen_url: menu.imagen_url
      }));
    });

    // Postres
    this.menuService.getPostres().subscribe((postresFromBackend: any[]) => {
      this.postres = postresFromBackend.map(postre => ({
        id: postre.id_postre,
        nombre: postre.nombre,
        descripcion: postre.descripcion,
        precio: postre.precio,
        imagen_url: postre.imagen_url
      }));
    });

    // Bebidas
    this.menuService.getBebidas().subscribe((bebidasFromBackend: any[]) => {
      this.bebidas = bebidasFromBackend.map(bebida => ({
        id: bebida.id_bebida,
        nombre: bebida.nombre,
        descripcion: bebida.descripcion,
        precio: bebida.precio,
        imagen_url: bebida.imagen_url
      }));
    });
  }

  agregarAlCarrito(tipo: 'menu' | 'postre' | 'bebida', producto: any) {
    if (!this.usuarioId) return alert('Debes iniciar sesión para añadir productos');

    console.log('Producto que se va a agregar:', producto.id);

    this.pedidoService.agregarAlCarrito(producto.id, tipo).subscribe({
      next: () => alert(`${producto.nombre} añadido al carrito`),
      error: (err) => console.error(err)
    });
  }


  eliminarProducto(tipo: 'menu' | 'postre' | 'bebida', id: number) {
    if (!confirm('¿Seguro que quieres eliminar este producto?')) return;

    this.menuService.eliminarProducto(tipo, id).subscribe({
      next: () => {
        alert('Producto eliminado');
        this.cargarProductos();
      },
      error: err => console.error('Error eliminando producto', err)
    });
  }
}
