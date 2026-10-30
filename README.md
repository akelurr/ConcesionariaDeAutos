# Concesionaria de Autos

**Sistema de gestión de una concesionaria desarrollado en Java 21, con interfaz de consola y persistencia de datos.**

![Java](https://img.shields.io/badge/Java-21-orange) ![Maven](https://img.shields.io/badge/Maven-Proyecto-blue) ![Estado](https://img.shields.io/badge/Proyecto-Acad%C3%A9mico-informational)

**ConcesionariaDeAutos** es una aplicación de consola que permite administrar vehículos, clientes, vendedores y operaciones de venta. Su motor de gestión aplica programación orientada a objetos para organizar la información, calcular precios según el tipo y estado del vehículo, validar ventas y consultar reportes.

El proyecto fue realizado para la asignatura **Programación Avanzada** de **Ingeniería Civil en Computación**, **Universidad de Talca**.

## Funcionalidades

- **Vehículos:** registrar automóviles diésel, eléctricos y a gasolina; listar, buscar y ordenar el inventario por precio final.
- **Clientes:** registrar clientes, buscarlos por ID o nombre y consultar su historial de compras.
- **Vendedores:** registrar y consultar vendedores, mantener su historial de ventas y calcular comisiones.
- **Ventas:** seleccionar cliente, vendedor, vehículo y método de pago; comprobar que la operación sea válida; registrar la venta y retirar el vehículo del inventario disponible.
- **Reportes:** consultar un ranking de vendedores ordenado por monto vendido y un resumen de vehículos disponibles, vendedores, ventas e importe total facturado.
- **Persistencia:** recuperar la información al iniciar y guardarla al seleccionar la opción de salir.

## Tecnologías utilizadas

| Tecnología | Uso |
| --- | --- |
| Java 21 | Lenguaje de programación |
| Apache Maven | Configuración y compilación del proyecto (`pom.xml`) |
| Java Collections Framework | Gestión de registros con `ArrayList` y `HashMap` |
| Java Serialization | Almacenamiento y recuperación del estado del sistema |
| `Scanner` | Entrada interactiva desde consola |

## Diseño y arquitectura

El sistema separa los modelos del dominio, las reglas de gestión, la entrada de consola y el almacenamiento de datos.

### Modelo de vehículos

La clase abstracta `Vehiculo` representa los atributos y comportamientos comunes. Sus subclases implementan de manera distinta el método `precioDef()`:

```text
                  Vehiculo (abstracta)
                   /       |       \
                  /        |        \
         AutoDiesel  AutoElectrico  AutoGasolina
```

El método `precioFinal()` considera el precio definido por el subtipo y ajustes por kilometraje o desperfectos. Así se aplica **herencia y polimorfismo**.

### Otros conceptos de programación orientada a objetos

- **Interfaces:** `Registrable` define datos de registro; `Rankeable` permite construir el ranking de vendedores.
- **Enumeraciones:** `Marca`, `Modelo`, `CondicionVehiculo`, `EstadoVehiculo` y `MetodoPago` representan valores definidos del dominio.
- **Colecciones:** `ArrayList` administra inventario, clientes, vendedores y ventas; `HashMap` facilita localizar clientes por ID.
- **Identificadores:** contadores `static` generan IDs y se actualizan después de cargar los datos almacenados.
- **Constantes:** se emplean campos `final`, como la tasa de comisión del vendedor.
- **Excepciones:** el proyecto incluye `ClienteNoEncontradoException` y `VehiculoNoDisponibleException`, además de validaciones de argumentos y estado.

### Organización del código

```text
ConcesionariaDeAutos/
├── pom.xml
├── data/
│   └── concesionaria.dat
├── docs/
│   └── uml/
└── src/
    └── main/
        └── java/
            └── com/mycompany/concesionariadeautos/
                ├── ConcesionariaDeAutos.java
                ├── enums/
                ├── excepciones/
                ├── interfaces/
                ├── modelo/
                ├── persistencia/
                │   └── GestorPersistencia.java
                ├── servicio/
                │   └── Concesionaria.java
                └── vista/
                    ├── EntradaConsola.java
                    ├── MenuConsola.java
                    ├── MenuClientes.java
                    ├── MenuVehiculos.java
                    ├── MenuVendedores.java
                    ├── MenuVentas.java
                    └── MenuReportes.java
```

**Responsabilidades principales:**

- `modelo`: entidades `Vehiculo`, `Cliente`, `Vendedor` y `Venta`, junto con los subtipos de vehículos.
- `servicio`: `Concesionaria`, que centraliza las operaciones y validaciones del negocio.
- `vista`: menús y entrada de datos por consola.
- `persistencia`: lectura y escritura del estado de la concesionaria.
- `enums`, `interfaces` y `excepciones`: tipos auxiliares que apoyan el modelo.

## Requisitos previos

- **JDK 21** instalado.
- **Apache Maven** disponible en el sistema, o un IDE compatible con proyectos Maven, como NetBeans.

Para comprobar las herramientas:

```bash
java -version
mvn -version
```

## Instalación y ejecución

Clona el repositorio:

```bash
git clone https://github.com/akelurr/ConcesionariaDeAutos.git
cd ConcesionariaDeAutos
```

> El repositorio es privado; para clonarlo necesitas una cuenta con acceso autorizado.

Compila y ejecuta desde la raíz del proyecto:

```bash
mvn compile
mvn exec:java -Dexec.mainClass=com.mycompany.concesionariadeautos.ConcesionariaDeAutos
```

También puedes abrir el directorio del proyecto en **NetBeans** y ejecutar la clase principal `ConcesionariaDeAutos`.

**Importante:** ejecuta la aplicación desde la raíz del proyecto, ya que la ruta de persistencia `data/concesionaria.dat` es relativa al directorio de trabajo.

## Menú principal

Al iniciar, se presentan las siguientes operaciones:

```text
========== CONCESIONARIA DE AUTOS ==========
1. Registrar vehículo
2. Listar vehículos
3. Buscar vehículos
4. Listar vehículos por precio
5. Registrar cliente
6. Buscar cliente y ver historial
7. Registrar vendedor
8. Listar vendedores
9. Realizar venta
10. Ver historial de ventas
11. Ranking de vendedores
12. Reporte general
0. Salir y guardar
===========================================
```

### Ejemplo de flujo de uso

1. Registra un vehículo, un cliente y un vendedor (si aún no existen).
2. Selecciona **Realizar venta** e indica los IDs correspondientes.
3. Elige un método de pago y confirma la operación.
4. Si la venta es válida, el sistema la incorpora al historial, descuenta el importe del saldo del cliente y retira el vehículo del inventario.
5. Consulta el historial de ventas, el ranking o el reporte general.
6. Selecciona **Salir y guardar** para conservar los cambios.

## Persistencia de datos

La clase `GestorPersistencia` utiliza `ObjectOutputStream` y `ObjectInputStream` para guardar y restaurar el objeto `Concesionaria` en:

```text
data/concesionaria.dat
```

- Si el archivo existe, se intenta recuperar el estado almacenado al iniciar.
- Si no existe, se crea una concesionaria vacía.
- Al seleccionar **0. Salir y guardar**, se escribe el estado actual en el archivo.
- Al recuperar los datos, se actualizan los contadores de IDs para evitar reutilizar identificadores existentes.

El guardado se realiza al salir mediante el menú; no se documenta como un guardado continuo después de cada operación.

## Integrantes

- **Alonso**
- **Bernardo**
- **Vicente**

**Asignatura:** Programación Avanzada  
**Carrera:** Ingeniería Civil en Computación  
**Universidad:** Universidad de Talca

---

*Proyecto académico orientado a aplicar programación orientada a objetos, gestión de colecciones, validación de operaciones y persistencia en Java.*
