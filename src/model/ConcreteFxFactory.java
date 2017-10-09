package model;

import model.general.AbstractFactory;

public class ConcreteFxFactory implements AbstractFactory {

    private FieldFx fieldFx;

    public void createField(int N) {
        fieldFx = new FieldFx(N);
    }

    public FieldFx getFieldFx() {
        return fieldFx;
    }
}
