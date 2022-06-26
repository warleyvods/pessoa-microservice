Here is a simple flow chart:

```mermaid
sequenceDiagram
    participant People-MS
    participant Address-MS
    People-MS->>Address-MS: POST, Object
    
    Note right of John: Rational thoughts <br/>prevail!
    John-->>Alice: Great!
    John->>Bob: How about you?
    Bob-->>John: Jolly good!
```
