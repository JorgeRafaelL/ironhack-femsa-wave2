/*PLAN secureDataCommunication:
    IMPLEMENT SSL/TLS for all data in transit
USE encrypted storage solutions for data at rest
ENSURE all data exchanges comply with HTTPS protocols*/

/*Analisis SAST
1: Asegurar HTTPS para todos los intercambios de datos
    1.1 Mitigación: Implementar HTTPS con validación de certificados para todos los puntos de intercambio de datos
2: No se especifica las versiones de TLS
    2.1 Mitigación: Configurar TLS 1.2 o superior y usar ciphers robustos

Análisis DAST
1:   Pruebas de interceptación de tráfico
    1.1 Se podría intentar interceptar el tráfico, La implementación de SSL/TLS debería prevenir cualquier intento de interceptación
 */