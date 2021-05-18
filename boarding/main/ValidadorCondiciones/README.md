**Conditions Validator**

Esta clase permite comprobar si una entidad obtenida a partir de una entidad padre (o entidad principal) cumple una determinada condicion, partiendo de dos POJOs: Uno con la entidad principal, y otro la condición a comprobar. Para ejecutar esta clase, es necesario haber creado previamente los gettes correspondientes.

El objeto "Condición" debe seguir la siguiente estructura:

{

id: int
nombre: String (Nombre de la condición)
tabla: String (Tabla en la que se encuentra el objeto a validar)
campo: String (Campo para el que se quiere validar su valor [Debe tener su getter, y estar escrito en CamelCase])
valor: String (Valor esperado para el campo)
operador: String (Condición java para el campo. Ex: "**!=**")
desripcion: String (Descripción de la condición)
capasDesdeEntidadPrincipal: String (Capas desde la entidad principal, hasta la última entidad a evaluar separadas por comas. Ex: capa1,capa2,capa3...)

}



Mediante esta clase, podemos extraer un gran número de condiciones, siempre y cuando se espere que el todos los objetos finales que parten de la entidad principal cumplan la condición. Es decir, no permite un "Cuando *al menos un* elemento X cumpla Y".

Estas condiciones pueden hacer que se lance excepción o no, en función de un campo booleano: "Excluyente".

Un ejemplo de uso de esta clase es para añadir fracciones de texto a un documento en función de si el objeto al que se refiere el documento cumple unas determinadas condiciones o no.
