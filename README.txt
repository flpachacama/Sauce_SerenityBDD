SauceDemo - Automatizacion E2E con Serenity BDD + Cucumber + Screenplay

1) Prerrequisitos
- Java 11 o superior instalado
- Google Chrome instalado
- Acceso a internet para abrir https://www.saucedemo.com/

2) Estructura principal
- src/test/resources/features/saucedemo/compra_e2e.feature
- src/test/resources/serenity.conf
- src/test/java/com/saucedemo/
  - runners
  - stepdefinitions
  - tasks
  - questions
  - ui
  - models
  - utils

3) Ejecucion de pruebas (Windows cmd)
- Ejecutar:
  gradlew.bat clean test

4) Reportes Serenity
- El build ejecuta aggregate al finalizar test.
- Abrir el reporte HTML en la carpeta:
  target/site/serenity/index.html

5) Flujo cubierto
- Login con standard_user / secret_sauce
- Agregar 2 productos
- Visualizar carrito
- Completar checkout
- Finalizar compra y validar: THANK YOU FOR YOUR ORDER

6) Notas
- Los productos y datos de checkout se definen en el feature para facilitar mantenimiento.
- La logica de negocio esta en Tasks y las validaciones en Questions.
