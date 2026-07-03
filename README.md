## Fase 6: Orquestación con Colecciones Dinámicas

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
