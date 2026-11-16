# Découvrir Java 21

Petit programme sans dépendances qui montre quatre nouveautés **finalisées dans Java 21** :

1. `SequencedCollection` : premier élément, dernier élément et vue inversée d'une liste ;
2. record patterns : lecture directe des champs d'un `record`, même imbriqué ;
3. pattern matching pour `switch` : choix selon le type et condition `when` ;
4. threads virtuels : six tâches en attente exécutées en parallèle.

## Classes et déroulement

- `src/Main.java` : affiche la version de Java et le PID, puis lance les démonstrations.
- `src/news/CollectionsOrdonnees.java` : montre `getFirst()`, `getLast()` et `reversed()` sur une liste.
- `src/news/RecordPatternsEtSwitch.java` : extrait les valeurs des `record` et choisit une description avec `switch`.
- `src/news/ThreadsVirtuels.java` : lance six tâches avec un thread virtuel par tâche et mesure leur durée totale.
- `src/news/ObservationThreadsVirtuels.java` : garde quatre tâches actives pendant 45 secondes pour permettre l'inspection du processus.
- `src/config/java21-decouverte.iml` : définit le module IntelliJ et son dossier source `src`.

**Déroulement normal :** `Main` → collections → records et switch → threads virtuels. Avec `--observer` : `Main` → observation des threads virtuels.

## Lancer le projet

Installer un **JDK 21** puis, dans PowerShell :

```powershell
cd "E:\workspace IT\java21-decouverte"
New-Item -ItemType Directory -Force out | Out-Null
javac -d out src/Main.java src/news/CollectionsOrdonnees.java src/news/RecordPatternsEtSwitch.java src/news/ThreadsVirtuels.java src/news/ObservationThreadsVirtuels.java
java '-Dstdout.encoding=UTF-8' -cp out Main
```

Le dossier `src` est la racine des sources dans IntelliJ IDEA. Les exemples appartiennent au package `news` : il faut compiler ces fichiers avec `Main` avant de lancer le programme.

## Voir le processus et les threads

Dans un premier terminal :

```powershell
java '-Dstdout.encoding=UTF-8' -cp out Main --observer
```

Le programme affiche son **PID** et reste actif pendant 45 secondes. Dans un deuxième terminal, remplacer `<PID>` par ce numéro :

```powershell
Get-Process -Id <PID>
& 'C:\Program Files\Java\jdk-21.0.12.1\bin\jcmd.exe' <PID> Thread.dump_to_file -format=json threads.json
```

`Get-Process` montre le processus Java. `jcmd` écrit un instantané des threads dans `threads.json`, où l'on peut rechercher `VirtualThread`. Le programme affiche aussi la commande exacte pour votre installation du JDK. Le fichier est créé dans le dossier du deuxième terminal. Les quatre tâches utilisent `Thread.sleep` pour représenter une attente d'entrée/sortie ; elles ne consomment pas chacune un thread système pendant toute cette attente.


## TERMINAL

```powershell
Découverte de Java 21
PID du processus Java : 17120

1. Collections ordonnées (SequencedCollection)
   Étapes             : [écrire, compiler, exécuter]
   Première / dernière: écrire / exécuter
   Vue inversée       : [exécuter, compiler, écrire]

2. Record patterns et switch sur les types
   point à l'origine
   point (3, 4)
   segment de (1, 2) à (5, 6)

3. Threads virtuels : 6 tâches attendent en parallèle
   tâche 1 terminée ; thread virtuel = true
   tâche 2 terminée ; thread virtuel = true
   tâche 3 terminée ; thread virtuel = true
   tâche 4 terminée ; thread virtuel = true
   tâche 5 terminée ; thread virtuel = true
   tâche 6 terminée ; thread virtuel = true
   Durée totale : 514 ms (environ 500 ms car les attentes se chevauchent)

Process finished with exit code 0

```
