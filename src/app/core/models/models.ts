// ─── STUDENT ─────────────────────────────────────────────────────────────────
export interface Student {
  id: string; sectionId: string; userId: string;
  firstName: string; lastName: string; birthDate: string;
  documentNumber: string; email: string; phone: string;
  address: string; bloodType: string; status: string; enrollmentYear?: number;
}
export interface RegisterStudentCommand {
  email: string; password: string; firstName: string; lastName: string;
  documentNumber: string; sectionId: string; birthDate: string;
  phone: string; address: string; bloodType: string;
}
export interface UpdateStudentCommand {
  firstName?: string; lastName?: string; phone?: string;
  address?: string; bloodType?: string; sectionId?: string;
}

// ─── TEACHER ──────────────────────────────────────────────────────────────────
export interface Teacher {
  id: string; firstName: string; lastName: string;
  documentNumber: string; email: string; phone: string;
  specialtiesJson: string; status: string; hireDate: string;
}
export interface CreateTeacherCommand {
  email: string; password: string; firstName: string; lastName: string;
  documentNumber: string; phone: string; specialtiesJson: string; hireDate?: string;
}
export interface UpdateTeacherCommand {
  firstName?: string; lastName?: string; phone?: string; specialtiesJson?: string;
}

// ─── USER ────────────────────────────────────────────────────────────────────
export interface User {
  id: string; email: string; firstName: string; lastName: string;
  role: string; isActive: number;
}

// ─── ATTENDANCE ──────────────────────────────────────────────────────────────
export interface AttendanceRecord {
  id: string; studentId: string; courseAssignmentId: string;
  attendanceDate: string; checkInTime: string; status: string;
  recordMethod: string; justificationReason: string;
}
export interface AttendanceSummary {
  sectionId: string; sectionName: string; courseAssignmentId: string;
  courseName: string; date: string; totalPresent: number; totalAbsent: number;
  totalLate: number; totalExcused: number; attendancePercentage: number;
}
export interface RecordQrAttendanceCommand {
  studentId: string; courseAssignmentId?: string; qrToken: string;
}
export interface StudentAttendanceItem { studentId: string; status: string; justificationReason?: string; }
export interface RecordManualAttendanceCommand {
  courseAssignmentId: string; attendanceDate: string;
  attendances: StudentAttendanceItem[];
}

// ─── SCHOOL YEAR (was Grade) ──────────────────────────────────────────────────
export interface SchoolYear {
  id: string; academicLevelId: string; name: string;
  gradeNumber: number; isActive: number;
}
export interface CreateSchoolYearCommand {
  academicLevelId: string; name: string; gradeNumber: number;
}

// ─── SECTION ──────────────────────────────────────────────────────────────────
export interface Section {
  id: string; schoolYearId: string; name: string;
  capacity: number; turn: string; isActive: number;
}
export interface CreateSectionCommand {
  gradeId: string; name: string; capacity: number; turn: string;
}

// ─── ACADEMIC LEVEL ───────────────────────────────────────────────────────────
export interface AcademicLevel { id: string; name: string; description: string; isActive: number; }
export interface CreateAcademicLevelCommand { name: string; description: string; }

// ─── ACADEMIC PERIOD ──────────────────────────────────────────────────────────
export interface AcademicPeriod {
  id: string; name: string; startDate: string; endDate: string;
  isActive: number; createdAt: string;
}
export interface CreateAcademicPeriodCommand { name: string; startDate: string; endDate: string; }

// ─── COURSE ──────────────────────────────────────────────────────────────────
export interface Course {
  id: string; academicLevelId: string; name: string; code: string;
  description: string; credits: number; isActive: number;
}
export interface CreateCourseCommand {
  academicLevelId: string; name: string; code: string; description: string; credits: number;
}

// ─── COURSE ASSIGNMENT ───────────────────────────────────────────────────────
export interface CourseAssignment {
  id: string; teacherId: string; courseId: string;
  sectionId: string; academicPeriodId: string; hoursPerWeek: number;
}
export interface RegisterCourseAssignmentCommand {
  teacherId: string; courseId: string; sectionId: string;
  academicPeriodId: string; hoursPerWeek?: number;
}

// ─── DIDACTIC UNIT ───────────────────────────────────────────────────────────
export interface DidacticUnit {
  id: string; courseAssignmentId: string; unitNumber: number;
  title: string; description: string; startDate: string; endDate: string; isActive: number;
}
export interface CreateDidacticUnitCommand {
  courseAssignmentId: string; unitNumber: number; title: string;
  description?: string; startDate?: string; endDate?: string;
}

// ─── STUDENT SCORE ───────────────────────────────────────────────────────────
export interface StudentScore {
  id: string; studentId: string; courseAssignmentId: string;
  didacticUnitId: string; score: number; evaluationType: string;
  performanceLevel: string; approved: boolean; comments: string; registeredAt: string;
}
export interface RegisterScoreCommand {
  studentId: string; courseAssignmentId: string; didacticUnitId?: string;
  score: number; evaluationType: string; comments?: string;
}

// ─── GUARDIAN ────────────────────────────────────────────────────────────────
export interface Guardian {
  id: string; studentId: string; firstName: string; lastName: string;
  relationship: string; documentNumber: string; phone: string;
  email: string; address: string; isPrimaryContact: number;
}

// ─── JUSTIFICATION ───────────────────────────────────────────────────────────
export interface Justification {
  id: string; attendanceId: string; studentId: string; requestedBy: string;
  reviewedBy: string; reason: string; status: string; rejectionNote: string;
  createdAt: string; reviewedAt: string;
}
export interface RequestJustificationCommand {
  attendanceId: string; studentId: string; requestedBy: string;
  reason: string; documentUrl?: string;
}

// ─── NOTIFICATION ────────────────────────────────────────────────────────────
export interface Notification {
  id: string; recipientId: string; type: string; title: string;
  body: string; referenceId: string; referenceType: string;
  isRead: number; createdAt: string;
}

// ─── BLOCKCHAIN ──────────────────────────────────────────────────────────────
export interface BlockRecord {
  id: string; blockIndex: number; eventType: string; entityId: string;
  entityType: string; payload: string; previousHash: string;
  hash: string; createdBy: string; timestamp: string; valid: boolean;
}

// ─── CHATBOT ─────────────────────────────────────────────────────────────────
export interface ChatRequest { sessionId?: string; userId: string; userRole: string; message: string; }
export interface SourceCitation { documentTitle: string; page: number; excerpt: string; similarityScore: number; }
export interface ChatResponse {
  message: string; sources: SourceCitation[];
  sessionId: string; confidence: number; hasContext: boolean; timestamp: string;
}
export interface ChatMessage { id?: string; role: 'user' | 'assistant'; content: string; sources?: SourceCitation[]; timestamp?: string; }
