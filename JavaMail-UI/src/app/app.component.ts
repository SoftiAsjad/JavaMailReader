import { Component } from '@angular/core';
import { TranslationService} from './i18n/translation.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  messages: any;

  constructor(private translationService: TranslationService) { }

  ngOnInit() {
    this.translationService.currentMessages.subscribe(messages => this.messages = messages);
  }

  reloadLanguage() {
    this.messages = this.translationService.getTranslations();
  }

  handleClick(event: Event) {
    const target = event.target as HTMLElement;
    document.documentElement.lang = target.getAttribute("lang");

    this.reloadLanguage();
    this.translationService.changeMessages(this.messages);

    this.toggleLangButtons(target);
  }

  toggleLangButtons(target) {
    const elems = document.getElementsByClassName("language");

    for (let i = 0; i < elems.length; i++) {
      const elem = elems[i] as HTMLElement;
      elem.hidden = false;
      target.hidden = true;
    }
  }

}
