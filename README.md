## REQUERIMIENTO

Cree las API REST necesarias para una aplicación de manejo del flujo de efectivo. El trabajo de esta aplicación es realizar un seguimiento de todas las transacciones que ocurren en una cuenta.

## ENTREGABLE:

#### \*Repositorio Github

    https://github.com/basewicoder/evaluacion_tecnica

### \*Tecnologia utitlizadas

### \* \*Backend

*Java*: Lenguaje de programación utilizado para desarrollar aplicaciones.

*Spring Boot*: Framework de desarrollo de aplicaciones web y microservicios.

*Hibernate*: Framework de mapeo objeto-relacional para bases de datos.

*H2*: Sistema de gestión de bases de datos relacionales de código abierto, rápido y ligero.

*JPA*: API de Java para la gestión de datos en bases de datos relacionales.

*JWT*: JSON Web Tokens, es un estándar para la creación de tokens de autenticación que pueden ser utilizados para la comunicación entre servicios.

### \* \*FrontEnd

*Angular* 15 : Framework de desarrollo de aplicaciones web en el lado del cliente.
para acceder al portal web debe ir al siguiente link:
\*http://localhost:8443/passport/login
Datos de acceso


usuario: *admin*

contraseña: *123456*

\[\[Pasted image 20230401183328.png\]\]
Opciones :
1. La documentacion de los Api con swagger
2. Visualizacion de cuenta y filtro por le numero de cuenta
3. Historial de transacciones

### \*Despleable

- examen-0.0.1-SNAPSHOT.war

El deploy .war se encuentra en raiz de del proyecto git

### \*Documentación de la API

    http://localhost:8443/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

## REQUISITOS DETALLADOS

# 1. CREAR CUENTA

#### La aplicación debe permitir crear una cuenta con un balance inicial con estado inicial de *ACTIVE.*

\*\*\*Primero debemos crear a un Cliente.

- API REST

Doc: http://localhost:8443/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Cliente/create_1
URL: http://localhost:8443/v1/cliente

Request body

``` json
 {
    "nombre": "Juan",
    "apellido": "Pinto",
    "telefono": "54545454",
    "correoElectronico": "string@gmail.com"
  }
```

Response body

``` json
{
  "id": 1,
  "nombre": "Juan",
  "apellido": "Pinto",
  "telefono": "54545454",
  "correoElectronico": "string@gmail.com"
}
```

- FrontEnd

\*\*\*Segundo paso creamos la cuenta del cliente.

- API REST

Doc: http://localhost:8443/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Cuenta/create
URL: http://localhost:8443/v1/cuenta
Request body

``` json
 {
  "numeroCuenta": "222535456",
  "cliente": {
   "id": 1
  },
  "saldo": 1000,
  "moneda": "USD",
  "estadoCuenta": "ACTIVE"
}
```

Response body

``` json
{
  "id": 1,
  "numeroCuenta": "string",
  "cliente": {
    "id": 1,
    "nombre": "Juan",
    "apellido": "Pinto",
    "telefono": "54545454",
    "correoElectronico": "string@gmail.com"
  },
  "saldo": 1000.00,
  "moneda": "USD",
  "estadoCuenta": "ACTIVE"
}
```

### \*para ver la lista de cuenta

Doc:http://localhost:8443/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Cuenta/allCuenta
ULR : http://localhost:8443/v1/cuenta/all

``` json
[
    {
        "id": 1,
        "numeroCuenta": "222535456",
        "cliente": {
            "id": 1,
            "nombre": "Juan",
            "apellido": "Pinto",
            "telefono": "54545454",
            "correoElectronico": "string@gmail.com"
        },
        "saldo": 1000.00,
        "moneda": "USD",
        "estadoCuenta": "ACTIVE"
    }
]
```

- FrontEnd

http://localhost:8443/transaccion

\[\[Pasted image 20230401175925.png\]\]
\# 2. DEPOSITAR DINERO  
\## La aplicación debe permitir el \*retiro de dinero.

#### Es posible el retiro de monto superior a saldo por única vez, y en tal escenario el estado de cuenta pasa a *HOLD.*

- API REST

Doc: http://localhost:8443/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Transaccion
URL: http://localhost:8443/v1/transaccion/1/retiros

Request body

``` json
{
  "monto": 1002,
  "moneda": "USD"
}
```

Response body

``` json
{
  "msg": "Operacion exitosa"
}
```

#### No se puede realizar retiros de cuentas en estado *HOLD.*

Request body

``` json
{
  "monto": 10,
  "moneda": "USD"
}
```

Response body

``` json
{
  "msg": "No se puede realizar retiros en cuentas en estado HOLD."
}
```

#### Solo se debe hacer *retiro* en la misma moneda.

Request body

``` json
{
  "monto": 10,
  "moneda": "BOB"
}
```

Response body

``` json
{
  "msg": "El retiro debe ser en la misma moneda de la cuenta."
}
```

Frontend
\[\[Pasted image 20230401180029.png\]\]
\# 3. RETIRAR DINERO
\## La aplicación debe permitir el \*depósito de dinero

#### Si el estado de la cuenta esta en *HOLD* y el monto del depósito es mayor o igual al saldo negativo, la cuenta pasa a estado *ACTIVE*.

- API REST

Doc: http://localhost:8443/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Transaccion/depositos
URL: http://localhost:8443/v1/transaccion/1/depositos

Request body

``` json
{
  "monto": 500,
  "moneda": "USD"
}
```

Response body

``` json
{
  "msg": "Operacion exitosa"
}
```

Revisamos el estado de la cuenta que debe pasar de \*HOLD a ACTIVE

