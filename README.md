# comexport

Roteiro de avaliação prática de Java 
Objetivo principal: Construir um projeto baseado na arquitetura  de microsserviços que possibilite um usuário realizar a compra de  um produto, porem enfatizando as operações de CRUD de um  usuário e funcionalidade de compra do produto. 
Modelo de dados proposto:

Regras para as informações da entidade Usuário: 
Cadastro: Durante o cadastro de um novo usuário, as seguintes  informações são obrigatórias de preenchimento: 
• Nome 
• E-mail (validar se formato do e-mail esta correto) • Data de nascimento 
Caso não sejam preenchidas corretamente as informações acima,  uma mensagem deve ser devolvida como resposta para API com o  respectivo código HTTP. 
O Campo enable deve ser criado como true. 
Alteração: A funcionalidade de alteração do usuário, não pode  permitir que o E-mail seja modificado. 
Consulta: A API de consulta deve permitir a consulta de usuários,  pelos seguintes campos: 
• E-mail 
• Nome (qualquer parte do nome) 
• Data de nascimento 
As consultas devem ser paginadas.
Regras para as informações da entidade Produto: 
Não é necessário construir APIs para cadastro de produtos, podem  ser inseridos via script. Abaixo seguem algumas sugestões.

Regras para efetuar a compra de um produto: 
API de Compra: A API que realiza a compra de um produto deve  receber os seguintes campos: 
• Id do usuário 
• Id do produto 
• Preço do produto 
• Canal de venda 
Caso não sejam preenchidas corretamente as informações acima,  uma mensagem deve ser devolvida como resposta para API com o  respectivo código HTTP. 
Observação: 
• O campo Canal de venda, será uma Enum contendo os  seguintes valores 
◦e_commerce 
◦loja_fisica 
◦parceiros 
• O campo status, será uma Enum que corresponde ao status  do pedido com as seguintes opções: 
◦aguardando_entrega 
◦ entregue 
◦ aguardando_retirada_parceiro 
Detalhamento das regras: 
Ao receber as informações preenchidas corretamente, um novo  registro deve ser inserido na tabela Orders. 
O campo Price deve ser preenchido de acordo com as seguintes  regras:
1. Se o canal de venda for e_commerce: O preço total do  pedido deve ser a soma do valor do produto cadastrado na  tabela Product acrecentado de 5,37% simulando o valor de  um frete. 
2. Se o canal de venda for loja_fisica, o preço do pedido deve  ser o mesmo valor cadastrado na tabela Product. 
3. Se o canal de venda for parceiros, o preço total do pedido ser  preço do produto (tabela Product) + taxa fixa no valor de  R$ 10,34. Com o resultado da soma da taxa fixa, acrescentar  o valor de 8.78% do valor do produto original cadastrado na  tabela Product 
O campo de status da tabela Orders, irá variar de acordo com o  canal de venda 
• loja_fisica: Status = entregue 
• parceiros: Status = aguardando_retirada_parceiro • e_commerce = aguardando_entrega 
Regra de implementação: 
Construir uma implementação core, que será responsavel por  receber as informaçoes do pedido e persistir as informações na  tabela orders. 
Construir outras implementações que atendam cada regra de cálculo do pedido, de acordo com o canal de venda. Essas  implementações devem chamar o serviço core responsável por  manter as informações de pedido. 
API de consulta de pedido: Construir uma API que consulte os  pedidos, possibilitando filtrar por qualquer campo da tabela  Orders.
Detalhes técnicos obrigatórios: O projeto deve ser com struído utilizando a seguinte stack de tecnologia: 
• Java 8 ou superior 
• SpringBoot 
• APIs devem ser construídas utilizando os princípios REST • Utilizar JUnit para para testes 
• Utilizar banco de dados mysql 
• Utilizar documentação das APIs via Swagger 
• Utilizar docker 
• Descrever no arquivo README.md do projeto como  realizar o start das APIs para realizar validação 
Forma de entrega: O código fonte deve ser disponibilizado em  um repositório publico no github, bitbucket ou gitlab. 
Detalhes técnicos opcionais: Estes detalhes são opcionais e não  terão relevância se os requisitos obrigatórios não forem atendidos • Construir uma projeto web SPA que integre com as APIs para  realização das operações de criação, consulta, atualização e  deleção. 
• Disponibilizar o projeto em uma plataforma cloud para  acessos (Ex. Heroku) 
• Testes realizado utilizando BDD 
• Efetuar deploy utilizando minikube/kubernates
