# Découvrir Java 21

Petit programme sans dépendances qui montre quatre nouveautés **finalisées dans Java 21** :

1. `SequencedCollection` : premier élément, dernier élément et vue inversée d'une liste ;
2. record patterns : lecture directe des champs d'un `record`, même imbriqué ;
3. pattern matching pour `switch` : choix selon le type et condition `when` ;
4. threads virtuels : six tâches en attente exécutées en parallèle.

## Méthodes et workflow

- `main(String[] args)` : affiche la version de Java et le PID, puis choisit le mode d'exécution.
- `collectionsOrdonnees()` : montre `getFirst()`, `getLast()` et `reversed()` sur une liste.
- `recordPatternsEtSwitch()` : extrait les valeurs des `record` et choisit une description avec `switch`.
- `threadsVirtuels()` : lance six tâches avec un thread virtuel par tâche et mesure leur durée totale.
- `observerThreadsVirtuels()` : garde quatre tâches actives pendant 45 secondes pour permettre l'inspection du processus.

**Déroulement normal :** `main` → collections → records et switch → threads virtuels. Avec `--observer` : `main` → observation des threads virtuels.

## Lancer le projet

Installer un **JDK 21** puis, dans PowerShell :

```powershell
cd "E:\workspace IT\java21-decouverte"
java '-Dstdout.encoding=UTF-8' Main.java
```

La commande `java Main.java` compile le fichier source en mémoire et exécute directement le programme. Pour compiler séparément :

```powershell
javac Main.java
java '-Dstdout.encoding=UTF-8' Main
```

## Voir le processus et les threads

Dans un premier terminal :

```powershell
java '-Dstdout.encoding=UTF-8' Main.java --observer
```

Le programme affiche son **PID** et reste actif pendant 45 secondes. Dans un deuxième terminal, remplacer `<PID>` par ce numéro :

```powershell
Get-Process -Id <PID>
& 'C:\Program Files\Java\jdk-21.0.12.1\bin\jcmd.exe' <PID> Thread.dump_to_file -format=json threads.json
```

`Get-Process` montre le processus Java. `jcmd` écrit un instantané des threads dans `threads.json`, où l'on peut rechercher `VirtualThread`. Le programme affiche aussi la commande exacte pour votre installation du JDK. Le fichier est créé dans le dossier du deuxième terminal. Les quatre tâches utilisent `Thread.sleep` pour représenter une attente d'entrée/sortie ; elles ne consomment pas chacune un thread système pendant toute cette attente.
