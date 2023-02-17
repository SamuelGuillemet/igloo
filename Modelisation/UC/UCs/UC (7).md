### **Mettre une tâche à la corbeille**

> Priorité : 2

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
| **Nombre de tests**                                    | 1   | 1   | 1   | 2   |

> Pour le cas `4` on fait 2 tests pour le cas ou il n'existe pas de période de travail actives pour la tâche

#### Tests `5`:
