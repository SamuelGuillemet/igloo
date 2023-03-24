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
