package de.espend.idea.php.annotation.highlighting.file;

import com.intellij.lang.Language;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.MultiplePsiFilesPerDocumentFileViewProvider;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.templateLanguages.TemplateDataElementType;
import com.intellij.psi.templateLanguages.TemplateLanguageFileViewProvider;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.php.lang.PhpLanguage;
import de.espend.idea.php.annotation.highlighting.language.AnnotationPhpLanguage;
import de.espend.idea.php.annotation.highlighting.language.psi.AnnotationPhpElementType;
import de.espend.idea.php.annotation.highlighting.language.psi.AnnotationPhpFile;
import gnu.trove.THashSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Set;

public class AnnotationPhpFileViewProvider extends MultiplePsiFilesPerDocumentFileViewProvider implements TemplateLanguageFileViewProvider {

    public AnnotationPhpFileViewProvider(@NotNull PsiManager manager, @NotNull VirtualFile virtualFile, boolean eventSystemEnabled) {
        super(manager, virtualFile, eventSystemEnabled);
    }

    @Override
    public @NotNull
    Language getBaseLanguage() {
        System.out.println("getBaseLanguage()");
        return AnnotationPhpLanguage.INSTANCE;
    }

    @Override
    public @NotNull
    Language getTemplateDataLanguage() {
        System.out.println("getTemplateLanguage()");
        return PhpLanguage.INSTANCE;
    }

    @Override
    public boolean hasLanguage(@NotNull Language language) {
        System.out.println("Has language?");
        return language == PhpLanguage.INSTANCE || language == AnnotationPhpLanguage.INSTANCE;
    }

    @Override
    public @NotNull
    Set<Language> getLanguages() {
        System.out.println("getLanguages()");
        return new THashSet<>(Arrays.asList(AnnotationPhpLanguage.INSTANCE, PhpLanguage.INSTANCE));
    }

    @Override
    protected @NotNull
    MultiplePsiFilesPerDocumentFileViewProvider cloneInner(@NotNull VirtualFile fileCopy) {
        System.out.println("cloneInner()");
        return new AnnotationPhpFileViewProvider(this.getManager(), fileCopy, false);
    }

    @Override
    protected @Nullable
    PsiFile createFile(@NotNull Language lang) {
        System.out.println("Create file!");
        if (lang == AnnotationPhpLanguage.INSTANCE) {
            return new AnnotationPhpFile(this);
        }

        return super.createFile(lang);
    }
}
