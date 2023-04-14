Ce fichier contient et contiendra des remarques de suivi sur votre
projet tant sur la modélisation que sur la programmation. Un nouveau
suivi est indiqué par une nouvelle section datée.

Certaines remarques demandent des actions de votre part, vous les
repérerez par une case à cocher.

- [x] Action (à réaliser)

Merci de nous indiquer que vous avez pris en compte la remarque en
cochant la case. N'hésitez pas à écrire dans ce fichier et à nous
exposer votre point de vue.

- [x] Action (réalisée)
  - RÉPONSE et éventuelles remarques de votre part,

**Les messages standardisés avec leur explication sont dans le dépôt
des exemples et plus particulièrement
[ici](https://gitlabens.imtbs-tsp.eu/enseignants.csc4102/csc4102-exemples/-/blob/main/Suivi/messages_pour_le_suivi.md)**

---

# Suivi du lun. 20 févr. 2023 20:50:37

Olivier Berger

- [x] Votre document de modélisation PDF doit être auto-porteur, sans avoir besoin d'ouvrir un autre document. Il ne nous est pas possible de gérer des cas particuliers, compte-tenu du nombre de projets à évaluer.

  Si je ne m'abuse vous avez mis le détail des cas d'utilisation dans "./Modelisation/UC/UseCasesDetails.pdf", que je regarde, exceptionnellement.

- [x] PREPOSTCOND-05-Précondition-incomplète **Oubli de correction sur le détails de UC, corrigée sur les diagrammes de séquence**

  - La précondition du cas d'utilisation est incomplète. Relisez le cahier des charges pour trouver les conditions manquantes. (inspirez-vous du format du document initalement fourni, notamment pour les précisions sur les conditions non-null ou non-vide sur les chaînes de caractères des données en entrée. C'est fastidieux mais nécessaire si on imagine que l'on doit pouvoir confier la réalisation du codage des tests de façon précise et non-ambigüe à une autre équipe)

- [x] Attention à la quantité de détail => quantité de travail pour plus tard. Si vous détaillez beaucoup de préconditions et post-confditions et faites les tables de décision pour toutes, vous risquez d'avoir à programmer beaucoup trop de choses pour le temps imparti pour le sprint 1. On cherche à être pragmatique : lever les ambiguités sur des cas d'utilisations difficiles à comprendre + démontrer qu'on a appris la méthodologie... pas plus que nécessaire. En principe, pour l'instant, on se limite au sprint1 (On fera des compléments utiles en cours pour la partie des spécifications du sprint2 en temps venu)

- [x] Attention à l'utilisation d'éléments issus du domaine de la programmation, et au fait de sur-spécifier. Essayez de coller au maximum au contenu du cahier des charges. Est-ce utile de penser qu'on va devoir réaliser un affichage avec différents critères de tri ? Attention à la charge de travail induite pour le module qui doit nécessairement être bornée. Certes, il peut être frustrant de brider votre créativité, mais nous avons en principe suffisamment de travail devant nous, si on se limite à ce qui est demandé ou nécessaire.

Bon courage.

P.S.: je travaille depuis le TGV, donc avec une connectivité limitée. J'espère que ça n'aura pas d'incidence sur la récupération de votre dernière version. Avec mes excuses si c'était le cas.

---

# Suivi du mer. 08 mars 2023 21:04:59

Olivier Berger

## Diagramme de classes

- [x] DIAGCLAS-16-Nom-association
  - Une ou plusieurs associations sont mal nommées. Les noms des associations sont des expressions verbales avec verbe dans la forme active et à la troisième personne du singulier. ("créer" ? A priori, la façade connaît les objet mais pas besoin de nommer ces associations)
- [x] DIAGCLA-31-Pb-Navigabilité

  - Une ou plusieurs navigabilités posent problème : des choix de navigabilité qui pourraient vous bloquer dans la suite de votre travail. La navigabilité contraint le parcours du graphe de classes. Par défaut, et c’est classiquement le cas en conception préliminaire, les associations sont bidirectionnelles et peuvent être parcourues dans les deux sens. Lorsque l’association est contrainte pour devenir unidirectionnelle, la navigabilité qui reste possible est spécifiée par une flèche (on ne peut pas naviguer contre une flèche).

- [x] DIAGCLAS-10-Erreur-multiplicité
  - Pour la réalisation du Sprint, une ou plusieurs multiplicités ne sont pas correctes. (Est-ce qu'un développeur n'intervient que dans une seule période de travail ?)

## Diagrammes de séquence

- [x] DIAGSEQ-26-pb-recherche-participant

  - Un ou plusieurs recherchent d'objet pour être des participants ne sont pas modélisées correctement : par exemple, on ne cherche pas un objet en interrogeant un objet du même type pour trouver un autre objet ; d'ailleurs d'où vient le participant qui est interrogé ? Autre exemple, oubli de récupérer dans une variable globale l'objet trouvé (message « chercherClient(id) » au lieu du message « c = chercherClient(id) : Client), ce qui empêche d'utiliser la référence retournée pour par exemple nommer le participant trouvé.

- [x] DIAGSEQ-25-message-création-participant

  - Un ou plusieurs messages qui créent des objets ne respectent pas la convention de nommage UML qui veut que ces messages soient nommés « `create` ». Par exemple, on ne modélise pas que pour JAVA, donc on ne met pas des noms de constructeur, mais `create`.

- [x] Dans Création d'une tâche plusieurs soucis (dont celui-ci-dessus). Je déroule l'exécution telle qu'on la lit et vous donne les remarques :

* nom, id, et activite sont des arguments de l'appel à la méthode CreeTache initiale de la façade => ce sont des chaines de caractères. Attention au nommage, car ici "activite" est une chaine, plus loin on a "activité"... c'est un détail, mais autant être très précis
* plus tard on va avoir un appel à chercherActivité() qui renvoit une activité, mais on semble nommer cette variable de type Activité "existante"... qui semblerait plutôt être un booléen ... pourquoi ne pas le nommer "a" plutôt que "existante"... ou nommer "activité" idActivité, et activité pour la valeur de retour de chercherActivité()
* attention, les méthodes sont nommées sans capitale au premier caractère : chercherActivité au lieu de ChercherActivité. Encore des détails... mais c'est la norme.
* ChercherTâche() renvoit une Tâche ("unique") mais plus tard vous testez si "unique" prend une valeur booléenne... tester plutôt null, alors ?
* Comment s'appelle la tâche créée ?
* Qu'en fait-on après l'avoir créée... faut-il l'associer avec son activité ("a"), qui doit alors apparaître comme participant au diagramme... Cela doit correspondre à la post-condition qu'on a spécifiée (et que vous devrez donc mettre à jour en conséquence, car elle semble ne pas parler de l'activité)

En fait ça doit ce lire comme du code, et il faut pas mal de rigueur, comme quand on programme, sur la syntaxe, les typages, etc.

- [x] À quoi correspond l'envoi du message ActivitéActive() de la façade vers elle-même... au niveau synytaxique, ne serait-ce pas plutôt un envoi de message à l'instance "existante/a" de type activité ? "dis, l'activité, est-tu active ?"... mais au niveau sémantique : qu'est-ce qu'une activité active ... pas dans la corbeille ?

Au final : la façon de constituer les diagrammes, les sections du document pose problème : le PDF pourrait utilement permettre le copier-coller autant que possible pour faciliter le travail des équipes qui le récupèrent et en dérivent d'autres choses comme le code ? ou bien il faut aller chercher ça ailleurs dans le source qui a servi à le fabriquer ? Je vous parlerai du principe KISS (Keep It Simple Stupid) si on a le temps ... Et surtout le format d'UML sans l'outil qu'on préconise me laisse entrevoir des incohérences entre diagramme de classe et diagrammes de séquences... Modelio pourrait aider, j'ai l'impression :-/

J'arrête la ma revue faute de plus de temps. Bon courage pour la suite.

---

# Suivi du mer. 15 mars 2023 16:47:00

Olivier Berger

## Diagrammes de séquence

- [x] Dans le diagramme de "création d'une tâche", l'activité mériterait d'être identifiée dans le cartouche au-dessus de la ligne de vie, en tant que "activite:Activité"
- [x] L'appel au create() de tache devrait prendre activité en argument, et non l'identifiant de l'activité, maintenant qu'on connaît activité. Sinon, le constructeur de tache ne peut invoquer l'appel à ajouterTache() / envoyer le message correspondant

### Invariants

- [x] INV-03-Invariant-à-compléter
  - L'invariant contient en général des termes sur l'ensemble des attributs : par exemple telle référence non `null`, telle chaîne de caractères non `null` et non vide, etc. Cela peut paraître rébarbatif, mais c'est très important lors de la programmation : nous utilisons le principe [_fail fast_](https://en.wikipedia.org/wiki/Fail-fast) et donc détectons par exemple les erreurs de déréférencement le plus tôt possible.
  - Autre exemple, il est important d'indiquer dans quelles conditions les attributs qui traduisent les associations sont `null`. (qu'est-ce qui matérialise le fait d'être dans l'état Dans la Corbeille ou en fonctionnement ?)

### 6.1. Table de décision des tests unitaires

- [x] TABLEDECTU-07-Incohérence-pré-post-conditions-avec-conception
  - Les attributs utilisés dans les pré-condition et post-condition des tables de décsion doivent correspondre aux arguments des prototypes des méthodes et/ou aux attributs de la conception (diagramme de classes et diagrammes de séquence) (quid des périodes de travail après l'appel aux constructeurs ?)

Remarque additionnelle : à quoi correspond l'attribut "actif" de Développeur... est-ce lié à l'état "EnFonctionnement" (donc pas à la Corbeille) ? Est-ce lié à l'association entre Développeur et Corbeille dans le diagramme de classe ? J'ai peur d'une certaine redondance, ici... Dans tous les cas, ce n'est pas très clair de décoder la table de décision du test unitaire de la "Mét_H_ode mettreALaCorbeille() de la classe Développeur" : table pas formée avec la diagonale habituelle...

- [x] TABLEDECTU-03-Incohérence-prototype
  - La préconditon de la table de décision d'une opération est exprimée avec les arguments de l'opération et la postcondition est exprimée avec les attributs de la classe. Vérifiez la cohérence. (dansle diagramme de séquence, vérifiez ce que vous avez spécifié (modulo les remarques ci-dessus) au niveau des arguments du create() de Tâche)

Vous avez bien avancé. Keep up the good work.

---

# Suivi du mar. 21 mars 2023 22:39:07

Olivier Berger

- [x] Problème de continuité de l'historique Git : je me retrouve à devoir, pour la deuxième fois, repositionner ma branche sprint1 sur la nouvelle branche sprint1 que vous avez créée, sans qu'il y ait de continuité entre les deux... c'est loin d'être optimal. Je ne sais pas exactement comment vous vous y prenez, mais, normalement, les branches devraient rester sur un même chemin avec des merges vers la branche, mais sans la transplanter (abus de rebase ?) ailleurs :-/ Il faudrait peut-être tirer cela au clair... Néanmoins, je m'adapte et ferai le suivi sur la nouvelle branche sprint1.

---

lun. 03 avril 2023 16:15:38
Denis Conan
Voici le résultat de l'évaluation très détaillée de votre travail
durant le sprint 1, faite par le coordinateur du module. Vous verrez
qu'il y a quelques faiblesses, notamment sur le découplage entre
objets dans la programmation des méthodes des cas d'utilisation,
visant à améliorer la qualité de votre code. Vous êtes bien partis
pour entreprendre le sprint 2. Bon courage pour la suite.

---

Pour rappel, vous trouverez les explications sur les messages à l'adresse suivante :
https://gitlabens.imtbs-tsp.eu/enseignants.csc4102/csc4102-exemples/-/blob/main/Suivi/messages_pour_le_suivi.md

# Évaluation du logiciel livré à la fin du Sprint 1

- Vous n'utilisez pas Modelio. Avez-vous valider avec vos encadrants ?
  Vous prenez le risque d'erreur de notation : par exemple, les
  visibilités dans le diagramme de classes

## Modélisation du logiciel

### Spécification et préparation des tests de validation

#### Diagrammes de cas d'utilisation = a

- Sprint 2 non regardé

#### Préconditions et postconditions = a

Cas d'utilisation « ajouter une tâche » : ok

- [x] PREPOSTCOND-02-Compréhension-étude-de-cas : qu'est-ce que « nom
      fourni non null et non vide » ?

- attention au vocabulaire : activité active

> Prise en compte de la remarque : passage du terme actif en `enFonctionnement`, en place dans le code mais pas dans la modélisation.

Cas d'utilisation « mettre à la corbeille une tâche » :

Cas d'utilisation « mettre à la corbeille un développeur » : ok

#### Tables de décision des tests de validation = a-

Cas d'utilisation « ajouter une tâche » : ok

Cas d'utilisation « mettre à la corbeille une tâche » :

- [x] TABLEDECTV-06-Erreur-nombre-de-tests : test 5, avec ou sans période

Cas d'utilisation « mettre à la corbeille un développeur » :

- [x] TABLEDECTV-06-Erreur-nombre-de-tests : test 3, avec ou sans période

### Conception préliminaire

#### Diagramme de classes = b+

- [x] DIAGCLAS-02-Compréhension-étude-de-cas : le cahier des charges
  n'utilise pas l'adjectif « actif »

- [x] DIAGCLAS-19-Erreur-notation-navigabilité : par exemple entre
  Activité et Tâche

- [x] DIAGCLAS-10-Erreur-multiplicité : min 1 de ElementJetable vers Corbeille

- [x] DIAGCLAS-10-Erreur-multiplicité : max \* de PériodeDeTravail vers
  Tâche et vers Développeur

- qu'est-ce que la figure 10 ? quelle est son rôle ?

#### Diagrammes de séquence (2 parmi) = b

Cas d'utilisation « ajouter une tâche » :

- [x] DIAGSEQ-36-Pb-syntaxe-message-appel-opération : revoir le cours

- vous faites tout à partir de la façade et vous dégradez votre modèle
  en des structures de données avec du procédural

- [x] la façade n'ayant pas d'association vers Tâche, la création à
      partir de la façade est maladroite
  > Vu avec Denis Conan : la création de Tâche est faite par la façade
  > afin de gérer le système d'event.
  > Diagramme de séquences à revoir.

Cas d'utilisation « mettre à la corbeille une tâche » : à faire

Cas d'utilisation « mettre à la corbeille un développeur » : ou à faire

### Conception détaillée et préparation des tests unitaires

#### Raffinement du diagramme de classes = a

- [x] RAFDIAGCLAS-10-Visibilité-attribut-dans-diagramme-de-classes
- [x] RAFDIAGCLAS-12-Visibilité-méthode-dans-diagramme-de-classes

- [x] RAFDIAGCLAS-04-Pas-d-attributs-d-association-dans-diagramme-de-classes

#### Diagramme de machine à états et invariant = a-

- [x] DIAGMACHETATS-02-Compréhension-étude-de-cas : la terminologie
      « objet actif » ne correspond pas à l'étude de cas et peut signifier
      autre chose (actif = avec un thread)
  - sous prétexte de ne pas vouloir de Modelio, vous ré-écrivez le
    cahier des charges... quand vous arrêterez-vous ?

#### Tables de décision de tests unitaires = a-

- [x] Tâche::constructeur : comment pouvez-vous vérifier« activité
      active » dans le constructeur de Tâche ?

## Programmation

### Utilisation des outils de programmation

#### Module Maven et tests avec JUnit = a

### Programmation de la solution

#### Classes du diagramme de classes avec leurs attributs = a

- Une interface se justifie soit lorsque l'on peut avoir plusieurs
  réalisation soit pour des s'interfacer avec un autre système ; en
  l'état, je ne vois ni l'une ni l'autre des raisons
  - c'est cher payé pour avoir des mocked objects ;-)

> Vu avec Denis Conan, principe de Clean Code.

- [x] JAVA-03-Cohérence-avec-diagramme-de-classes : avec
      Corbeille::public static Corbeille getInstance(), Corbeille est un
      singleton accessible de partout (de toutes les instances) ; ce n'est
      pas ce que dit le diagramme de classes et cela vous gênera pour les
      tests

> Correction en utilisant un système d'event et de référence dirrect vers la corbeille.

- [x] Developpeur, ArrayList<IPeriodeDeTravail> : préférable de mettre
      l'interface List

- ElementJetable::mettreALaCorbeille : ne correspond pas à ce qui serait
  fait dans les diag. de séquence de mise à la corbeille

  > Diagramme de séquence à faire.

- PeriodeDeTravail :
  //! Because of a bug inside the IntervalleInstants class,
  //! we need to check if `debut` is before `fin` manually.
  - où ? merci de remonter le pb
  - ce que vous écrivez réalise-t-il la vérification de non-intersection ?

> Mail envoyé

- [x] JAVA-25-Pb-accès-objet-intérieur : pb sur getDeveloppeur, etc.

> Ajout d'un decorateur pour signifier le `@Deprecated` de ces méthodes.

- [x] Utils::printCollection : cf. cours sur les Stream pour faire cela
      plus élégamment

#### Méthodes des cas d'utilisation de base = a

Cas d'utilisation « ajouter une tâche » :

- [x] JAVA-04-Cohérence-avec-diagrammes-de-séquence :
      Activite::ajouterTache, à quoi correspond if (!tache.estActif())
      alors que c'est le cas d'utilisation d'ajout d'une tâche, donc par
      construction « pas dans la corbeille »

- [x] dans Activite::ajouterTache, if (tache.getActivite() != this) :
      voilà typiquement pourquoi tout faire dans la façade amène à écrire
      du drôle de code
  - il est préférable de laisser l'activité créer la tâche elle-même

> Vu avec Denis Conan, on préfère laisser la création de la tâche à la façade pour peremttre la mise en place
> d'un système d'event.

Cas d'utilisation « mettre à la corbeille une tâche » :

- [x] JAVA-19-Pb-utilisation-exception : dans la façade, lorsque l'on
      vérifie les précondition, lever des exceptions sous contrôle

- utilisation du singleton...

Cas d'utilisation « mettre à la corbeille un développeur » :

- [x] idem

### Cohérence entre le code et le modèle

#### Cohérences du code avec le diagramme de classes = a-

#### Cohérences du code avec les diagrammes de séquence de base = a-

### Programmation et exécution des tests de validation et unitaires

#### Tests de validation des cas d'utilisation = b+

Cas d'utilisation « ajouter une tâche » :

- [x] JAVATEST-05-Pb-nommage-méthode-de-test : Test6 doit être nommé Test7Puis6

Cas d'utilisation « mettre à la corbeille une tâche » :

- [x] JAVATEST-08-Test-validation-sans-accès-à-l-intérieur : pb sur
      Corbeille.getInstance()

  - ce n'est plus un test de validation avec cet appel

- [x] JAVATEST-05-Test-manquant : avec période de travail

Cas d'utilisation « mettre à la corbeille un développeur » :

- [x] JAVATEST-05-Test-manquant : avec période de travail

#### Tests unitaires de méthodes d'une classe = a
