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

## Fase 4: Polimorfismo mediante Interfaces y Desacoplamiento

En esta última fase, se transformó el simulador aislado en un ecosistema modular de componentes interconectados a través de **contratos abstractos (Interfaces)**.

### Beneficios del Desacoplamiento Mecánico
* **Interfaz `Entrenable`:** Permite que cualquier modelo arquitectónico se someta a optimizaciones de parámetros sin que el módulo ejecutor o pipeline conozca los algoritmos matemáticos subyacentes (`Backpropagation`, `Poda de Nodos` o `Gradiente Descendente`). El simulador interactúa de forma genérica con la abstracción.
* **Interfaz `Tokenizador`:** Consigue que la tubería de procesamiento de texto sea inmune a los cambios de lógica interna. Se demostró la capacidad de sustituir en tiempo de ejecución un algoritmo lineal basado en espacios (`TokenizadorBasico`) por un motor de parsing simulado basado en subpalabras (`TokenizadorHuggingFace`) mediante la reasignación de referencias de interfaz.

### Salida Rigurosa de la Consola
A continuación se adjunta el resultado de la ejecución del simulador integrado:

```text

Ejecutando Optimización Genérica en el Pipeline
[Perceptrón Multicapa] Backpropagation ejecutado en 5 capas. Precisión sube a: 65.25%
[Clasificador de Iris] División de nodos calculada (Profundidad Máx: 15). Precisión: 77.50%
[Regresión Ridge] Gradiente descendente aplicado con penalización 0.01. Precisión: 61.99%

Texto original: "La inteligencia artificial y el aprendizaje neuronal cambian el software."

[Configuración: TokenizadorBasico]
Tokens resultantes: [La, inteligencia, artificial, y, el, aprendizaje, neuronal, cambian, el, software.]

[Configuración: TokenizadorHuggingFace]
Tokens resultantes: [La, intel, ##igencia, artificial, y, el, apren, ##dizaje, neuro, ##nal, cambian, el, software.]
```

## Fase 5: Abstracción y Contratos Estructurales (`feature/abstraction-contracts`)

En esta fase, el simulador evoluciona hacia un diseño más robusto mediante la implementación de **Clases Abstractas** y **Contratos Estructurales (Interfaces)**, garantizando la integridad de los componentes del sistema.

### 1. Robustez del Diseño mediante Abstracción

Al transformar la superclase `ModeloIA` en una **Clase Abstracta**, se prohibió su instanciación directa (`new ModeloIA()`). En un entorno real de producción, no existe un "modelo de IA" genérico en ejecución; siempre se trata de una arquitectura o algoritmo específico (como una Red Neuronal o un Árbol de Decisión).

* **Prevención de Modelos Incompletos:** La abstracción impide que el sistema cree objetos vacíos o sin lógica matemática definida. Al declarar el método `public abstract void entrenar();`, delegamos la responsabilidad de la implementación exacta a las subclases concretas. Esto asegura que cualquier algoritmo que se incorpore al simulador cumpla obligatoriamente con el ciclo de vida del pipeline sin acoplarse a su resolución interna.

### 2. Diferencia entre Clases Abstractas e Interfaces en el Pipeline

Dentro del simulador conviven dos conceptos clave de abstracción pura que resuelven problemas distintos:

| Criterio | Clase Abstracta (`ModeloIA`) | Interfaz (`Tokenizador`) |
| :--- | :--- | :--- |
| **Propósito** | Define un molde conceptual e identidad para una jerarquía de objetos relacionados ("Es un..."). | Define una capacidad, comportamiento o contrato puro intercambiable ("Puede hacer..."). |
| **Estado y Atributos** | Puede contener atributos encapsulados (variables de instancia como `nombre` y `precision`) y lógica compartida (`mostrarMetricas()`). | No posee estado (variables de instancia). Solo declara firmas de métodos que actúan como capas puras. |
| **Herencia / Implementación** | Las subclases extienden (`extends`) una única superclase abstracta debido a la herencia simple en Java. | Las clases implementan (`implements`) una o múltiples interfaces, permitiendo un desacoplamiento total de estrategias en tiempo de ejecución. |

