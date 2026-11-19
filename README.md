# Tienda Deportiva (Programación III – Primer parcial)

App Android en Kotlin + Jetpack Compose (Material 3) basada en el modelo de clases de una tienda de artículos deportivos.

## Estructura
- `model/`: Identificable, Categoria, ProductoDeportivo (abstracta), Balon, CalzadoDeportivo, Cliente, Proveedor, ItemCarrito, Carrito, EstadoPedido (sealed class), Pedido.
- `data/DatosPrueba.kt`: instancias de ejemplo usadas por las pantallas.
- `ui/`: ProductosScreen (Pantalla 1), CategoriasScreen (Pantalla 2), navegación y componentes compartidos.

## Versiones de herramientas
Gradle 9.1.0, Android Gradle Plugin 8.13.2, Kotlin 2.2.21, Compose BOM 2026.04.01, compileSdk/targetSdk 36, minSdk 26.
Gradle 9.1 puede ejecutarse con JDK 17 hasta 25.

## Cómo ejecutar
1. Abrir la carpeta en Android Studio (File > Open) y esperar el Gradle Sync.
2. Elegir un emulador o dispositivo (Android 8.0 / API 26 o superior).
3. Pulsar Run.

## Navegación
- Pantalla 1 (Productos): el ícono de menú abre Categorías.
- Pantalla 2 (Categorías): al tocar una categoría se vuelve a Productos filtrado por su deporte.
