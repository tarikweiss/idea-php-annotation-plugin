package de.espend.idea.php.annotation.highlighting.language;

import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.ex.util.LayerDescriptor;
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.php.lang.PhpLanguage;
import de.espend.idea.php.annotation.highlighting.language.psi.AnnotationPhpTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AnnotationPhpDocSyntaxHighlighter extends LayeredLexerEditorHighlighter {

    public AnnotationPhpDocSyntaxHighlighter(@Nullable Project project, @Nullable VirtualFile virtualFile, @NotNull EditorColorsScheme scheme) {
        super(new AnnotationPhpSyntaxHighlighter(), scheme);
//        FileType fileType = PlainTextFileType.INSTANCE;

//        if (project != null && virtualFile != null) {
//            Language language = TemplateDataLanguageMappings.getInstance(project).getMapping(virtualFile);
//            fileType = PhpFileType.INSTANCE;
//            if (language != null) {
//                fileType = language.getAssociatedFileType();
//            }
//        }

        registerLayer(AnnotationPhpTypes.SURROUNDING_CODE, new LayerDescriptor(SyntaxHighlighterFactory.getSyntaxHighlighter(PhpLanguage.INSTANCE, project, virtualFile), ""));

    }
}
