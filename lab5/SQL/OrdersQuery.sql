SELECT Orders.OrderID, SUM(OrderDetails.Quantity * OrderDetails.UnitPrice) AS TotalPrice
FROM Orders
         JOIN OrderDetails ON Orders.OrderID = OrderDetails.OrderID
WHERE OrderDetails.Quantity > 10
GROUP BY Orders.OrderID;

/*
Mejoras:
1: Crear los índices necesarios
    - CREATE INDEX idx_orderdetails_quantity ON OrderDetails(Quantity);
    - CREATE INDEX idx_orders_orderid ON Orders(OrderID);
    - CREATE INDEX idx_orderdetails_orderid ON OrderDetails(OrderID);
2: Precalcular el precio total una sola vez con una expresión común de tabla (CTE)
3: Usar el comando EXPLAIN para verificar el plan de ejecución de la consulta
*/

--La consulta optimizada quedaría:
WITH OrderTotals AS (
    SELECT OrderDetails.OrderID,
           OrderDetails.Quantity * OrderDetails.UnitPrice AS TotalItemPrice
    FROM OrderDetails
    WHERE OrderDetails.Quantity > 10
)
SELECT Orders.OrderID, SUM(OrderTotals.TotalItemPrice) AS TotalPrice
FROM Orders
         INNER JOIN OrderTotals ON Orders.OrderID = OrderTotals.OrderID
GROUP BY Orders.OrderID;
