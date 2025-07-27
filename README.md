# 🚗 Consulta Tabela FIPE - Projeto Java Spring Boot

Um projeto Java com Spring Boot que consulta a API pública da Tabela FIPE para obter informações históricas sobre marcas e modelos de veículos.

## ✨ Funcionalidades

- 🔍 Consulta veículos por marca e modelo
- 📊 Retorna histórico completo de preços e especificações
- 📈 Gera relatórios comparativos:
  - ✅ Top 10 mais baratos
  - 💰 Top 10 mais caros
  - 🕰️ Modelos mais antigos
  - 🆕 Modelos mais novos
  - 📅 Melhores ofertas por ano

## 🛠️ Tecnologias

- Java 21
- Spring Boot
- Lombok
- Records Java
- Stream API
- Requisições HTTP
- Consumo de API pública

## ⚙️ Configuração

Para alterar a busca padrão ("Chevrolet Cobalt"), edite o método `run` em:  
`TabelafipeApplication.java`

```java
@Override
public void run(String... args) throws Exception {
    app.startApp("MarcaDesejada", "ModeloDesejado"); // Altere aqui
}
