SELECT CustomerName FROM Customers WHERE City = 'London' ORDER BY CustomerName;
/*
Mejoras:
1: Crear los índices necesarios
    CREATE INDEX idx_city ON Customers(City);
2: Usar el comando EXPLAIN para verificar el plan de ejecución de la consulta
*/

--La consulta optimizada quedaría igual, solo con los puntos anteriores:
SELECT CustomerName FROM Customers WHERE City = 'London' ORDER BY CustomerName;