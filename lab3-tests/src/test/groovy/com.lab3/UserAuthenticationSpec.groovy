import spock.lang.Specification

import javax.security.sasl.AuthenticationException

class UserAuthenticationSpec extends Specification {

    def "Should succeed with correct credentials"() {
        given:
        def user = "validUser"
        def password = "validPass"

        when: //Simulación del Método authenticate
        def result = authenticate(user, password)

        then: //Usamos el bloque thenexpect para definir las expectativas del test.
        result
    }

    def "Should fail with wrong credentials"() {
        given:
        def user = "invalidUser"
        def password = "invalidPass"

        when://Simulación del Método authenticate
        def result = authenticate(user, password)

        then://Usamos el bloque thenexpect para definir las expectativas del test.
        !result
        def error = thrown(AuthenticationException)
        error.message == "wrong credentials"
    }

    static boolean authenticate(String username, String password) {
        return username == "validUser" && password == "validPass"
    }

}