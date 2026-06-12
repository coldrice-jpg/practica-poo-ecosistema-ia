# Práctica POO: Ecosistema de Inteligencia Artificial

Este repositorio contiene el desarrollo de un simulador de modelos de Inteligencia Artificial, diseñado para poner en práctica los pilares fundamentales de la Programación Orientada a Objetos (POO) en Java, utilizando un flujo de trabajo profesional basado en Gitflow.



## 1. Configuración del Repositorio Central

Para comenzar con el proyecto, sigue estos pasos para inicializar el repositorio local y su estructura base:

1. **Crear un nuevo repositorio** en GitHub con el nombre: `practica-poo-ecosistema-ia`.
2. **Clonar el repositorio** en tu entorno local e ingresar a la carpeta del proyecto:
   ```bash
   git clone [https://github.com/USUARIO/practica-poo-ecosistema-ia.git](https://github.com/USUARIO/practica-poo-ecosistema-ia.git)
   cd practica-poo-ecosistema-ia
3. Crear la estructura inicial de directorios y paquetes del proyecto:

         mkdir -p src/main/java/com/ia/modelos
         mkdir -p src/main/java/com/ia/interfaces
         mkdir -p src/main/java/com/ia/aplicacion
         mkdir -p docs
         touch README.md
         Realizar el primer commit en la rama principal (main):

      
         git add .
         git commit -m "chore: estructura inicial del proyecto"
         git push origin main

## 2. Gestión de Ramas (Gitflow)

El desarrollo del ecosistema se administra mediante el flujo de trabajo Gitflow para garantizar un historial limpio y un desarrollo en paralelo organizado.

### Crear la rama de desarrollo
Toda la integración de las características se realizará sobre la rama develop:

      git checkout -b develop
      git push origin develop

### Crear ramas de trabajo (Features)
Cada fase del proyecto cuenta con su propia rama dedicada partiendo siempre desde develop:

* Rama para Encapsulación:

      git checkout develop
      git checkout -b feature/encapsulacion

* Rama para Herencia:

      git checkout develop
      git checkout -b feature/herencia

* Rama para Polimorfismo:
  
      git checkout develop
      git checkout -b feature/polimorfismo

* Rama para Integración Final:

      git checkout develop
      git checkout -b feature/integracion-simulador

* Fusión de ramas (Merge)
Al concluir y validar el correcto funcionamiento de cada funcionalidad, se debe fusionar hacia develop:

      git checkout develop
      git merge feature/nombre-rama
      git push origin develop

* Liberación final
  
   Una vez que el simulador esté completamente integrado y libre de errores en develop, se realiza el despliegue final a la rama productiva:

      git checkout main
      git merge develop
      git push origin main

## Fase 2: Implementación de Encapsulación Dinámica

En esta fase se desarrolló e integró la lógica de protección del estado interno para los objetos `ModeloIA`, asegurando que métricas sensibles de machine learning no puedan ser alteradas externamente de manera maliciosa o errónea.

### Aplicación de la Encapsulación en `ModeloIA`

La clase `ModeloIA` implementa la ocultación de información mediante los siguientes mecanismos clave:

* **Acceso Restringido (`private`):** Todos los atributos fundamentales de la IA (`nombre`, `precision`, `epocasEntrenadas`, `tasaAprendizaje`) están definidos como privados. Ninguna clase externa (incluyendo `SimuladorIA`) puede alterarlos directamente vía asignación directa (`objeto.precision = 100`).
* **Inmutabilidad Controlada (Getters de Solo Lectura):** Se omitieron deliberadamente los métodos `setPrecision()` y `setEpocasEntrenadas()`. La única vía legítima para incrementar el progreso de la IA es mediante el método controlado `entrenar()`.
* **Validación de Reglas de Negocio en Setters:** El método `setTasaAprendizaje(double tasa)` actúa como un firewall de datos. Implementa una condicional estricta ($0.0 < \text{tasa} < 1.0$) que descarta y notifica en consola cualquier intento de inyección de parámetros inválidos, manteniendo intacto el estado previo del objeto.



### Evidencia de Ejecución en Consola

A continuación, se detalla la salida generada por el programa, donde se comprueba el rechazo de valores fuera de rango y la evolución segura de las métricas durante el entrenamiento:

```text
INICIANDO SIMULACIÓN DE MODELOS DE IA

