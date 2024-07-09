# RocketSeat NLW 2024 - Trilha Java
## Plann.er REST API

### Aula 1
1. _(sugestão)_ Criação do projeto no GitHub
    - branch por aula (aula-1, aula-2, etc) 
    - _main_ com projeto final
2. Criação do projeto com [Spring Initializr](https://start.spring.io/)
    - focar inicialmente na API REST e implementar os endpoints sem aplicar nenhuma regra de negócio inicialmente
    - utilizar @JsonProperty e não perder tempo convertendo LocalDate  
    - Trip não é DTO!!! Eu não acho correto ensinar @Entity na camada REST
        - passou despercebido, mas Trip não retorna o mesmo JSON que TripRequestPayload
        - ótima oportunidade para mostrar @JsonUnwrapped (TripDetails)
3. _(sugestão)_ Aproveitando o foco na API REST, aplicar validação em CreateTripRequest.
    - talvez eu esteja me adiantando caso esteja nas próximas aulas
4. _(sugestão)_ Modelar a camada de negócios com [Data-Oriented Programming](https://www.youtube.com/watch?v=zn4neparqUQ)
   - isso permitiria deixar o banco de dados pro final (concordo que isso pode diminuir o engajamento com o público mais "afoito")
   - ao invés de criar ParticipantService#registerParticipantsToEvent, implementar essa funcionalidade (futuramente) como decorator de TripRepository 