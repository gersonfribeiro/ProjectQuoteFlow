import { Component } from '@angular/core';
import { RouterLinkActive, RouterModule } from '@angular/router';

// Decorador para definir as propriedades do componente
@Component({
  selector: 'app-sidebar-dashboard', // Nome do seletor usado para o componente
  standalone: true, // Define que o componente é independente, não precisa de um módulo Angular específico
  imports: [RouterModule, RouterLinkActive], // Importa módulos necessários para roteamento e ativação de links
  templateUrl: './sidebar-dashboard.component.html', // Caminho para o template HTML do componente
  styleUrls: ['./sidebar-dashboard.component.css'], // Caminho para o arquivo de estilos CSS do componente
})
export class SidebarDashboardComponent {
  // Propriedade para controlar o estado de expansão do sidebar
  isExpanded = false;

  // Método para alternar o estado de expansão do sidebar
  toggleSidebar() {
    // Inverte o valor da propriedade isExpanded
    this.isExpanded = !this.isExpanded;
  }
}
