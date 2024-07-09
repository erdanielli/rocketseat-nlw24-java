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
    - Talvez eu esteja me adiantando caso esteja nas próximas aulas