--- ESTADO INICIAL ---

Modelo IA: RedNeuronal     
   Precisión:      50.00%
   Épocas:        0
   Tasa Learning: 0.1000


Modelo IA: ArbolDecision   
   Precisión:      50.00%
   Épocas:        0
   Tasa Learning: 0.0500


PRUEBA DE ENCAPSULACIÓN (Valores Inválidos)
WARNING [RedNeuronal]: Intento de asignar tasa inválida (-0.5). Se mantiene el valor anterior: 0.1
WARNING [ArbolDecision]: Intento de asignar tasa inválida (1.2). Se mantiene el valor anterior: 0.05

Verificando que los objetos mantengan su integridad:
Tasa RedNeuronal: 0.1
Tasa ArbolDecision: 0.05

INICIANDO CICLO DE ENTRENAMIENTO

>> Entrenando: RedNeuronal

Modelo IA: RedNeuronal     
   Precisión:      54.12%
   Épocas:        1
   Tasa Learning: 0.1000


Modelo IA: RedNeuronal     
   Precisión:      57.35%
   Épocas:        2
   Tasa Learning: 0.1000


Modelo IA: RedNeuronal     
   Precisión:      58.91%
   Épocas:        3
   Tasa Learning: 0.1000


>> Entrenando: ArbolDecision

Modelo IA: ArbolDecision  
   Precisión:      51.45%
   Épocas:        1
   Tasa Learning: 0.0500


Modelo IA: ArbolDecision   
   Precisión:      53.21%
   Épocas:        2
   Tasa Learning: 0.0500


Modelo IA: ArbolDecision   
   Precisión:      54.60%
   Épocas:        3
   Tasa Learning: 0.0500

SIMULACIÓN FINALIZADA CON ÉXITO
```



## Fase 3: Herencia y Especialización de Modelos de IA

En esta etapa del proyecto, se ha expandido el ecosistema de simulación mediante la aplicación de conceptos avanzados de Programación Orientada a Objetos (POO), específicamente **Herencia** y **Polimorfismo por sobreescritura**.

### Reutilización de Código y Uso de `super`
Se definieron tres subclases concretas (`RedNeuronal`, `ArbolDecision` y `ModeloRegresion`) que heredan directamente de la superclase abstracta `ModeloIA`. 

* **Acoplamiento y Constructores:** Para respetar el principio de encapsulamiento, los atributos base (`nombre`, `tasaAprendizaje`, etc.) se mantuvieron estrictamente privados (`private`) en la superclase. La inicialización del estado base desde las subclases se logró mediante la instrucción `super(nombre, tasaAprendizaje)`, invocando directamente al constructor protegido de la clase madre en la primera línea de los constructores hijos.
* **Especialización y Polimorfismo:** Cada modelo añade un atributo particular que define su comportamiento o estructura. Para mostrar estas métricas de forma limpia, se sobreescribió el método `mostrarMetricas()`. Utilizando la instrucción `super.mostrarMetricas()`, cada subclase delega primero la impresión de los datos generales a la superclase, para posteriormente ejecutar su propia lógica imprimiendo su atributo especializado.

### Evidencia de Ejecución en Consola
A continuación se muestra la salida real obtenida en la consola del simulador al iterar polimórficamente sobre el arreglo de modelos de IA:

```text
--- Métricas del Modelo: Perceptrón Multicapa ---
Tasa de Aprendizaje: 0.01
Precisión Actual: 0.0%
Épocas de Entrenamiento: 0
Capas Ocultas: 5

--- Métricas del Modelo: Clasificador de Iris ---
Tasa de Aprendizaje: 0.05
Precisión Actual: 0.0%
Épocas de Entrenamiento: 0
Profundidad Máxima: 15

--- Métricas del Modelo: Regresión Ridge ---
Tasa de Aprendizaje: 0.001
Precisión Actual: 0.0%
Épocas de Entrenamiento: 0
Coeficiente de Regularización: 0.01
```
