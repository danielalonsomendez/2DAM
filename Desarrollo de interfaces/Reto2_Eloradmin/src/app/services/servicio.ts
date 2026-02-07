import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../interfaces/user';
import { Reuniones } from '../interfaces/reuniones';
import { Centros } from '../interfaces/centros';
import { Horarios } from '../interfaces/horarios';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class Servicio {
  private url = environment.apiUrl;

  /**
   * Servicio HTTP para comunicarse con la API del backend.
   * @param http Cliente HTTP inyectado para realizar peticiones.
   */
  constructor(private http: HttpClient) {}

// usuarios
  /**
   * Recupera la lista completa de usuarios desde la API.
   * @returns Observable con un array de `User`.
   */
  getUsuarios(): Observable<User[]> {
    return this.http.get<User[]>(`${this.url}/usuarios/`);
  }
  
  /**
   * Recupera un usuario por su ID.
   * @param id Identificador del usuario.
   * @returns Observable con el `User` solicitado.
   */
  getUsuarioPorID(id: number): Observable<User> {
    return this.http.get<User>(`${this.url}/usuarios/${id}`);
  }

  // login
  /**
   * Intenta iniciar sesión con credenciales proporcionadas.
   * @param username Nombre de usuario.
   * @param contrasena Contraseña.
   * @returns Observable con el `User` autenticado.
   */
  iniciarSesion(username: string, contrasena: string): Observable<User> {
    return this.http.post<User>(`${this.url}/login?username=${username}&contrasena=${contrasena}`, {});
  }

  // crear usuario
  /**
   * Crea un nuevo usuario en el backend.
   * @param user Objeto `User` con los datos del usuario.
   * @returns Observable con la respuesta (texto).
   */
  crearUsuario(user: User): Observable<any> {
  return this.http.post(`${this.url}/usuarios/`, user, { responseType: 'text' });
} 


  /**
   * Actualiza un usuario existente.
   * @param id ID del usuario a actualizar.
   * @param user Datos del usuario.
   * @returns Observable con el `User` actualizado.
   */
  actualizarUsuario(id: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.url}/usuarios/${id}`, user);
  }

  /**
   * Verifica si un nombre de usuario está ya en uso (opcionalmente excluye un ID).
   * @param username Nombre de usuario a verificar.
   * @param id ID a excluir de la comprobación (opcional).
   * @returns Observable<boolean> indicando si existe el username.
   */
  verificarUsername(username: string, id?: number): Observable<boolean> {
    const sanitized = encodeURIComponent(username.trim());
    const endpoint = typeof id === 'number'
      ? `${this.url}/usuarios/${id}/${sanitized}/exists`
      : `${this.url}/usuarios/${sanitized}/exists`;
    return this.http.get<boolean>(endpoint);
  }

  /**
   * Cambia la contraseña de un usuario.
   * @param id ID del usuario.
   * @param nuevaContrasena Nueva contraseña.
   * @returns Observable con respuesta en texto.
   */
  cambiarContrasena(id: number, nuevaContrasena: string): Observable<string> {
    return this.http.put(`${this.url}/usuarios/${id}/contrasena?nuevaContrasena=${nuevaContrasena}`, null, {
      responseType: 'text',
    });
  }

  // borrar usuario
  /**
   * Elimina un usuario por ID.
   * @param id ID del usuario a eliminar.
   * @returns Observable con la respuesta del servidor.
   */
  borrarUsuario(id: number): Observable<any> {
    return this.http.delete<any>(`${this.url}/usuarios/${id}`);
  }

  // centros
  /**
   * Recupera todos los centros.
   * @returns Observable con un array de `Centros`.
   */
  getAllCentros(): Observable<Centros[]> {
    return this.http.get<Centros[]>(`${this.url}/centros`);
  }

  /**
   * Recupera un centro por su ID.
   * @param id ID del centro.
   * @returns Observable con el `Centros` solicitado.
   */
  getAllCentrosById(id: number): Observable<Centros> {
    return this.http.get<Centros>(`${this.url}/centros/${id}`);
  }

  // reuniones
  /**
   * Recupera todas las reuniones.
   * @returns Observable con un array de `Reuniones`.
   */
  getReuniones(): Observable<Reuniones[]> {
    return this.http.get<Reuniones[]>(`${this.url}/reuniones/`);
  }

  /**
   * Recupera reuniones para un usuario concreto.
   * @param id ID del usuario.
   * @returns Observable con un array de `Reuniones`.
   */
  getReunionesByUserID(id: number): Observable<Reuniones[]> {
    return this.http.get<Reuniones[]>(`${this.url}/reuniones/${id}`);
  }

  /**
   * Crea una nueva reunión.
   * @param reunion Datos de la reunión a crear.
   * @returns Observable con la reunión creada.
   */
  crearReunion(reunion: Reuniones): Observable<Reuniones> {
    return this.http.post<Reuniones>(`${this.url}/reuniones/`, reunion);
  }
  
  
  // horarios
  /**
   * Recupera los horarios asociados a un usuario por ID.
   * @param id ID del usuario.
   * @returns Observable con un array de `Horarios`.
   */
  getHorariosByUserID(id: number): Observable<Horarios[]> {
    return this.http.get<Horarios[]>(`${this.url}/horarios/${id}`);
  }
}
