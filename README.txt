SauceDemo - Automatizacion E2E con Serenity BDD + Cucumber + Screenplay

PREREQUISITOS
=============
- Java 11 o superior (verificar con: java -version)
- Google Chrome instalado
- Git (para clonar/subir el repositorio)

INSTALACION
===========
1. Clonar o descargar este repositorio
2. Abrir terminal/PowerShell en la carpeta raiz del proyecto
3. No se requiere instalacion adicional (Gradle utiliza wrapper)

EJECUCION DE PRUEBAS
====================
Windows (PowerShell/CMD):
    .\gradlew.bat clean test
    
Linux/Mac:
    ./gradlew clean test

Ejecutar y abrir reportes automaticamente (Windows):
    .\run_tests.bat

FLUJO CUBIERTO POR LA AUTOMATIZACION
=====================================
1. Abrir https://www.saucedemo.com/
2. Iniciar sesion con credenciales:
   - Usuario: standard_user
   - Clave: secret_sauce
3. Agregar dos productos al carrito:
   - Sauce Labs Backpack
   - Sauce Labs Bike Light
4. Visualizar el carrito
5. Completar formulario de checkout:
   - First Name: Freddy
   - Last Name: Leonel
   - Zip Code: 110111
6. Finalizar compra
7. Validar mensaje final: "THANK YOU FOR YOUR ORDER"

REPORTES SERENITY
=================
Los reportes se generan automaticamente en:
    target/site/serenity/index.html

Abrirlo en un navegador web para ver:
- Resumen de ejecucion
- Resultado de cada escenario
- Capturas de pantalla por accion
- Trazabilidad completa

ESTRUCTURA DEL PROYECTO
=======================
src/test/java/com/saucedemo/
    ├── runners/              - Configuracion de ejecucion
    ├── stepdefinitions/      - Definiciones de pasos (Gherkin)
    ├── tasks/                - Acciones de negocio (Screenplay)
    ├── questions/            - Validaciones/Preguntas (Screenplay)
    ├── ui/                   - Localizadores de elementos
    ├── models/               - Objetos de datos
    └── utils/                - Utilidades compartidas

src/test/resources/
    ├── features/             - Archivos .feature (Gherkin)
    └── serenity.conf         - Configuracion de webdriver

ARCHIVOS IMPORTANTES
====================
- AI_Protocol/AI_Workflow.md - Documentacion del workflow
- LOCATORS.md               - Mapeo de localizadores SauceDemo
- TROUBLESHOOTING.md        - Solucion de problemas comunes
- .gitignore                - Archivos excluidos de Git

DUDA O PROBLEMA?
================
1. Revisar TROUBLESHOOTING.md
2. Verificar que Java 11+ este instalado
3. Ejecutar: gradlew.bat clean compileTestJava
4. Revisar serenity.conf para configurar locales/idioma
