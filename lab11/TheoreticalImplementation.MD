# Descripción de la arquitectura:

* Los usuarios acceden al sitio web a través de un Application Load Balancer configurado en la subred pública.
* El ALB distribuye las solicitudes a las instancias EC2 en la subred privada. Estas instancias ejecutan la lógica de la
  aplicación.
* Las instancias EC2 acceden a S3 para recuperar o almacenar contenido estático, como imágenes de usuario.
* Todas las métricas y registros se envían a Amazon CloudWatch para monitoreo.
* Las políticas IAM aseguran que solo los roles definidos puedan interactuar con los servicios, minimizando riesgos de
  seguridad.