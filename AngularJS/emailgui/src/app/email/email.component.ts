import { Component } from '@angular/core';
import { AppComponent } from '../app.component'; 
import { AppModule } from '../app.module'; 
import { AppRoutingModule } from '../app-routing.module'; 
import { RouterOutlet } from '@angular/router';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-email',
  imports:[AppComponent, AppModule, AppRoutingModule, RouterModule, RouterOutlet],
  templateUrl: './email.component.html',
  styleUrl: './email.component.css'
})
export class EmailComponent {

}