* **En el simulador:** `ModeloIA` unifica la identidad y el estado de los algoritmos de IA, mientras que `Tokenizador` actúa como una capa abstracta pura que permite intercambiar dinámicamente estrategias de procesamiento de texto (`TokenizadorBasico` frente a `TokenizadorHuggingFace`) en el pipeline de datos.

---

### 3. Salida de la Consola

A continuación, se detalla la traza de ejecución generada por la clase orquestadora `SimuladorIA`, demostrando la protección contra instanciación genérica, el entrenamiento polimórfico dirigido y el comportamiento diferenciado del pipeline de procesamiento de texto:

```text
PRUEBA DE ABSTRACCIÓN Y CONTRATOS
[OK] La superclase ModeloIA está protegida contra instanciaciones directas.

Ejecutando Entrenamiento Polimórfico
Modelo: Perceptrón Multicapa   | Precisión Actual: 70.00%
-> Entrenando Red Neuronal mediante Backpropagation...
Modelo: Perceptrón Multicapa   | Precisión Actual: 85.00%

Modelo: Random Forest Node   | Precisión Actual: 65.00%
-> Entrenando Árbol de Decisión calculando la Ganancia de Información...
Modelo: Random Forest Node   | Precisión Actual: 81.00%

Modelo: ElasticNet Linear    | Precisión Actual: 60.00%
-> Ajustando coeficientes de Regresión con regularización Lasso...
Modelo: ElasticNet Linear    | Precisión Actual: 64.00%

Pipeline de Procesamiento de Texto Abstracto
Usando: TokenizadorBasico
Tokens generados: [ 'Corelia' 'automatiza' 'los' 'procesos' 'académicos' 'universitarios.' ]

Usando: TokenizadorHuggingFace (Subwords/Signos)
Tokens generados: [ 'Corelia' ' ' 'automatiza' ' ' 'los' ' ' 'procesos' ' ' 'académicos' ' ' 'universitarios' '.' ' ' ]
```

## Fase 5: Abstracción y Contratos Estructurales (`feature/abstraction-contracts`)

En esta fase, el simulador evoluciona hacia un diseño más robusto mediante la implementación de **Clases Abstractas** y **Contratos Estructurales (Interfaces)**, garantizando la integridad de los componentes del sistema.

### 1. Robustez del Diseño mediante Abstracción
Al transformar la superclase `ModeloIA` en una **Clase Abstracta**, se prohibió su instanciación directa (`new ModeloIA()`). En un entorno real de producción, no existe un "modelo de IA" genérico en ejecución; siempre se trata de una arquitectura o algoritmo específico (como una Red Neuronal o un Árbol de Decisión).

* **Prevención de Modelos Incompletos:** La abstracción impide que el sistema cree objetos vacíos o sin lógica matemática definida. Al declarar el método `public abstract void entrenar();`, delegamos la responsabilidad de la implementación exacta a las subclases concretas. Esto asegura que cualquier algoritmo que se incorpore al simulador cumpla obligatoriamente con el ciclo de vida del pipeline sin acoplarse a su resolución interna.

### 2. Diferencia entre Clases Abstractas e Interfaces en el Pipeline
Dentro del simulador conviven dos conceptos clave de abstracción pura que resuelven problemas distintos:

