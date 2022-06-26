Um Simples crud com relacionamento utilizando o conceito de microserviços.


```
POST: Save People

{
    "name": "Warley",
    "cpf": "036541657",
    "idade": 28
}
```

```mermaid
graph TD;
    PeopleMS-->PeopleDB;
```
