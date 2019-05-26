package be.perzival.danager.configuration.specific;

import javax.validation.constraints.NotBlank;

public class Commands {
    @NotBlank
    private String prefix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
