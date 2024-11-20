# Propuesta:

## Roles IAM:

### Desarrolladores:

* Acceso limitado a los servicios requeridos.
* Políticas personalizadas que prohíben la creación o eliminación de instancias de producción.

### Administradores:

* Acceso completo a la gestión de infraestructura.
* Uso de MFA para seguridad adicional.

### Servidores de aplicación:

* Crear un IAM Role para las instancias EC2 con permisos de acceso a S3 para almacenar y recuperar datos.
* Sin permisos administrativos.