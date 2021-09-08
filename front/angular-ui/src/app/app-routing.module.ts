import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {AdminComponent} from "./admin/admin.component";
import {ProfComponent} from "./prof/prof.component";
import {PlanningManuelGeneratorComponent} from "./planning-manuel-generator/planning-manuel-generator.component";
import {AdmindashboardComponent} from './admindashboard/admindashboard.component';
import {TableauContraintesComponent} from './tableau-contraintes/tableau-contraintes.component';
const routes: Routes = [{path:'login',component:LoginComponent},
  {path:'admin',component:AdminComponent},
  {path:'prof',component:ProfComponent},
  {path:'manuel',component:PlanningManuelGeneratorComponent},
  {path:'tableau',component:TableauContraintesComponent},
  {path:'admindashboard', component:AdmindashboardComponent},
  {path:'',redirectTo:'/login',pathMatch:'full'}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
