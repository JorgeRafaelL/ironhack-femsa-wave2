## Nota: 
Faltaron los iconos de cliente(dispositivos) que interactuan con el sistema, pero ya no me dejó agregar más cosas porque se acabó la prueba gratis.

# Arquitectura basada en Microservicios

### Microservicio Gestión de Usuarios

* Responsabilidad: gestionar perfiles de usuario, autenticación y autorización.
* Funcionalidades: CRUD de usuarios, autenticación de sesiones, autorización basada en roles y manejo de preferencias
  del usuario.
* Base de datos: una base de datos dedicada para la información del usuario, contraseñas encriptadas y tokens de sesión.

### Microservicio de Catálogo de Productos

* Responsabilidad: mantener y actualizar la lista de productos.
* Funcionalidades: administración de productos (creación, actualización, eliminación), búsqueda y categorización.
* Base de datos: base de datos independiente para productos con tablas de inventario, precios, descripciones y
  categorías.

### Microservicio de Procesamiento de Pedidos

* Responsabilidad: gestionar el flujo completo de los pedidos.
* Funcionalidades: manejo del carrito de compras, procesamiento de pedidos, gestión de pagos y actualización del
  historial de pedidos.
* Base de datos: base de datos específica para pedidos, incluyendo detalles de transacciones, métodos de pago y estado
  de cada pedido.

### Microservicio de Atención al Cliente

* Responsabilidad: administrar consultas, devoluciones, quejas y comentarios de los clientes.
* Funcionalidades: creación y manejo de tickets, integración con usuarios y pedidos para recuperar información
  relevante.
* Base de datos: base de datos independiente para tickets de soporte y feedback de clientes.

## Comunicación entre Microservicios

API REST: ideal para operaciones sincrónicas, como la consulta de productos o la verificación de un usuario.
Cola de Mensajes (Kafka): para manejar eventos y procesos asíncronos, como la actualización del inventario después de un
pedido o la notificación de un ticket de soporte.
Este enfoque ayuda a reducir la carga y mejora la escalabilidad.

## API GATEWAY

Para unificar las llamadas desde el frontend, utiliza un API Gateway. Esto permite que el cliente interactúe con un
único punto de entrada
y delega las solicitudes a los microservicios correspondientes. También ayuda con la gestión de autenticación,
autorización y enrutamiento de tráfico.

## Seguridad

Considera la implementación de autenticación centralizada y autorización mediante OAuth2 o JWT. Un sistema centralizado
de identidad (como un Identity Provider) simplifica la autenticación y proporciona tokens que pueden ser verificados por
cada microservicio.

## Monitoreo y Logging

Para tener una visión completa del sistema.
