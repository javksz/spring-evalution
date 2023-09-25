## Evaluierung

**Spring-Security-Dependencies sind aktuell in der `pom.xml` auskommentiert; wenn bereit, einkommentieren**

Ein große Firma mochte im Rahmen einer Selbstevaluierung ihren MitarbeiterInnen die Möglichkeit geben, gezielte Fragen
über eine Webapplikation beantworten zu können. Dafür soll ein Prototyp entwickelt werden.

#### Frage

* autogenerierte Identifikationsnummer
* 20 Zeichen lange Bezeichnung
* 200 Zeichen langer Fragetext
* 3 Antwortmöglichkeiten:
    * trifft völlig zu
    * trifft teilweise zu
    * trifft nicht zu
* Ablaufdatum; eine abgelaufene Frage kann nicht mehr beantwortet werden
* ein Mitarbeiter darf eine Frage nur einmal beantworten und die Antwort ist final

#### User

* id Mailadresse
* 2 Typen: Admins und User
* verschlüsseltes Passwort
* Role/Authorities
* Beim Start der Applikation sind mindestens folgende Datensatze in der Datenbank zu speichern:
    * Ein Admin sowie 3 User
    * Fünf Fragen, wobei eine bereits abgelaufen ist und von user1 und user2 beantwortet wurde und 4 Fragen, die in
      einem Monat ablaufen. Eine davon wurde von user1 bereits beantwortet.

#### Webapp

Es ist auf jeder Seite die Email des eingeloggten Users anzuzeigen.

* **Admin**  
  Nach erfolgreichem Login kann der Administrator über ein HTML-Formular neue Fragen erfassen. Außerdem kann er sich mit
  Hilfe einer Webseite alle gespeicherten Fragen in Form einer Tabelle anzeigen lassen. Dabei sind der Fragentitel, das
  Ablaufdatum und die Anzahl der Antworten (aufgegliedert nach den einzelnen Zustimmungsstufen) anzuzeigen.
* **User**  
  Nach erfolgreichem Login sieht ein User all jene Fragen in Form einer Tabelle, die noch nicht beantwortet wurden und
  noch nicht abgelaufen sind. Nach Selektion einer Frage kann die Beantwortung in einem eigenen
  HTML-Formular erfolgen. Nach erfolgreicher Beantwortung wird wieder die Tabelle mit den nicht beantworteten Fragen
  angezeigt.

| ![](/images/add-question.png) | 
|:-----------------------------:| 
| *Admin fügt eine Frage hinzu* |

| ![](/images/open-questions.png) | 
|:-------------------------------:| 
|   *User sieht offene Fragen*    |

| ![](/images/answer-question.png) | 
|:--------------------------------:| 
|     *User beantwortet Frage*     |