import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LucideAngularModule, QrCode, Camera, CheckCircle } from 'lucide-angular';

@Component({
  selector: 'app-entrance', standalone: true,
  imports: [CommonModule, FormsModule, LucideAngularModule],
  templateUrl: './entrance.html'
})
export class Entrance implements OnInit, OnDestroy {
  readonly QrIcon = QrCode; readonly CameraIcon = Camera; readonly CheckIcon = CheckCircle;

  currentTime = ''; manualCode = ''; lastRegistered: any = null;
  private timer: any;

  todayRecords = [
    { time: '07:28', name: 'Carlos Huamán Quispe', section: '3°A', method: 'QR', status: 'PRESENT' },
    { time: '07:31', name: 'Ángel Vásquez Díaz', section: '3°B', method: 'QR', status: 'PRESENT' },
    { time: '07:35', name: 'Miguel Ramos Condori', section: '4°A', method: 'QR', status: 'PRESENT' },
    { time: '07:51', name: 'José Mamani Torres', section: '3°A', method: 'Manual', status: 'LATE' },
    { time: '08:05', name: 'Pedro Quispe Layme', section: '4°A', method: 'Manual', status: 'LATE' },
  ];

  ngOnInit() {
    this.updateTime();
    this.timer = setInterval(() => this.updateTime(), 1000);
  }

  ngOnDestroy() { clearInterval(this.timer); }

  updateTime() { this.currentTime = new Date().toLocaleTimeString('es-PE', { hour: '2-digit', minute: '2-digit', second: '2-digit' }); }

  registerManual() {
    if (!this.manualCode.trim()) return;
    const hour = new Date().toLocaleTimeString('es-PE', { hour: '2-digit', minute: '2-digit' });
    const isLate = new Date().getHours() >= 8;
    const newRecord = { time: hour, name: 'Estudiante (DNI: ' + this.manualCode + ')', section: '—', method: 'Manual', status: isLate ? 'LATE' : 'PRESENT' };
    this.todayRecords = [newRecord, ...this.todayRecords];
    this.lastRegistered = { name: 'DNI: ' + this.manualCode, time: hour };
    this.manualCode = '';
    setTimeout(() => this.lastRegistered = null, 4000);
  }
}
