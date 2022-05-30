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
import { ChangeRouteService } from './services/change-route.service';
import {AllCoursesComponent} from "./all-courses/all-courses.component";

const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'admin',component:AdminComponent,canActivate: [AuthGuard,ChangeRouteService]},
  {path:'prof',component:ProfComponent,canActivate: [AuthGuard,ChangeRouteService]},
  {path:'manuel',component:PlanningManuelGeneratorComponent,canActivate: [AuthGuard,ChangeRouteService]},
  {path:'tableau',component:TableauContraintesComponent,canActivate: [AuthGuard,ChangeRouteService]},
  {path:'auto',component:PlanningAutoGeneratorComponent,canActivate: [AuthGuard,ChangeRouteService]},
  {path:'all-plannings',component:AllPlanningsComponent, canActivate: [AuthGuard,ChangeRouteService]},
  {path:'',redirectTo:'/admin',pathMatch:'full'},
  {path:'les-utilisateurs',component:AllUsersComponent,canActivate: [AuthGuard,ChangeRouteService]},
  {path:'les_salles',component:AllRoomsComponent,canActivate: [AuthGuard,ChangeRouteService]},
  {path:'les-departements',component:DepartmentComponent,canActivate: [AuthGuard,ChangeRouteService]},
  {path:'les-contraintes',component:AllConstraintsComponent,canActivate: [AuthGuard,ChangeRouteService]},
  {path:'les-groupes',component:AllGroupsComponent,canActivate: [AuthGuard,ChangeRouteService]},
  {path:'register',component:RegisterComponent},
  {path:'les-cours',component:AllCoursesComponent,canActivate: [AuthGuard,ChangeRouteService]}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
