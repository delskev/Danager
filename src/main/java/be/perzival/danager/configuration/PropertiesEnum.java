package be.perzival.danager.configuration;

/**
 * Created by Perzival on 01/08/2017.
 */
public enum PropertiesEnum {
    PREFIX("commands.prefix"),
    LUCIOLE("listener.luciole"),
    NEWCONNECTION("listener.newconnection");

    private String value;

    PropertiesEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public String value() {
        return this.value;
    }
}
