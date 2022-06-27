Um simples crud com relacionamento utilizando o conceito de microserviços.


POST: /api/people
```
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

GET: /api/people/1

```mermaid
graph TD;
    PeopleMS-->PeopleDB;
    PeopleMS-->AddressMS
    AddressMS-->AddressDB
    AddressDB-->AddressMS
    AddressMS-->CachePeople
    CachePeople-->PeopleMS
```

DELETE: /api/people/1

```mermaid
graph TD;
    PeopleMS-->PeopleDB;
    PeopleMS-->KafkaTopic
    KafkaTopic-->KafkaConsumer
    KafkaConsumer-->AddressMS
    AddressMS-->AddressDB
    AddressDB-->AddressMS
    AddressMS-->PeopleMS
```
