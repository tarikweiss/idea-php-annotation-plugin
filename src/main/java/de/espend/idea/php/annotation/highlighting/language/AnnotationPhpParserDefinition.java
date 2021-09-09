package de.espend.idea.php.annotation.highlighting.language;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import de.espend.idea.php.annotation.highlighting.language.parser.AnnotationPhpParser;
import de.espend.idea.php.annotation.highlighting.language.psi.AnnotationPhpFile;
import de.espend.idea.php.annotation.highlighting.language.psi.AnnotationPhpTypes;
import org.jetbrains.annotations.NotNull;

public class AnnotationPhpParserDefinition implements ParserDefinition {

    public static final TokenSet         WHITE_SPACES    = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet         STRING_LITERALS = TokenSet.create(AnnotationPhpTypes.STRING);
    public static final TokenSet         COMMENTS        = TokenSet.create(AnnotationPhpTypes.DOC_BLOCK_LEADING_ASTERISK);
    public static final IFileElementType FILE            = new IFileElementType(AnnotationPhpLanguage.INSTANCE);

    @Override
    public @NotNull
    Lexer createLexer(Project project) {
        return new AnnotationPhpLexerAdapter();
    }

    @Override
    public PsiParser createParser(Project project) {
        return new AnnotationPhpParser();
    }

    @Override
    public @NotNull
    TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public @NotNull
    TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @Override
    public @NotNull
    TokenSet getStringLiteralElements() {
        return STRING_LITERALS;
    }

    @Override
    public @NotNull
    PsiElement createElement(ASTNode node) {
        return AnnotationPhpTypes.Factory.createElement(node);
    }

    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new AnnotationPhpFile(viewProvider);
    }
}
