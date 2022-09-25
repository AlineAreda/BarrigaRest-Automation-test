# BarrigaRest - Automação de Testes API

🚀 Projeto de automação de testes  da API  de controle financeiro BarrigaRest 


## 🛠️ Construído com

JAVA
RestAssured
JUnit


### 🔩 Cenários de testes da API

Rota:  [http://barrigarest.wcaquino.me](http://barrigarest.wcaquino.me/)

### Autenticação
- [X] Não deve acessar API sem token

### Contas
- [X] Não deve acessar api sem token 
- [X] Deve inserir conta com sucesso
- [X] Deve alterar conta com sucesso  
- [X] Não deve  inserir conta com nome repetido  
- [X] Não deve remover conta com movimentação  

### Movimentação
- [X] Deve inserir movimentação com sucesso 
- [X] Deve validar campos obrigatórios na movimentação  
- [X] Não deve cadastrar transação  com data futura  
- [X] Deve remover  movimentação  

### Saldo
- [X] Deve calcular saldo das contas  


## ⚙️ Executando os testes

```
Executar os testes através da Suite.class
```