| Criterio | Clase Abstracta (`ModeloIA`) | Interfaz (`Tokenizador`) |
| :--- | :--- | :--- |
| **Propósito** | Define un molde conceptual e identidad para una jerarquía de objetos relacionados ("Es un..."). | Define una capacidad, comportamiento o contrato puro intercambiable ("Puede hacer..."). |
| **Estado y Atributos** | Puede contener atributos encapsulados (variables de instancia como `nombre` y `precision`) y lógica compartida (`mostrarMetricas()`). | No posee estado (variables de instancia). Solo declara firmas de métodos que actúan como capas puras. |
| **Herencia / Implementación** | Las subclases extienden (`extends`) una única superclase abstracta debido a la herencia simple en Java. | Las clases implementan (`implements`) una o múltiples interfaces, permitiendo un desacoplamiento total de estrategias en tiempo de ejecución. |

---

## Fase 6: Orquestación con Colecciones Dinámicas (`feature/dynamic-collections`)

Esta fase resuelve las limitaciones de escalabilidad de la arquitectura previa al migrar de arreglos rígidos de tamaño estático (`[]`) a estructuras de datos dinámicas provistas por el **Java Collections Framework**. Esto permite al simulador gestionar cargas y pipelines dinámicos en tiempo de ejecución.

### 1. Gestión Dinámica del Ciclo de Vida (`List`)
La clase orquestadora `SimuladorIA` centraliza los componentes sustituyendo el arreglo fijo por una lista genérica:
* **Estructura:** `List<ModeloIA> inventarioModelos = new ArrayList<>();`
* **Impacto:** Permite la adición orgánica de nuevos algoritmos (`RedNeuronal`, `ArbolDecision`, `ModeloRegresion`) en tiempo de ejecución mediante `.add()`, simulando un entorno de producción donde los modelos se despliegan de forma elástica sin necesidad de reiniciar la infraestructura ni redimensionar contenedores rígidos.

### 2. Catálogo Indexado de Componentes (`Map`)
Para evitar el uso de lógica condicional acoplada (`if/else` duros) o variables sueltas al seleccionar procesadores de lenguaje natural, se estructuró un catálogo centralizado en memoria:
* **Estructura:** `Map<String, Tokenizador> catalogoTokenizadores = new HashMap<>();`
* **Impacto:** Los procesadores se registran bajo claves semánticas únicas (`"BASICO"`, `"HUGGING_FACE"`). El pipeline recupera el componente deseado de forma directa con `.get(clave)`, logrando un desacoplamiento absoluto en la arquitectura del procesamiento de texto.

### 3. Flexibilidad y Reglas de Negocio Avanzadas (Filtrado)
Gracias a las ventajas de legibilidad y manipulación que ofrece el framework de colecciones, se incorporó un módulo de auditoría encargado de filtrar estructuras en base a umbrales específicos de negocio (por ejemplo, reportar únicamente aquellos modelos cuya precisión post-entrenamiento sea estrictamente superior al 80%).

---

### 4. Salida de la Consola

A continuación, se detalla la traza de ejecución generada por la clase orquestadora `SimuladorIA` en esta fase, reflejando el inventario dinámico, la resolución por clave del mapa y el filtrado avanzado:

```text
Ejecutando Ciclo de Vida y Entrenamiento Dinámico
[Estado Inicial]
Modelo: Perceptrón Multicapa   | Precisión Actual: 70.00%
-> Entrenando Red Neuronal mediante Backpropagation...
[Estado Post-Entrenamiento]
Modelo: Perceptrón Multicapa   | Precisión Actual: 85.00%

[Estado Inicial]
Modelo: Random Forest Node   | Precisión Actual: 65.00%
-> Entrenando Árbol de Decisión calculando la Ganancia de Información...
[Estado Post-Entrenamiento]
Modelo: Random Forest Node   | Precisión Actual: 81.00%

[Estado Inicial]
Modelo: ElasticNet Linear    | Precisión Actual: 60.00%
-> Ajustando coeficientes de Regresión con regularización Lasso...
[Estado Post-Entrenamiento]
Modelo: ElasticNet Linear    | Precisión Actual: 64.00%

[Estado Inicial]
Modelo: Deep CNN Vision      | Precisión Actual: 55.00%
-> Entrenando Red Neuronal mediante Backpropagation...
[Estado Post-Entrenamiento]
Modelo: Deep CNN Vision      | Precisión Actual: 80.00%

Pipeline de Procesamiento de Texto Abstracto
Componente recuperado con éxito mediante la clave: HUGGING_FACE
Tokens generados: [ 'Corelia' ' ' 'optimiza' ' ' 'la' ' ' 'gestión' ' ' 'del' ' ' 'tiempo' ' ' 'de' ' ' 'los' ' ' 'estudiantes' '.' ' ' ]

Reporte de Auditoría: Modelos de Alta Precisión
Filtrando modelos con precisión estrictamente superior al 80%
 -> [APROBADO] Perceptrón Multicapa (85.00%)
 -> [APROBADO] Random Forest Node (81.00%)
```

