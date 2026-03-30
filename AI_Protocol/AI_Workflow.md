# AI Workflow - Automatizacion E2E SauceDemo

## 1. Objetivo del Workflow
Este workflow define como construir, ejecutar y mantener una automatizacion E2E sobre [SauceDemo](https://www.saucedemo.com/) usando **Serenity BDD + Cucumber + Screenplay Pattern**.

El objetivo principal es validar, de punta a punta, que un usuario puede:
- iniciar sesion,
- agregar productos al carrito,
- completar el checkout,
- y finalizar la compra con confirmacion exitosa.

## 2. Alcance
### Incluye
- Flujo E2E funcional completo en web:
  1. Login con `standard_user` / `secret_sauce`
  2. Agregar dos productos al carrito
  3. Abrir y validar el carrito
  4. Completar formulario de checkout
  5. Finalizar compra y validar `THANK YOU FOR YOUR ORDER`
- Ejecucion automatizada con Gradle.
- Generacion de reportes de Serenity.
- Publicacion del proyecto en repositorio publico con archivos de soporte.

### No incluye
- Pruebas de rendimiento o carga.
- Pruebas de seguridad.
- Compatibilidad multi-navegador avanzada (a menos que se configure explicitamente).
- Integracion con servicios externos fuera de SauceDemo.

## 3. Herramientas y Tecnologias
- **Serenity BDD**: orquesta ejecucion, evidencias y reportes.
- **Cucumber**: define escenarios en lenguaje legible (Gherkin).
- **Screenplay Pattern**: organiza la automatizacion por actores, tareas y preguntas.
- **Gradle**: gestiona dependencias y ejecucion de pruebas.
- **Selenium WebDriver**: automatiza interacciones del navegador.

## 4. Flujo de Automatizacion (Paso a Paso)
1. **Ingreso al sitio**
   - El actor abre `https://www.saucedemo.com/`.
2. **Autenticacion**
   - Ingresa usuario `standard_user` y contrasena `secret_sauce`.
   - Verifica que llego al inventario de productos.
3. **Seleccion de productos**
   - Agrega dos productos al carrito desde la lista de inventario.
   - Verifica que el contador del carrito refleje `2`.
4. **Visualizacion de carrito**
   - Abre el carrito.
   - Valida que los dos productos esperados esten presentes.
5. **Checkout**
   - Inicia checkout.
   - Completa datos requeridos (nombre, apellido, codigo postal).
6. **Finalizacion de compra**
   - Confirma la orden.
   - Valida el mensaje final: `THANK YOU FOR YOUR ORDER`.

## 5. Estrategia de Automatizacion
### Uso de Screenplay Pattern
- **Actors**: representan usuarios del sistema (por ejemplo, comprador web).
- **Tasks**: encapsulan acciones de negocio (iniciar sesion, agregar productos, finalizar compra).
- **Questions**: validan resultados observables (mensaje final, cantidad en carrito, productos visibles).
- **UI (Targets/Page Objects ligeros)**: centralizan localizadores para mantener el codigo limpio.

### Organizacion sugerida del proyecto
```text
src/test/java/
  |-- tasks/
  |-- questions/
  |-- interactions/      (opcional, para acciones reutilizables)
  |-- ui/
  |-- stepdefinitions/
  |-- runners/
src/test/resources/
  |-- features/
```

Buenas decisiones de diseno:
- Una tarea por objetivo funcional.
- Localizadores separados de la logica.
- Validaciones en `questions`, no mezcladas dentro de `tasks`.

## 6. Flujo de Ejecucion
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

## 7. Integracion con GitHub
### Buenas practicas al subir el proyecto
- Crear repositorio **publico** con nombre claro.
- Mantener historial de commits descriptivos.
- Incluir `.gitignore` adecuado para Java/Gradle.
- Evitar subir secretos, credenciales reales o archivos temporales.

### Archivos que deben incluirse
- Codigo fuente de automatizacion.
- Scripts necesarios de ejecucion.
- Reportes generados (si la evaluacion lo solicita).
- `README.txt` con pasos de instalacion y ejecucion.
- `conclusiones.txt` con hallazgos, riesgos y recomendaciones.

## 8. Resultados Esperados
La automatizacion se considera exitosa cuando:
- El escenario E2E completa todas las etapas sin errores.
- Se valida correctamente la confirmacion final de compra.
- El reporte muestra evidencia trazable de cada paso.

**Validacion clave final:**
- Mensaje esperado: `THANK YOU FOR YOUR ORDER`

## 9. Buenas Practicas
- **Reutilizacion de codigo**: crear tareas e interacciones reutilizables para evitar duplicacion.
- **Separacion de responsabilidades**: acciones en `tasks`, verificaciones en `questions`, localizadores en `ui`.
- **Legibilidad de pruebas**: nombres de escenarios, pasos y clases orientados al negocio.
- **Mantenibilidad**: centralizar cambios de UI y minimizar impactos.
- **Trazabilidad**: mantener alineacion entre feature, pasos y evidencia en reporte.

---

Este documento sirve como guia base para implementar y evaluar un proyecto real de automatizacion E2E con Serenity BDD en SauceDemo.