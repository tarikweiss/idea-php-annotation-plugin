package de.espend.idea.php.annotation.highlighting.language;

import com.intellij.lexer.FlexAdapter;

public class AnnotationPhpLexerAdapter extends FlexAdapter {
    public AnnotationPhpLexerAdapter() {
        super(new _AnnotationPhpLexer());
    }
}
