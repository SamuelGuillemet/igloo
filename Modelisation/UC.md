# Unités de développement :

- Developpeur
- Activité
- Tache
- Période de travail
- Corbeille

# UC:

## Tâches de créations :

<details>
<summary>Créer un développeur</summary>

### **Créer un développeur**

> MUST

#### Préconditions:

- Nom fourni
- Prénom fourni
- Alias fourni
- Alias unique

#### Postconditions:

- Création d'un développeur
- Message de confirmation

#### Scénario:

- Saisie du nom
- Saisie du prénom
- Saisie de l'alias
- Création du développeur
- Message de confirmation

#### Table de decision:

| Préconditions               | 1   | 2   | 3   | 4   | 5   |
| --------------------------- | --- | --- | --- | --- | --- |
| _Nom fourni_                | Non | Oui | Oui | Oui | Oui |
| _Prénom fourni_             |     | Non | Oui | Oui | Oui |
| _Alias fourni_              |     |     | Non | Oui | Oui |
| _Alias unique_              |     |     |     | Non | Oui |
| **Postconditions**          |     |     |     |     |     |
| _Création d'un développeur_ | Non | Non | Non | Non | Oui |
| _Message d'erreur_          | Oui | Oui | Oui | Oui | Non |

---

</details>

<details>
<summary>Créer une activité</summary>

### **Créer une activité**

> MUST

#### Préconditions:

- Nom fourni
- Id fourni
- Id unique

#### Postconditions:

- Création d'une activité
- Message de confirmation

#### Scénario:

- Saisie du nom
- Saisie de l'id
- Création de l'activité
- Message de confirmation

#### Table de decision:

| Préconditions             | 1   | 2   | 3   | 4   |
| ------------------------- | --- | --- | --- | --- |
| _Nom fourni_              | Non | Oui | Oui | Oui |
| _Id fourni_               |     | Non | Oui | Oui |
| _Id unique_               |     |     | Non | Oui |
| **Postconditions**        |     |     |     |     |
| _Création d'une activité_ | Non | Non | Non | Oui |
| _Message d'erreur_        | Oui | Oui | Oui | Non |

---

</details>

<details>
<summary>Créer une tâche</summary>

### **Créer une tâche**

> MUST

#### Préconditions:

- Nom fourni
- Id fourni
- Id unique
- Activité fournie
- Activité existante
- Activité active

#### Postconditions:

- Création d'une tâche
- Message de confirmation

#### Scénario:

- Saisie du nom
- Saisie de l'id
- Saisie de l'activité
- Création de la tâche
- Message de confirmation

#### Table de decision:

| Préconditions          | 1   | 2   | 3   | 4   | 5   | 6   | 7   |
| ---------------------- | --- | --- | --- | --- | --- | --- | --- |
| _Nom fourni_           | Non | Oui | Oui | Oui | Oui | Oui | Oui |
| _Id fourni_            |     | Non | Oui | Oui | Oui | Oui | Oui |
| _Id unique_            |     |     | Non | Oui | Oui | Oui | Oui |
| _Activité fournie_     |     |     |     | Non | Oui | Oui | Oui |
| _Activité existante_   |     |     |     |     | Non | Oui | Oui |
| _Activité active_      |     |     |     |     |     | Non | Oui |
| **Postconditions**     |     |     |     |     |     |     |     |
| _Création d'une tâche_ | Non | Non | Non | Non | Non | Non | Oui |
| _Message d'erreur_     | Oui | Oui | Oui | Oui | Oui | Oui | Non |

---

</details>

<details>
<summary>Créer une période de travail</summary>

### **Créer une période de travail**

> MUST

#### Préconditions:

- Début fourni
- Fin fournie
- Début < Fin
- Tâche fournie
- Tâche existante
- Tâche active
- Développeur fourni
- Développeur existant
- Développeur actif
- Pas de chevauchement avec une autre période de travail

