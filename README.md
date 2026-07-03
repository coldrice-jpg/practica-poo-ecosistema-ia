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
