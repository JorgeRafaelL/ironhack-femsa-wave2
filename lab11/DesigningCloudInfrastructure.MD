# Propuesta:

## Instancias:

### Utilizar AWS EC2 para alojar la aplicación web.

* Grupo de instancias EC2 detrás de un Elastic Load Balancer (ELB) para distribuir tráfico.
* Configuración en múltiples zonas de disponibilidad para alta disponibilidad.
* Configuración de Auto Scaling Group (ASG) para garantizar escalabilidad.

## Storage:

### Utilizar Amazon S3

* Almacenar contenido estático (imágenes, videos, archivos).
* Almacenar backups de base de datos periódicamente.

## Configuraciones de red:

* Crear Virtual Private Cloud (VPC) con subredes públicas y privadas:
    - Subred pública: Para el balanceador de carga (ELB).
    - Subred privada: Para las instancias EC2 y la base de datos.
* Configurar Security Groups para controlar el tráfico entrante y saliente.
* Utilizar una NAT Gateway para permitir que las instancias en subredes privadas accedan a Internet.
