# Sauce_SerenityBDD

Automatizacion E2E de SauceDemo con Serenity BDD, Cucumber y Screenplay Pattern.

## Flujo cubierto
- Login con `standard_user` / `secret_sauce`
- Agregar dos productos al carrito
- Validar carrito
- Completar checkout
- Finalizar compra y validar `THANK YOU FOR YOUR ORDER`

## Ejecutar pruebas (Windows)
```bat
gradlew.bat clean test
```

## Reporte Serenity
El reporte HTML se genera en `target/site/serenity/index.html`.

## Feature principal
`src/test/resources/features/saucedemo/compra_e2e.feature`