import { Injectable } from '@angular/core';
import { HousingLocationInfo, User } from './housinglocation';
@Injectable({
  providedIn: 'root',
})
export class UsersService {
  // user are in local storage, field name users
  getAllUsers(): User[] {
    const usersJson = localStorage.getItem('users');
    if (usersJson) {
      return JSON.parse(usersJson) as User[];
    }
    return [];
  }
  addUser(user: User): void {
    const users = this.getAllUsers();
    users.push(user);
    localStorage.setItem('users', JSON.stringify(users));
  }
  submitApplication(firstName: string, lastName: string, email: string, id: number = 0): void {
    const newUser: User = {
      firstName,
      lastName,
      email,
      id: id
    };
    this.addUser(newUser);
  }
  getUsersByHouseId(houseId: string): User[] | undefined {
    const users = this.getAllUsers();
    return users.filter(user => user.id.toString() === houseId);
  }
}
