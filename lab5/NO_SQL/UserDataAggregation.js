db.users.aggregate([
    {$match: {status: "active"}},
    {$group: {_id: "$location", totalUsers: {$sum: 1}}},
]);
/*
Mejoras:
1: Crear un índice de status y uno compuesto de status y location
    - db.users.createIndex({ status: 1 });
    - db.users.createIndex({ status: 1, location: 1 });
2: Verificar el plan de ejecución
db.users.aggregate([
    {$match: {status: "active"}},
    {$group: {_id: "$location", totalUsers: {$sum: 1}}},
]).explain("executionStats");
*/

//La consulta optimizada quedaría igual, solo con los puntos anteriores:
db.users.aggregate([
    {$match: {status: "active"}},
    {$group: {_id: "$location", totalUsers: {$sum: 1}}},
]);
