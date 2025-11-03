# Flight Management System
**Projektaufgabe 1 – Thema 2**  
**Autoren:** Grosu Sofia, Holobîcă Bianca  
**Kurs:** MAP – Laborprojekt  
 
---

##  1. Überblick
Dieses Projekt implementiert ein **Flight Management System** auf der Grundlage von **Spring Boot** und den **Prinzipien der objektorientierten Programmierung**.  
Ziel ist es, die Grundstruktur einer mehrschichtigen Anwendung nach dem **MVC-Modell (Model – Repository – Service – Controller)** zu verstehen und anzuwenden.

Alle Daten werden **im Speicher (In-Memory)** gespeichert, ohne Verwendung einer externen Datenbank.  
Dieses Projekt dient als Vorbereitung für spätere Phasen, in denen Persistenz, REST-Schnittstellen und Datenbanken integriert werden.

---

##  2. Verwendete Technologien
- **Java 17**
- **Spring Boot 3.x**
- **Maven**
- **IntelliJ IDEA Community Edition**
- **Git / GitHub**

---

##  3. Architektur und Schichtenaufbau

Projektstruktur:
src/
└── main/java/com/example/flight/
├── model/
├── repository/
├── service/
└── controller/
└── main/resources/
├── templates/ → Thymeleaf-Templates (index.html, form.html für jede Entität)
└── application.properties


## 4. Designentscheidungen

-**Ein Repository und ein Service pro Entität, um die Prinzipien von Single Responsibility (SRP) konsequent umzusetzen.**

-**Drei Controller (Staff, Flight, Passenger), um logische Verantwortungsbereiche getrennt zu halten.**

### 4.1. CRUD-Service und Abstraktion
Um Wiederholungen zu vermeiden, wurde ein **generisches Service-Interface** `CrudService<T>` eingeführt.
Für jede Entität (z. B. FlightService, PassengerService, AirplaneService) wird dieses Interface implementiert.
Die Implementierungen arbeiten ausschließlich In-Memory und generieren IDs automatisch, falls keine angegeben sind.

### 4.2. AbstractController
Zur Vereinfachung der Controller-Logik wurde ein AbstractCrudController<T> erstellt, der die Grundfunktionen bereitstellt:
-GET / – Listet alle Objekte (index.html)
-GET /new – Öffnet das Formular für eine neue Entität (form.html)
-POST / – Speichert ein neues Objekt
-POST /{id}/delete – Löscht ein Objekt anhand der ID
Jede konkrete Controller-Klasse (z. B. FlightController, PassengerController, AirplaneController) erbt davon und definiert nur:
-den URL-Pfad (z. B. /flights),
-die View-Dateien (flights/index, flights/form), 
-und die Model-Schlüssel ("flights", "flight").

### 4.3. View-Schicht (Thymeleaf)
Jede Entität besitzt nun eigene Thymeleaf-Templates:
-index.html → zeigt die Liste aller Objekte
-form.html → Formular zum Hinzufügen neuer Objekte

### 5. Beispiel für Anwendung
Beim Start der Anwendung (CommandLineRunner in FlightManagementSystemApplication) werden automatisch Testdaten in den Speicher geladen.
Diese sind sofort über den Browser sichtbar, z. B.:

/flights → zeigt alle gespeicherten Flüge

/passengers → zeigt alle Passagiere

/noticeboards → zeigt die gespeicherten Aushänge

Jeder Eintrag kann über das Formular hinzugefügt und über den Delete-Button gelöscht werden.