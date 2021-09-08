import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {AdminComponent} from "./admin/admin.component";
import {ProfComponent} from "./prof/prof.component";
import {PlanningManuelGeneratorComponent} from "./planning-manuel-generator/planning-manuel-generator.component";
import {TableauContraintesComponent} from './tableau-contraintes/tableau-contraintes.component';
<<<<<<< HEAD
import { AuthGuard } from './services/auth-guard.service';
import { PlanningAutoGeneratorComponent } from './planning-auto-generator/planning-auto-generator.component';
const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'admin',component:AdminComponent,canActivate: [AuthGuard]},
  {path:'prof',component:ProfComponent,canActivate: [AuthGuard]},
  {path:'manuel',component:PlanningManuelGeneratorComponent,canActivate: [AuthGuard]},
  {path:'tableau',component:TableauContraintesComponent,canActivate: [AuthGuard]},
  {path:'auto',component:PlanningAutoGeneratorComponent,canActivate: [AuthGuard]},
  {path:'',redirectTo:'/admin',pathMatch:'full'}
];
=======
const routes: Routes = [{path:'login',component:LoginComponent},
  {path:'admin',component:AdminComponent},
  {path:'prof',component:ProfComponent},
  {path:'manuel',component:PlanningManuelGeneratorComponent},
  {path:'tableau',component:TableauContraintesComponent},
  {path:'',redirectTo:'/login',pathMatch:'full'}];
>>>>>>> Réparation des problèmes d'import

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
