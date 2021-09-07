import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {AdminComponent} from "./admin/admin.component";
import {ProfComponent} from "./prof/prof.component";

const routes: Routes = [{path:'login',component:LoginComponent},
  {path:'admin',component:AdminComponent},
  {path:'prof',component:ProfComponent},
  {path:'',redirectTo:'/login',pathMatch:'full'}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
