## 15 Aprile 2024
### SCOPE: 
CheckStyle e build con Gradle
### AZIONI: "./gradlew build" ogni volta che viene sviluppata qualche feature
### TASKS:  _SCADENZA_ 29 aprile 2024
- Marco --> gestione mappa (Model, Control)
- Luca --> Model, Control defense
- Francesco --> Termino classe player, preloading mappa, preloading difese, sistemo la parte grafico del gioco
- Cristina --> Model, Control enemies

---------

## 09/02/2024
###  TODO 
Custom deserializer per Tower
string image path (caricamento torri)

GameEngine
	- RoundManager
		scope: Gestione round, complessità del round

Map controller

---------

## 20/05/2024
### TODO

Prossimo step:
	mettere torri sulla mappa (capire posizionamento):
		lettura json delle towers
			return List<Towers>
			visualizzo le tower passate dal model
	
	costruzione torre:
		click sulla torre
		click sulla cella di posizionamento
			se la cella è disponibile [metodo per evidenziare le celle disponibili]
				visualizzo l'immagine della torre selezionata nella cella selezionata.		
		decrease money
	
	disattivare torri che hanno costo maggiore dei money attualmente posseduti
	
	UTILIZZARE INPUT STREAM COME IN TILEFACTORY_IMPL
	
	[WAIT]
	gestione thread/engine:
		gestione con engine
		no gestione thread con towers
		gestione thread bullets
		
	tests
	
---------

## 28/05/2024

ENEMY:
	observer implementation

DEFENSE:
	observer implementation
	GuiGameStart creare TowerCard
		
Interazione torri-nemici
Gestione economia/round
	Quando posiziono una torre, scalo i soldi
	Quando killo un nemico, aumento i soldi
Torri non posizionabili sul percorso

RoundManager come thread separato

IN FUTURO:
	Gestione transazioni per economia

---------------------------

## 05/06/2024

Difesa:
	- pulizia codice card
	- interazione con bullet view
	- guardare quanto deve togliere ogni bullet
	- update() per Bullet
	
Nemici:
	- Nell'enemy manager togliere o gestire i nemici attivi o meno
	- Metodo che accetta come parametro (attraverso id) e creare il nemico del tipo di id passato
	- Metodo numeroNemiciVivi() 
	- Metodo per gettare il danno nemici che sono alla fine della mappa
		- eliminare i nemici al termine del metodo dopo aver inflitto il danno al player

Fra:
	- Countdown visualizzabile sulla mappa
		Tempo nel GameState: nel gameState ci sarà un'istanza del roundManager
			getCurrentTime()
		Quando il countdown termina:
			- i nemici vengono spawnati
	- RoundManager
		metodo gameOver(): smette di andare avanti
	- Visualizzazione di:
		- vite con update
		- tempo ondata
		- soldi disponibili
	- EnemiesManager parametro del costruttore roundManager
	
Casali:
	Il percorso deve essere disabilitato (per il posizionamento delle torri)
	Gestione del fine mappa
		- ogni enemy muore
		- toglie vita al player
			- se player <= 0 e tempo ancora attivo
				- schermata end
