package com.yarikcompany.potujno;

import java.util.HashMap;
import java.util.Map;

public class PotujnoInstance {
    private PotujnoClass potujnoClass;
    private final Map<String, Object> fields = new HashMap<>();

    PotujnoInstance(PotujnoClass potujnoClass) {
        this.potujnoClass = potujnoClass;
    }

    Object get(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }

        PotujnoFunction method = potujnoClass.findMethod(name.lexeme);
        if (method != null) return method.bind(this);

        throw new RuntimeError(name, "Undefined property '" + name.lexeme + "'.");
    }

    void set(Token name, Object value) {
        fields.put(name.lexeme, value);
    }

    @Override
    public String toString() {
        return potujnoClass.name + " instance";
    }
}
