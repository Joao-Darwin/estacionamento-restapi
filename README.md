<h1 align="center" id="titulo">Parking API 🚗</h1>

<div align="center" id="badges">

  [![GitHub License](https://img.shields.io/github/license/Joao-Darwin/parking-api?style=for-the-badge)](https://github.com/Joao-Darwin/parking-api/blob/main/LICENSE)
  ![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)
</div>

# Índice 
* [Badges](#badges)
* [Índice](#índice)
* [Sobre o Projeto](#sobreProjeto)
* [Técnologias Usadas](#techs)
* [Executar o Projeto](#execute)
* [Autor](#author)

<h1 id="sobreProjeto">Sobre o Projeto</h1>

O projeto consiste em uma API de estacionamento, onde podemos criar, editar, excluir e atualizar estacionamentos de estabelecimentos, bem como os veículos presentes nesse estacionamento. Quando criamos o estacionamento, informamos a quantidade de carros e motocicletas em que ele pode conter.

Além disso podemos fazer consultas dos histórico de veículos que já passaram naquele estacionamento, bem como os veículos que lá estão estacionados

## Camadas lógicas
![Modelo Conceitual](https://github.com/Joao-Darwin/repoImgs/blob/main/Imgs%20-%20Web%20Service%20SpringBoot/camadasLogicas.png)

## Diagrama de Classes
![Domínio](https://github.com/Joao-Darwin/repoImgs/blob/main/Imgs%20-%20Parking%20API/Parking%20API%20Class%20DiagramUpdated.png)

<h1 id="techs">Técnologias Usadas</h1>

- Java (linguagem)
- Spring Boot (framework)
- JPA / Hibernate (persistência de dados e ORM)
- Maven (gerênciador de depedências)
- H2 (banco de dados para teste)
- PostgreSQL (banco de dados)
- PostMan (API client)

<h1 id="execute">Executar o Projeto</h1>
Pré-requisitos: Java 17 e Spring Boot 3.0

### Clonar repositório
```bash
git clone https://github.com/Joao-Darwin/parking-api ParkingAPI
```

### Entrar na pasta do projeto
```bash
cd ParkingAPI
```
### Instalar o Maven
```bash
sudo apt-get install maven
```
### Instalar depedências do projeto
```bash
sudo mvn clean install
```
### Executar o projeto
```bash
mvn spring-boot:run
```

<div>
  <h2 id="author">Autor</h2>
 <a href="https://www.linkedin.com/in/joao-darwin/" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" style="border-radius: 30px"></a>
</div>
