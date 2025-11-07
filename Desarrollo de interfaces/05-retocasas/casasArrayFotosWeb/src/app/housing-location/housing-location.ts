import { Component, Input, input, inject } from '@angular/core';
import { Router } from '@angular/router';
import { HousingLocationInfo } from '../housinglocation';

@Component({
  selector: 'app-housing-location',
  imports: [],
  templateUrl: './housing-location.html',
  styleUrl: './housing-location.css'
})
export class HousingLocation {
  @Input() housingLocation!: HousingLocationInfo;
  router: Router = inject(Router);
  
  goToLearnMore() {
    this.router.navigate(['/details', this.housingLocation.id]);
  }
}
