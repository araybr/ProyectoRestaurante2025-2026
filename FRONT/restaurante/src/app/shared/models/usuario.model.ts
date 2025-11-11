import { Direccion } from './direccion.model';
import { Alergeno } from './alergeno.model';

export interface Usuario {
  id: number;
  nombre: string;
  apellidos: string;
  email: string;
  password?: string;
  telefono?: string;
  fechaRegistro?: string;
  rol?: 'cliente' | 'admin' | 'repartidor';
  activo?: boolean;
  direcciones?: Direccion[];
  alergenos?: Alergeno[];
}