## Fase 7: Mecanismos de Control de Errores y Tolerancia a Fallos (Exception Handling)

### Robustez y Resiliencia en Arquitecturas de Integración de IA
En entornos de producción, los sistemas que integran Inteligencia Artificial se enfrentan a una alta volatilidad debido a datos externos inestables, respuestas de APIs mal formateadas o configuraciones de hiperparámetros fuera de rango (como tasas de aprendizaje negativas). Si una arquitectura no controla estos eventos de manera proactiva, un solo fallo local puede propagarse en cascada, corromper el estado de los modelos en memoria y provocar el colapso abrupto de todo el servicio.

El diseño e implementación de **Excepciones Personalizadas** (como `IAComponentException`) eleva drásticamente la robustez y resiliencia del software por las siguientes razones:
1. **Aislamiento del Fallo (Bulkheading):** Permite interrumpir inmediatamente un flujo anómalo de configuración corrupta antes de que afecte a otros componentes del ecosistema.
2. **Semántica y Diagnóstico Claro:** Al lanzar una excepción específica en lugar de errores genéricos del sistema (como `NullPointerException`), se proporciona un contexto preciso de qué regla de negocio se violó.
3. **Flujos de Rescate y Continuidad:** Obliga al orquestador a capturar los fallos mediante bloques `try-catch`, lo que permite desviar la anomalía hacia un flujo de contingencia seguro, garantizando que el hilo principal del programa nunca detenga su operación.

---

## Resultados de Ejecución y Reportes de Consola

Al ejecutar la clase principal `SimuladorIA` para evaluar las pruebas de robustez ante datos corruptos, el sistema arroja la siguiente salida estructurada en la terminal. Aquí se observa nítidamente cómo se capturan de forma controlada los mensajes de error personalizados y cómo la ejecución continúa de forma segura hasta procesar los bloques `finally` de auditoría sin interrumpir el ciclo de vida del programa:

```text
INICIANDO SIMULADOR DE ECOSISTEMA IA (CON CONTROL DE ERRORES)
[Ejecución] Intentando instanciar RedNeuronal con tasa errónea...
[REPORTE DE FALLO] Error Crítico: La tasa de aprendizaje -0.05 está fuera del rango permitido (0.0 - 1.0).
[AUDITORÍA] Finalizada la verificación de inicialización del modelo.

[Ejecución] Intentando recuperar un tokenizador no registrado...
[REPORTE DE FALLO] Error de Configuración: El componente NLP (Tokenizador) 'OPEN_AI' no se encuentra registrado en el catálogo.
[AUDITORÍA] Finalizada la verificación del catálogo de componentes NLP.

[Ejecución] Intentando flujo con parámetros válidos...
>> Modelo creado con éxito: Transformer Core (Tasa: 0.01)
>> Tokenizador activo: Tokenizer de procesamiento síncrono para BERT
[AUDITORÍA] Finalizada la auditoría del flujo estable.


SIMULACIÓN FINALIZADA CON ÉXITO: El hilo principal no colapsó
```
