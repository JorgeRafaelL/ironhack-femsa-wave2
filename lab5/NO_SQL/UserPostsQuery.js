db.posts
    .find({status: "active"}, {title: 1, likes: 1})
    .sort({likes: -1});

/*
Mejoras:
1: Crear un índice compuesto de status y likes (porque por este se ordena)
    db.posts.createIndex({ status: 1, likes: -1 });
2: Verificar el plan de ejecución
    db.posts.find({ status: "active" }, { title: 1, likes: 1 }).sort({ likes: -1 }).explain("executionStats");
*/

//La consulta optimizada quedaría igual, solo con los puntos anteriores:
db.posts
    .find({status: "active"}, {title: 1, likes: 1})
    .sort({likes: -1});