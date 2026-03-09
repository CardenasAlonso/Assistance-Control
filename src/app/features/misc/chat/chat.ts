import { Component, ViewChild, ElementRef, AfterViewChecked } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LucideAngularModule, Bot, Send, Trash2 } from 'lucide-angular';
import { ChatService } from '../../../core/services/chat.service';

interface ChatMsg { id: string; role: 'user' | 'assistant'; content: string; time: string; sources?: string[]; }

@Component({
  selector: 'app-chat', standalone: true,
  imports: [CommonModule, FormsModule, LucideAngularModule],
  templateUrl: './chat.html'
})
export class Chat implements AfterViewChecked {
  readonly BotIcon = Bot; readonly SendIcon = Send; readonly TrashIcon = Trash2;
  @ViewChild('messagesContainer') messagesContainer!: ElementRef;

  connected = true; thinking = false; inputText = '';
  messages: ChatMsg[] = [];

  suggestions = [
    '¿Qué son las competencias en el Currículo Nacional?',
    '¿Cómo se evalúa por niveles de logro?',
    '¿Cuáles son los enfoques transversales?',
    '¿Qué es la evaluación formativa?',
  ];

  constructor(private chatSvc: ChatService) { }

  ngAfterViewChecked() { this.scrollToBottom(); }

  scrollToBottom() {
    try { this.messagesContainer.nativeElement.scrollTop = this.messagesContainer.nativeElement.scrollHeight; } catch { }
  }

  onEnter() { this.sendMessage(); }

  sendSuggestion(text: string) { this.inputText = text; this.sendMessage(); }

  sendMessage() {
    const text = this.inputText.trim();
    if (!text || this.thinking) return;
    this.inputText = '';

    this.messages.push({ id: Date.now().toString(), role: 'user', content: text, time: this.getTime() });
    this.thinking = true;

    this.chatSvc.sendMessage({ message: text, sessionId: 'demo-session' }).subscribe({
      next: (res: any) => {
        this.thinking = false;
        this.messages.push({ id: Date.now().toString(), role: 'assistant', content: res.response || res.message, time: this.getTime(), sources: res.sources });
      },
      error: () => {
        this.thinking = false;
        this.messages.push({ id: Date.now().toString(), role: 'assistant', content: this.getMockResponse(text), time: this.getTime(), sources: ['Currículo Nacional MINEDU 2022'] });
      }
    });
  }

  clearChat() { this.messages = []; }

  private getMockResponse(q: string): string {
    if (q.toLowerCase().includes('competencia')) return 'Las competencias en el Currículo Nacional son la combinación de capacidades, conocimientos y actitudes que permiten al estudiante actuar frente a una situación concreta. El MINEDU define 29 competencias organizadas en 8 áreas curriculares para la EBR.';
    if (q.toLowerCase().includes('logro') || q.toLowerCase().includes('nivel')) return 'Los niveles de logro son cuatro:\n\n• AD (Destacado): 18–20\n• A (Logrado): 14–17\n• B (En Proceso): 11–13\n• C (En Inicio): 00–10\n\nEstos niveles reemplazan la escala vigesimal en la evaluación cualitativa por competencias.';
    if (q.toLowerCase().includes('formativa')) return 'La evaluación formativa es un proceso continuo que busca retroalimentar al estudiante y al docente. No busca calificar, sino identificar avances y dificultades para mejorar el aprendizaje. Se basa en evidencias de aprendizaje y se realiza durante todo el proceso educativo.';
    return `Según el Currículo Nacional del MINEDU, en relación a tu consulta sobre "${q.slice(0, 40)}...", el enfoque pedagógico se centra en el desarrollo de competencias a lo largo de toda la EBR. Para información más detallada, consulta el portal oficial de MINEDU o PerúEduca.`;
  }

  private getTime(): string { return new Date().toLocaleTimeString('es-PE', { hour: '2-digit', minute: '2-digit' }); }
}
