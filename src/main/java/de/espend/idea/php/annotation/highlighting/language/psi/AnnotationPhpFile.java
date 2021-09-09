package de.espend.idea.php.annotation.highlighting.language.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import de.espend.idea.php.annotation.highlighting.language.AnnotationPhpFileType;
import de.espend.idea.php.annotation.highlighting.language.AnnotationPhpLanguage;
import org.jetbrains.annotations.NotNull;

public class AnnotationPhpFile  extends PsiFileBase {

    public AnnotationPhpFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, AnnotationPhpLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return AnnotationPhpFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Annotation PHP File";
    }
}
