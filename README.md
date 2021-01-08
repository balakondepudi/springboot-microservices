# Springboot Microservices Shopping Cart Application
## Table of Contents

- [Introduction](#introduction)
- [Technologies] (#technologies)
- [Build Process](#build-process)

## Introduction
A Spring Boot microservices application to implement a simple shopping cart. This is achieved by building below individual microservices:
- justshopme-service-registry : Service registry that functions as service registry and discovery for microservices
- justshopme-api-gateway : API gateway to have common load balancer for all incoming/outgoing requests
- cloud-config-server : Cloud Config Server to expose common config data for shop and inventory microservices
- justshopme-shop-cart - shop cart microservice to expose API's for shopping cart
- justshopme-inventory-cart - inventory cart microservice to expose API's for inventory cart
- hystrix-dashboard - hystrix dashboard implements curcuit breaker and to view API success/failures from the dashboard
- zipkin-server - common zipkin log server that can be used as end-to-end log analysis between microservices for an API request

Shop and Inventory API usage can be found from https://github.com/balakondepudi/spring-boot-shopping-cart-apis#introduction

## Technologies
Install following tools:<br>
- Spring Source Tool Suite 4
- Spring Boot 2.4.1
- Java 8
- Maven 3.6.3
- zipkin 2.23.2

## Build Process
Build jar file for each microservice
$ mvn clean install

Using the Maven Plugin for each microservice
$ mvn spring-boot:run
