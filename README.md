# RocketSeat NLW 2024 - Trilha Java
## Plann.er REST API

### DISCLAIMER

Como não uso LinkedIn, estou publicando os desafios aqui no GitHub. Por ter experiência no assunto (19 anos atuando 
profissionalmente com Java), estou aproveitando o caso de uso e implementando de uma forma que me agrada mais. 
Didaticamente pode ser que minha abordagem não seja das melhores, e de forma alguma estou sugerindo que o curso seja 
ruim. Pelo contrário, agradeço a iniciativa de oferecê-lo de forma gratuita. Minha única intenção é aprimorar meus 
conhecimentos ao mesmo tempo que passo meu feedback. 

### Aula 1

1. _(sugestão)_ Criação do projeto no GitHub:
    - branch por aula (aula-1, aula-2, etc);
    - _main_ com projeto final.
2. Criação do projeto com [Spring Initializr](https://start.spring.io/):
    - focar inicialmente na API REST e implementar os endpoints sem aplicar nenhuma regra de negócio inicialmente;
    - montar DTOs com @JsonProperty e evitar a nomenclatura fora do padrão;
    - @Entity não é DTO! Eu recomendo manter a API REST exclusivamente com DTOs (ou tipos padrão da JRE)
        - passou despercebido, mas Trip não retorna o mesmo JSON que TripRequestPayload
        - ótima oportunidade para propor um padrão de nomenclatura de DTOs (*Request)
        - de bônus dá pra aprofundar um pouco no Jackson (@JsonUnwrapped por exemplo)        
3. _(sugestão)_ Aproveitando o foco na API REST, aplicar validação em CreateTripRequest.
    - talvez eu esteja me adiantando e o assunto está nas aulas seguintes
4. _(sugestão)_ Modelar a camada de negócios com [Data-Oriented Programming](https://www.youtube.com/watch?v=zn4neparqUQ)
   - isso permitirá deixar o banco de dados pro final (concordo que isso pode diminuir o engajamento com o público mais "afoito")
   - abstrair repository/services com [Ports and Adapters](https://alistair.cockburn.us/hexagonal-architecture/)
       - Utilizar interface (port) com Spring Data já acopla com JPA (adapter) desde a definição. 
Dessa forma ela não pode ser exposta diretamente pro Controller.
       - Implementar com fake p/ demonstrar que o banco de dados é só um detalhe (polêmico!)       
   - ao invés de criar ParticipantService#registerParticipantsToEvent, implementar essa funcionalidade (futuramente) 
como [decorator](https://www.yegor256.com/2015/02/26/composable-decorators.html) de TripRepository (polêmico também!) 