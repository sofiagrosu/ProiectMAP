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
└── application.properties

## 4. Designentscheidungen

-**Ein Repository und ein Service pro Entität, um die Prinzipien von Single Responsibility (SRP) konsequent umzusetzen.**

-**Drei Controller (Staff, Flight, Passenger), um logische Verantwortungsbereiche getrennt zu halten.**
  
