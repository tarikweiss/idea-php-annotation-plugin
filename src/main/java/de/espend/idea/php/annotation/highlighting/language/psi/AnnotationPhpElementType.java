package de.espend.idea.php.annotation.highlighting.language.psi;

import com.intellij.psi.tree.IElementType;
import de.espend.idea.php.annotation.highlighting.language.AnnotationPhpLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class AnnotationPhpElementType extends IElementType {

    public AnnotationPhpElementType(@NonNls @NotNull String debugName) {
        super(debugName, AnnotationPhpLanguage.INSTANCE);
    }
}
