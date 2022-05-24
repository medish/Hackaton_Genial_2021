import { NgModule } from '@angular/core';
import { FullCalendarModule } from '@fullcalendar/angular';
import { BrowserModule } from '@angular/platform-browser';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { ProfComponent } from './prof/prof.component';
import { HeaderComponent } from './header/header.component';
import { TableauContraintesComponent } from './tableau-contraintes/tableau-contraintes.component';
import { HttpClientModule } from '@angular/common/http';
import {PlanningManuelGeneratorComponent} from "./planning-manuel-generator/planning-manuel-generator.component";
import dayGridPlugin from '@fullcalendar/daygrid'; // a plugin!
import interactionPlugin from '@fullcalendar/interaction';
import { authInterceptorProviders } from './interceptors/auth.interceptor';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PlanningAutoGeneratorComponent } from './planning-auto-generator/planning-auto-generator.component';
import { GetFileConstraintsComponent } from './get-file-constraints/get-file-constraints.component';
import { ConstraintsComponent } from './constraints/constraints.component';
import { PdfExportComponent } from './pdf-export/pdf-export.component';
import { AllPlanningsComponent } from './all-plannings/all-plannings.component';
import { CardPlanningComponent } from './all-plannings/card-planning/card-planning.component';
import { AllUsersComponent } from './all-users/all-users.component';
import {DataTablesModule} from "angular-datatables";
import { AllRoomsComponent } from './all-rooms/all-rooms.component';
import { DepartmentComponent } from './department/department.component';
import { AllConstraintsComponent } from './all-constraints/all-constraints.component';
import { AllGroupsComponent } from './all-groups/all-groups.component';

FullCalendarModule.registerPlugins([ // register FullCalendar plugins
  dayGridPlugin,
  interactionPlugin
]);

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminComponent,
    ProfComponent,
    HeaderComponent,
    TableauContraintesComponent,
    PlanningManuelGeneratorComponent,
    PlanningAutoGeneratorComponent,
    GetFileConstraintsComponent,
    ConstraintsComponent,
    PdfExportComponent,
    AllPlanningsComponent,
    CardPlanningComponent,
    AllUsersComponent,
    AllRoomsComponent,
    DepartmentComponent,
    AllConstraintsComponent,
    AllGroupsComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FullCalendarModule,
    FormsModule,
    ReactiveFormsModule,
    CollapseModule.forRoot(),
    DataTablesModule,
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
