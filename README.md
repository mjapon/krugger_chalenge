# krugger_chalenge

Se desarrolló la aplicación en dos partes:

BACK:
Es una aplicación de spring según lo requerido, para el tema de autenticación se lo implementó mediante tokens.

FRONT:
Es una aplicación de angular

Url Swager:
http://localhost:8080/vacunas/api/v1.0/swagger-ui.html

API de la aplicación se despliega en la ruta:
http://localhost:8080/vacunas/api/v1.0

Para construir la aplicación se debe compilar las fuentes del back
cd vacunas
mvn clean package

Luego se debe levantar el contenedor configurado que se encuentra en la carpeta raiz, para ello ejecutar
docker-compose up


Usuarios de Prueba:
-------------------

La aplicación se inicia con dos usuarios de prueba los cuales son:

admin: admin@domain.com
clave: 1212

admin: empleado@domain.com
clave: 1212


Nota: Cuando se da de alta un nuevo empleado se crea por defecto un usuario para ese empleado
según lo indicando. Este usuario se genera con la clave por defecto: 1212 y con el rol de 'empleado'

La url de autenticación generada es:
http://localhost:8080/vacunas/api/v1.0/aut/authenticate

El body para esta petición es del tipo:
{
    "username":"admin@domain.com",
    "password":"1212"
}