# Prestamos-Bersoft - descripcion.

El sistema de prestamos Bersoft es un sistema destinado a agilizar el trabajo de los duenos de rutas prestamos, dentro de el se ofrece la posibilidad de tener una modalidad de prestamos semanales o diarios, la cantidad de cuotas a pagar definidas por el usuario, ademas tambien del % de interes. Otra gran funcionalidad que ofrece este sistema es el poder gestionar los atrasos (viejos) y abonos de una manera clara y sencilla.

### ¿Como funciona?

1. Solo el usuario administrador tiene acceso al modulo de configuraciones, el debe de crear las configuraciones disponibles, asi como los usuario iniciales.
2. El usuario para comenzar a trabajar, debe de crear primero una zona de cobro, una zona tiene   una relacion de 1 a muchos con los cobradores, los clientes y prestamos.
3. Luego se debe de crear un cobrador y asiganarle sus zona correspondiente.
4. Antes de crear un prestamo debe de haber clientes, por ende se deben de crear los clientes.
5. Despues se procede a crear los prestamos, antes de, se debe de ingresar el codigo del cobrador al cual corresponderan los prestamos creados.
6. Luego de que todos los prestamos han sido creados se puede proceder a imprimir la lista de recibos correspondiente a la semana o dia de trabajo y se procede a imprimir.
7. En caso de necesitar registras los atrasos, se puede acceder al modulo de deudas y para abonar o saldar dichos atrasos en ese mismo modulo se puede realizar dicha accion.
8. El modulo de informacion esta habilitado para mostrar informacion correcta pero aun se esta trabajando en el diseno de sus reportes.

### Instalaciones necesarias:

* Se debe instalar xammp, en el puerto 3306 luego activar el servidor local Apache y mysql.
* Dentro de la carpeta **BaseDeDatos** esta la base de datos que se debe importar a mysql.
* Dentro de la carpeta **Lib** estan las librerias necesarias para ejecutar el proyecto.
* Para llevar el proyecto a produccion se debe de incluir dentro de la carpeta de distribucion  
la carpeta **Reportes** y el archivo **Conf.txt** y dentro de este ultimo agregar la ruta donde  
deseas que la copia de seguridad sea guardada.
* Se debe de agregar a las variables de entorno del sistema la carpeta **bin** de **mysql** para  
que el sistema pueda realizar correctamente la copia de seguridad.
* Se debe de utilizar impresoras LX-300 o similar con papel de tamano 8.5 x 3.67 (pulgadas)

### Credendiales

* **Usuario0** : Admin  **Contrasena**: Admin
* **Usuario1** : Berlis **Contrasena** : 1234

### Tecnologias utilizadas:

* JDK 8
* Jasper Resports
* Java Swing
* Mysql
* Desarrollado en Netbeans 8.2

## Imagenes del sistema 

![login](https://github.com/Berliss/Prestamos-Bersoft/blob/master/Imagenes%20Bersoft/login.png)
![home](https://github.com/Berliss/Prestamos-Bersoft/blob/master/Imagenes%20Bersoft/home.png)
![clientes](https://github.com/Berliss/Prestamos-Bersoft/blob/master/Imagenes%20Bersoft/mclientes.png)
![mbclientes](https://github.com/Berliss/Prestamos-Bersoft/blob/master/Imagenes%20Bersoft/mbclientes.png)
![prestamos](https://github.com/Berliss/Prestamos-Bersoft/blob/master/Imagenes%20Bersoft/mprestamo.png)
![cprestamo](https://github.com/Berliss/Prestamos-Bersoft/blob/master/Imagenes%20Bersoft/cprestamos.png)
![recibo](https://github.com/Berliss/Prestamos-Bersoft/blob/master/Imagenes%20Bersoft/imagenderecibo.png)

[Ver mas imagenes](https://github.com/Berliss/Prestamos-Bersoft/tree/master/Imagenes%20Bersoft)



