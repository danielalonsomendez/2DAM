import { CommonModule, DOCUMENT } from '@angular/common';
import { Component, OnDestroy, inject, ViewChild } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AvatarModule } from 'primeng/avatar';
import { MenuItem, MenuItemCommandEvent } from 'primeng/api';
import { Menu, MenuModule } from 'primeng/menu';
import { UserService } from '../../services/user';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { Subscription } from 'rxjs';

type ThemeMode = 'light' | 'dark';

interface ThemeOption {
  value: ThemeMode;
  icon: string;
  labelKey: string;
}

@Component({
  selector: 'app-header',
  imports: [CommonModule, AvatarModule, MenuModule, RouterLink, RouterLinkActive, TranslateModule],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header implements OnDestroy {
  @ViewChild('userMenu') private userMenu?: Menu;
  userService: UserService = inject(UserService);
  private readonly translate = inject(TranslateService);
  private readonly document = inject(DOCUMENT);
  mobileMenuOpen = false;
  private readonly languages = ['es', 'eu', 'en', 'ar'];
  private readonly themeOptions: ThemeOption[] = [
    { value: 'light', icon: 'pi pi-sun', labelKey: 'header.themeLight' },
    { value: 'dark', icon: 'pi pi-moon', labelKey: 'header.themeDark' }
  ];
  private readonly themeStorageKey = 'elor-theme';
  protected menuItems: MenuItem[] = [];
  protected currentTheme: ThemeMode = 'light';
  private langSub?: Subscription;

  /**
   * Inicializa el header, aplicando el tema guardado y construyendo los items del menú.
   */
  constructor() {
    this.currentTheme = this.resolveInitialTheme();
    this.applyTheme(this.currentTheme);
    this.buildMenuItems();
    this.langSub = this.translate.onLangChange.subscribe(() => this.buildMenuItems());
  }

  /**
   * Limpia suscripciones al destruir el componente.
   */
  ngOnDestroy(): void {
    this.langSub?.unsubscribe();
  }

  /**
   * Abre/cierra el menú de usuario.
   */
  protected toggleUserMenu(event: Event): void {
    this.userMenu?.toggle(event);
  }

  /**
   * Alterna el menú móvil.
   */
  protected toggleMobileMenu(): void {
    this.mobileMenuOpen = !this.mobileMenuOpen;
  }

  /**
   * Cierra el menú móvil.
   */
  protected closeMobileMenu(): void {
    this.mobileMenuOpen = false;
  }

  /**
   * Cierra la sesión del usuario y oculta el menú.
   */
  protected onLogout(): void {
    this.userMenu?.hide();
    this.userService.logout();
  }

  /**
   * Devuelve el código del idioma actualmente activo.
   */
  protected get currentLanguage(): string {
    return this.translate.currentLang || this.translate.defaultLang || 'es';
  }

  /**
   * Cambia el idioma de la aplicación y lo persiste en localStorage.
   */
  protected changeLanguage(lang: string): void {
    this.translate.use(lang);
    localStorage.setItem('elor-lang', lang);
    this.userMenu?.hide();
    this.buildMenuItems();
  }

  /**
   * Cambia el tema y actualiza el menú.
   */
  protected changeTheme(theme: ThemeMode): void {
    this.setTheme(theme);
    this.userMenu?.hide();
    this.buildMenuItems();
  }

  /**
   * Devuelve la clave de traducción para un roleId dado.
   */
  protected roleKey(roleId?: number | null): string {
    switch (roleId) {
      case 1:
        return 'roles.god';
      case 2:
        return 'roles.admin';
      case 3:
        return 'roles.teacher';
      case 4:
        return 'roles.student';
      default:
        return 'roles.unknown';
    }
  }

  /**
   * Indica si el usuario puede ver la sección de usuarios.
   */
  get canViewUsuarios(): boolean {
    const roleId = this.userService.getUser()?.tipos?.id;
    return roleId === 1 || roleId === 2 || roleId === 3;
  }

  /**
   * Clave de traducción para la etiqueta de usuarios según el rol.
   */
  get usuariosLabelKey(): string {
    return this.userService.getUser()?.tipos?.id === 3
      ? 'navigation.educationalCommunity'
      : 'navigation.users';
  }
  
  /**
   * Reconstruye los items del menú dinámicamente (tema, idioma, logout).
   */
  private buildMenuItems(): void {
    const themeItems = this.themeOptions.map(option => ({
      label: this.translate.instant(option.labelKey),
      icon: option.icon,
      styleClass: option.value === this.currentTheme ? 'theme-option theme-active-item' : 'theme-option',
      command: () => this.changeTheme(option.value)
    }));

    const languages = this.languages.map(code => ({
      label: this.translate.instant(`languages.${code}`),
      icon: this.currentLanguage === code ? 'pi pi-check' : undefined,
      command: () => this.changeLanguage(code)
    }));

    this.menuItems = [
      {
        label: this.translate.instant('header.theme'),
        icon: 'pi pi-palette',
        disabled: true,
        styleClass: 'menu-section-label'
      },
      ...themeItems,
      { separator: true },
      {
        label: this.translate.instant('header.language'),
        icon: 'pi pi-globe',
        disabled: true,
        styleClass: 'menu-section-label'
      },
      ...languages,
      { separator: true },
      {
        label: this.translate.instant('header.logout'),
        icon: 'pi pi-sign-out',
        command: (event?: MenuItemCommandEvent) => {
          event?.originalEvent?.preventDefault();
          this.onLogout();
        }
      }
    ];
  }

  /**
   * Establece y persiste el tema seleccionado.
   */
  private setTheme(theme: ThemeMode): void {
    if (this.currentTheme === theme) {
      return;
    }
    this.currentTheme = theme;
    this.applyTheme(theme);
    this.persistTheme(theme);
  }

  /**
   * Aplica el CSS necesario para el tema actual al DOM.
   */
  private applyTheme(theme: ThemeMode): void {
    const root = this.document?.documentElement;
    const body = this.document?.body;
    if (!root || !body) {
      return;
    }

    const themeClass = theme === 'dark' ? 'theme-dark' : 'theme-light';
    const classesToRemove = ['theme-light', 'theme-dark', 'p-dark'];

    root.classList.remove(...classesToRemove);
    body.classList.remove(...classesToRemove);

    root.classList.add(themeClass);
    body.classList.add(themeClass);

    if (theme === 'dark') {
      root.classList.add('p-dark');
      body.classList.add('p-dark');
    }
  }

  /**
   * Persiste la preferencia de tema en localStorage (si está disponible).
   */
  private persistTheme(theme: ThemeMode): void {
    if (typeof window === 'undefined') {
      return;
    }
    try {
      window.localStorage.setItem(this.themeStorageKey, theme);
    } catch (error) {
      console.warn('Unable to persist theme preference', error);
    }
  }

  /**
   * Determina el tema inicial leyendo almacenamiento o `prefers-color-scheme`.
   */
  private resolveInitialTheme(): ThemeMode {
    if (typeof window === 'undefined') {
      return 'light';
    }
    try {
      const stored = window.localStorage.getItem(this.themeStorageKey) as ThemeMode | null;
      if (stored === 'light' || stored === 'dark') {
        return stored;
      }
      if (window.matchMedia?.('(prefers-color-scheme: dark)').matches) {
        return 'dark';
      }
    } catch (error) {
      console.warn('Unable to read theme preference', error);
    }
    return 'light';
  }
}
