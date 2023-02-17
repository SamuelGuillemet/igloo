# Cas d'utilsations :

## Diagramme des cas d'utilisations des développeurs

<div hidden>

```plantuml
@startuml UseCase1
title Diagramme des cas d'utilisations des développeurs

left to right direction
skinparam style strictuml
skinparam backgroundColor #EEEBDC

actor Utilisateur #Orange;line:Orange;line.bold

package "Gestion des développeurs" {
    usecase "== Créer un développeur" as UC1 #PaleGreen;line:Green;line.bold
    usecase "== Mettre un développeur à la corbeille" as UC5 #PaleGreen;line:Green;line.bold
    usecase "== Afficher les développeurs" as UC8 #PaleGreen;line:Green;line.bold
    usecase "== Restaurer un développeur" as UC21 #PaleGreen;line:Green;line.bold
}

Utilisateur -- UC1
Utilisateur -- UC5
Utilisateur -- UC8
Utilisateur -- UC21

@enduml
```

</div>

![Diagramme des cas d'utilisations des développeurs](./Diagrammes/UC/UseCase1.png)

### Détails des cas d'utilisations

- **[Cas d'utilisation 1 : Créer un développeur](<./UCs/UC (1).md>)**
  - Priorité : 1
- **[Cas d'utilisation 5 : Mettre un développeur à la corbeille](<./UCs/UC (5).md>)**
  - Priorité : 2
- **[Cas d'utilisation 8 : Afficher les développeurs](<./UCs/UC (8).md>)**
  - Priorité : 1
- **[Cas d'utilisation 21 : Restaurer un développeur](<./UCs/UC (21).md>)**
  - Priorité : 3

---

## Diagramme des cas d'utilisations des activités

<div hidden>

```plantuml
@startuml UseCase2
title Diagramme de cas d'utilisation des activités

left to right direction
skinparam style strictuml
skinparam backgroundColor #EEEBDC

actor Utilisateur #Orange;line:Orange;line.bold


package "Gestion des activités" {
  usecase "== Créer une activité" as UC2 #PaleGreen;line:Green;line.bold
    usecase "== Mettre une activité à la corbeille" as UC6 #PaleGreen;line:Green;line.bold
    usecase "== Afficher les activités" as UC9 #PaleGreen;line:Green;line.bold
    usecase "== Restaurer une activité" as UC22 #PaleGreen;line:Green;line.bold
}

Utilisateur -- UC2
Utilisateur -- UC6
Utilisateur -- UC9
Utilisateur -- UC22

@enduml
```

</div>

![Diagramme de cas d'utilisation des activités](./Diagrammes/UC/UseCase2.png)

### Détails des cas d'utilisations

- **[Cas d'utilisation 2 : Créer une activité](<./UCs/UC (2).md>)**
  - Priorité : 1
- **[Cas d'utilisation 6 : Mettre une activité à la corbeille](<./UCs/UC (6).md>)**
  - Priorité : 2
- **[Cas d'utilisation 9 : Afficher les activités](<./UCs/UC (9).md>)**
  - Priorité : 1
- **[Cas d'utilisation 22 : Restaurer une activité](<./UCs/UC (22).md>)**
  - Priorité : 3

---

## Diagramme des cas d'utilisations des tâches

<div hidden>

```plantuml
@startuml UseCase3
title Diagramme de cas d'utilisation des tâches

left to right direction
skinparam style strictuml
skinparam backgroundColor #EEEBDC

actor Utilisateur #Orange;line:Orange;line.bold

package "Gestion des tâches" {
    usecase "== Créer une tâche" as UC3 #PaleGreen;line:Green;line.bold
    usecase "== Mettre une tâche à la corbeille" as UC7 #PaleGreen;line:Green;line.bold
    usecase "== Afficher les tâches" as UC10 #PaleGreen;line:Green;line.bold
    usecase "== Restaurer une tâche" as UC23 #PaleGreen;line:Green;line.bold
}

Utilisateur -- UC3
Utilisateur -- UC7
Utilisateur -- UC10
Utilisateur -- UC23

@enduml
```

</div>

![Diagramme de cas d'utilisation des tâches](./Diagrammes/UC/UseCase3.png)

### Détails des cas d'utilisations

- **[Cas d'utilisation 3 : Créer une tâche](<./UCs/UC (3).md>)**
  - Priorité : 1
- **[Cas d'utilisation 7 : Mettre une tâche à la corbeille](<./UCs/UC (7).md>)**
  - Priorité : 2
- **[Cas d'utilisation 10 : Afficher les tâches](<./UCs/UC (10).md>)**
  - Priorité : 1
- **[Cas d'utilisation 23 : Restaurer une tâche](<./UCs/UC (23).md>)**
  - Priorité : 3

---

## Diagramme des cas d'utilisations des périodes de travail

<div hidden>

```plantuml
@startuml UseCase4
title Diagramme de cas d'utilisation des périodes de travail

left to right direction
skinparam style strictuml
skinparam backgroundColor #EEEBDC

actor Utilisateur #Orange;line:Orange;line.bold

package "Gestion des périodes de travail" {
    usecase "== Créer une période de travail" as UC4 #PaleGreen;line:Green;line.bold
    usecase "== Afficher les périodes de travail" as UC11 #PaleGreen;line:Green;line.bold
    usecase "== Afficher les périodes de travail d'une tâche" as UC12 #PaleGreen;line:Green;line.bold
    usecase "== Afficher les périodes de travail d'un développeur" as UC13 #PaleGreen;line:Green;line.bold
}


Utilisateur -- UC4
Utilisateur -- UC11
Utilisateur -- UC12
Utilisateur -- UC13

@enduml
```

</div>

![Diagramme de cas d'utilisation des projets](./Diagrammes/UC/UseCase4.png)

### Détails des cas d'utilisations

- **[Cas d'utilisation 4 : Créer une période de travail](<./UCs/UC (4).md>)**
  - Priorité : 1
- **[Cas d'utilisation 11 : Afficher les périodes de travail](<./UCs/UC (11).md>)**
  - Priorité : 1
- **[Cas d'utilisation 12 : Afficher les périodes de travail d'une tâche](<./UCs/UC (12).md>)**
  - Priorité : 2
- **[Cas d'utilisation 13 : Afficher les périodes de travail d'un développeur](<./UCs/UC (13).md>)**
  - Priorité : 2

---

## Diagramme des cas d'utilisations des labels

<div hidden>

```plantuml
@startuml UseCase5
title Diagramme de cas d'utilisation des labels

left to right direction
skinparam style strictuml
skinparam backgroundColor #EEEBDC

actor Utilisateur #Orange;line:Orange;line.bold

package "Gestion des labels" {
    usecase "== Créer un label" as UC18 #PaleGreen;line:Green;line.bold
    usecase "== Ajouter un label à une activité" as UC19 #PaleGreen;line:Green;line.bold
    usecase "== Ajouter un label à une tâche" as UC20 #PaleGreen;line:Green;line.bold
}

Utilisateur -- UC18
Utilisateur -- UC19
Utilisateur -- UC20

@enduml
```

</div>

![Diagramme de cas d'utilisation des labels](./Diagrammes/UC/UseCase5.png)

### Détails des cas d'utilisations

- **[Cas d'utilisation 18 : Créer un label](<./UCs/UC (18).md>)**
  - Priorité : 2
- **[Cas d'utilisation 19 : Ajouter un label à une activité](<./UCs/UC (19).md>)**
  - Priorité : 2
- **[Cas d'utilisation 20 : Ajouter un label à une tâche](<./UCs/UC (20).md>)**
  - Priorité : 2

---

## Diagramme des cas d'utilisations de la corbeille

<div hidden>

```plantuml
@startuml UseCase6
title Diagramme de cas d'utilisation de la corbeille

left to right direction
skinparam style strictuml
skinparam backgroundColor #EEEBDC

actor Utilisateur #Orange;line:Orange;line.bold

package "Gestion de la corbeille" {
    usecase "== Vider la corbeille" as UC24 #PaleGreen;line:Green;line.bold
}

Utilisateur -- UC24

@enduml
```

</div>

![Diagramme de cas d'utilisation de la corbeille](./Diagrammes/UC/UseCase6.png)

### Détails des cas d'utilisations

- **[Cas d'utilisation 24 : Vider la corbeille](<./UCs/UC (24).md>)**
  - Priorité : 3

---

## Diagramme des cas d'utilisations des calculs

<div hidden>

```plantuml
@startuml UseCase7
title Diagramme de cas d'utilisation des calculs

left to right direction
skinparam style strictuml
skinparam backgroundColor #EEEBDC

actor Utilisateur #Orange;line:Orange;line.bold

package "Calculs" {
    usecase "== Calculer le temps passé sur un projet" as UC14 #PaleGreen;line:Green;line.bold
    usecase "== Calculer le temps passé sur une activité" as UC15 #PaleGreen;line:Green;line.bold
    usecase "== Calculer le temps passé sur une tâche" as UC16 #PaleGreen;line:Green;line.bold
    usecase "== Calculer le temps passé par un développeur" as UC17 #PaleGreen;line:Green;line.bold
}


Utilisateur -- UC14
Utilisateur -- UC15
Utilisateur -- UC16
Utilisateur -- UC17

@enduml
```

</div>

![Diagramme de cas d'utilisation des calculs](./Diagrammes/UC/UseCase7.png)

### Détails des cas d'utilisations

- **[Cas d'utilisation 14 : Calculer le temps passé sur un projet](<./UCs/UC (14).md>)**
  - Priorité : 2
- **[Cas d'utilisation 15 : Calculer le temps passé sur une activité](<./UCs/UC (15).md>)**
  - Priorité : 3
- **[Cas d'utilisation 16 : Calculer le temps passé sur une tâche](<./UCs/UC (16).md>)**
  - Priorité : 3
- **[Cas d'utilisation 17 : Calculer le temps passé par un développeur](<./UCs/UC (17).md>)**
  - Priorité : 2

---
