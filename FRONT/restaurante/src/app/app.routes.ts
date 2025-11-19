import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { Menus } from './pages/menus/menus';
import { Login } from './pages/login/login';
import { Perfil } from './pages/perfil/perfil';
import {AdminGuard} from './core/services/admin.guard';
import {AdminPage} from './pages/admin-page/admin-page';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'menus', component: Menus },
  { path: 'login', component: Login },
  { path: 'perfil', component: Perfil },
  {
    path: 'admin',
    component: AdminPage,
    canActivate: [AdminGuard]
  },
  { path: '**', redirectTo: '' }

];
