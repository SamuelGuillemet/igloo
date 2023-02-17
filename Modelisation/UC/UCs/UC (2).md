### **Créer une activité**

> Priorité : 1

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
- Vérification de l'unicité de l'id
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
| **Nombre de tests**       | 1   | 1   | 1   | 1   |

#### Tests `4`:

> TODO

#### Diagramme de séquence:

<div hidden>

```plantuml
@startuml UC2

title Création d'une activité

skinparam style strictuml
skinparam sequenceArrowThickness 2
skinparam backgroundColor #EEEBDC

actor Utilisateur #Orange

== Interface utilisateur ==

Utilisateur -> CLI : Demande la création d'une activité
activate CLI
CLI --> Utilisateur ++ : Demande le nom
Utilisateur -> CLI : Saisit le nom
CLI --> Utilisateur : Demande l'id
Utilisateur -> CLI -- : Saisit l'id

== Création de l'activité ==

CLI -> SuiPro : CreeActivite(nom, id)
activate SuiPro #Gray
deactivate CLI
SuiPro -> SuiPro : Vérifie l'unicité de l'id
group#Gold #LightGreen Id Unique ? [true]
SuiPro -> Activite ** : Cree(nom, id)
activate Activite
Activite -> SuiPro -- : Confirme la création
SuiPro -[#Green]> CLI ++ : Envoie le message de confirmation
else #Pink false
SuiPro -[#Red]> CLI : Envoie un message d'erreur
deactivate SuiPro
end

CLI --> Utilisateur : Affiche le résultat de la demande de création

@enduml
```

</div>

![UC2](../Diagrammes/Seq/UC2.png)
