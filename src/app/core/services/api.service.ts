import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
  Student, RegisterStudentCommand, UpdateStudentCommand,
  Teacher, CreateTeacherCommand, UpdateTeacherCommand,
  User, AttendanceRecord, AttendanceSummary,
  RecordQrAttendanceCommand, RecordManualAttendanceCommand,
  SchoolYear, CreateSchoolYearCommand,
  Section, CreateSectionCommand,
  AcademicLevel, CreateAcademicLevelCommand,
  AcademicPeriod, CreateAcademicPeriodCommand,
  Course, CreateCourseCommand,
  CourseAssignment, RegisterCourseAssignmentCommand,
  DidacticUnit, CreateDidacticUnitCommand,
  StudentScore, RegisterScoreCommand,
  Guardian, Justification, RequestJustificationCommand,
  Notification, BlockRecord
} from '../models/models';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private http = inject(HttpClient);
  private readonly BASE = environment.apiUrl;
  private readonly json = new HttpHeaders({ 'Content-Type': 'application/json' });

  // ═══ STUDENTS ═══════════════════════════════════════════════════════════════
  getStudents(): Observable<Student[]> { return this.http.get<Student[]>(`${this.BASE}/students`); }
  getStudentById(id: string): Observable<Student> { return this.http.get<Student>(`${this.BASE}/students/${id}`); }
  getStudentByDocument(doc: string): Observable<Student> { return this.http.get<Student>(`${this.BASE}/students/document/${doc}`); }
  getStudentsBySection(sectionId: string): Observable<Student[]> { return this.http.get<Student[]>(`${this.BASE}/students/section/${sectionId}`); }
  registerStudent(cmd: RegisterStudentCommand): Observable<Student> { return this.http.post<Student>(`${this.BASE}/students`, cmd, { headers: this.json }); }
  updateStudent(id: string, cmd: UpdateStudentCommand): Observable<Student> { return this.http.put<Student>(`${this.BASE}/students/${id}`, cmd, { headers: this.json }); }
  deleteStudent(id: string): Observable<void> { return this.http.delete<void>(`${this.BASE}/students/${id}`); }

  // ═══ TEACHERS ═══════════════════════════════════════════════════════════════
  getTeachers(): Observable<Teacher[]> { return this.http.get<Teacher[]>(`${this.BASE}/teachers`); }
  getTeacherById(id: string): Observable<Teacher> { return this.http.get<Teacher>(`${this.BASE}/teachers/${id}`); }
  createTeacher(cmd: CreateTeacherCommand): Observable<Teacher> { return this.http.post<Teacher>(`${this.BASE}/teachers`, cmd, { headers: this.json }); }
  updateTeacher(id: string, cmd: UpdateTeacherCommand): Observable<Teacher> { return this.http.put<Teacher>(`${this.BASE}/teachers/${id}`, cmd, { headers: this.json }); }
  deactivateTeacher(id: string): Observable<Teacher> { return this.http.patch<Teacher>(`${this.BASE}/teachers/deactivate/${id}`, {}); }
  reactivateTeacher(id: string): Observable<Teacher> { return this.http.patch<Teacher>(`${this.BASE}/teachers/reactivate/${id}`, {}); }
  deleteTeacher(id: string): Observable<void> { return this.http.delete<void>(`${this.BASE}/teachers/${id}`); }

  // ═══ USERS ══════════════════════════════════════════════════════════════════
  getUsers(): Observable<User[]> { return this.http.get<User[]>(`${this.BASE}/users`); }
  getUserById(id: string): Observable<User> { return this.http.get<User>(`${this.BASE}/users/${id}`); }

  // ═══ ATTENDANCES ════════════════════════════════════════════════════════════
  recordQrAttendance(cmd: RecordQrAttendanceCommand): Observable<AttendanceRecord> {
    return this.http.post<AttendanceRecord>(`${this.BASE}/attendances/qr`, cmd, { headers: this.json });
  }
  recordManualAttendance(cmd: RecordManualAttendanceCommand): Observable<AttendanceRecord[]> {
    return this.http.post<AttendanceRecord[]>(`${this.BASE}/attendances/manual`, cmd, { headers: this.json });
  }
  getAttendanceByDate(date: string): Observable<AttendanceRecord[]> {
    return this.http.get<AttendanceRecord[]>(`${this.BASE}/attendances?date=${date}`);
  }
  getAttendanceByStudent(studentId: string): Observable<AttendanceRecord[]> {
    return this.http.get<AttendanceRecord[]>(`${this.BASE}/attendances/student/${studentId}`);
  }
  getAttendanceByCourseAssignment(assignmentId: string): Observable<AttendanceRecord[]> {
    return this.http.get<AttendanceRecord[]>(`${this.BASE}/attendances/course-assignment/${assignmentId}`);
  }
  getAttendanceSummary(sectionId: string, date: string): Observable<AttendanceSummary> {
    return this.http.get<AttendanceSummary>(`${this.BASE}/attendances/summary?sectionId=${sectionId}&date=${date}`);
  }

  // ═══ SCHOOL YEARS (was Grades) ══════════════════════════════════════════════
  getSchoolYears(): Observable<SchoolYear[]> { return this.http.get<SchoolYear[]>(`${this.BASE}/school-years`); }
  getGrades(): Observable<SchoolYear[]> { return this.getSchoolYears(); } // alias for legacy components
  createSchoolYear(cmd: CreateSchoolYearCommand): Observable<SchoolYear> { return this.http.post<SchoolYear>(`${this.BASE}/school-years`, cmd, { headers: this.json }); }
  updateSchoolYear(id: string, cmd: CreateSchoolYearCommand): Observable<SchoolYear> { return this.http.put<SchoolYear>(`${this.BASE}/school-years/${id}`, cmd, { headers: this.json }); }
  deleteSchoolYear(id: string): Observable<void> { return this.http.delete<void>(`${this.BASE}/school-years/${id}`); }

  // ═══ SECTIONS ═══════════════════════════════════════════════════════════════
  getSections(): Observable<Section[]> { return this.http.get<Section[]>(`${this.BASE}/sections`); }
  createSection(cmd: CreateSectionCommand): Observable<Section> { return this.http.post<Section>(`${this.BASE}/sections`, cmd, { headers: this.json }); }
  updateSection(id: string, cmd: CreateSectionCommand): Observable<Section> { return this.http.put<Section>(`${this.BASE}/sections/${id}`, cmd, { headers: this.json }); }
  deleteSection(id: string): Observable<void> { return this.http.delete<void>(`${this.BASE}/sections/${id}`); }

  // ═══ ACADEMIC LEVELS ════════════════════════════════════════════════════════
  getAcademicLevels(): Observable<AcademicLevel[]> { return this.http.get<AcademicLevel[]>(`${this.BASE}/academic-levels`); }
  createAcademicLevel(cmd: CreateAcademicLevelCommand): Observable<AcademicLevel> { return this.http.post<AcademicLevel>(`${this.BASE}/academic-levels`, cmd, { headers: this.json }); }

  // ═══ ACADEMIC PERIODS ═══════════════════════════════════════════════════════
  getAcademicPeriods(): Observable<AcademicPeriod[]> { return this.http.get<AcademicPeriod[]>(`${this.BASE}/academic-periods`); }
  getActivePeriod(): Observable<AcademicPeriod> { return this.http.get<AcademicPeriod>(`${this.BASE}/academic-periods/active`); }
  createAcademicPeriod(cmd: CreateAcademicPeriodCommand): Observable<AcademicPeriod> { return this.http.post<AcademicPeriod>(`${this.BASE}/academic-periods`, cmd, { headers: this.json }); }
  deactivatePeriod(id: string): Observable<AcademicPeriod> { return this.http.patch<AcademicPeriod>(`${this.BASE}/academic-periods/deactivate/${id}`, {}); }
  reactivatePeriod(id: string): Observable<AcademicPeriod> { return this.http.patch<AcademicPeriod>(`${this.BASE}/academic-periods/reactivate/${id}`, {}); }
  deletePeriod(id: string): Observable<void> { return this.http.delete<void>(`${this.BASE}/academic-periods/${id}`); }

  // ═══ COURSES ════════════════════════════════════════════════════════════════
  getCourses(): Observable<{ total: number; courses: Course[] }> { return this.http.get<{ total: number; courses: Course[] }>(`${this.BASE}/courses`); }
  getCourseById(id: string): Observable<Course> { return this.http.get<Course>(`${this.BASE}/courses/${id}`); }
  createCourse(cmd: CreateCourseCommand): Observable<Course> { return this.http.post<Course>(`${this.BASE}/courses`, cmd, { headers: this.json }); }

  // ═══ COURSE ASSIGNMENTS ══════════════════════════════════════════════════════
  getCourseAssignments(): Observable<CourseAssignment[]> { return this.http.get<CourseAssignment[]>(`${this.BASE}/course-assignments`); }
  getCourseAssignmentsByTeacher(teacherId: string): Observable<CourseAssignment[]> { return this.http.get<CourseAssignment[]>(`${this.BASE}/course-assignments/teacher/${teacherId}`); }
  getCourseAssignmentsBySection(sectionId: string): Observable<CourseAssignment[]> { return this.http.get<CourseAssignment[]>(`${this.BASE}/course-assignments/section/${sectionId}`); }
  getCourseAssignmentsByPeriod(periodId: string): Observable<CourseAssignment[]> { return this.http.get<CourseAssignment[]>(`${this.BASE}/course-assignments/period/${periodId}`); }
  registerCourseAssignment(cmd: RegisterCourseAssignmentCommand): Observable<CourseAssignment> { return this.http.post<CourseAssignment>(`${this.BASE}/course-assignments`, cmd, { headers: this.json }); }
  deleteCourseAssignment(id: string): Observable<void> { return this.http.delete<void>(`${this.BASE}/course-assignments/${id}`); }

  // ═══ DIDACTIC UNITS ══════════════════════════════════════════════════════════
  getDidacticUnits(): Observable<DidacticUnit[]> { return this.http.get<DidacticUnit[]>(`${this.BASE}/didactic-units`); }
  getDidacticUnitsByAssignment(assignmentId: string): Observable<DidacticUnit[]> { return this.http.get<DidacticUnit[]>(`${this.BASE}/didactic-units/by-assignment/${assignmentId}`); }
  createDidacticUnit(cmd: CreateDidacticUnitCommand): Observable<DidacticUnit> { return this.http.post<DidacticUnit>(`${this.BASE}/didactic-units`, cmd, { headers: this.json }); }
  closeDidacticUnit(id: string): Observable<DidacticUnit> { return this.http.patch<DidacticUnit>(`${this.BASE}/didactic-units/${id}/close`, {}); }
  deleteDidacticUnit(id: string): Observable<void> { return this.http.delete<void>(`${this.BASE}/didactic-units/${id}`); }

  // ═══ STUDENT SCORES ══════════════════════════════════════════════════════════
  getScoresByStudent(studentId: string): Observable<StudentScore[]> { return this.http.get<StudentScore[]>(`${this.BASE}/scores/student/${studentId}`); }
  getScoresByCourseAssignment(assignmentId: string): Observable<StudentScore[]> { return this.http.get<StudentScore[]>(`${this.BASE}/scores/assignment/${assignmentId}`); }
  registerScore(cmd: RegisterScoreCommand): Observable<StudentScore> { return this.http.post<StudentScore>(`${this.BASE}/scores`, cmd, { headers: this.json }); }
  updateScore(id: string, cmd: Partial<RegisterScoreCommand>): Observable<StudentScore> { return this.http.put<StudentScore>(`${this.BASE}/scores/${id}`, cmd, { headers: this.json }); }
  deleteScore(id: string): Observable<void> { return this.http.delete<void>(`${this.BASE}/scores/${id}`); }
  getScoreAverage(studentId: string, assignmentId: string): Observable<number> { return this.http.get<number>(`${this.BASE}/scores/average?studentId=${studentId}&assignmentId=${assignmentId}`); }

  // ═══ GUARDIANS ══════════════════════════════════════════════════════════════
  getGuardians(): Observable<Guardian[]> { return this.http.get<Guardian[]>(`${this.BASE}/guardians`); }
  getGuardiansByStudent(studentId: string): Observable<Guardian[]> { return this.http.get<Guardian[]>(`${this.BASE}/guardians/student/${studentId}`); }

  // ═══ JUSTIFICATIONS ══════════════════════════════════════════════════════════
  requestJustification(cmd: RequestJustificationCommand): Observable<Justification> { return this.http.post<Justification>(`${this.BASE}/justifications`, cmd, { headers: this.json }); }
  approveJustification(id: string, reviewedBy: string): Observable<Justification> { return this.http.patch<Justification>(`${this.BASE}/justifications/${id}/approve?reviewedBy=${reviewedBy}`, {}); }
  rejectJustification(id: string, reviewedBy: string, note: string): Observable<Justification> { return this.http.patch<Justification>(`${this.BASE}/justifications/${id}/reject?reviewedBy=${reviewedBy}&note=${note}`, {}); }
  getJustificationsByStudent(studentId: string): Observable<Justification[]> { return this.http.get<Justification[]>(`${this.BASE}/justifications/student/${studentId}`); }
  getPendingJustifications(): Observable<Justification[]> { return this.http.get<Justification[]>(`${this.BASE}/justifications/status/PENDING`); }

  // ═══ NOTIFICATIONS ══════════════════════════════════════════════════════════
  getNotifications(userId: string): Observable<Notification[]> { return this.http.get<Notification[]>(`${this.BASE}/notifications?recipientId=${userId}`); }
  getUnreadNotifications(userId: string): Observable<Notification[]> { return this.http.get<Notification[]>(`${this.BASE}/notifications/unread?recipientId=${userId}`); }
  markNotificationAsRead(id: string): Observable<void> { return this.http.patch<void>(`${this.BASE}/notifications/${id}/read`, {}); }
  markAllAsRead(userId: string): Observable<void> { return this.http.patch<void>(`${this.BASE}/notifications/read-all?recipientId=${userId}`, {}); }

  // ═══ BLOCKCHAIN ══════════════════════════════════════════════════════════════
  verifyBlockchain(): Observable<boolean> { return this.http.get<boolean>(`${this.BASE}/blockchain/verify`); }
  getBlockchainByStudent(studentId: string): Observable<BlockRecord[]> { return this.http.get<BlockRecord[]>(`${this.BASE}/blockchain/student/${studentId}`); }
  getLatestBlock(): Observable<BlockRecord> { return this.http.get<BlockRecord>(`${this.BASE}/blockchain/latest`); }
}
