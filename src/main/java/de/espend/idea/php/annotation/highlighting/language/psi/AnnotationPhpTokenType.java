package de.espend.idea.php.annotation.highlighting.language.psi;

import com.intellij.psi.tree.IElementType;
import de.espend.idea.php.annotation.highlighting.language.AnnotationPhpLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class AnnotationPhpTokenType extends IElementType {

    public AnnotationPhpTokenType(@NonNls @NotNull String debugName) {
        super(debugName, AnnotationPhpLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "AnnotationPhpTokenType." + super.toString();
    }
}
