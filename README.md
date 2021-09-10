# Hackaton_Genial_2021

What to install before running:

```
npm install mockserver
```

## Comment utiliser l'API
Le service `data-interface-service` a été créé pour gérer l'interface avec l'API. 

Pour recevoir des contraintes depuis le Core: +

D'abord, rajouter un élément de type `DataInterfaceService` dans le constructeur:
```typescript
constructor(private: dataInterface : DataInterfaceService) { }
```
Ensuite, appeler la fonction `fetchTimeConstraints()` en lui passant une callback:
```typescript
dataInterface.fetchTimeConstraints(this.onTimeConstraintsReceived);
```
Enfin, définir la callback:
```typescript
onTimeConstraintsReceived(constraints: [ConstraintTimeRoom]) {
    // handle constraints
    console.log(constraints);
  }
```
Le détail des interfaces à utiliser est dans `front/model/datastore/datamodel.js`.
