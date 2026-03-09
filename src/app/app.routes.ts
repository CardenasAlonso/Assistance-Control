import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', loadComponent: () => import('./features/auth/login/login').then(m => m.Login) },
  {
    path: '',
    loadComponent: () => import('./shared/layout/layout').then(m => m.Layout),
    //canActivate: [authGuard],
    children: [
      { path: 'dashboard', loadComponent: () => import('./features/dashboard/dashboard').then(m => m.Dashboard) },
      { path: 'admin-users', loadComponent: () => import('./features/users/admin-users/admin-users').then(m => m.AdminUsers) },
      { path: 'students', loadComponent: () => import('./features/users/students/students').then(m => m.Students) },
      { path: 'teachers', loadComponent: () => import('./features/users/teachers/teachers').then(m => m.Teachers) },
      { path: 'attendance-dashboard', loadComponent: () => import('./features/attendance/attendance-dashboard/attendance-dashboard').then(m => m.AttendanceDashboard) },
      { path: 'record-attendance', loadComponent: () => import('./features/attendance/record-attendance/record-attendance').then(m => m.RecordAttendance) },
      { path: 'my-attendance', loadComponent: () => import('./features/attendance/my-attendance/my-attendance').then(m => m.MyAttendance) },
      { path: 'student-attendance', loadComponent: () => import('./features/attendance/student-attendance/student-attendance').then(m => m.StudentAttendance) },
      { path: 'grades', loadComponent: () => import('./features/grades/grade-registry/grade-registry').then(m => m.GradeRegistry) },
      { path: 'my-grades', loadComponent: () => import('./features/grades/my-grades/my-grades').then(m => m.MyGrades) },
      { path: 'student-grades', loadComponent: () => import('./features/grades/student-grades/student-grades').then(m => m.StudentGrades) },
      { path: 'classes', loadComponent: () => import('./features/academics/classes/classes').then(m => m.Classes) },
      { path: 'institutions', loadComponent: () => import('./features/academics/institutions/institutions').then(m => m.Institutions) },
      { path: 'reports', loadComponent: () => import('./features/academics/reports/reports').then(m => m.Reports) },
      { path: 'entrance', loadComponent: () => import('./features/misc/entrance/entrance').then(m => m.Entrance) },
      { path: 'settings', loadComponent: () => import('./features/misc/settings/settings').then(m => m.Settings) },
      { path: 'chat', loadComponent: () => import('./features/misc/chat/chat').then(m => m.Chat) },
      { path: 'blockchain', loadComponent: () => import('./features/misc/blockchain/blockchain').then(m => m.Blockchain) },
    ]
  },
  { path: '**', redirectTo: 'login' }
];
