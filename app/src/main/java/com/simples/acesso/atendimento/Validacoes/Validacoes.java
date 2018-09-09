package com.simples.acesso.atendimento.Validacoes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacoes {

    public static boolean email (String email) {
        Matcher matcher = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(email);
        return matcher.find();
    }

}
