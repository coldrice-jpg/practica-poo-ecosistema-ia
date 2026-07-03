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
