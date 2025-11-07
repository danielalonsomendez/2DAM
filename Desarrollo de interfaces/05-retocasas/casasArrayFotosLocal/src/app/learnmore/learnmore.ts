import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HousingService } from '../housing.service';
import { HousingLocationInfo, User } from '../housinglocation';
import { UsersService } from '../users.service';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-learnmore',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './learnmore.html',
  styleUrl: './learnmore.css'
})
export class Learnmore implements OnInit {
  housingService: HousingService = inject(HousingService);
  route: ActivatedRoute = inject(ActivatedRoute);
  house: HousingLocationInfo | undefined;
  usersService: UsersService = inject(UsersService);
  newUser: User = {
    firstName: '',
    lastName: '',
    email: '',
    id: 0
  }
  applyForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl(''),
  });
  constructor() {}

  ngOnInit() {
    const housingLocationId = parseInt(this.route.snapshot.params['id'], 10);
    this.house = this.housingService.getHousingLocationById(housingLocationId);
  }
  submitApplication() {
    console.log('Form submitted:', this.applyForm.value);
    this.usersService.submitApplication(
      this.applyForm.value.firstName ?? '',
      this.applyForm.value.lastName ?? '',
      this.applyForm.value.email ?? '',
      parseInt(this.house?.id ?? '0', 10)
    );
  }

  getImageUrl(): string | undefined {
    return this.house ? `casas/${this.house.photo}` : undefined;
  }

  async downloadImage(): Promise<void> {
    const url = this.getImageUrl();
    if (!url) return;
    try {
      const res = await fetch(url, { mode: 'cors' });
      const blob = await res.blob();
      const objectUrl = URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = objectUrl;
      const fileName = this.house?.photo || `house-${this.house?.id}.jpg`;
      a.download = fileName;
      document.body.appendChild(a);
      a.click();
      a.remove();
      URL.revokeObjectURL(objectUrl);
    } catch (e) {
      console.error('Fallo', e);
    }
  }
}
