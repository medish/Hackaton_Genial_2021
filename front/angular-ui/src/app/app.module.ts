import { NgModule } from '@angular/core';
import { FullCalendarModule } from '@fullcalendar/angular';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { ProfComponent } from './prof/prof.component';
import { HeaderComponent } from './header/header.component';
import { AComponent } from './a/a.component';
import { BComponent } from './b/b.component';
import { TableauContraintesComponent } from './tableau-contraintes/tableau-contraintes.component';
import { InputConstraintComponent } from './input-constraint/input-constraint.component';
import { HttpClientModule } from '@angular/common/http';
import {PlanningManuelGeneratorComponent} from "./planning-manuel-generator/planning-manuel-generator.component";
import dayGridPlugin from '@fullcalendar/daygrid'; // a plugin!
import interactionPlugin from '@fullcalendar/interaction';


FullCalendarModule.registerPlugins([ // register FullCalendar plugins
  dayGridPlugin,
  interactionPlugin
]);

import { MatCardModule } from '@angular/material/card';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminComponent,
    ProfComponent,
    HeaderComponent,
    AComponent,
    BComponent,
    TableauContraintesComponent,
    InputConstraintComponent,
    PlanningManuelGeneratorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FullCalendarModule,
    MatCardModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
