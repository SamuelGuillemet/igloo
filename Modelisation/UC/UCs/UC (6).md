### **Mettre une activité à la corbeille**

> Priorité : 2

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
| **Nombre de tests**                                   | 1   | 1   | 1   | 3   |

> Pour le cas `4` il faut 3 tests pour tester les 3 postconditions, donc les cas où il n'existe pas de tâches active pour l'activité, ni de périodes de travail actvies pour les tâches.

#### Tests `6`:
