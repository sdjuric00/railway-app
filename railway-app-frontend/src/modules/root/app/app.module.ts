import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RootLayoutComponent } from './components/root-layout/root-layout.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { BrowserAnimationsModule, provideAnimations } from '@angular/platform-browser/animations';
import { MaterialModule } from 'src/modules/material/material.module';
import { AuthModule } from 'src/modules/auth/auth.module';
import { SocialLoginModule, GoogleSigninButtonModule, GoogleLoginProvider, SocialAuthServiceConfig, MicrosoftLoginProvider } from '@abacritt/angularx-social-login';
import { enviroments } from 'src/enviroments/enviroments';
import { CommonModule } from '@angular/common';
import { ToastrModule, provideToastr } from 'ngx-toastr';


@NgModule({
  declarations: [
    AppComponent,
    RootLayoutComponent,
    NavBarComponent
  ],
  imports: [
    CommonModule,
    ToastrModule.forRoot(),
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    AppRoutingModule,
    AuthModule,
    SocialLoginModule,
    GoogleSigninButtonModule,
  ],
  providers: [{
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(enviroments.googleClientId)
          },
          {
            id: MicrosoftLoginProvider.PROVIDER_ID,
            provider: new MicrosoftLoginProvider(enviroments.microsoftClientId,{
                scopes: ['user.read', 'profile', 'email']
              })
          },
        ],
        onError: (err) => {
          console.log(err);
        }
      } as SocialAuthServiceConfig,
    },
    provideAnimations(),
    provideToastr({
      timeOut: 4000,
      positionClass: 'toast-bottom-left',
      preventDuplicates: true,
      progressBar: true
    })
  ],
  exports: [
    GoogleSigninButtonModule, // Export the component
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
