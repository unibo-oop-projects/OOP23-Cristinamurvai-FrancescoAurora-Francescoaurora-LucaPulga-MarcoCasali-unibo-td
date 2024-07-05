## 15 Aprile 2024
Termino classe player, preloading mappa, preloading difese, sistemo la parte grafica del gioco

## 09/05/2024
- RoundManager
		scope: Gestione round, complessità del round

## 15/05/2024
Gestione vittoria e sconfitta

## 20/05/2024
Fix map selection and gui

## 28/05/2024
Gestione economia/round
RoundManager come thread separato

## 05/06/2024
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