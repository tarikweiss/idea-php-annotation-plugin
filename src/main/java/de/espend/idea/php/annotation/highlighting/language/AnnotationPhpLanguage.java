package de.espend.idea.php.annotation.highlighting.language;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.Nullable;

public class AnnotationPhpLanguage extends Language {

    public static final AnnotationPhpLanguage INSTANCE = new AnnotationPhpLanguage();

    private AnnotationPhpLanguage() {
        super("AnnotationPhp");
    }

    @Override
    public @Nullable LanguageFileType getAssociatedFileType() {
        return AnnotationPhpFileType.INSTANCE;
    }
}
