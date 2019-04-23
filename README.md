# iMusica

<p align="left">
  <img src="https://github.com/marcellocamara/iMusica/blob/master/extras/images/imusica.png" title="imusicacorp">
</p>

Projeto realizado como desafio técnico para a empresa [iMusicaCorp](https://www.imusica.com.br/).

<p align="center">
  <img src="https://github.com/marcellocamara/iMusica/blob/master/extras/gif/imusica.gif" width="280" title="Aplicação">
</p>

Um exemplar do aplicativo encontra-se disponível para download. Para baixá-lo, [clique aqui](https://raw.githubusercontent.com/marcellocamara/iMusica/master/extras/apk/imusica.apk). 

Para assistir o vídeo de demonstração da aplicação, [clique aqui](https://raw.githubusercontent.com/marcellocamara/iMusica/master/extras/video/imusica.mp4).

## Cache / Banco de dados

Para fazer a sessão do usuário no aplicativo, utilizou-se da ferramenta SharedPreferences.

Auth.xml
```
<map>
    <string name="email">marcellocamara@id.uff.br</string>
    <string name="name">Marcello Câmara</string>
    <string name="password">123456</string>
    <int name="id" value="1" />
</map>
```

Para salvar a escolha de idioma do usuário, também utilizou-se o SharedPreferences.

Language.xml
```
<map>
    <string name="locale">pt</string>
    <string name="country">BR</string>
</map>
```

Para a persistência dos dados de registro do usuário, utilizou-se da ferramenta SQLite.

A ferramenta [SQLiteBrowser](https://sqlitebrowser.org/) permite gerenciar/visualizar toda a estrutura do banco de dados e seus dados contidos em tabelas.

A estrutura do banco de dados pode ser visualizada [aqui](https://raw.githubusercontent.com/marcellocamara/iMusica/master/extras/database/db_structure.png). Já a tabela, com os dados persistentes do usuário, pode ser visualizada [aqui](https://raw.githubusercontent.com/marcellocamara/iMusica/master/extras/database/db_table.png).

As operações CRUD do banco de dados estão listadas na interface [IDatabaseCRUD](https://github.com/marcellocamara/iMusica/blob/master/app/src/main/java/dev/marcello/imusica/model/IDatabaseCRUD.java) :
```
public interface IDatabaseCRUD<T> {

    long Create(T obj);

    Cursor Read(T obj);

    Integer Update(T obj);

    Integer Delete(T obj);

}
```

## API REST

Para consumo dos dados da mock API, utilizou-se da ferramenta [Apiary](https://apiary.io/).

<p align="left">
  <img src="https://github.com/marcellocamara/iMusica/blob/master/extras/api/apiary.png" width="450" title="Apiary">
</p>

As operações CRUD da API estão listadas na interface [IApiaryApiCRUD](https://github.com/marcellocamara/iMusica/blob/master/app/src/main/java/dev/marcello/imusica/api/IApiaryApiCRUD.java) :
```
public interface IApiaryApiCRUD {

    @POST("posts")
    Call<Post> Create(@Body Post post);

    @GET("posts")
    Call<Listing> Read();

    @PUT("posts/{created}")
    Call<Post> Update(@Path("created") long created, @Body Post post);

    @DELETE("posts/{created}")
    Call<Void> Delete(@Path("created") long created);

}
```

Para realizar as operações de CRUD na aplicação, basta clicar sobre algum post que um Dialog surgirá, possibilitando editar ou excluir um post. Para criar um novo post, um FloatingActionButton foi adicionao ao layout. Como  trata-se de uma mock API, os dados inseridos, alterados ou deletados, não surtirão efeitos, apenas na aplicação. Ao carregar a lista de posts novamente, os mesmos posts são carregados pois o JSON é estático.

## Unix Timestamp Conversion

Para exibir a data de criação dos posts na aplicação, utilizou-se do website [Epoch Converter](https://www.epochconverter.com/).

Um teste unitário, [TimestampUtilTest.java](https://github.com/marcellocamara/iMusica/blob/master/app/src/test/java/dev/marcello/imusica/util/TimestampUtilTest.java), foi criado para verificar a classe utilitária, [TimestampUtil.java](https://github.com/marcellocamara/iMusica/blob/master/app/src/main/java/dev/marcello/imusica/util/TimestampUtil.java), de conversão das datas.

A tabela abaixo mostra todas as datas de criação dos posts, em `Unix` contidos no JSON, convertidos para `GMT/UTC`.

Epoch date | Human readable date (GMT)
:---  | :---: 
1497903395 | 2017-06-19 20:16:35
1555705461 | 2019-04-19 20:24:21
1555725059 | 2019-04-20 01:50:59
1555665621 | 2019-04-19 09:20:21
1555686880 | 2019-04-19 15:14:40
1555702115 | 2019-04-19 19:28:35
1555679786 | 2019-04-19 13:16:26
1555695502 | 2019-04-19 17:38:22
1555705538 | 2019-04-19 20:25:38
1555703133 | 2019-04-19 19:45:33
1555698977 | 2019-04-19 18:36:17
1555603930 | 2019-04-18 16:12:10
1555657694 | 2019-04-19 07:08:14
1555672619 | 2019-04-19 11:16:59
1555679956 | 2019-04-19 13:19:16
1555676786 | 2019-04-19 12:26:26
1555670386 | 2019-04-19 10:39:46
1555661100 | 2019-04-19 08:05:00
1555629139 | 2019-04-18 23:12:19
1555657774 | 2019-04-19 07:09:34
1555636168 | 2019-04-19 01:09:28
1555641702 | 2019-04-19 02:41:42
1555640881 | 2019-04-19 02:28:01
1555606250 | 2019-04-18 16:50:50
1555644464 | 2019-04-19 03:27:44
1555621697 | 2019-04-18 21:08:17

A formatação `dd/MM/yyyy` (dia/mês/ano) foi aplicada para melhor visualização.

## Telas / idioma

A aplicação foi desenvolvida em dois idiomas, `en - inglês` e `pt - português`, para os seguintes tamanhos de tela:
```
320dp
600dp
960dp
```

Todas as telas desenvolvidas podem ser visualizadas na tabela abaixo, com seu respectivo idioma.

Tela | Idioma
:---  | :---: 
Login | [en](https://raw.githubusercontent.com/marcellocamara/iMusica/master/extras/images/en/login.png) - [pt](https://raw.githubusercontent.com/marcellocamara/iMusica/master/extras/images/pt/login.png)
Registro | [en](https://raw.githubusercontent.com/marcellocamara/iMusica/master/extras/images/en/register.png) - [pt](https://raw.githubusercontent.com/marcellocamara/iMusica/master/extras/images/pt/register.png)
Menu | [en](https://raw.githubusercontent.com/marcellocamara/iMusica/master/extras/images/en/menu.png) - [pt](https://raw.githubusercontent.com/marcellocamara/iMusica/master/extras/images/pt/menu.png)
Principal | [en](https://raw.githubusercontent.com/marcellocamara/iMusica/master/extras/images/en/home.png) - [pt](https://raw.githubusercontent.com/marcellocamara/iMusica/master/extras/images/pt/home.png)
Idioma | [en](https://raw.githubusercontent.com/marcellocamara/iMusica/master/extras/images/en/language.png) - [pt](https://raw.githubusercontent.com/marcellocamara/iMusica/master/extras/images/pt/language.png)
Perfil | [en](https://raw.githubusercontent.com/marcellocamara/iMusica/master/extras/images/en/profile.png) - [pt](https://raw.githubusercontent.com/marcellocamara/iMusica/master/extras/images/pt/profile.png)
Notificação | [en](https://raw.githubusercontent.com/marcellocamara/iMusica/master/extras/images/en/notification.png) - [pt](https://raw.githubusercontent.com/marcellocamara/iMusica/master/extras/images/pt/notification.png)

## Push notification

Ao fazer login na aplicação, um thread se inicia contando o tempo de inatividade do usuário na tela `MainActivity`. Caso o usuário fique inativo por 20 segundos, uma notificação é enviada. Ao clicar na notificação para fechá-la, a tela `MainActivity` é recarregada, obtendo todos os posts da mock API novamente. Caso o usuário abra a tela de edição do perfil (`RegisterActivity` - layout e classe já existentes foram reaproveitados para fazer a tela de edição dos dados do usuário), a thread é parada até que volte para a tela `MainActivity`.

## Ferramentas utilizadas

- [Architectural Pattern MVP (Model View Presenter)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter)
- [Apiary](https://apiary.io/)
- [Butter Knife](https://github.com/JakeWharton/butterknife)
- [CardView](https://developer.android.com/guide/topics/ui/layout/cardview)
- [GSON](https://github.com/google/gson)
- [KeyboardVisibilityEvent](https://github.com/yshrsmz/KeyboardVisibilityEvent)
- [Material Design](https://material.io/design/)
- [OkHttp](https://github.com/square/okhttp)
- [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview)
- [Retrofit2](https://square.github.io/retrofit/)
- [SQLite](https://github.com/mackyle/sqlite)
- [SQLiteBrowser](https://github.com/sqlitebrowser/sqlitebrowser)

<p align="center">
  <img src="https://github.com/marcellocamara/iMusica/blob/master/app/src/main/ic_launcher-web.png" height="128" title="Ícone">
</p>
