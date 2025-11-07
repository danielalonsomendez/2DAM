export interface HousingLocationInfo {
  id: number;
  name: string;
  city: string;
  state: string;
  photo: string;
  availableUnits: number;
  wifi: boolean;
  laundry: boolean;
}
export interface User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
}
