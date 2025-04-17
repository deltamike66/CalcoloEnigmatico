Questo software scritto in java risolve il Calcolo Enigmatico della settimana enigmistica utilizzando un algoritmo di forza bruta.

![schermata di esempio](https://github.com/user-attachments/assets/4e5836c3-b750-4a94-a993-f14b1f0b160b)

L'algoritmo non è difficile da comprendere, prendo le prime tre operazioni in orizzontale e per ogni riga cerco le combinazioni possibili che risolvono le operazioni.
Metto i risultati in 3 diversi ArrayList e nel calcolo finale cerco i valori per cui tutte le operazioni sono risolte contemporaneamente.

Questo mi permette di ridurre i tempi di elaborazione perchè i simboli per ogni singola operazione non sono mai tutti e nove, e quindi i calcoli necessari sono stati ridotti al minimo.

L'utilizzo è semplice, con lo schema vuoto, cliccare su una casella e scegliere il simbolo o l'operazione desiderata, e una volta completato lo schema, premere il tasto "risolvi".

