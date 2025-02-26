ENUNCIADO:

EJERCICIO PROPUESTO SERVICIO DE RESPUESTAS A LAS PREGUNTAS.
SOCKTES STREAM. SOCKETS TCP.
 EL PROCESO SERVIDOR ES UNA APLICACIÓN MULTIHILO: EN CADA INSTANTE ES 
UN ÁRBOL N_ARIO DE HILOS. 
Realizar un proceso en el que:
 El proceso servidor atiende múltiples peticiones de conexión de los procesos clientes, el proceso 
con cada cliente consiste en atender a una batería de preguntas enviando UNA respuesta en 
contestación a la pregunta enviada por el cliente, en caso de no tener registrada en su base de datos 
la pregunta recibida desde el cliente indicará que no dispone de respuesta a la pregunta. El proceso 
con cada cliente admite varias preguntas hasta que el cliente envíe como pregunta “SALIR”.
 El proceso servidor arranca un hilo nuevo de la misma clase que los demás para cada cliente 
conectado. Habrá tantos hilos en ejecución como clientes conectados en cada instante.
--→ todos los hilos son de la misma clase-→”clones” realizan la misma tarea.
 El objeto de la clase Socket que el servidor pasa a cada hilo es único-distinto para cada cliente.
No es un objeto compartido (no tenemos que plantearnos problemas de sincronización ni de 
secuenciación de los hilos en ejecución, cada hilo recibe un Socket distinto, es un cliente distinto.
 Cada hilo servidor establece los streams de comunicación con su cliente, realizará operaciones de 
lectura y escritura con su cliente: recibe una serie de preguntas que tendrá que localizar en su base 
de preguntas_respuestas.-→ la información envíada/recibida a través del socket es de tipo cadena. 
• Una pregunta recibida podrá no estar registrada en la base de datos del servidor, entonces la
respuesta será la cadena “No disponemos de respuesta a su pregunta” 
• Una pregunta podrá tener registrada una o más de una respuesta, el proceso contestará con 
una de todas las respuestas encontradas en la base de datos , una al azar.
• El proceso con el servidor lo inicia el cliente solicitando una conexión con el servidor que
está a la escucha y lo finaliza el cliente al indicar como pregunta “SALIR”
El servidor dispone de una base de datos que contiene registradas las preguntas, las respuestas y la 
relación: una pregunta tiene 1 o más respuestas- una respuesta lo es de una o más preguntas. 
Relación de muchos a muchos, con participación obligatoria en ambos lados.
El almacenamiento de la información se hace única y exclusivamente a través de un procedimiento 
que tiene dos argumentos: cadena con la pregunta y cadena con la respuesta.