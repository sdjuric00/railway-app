import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RootLayoutComponent } from './components/root-layout/root-layout.component';

const routes: Routes = [
  {
    path: "railway-system",
    component: RootLayoutComponent,
    children: [
      {
        path: "auth",
        loadChildren: () => 
          import("./../../auth/auth.module").then((m) => m.AuthModule)
      },
      {
        path: "shared",
        loadChildren: () => 
          import("./../../shared/shared.module").then((m) => m.SharedModule)
      },
      {
        path: "regular",
        loadChildren: () => 
          import("./../../regular-user/regular.module").then((m) => m.RegularModule)
      },
    ],
  },
  {
    path: "",
    redirectTo: "/railway-system/auth/login",
    pathMatch: "full",
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
