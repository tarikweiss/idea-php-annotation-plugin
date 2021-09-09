// This is a generated file. Not intended for manual editing.
package de.espend.idea.php.annotation.highlighting.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import de.espend.idea.php.annotation.highlighting.language.psi.impl.*;

public interface AnnotationPhpTypes {

  IElementType ANNOTATION = new AnnotationPhpElementType("ANNOTATION");
  IElementType ANNOTATION_BODY = new AnnotationPhpElementType("ANNOTATION_BODY");
  IElementType ANNOTATION_NAME = new AnnotationPhpElementType("ANNOTATION_NAME");
  IElementType ANNOTATION_NAMESPACE = new AnnotationPhpElementType("ANNOTATION_NAMESPACE");
  IElementType ANNOTATION_WRAPPER = new AnnotationPhpElementType("ANNOTATION_WRAPPER");
  IElementType ARRAY = new AnnotationPhpElementType("ARRAY");
  IElementType ARRAY_ENTRY = new AnnotationPhpElementType("ARRAY_ENTRY");
  IElementType BOOLEAN = new AnnotationPhpElementType("BOOLEAN");
  IElementType CLASS_NAME = new AnnotationPhpElementType("CLASS_NAME");
  IElementType CLASS_NAMESPACE = new AnnotationPhpElementType("CLASS_NAMESPACE");
  IElementType FIELD_ASSIGNMENT = new AnnotationPhpElementType("FIELD_ASSIGNMENT");
  IElementType FLOAT = new AnnotationPhpElementType("FLOAT");
  IElementType INTEGER = new AnnotationPhpElementType("INTEGER");
  IElementType KEY_VALUE_PAIR = new AnnotationPhpElementType("KEY_VALUE_PAIR");
  IElementType PLAIN_VALUE = new AnnotationPhpElementType("PLAIN_VALUE");
  IElementType VALUE = new AnnotationPhpElementType("VALUE");
  IElementType VALUES = new AnnotationPhpElementType("VALUES");

  IElementType ANNOTATION_AT = new AnnotationPhpTokenType("@");
  IElementType ANNOTATION_CLASS = new AnnotationPhpTokenType("ANNOTATION_CLASS");
  IElementType ANNOTATION_NAMESPACE_NAME = new AnnotationPhpTokenType("ANNOTATION_NAMESPACE_NAME");
  IElementType ANNOTATION_NAMESPACE_OPERATOR = new AnnotationPhpTokenType("ANNOTATION_NAMESPACE_OPERATOR");
  IElementType BOOL_FALSE_KEYWORD = new AnnotationPhpTokenType("BOOL_FALSE_KEYWORD");
  IElementType BOOL_TRUE_KEYWORD = new AnnotationPhpTokenType("BOOL_TRUE_KEYWORD");
  IElementType CLASS = new AnnotationPhpTokenType("CLASS");
  IElementType CLASS_FIELD = new AnnotationPhpTokenType("CLASS_FIELD");
  IElementType CLASS_KEYWORD = new AnnotationPhpTokenType("CLASS_KEYWORD");
  IElementType CLASS_NAMESPACE_NAME = new AnnotationPhpTokenType("CLASS_NAMESPACE_NAME");
  IElementType CLASS_NAMESPACE_OPERATOR = new AnnotationPhpTokenType("CLASS_NAMESPACE_OPERATOR");
  IElementType COMMA = new AnnotationPhpTokenType(",");
  IElementType CONSTANT = new AnnotationPhpTokenType("CONSTANT");
  IElementType DOC_BLOCK_LEADING_ASTERISK = new AnnotationPhpTokenType("DOC_BLOCK_LEADING_ASTERISK");
  IElementType EQUAL_OPERATOR = new AnnotationPhpTokenType("=");
  IElementType IDENTIFIER = new AnnotationPhpTokenType("IDENTIFIER");
  IElementType KEY_VALUE_ASSIGN = new AnnotationPhpTokenType(":");
  IElementType LBRACES = new AnnotationPhpTokenType("{");
  IElementType LPARANTHESES_CLASS_BODY = new AnnotationPhpTokenType("LPARANTHESES_CLASS_BODY");
  IElementType NAMESPACE_OPERATOR = new AnnotationPhpTokenType("NAMESPACE_OPERATOR");
  IElementType NULL_KEYWORD = new AnnotationPhpTokenType("NULL_KEYWORD");
  IElementType NUMBER = new AnnotationPhpTokenType("NUMBER");
  IElementType RBRACES = new AnnotationPhpTokenType("}");
  IElementType RPARANTHESES_CLASS_BODY = new AnnotationPhpTokenType("RPARANTHESES_CLASS_BODY");
  IElementType STATIC_ACCESS_OPERATOR = new AnnotationPhpTokenType("STATIC_ACCESS_OPERATOR");
  IElementType STRING = new AnnotationPhpTokenType("STRING");
  IElementType SURROUNDING_CODE = new AnnotationPhpTokenType("SURROUNDING_CODE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ANNOTATION) {
        return new AnnotationPhpAnnotationImpl(node);
      }
      else if (type == ANNOTATION_BODY) {
        return new AnnotationPhpAnnotationBodyImpl(node);
      }
      else if (type == ANNOTATION_NAME) {
        return new AnnotationPhpAnnotationNameImpl(node);
      }
      else if (type == ANNOTATION_NAMESPACE) {
        return new AnnotationPhpAnnotationNamespaceImpl(node);
      }
      else if (type == ANNOTATION_WRAPPER) {
        return new AnnotationPhpAnnotationWrapperImpl(node);
      }
      else if (type == ARRAY) {
        return new AnnotationPhpArrayImpl(node);
      }
      else if (type == ARRAY_ENTRY) {
        return new AnnotationPhpArrayEntryImpl(node);
      }
      else if (type == BOOLEAN) {
        return new AnnotationPhpBooleanImpl(node);
      }
      else if (type == CLASS_NAME) {
        return new AnnotationPhpClassNameImpl(node);
      }
      else if (type == CLASS_NAMESPACE) {
        return new AnnotationPhpClassNamespaceImpl(node);
      }
      else if (type == FIELD_ASSIGNMENT) {
        return new AnnotationPhpFieldAssignmentImpl(node);
      }
      else if (type == FLOAT) {
        return new AnnotationPhpFloatImpl(node);
      }
      else if (type == INTEGER) {
        return new AnnotationPhpIntegerImpl(node);
      }
      else if (type == KEY_VALUE_PAIR) {
        return new AnnotationPhpKeyValuePairImpl(node);
      }
      else if (type == PLAIN_VALUE) {
        return new AnnotationPhpPlainValueImpl(node);
      }
      else if (type == VALUE) {
        return new AnnotationPhpValueImpl(node);
      }
      else if (type == VALUES) {
        return new AnnotationPhpValuesImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
