## Fase 3: Herencia y Especialización de Modelos de IA

En esta etapa del proyecto, se ha expandido el ecosistema de simulación mediante la aplicación de conceptos avanzados de Programación Orientada a Objetos (POO), específicamente **Herencia** y **Polimorfismo por sobreescritura**.

### Reutilización de Código y Uso de `super`
Se definieron tres subclases concretas (`RedNeuronal`, `ArbolDecision` y `ModeloRegresion`) que heredan directamente de la superclase abstracta `ModeloIA`. 

* **Acoplamiento y Constructores:** Para respetar el principio de encapsulamiento, los atributos base (`nombre`, `tasaAprendizaje`, etc.) se mantuvieron estrictamente privados (`private`) en la superclase. La inicialización del estado base desde las subclases se logró mediante la instrucción `super(nombre, tasaAprendizaje)`, invocando directamente al constructor protegido de la clase madre en la primera línea de los constructores hijos.
* **Especialización y Polimorfismo:** Cada modelo añade un atributo particular que define su comportamiento o estructura. Para mostrar estas métricas de forma limpia, se sobreescribió el método `mostrarMetricas()`. Utilizando la instrucción `super.mostrarMetricas()`, cada subclase delega primero la impresión de los datos generales a la superclase, para posteriormente ejecutar su propia lógica imprimiendo su atributo especializado.

### Evidencia de Ejecución en Consola
A continuación se muestra la salida real obtenida en la consola del simulador al iterar polimórficamente sobre el arreglo de modelos de IA:

```text
Métricas del Modelo: Perceptrón Multicapa
Precisión Actual: 0.0%
Épocas de Entrenamiento: 0
Capas Ocultas: 5

Métricas del Modelo: Clasificador de Iris
Tasa de Aprendizaje: 0.05
Precisión Actual: 0.0%
Épocas de Entrenamiento: 0
Profundidad Máxima: 15

Métricas del Modelo: Regresión Ridge
Tasa de Aprendizaje: 0.001
Precisión Actual: 0.0%
Épocas de Entrenamiento: 0
Coeficiente de Regularización: 0.01
