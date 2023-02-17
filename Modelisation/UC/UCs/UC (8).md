### **Afficher les développeurs**

> Priorité : 1

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
| **Nombre de tests**                   | 1   | 1   | 2            | 2              | 2         |

> 2 tests pour les cas où le résultat renvoie une liste vide

#### Tests `8`:
