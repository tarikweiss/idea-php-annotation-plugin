package de.espend.idea.php.annotation.highlighting.language;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.php.lang.highlighter.PhpHighlightingData;
import de.espend.idea.php.annotation.highlighting.language.psi.AnnotationPhpTypes;
import org.jetbrains.annotations.NotNull;

public class AnnotationPhpSyntaxHighlighter extends SyntaxHighlighterBase {
    @Override
    public @NotNull
    Lexer getHighlightingLexer() {
        return new AnnotationPhpLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(AnnotationPhpTypes.SURROUNDING_CODE)) {
            return pack(HighlighterColors.NO_HIGHLIGHTING);
        }
        if (tokenType.equals(AnnotationPhpTypes.ANNOTATION_AT)) {
            return pack(PhpHighlightingData.ATTRIBUTE);
        }
        if (tokenType.equals(AnnotationPhpTypes.ANNOTATION_CLASS)) {
            return pack(PhpHighlightingData.ATTRIBUTE);
        }
        if (tokenType.equals(AnnotationPhpTypes.ANNOTATION_NAMESPACE_NAME)) {
            return pack(PhpHighlightingData.ATTRIBUTE);
        }
        if (tokenType.equals(AnnotationPhpTypes.ANNOTATION_NAMESPACE_OPERATOR)) {
            return pack(PhpHighlightingData.ATTRIBUTE);
        }
        if (tokenType.equals(AnnotationPhpTypes.CLASS_FIELD)) {
            return pack(PhpHighlightingData.VAR);
        }
        if (tokenType.equals(AnnotationPhpTypes.IDENTIFIER)) {
            return pack(PhpHighlightingData.IDENTIFIER);
        }
        if (tokenType.equals(AnnotationPhpTypes.COMMA)) {
            return pack(PhpHighlightingData.COMMA);
        }
        if (tokenType.equals(AnnotationPhpTypes.STRING)) {
            return pack(PhpHighlightingData.STRING);
        }
        if (tokenType.equals(AnnotationPhpTypes.NUMBER)) {
            return pack(PhpHighlightingData.NUMBER);
        }
        if (tokenType.equals(AnnotationPhpTypes.NULL_KEYWORD)) {
            return pack(PhpHighlightingData.KEYWORD);
        }
        if (tokenType.equals(AnnotationPhpTypes.CONSTANT)) {
            return pack(PhpHighlightingData.CONSTANT);
        }
        if (tokenType.equals(AnnotationPhpTypes.CLASS_KEYWORD)) {
            return pack(PhpHighlightingData.KEYWORD);
        }
        return pack(HighlighterColors.NO_HIGHLIGHTING);
    }

}
