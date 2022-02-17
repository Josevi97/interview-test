# interview-

La aplicación se divide empezando desde la capa más cercana a la base de datos, de la siguiente manera:
- Repositorios, interfaces que extienden de JPA.
- Servicios, se comunican con los repositorios para interactuar con la base de datos. A su vez, los servicios se encargarán de comprobar que se cumplen las precondiciones necesarias para que la funcion se ejecute correctamente, en caso de no ser así, lanzarían excepciones.
- Controladores, son los encargados de dirigir a los servicios correspondientes según las peticiones. Para los controladores (solo hay uno en este caso), los repositorios son completamente invisibles, dejando este trabajo a la capa de servicios.
- Entidades, son los objetos que devuelve la api.
- Beans, son los objetos que entran por la api, de esta forma no me tengo que preocupar de que se intente modificar un campo como por ejemplo el id de un producto que ya existe. 
- Constantes, han sido utilizados como "metadatos" para fijar los requisitos de las columnas de una tabla, así como para comprobar si un bean es valido.

Explicación general:
1. Se realiza una consulta, supongamos de tipo post en /products.
2. Esta llamada requiere un bean, en este caso un ProductBean, que para este ejemplo, no tiene mucha diferencia en comparación con la entidad.
3. El controlador dirige al servicio correspondiente y llama a la función que se encarga de crear un producto nuevo.
4. Se realizan ciertas precondiciiones para comprobar que se cumplen con ciertos requisitos. Por ejemplo, comprueba que el bean sea valido o que el producto no exista, de no ser así, se lanzarían excepciones (InvalidProductDataException o ProductAlreadyExistsException, en este caso). De no saltar ninguna exception, el bean se transformaría en una entidad mediante un metodo que tiene y se almacenaría en la base de datos haciendo uso del repositorio correspondiente. En este caso, ProductRepository.
6. El servicio le devuelve la entidad al controlador y finalmente responde con este objeto, en caso de que no haya habido ningun error, haciendo uso del ResponseEntity.

Destacar que producto tiene la columna "code" como campo único.
