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