Si bien el deposito se hizo *500 USD* y en la lista nos muestra *498.00 USD* es por que nosotros retiramos *1002 USD* en el saldo tenia *1000 USB* como se sobre paso el saldo y nos quedaria saldo de *-2 USD* por lo cual tenemos *498 USD*

Doc: http://localhost:8443/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Cuenta/allCuenta
URL: http://localhost:8443/v1/cuenta/all

``` json
[
    {
        "id": 1,
        "numeroCuenta": "222535456",
        "cliente": {
            "id": 1,
            "nombre": "Juan",
            "apellido": "Pinto",
            "telefono": "54545454",
            "correoElectronico": "string@gmail.com"
        },
        "saldo": 498.00,
        "moneda": "USD",
        "estadoCuenta": "ACTIVE"

    }

]
```

#### Si el estado de la cuenta esta en *HOLD* y el monto del depósito es menor al saldo negativo, la cuenta se mantiene en estado *HOLD* y solo se \*reduce el saldo negativo.

Request body

``` json
{
  "monto": 1,
  "moneda": "USD"
}
```

Response body

``` json
{
  "msg": "Operacion exitosa."
}
```

#### Solo debe hacer *deposito* en la misma moneda.

Request body

``` json
{
  "monto": 10,
  "moneda": "BOB"
}
```

Response body

``` json
{
  "msg": "El deposito debe ser en la misma moneda de la cuenta."
}
```

Front end
\[\[Pasted image 20230401180351.png\]\]
\# 4. CONSULTAR SALDO

Doc: http://localhost:8443/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Cuenta/findByCuenta
URL: http://localhost:8443/v1/cuenta/consulta/222535456

Numero de cuenta: *222535456*

Response body

``` json
 {
  "id": 1,
  "numeroCuenta": "222535456",
  "cliente": {
      "id": 1,
      "nombre": "Juan",
      "apellido": "Pinto",
      "telefono": "54545454",
      "correoElectronico": "string@gmail.com"
    },
  "saldo": 9.00,
  "moneda": "USD",
  "estadoCuenta": "ACTIVE"
}
```

Front end
\[\[Pasted image 20230401180441.png\]\]
\# 5. VER HISTORICO DE TRANSACCIONES

## La aplicación debe permitir ver el \*histórico de transacciones.

- API REST

Doc: http://localhost:8443/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Transaccion/allTransaccion
URL: http://localhost:8443/v1/transaccion/all

Response body

``` json
[
    {
        "id": 1,
        "tipo": "RETIRO",
        "monto": -1002.00,
        "moneda": "USD",
        "fecha": "2023-04-01T10:54:55.933",
        "saldo": -2.00,
        "descripcion": "Retiro completa : HOLD",
        "cuenta": {
            "id": 1,
            "numeroCuenta": "222535456",
            "cliente": {
                "id": 1,
                "nombre": "Juan",
                "apellido": "Pinto",
                "telefono": "54545454",
                "correoElectronico": "string@gmail.com"
            },
            "saldo": 9.00,
            "moneda": "USD",
            "estadoCuenta": "ACTIVE"
        }
    },
    {
        "id": 2,
        "tipo": "DEPOSITO",
        "monto": 500.00,
        "moneda": "USD",
        "fecha": "2023-04-01T10:54:59.424",
        "saldo": 498.00,
        "descripcion": "Deposito completa : ACTIVE",
        "cuenta": {
            "id": 1,
            "numeroCuenta": "222535456",
            "cliente": {
                "id": 1,
                "nombre": "Juan",
                "apellido": "Pinto",
                "telefono": "54545454",
                "correoElectronico": "string@gmail.com"
            },
            "saldo": 9.00,
            "moneda": "USD",
            "estadoCuenta": "ACTIVE"
        }
    },
    {
        "id": 3,
        "tipo": "RETIRO",
        "monto": -500.00,
        "moneda": "USD",
        "fecha": "2023-04-01T11:01:20.172",
        "saldo": -2.00,
        "descripcion": "Retiro completa : HOLD",
        "cuenta": {
            "id": 1,
            "numeroCuenta": "222535456",
            "cliente": {
                "id": 1,
                "nombre": "Juan",
                "apellido": "Pinto",
                "telefono": "54545454",
                "correoElectronico": "string@gmail.com"
            },
            "saldo": 9.00,
            "moneda": "USD",
            "estadoCuenta": "ACTIVE"
        }
    },
    {
        "id": 4,
        "tipo": "DEPOSITO",
        "monto": 1.00,
        "moneda": "USD",
        "fecha": "2023-04-01T11:01:43.768",
        "saldo": -1.00,
        "descripcion": "Deposito completa : HOLD",
        "cuenta": {
            "id": 1,
            "numeroCuenta": "222535456",
            "cliente": {
                "id": 1,
                "nombre": "Juan",
                "apellido": "Pinto",
                "telefono": "54545454",
                "correoElectronico": "string@gmail.com"
            },
            "saldo": 9.00,
            "moneda": "USD",
            "estadoCuenta": "ACTIVE"
        }
    },
    {
        "id": 5,
        "tipo": "DEPOSITO",
        "monto": 10.00,
        "moneda": "USD",
        "fecha": "2023-04-01T11:01:57.627",
        "saldo": 9.00,
        "descripcion": "Deposito completa : ACTIVE",
        "cuenta": {
            "id": 1,
            "numeroCuenta": "222535456",
            "cliente": {
                "id": 1,
                "nombre": "Juan",
                "apellido": "Pinto",
                "telefono": "54545454",
                "correoElectronico": "string@gmail.com"
            },
            "saldo": 9.00,
            "moneda": "USD",
            "estadoCuenta": "ACTIVE"
        }
    }
]
```

- FRONTEND
  \[\[Pasted image 20230401180620.png\]\]
