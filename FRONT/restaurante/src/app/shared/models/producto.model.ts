export interface Menu {
  id_menu: number;
  nombre: string;
  descripcion: string;
  precio: number;
  imagen_url?: string;
}

export interface Postre {
  id_postre: number;
  nombre: string;
  descripcion: string;
  precio: number;
  imagen_url?: string;
}

export interface Bebida {
  id_bebida: number;
  nombre: string;
  descripcion: string;
  precio: number;
  imagen_url?: string;
}
