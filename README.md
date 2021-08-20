Repositorio que contiene clases de utilidades, clases con tricks de distintos frameworks y distintas clases elaboradas, desarrolladas en lenguaje Java y agrupadas tanto por proyecto, como por framework.



# **SPRING FRAMEWORK**

Clases utiles desarrolladas aprovechando las ventajas que ofrece el framework de Spring.

# **BOARDING**

Boarding consiste en un proyecto de desarrollo de una aplicación Web orientada a la gestión de recursos de una inmobiliaria. 
Este proyecto se desarrolló en un plazo de 8 meses, siguiendo los estándares de la *arquitectura hexagonal*, con *Maven* como gestor de dependencias, *Jenkins* para el tratar el despliegue continuo de la app, *Docker* para despliegue de contenedores, y otras muchas herramientas. 
Para el desarrollo de este proyecto se utilizó **Spring Framework**.
Este proyecto se nutría de una base de datos MySql.

En este repositorio se encuentran las clases que podrían ser adaptadas para su uso en otros proyectos similares.

# **STAFFIT**

Proyecto orientado a la gestión de personal y recursos humanos de una empresa.
Este proyecto se desarrolló siguiendo la arquitectura *MVC* y desplegando distintos microservicios de Spring en contenedores docker, que interactuaban entre sí.
De esta forma, se formó una red de 6 microservicios correlacionados: **Zuul** (proxy-inverso), **Auth** (servicio de autenticación), **Eureka** (gestión de microservicios), **Employee** (microservicio para la gestión de recursos humanos), **Evaluations** (microservicio de automatización para postulaciones de trabajo), **Nfq-Synchronizer** (microservicio encargado de la migración de datos con un BD externa).

En este repositorio se encuentran las clases que podrían ser de utilidad, extraidas de este proyecto.

# **UTILITIES**

Clases generales de utilidades, escritas en Java.