#### Postconditions:

- Création d'une période de travail
- Message de confirmation

#### Scénario:

- Saisie du début
- Saisie de la fin
- Saisie de la tâche
- Saisie du développeur
- Création de la période de travail
- Message de confirmation

#### Table de decision:

| Préconditions                                            | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   | 10  | 11  |
| -------------------------------------------------------- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| _Début fourni_                                           | Non | Oui | Oui | Oui | Oui | Oui | Oui | Oui | Oui | Oui | Oui |
| _Fin fournie_                                            |     | Non | Oui | Oui | Oui | Oui | Oui | Oui | Oui | Oui | Oui |
| _Début < Fin_                                            |     |     | Non | Oui | Oui | Oui | Oui | Oui | Oui | Oui | Oui |
| _Tâche fournie_                                          |     |     |     | Non | Oui | Oui | Oui | Oui | Oui | Oui | Oui |
| _Tâche existante_                                        |     |     |     |     | Non | Oui | Oui | Oui | Oui | Oui | Oui |
| _Tâche active_                                           |     |     |     |     |     | Non | Oui | Oui | Oui | Oui | Oui |
| _Développeur fourni_                                     |     |     |     |     |     |     | Non | Oui | Oui | Oui | Oui |
| _Développeur existant_                                   |     |     |     |     |     |     |     | Non | Oui | Oui | Oui |
| _Développeur actif_                                      |     |     |     |     |     |     |     |     | Non | Oui | Oui |
| _Pas de chevauchement avec une autre période de travail_ |     |     |     |     |     |     |     |     |     | Non | Oui |
| **Postconditions**                                       |     |     |     |     |     |     |     |     |     |     |     |
| _Création d'une période de travail_                      | Non | Non | Non | Non | Non | Non | Non | Non | Non | Non | Oui |
| _Message d'erreur_                                       | Oui | Oui | Oui | Oui | Oui | Oui | Oui | Oui | Oui | Oui | Non |

---

</details>

## Tâches de mise à la corbeille :

<details>
<summary>Mettre un développeur à la corbeille</summary>

### **Mettre un développeur à la corbeille**

> MUST

#### Préconditions:

- Développeur fourni
- Développeur existant
- Développeur actif

#### Postconditions:

- Développeur mis à la corbeille
- Périodes de travail du développeur mises à la corbeille
- Message de confirmation

#### Scénario:

- Saisie du développeur
- Mise à la corbeille du développeur
- Message de confirmation

#### Table de decision:

| Préconditions                                             | 1   | 2   | 3   | 4   |
| --------------------------------------------------------- | --- | --- | --- | --- |
| _Développeur fourni_                                      | Non | Oui | Oui | Oui |
| _Développeur existant_                                    |     | Non | Oui | Oui |
| _Développeur actif_                                       |     |     | Non | Oui |
| **Postconditions**                                        |     |     |     |     |
| _Développeur mis à la corbeille_                          | Non | Non | Non | Oui |
| _Périodes de travail du développeur mises à la corbeille_ | Non | Non | Non | Oui |
| _Message d'erreur_                                        | Oui | Oui | Oui | Non |

---

</details>

<details>
<summary>Mettre une activité à la corbeille</summary>

### **Mettre une activité à la corbeille**

> MUST

#### Préconditions:

- Activité fournie
- Activité existante
- Activité active

#### Postconditions:

- Activité mise à la corbeille
- Tâches de l'activité mises à la corbeille
- Périodes de travail des tâches mises à la corbeille
- Message de confirmation

#### Scénario:

- Saisie de l'activité
- Mise à la corbeille de l'activité
- Message de confirmation

#### Table de decision:

