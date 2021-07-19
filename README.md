# Example-API-CRUD-SpringDataJPA

Exemplo de uma API utilizando SpringDataJPA.

É necessário adicionar o usuário, senha e url do seu Banco de dados.

- Listando produtos:  
GET /produtos/ HTTP/1.1  
Host: localhost:8080

- Cadastrando produtos:  
POST /produtos/ HTTP/1.1  
Host: localhost:8080  
Content-Type: application/json  

- Buscando produtos por ID:  
GET /produtos/1 HTTP/1.1  
Host: localhost:8080  

- Buscando produtos por nome:  
GET /produtos/por-nome?nome=a HTTP/1.1  
Host: localhost:8080  

- Atualizando produtos:  
PUT /produtos/1 HTTP/1.1  
Host: localhost:8080  
Content-Type: application/json  

- Deletando produtos:  
DELETE /produtos/1 HTTP/1.1  
Host: localhost:8080  
