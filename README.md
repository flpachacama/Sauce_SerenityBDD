# Sauce SerenityBDD - Workflow v2

## Descripcion
Suite de automatizacion E2E para [SauceDemo](https://www.saucedemo.com/) con **Serenity BDD + Cucumber + Screenplay**, actualizada a `AI_Protocol/AI_Workflow_v2.md`.

Incluye cobertura positiva y negativa para:

- Login
- Productos
- Carrito
- Checkout
- Flujo E2E completo

## Stack
- Java 11
- Gradle Wrapper
- Serenity BDD
- Cucumber
- Screenplay Pattern
- WebDriverManager

## Estructura principal
- `src/test/java/com/saucedemo/tasks`: acciones Screenplay (incluye flujos negativos).
- `src/test/java/com/saucedemo/questions`: validaciones Screenplay (incluye `MensajeErrorLogin`, `CarritoVacio`, `ProductoNoDisponible`).
- `src/test/java/com/saucedemo/stepdefinitions`: steps declarativos y data-driven.
- `src/test/resources/features/saucedemo`: features modulares por dominio (`login`, `productos`, `carrito`, `checkout`, `e2e`).
- `src/test/resources/data`: datos externos (`login-data.json`, `products-data.csv`, `checkout-data.json`, `negative-flows.csv`).

## Ejecucion
Desde la raiz del proyecto:

```bat
gradlew.bat clean test
```

Para generar consolidado Serenity:

```bat
gradlew.bat clean test aggregate
```

## Reportes
- Ruta comun del reporte Serenity: `target/site/serenity/index.html`

## Notas de diseno (Workflow v2)
- Features con `Scenario Outline`.
- Datos de prueba externos en JSON/CSV.
- Separacion estricta Screenplay:
  - `Task` para acciones.
  - `Question` para validaciones.

---

Referencia funcional: `AI_Protocol/AI_Workflow_v2.md`.