| Préconditions                                         | 1   | 2   | 3   | 4   |
| ----------------------------------------------------- | --- | --- | --- | --- |
| _Activité fournie_                                    | Non | Oui | Oui | Oui |
| _Activité existante_                                  |     | Non | Oui | Oui |
| _Activité active_                                     |     |     | Non | Oui |
| **Postconditions**                                    |     |     |     |     |
| _Activité mise à la corbeille_                        | Non | Non | Non | Oui |
| _Tâches de l'activité mises à la corbeille_           | Non | Non | Non | Oui |
| _Périodes de travail des tâches mises à la corbeille_ | Non | Non | Non | Oui |
| _Message d'erreur_                                    | Oui | Oui | Oui | Non |

---

</details>

<details>
<summary>Mettre une tâche à la corbeille</summary>

### **Mettre une tâche à la corbeille**

> MUST

#### Préconditions:

- Tâche fournie
- Tâche existante
- Tâche active

#### Postconditions:

- Tâche mise à la corbeille
- Périodes de travail de la tâche mises à la corbeille
- Message de confirmation

#### Scénario:

- Saisie de la tâche
- Mise à la corbeille de la tâche
- Message de confirmation

#### Table de decision:

| Préconditions                                          | 1   | 2   | 3   | 4   |
| ------------------------------------------------------ | --- | --- | --- | --- |
| _Tâche fournie_                                        | Non | Oui | Oui | Oui |
| _Tâche existante_                                      |     | Non | Oui | Oui |
| _Tâche active_                                         |     |     | Non | Oui |
| **Postconditions**                                     |     |     |     |     |
| _Tâche mise à la corbeille_                            | Non | Non | Non | Oui |
| _Périodes de travail de la tâche mises à la corbeille_ | Non | Non | Non | Oui |
| _Message d'erreur_                                     | Oui | Oui | Oui | Non |

---

</details>

## Tâches d'affichage :

<details>
<summary>Afficher les développeurs</summary>

### **Afficher les développeurs**

> MUST

#### Préconditions:

- Type de tri fourni

```java
public enum SortType {
	ACTIVE,
	INACTIVE,
	ALL
}
```

#### Postconditions:

- Affichage des développeurs

#### Scénario:

- Saisie du type de tri
- Affichage des développeurs

#### Table de decision:

| Préconditions                         | 1   | 2   | 3            | 4              | 5         |
| ------------------------------------- | --- | --- | ------------ | -------------- | --------- |
| _Type de tri fourni_                  | Non | Oui | Oui          | Oui            | Oui       |
| _Type de tri valide_                  |     | Non | Oui `ACTIVE` | Oui `INACTIVE` | Oui `ALL` |
| **Postconditions**                    |     |     |              |                |           |
| _Affichage des développeurs actifs_   | Non | Non | Oui          | Non            | Non       |
| _Affichage des développeurs inactifs_ | Non | Non | Non          | Oui            | Non       |
| _Affichage de tous les développeurs_  | Non | Non | Non          | Non            | Oui       |
| _Message d'erreur_                    | Oui | Oui | Non          | Non            | Non       |

---

</details>

<details>
<summary>Afficher les activités</summary>

### **Afficher les activités**

> MAY

#### Préconditions:

- Type de tri fourni

```java
public enum SortType {
	ACTIVE,
	INACTIVE,
	ALL
}
```

#### Postconditions:

- Affichage des activités

#### Scénario:

- Saisie du type de tri
- Affichage des activités

#### Table de decision:

| Préconditions                       | 1   | 2   | 3            | 4              | 5         |
| ----------------------------------- | --- | --- | ------------ | -------------- | --------- |
| _Type de tri fourni_                | Non | Oui | Oui          | Oui            | Oui       |
| _Type de tri valide_                |     | Non | Oui `ACTIVE` | Oui `INACTIVE` | Oui `ALL` |
| **Postconditions**                  |     |     |              |                |           |
| _Affichage des activités actives_   | Non | Non | Oui          | Non            | Non       |
| _Affichage des activités inactives_ | Non | Non | Non          | Oui            | Non       |
| _Affichage de toutes les activités_ | Non | Non | Non          | Non            | Oui       |
| _Message d'erreur_                  | Oui | Oui | Non          | Non            | Non       |

