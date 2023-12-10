# Demo BCI

Esta demo para insertar usuario usando Spring en una tabla H2, el request para insertar un nuevo usuario debe considerar el siguiente formato:

     { 
    "name": "string", 
    "email": "string", 
    "password": "string", 
    "phones": 	[
		    { 
		    "number": "string", 
		    "citycode": "string", 
		    "contrycode": "string" 
		    }
      		] 
     }

Para usar el proyecto, sigue estos pasos:
1.  Levanta el proyecto:
2.  Abre el swagger en tu navegador:
	http://localhost:8080/swagger-ui/index.html#
3.  Usa el swagger para probar el proyecto.
4.  Para consultar los usuarios ingresados, usa el siguiente método: `GET /api/usuarios/usuariosregistados`
5. Para ingresar / guardar un nuevo usuario: `POST /api/usuarios/registro`
