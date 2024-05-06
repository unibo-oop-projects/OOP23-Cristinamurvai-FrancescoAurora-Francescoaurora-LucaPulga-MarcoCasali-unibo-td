## PRIMA DEL 15 Aprile 2024
### SCOPE: 
Sviluppo design parte di defense (torri, armi, proiettili)
### AZIONI: Design architettura software
### TASKS:  _SCADENZA_ 29 aprile 2024
- Luca --> Model, Control defense

---------

## 18/04/2024
Ridefinizione del Defense Model, ridefinizione organizzazione cartella.
Trovato il filepath da passare come parametro per trovare i json delle torri da caricare e successivamente trasformare in java objects.
Errore nella conversione da json a java object leggendo "tower1.json"

---------

## 20/04/2024
New defense model implementation with interfaces, abstract classes and concrete classes partly re-designed.
Prima implementazione della fisica del gioco.

---------

## 04/05/2024

EntityFactoryImpl e dunque EntityFactory:
	gestisce il loading della torre, ma non l'inizializzazione del thread della torre;
	metodo generico utile per tutti i tipi di entità;
DefenseManagerImpl:
	dopo il loading della torre da parte dell'EntityFactoryImpl, gestisce l'inizializzazione del thread della torre;
	
Main:
	test per lettura file json, creazione obj voluto e gestione dei thread con il proprio manager;
	
ALTRO:
	Aggiunta di @JsonProperty("id") nei parametri dei costruttori, altrimenti la libreria jackson non capisce cosa deve parsare;

---------

## 07/05/2024

Strategy Pattern per attack e target delle torri.
Lettura json dei nuovi parametri.