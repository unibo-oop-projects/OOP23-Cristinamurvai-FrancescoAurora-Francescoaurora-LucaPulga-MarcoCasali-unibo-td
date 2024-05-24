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
