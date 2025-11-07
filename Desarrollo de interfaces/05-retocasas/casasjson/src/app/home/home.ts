import { Component, inject } from '@angular/core';
import { HousingLocationInfo } from '../housinglocation';
import { HousingService } from '../housing.service';
import { HousingLocation } from "../housing-location/housing-location";
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  imports: [HousingLocation, CommonModule,FormsModule],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {
  housingLocationList: HousingLocationInfo[] = [];
  housingService: HousingService = inject(HousingService);
  filteredLocationList: HousingLocationInfo[] = [];
  searchText: string = '';

  constructor() {
    this.housingService.getAllHousingLocations().subscribe((housingLocationList: HousingLocationInfo[]) => {
      this.housingLocationList = housingLocationList;
      this.filteredLocationList = this.housingLocationList;
    });
  } 
  public buscar() {
    const search = this.searchText.toLowerCase().trim();
    if (!search) {
      this.filteredLocationList = this.housingLocationList;
      return;
    }
    this.filteredLocationList = this.housingLocationList.filter(item =>
      item.name.toLowerCase().includes(search) ||
      item.city.toLowerCase().includes(search) ||
      item.state.toLowerCase().includes(search)
    );
  }
}
