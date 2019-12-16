import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component'
import { EmailListComponent } from './email-list/email-list.component';


const routes: Routes = [
    { path: '', redirectTo: 'emails', pathMatch: 'full' },
    { path: 'emails', component: EmailListComponent },
    { path: '**', redirectTo: 'emails', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
