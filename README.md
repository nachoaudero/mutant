# IGNACIO AUDERO - PRIMER PARCIAL MAGNETO
## Consigna
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Mens.

Te ha contratado a ti para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN. Para eso te ha pedido crear un programa con un método o función con la siguiente firma:

```java
boolean isMutant(String[] dna);
```

## Como funciona el programa?
Esta API recibira como parametro un array de Strings que representaran cada fila de una tabla de NxN con la secuencia de ADN. Las letras de los Strings solo pueden ser (A, T, C, G), las cuales representa cada base nitrogenada del ADN.

Se sabra si un humano es mutante, si se encuentra **MAS DE UNA SECUENCIA** de **CUATRO LETRAS IGUALES**, de forma oblicua, horizontal o vertical.

El usuario debe de ingresar en formato JSON un array de Strings que formen una matriz de NxN.

###### Request de un DNA mutante
```json
{
  "dna": [
    "AACGTT",
    "ATGTCC",
    "ACGGGG",
    "ATTGCC",
    "CCGTCC",
    "CCGTCC"
  ] 
}
```
Esta matriz tiene mas de una secuencia de cuatro letras iguales, esto quiere decir que el **DNA** ingresado es de un **_MUTANTE_**.

La API nos dara una response de error personalizado si ingresamos una secuencia no valida como por podria ser una matriz que **NO** sea de NxN, un arreglo vacio, una letra no valida, un array de null o una fila null.

###### Request de un DNA no valido
```json
{
  "dna": [
    "AACGTT",
    "ATGTCC",
    "ACGGGG",
    "ATTGCC",
    "CCGTCC",
    "CC"
  ] 
}
```
###### Response de un DNA no valido
```json
{
    "error_message": "El array tiene una fila null o no es una matriz de NxN.",
    "error_class": "Exception"
}
```
## Despliegue
El proyecto se encuentra alojado en Render y podran acceder a travez del siguiente link.

https://mutant-yq8w.onrender.com

Al mismo tiempo podras ingresar al siguiente link para ver la base de datos en memoria de H2 donde se cargan los DNA.

http://localhost:8080/h2-console/

Credenciales de acceso a la base de datos en memoria:
* URL: jdbc:h2:mem:./mutantdb
* USERNAME: mutante
* PASSWORD: mutante

## Endpoints y Ejemplos
* ### Endpoint /mutant
Este endpoint de tipo **POST** recibe como parametro un JSON con la matriz que contiene el DNA. 

###### Request de un DNA valido

```json
{
  "dna": [
    "AAAA",
    "CCCC",
    "TCAG",
    "GGTC"
  ]
}
```

###### Response del DNA anterior

```json
{
  "dna": [
    "AAAA",
    "CCCC",
    "TCAG",
    "GGTC"
  ],
  "mutant": true
}
```

* ### Endpoint /stats
Este endpoint de tipo **GET** nos devuelve un JSON con la cantidad de DNA mutantes verificados, la cantidad de DNA humanos verificados y un ratio de cada cuantos humanos cuantos mutantes hay.

###### Respons del endpoint /stats

```json
{
    "ratio": 0.5,
    "count_mutant_dna": 5,
    "count_human_dna": 10
}
```
