Binôme:
- Nom1 Prenom1 (to be adapted)
- Nom2 Prenom2 (to be adapted)

Copyright (C) 2016-2023
Contact: Denis.Conan[at]telecom-sudparis.eu

----

Structure du projet:
```
$ tree
├── Code                 // le code du logiciel dans un module Maven
│   ├── pom.xml          // la configuration Maven du projet
│   └── src              // le code source
├── LICENSE              // la lisence (GPL)
├── Modelisation         // le dossier de modélisation avec sources + PDF
│   ├── Diagrammes       // vos diagrammes : images png par exemple
│   ├── modelisation.pdf // le document de modélisation pour évaluation
│   ├── ...
│   └── *.zip            // le fichier Modelio, qui doit être à jour
├── readme.md            // ce fichier
└── Suivi
    └── readme.md        // les messages échangés lors du suivi entre séances
```

# Avant de commencer à modéliser

Lisez et suivez les instructions dans le fichier [`Modelisation/readme.md`](Modelisation/readme.md).

# Prérequis logiciels

1. JAVA Version >= 17.0 (de préférence, une version *Long-Term Support*)
    * (https://openjdk.java.net/install/index.html)
2. Maven Version >= 3.8
    * (http://maven.apache.org/)
3. Modelio Version 5.1.0

# Avant d'utiliser le module Maven

Modifiez la ligne
```
 <artifactId>csc4102-prenom1Nom1-prenom2Nom2</artifactId>
```
dans le fichier `Code/pom.xml`

# Compilation et installation Maven

Pour compiler et installer le module Maven du logiciel, exécuter la commande qui suit.

```
(cd Code; mvn install)
```

# Dans Eclipse:

Dans Eclipse, pour charger le projet Maven: File > Import > Maven > Existing maven project.
