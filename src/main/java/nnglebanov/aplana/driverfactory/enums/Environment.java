package nnglebanov.aplana.driverfactory.enums;


public enum Environment {
    LOCAL("local"),REMOTE("remote");

    private Environment(String name)
    {
        this.name=name;
    }

    private final String name;

    @Override
    public String toString() {
        return this.name;
    }

}