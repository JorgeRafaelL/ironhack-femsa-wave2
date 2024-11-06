/*FUNCTION authenticateUser(username, password):
QUERY database WITH username AND password
IF found RETURN True
ELSE RETURN False*/

/*Analisis SAST
1: La consulta a base de datos de usuario y contraseña concatenados permite inyección SQL
    1.1 Mitigación: Usa consultas preparadas o declaraciones parametrizadas.
2: Almacenamiento de contraseñas en texto plano
    2.1 Mitigación: Guardar las contraseñas en la base de datos encriptadas
3: Autenticación Insegura, no hay límites de intentos de autenticación
    3.1 Mitigación: Implementar límites de intentos

Análisis DAST
1: Pruebas de Inyección SQL
    1.1 Ejemplo: probar enviando username = "admin", Si el sistema es seguro, debería rechazar estos valores
2:  Pruebas de autenticación y autorización
    2.1 Se pueden simular ataques de fuerza bruta para verificar si el sistema tiene límites de intentos, debería limitar los intentos
 */