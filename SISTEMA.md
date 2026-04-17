# Sistema de Cadastro de Perfis - Almoxarifado

Um sistema em Java para cadastrar e gerenciar perfis/categorias de itens no almoxarifado da esquadrilia.

## Funcionalidades

- ✅ Adicionar novo perfil (tipologia, tamanho, peso, fornecedor)
- ✅ Listar todos os perfis cadastrados
- ✅ Buscar perfil por ID
- ✅ Atualizar dados de um perfil
- ✅ Deletar um perfil
- ✅ Banco de dados SQLite

## Instalação

### 1. Baixar o Driver SQLite JDBC

Acesse: https://github.com/xerial/sqlite-jdbc/releases

Baixe a versão mais recente (ex: `sqlite-jdbc-3.45.0.0.jar`)

### 2. Adicionar ao Projeto

Coloque o arquivo `sqlite-jdbc-*.jar` na pasta `lib/` do projeto.

### 3. Compilar

No terminal, na pasta raiz do projeto:

```bash
javac -cp lib/sqlite-jdbc-*.jar:. src/*.java -d bin/
```

**No Windows, use `;` em vez de `:`**

```cmd
javac -cp "lib/sqlite-jdbc-*.jar;." src/*.java -d bin/
```

### 4. Executar

```bash
java -cp lib/sqlite-jdbc-*.jar:bin Main
```

**No Windows:**

```cmd
java -cp "lib/sqlite-jdbc-*.jar;bin" Main
```

## Estrutura do Projeto

```
├── src/
│   ├── Main.java          (Menu principal e interface do usuário)
│   ├── Perfil.java        (Classe que representa um perfil)
│   ├── ConexaoDB.java     (Gerencia conexão com o banco)
│   └── PerfilDAO.java     (Operações no banco de dados)
├── lib/
│   └── sqlite-jdbc-*.jar  (Driver JDBC do SQLite)
└── bin/                   (Arquivos compilados)
```

## Dados Armazenados

Cada perfil contém:
- **ID** (gerado automaticamente)
- **Tipologia** - tipo/categoria do item
- **Tamanho** - dimensões ou classificação de tamanho
- **Peso** - peso em kg
- **Linha de Fornecedor** - qual fornecedor supre este item

## Exemplo de Uso

```
===== SISTEMA DE ALMOXARIFADO =====
1. Adicionar novo perfil/categoria
2. Listar todos os perfis
3. Buscar perfil por ID
4. Atualizar perfil
5. Deletar perfil
6. Sair
====================================
Escolha uma opção: 1

--- Adicionar Novo Perfil ---
Tipologia: Parafuso Aço M8
Tamanho: 25mm
Peso (em kg): 0.05
Linha de Fornecedor: Fornecedora ABC
Perfil adicionado com sucesso!
```

## Banco de Dados

O banco de dados é armazenado em `almoxarifado.db` na raiz do projeto. 

Se quiser resetar os dados, basta deletar este arquivo e executar o programa novamente.

## Melhorias Futuras

- Autenticação de usuários
- Relatórios em PDF
- Interface gráfica (Swing/JavaFX)
- Exportar/Importar dados
- Sistema de reservas

---

**Desenvolvido para o Almoxarifado da Esquadrilia**
