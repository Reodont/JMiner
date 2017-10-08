package model;

public class ConcreteFxFactory implements AbstractFactory {

    private FieldFx fieldFx;

    public void createField() {
        fieldFx = new FieldFx();
    }

    public FieldFx getFieldFx() {
        return fieldFx;
    }
}
