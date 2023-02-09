## Desafio Android

O desafio consiste em criar uma Wiki de Star Wars onde é mostrada uma lista de personagens e o usuário pode favoritar alguns deles.

A documentação das API’s que serão utilizadas no desafio estão disponíveis nos links abaixo:
- http://swapi.dev/
- http://docs.starwarsfavorites.apiary.io

## Lista de Personagens
Para obter os personagens, sua aplicação deverá utilizar o recurso people da Swapi (documentação disponível no topo do documento). A aplicação deve exibir todos os 87 personagens e permitir pesquisar o personagem peio nome. Sugerimos exibir as primeiras páginas enquanto carrega as outras, em um formato de scroll infinito.
A lista de itens deve exibir as seguintes informações:
- Nome [name]
- Altura [height]
- Gênero [gender]
- Peso [mass]

Obs: Os dados devem ser salvos em banco de dados local para acesso off-line e atualizados sempre que a teia for aberta.

## Detalhes do Personagem
Ao clicar em um litem da lista o seu app deve mostrar as informações abaixo:
- name
- height
- mass
- hair_color
- skin_color
- eye_color
- birth_year
- gender
- Nome do Planeta Natal
- Nome da Espécie
Obs: A busca peio nome do planeta e da espécie deve ser feita em paralelo.

## Favoritos
Na lista e nos detalhes deve ser possíveis adicionar e remover um personagem à sua lista de favoritos. Também deve ser possível filtrar quais personagens foram favoritos na lista principal.
O que iremos analisar:
- Organização do código;
- Aplicação de design patterns;
- Separação de módulos e componentes;
- Legibilidade;
