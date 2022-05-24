import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {AdminComponent} from "./admin/admin.component";
import {ProfComponent} from "./prof/prof.component";
import {PlanningManuelGeneratorComponent} from "./planning-manuel-generator/planning-manuel-generator.component";
import {TableauContraintesComponent} from './tableau-contraintes/tableau-contraintes.component';
import {AuthGuard} from './services/auth-guard.service';
import {PlanningAutoGeneratorComponent} from './planning-auto-generator/planning-auto-generator.component';
import {AllPlanningsComponent} from './all-plannings/all-plannings.component';
import {AllUsersComponent} from "./all-users/all-users.component";

import { AllRoomsComponent } from './all-rooms/all-rooms.component';
import {DepartmentComponent} from "./department/department.component";
import {AllConstraintsComponent} from "./all-constraints/all-constraints.component";
import {AllGroupsComponent} from "./all-groups/all-groups.component";
import { RegisterComponent } from './register/register.component';
const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'admin',component:AdminComponent,canActivate: [AuthGuard]},
  {path:'prof',component:ProfComponent,canActivate: [AuthGuard]},
  {path:'manuel',component:PlanningManuelGeneratorComponent,canActivate: [AuthGuard]},
  {path:'tableau',component:TableauContraintesComponent,canActivate: [AuthGuard]},
  {path:'auto',component:PlanningAutoGeneratorComponent,canActivate: [AuthGuard]},
  {path:'all-plannings',component:AllPlanningsComponent, canActivate: [AuthGuard]},
  {path:'',redirectTo:'/admin',pathMatch:'full'},
  {path:'les-utilisateurs',component:AllUsersComponent,canActivate: [AuthGuard]},
  {path:'les_salles',component:AllRoomsComponent,canActivate: [AuthGuard]},
  {path:'les-departements',component:DepartmentComponent,canActivate: [AuthGuard]},
  {path:'les-contraintes',component:AllConstraintsComponent,canActivate: [AuthGuard]},
  {path:'les-groupes',component:AllGroupsComponent,canActivate: [AuthGuard]},
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
