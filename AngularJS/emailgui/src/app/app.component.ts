import { Component } from '@angular/core';
import { NavbarComponent } from './navbar/navbar.component'; 
import { RouterOutlet } from '@angular/router';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [NavbarComponent, RouterOutlet, RouterModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'emailgui';
}
