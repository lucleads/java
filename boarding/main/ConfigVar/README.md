# **Config Var**

Clase para declarar un mapa con variables de negocio, obtenidas de la base de datos. Este mapa se incializa al inciar la apliación, por lo que para actualizar las variables declaradas, es necesario relanzar el main tras haber cambiado las variables en la base de datos. 

Para obtener el valor de estas variables, es suficiente con hacer una llamada a ConfigVar.get(key).
