# AI Workflow - Automatización E2E SauceDemo

## 1. Objetivo del Workflow

Definir un flujo de automatización E2E robusto, escalable y mantenible sobre SauceDemo usando **Serenity BDD + Cucumber + Screenplay Pattern**, incorporando:

* Parametrización declarativa (CSV/JSON)
* Uso de `Scenario Outline`
* Cobertura de escenarios negativos críticos

---

## 2. Alcance

### Incluye

#### Flujos positivos:

* Login exitoso
* Agregar productos al carrito
* Validar carrito con productos
* Completar checkout
* Confirmar compra exitosa

#### Flujos negativos:

* Login fallido (credenciales inválidas)
* Intento de compra sin productos (carrito vacío)
* Intento de agregar producto no disponible

---

## 3. Estrategia de Automatización

### Evolución del enfoque

Antes:

* Escenario único
* Datos hardcodeados en `.feature`

Ahora:

* Enfoque **Data-Driven**
* Uso de `Scenario Outline`
* Datos externos (JSON/CSV)
* Escenarios positivos + negativos

---

## 4. Flujo de Automatización (Paso a Paso)

### Paso 1: Ingreso al sitio

- El actor abre `https://www.saucedemo.com/`.

---

### Paso 2: Autenticación

#### Casos cubiertos:

- Login exitoso
- Login con credenciales inválidas

Validaciones:

* Acceso a inventario (positivo)
* Mensaje de error (negativo)

---

### Paso 3: Selección de productos

#### Casos:

- Agregar productos disponibles
- Intentar agregar producto inexistente/no disponible

---

### Paso 4: Carrito

#### Casos:

- Carrito con productos
- Carrito vacío (flujo negativo)

Validaciones:

* Productos visibles
* Mensaje o estado de carrito vacío

---

### Paso 5: Checkout
- Inicia checkout.
- Completa datos requeridos (nombre, apellido, codigo postal).
(Sin cambios en flujo positivo, pero condicionado a validaciones previas)
---

### Paso 6: Finalización
- Confirma la orden.
- Valida el mensaje final: `THANK YOU FOR YOUR ORDER`.
---

## 5. Diseño de Features

### Nuevo estándar obligatorio

* Uso de `Scenario Outline`
* Uso de `Examples` o datos externos (`JSON` o `CSV`)
* Eliminación de datos hardcodeados

---

### Ejemplo de Feature Mejorado

```gherkin id="e2e1"
Feature: Flujo de compra en SauceDemo

  Scenario Outline: Flujo E2E con diferentes tipos de usuario
    Given el usuario abre la pagina de SauceDemo
    When inicia sesion con "<username>" y "<password>"
    Then valida "<resultado_login>"

    When agrega "<producto>" al carrito
    Then valida "<resultado_producto>"

    When visualiza el carrito
    Then valida "<resultado_carrito>"

Examples:
| username        | password       | producto         | resultado_login | resultado_producto     | resultado_carrito |
| standard_user   | secret_sauce  | Sauce Labs Bike  | success         | agregado              | carrito_con_items |
| locked_user     | secret_sauce  | Sauce Labs Bike  | error_login     | no_aplica             | no_aplica         |
| standard_user   | wrong_pass    | Sauce Labs Bike  | error_login     | no_aplica             | no_aplica         |
| standard_user   | secret_sauce  | producto_fake    | success         | producto_no_existe    | carrito_vacio     |
```

---

## 6. Manejo de Datos

### Uso de archivos externos

Ubicación:

```text
src/test/resources/data/
```

Archivos sugeridos:

```text
login-data.json
products-data.json
checkout-data.json
```

---

### Ejemplo JSON

```json id="data1"
[
  {
    "username": "standard_user",
    "password": "secret_sauce",
    "producto": "Sauce Labs Backpack",
    "resultado_login": "success"
  },
  {
    "username": "invalid_user",
    "password": "wrong",
    "producto": "Sauce Labs Bike",
    "resultado_login": "error_login"
  }
]
```

---

### Integración en StepDefinitions

* Mapear datos a modelos POJO o usar `DataTable`
* Evitar lógica compleja en steps
* Mantener steps declarativos

---

## 7. Implementación Screenplay

### Tasks

Agregar nuevas tareas:

* `LoginFallido`
* `AgregarProductoInexistente`
* `IntentarCheckoutSinProductos`

---

### Questions

Agregar validaciones:

* `MensajeErrorLogin`
* `CarritoVacio`
* `ProductoNoDisponible`

---

### Principio clave

* NO validar dentro de Tasks
* SI validar con Questions

---

## 8. Flujo de Ejecución

### Ejecucion de pruebas
En entorno local con Gradle:

```bat
gradlew.bat clean test
```

Si el proyecto tiene configurada la tarea de agregacion de reportes Serenity:

```bat
gradlew.bat clean test aggregate
```

### Generacion y consulta de reportes
- Serenity genera evidencias de cada paso (acciones, capturas, estado).
- Reporte HTML esperado en una ruta como:
    - `target/site/serenity/index.html` (configuracion comun)
    - o la ruta definida por el proyecto.
---

## 9. Estrategia de Pruebas (ACTUALIZADA)

### Tipos de pruebas

#### Positivas:

* Flujo completo E2E exitoso

#### Negativas:

* Login inválido
* Producto inexistente
* Carrito vacío

---

### Cobertura mínima requerida

| Módulo    | Positivo | Negativo        |
| --------- | -------- | --------------- |
| Login     | ✅        | ✅               |
| Productos | ✅        | ✅               |
| Carrito   | ✅        | ✅               |
| Checkout  | ✅        | ⚠️ condicionado |

---

## 10. Buenas Prácticas

* Usar `Scenario Outline` siempre que haya variación de datos
* Externalizar datos (JSON/CSV)
* Evitar hardcode en features
* Mantener Screenplay puro:

    * Tasks → acciones
    * Questions → validaciones
* Reutilizar componentes
* Escenarios independientes
* Nombres orientados a negocio

---

## 11. Evolución del Workflow

### Antes

* 1 solo escenario
* Datos hardcodeados
* Sin negativos

### Ahora

* Escenarios data-driven
* Parametrización externa
* Cobertura completa (positivo + negativo)
* Mayor mantenibilidad y escalabilidad

---

## 12. Conclusión

Este workflow mejorado permite:

* Escalar fácilmente escenarios
* Detectar fallos reales del sistema
* Reducir deuda técnica
* Alinear el proyecto con estándares QA profesionales

---
