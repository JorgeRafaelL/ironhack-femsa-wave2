import spock.lang.Specification

class UserAuthenticationSpec extends Specification {

    def "should adjust UI width of 1024 pixels"() {
        given:
        def uiComponent = setupUIComponent(1024)

        expect:
        uiComponent.adjustsToScreenSize(1024)
    }

    // Simulando el método de configuración del componente UI
    UIComponent setupUIComponent(int width) {
        return new UIComponent(width)
    }

    // Ejemplo de clase de componente UI
    class UIComponent {
        int width

        UIComponent(int width) {
            this.width = width
        }


        boolean adjustsToScreenSize(int screenWidth) {
            return this.width == screenWidth
        }
    }

}