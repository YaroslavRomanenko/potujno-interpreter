package com.yarikcompany.potujno;

import java.util.List;

public interface PotujnoCallable {
    int arity();
    Object call(Interpreter interpreter, List<Object> arguments);
}

