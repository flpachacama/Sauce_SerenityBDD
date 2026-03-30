# Sauce_SerenityBDD - E2E Automation

Automatización de pruebas E2E para **SauceDemo** utilizando **Serenity BDD**, **Cucumber** y **Screenplay Pattern**.

## 📋 Tabla de Contenidos
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Ejecución](#ejecución)
- [Flujo Automatizado](#flujo-automatizado)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Reportes](#reportes)
- [Documentación](#documentación)

## ⚙️ Requisitos

- **Java 11 o superior** (verificar con: `java -version`)
- **Google Chrome instalado**
- **Git** (para clonar/trabajar con repositorio)

## 📦 Instalación

```bash
# Clonar el repositorio
git clone <repository-url>

# Navegar a la carpeta
cd Sauce_SerenityBDD

# No requiere instalación adicional (usa Gradle wrapper)
```

## ▶️ Ejecución

### Windows (PowerShell / CMD)
```bash
.\gradlew.bat clean test
```

### Linux / Mac
```bash
./gradlew clean test
```

### Ejecutar y abrir reporte automáticamente (Windows)
```bash
.\run_tests.bat
```

## 🎯 Flujo Automatizado

El proyecto automatiza el siguiente flujo E2E:

1. **Acceso**: Abrir https://www.saucedemo.com/
2. **Autenticación**: 
   - Usuario: `standard_user`
   - Contraseña: `secret_sauce`
3. **Selección de Productos**: Agregar 2 artículos al carrito
   - Sauce Labs Backpack
   - Sauce Labs Bike Light
4. **Visualización**: Abrir y revisar el carrito
5. **Checkout**: Completar formulario con datos:
   - First Name: `Freddy`
   - Last Name: `Leonel`
   - Zip Code: `110111`
6. **Confirmación**: Finalizar compra
7. **Validación**: Verificar mensaje → `THANK YOU FOR YOUR ORDER`

## 📁 Estructura del Proyecto

```
src/test/
├── java/com/saucedemo/
│   ├── runners/              # Ejecutor de pruebas (CucumberWithSerenity)
│   ├── stepdefinitions/      # Definiciones de pasos (Gherkin)
│   ├── tasks/                # Acciones de negocio (Screenplay)
│   ├── questions/            # Validaciones/Preguntas (Screenplay)
│   ├── ui/                   # Localizadores de elementos (Page Objects)
│   ├── models/               # Modelos de datos (CheckoutData)
│   └── utils/                # Utilidades (Formatters, Constants)
└── resources/
    ├── features/saucedemo/   # Archivos .feature (Gherkin)
    └── serenity.conf         # Configuración de WebDriver

Archivos raíz:
├── build.gradle              # Dependencias y configuración Gradle
├── serenity.conf             # Configuración de Serenity
├── README.txt                # Guía rápida en texto
├── LOCATORS.md               # Mapeo de localizadores
└── TROUBLESHOOTING.md        # Solución de problemas
```

## 📊 Reportes

Los reportes de **Serenity** se generan automáticamente después de cada ejecución:

```
target/site/serenity/index.html
```

El reporte incluye:
- ✅ Resumen de ejecución (PASS/FAIL)
- 📸 Capturas de pantalla de cada acción
- 📝 Log detallado de pasos ejecutados
- 🔍 Trazabilidad completa del flujo

## 📚 Documentación

| Documento | Descripción |
|-----------|------------|
| `AI_Protocol/AI_Workflow.md` | Workflow y estrategia de automatización |
| `LOCATORS.md` | Mapeo de localizadores de SauceDemo |
| `TROUBLESHOOTING.md` | Solución de problemas comunes |
| `README.txt` | Guía rápida en formato texto |

## 🏗️ Tecnologías

- **Serenity BDD 4.2.34** - Framework de automatización
- **Cucumber 7.14.1** - DSL para pruebas (Gherkin)
- **Screenplay Pattern** - Patrón de diseño orientado al actor
- **Selenium WebDriver** - Automatización del navegador
- **Gradle** - Gestor de dependencias y build
- **JUnit 4** - Framework de pruebas

## 🔧 Configuración

### Cambiar navegador (en `serenity.conf`)
```properties
webdriver.driver = chrome        # firefox, edge, safari
```

### Aumentar timeout de espera
```properties
webdriver.wait.for.timeout = 10000  # en milisegundos
```

### Modo headless (sin interfaz visual)
```properties
headless.mode = true
```

## 🐛 Troubleshooting

Para problemas comunes y soluciones, ver **TROUBLESHOOTING.md**

Ejemplos:
- ChromeDriver no encontrado
- Elementos no visibles
- Reportes no generados
- Errores de compilación Java

## 📝 Notas Importantes

- Los **productos y datos de checkout** se definen en el feature file para fácil mantenimiento
- La **lógica de negocio** está en `Tasks`, las **validaciones** en `Questions`
- Los **localizadores** están centralizados en clases `UI`
- El proyecto está **listo para subir a GitHub** con `.gitignore` configurado

## 🚀 Próximos Pasos

Posibles extensiones:
- [ ] Agregar escenarios de login fallido
- [ ] Pruebas con múltiples usuarios
- [ ] Validaciones de filtros de productos
- [ ] Pruebas de descuentos/cupones
- [ ] Integración CI/CD (GitHub Actions)

---

**Última actualización**: Marzo 2026  
**Versión**: 1.0-SNAPSHOT