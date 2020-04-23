* **Observações**

   O objetivo deste projeto é cadastrar pautas e votar nas mesmas.
   

* **Dependências**

   <p>SDK Java11</p>
   <p>Instalar o docker compose</p>
   <p>Plugin lombok na IDE</p>
   <p>Banco de dados SQLSERVER</p> 

#### Entendendo a aplicação
* **1ª Iniciando**

    <p>Após você ter instalado o docker compose em seu pc, vamos precisar iniciar o servidor do kafka</p>
    <p>Na raiz deste projeto possui um arquivo chamado docker-compose.yml</p>
    <p>Navegue até esse arquivo por sua IDE ou linha de comando do seu sistema operacional</p>
    <p>Execute o comando a seguir</p>
    <p>(docker-compose up)</p>
    <p>Em seguida as imagens do docker vão baixar e se iniciar sozinhas</p>

* **2ª Iniciando a API** 

    <p>Execute a classe CooperativismoApplication.java nela esta o metodo main</p>
    
    Nossa API está no ar agora.
    
* **3ª Iniciando o consumidor**

    <p>Como solicitado de tarefa bonus, quando uma pauta é encerrada, a API dispara um evento para a fila do kafka.</p>
    <p>Na Raiz do projeto, existe uma pasta chamada kafka-client. Este projeto serve de consumidor do Kafka.</p>
    <p>Execute a classe Main.java, e pronto nosso consumidor já esta olhando para o kafka, quando alimentarmos o kafka com a Pauta encerrada, oberseve o console da IDE, os dados vão ir para lá.</p>
    
* **4ª Exemplos de requisições da API**

    <p>Com nossa API do ar, podemos iniciar as requisições.</p>
    
    <p>Metodo: POST</p>
    <p>url: http://localhost:8080/pauta/save</p>
    <p>body: {"nomePauta": "TESTE", "descricaoPauta": "DESCRIÇÃO TESTE", "expirarPauta": 1}</p>
    
    <p>Metodo: POST</p>
    <p>url: http://localhost:8080/voto/save</p>
    <p>body: {"nomePauta": "TESTE", "voto": "NAO", "cpf": "03633539092"}</p>
    
    
     "# CooperativismoTeste" 
"# CooperativismoAPI" 
