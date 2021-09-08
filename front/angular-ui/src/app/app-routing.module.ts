import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {AdminComponent} from "./admin/admin.component";
import {ProfComponent} from "./prof/prof.component";
import {PlanningManuelGeneratorComponent} from "./planning-manuel-generator/planning-manuel-generator.component";
import {TableauContraintesComponent} from './tableau-contraintes/tableau-contraintes.component';
import { AuthGuard } from './services/auth-guard.service';
const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'admin',component:AdminComponent,canActivate: [AuthGuard]},
  {path:'prof',component:ProfComponent,canActivate: [AuthGuard]},
  {path:'manuel',component:PlanningManuelGeneratorComponent,canActivate: [AuthGuard]},
  {path:'tableau',component:TableauContraintesComponent,canActivate: [AuthGuard]},
  {path:'',redirectTo:'/admin',pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
