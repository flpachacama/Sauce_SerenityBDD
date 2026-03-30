# Sauce SerenityBDD - Automatizacion E2E en SauceDemo

## 1. Descripcion
Este proyecto automatiza una prueba funcional E2E sobre [SauceDemo](https://www.saucedemo.com/) usando Serenity BDD.
El objetivo es validar el flujo completo de compra: inicio de sesion, seleccion de productos, carrito, checkout y confirmacion final de la orden.

## 2. Tecnologias utilizadas
- Serenity BDD
- Cucumber (Gherkin)
- Screenplay Pattern
- Java
- Gradle

## 3. Estructura del proyecto
La automatizacion se organiza para separar responsabilidades y facilitar el mantenimiento:

- `src/test/java/com/saucedemo/tasks`: acciones de negocio (login, agregar productos, checkout, finalizar compra).
- `src/test/java/com/saucedemo/questions`: validaciones del resultado esperado.
- `src/test/java/com/saucedemo/ui`: localizadores de elementos de la interfaz.
- `src/test/java/com/saucedemo/stepdefinitions`: mapeo de pasos Gherkin a acciones Screenplay.
- `src/test/java/com/saucedemo/runners`: clase runner para ejecutar los escenarios con Serenity + Cucumber.
- `src/test/resources/features`: escenarios en lenguaje Gherkin.
- `src/test/resources/serenity.conf`: configuracion base de Serenity/WebDriver.

## 4. Prerrequisitos
Antes de ejecutar, asegure lo siguiente:

- Java 11 o superior instalado.
- Gradle (o usar el wrapper incluido `gradlew` / `gradlew.bat`).
- Navegador web instalado (por ejemplo, Chrome o Edge).

## 5. Instalacion
1. Clonar el repositorio.
2. Abrir el proyecto en su IDE (por ejemplo IntelliJ IDEA).
3. Verificar que se descarguen las dependencias del proyecto.

Ejemplo de clonacion:

```bash
git clone https://github.com/flpachacama/Sauce_SerenityBDD.git
cd Sauce_SerenityBDD
```

## 6. Ejecucion de pruebas (paso a paso)
### Con Gradle (recomendado en este proyecto)
1. Abrir terminal en la raiz del proyecto.
2. Ejecutar limpieza y pruebas.

Windows:

```bat
gradlew.bat clean test
```

Linux/Mac:

```bash
./gradlew clean test
```

### Con Maven (si el proyecto se migra a Maven)

```bash
mvn clean verify
```

## 7. Reportes
Al finalizar la ejecucion, Serenity genera reportes HTML con detalle de cada paso.

- Ruta comun del reporte principal:
  - `target/site/serenity/index.html`

En el reporte se puede revisar:
- estado de los escenarios,
- evidencia de ejecucion,
- detalle de pasos ejecutados y validaciones.

## 8. Flujo automatizado
El escenario E2E cubre este flujo:

1. Login con usuario `standard_user`.
2. Agregar dos productos al carrito.
3. Visualizar carrito.
4. Completar checkout.
5. Finalizar compra y validar el mensaje `THANK YOU FOR YOUR ORDER`.

## 9. Buenas practicas aplicadas
- Uso de Screenplay Pattern para organizar actores, tareas y validaciones.
- Aplicacion de Clean Code con nombres descriptivos y clases enfocadas.
- Separacion de responsabilidades:
  - acciones en `tasks`,
  - verificaciones en `questions`,
  - localizadores en `ui`.

---

Referencia funcional y de estrategia: `AI_Protocol/AI_Workflow.md`.