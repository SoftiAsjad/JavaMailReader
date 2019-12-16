import { Component, OnInit } from '@angular/core';
import { Observable} from "rxjs";

import { EmailService } from '../email/email.service';
import { TranslationService} from '../i18n/translation.service'
import { AppComponent} from '../app.component'

@Component({
  selector: 'app-email-list',
  templateUrl: './email-list.component.html',
  styleUrls: ['./email-list.component.css']
})
export class EmailListComponent implements OnInit {

  emails: Observable<any>;
  messages: any;


  constructor(private emailService: EmailService, private translationService: TranslationService) { }

  ngOnInit() {
    this.reloadData();
    this.translationService.currentMessages.subscribe(messages => this.messages = messages);
  }

  reloadData() {
    this.emails = this.emailService.getEmailList();
  }
}
