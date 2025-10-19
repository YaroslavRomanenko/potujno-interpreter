package com.yarikcompany.potujno;

import java.util.List;
import java.util.Map;

public class PotujnoClass implements PotujnoCallable {
    final String name;
    final PotujnoClass superclass;
    private final Map<String, PotujnoFunction> methods;

    PotujnoClass(String name, PotujnoClass superclass, Map<String, PotujnoFunction> methods) {
        this.name = name;
        this.superclass = superclass;
        this.methods = methods;
    }

    PotujnoFunction findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }

        if (superclass != null) {
            return superclass.findMethod(name);
        }

        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        PotujnoInstance instance = new PotujnoInstance(this);

        PotujnoFunction initializer = findMethod("init");
        if (initializer != null) {
            initializer.bind(instance).call(interpreter, arguments);
        }

        return instance;
    }

    @Override
    public int arity() {
        PotujnoFunction initializer = findMethod("init");
        if (initializer == null) return 0;
        return initializer.arity();
    }

}