---

</details>

<details>
<summary>Afficher les tâches</summary>

### **Afficher les tâches d'une activité**

> MAY

#### Préconditions:

- Activité fournie
- Activité existante

#### Postconditions:

- Affichage des tâches de l'activité

#### Scénario:

- Saisie de l'activité
- Affichage des tâches de l'activité

#### Table de decision:

| Préconditions                        | 1   | 2   | 3   |
| ------------------------------------ | --- | --- | --- |
| _Activité fournie_                   | Non | Oui | Oui |
| _Activité existante_                 |     | Non | Oui |
| **Postconditions**                   |     |     |     |
| _Affichage des tâches de l'activité_ | Non | Non | Oui |
| _Message d'erreur_                   | Oui | Oui | Non |

---

</details>

<details>
<summary>Afficher les périodes de travail</summary>

### **Afficher les périodes de travail**

> MUST

#### Préconditions:

- Type de tri fourni

```java
public enum SortType {
	ACTIVE,
	INACTIVE,
	ALL
}
```

#### Postconditions:

- Affichage des périodes de travail

#### Scénario:

- Saisie du type de tri
- Saisie de l'activité (si type de tri `ACTIVE`)
- Saisie de la tâche (si type de tri `ACTIVE`)
- Affichage des périodes de travail

#### Table de decision:

| Préconditions                                 | 1   | 2   | 3         | 4              | 5            |
| --------------------------------------------- | --- | --- | --------- | -------------- | ------------ |
| _Type de tri fourni_                          | Non | Oui | Oui       | Oui            | Oui          |
| _Type de tri valide_                          |     | Non | Oui `ALL` | Oui `INACTIVE` | Oui `ACTIVE` |
| **Postconditions**                            |     |     |           |                |              |
| _Affichage des périodes de travail actives_   | Non | Non | Non       | Non            | Oui          |
| _Affichage des périodes de travail inactives_ | Non | Non | Non       | Oui            | Non          |
| _Affichage de toutes les périodes de travail_ | Non | Non | Oui       | Non            | Non          |
| _Message d'erreur_                            | Oui | Oui | Non       | Non            | Non          |

---

</details>

<details>
<summary>Afficher les périodes de travail d'une tâche</summary>

### **Afficher les périodes de travail d'une tâche**

> MUST

#### Préconditions:

- Activité fournie
- Activité existante
- Tâche fournie
- Tâche existante

#### Postconditions:

- Affichage des périodes de travail de la tâche

#### Scénario:

- Saisie de l'activité
- Saisie de la tâche
- Affichage des périodes de travail de la tâche

#### Table de decision:

| Préconditions                       | 1   | 2   | 3   | 4   | 5   |
| ----------------------------------- | --- | --- | --- | --- | --- |
| _Activité fournie_                  | Non | Oui | Oui | Oui | Oui |
| _Activité existante_                |     | Non | Oui | Oui | Oui |
| _Tâche fournie_                     |     |     | Non | Oui | Oui |
| _Tâche existante_                   |     |     |     | Non | Oui |
| **Postconditions**                  |     |     |     |     |     |
| _Affichage des périodes de travail_ | Non | Non | Non | Non | Oui |
| _Message d'erreur_                  | Oui | Oui | Oui | Oui | Non |

---

</details>

<details>
<summary>Afficher les périodes de travail d'un développeur</summary>

### **Afficher les périodes de travail d'un développeur**

> SHOULD

#### Préconditions:

- Développeur fourni
- Développeur existant

#### Postconditions:

- Affichage des périodes de travail du développeur

#### Scénario:

- Saisie du développeur
- Affichage des périodes de travail du développeur

#### Table de decision:

