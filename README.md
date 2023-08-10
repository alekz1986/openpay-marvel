# openpay-marvel

Se comparte curls para pruebas (bajo localhost:8080)

Para poder iniciar el proyecto se requiere primero instalar la dependencia con el proyecto ```marve-api-wrapper```. Al ingresar a la raiz de dicho proyecto, se requiere ejecutar el siguiente comando:

```shell
mvn install
```

Para iniciar este proyecto (luego de tener instalada la dependencia), ejecutar el siguiente comando:

```shell
mvn spring-boot:run
```

## Login
De acuerdo a lo solicitado, se agrego una forma de autenticar a los endpoints mediante jwt. Con el siguiente curl se solicita un token.
Si se realizara cualquier consulta sobre los endpoints segurizados sin el token, devolvera para todos los casos 401 Unauthorized

```shell
curl --location 'http://localhost:8080/marvel/authentication/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: Cookie_1=value' \
--data '{
"user": "alexch",
"password": "123456"
}'
```

dando una respuesta del siguiente tipo
```json
{
"token": "eyJhbGciOiJSUzI1NiJ9.eyJ1c2VyIjoiMmZhNmI5ZmItZDcxMS00MWNhLTk1NjctZTJjMDE2MTc0ZTE4IiwidXNlcl9uYW1lIjoiYWxleGNoIiwiZXhwIjoxNjkxNjgwMDc2fQ.ZOiHitCOa1jgmS3717nIlW8pdZfabeSM77ZN2K2I-2rDnhQvwYLqVfvyuBXs0H69BuiX4z0ngwfWEMNA3Fo-YMYa9zuH3P-Dfbw-Bvh5iCgz8MhSS4Sm2pr67qXwlZnbSsgzj2LTCUpJrF-6jQt0iQ0uPJXI_4RIIiFUxi64c3bns9MsNr69_miMhvmC1Tv5xhdD_7NMQO4ANuT_Tw8f7MgtK--jj9IHaCM--IfyeeVJVE16r1Kp3wd7J8Rc-mO6HCe-zymcSLH7uLzxhaS5RiOJNVNpWy8o-p0LMhhrKKd--7ezav40ELRwgakvu7SxqNTAyY3zB_1F3rHC509LQw"
}
```

## Endpoint /marvel/characters
Con el siguiente curl se obtiene la informacion de characters de marvel, considerar reemplazar <TOKEN_JWT> por el valor devuelto en el login

```shell
curl --location 'http://localhost:8080/marvel/characters' \
--header 'Authorization: Bearer <TOKEN_JWT>' \
--header 'Cookie: Cookie_1=value' \
--data ''
```

```shell
curl --location 'http://localhost:8080/marvel/characters?name=Wolverine' \
--header 'Authorization: Bearer <TOKEN_JWT>' \
--header 'Cookie: Cookie_1=value' \
--data ''
```

Este endpoint como desarrollo propio tiene validaciones de los querey param permitidos, para evitar golpear innecesariamente al recurso externo y disminuir el estres al servidor.

### Validaciones (Errores controlados)
El siguiente curl fallará por enviar queryparams desconocidos, y/o tiene valores nulos o vacios (para las key permitidas)

```shell
curl --location 'http://localhost:8080/marvel/characters?xxx=aaa&name=&yyy=bbb' \
--header 'Authorization: Bearer <TOKEN_JWT>' \
--header 'Cookie: Cookie_1=value' \
--data ''
```
dando una respuesta con el siguiente formato
```json
{
  "message": "Invalid query params",
  "not-allowed": {
    "message": "The following parameters are not allowed",
    "query-params": [
        "xxx",
        "yyy"
      ]
  },
  "empty": {
    "message": "The following parameters cannot be empty or null.",
    "no-data": [
      "name"
    ]
  }
}
```


## Endpoint /marvel/character/{idCharacter}
Con el siguiente curl se obtiene la informacion de un "Character" en especifico del servicio de marvel, considerar reemplazar <TOKEN_JWT> por el valor devuelto en el login

```shell
curl --location 'http://localhost:8080/marvel/character/1009718' \
--header 'Authorization: Bearer <TOKEN_JWT>' \
--header 'Cookie: Cookie_1=value' \
--data ''
```

El siguiente curl fallará por enviar queryparams desconocidos, y/o tiene valores nulos o vacios (para las key permitidas)


## Endpoint adicional /marvel/audit
A modo de visualizacion, se crea este endpoint para mostrar el listado de los eventos guardados en el H2 cada vez que se consultan los endpoints de marvel

```shell
curl --location 'http://localhost:8080/marvel/audit' \
--header 'Authorization: Bearer <TOKEN_JWT>' \
--header 'Cookie: Cookie_1=value' \
--data ''
```

La data mostrada seria de esta manera

```json
[
  {
    "id": "54c2a066-bb7d-4e3e-92b8-8919e43bf72b",
    "client": "Character_Client",
    "hitTime": "2023-08-10 09:11:48"
  },
  {
    "id": "1dbdf8a0-b1f6-453d-83d5-9039300c96ce",
    "client": "Character_Client",
    "hitTime": "2023-08-10 09:11:49"
  },
  {
    "id": "9e3f51ca-4288-49f5-b333-2ea415f1a6e8",
    "client": "Characters_Client",
    "hitTime": "2023-08-10 09:11:50"
  }
]
```