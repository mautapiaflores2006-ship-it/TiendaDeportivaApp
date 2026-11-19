# Prompts para Examen de Programación III — Tienda de Artículos Deportivos

Este documento recopila todos los *prompts* diseñados paso a paso para completar los **Checkpoints** del examen de **Programación III** (UPB) con el caso de estudio **Tienda de Artículos Deportivos**.

---

## 1. Prompt para Checkpoint 1 — Modelo de Clases (15 puntos)

```text
Actúa como un arquitecto de software experto en Programación Orientada a Objetos (POO) y desarrollo Android con Kotlin y Jetpack Compose.

Necesito que diseñes el **modelo de clases** para mi examen de Programación III, eligiendo el caso de estudio: **Tienda de Artículos Deportivos**.

El diseño debe cumplir con estos criterios:
1. **Análisis del dominio**: Define las clases necesarias para representar una tienda de artículos deportivos (por ejemplo: entidades principales como Producto/Artículo Deportivo, Categoría, Cliente, Carrito/Pedido o Proveedor, incluyendo al menos dos entidades principales que puedan listarse en pantallas de una app Android).
2. **Atributos**: Especifica cada atributo con su nombre, tipo de dato compatible con Kotlin y visibilidad (`val`/`var`, `private`, `public`, etc.), además de una breve descripción de su rol en el dominio deportivo (ej. talla, deporte, marca, stock, precio).
3. **Métodos**: Define los métodos de comportamiento y lógica de negocio propios de cada clase (con parámetros y tipo de retorno), aplicando principios de POO como encapsulamiento, herencia, polimorfismo, interfaces o clases abstractas/selladas (`sealed class` / `interface` / `abstract class`) donde aporte claridad.
4. **Relaciones**: Explica claramente las relaciones entre las clases (asociación, agregación, composición, herencia o implementación) y su cardinalidad (1 a 1, 1 a muchos, muchos a muchos).
5. **Enfoque para Android / Jetpack Compose**: Asegúrate de que al menos dos de las clases contengan información clara y estructurada para mostrarse en listados dentro de dos pantallas en Jetpack Compose, e incluye ejemplos concretos de objetos de prueba (datos ficticios) para validar el modelo.

Presenta el resultado de forma ordenada, clara y estructurada, listo para que luego pueda implementarlo en Kotlin.
```

---

## 2. Prompt para Checkpoint 2 — Implementación en Kotlin y Segundo Commit (10 puntos)

> **Nota:** Este *prompt* incluye la regla estricta de fidelidad absoluta al modelo de clases definido previamente.

```text
Actúa como un desarrollador Android senior experto en Kotlin y Programación Orientada a Objetos (POO).

A partir del **modelo de clases** definido previamente para mi caso de estudio **Tienda de Artículos Deportivos** (para mi examen de Programación III), ayúdame a preparar el contenido correspondiente al **segundo commit** de mi proyecto en GitHub.

REGLA ESTRICTA E INNEGOCIABLE:
Debes respetar y hacer TODO tal cual figura en el modelo de clases: mantén exactamente los mismos nombres de clases, atributos, tipos de datos/visibilidad, métodos (firmas, parámetros y lógica esperada) y relaciones. NO omitas elementos del modelo, NO cambies nombres por cuenta propia ni agregues o reestructures clases/relaciones que contradigan el diseño original. Si algún detalle del modelo requiere aclaración antes de codificar, indícalo sin alterar lo ya definido.

El resultado debe cumplir con estos requisitos:
1. **Implementación fiel en Kotlin**: Traduce al pie de la letra todas las clases, atributos, métodos y relaciones del modelo de clases a código **Kotlin** limpio y bien estructurado, aplicando los conceptos de POO indicados en el modelo (encapsulamiento, herencia, polimorfismo, interfaces, clases abstractas o `sealed class` según corresponda).
2. **Trazabilidad con el modelo**: Verifica o muestra brevemente cómo cada clase, atributo, método y relación del modelo quedó reflejada en el código Kotlin sin desviaciones.
3. **Organización de archivos**: Indica claramente el nombre y la ruta sugerida de cada archivo `.kt` dentro de la estructura típica de un proyecto Android (por ejemplo, paquetes `model`, `domain`, etc.) para facilitar su integración en el proyecto.
4. **Datos de prueba**: Incluye una función o bloque con instancias/datos de ejemplo construidos con las clases exactas del modelo, listos para probar el comportamiento y servir luego para listar al menos dos tipos de objetos en las pantallas de la app.
5. **Preparación para el segundo commit**: Proporciona los comandos de Git (`git status`, `git add`, `git commit -m "..."`, `git push`) y un mensaje de *commit* claro y descriptivo para registrar este segundo avance en el repositorio público de GitHub.

Pega aquí el modelo de clases exacto que se debe seguir al pie de la letra y genera la implementación completa.
```

---

## 3. Prompt para Checkpoint 4 — Diseño de Interfaz (25 puntos)