| Préconditions                       | 1   | 2   | 3   |
| ----------------------------------- | --- | --- | --- |
| _Développeur fourni_                | Non | Oui | Oui |
| _Développeur existant_              |     | Non | Oui |
| **Postconditions**                  |     |     |     |
| _Affichage des périodes de travail_ | Non | Non | Oui |
| _Message d'erreur_                  | Oui | Oui | Non |

---

</details>

## Tâches de calcul de temps :

<details>
<summary>Calculer le temps passé sur un projet</summary>

### **Calculer le temps passé sur un projet**

> MUST

#### Préconditions:

- Aucune

#### Postconditions:

- Affichage du temps passé sur le projet

#### Scénario:

- Affichage du temps passé sur le projet

#### Table de decision:

| Préconditions                            | 1   |
| ---------------------------------------- | --- |
| **Postconditions**                       |     |
| _Affichage du temps passé sur le projet_ | Oui |

---

</details>

<details>
<summary>Calculer le temps passé sur une activité</summary>

### **Calculer le temps passé sur une activité**

> SHOULD

#### Préconditions:

- Activité fournie
- Activité existante
- Activité active

#### Postconditions:

- Affichage du temps passé sur l'activité

#### Scénario:

- Saisie de l'activité
- Affichage du temps passé sur l'activité

#### Table de decision:

| Préconditions                             | 1   | 2   | 3   |
| ----------------------------------------- | --- | --- | --- |
| _Activité fournie_                        | Non | Oui | Oui |
| _Activité existante_                      |     | Non | Oui |
| _Activité active_                         |     |     | Non |
| **Postconditions**                        |     |     |     |
| _Affichage du temps passé sur l'activité_ | Non | Non | Oui |
| _Message d'erreur_                        | Oui | Oui | Non |

---

</details>

<details>
<summary>Calculer le temps passé sur une tâche</summary>

### **Calculer le temps passé sur une tâche**

> SHOULD

#### Préconditions:

- Activité fournie
- Activité existante
- Activité active
- Tâche fournie
- Tâche existante
- Tâche active

#### Postconditions:

- Affichage du temps passé sur la tâche

#### Scénario:

- Saisie de la tâche
- Affichage du temps passé sur la tâche

#### Table de decision:

| Préconditions                           | 1   | 2   | 3   | 4   | 5   | 6   | 7   |
| --------------------------------------- | --- | --- | --- | --- | --- | --- | --- |
| _Activité fournie_                      | Non | Oui | Oui | Oui | Oui | Oui | Oui |
| _Activité existante_                    |     | Non | Oui | Oui | Oui | Oui | Oui |
| _Activité active_                       |     |     | Non | Oui | Oui | Oui | Oui |
| _Tâche fournie_                         |     |     |     | Non | Oui | Oui | Oui |
| _Tâche existante_                       |     |     |     |     | Non | Oui | Oui |
| _Tâche active_                          |     |     |     |     |     | Non | Oui |
| **Postconditions**                      |     |     |     |     |     |     |     |
| _Affichage du temps passé sur la tâche_ | Non | Non | Non | Non | Non | Non | Oui |
| _Message d'erreur_                      | Oui | Oui | Oui | Oui | Oui | Oui | Non |

---

</details>

<details>
<summary>Calculer le temps passé par un développeur</summary>

### **Calculer le temps passé par un développeur**

> SHOULD

#### Préconditions:

- Développeur fourni
- Développeur existant

#### Postconditions:

- Affichage du temps passé par le développeur

#### Scénario:

- Saisie du développeur
- Affichage du temps passé par le développeur

#### Table de decision:

| Préconditions                         | 1   | 2   | 3   |
| ------------------------------------- | --- | --- | --- |
| _Développeur fourni_                  | Non | Oui | Oui |
| _Développeur existant_                |     | Non | Oui |
| **Postconditions**                    |     |     |     |
| _Affichage du temps passé par le dev_ | Non | Non | Oui |
| _Message d'erreur_                    | Oui | Oui | Non |

