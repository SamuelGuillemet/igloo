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

#### Postconditions:

- Création d'une tâche
- Message de confirmation

#### Scénario:

- Saisie du nom
- Saisie de l'id
- Saisie de l'activité
- Création de la tâche
- Message de confirmation

---

</details>

<details>
<summary>Créer une période de travail</summary>

### **Créer une période de travail**

> MUST

#### Préconditions:

- Début fourni
- Fin fournie
- Tâche fournie
- Développeur fourni
- Développeur actif
- Tâche active
- Début < Fin
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

---

</details>

## Tâches de mise à la corbeille :

<details>
<summary>Mettre un développeur à la corbeille</summary>

### **Mettre un développeur à la corbeille**

> MUST

#### Préconditions:

- Développeur fourni
- Développeur actif

#### Postconditions:

- Développeur mis à la corbeille
- Périodes de travail du développeur mises à la corbeille
- Message de confirmation

#### Scénario:

- Saisie du développeur
- Mise à la corbeille du développeur
- Message de confirmation

---

</details>

<details>
<summary>Mettre une activité à la corbeille</summary>

### **Mettre une activité à la corbeille**

> MUST

#### Préconditions:

- Activité fournie
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

---

</details>

<details>
<summary>Mettre une tâche à la corbeille</summary>

### **Mettre une tâche à la corbeille**

> MUST

#### Préconditions:

- Tâche fournie
- Tâche active

#### Postconditions:

- Tâche mise à la corbeille
- Périodes de travail de la tâche mises à la corbeille
- Message de confirmation

#### Scénario:

- Saisie de la tâche
- Mise à la corbeille de la tâche
- Message de confirmation

---

</details>

<details>
<summary>Mettre une période de travail à la corbeille</summary>

### **Mettre une période de travail à la corbeille**

> MUST

#### Préconditions:

- Période de travail fournie

#### Postconditions:

- Période de travail mise à la corbeille
- Message de confirmation

#### Scénario:

- Saisie de la période de travail
- Mise à la corbeille de la période de travail
- Message de confirmation

---

</details>

## Tâches d'affichage :

<details>
<summary>Afficher les développeurs</summary>

### **Afficher les développeurs**

> MUST

#### Préconditions:

- Aucune

#### Postconditions:

- Affichage des développeurs

#### Scénario:

- Affichage des développeurs

---

</details>

<details>
<summary>Afficher les activités</summary>

### **Afficher les activités**

> MAY

#### Préconditions:

- Aucune

#### Postconditions:

- Affichage des activités

#### Scénario:

- Affichage des activités

---

</details>

<details>
<summary>Afficher les tâches</summary>

### **Afficher les tâches d'une activité**

> MAY

#### Préconditions:

- Activité fournie

#### Postconditions:

- Affichage des tâches de l'activité

#### Scénario:

- Saisie de l'activité
- Affichage des tâches de l'activité

---

</details>

<details>
<summary>Afficher les périodes de travail</summary>

### **Afficher les périodes de travail**

> SHOULD

#### Préconditions:

- Type de période de travail fourni (active ou inactive)

#### Postconditions:

- Affichage des périodes de travail

#### Scénario:

- Saisie du type de période de travail
- Affichage des périodes de travail

---

</details>

<details>
<summary>Afficher les périodes de travail d'un développeur</summary>

### **Afficher les périodes de travail d'un développeur**

> SHOULD

#### Préconditions:

- Développeur fourni

#### Postconditions:

- Affichage des périodes de travail du développeur

#### Scénario:

- Saisie du développeur
- Affichage des périodes de travail du développeur

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

---

</details>

<details>
<summary>Calculer le temps passé sur une activité</summary>

### **Calculer le temps passé sur une activité**

> SHOULD

#### Préconditions:

- Activité fournie
- Activité active

#### Postconditions:

- Affichage du temps passé sur l'activité

#### Scénario:

- Saisie de l'activité
- Affichage du temps passé sur l'activité

---

</details>

<details>
<summary>Calculer le temps passé sur une tâche</summary>

### **Calculer le temps passé sur une tâche**

> SHOULD

#### Préconditions:

- Tâche fournie
- Tâche active

#### Postconditions:

- Affichage du temps passé sur la tâche

#### Scénario:

- Saisie de la tâche
- Affichage du temps passé sur la tâche

---

</details>

<details>
<summary>Calculer le temps passé par un développeur</summary>

### **Calculer le temps passé par un développeur**

> SHOULD

#### Préconditions:

- Développeur fourni

#### Postconditions:

- Affichage du temps passé par le développeur

#### Scénario:

- Saisie du développeur
- Affichage du temps passé par le développeur

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

---

</details>

<details>
<summary>Ajouter un label à une activité</summary>

### **Ajouter un label à une activité**

> SHOULD

#### Préconditions:

- Activité fournie
- Label fourni
- Activité active

#### Postconditions:

- Ajout du label à l'activité

#### Scénario:

- Saisie de l'activité
- Saisie du label
- Ajout du label à l'activité

---

</details>

<details>
<summary>Ajouter un label à une tâche</summary>

### **Ajouter un label à une tâche**

> SHOULD

#### Préconditions:

- Tâche fournie
- Label fourni
- Tâche active

#### Postconditions:

- Ajout du label à la tâche

#### Scénario:

- Saisie de la tâche
- Saisie du label
- Ajout du label à la tâche

---

</details>

## Tâches de restauration des éléments dans la corbeille :

<details>
<summary>Restaurer les éléments de la corbeille</summary>

### **Restaurer les éléments de la corbeille**

> SHOULD

#### Préconditions:

- Aucune

#### Postconditions:

- Restauration des éléments de la corbeille

#### Scénario:

- Restauration des éléments de la corbeille

---

</details>

<details>
<summary>Restaurer un développeur</summary>

### **Restaurer un développeur**

> SHOULD

#### Préconditions:

- Développeur fourni
- Développeur inactif

#### Postconditions:

- Restauration du développeur

#### Scénario:

- Saisie du développeur
- Restauration du développeur

---

</details>

<details>
<summary>Restaurer une activité</summary>

### **Restaurer une activité**

> SHOULD

#### Préconditions:

- Activité fournie
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

---

</details>

<details>
<summary>Restaurer une tâche</summary>

### **Restaurer une tâche**

> SHOULD

#### Préconditions:

- Tâche fournie
- Tâche inactive

#### Postconditions:

- Restauration de la tâche
- Restauration des périodes de travail de la tâche (si le développeur est actif)

#### Scénario:

- Saisie de la tâche
- Restauration de la tâche
- Restauration des périodes de travail de la tâche

---

</details>

<details>
<summary>Restaurer une période de travail</summary>

### **Restaurer une période de travail**

> MAY

#### Préconditions:

- Période de travail fournie
- Période de travail inactive

#### Postconditions:

- Restauration de la période de travail (si le développeur est actif)

#### Scénario:

- Saisie de la période de travail
- Restauration de la période de travail

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

---

</details>
