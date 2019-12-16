import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

import { map } from 'rxjs/operators';
import { ajax } from 'rxjs/ajax';

@Injectable({
  providedIn: 'root'
})
export class TranslationService {

  private url = "http://localhost:8080/i18n?lang=";

  private messagesSource = new BehaviorSubject(this.getTranslations());
  currentMessages = this.messagesSource.asObservable();

  changeMessages(messages: any) {
      this.messagesSource.next(messages);
  }

  getTranslations() {

    let messages = new Map();

    const url = this.url + document.documentElement.getAttribute("lang");

    const result = ajax(url).pipe(map(e => e.response));

    const subscription = result.subscribe(res => {
      res[Symbol.iterator] = function* () {
        let properties = Object.keys(this);
        for (let i of properties) {
          yield [i, this[i]];
        }
      };

      for (let [key, value] of res ) {
        console.log("key: " + key, " value: " + value);
        messages[key.replace(/\./g,'_')] = value;
      }
    });
    return messages;
  }
}
