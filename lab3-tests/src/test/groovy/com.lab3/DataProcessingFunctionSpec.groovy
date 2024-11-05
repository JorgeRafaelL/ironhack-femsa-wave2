import spock.lang.Specification

class DataProcessingFunctionSpec extends Specification {

    def dataService = Mock(DataService)

    def "Should process data successfully"() {
        given:
        def data = fetchData(true)

        when:
        processData(data)

        then:
        data.processedSuccessfully
    }

    def "Should handle processing errors"() {
        given:
        def data = null

        when:
        processData(data)

        then:
        def error = thrown(RuntimeException)
        error.message == "Data processing error"
    }

    //Simulamos método para obtener datos
    Data fetchData(boolean processedSuccessfully) {
        // Implementación de Mock para obtener datos
        return dataService.fetchData(processedSuccessfully)
    }

    void processData(Data data) {
        // Implementación de la lógica de procesamiento de datos o simulación
        if (data == null) {
            throw new RuntimeException("Data processing error")
        }
        data.processedSuccessfully = true
    }

    // Clase de datos
    class Data {
        boolean processedSuccessfully

        Data(boolean processedSuccessfully) {
            this.processedSuccessfully = processedSuccessfully
        }

        boolean getProcessedSuccessfully() {
            return processedSuccessfully
        }

        void setProcessedSuccessfully(boolean processedSuccessfully) {
            this.processedSuccessfully = processedSuccessfully
        }
    }

}