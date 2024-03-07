# OOP23-unibo-td

UNIBO-TD

Email dei componenti:

- cristina.murvai@studio.unibo.it
- francesco.aurora@studio.unibo.it
- luca.pulga@studio.unibo.it
- marco.casali13@studio.unibo.it


Il gruppo si pone l'obiettivo di realizzare un gioco di tipologia Tower-Defense, ispirato a Bloons TD (https://en.wikipedia.org/wiki/Bloons_Tower_Defense).


Nel gioco, implementato a round di difficoltà incrementale, i giocatori cercano di impedire ai nemici di raggiungere la fine di un percorso stabilito, posizionando difese per eliminarli. Eliminando i nemici e completando i round, si guadagnaranno monete che potranno essere utilizzate per acquistare nuove difese. Ad ogni partita si avranno a disposizione N vite. Il gioco finisce quando le vite terminano.



Funzionalità minimali ritenute obbligatorie:

- Modalità endless, gioco senza vincita
- Suddivisione del gioco in round con difficoltà incrementale
- Definizione e creazione mappa
- Posizionamento difese sulla mappa nei punti permessi
- Ogni difesa ha a disposizione una e una sola arma
- Gestione vite
- Gestione economia
- Interazione tra nemici e difese
- I nemici seguono lo stesso percorso alla stessa velocità



Funzionalità opzionali:

- Presenza di più tipologie di nemici 
- Presenza di più tipologie di difese
- Presenza di più tipologie di mappe
- Rimozione e riposizionamento difese sulla mappa nei punti permessi
- Ogni difesa può avere a disposizione più armi
- Gestioni effetti sonori



"Challenge" principali:

- Fluidità del gioco
- Gestione DVCS
- Gestione del multithreading
- Corretta implementazione dei pattern di OOP


Suddivisione del lavoro:

- Aurora: Gestione delle azioni utente (gestione economia, riposizionamento difese sulla mappa nei punti permessi), ogni difesa può avere a disposizione più armi
- Casali: Game loop, generazione mappa, controllo velocità di gioco, gestione perdita vite, presenza di più tipologie di mappe
- Murvai: Gestione dei nemici intera (spawn, movimento, distruzione), presenze di più tipologie di nemici, gestione effetti sonori
- Pulga: Gestione delle difese intera (creazione, interazioni coi nemici), presenze di più tipologie di difese
