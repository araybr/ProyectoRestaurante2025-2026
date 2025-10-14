import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { Menus } from './pages/menus/menus';
import { Login } from './pages/login/login';
import { Perfil } from './pages/perfil/perfil';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'menus', component: Menus },
  { path: 'login', component: Login },
  { path: 'perfil', component: Perfil },
  { path: '**', redirectTo: '' }
];
