// Unnecessary computations in data processing
public List<int> ProcessData(List<int> data) {
    List<int> result = new List<int>();
    foreach (var d in data) {
        if (d % 2 == 0) {
            result.Add(d * 2);
        } else {
            result.Add(d * 3);
        }
    }
    return result;
}

//Codigo mejorado:
public List<int> ProcessData(List<int> data) {
    return data.Select(d => d % 2 == 0 ? d * 2 : d * 3).ToList();// Reemplazar el bucle foreach por Select,
    //en lugar de agregarlos manualmente, Select genera la lista resultante de manera implícita.
    //Uso del operador ternario dentro de Select
}
