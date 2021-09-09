// This is a generated file. Not intended for manual editing.
package de.espend.idea.php.annotation.highlighting.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static de.espend.idea.php.annotation.highlighting.language.psi.AnnotationPhpTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import de.espend.idea.php.annotation.highlighting.language.psi.*;

public class AnnotationPhpPlainValueImpl extends ASTWrapperPsiElement implements AnnotationPhpPlainValue {

  public AnnotationPhpPlainValueImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull AnnotationPhpVisitor visitor) {
    visitor.visitPlainValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof AnnotationPhpVisitor) accept((AnnotationPhpVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public AnnotationPhpAnnotation getAnnotation() {
    return findChildByClass(AnnotationPhpAnnotation.class);
  }

  @Override
  @Nullable
  public AnnotationPhpArray getArray() {
    return findChildByClass(AnnotationPhpArray.class);
  }

  @Override
  @Nullable
  public AnnotationPhpBoolean getBoolean() {
    return findChildByClass(AnnotationPhpBoolean.class);
  }

  @Override
  @Nullable
  public AnnotationPhpClassName getClassName() {
    return findChildByClass(AnnotationPhpClassName.class);
  }

  @Override
  @Nullable
  public AnnotationPhpFloat getFloat() {
    return findChildByClass(AnnotationPhpFloat.class);
  }

  @Override
  @Nullable
  public AnnotationPhpInteger getInteger() {
    return findChildByClass(AnnotationPhpInteger.class);
  }

  @Override
  @Nullable
  public PsiElement getString() {
    return findChildByType(STRING);
  }

}