```text
Actúa como un diseñador UI/UX y desarrollador Android experto en **Jetpack Compose** y **Material 3**.

A partir del **modelo de clases** (y su implementación en Kotlin) definido previamente para mi caso de estudio **Tienda de Artículos Deportivos** en mi examen de Programación III, ayúdame a desarrollar el **Checkpoint 4 - Diseño de Interfaz**.

REGLA DE COHERENCIA CON EL MODELO:
El diseño de ambas pantallas debe basarse estrictamente en las clases, atributos y métodos definidos en el modelo de clases y en la implementación en Kotlin. NO inventes campos que no existan en el modelo ni cambies los nombres de las entidades o propiedades principales. Si un dato no está en el modelo, indícalo claramente o apóyate únicamente en los atributos y métodos existentes.

El resultado debe cumplir con estos requisitos:
1. **Selección de objetos a listar**: Indica claramente cuáles **dos (o más) clases/objetos** del modelo de clases se van a listar (una por cada pantalla) y qué atributos/métodos de cada objeto se mostrarán en los ítems de la lista.
2. **Diseño de la Pantalla 1 (Listado del Objeto A)**:
   - Describe su propósito, estructura visual (distribución de zonas, jerarquía de información, navegación/acciones si aplican) y cómo se presentan los datos de cada elemento de la lista.
   - Incluye un esquema visual en texto/ASCII o wireframe descriptivo de cómo se verá la pantalla.
   - Detalla de forma específica los **componentes de Jetpack Compose** a utilizar (por ejemplo: `Scaffold`, `TopAppBar`, `LazyColumn`, `Card`, `Row`, `Column`, `Text`, `Icon`, `Button`, `Modifier`, etc.) y qué rol cumple cada uno.
3. **Diseño de la Pantalla 2 (Listado del Objeto B)**:
   - Describe su propósito, estructura visual y cómo se presentan los datos de cada elemento de la lista.
   - Incluye un esquema visual en texto/ASCII o wireframe descriptivo de cómo se verá la pantalla.
   - Detalla de forma específica los **componentes de Jetpack Compose** a utilizar y el rol de cada uno.
4. **Preparación para el Checkpoint 5**: Asegúrate de que este diseño sea claro y directo para que luego pueda construirse en código Jetpack Compose sin contradicciones.

Pega aquí el modelo de clases (y/o la implementación en Kotlin) y genera el diseño completo de ambas pantallas.
```

---

## 4. Prompt para Checkpoints 5 y 6 — Correcciones de Modelo, Pantallas en Jetpack Compose y Proyecto en `.zip` (40 puntos)

> **Nota:** Este *prompt* resuelve las observaciones del modelo (`genero` en `CalzadoDeportivo` y *getters* de solo lectura para `stock` y `estado`), implementa las dos pantallas en **Jetpack Compose** y solicita el proyecto completo en formato `.zip` listo para **Android Studio**.

```text
Actúa como un desarrollador Android senior experto en **Kotlin**, **Programación Orientada a Objetos (POO)** y **Jetpack Compose** (Material 3).

Te confirmo cómo resolver los puntos pendientes sobre nuestro proyecto de **Tienda de Artículos Deportivos** (examen de Programación III):

1. **Punto 1 (`CalzadoDeportivo` — `general` vs. `genero`)**: Sí, es un error de género/acentuación en el modelo. Cambia `general` por `genero` en `CalzadoDeportivo` para que sea coherente con el dominio y con el resto del modelo/código.
2. **Punto 7 (Lectura de `stock` y `estado`)**: Define y crea *getters* de solo lectura (por ejemplo, propiedades/métodos de consulta públicos de solo lectura como `obtenerStock()` / `stockActual` y `obtenerEstado()` / `estadoActual` según corresponda en el modelo) manteniendo el encapsulamiento (`private set` o acceso controlado) para exponer el stock (por ejemplo, para mostrar `"Stock: 12"` en la Pantalla 1) y el estado sin romper la POO.
3. **Resto del modelo (Puntos 2 al 6)**: Mantén tal cual las decisiones ya tomadas en el modelo y en la implementación previa (relación conceptual `Categoria–ProductoDeportivo` por coincidencia de `deporte`, `Pedido` recibiendo sus ítems por constructor, contención de ítems en `Carrito`, `Proveedor.actualizarDatos()` normalizando con `trim()`, y estado inicial `Pendiente` por defecto en `Pedido`).

A continuación te adjunto / pego **el diseño de interfaz (Checkpoint 4)** y el **modelo/código base** de mi proyecto. Con esto, realiza lo siguiente:

1. **Actualización del modelo / clases en Kotlin**: Aplica el cambio de `general` a `genero` en `CalzadoDeportivo` y agrega los *getters* de solo lectura para `stock` y `estado`, actualizando las referencias necesarias en las clases/datos de prueba sin alterar lo demás.
2. **Implementación de las dos pantallas en Jetpack Compose**: Construye las dos pantallas en Jetpack Compose siguiendo fielmente el diseño de interfaz que te paso, listando al menos dos de los objetos implementados y mostrando los datos reales del modelo (incluido el stock/atributos correspondientes) a partir de los objetos/datos de prueba existentes, con navegación funcional entre ambas pantallas.
3. **Proyecto completo en un archivo `.zip` listo para Android Studio**:
   - Genera y entrégame **todo el proyecto Android completo dentro de un archivo `.zip` descargable** (por ejemplo, `TiendaDeportivaApp.zip`), con toda la estructura oficial de un proyecto de Android Studio (`app/src/main/...`, `build.gradle.kts`, `settings.gradle.kts`, `gradle.properties`, `AndroidManifest.xml`, recursos `res/`, paquetes `model`, `ui`, `MainActivity.kt`, etc.).
   - Asegúrate de que las dependencias de Kotlin y Jetpack Compose (Material 3) estén correctamente configuradas en Gradle para que **compile y ejecute sin errores** al abrirlo directamente en Android Studio y correrlo en un emulador o dispositivo Android.
   - Inclúyeme también los comandos de Git para subir este avance a mi repositorio público de GitHub.

[PEGA AQUÍ EL DISEÑO DE INTERFAZ DEL CHECKPOINT 4 Y EL MODELO/CÓDIGO BASE]
```