---

</details>

## Tâches de gestion des labels :

<details>
<summary>Créer un label</summary>

### **Créer un label**

> SHOULD

#### Préconditions:

- Label fourni
- Id fourni
- Id unique

#### Postconditions:

- Création du label
- Message de confirmation

#### Scénario:

- Saisie du label
- Saisie de l'id
- Création du label

#### Table de decision:

| Préconditions       | 1   | 2   | 3   | 4   |
| ------------------- | --- | --- | --- | --- |
| _Label fourni_      | Non | Oui | Oui | Oui |
| _Id fourni_         |     | Non | Oui | Oui |
| _Id unique_         |     |     | Non | Oui |
| **Postconditions**  |     |     |     |     |
| _Création du label_ | Non | Non | Non | Oui |
| _Message d'erreur_  | Oui | Oui | Oui | Non |

---

</details>

<details>
<summary>Ajouter un label à une activité</summary>

### **Ajouter un label à une activité**

> SHOULD

#### Préconditions:

- Activité fournie
- Activité existante
- Activité active
- Label fourni
- Label existant

#### Postconditions:

- Ajout du label à l'activité

#### Scénario:

- Saisie de l'activité
- Saisie du label
- Ajout du label à l'activité

#### Table de decision:

| Préconditions                 | 1   | 2   | 3   | 4   | 5   | 6   |
| ----------------------------- | --- | --- | --- | --- | --- | --- |
| _Activité fournie_            | Non | Oui | Oui | Oui | Oui | Oui |
| _Activité existante_          |     | Non | Oui | Oui | Oui | Oui |
| _Activité active_             |     |     | Non | Oui | Oui | Oui |
| _Label fourni_                |     |     |     | Non | Oui | Oui |
| _Label existant_              |     |     |     |     | Non | Oui |
| **Postconditions**            |     |     |     |     |     |     |
| _Ajout du label à l'activité_ | Non | Non | Non | Non | Non | Oui |
| _Message d'erreur_            | Oui | Oui | Oui | Oui | Oui | Non |

---

</details>

<details>
<summary>Ajouter un label à une tâche</summary>

### **Ajouter un label à une tâche**

> SHOULD

#### Préconditions:

- Activité fournie
- Activité existante
- Activité active
- Tâche fournie
- Tâche existante
- Tâche active
- Label fourni
- Label existant

#### Postconditions:

- Ajout du label à la tâche

#### Scénario:

- Saisie de la tâche
- Saisie du label
- Ajout du label à la tâche

#### Table de decision:

| Préconditions               | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   |
| --------------------------- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| _Activité fournie_          | Non | Oui | Oui | Oui | Oui | Oui | Oui | Oui | Oui |
| _Activité existante_        |     | Non | Oui | Oui | Oui | Oui | Oui | Oui | Oui |
| _Activité active_           |     |     | Non | Oui | Oui | Oui | Oui | Oui | Oui |
| _Tâche fournie_             |     |     |     | Non | Oui | Oui | Oui | Oui | Oui |
| _Tâche existante_           |     |     |     |     | Non | Oui | Oui | Oui | Oui |
| _Tâche active_              |     |     |     |     |     | Non | Oui | Oui | Oui |
| _Label fourni_              |     |     |     |     |     |     | Non | Oui | Oui |
| _Label existant_            |     |     |     |     |     |     |     | Non | Oui |
| **Postconditions**          |     |     |     |     |     |     |     |     |     |
| _Ajout du label à la tâche_ | Non | Non | Non | Non | Non | Non | Non | Non | Oui |
| _Message d'erreur_          | Oui | Oui | Oui | Oui | Oui | Oui | Oui | Oui | Non |

---

</details>

## Tâches de restauration des éléments dans la corbeille :

<details>
<summary>Restaurer un développeur</summary>

### **Restaurer un développeur**

> SHOULD

#### Préconditions:

