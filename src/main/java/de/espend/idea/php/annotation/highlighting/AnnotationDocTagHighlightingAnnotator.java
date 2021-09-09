package de.espend.idea.php.annotation.highlighting;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.patterns.StandardPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiRecursiveElementVisitor;
import com.intellij.psi.PsiWhiteSpace;
import com.jetbrains.php.lang.PhpLanguage;
import com.jetbrains.php.lang.documentation.phpdoc.lexer.PhpDocTokenTypes;
import com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes;
import com.jetbrains.php.lang.documentation.phpdoc.psi.tags.PhpDocTag;
import com.jetbrains.php.lang.highlighter.PhpHighlightingData;
import de.espend.idea.php.annotation.pattern.AnnotationPattern;
import de.espend.idea.php.annotation.util.AnnotationUtil;
import jnr.ffi.Platform;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AnnotationDocTagHighlightingAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof PhpDocTag)) {
            return;
        }

        String name = ((PhpDocTag) element).getName();
        if (AnnotationUtil.isBlockedAnnotationTag(name)) {
            return;
        }

        if (!AnnotationUtil.isAnnotationPhpDocTag((PhpDocTag) element)) {
            return;
        }

        element.acceptChildren(new PsiRecursiveElementVisitor() {
            @Override
            public void visitElement(@NotNull PsiElement visitingElement) {
                super.visitElement(visitingElement);
                if (PlatformPatterns.psiElement(PhpDocTokenTypes.DOC_LEADING_ASTERISK).accepts(visitingElement)) {
                    return;
                }
                if (AnnotationPattern.getBooleanValues().accepts(visitingElement)) {
                    eraseAndNormalizeHighlighting(visitingElement, holder);
                    annotate(visitingElement, holder, PhpHighlightingData.KEYWORD);
                    return;
                }
                if (PlatformPatterns.and(AnnotationPattern.getDocAttribute(), PlatformPatterns.psiElement().beforeLeafSkipping(PlatformPatterns.psiElement(PsiWhiteSpace.class), PlatformPatterns.psiElement().withText("="))).accepts(visitingElement)) {
                    highlightAnnotationAttribute(visitingElement, holder);
                    return;
                }
                if (PlatformPatterns.psiElement(PhpDocTokenTypes.DOC_IDENTIFIER).beforeLeaf(AnnotationPattern.getDocStaticPattern()).withLanguage(PhpLanguage.INSTANCE).accepts(visitingElement)) {
                    eraseAndNormalizeHighlighting(visitingElement, holder);
                    annotate(visitingElement, holder, PhpHighlightingData.CLASS);
                    return;
                }
                if (AnnotationPattern.getDocAttribute().accepts(visitingElement)) {
                    eraseAndNormalizeHighlighting(visitingElement, holder);
                    annotate(visitingElement, holder, PhpHighlightingData.CONSTANT);
                }
                if (PlatformPatterns.psiElement(PhpDocElementTypes.DOC_TAG_NAME).withText(StandardPatterns.string().startsWith("@")).withLanguage(PhpLanguage.INSTANCE).accepts(visitingElement)) {
                    highlightAnnotation(visitingElement, holder);
                }
                if (PlatformPatterns.or(PlatformPatterns.psiElement(PhpDocTokenTypes.DOC_LPAREN), PlatformPatterns.psiElement(PhpDocTokenTypes.DOC_RPAREN)).accepts(visitingElement)) {
                    eraseAndNormalizeHighlighting(visitingElement, holder);
                    annotate(visitingElement, holder, PhpHighlightingData.ATTRIBUTE);
                }
                if (PlatformPatterns.psiElement(PhpDocElementTypes.DOC_COMMA).accepts(visitingElement)) {

                    eraseAndNormalizeHighlighting(visitingElement, holder);
                    annotate(visitingElement, holder, PhpHighlightingData.COMMA);
                }
                if (PlatformPatterns.psiElement(PhpDocTokenTypes.DOC_NAMESPACE).accepts(visitingElement)) {
                    eraseAndNormalizeHighlighting(visitingElement, holder);
                    annotate(visitingElement, holder, PhpHighlightingData.CLASS);
                }
                if (PlatformPatterns.psiElement(PhpDocTokenTypes.DOC_IDENTIFIER).beforeLeaf(PlatformPatterns.psiElement(PhpDocTokenTypes.DOC_NAMESPACE)).accepts(visitingElement)) {
                    eraseAndNormalizeHighlighting(visitingElement, holder);
                    annotate(visitingElement, holder, PhpHighlightingData.CLASS);
                }
                if (PlatformPatterns.psiElement(PhpDocTokenTypes.DOC_IDENTIFIER).afterLeaf(PlatformPatterns.psiElement(PhpDocTokenTypes.DOC_NAMESPACE)).accepts(visitingElement)) {
                    eraseAndNormalizeHighlighting(visitingElement, holder);
                    annotate(visitingElement, holder, PhpHighlightingData.CLASS);
                }
                if (PlatformPatterns.and(
                        PlatformPatterns.psiElement(PhpDocTokenTypes.DOC_IDENTIFIER),
                        PlatformPatterns.not(PlatformPatterns.psiElement(PhpDocTokenTypes.DOC_IDENTIFIER).afterLeaf("::")),
                        PlatformPatterns.not(PlatformPatterns.psiElement(PhpDocTokenTypes.DOC_IDENTIFIER).beforeLeafSkipping(PlatformPatterns.psiElement(PsiWhiteSpace.class), PlatformPatterns.psiElement().withText("="))),
                        PlatformPatterns.not(PlatformPatterns.psiElement(PhpDocTokenTypes.DOC_IDENTIFIER).afterLeafSkipping(PlatformPatterns.psiElement(PsiWhiteSpace.class), AnnotationPattern.getBooleanValues()))
                ).accepts(visitingElement)) {
                    eraseAndNormalizeHighlighting(visitingElement, holder);
                    annotate(visitingElement, holder, PhpHighlightingData.CLASS);
                }
                if (PlatformPatterns.psiElement(PhpDocTokenTypes.DOC_STRING).accepts(visitingElement)) {
                    eraseAndNormalizeHighlighting(visitingElement, holder);
                    annotate(visitingElement, holder, PhpHighlightingData.STRING);
                }
                if (PlatformPatterns.or(AnnotationPattern.getDocStaticPattern()).accepts(visitingElement)) {
                    eraseAndNormalizeHighlighting(visitingElement, holder);
                    annotate(visitingElement, holder, PhpHighlightingData.OPERATION_SIGN);
                }
                if (PlatformPatterns.psiElement(PhpDocTokenTypes.DOC_TEXT).withText(StandardPatterns.string().contains("=")).accepts(visitingElement)) {
                    List<Integer> positions = new ArrayList<>();
                    String        text      = visitingElement.getText();
                    char          operator  = '=';
                    int           index     = text.indexOf(operator);
                    while (index >= 0) {
                        positions.add(visitingElement.getTextRange().getStartOffset() + index);
                        index = text.indexOf(operator, index + 1);
                    }
                    positions.forEach(position -> {
                        eraseAndNormalizeHighlighting(visitingElement, holder);
                        holder
                                .newSilentAnnotation(HighlightSeverity.INFORMATION)
                                .range(new TextRange(position, position + 1))
                                .textAttributes(PhpHighlightingData.OPERATION_SIGN)
                                .create()
                        ;
                    });
                }
                if (AnnotationPattern.getClassConstant().accepts(visitingElement)) {
                    eraseAndNormalizeHighlighting(visitingElement, holder);
                    if (visitingElement.getText().equals("class")) {
                        annotate(visitingElement, holder, PhpHighlightingData.KEYWORD);
                        return;
                    }
                    annotate(visitingElement, holder, PhpHighlightingData.CONSTANT);
                }
                if (PlatformPatterns.psiElement(PhpDocTokenTypes.DOC_TEXT).withText(matchNumbers()).accepts(visitingElement)) {
                    List<Integer> positions = new ArrayList<>();
                    String        text      = visitingElement.getText();
                    char[]        numbers   = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
                    for (char number : numbers) {
                        int index = text.indexOf(number);
                        while (index >= 0) {
                            positions.add(visitingElement.getTextRange().getStartOffset() + index);
                            index = text.indexOf(number, index + 1);
                        }
                        positions.forEach(position -> {
                            eraseAndNormalizeHighlighting(visitingElement, holder);
                            holder
                                    .newSilentAnnotation(HighlightSeverity.INFORMATION)
                                    .range(new TextRange(position, position + 1))
                                    .textAttributes(PhpHighlightingData.NUMBER)
                                    .create()
                            ;
                        });
                    }
                }

                if (AnnotationPattern.getCollectionBraces().accepts(visitingElement)) {
                    eraseAndNormalizeHighlighting(visitingElement, holder);
                    annotate(visitingElement, holder, PhpHighlightingData.BRACES);
                }
            }
        });
    }

    private @NotNull
    ElementPattern<String> matchNumbers() {
        return StandardPatterns.or(
                StandardPatterns.string().contains("0"),
                StandardPatterns.string().contains("1"),
                StandardPatterns.string().contains("2"),
                StandardPatterns.string().contains("3"),
                StandardPatterns.string().contains("4"),
                StandardPatterns.string().contains("5"),
                StandardPatterns.string().contains("6"),
                StandardPatterns.string().contains("7"),
                StandardPatterns.string().contains("8"),
                StandardPatterns.string().contains("9")
        );
    }

    private void highlightAnnotation(@NotNull PsiElement visitingElement, @NotNull AnnotationHolder holder) {
        eraseAndNormalizeHighlighting(visitingElement, holder);
        TextAttributesKey attribute = PhpHighlightingData.ATTRIBUTE;
        annotate(visitingElement, holder, attribute);
    }

    private void highlightAnnotationAttribute(@NotNull PsiElement visitingElement, @NotNull AnnotationHolder holder) {
        eraseAndNormalizeHighlighting(visitingElement, holder);
        holder
                .newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(visitingElement)
                .textAttributes(PhpHighlightingData.INSTANCE_FIELD)
                .create()
        ;
    }

    private void eraseAndNormalizeHighlighting(@NotNull PsiElement visitingElement, @NotNull AnnotationHolder holder) {
        holder
                .newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(visitingElement)
                .enforcedTextAttributes(TextAttributes.ERASE_MARKER)
                .create()
        ;
        holder
                .newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(visitingElement)
                .textAttributes(PhpHighlightingData.PHP_SCRIPTING)
                .create()
        ;
    }

    private void annotate(@NotNull PsiElement visitingElement, @NotNull AnnotationHolder holder, TextAttributesKey textAttributesKey) {
        holder
                .newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(visitingElement.getTextRange())
                .textAttributes(textAttributesKey)
                .create()
        ;
    }
}
