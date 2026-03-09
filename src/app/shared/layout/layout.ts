import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import {
  LucideAngularModule,
  LayoutDashboard, Users, BookOpen, LogOut, Menu,
  GraduationCap, Calendar, BarChart3, Building2, FileText,
  Settings, QrCode, ClipboardList, MessageSquare, Link,
  UserCheck, BookMarked, ChevronDown, ChevronRight, Bell,
  ShieldCheck
} from 'lucide-angular';

interface NavItem {
  label: string; icon: any; route?: string;
  roles: string[]; children?: NavItem[]; expanded?: boolean;
}

@Component({
  selector: 'app-layout', standalone: true,
  imports: [CommonModule, RouterModule, MatSidenavModule, MatToolbarModule, MatButtonModule, LucideAngularModule],
  templateUrl: './layout.html', styleUrl: './layout.scss'
})
export class Layout implements OnInit {
  readonly LogOutIcon = LogOut; readonly MenuIcon = Menu;
  readonly BellIcon = Bell; readonly ChevDownIcon = ChevronDown;
  readonly ChevRightIcon = ChevronRight;

  userRole = 'student';
  userName = 'Usuario';
  sidenavOpen = signal(true);
  currentRoute = '';

  navItems: NavItem[] = [
    { label: 'Inicio', icon: LayoutDashboard, route: '/dashboard', roles: ['student', 'teacher', 'coordinator', 'admin', 'guardian'] },
    {
      label: 'Usuarios', icon: Users, roles: ['admin'], expanded: false,
      children: [
        { label: 'Gestión Usuarios', icon: UserCheck, route: '/admin-users', roles: ['admin'] },
        { label: 'Estudiantes', icon: GraduationCap, route: '/students', roles: ['admin', 'coordinator'] },
        { label: 'Docentes', icon: BookOpen, route: '/teachers', roles: ['admin'] },
      ]
    },
    {
      label: 'Asistencia', icon: Calendar, roles: ['student', 'teacher', 'coordinator', 'admin', 'guardian'], expanded: false,
      children: [
        { label: 'Panel Asistencia', icon: BarChart3, route: '/attendance-dashboard', roles: ['teacher', 'coordinator', 'admin'] },
        { label: 'Registrar', icon: ClipboardList, route: '/record-attendance', roles: ['teacher', 'admin'] },
        { label: 'Mi Asistencia', icon: UserCheck, route: '/my-attendance', roles: ['student'] },
        { label: 'Por Estudiante', icon: Users, route: '/student-attendance', roles: ['teacher', 'coordinator', 'admin', 'guardian'] },
        { label: 'Control de Entrada', icon: QrCode, route: '/entrance', roles: ['teacher', 'admin'] },
      ]
    },
    {
      label: 'Calificaciones', icon: BookMarked, roles: ['student', 'teacher', 'coordinator', 'admin', 'guardian'], expanded: false,
      children: [
        { label: 'Registrar Notas', icon: FileText, route: '/grades', roles: ['teacher', 'admin'] },
        { label: 'Mis Notas', icon: BookMarked, route: '/my-grades', roles: ['student'] },
        { label: 'Notas por Estudiante', icon: BarChart3, route: '/student-grades', roles: ['teacher', 'coordinator', 'admin', 'guardian'] },
      ]
    },
    {
      label: 'Académico', icon: Building2, roles: ['admin', 'coordinator'], expanded: false,
      children: [
        { label: 'Clases y Secciones', icon: BookOpen, route: '/classes', roles: ['admin', 'coordinator'] },
        { label: 'Institución', icon: Building2, route: '/institutions', roles: ['admin'] },
        { label: 'Reportes', icon: FileText, route: '/reports', roles: ['admin', 'coordinator'] },
      ]
    },
    { label: 'Asistente IA', icon: MessageSquare, route: '/chat', roles: ['student', 'teacher', 'coordinator', 'admin'] },
    { label: 'Blockchain', icon: ShieldCheck, route: '/blockchain', roles: ['admin', 'coordinator'] },
    { label: 'Configuración', icon: Settings, route: '/settings', roles: ['student', 'teacher', 'coordinator', 'admin'] },
  ];

  get currentPageTitle(): string {
    const all: NavItem[] = [];
    this.navItems.forEach(i => { all.push(i); (i.children || []).forEach(c => all.push(c)); });
    return all.find(i => i.route && this.currentRoute.startsWith(i.route))?.label ?? 'Panel';
  }

  constructor(private router: Router) { }

  ngOnInit() {
    this.userRole = localStorage.getItem('role') || 'student';
    this.userName = localStorage.getItem('userName') || this.getRoleLabel(this.userRole);
    this.router.events.pipe(filter(e => e instanceof NavigationEnd)).subscribe((e: any) => this.currentRoute = e.url);
    this.currentRoute = this.router.url;
  }

  getVisibleItems() { return this.navItems.filter(i => i.roles.includes(this.userRole)); }
  hasVisibleChildren(i: NavItem) { return (i.children || []).some(c => c.roles.includes(this.userRole)); }
  getVisibleChildren(i: NavItem) { return (i.children || []).filter(c => c.roles.includes(this.userRole)); }
  isGroupActive(i: NavItem) { return (i.children || []).some(c => c.route && this.currentRoute.startsWith(c.route)); }
  isActive(route: string) { return this.currentRoute === route || this.currentRoute.startsWith(route + '/'); }
  toggleExpand(i: NavItem) { i.expanded = !i.expanded; }
  toggleSidenav() { this.sidenavOpen.set(!this.sidenavOpen()); }

  navigate(route: string) { this.router.navigate([route]); }

  logout() {
    ['token', 'role', 'userId', 'userName'].forEach(k => localStorage.removeItem(k));
    this.router.navigate(['/login']);
  }

  getRoleLabel(role: string) {
    const labels: Record<string, string> = {
      admin: 'Admin', coordinator: 'Coord.', teacher: 'Docente', student: 'Alumno', guardian: 'Apoderado'
    };
    return labels[role] ?? role;
  }
}