- Développeur fourni
- Développeur existant
- Développeur inactif

#### Postconditions:

- Restauration du développeur

#### Scénario:

- Saisie du développeur
- Restauration du développeur

#### Table de decision:

| Préconditions                 | 1   | 2   | 3   | 4   |
| ----------------------------- | --- | --- | --- | --- |
| _Développeur fourni_          | Non | Oui | Oui | Oui |
| _Développeur existant_        |     | Non | Oui | Oui |
| _Développeur inactif_         |     |     | Non | Oui |
| **Postconditions**            |     |     |     |     |
| _Restauration du développeur_ | Non | Non | Non | Oui |
| _Message d'erreur_            | Oui | Oui | Oui | Non |

---

</details>

<details>
<summary>Restaurer une activité</summary>

### **Restaurer une activité**

> SHOULD

#### Préconditions:

- Activité fournie
- Activité existante
- Activité inactive

#### Postconditions:

- Restauration de l'activité
- Restauration des tâches de l'activité
- Restauration des périodes des tâches de l'activité (si le développeur est actif)

#### Scénario:

- Saisie de l'activité
- Restauration de l'activité
- Restauration des tâches de l'activité
- Restauration des périodes des tâches de l'activité

#### Table de decision:

| Préconditions                | 1   | 2   | 3   | 4   |
| ---------------------------- | --- | --- | --- | --- |
| _Activité fournie_           | Non | Oui | Oui | Oui |
| _Activité existante_         |     | Non | Oui | Oui |
| _Activité inactive_          |     |     | Non | Oui |
| **Postconditions**           |     |     |     |     |
| _Restauration de l'activité_ | Non | Non | Non | Oui |
| _Restauration des tâches_    | Non | Non | Non | Oui |
| _Restauration des périodes_  | Non | Non | Non | Oui |
| _Message d'erreur_           | Oui | Oui | Oui | Non |

---

</details>

<details>
<summary>Restaurer une tâche</summary>

### **Restaurer une tâche**

> SHOULD

#### Préconditions:

- Activité fournie
- Activité existante
- Activité active
- Tâche fournie
- Tâche existante
- Tâche inactive

#### Postconditions:

- Restauration de la tâche
- Restauration des périodes de travail de la tâche (si le développeur est actif)

#### Scénario:

- Saisie de la tâche
- Restauration de la tâche
- Restauration des périodes de travail de la tâche

#### Table de decision:

| Préconditions               | 1   | 2   | 3   | 4   | 5   | 6   | 7   |
| --------------------------- | --- | --- | --- | --- | --- | --- | --- |
| _Activité fournie_          | Non | Oui | Oui | Oui | Oui | Oui | Oui |
| _Activité existante_        |     | Non | Oui | Oui | Oui | Oui | Oui |
| _Activité active_           |     |     | Non | Oui | Oui | Oui | Oui |
| _Tâche fournie_             |     |     |     | Non | Oui | Oui | Oui |
| _Tâche existante_           |     |     |     |     | Non | Oui | Oui |
| _Tâche inactive_            |     |     |     |     |     | Non | Oui |
| **Postconditions**          |     |     |     |     |     |     |     |
| _Restauration de la tâche_  | Non | Non | Non | Non | Non | Non | Oui |
| _Restauration des périodes_ | Non | Non | Non | Non | Non | Non | Oui |
| _Message d'erreur_          | Oui | Oui | Oui | Oui | Oui | Oui | Non |

---

</details>

<details>
<summary>Vider la corbeille</summary>

### **Vider la corbeille**

> SHOULD

#### Préconditions:

- Aucune

#### Postconditions:

- Vidage de la corbeille

#### Scénario:

- Vidage de la corbeille

#### Table de decision:

| Préconditions            | 1   |
| ------------------------ | --- |
| **Postconditions**       |     |
| _Vidage de la corbeille_ | Oui |

---

</details>
