### **Mettre un développeur à la corbeille**

> Priorité : 2

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
| **Nombre de tests**                                       | 1   | 1   | 1   | 2   |

> Pour le cas `4`, il faut aussi tester un développeur sans période de travail.

#### Tests `5`:

> TODO
