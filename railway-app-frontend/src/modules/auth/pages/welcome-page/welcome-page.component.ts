import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.scss']
})
export class WelcomePageComponent implements OnInit {

  showLogin: boolean;

  constructor(private route: ActivatedRoute) {
    this.showLogin = true;
  }

  ngOnInit() {
    this.route.url.subscribe(segments => {
      const url = segments.join('/');
      if (url.includes('login')) {
        this.showLogin = true;        
      } else if (url.includes('register')) {
        this.showLogin = false;
      }
    });
  }

}
