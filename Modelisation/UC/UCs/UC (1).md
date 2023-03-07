[Go back](../UseCase.md)

---

### **Créer un développeur**

> Priorité : 1

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
- Vérification de l'unicité de l'alias
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
| **Nombre de tests**         | 1   | 1   | 1   | 1   | 1   |

#### Tests `5`

> TODO

#### Diagramme de séquence:

<div hidden>

```plantuml
@startuml UC1

!include diag_seq_template.iuml

!$schema = {
    "entity": "Développeur",
    "name": "Création d'un développeur",
    "create": "CreeDeveloppeur(nom, prenom, alias)",
    "requirements": [
        "nom",
        "prénom",
        "alias"
    ],
    "preconditions": [
        {
            "bool": "unique",
            "condition": "ChercherDeveloppeur(alias)",
            "entity": "Développeur"
        }
    ]
}

Draw($schema)

@enduml

```

</div>

![Créer un développeur](/Modelisation/UC/Diagrammes/Seq/UC1.png)
