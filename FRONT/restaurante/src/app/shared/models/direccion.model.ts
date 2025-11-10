export interface Direccion {
  id_direccion: number;
  calle: string;
  numero: string;
  ciudad: string;
  provincia: string;
  cp: string;
  principal?: boolean;
}
