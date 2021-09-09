package de.espend.idea.php.annotation.highlighting.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.jetbrains.php.lang.PhpLanguage;
import de.espend.idea.php.annotation.PhpAnnotationIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class AnnotationPhpFileType extends LanguageFileType {

    public static final AnnotationPhpFileType INSTANCE = new AnnotationPhpFileType();

    protected AnnotationPhpFileType() {
        super(PhpLanguage.INSTANCE, true);
        System.out.println("Called me bby");
    }

    @NotNull
    @Override
    public String getName() {
        System.out.println("file type name");
        return "Annotation PHP File";
    }

    @NotNull
    @Override
    public String getDescription() {
        System.out.println("file type desc");
        return "Annotation PHP File description";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        System.out.println("file type ext");
        return "php";
    }

    @Override
    public Icon getIcon() {
        System.out.println("file type ico");
        return PhpAnnotationIcons.DOCTRINE;
    }


}
