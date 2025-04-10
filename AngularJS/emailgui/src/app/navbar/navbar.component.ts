import { Component } from '@angular/core';
import { AppComponent } from '../app.component'; 
import { AppModule } from '../app.module'; 
import { AppRoutingModule } from '../app-routing.module'; 
import { RouterOutlet } from '@angular/router';
import { RouterModule } from '@angular/router';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'app-navbar',
  imports:[MatIcon, AppComponent, AppModule, AppRoutingModule, RouterModule, RouterOutlet],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent { }
