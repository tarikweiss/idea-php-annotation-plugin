package de.espend.idea.php.annotation.highlighting;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.php.lang.PhpPairedBraceMatcher;
import com.jetbrains.php.lang.lexer.PhpTokenTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class AnnotationPhpBraceMatcher extends PhpPairedBraceMatcher {

    @Override
    public boolean isLBraceToken(@NotNull HighlighterIterator iterator, @NotNull CharSequence fileText, @NotNull FileType fileType) {
        boolean lBraceToken = super.isLBraceToken(iterator, fileText, fileType);
        if (!lBraceToken) {
            return findPair(true, iterator, fileText, fileType) != null;
        }
        return true;
    }

    @Override
    public boolean isRBraceToken(@NotNull HighlighterIterator iterator, @NotNull CharSequence fileText, @NotNull FileType fileType) {
        boolean rBraceToken = super.isRBraceToken(iterator, fileText, fileType);
        if (!rBraceToken) {
            return findPair(false, iterator, fileText, fileType) != null;
        }
        return true;
    }

    public static class AnnotationPhpPairedBraceMatcher implements PairedBraceMatcher {
        @NotNull
        @Override
        public BracePair[] getPairs() {
            PhpPairedBraceMatcher matcher = new PhpPairedBraceMatcher();
            BracePair[]     pairs          = matcher.getPairs();
            List<BracePair> pairsArrayList = Arrays.asList(pairs);
            pairsArrayList.add(new BracePair(PhpTokenTypes.DOC_LBRACE, PhpTokenTypes.DOC_RBRACE, false));
            pairsArrayList.add(new BracePair(PhpTokenTypes.DOC_LPAREN, PhpTokenTypes.DOC_RPAREN, false));
            pairsArrayList.add(new BracePair(PhpTokenTypes.DOC_LBRACKET, PhpTokenTypes.DOC_RBRACKET, false));
            return pairsArrayList.toArray(BracePair[]::new);
        }

        @Override
        public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
            return true;
        }

        @Override
        public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
            return openingBraceOffset;
        }

    }
}
