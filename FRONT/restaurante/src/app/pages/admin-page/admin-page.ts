import {Component, OnInit} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MenuService } from '../../core/services/menu';
import { Menu, Postre, Bebida } from '../../shared/models/producto.model';
import { Usuario } from '../../shared/models/usuario.model';
import {UsuarioService} from '../../core/services/usuario';

@Component({
  selector: 'app-admin-page',
  standalone: true,
  imports: [FormsModule], //
  templateUrl: './admin-page.html',
  styleUrls: ['./admin-page.css']
})
export class AdminPage implements OnInit {

  usuarios: Usuario[] = [];

  constructor(
    private menuService: MenuService,
    private usuarioService: UsuarioService
  ) {}

  ngOnInit(): void {
    this.cargarUsuarios();
  }

  cargarUsuarios() {
    this.usuarioService.getUsuarios().subscribe(data => {
      this.usuarios = data;
    });
  }

  cambiarRol(usuario: Usuario, rol: string) {
    this.usuarioService.actualizarRol(usuario.id, rol).subscribe(() => {
      alert('Rol actualizado');
      usuario.rol = rol as any;
    });
  }

  eliminarUsuario(id: number) {
    if (!confirm("¿Seguro que deseas eliminar este usuario?")) return;

    this.usuarioService.deleteUsuario(id).subscribe(() => {
      this.usuarios = this.usuarios.filter(u => u.id !== id);
      alert("Usuario eliminado");
    });
  }

  nuevoProducto: Partial<Menu & Bebida & Postre & { tipo: string }> = {
    nombre: '',
    descripcion: '',
    precio: 0,
    imagen_url: '',
    tipo: 'menu'
  };

  agregarProducto() {
    const producto = {
      ...this.nuevoProducto,
      imagen_url: 'assets/images/' + this.nuevoProducto.imagen_url
    };

    switch (this.nuevoProducto.tipo) {
      case 'menu':
        this.menuService.crearMenu(producto).subscribe(() => alert('Menú añadido'));
        break;
      case 'bebida':
        this.menuService.crearBebida(producto).subscribe(() => alert('Bebida añadida'));
        break;
      case 'postre':
        this.menuService.crearPostre(producto).subscribe(() => alert('Postre añadido'));
        break;
    }

    // Limpiar formulario
    this.nuevoProducto = { nombre: '', descripcion: '', precio: 0, imagen_url: '', tipo: 'menu' };

  }
}
