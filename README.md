# *Serasa Web*

### 1. Produção

Para acessar a aplicação desenvolvida entre no site - [SERASA WEB](https://serasa-web.herokuapp.com).
 
O primeiro acesso poderá levar até 1 minuto para ser inicializado, quando inativo o servidor é desligado. 

Através do menu estará disponível para você navegar nas páginas abaixo.

#### [Ranking das empresas](https://serasa-web.herokuapp.com/ranking)
Nesta tela será listado todas as empresas com os dados de:
- **Código**
- **Nome**
- **Confiabilidade**

Podendo verificar a confiabilidade das empresas após inclusão de dados bancários.

#### [Inclusão de dados bancários](https://serasa-web.herokuapp.com/dados-bancarios/incluir)
Nesta tela é possível fazer o download do modelo de arquivo que o sistema aceita processar.
Clique em modelo.csv, ao concluir o download você pode editar no excel, caso não tenha disponível pode ser em outras ferramentas de texto.

É possivel encontrar as descrições para os campos à seguir:
- **Selecione a empresa:** Você deve escolher uma das empresas para que os dados bancários sejam processados.

- **Selecione o arquivo:** Você deve clicar dentro do campo e escolher um arquivo com a extensão .cvs conforme o modelo em seu computador.

Utilize o botão "Processar" para que as infromações sejam enviadas para o sistema efetuar os cálculos.

Ao finalizar o processamento com sucesso você será direcionado para a página de Listagem financeira por empresa.

#### [Listagem financeira por empresa](https://serasa-web.herokuapp.com/dados-bancarios/listar) 
Nesta tela será listado as empresas com todos os dados bancários importados para o sistema.
Para visualizar os dados bancários da empresa selecionada, clique sobre ela, para recolher as informações, basta clicar sobre ela novamente.

#### Processamento
Para ser processado uma linha do arquivo csv, é necessário que a coluna **Tipo** seja preenchida com o valor **DEBITO** ou **EMISSAO**.
Caso a coluna **Tipo** não for preenchida, a linha será ignorada.
Não será obrigatório o preenchimento de valores nas colunas **Descricao**, **Data** e **Valor**.

O cálculo desenvolvido para seguir os requisitos encontrou uma inconsistência quando efetuado testes que forçam diminuir a pontuação.
Exemplo: Se a pontuação é 22 e possuir um débito:
Computando o debito 1 -> 22 - 0,88 = 21,12. Sendo que "0,88" é 4% de 22.
Pontuação final é 22, ou seja a pontuação 22 é a pontuação mínima, levando em consideração o arredondadmento para cima a partir da primeira casa decimal.

### 2. Desenvolvimento

#### Instruções para rodar o sistema localmente
Obter e configurar os seguintes sistemas:
- **Java 11**
- **Maven 3.6.3.**
- **PostgreSQL 12.**

Faça download do programa no [Github](https://github.com/DhieniferBock/serasa-web)

Para conferir a conexão com o banco de dados abra o arquivo **/serasa-web/src/main/resources/application-dev.properties**

É necessário abrir em sua máquina o arquivo **/serasa-web/src/main/resources/application.properties** e editar a primeira linha para:

```
spring.profiles.active=dev
```

É necessário rodar o seguinte comando:

```
mvn clean install
```

Após a conclusão com BUILD SUCCESS, rodar o comando abaixo:

```
mvnw spring-boot:run
```

Para acessar o sistema após iniciar o spring boot, [clique aqui](http://localhost:8080/).