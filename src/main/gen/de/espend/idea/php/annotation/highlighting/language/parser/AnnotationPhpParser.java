// This is a generated file. Not intended for manual editing.
package de.espend.idea.php.annotation.highlighting.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static de.espend.idea.php.annotation.highlighting.language.psi.AnnotationPhpTypes.*;
import static de.espend.idea.php.annotation.highlighting.language.parser.AnnotationPhpParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class AnnotationPhpParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return root_expr(b, l + 1);
  }

  /* ********************************************************** */
  // ANNOTATION_AT AnnotationName AnnotationBody
  public static boolean Annotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Annotation")) return false;
    if (!nextTokenIs(b, ANNOTATION_AT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ANNOTATION_AT);
    r = r && AnnotationName(b, l + 1);
    r = r && AnnotationBody(b, l + 1);
    exit_section_(b, m, ANNOTATION, r);
    return r;
  }

  /* ********************************************************** */
  // [ LPARANTHESES_CLASS_BODY [ Values ] RPARANTHESES_CLASS_BODY ]
  public static boolean AnnotationBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnnotationBody")) return false;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATION_BODY, "<annotation body>");
    AnnotationBody_0(b, l + 1);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // LPARANTHESES_CLASS_BODY [ Values ] RPARANTHESES_CLASS_BODY
  private static boolean AnnotationBody_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnnotationBody_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPARANTHESES_CLASS_BODY);
    r = r && AnnotationBody_0_1(b, l + 1);
    r = r && consumeToken(b, RPARANTHESES_CLASS_BODY);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ Values ]
  private static boolean AnnotationBody_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnnotationBody_0_1")) return false;
    Values(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // [ AnnotationNamespace ] ANNOTATION_CLASS
  public static boolean AnnotationName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnnotationName")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATION_NAME, "<annotation name>");
    r = AnnotationName_0(b, l + 1);
    r = r && consumeToken(b, ANNOTATION_CLASS);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ AnnotationNamespace ]
  private static boolean AnnotationName_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnnotationName_0")) return false;
    AnnotationNamespace(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // [ ANNOTATION_NAMESPACE_OPERATOR ] [ ( ANNOTATION_NAMESPACE_NAME ANNOTATION_NAMESPACE_OPERATOR ) * ]
  public static boolean AnnotationNamespace(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnnotationNamespace")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATION_NAMESPACE, "<annotation namespace>");
    r = AnnotationNamespace_0(b, l + 1);
    r = r && AnnotationNamespace_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ ANNOTATION_NAMESPACE_OPERATOR ]
  private static boolean AnnotationNamespace_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnnotationNamespace_0")) return false;
    consumeToken(b, ANNOTATION_NAMESPACE_OPERATOR);
    return true;
  }

  // [ ( ANNOTATION_NAMESPACE_NAME ANNOTATION_NAMESPACE_OPERATOR ) * ]
  private static boolean AnnotationNamespace_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnnotationNamespace_1")) return false;
    AnnotationNamespace_1_0(b, l + 1);
    return true;
  }

  // ( ANNOTATION_NAMESPACE_NAME ANNOTATION_NAMESPACE_OPERATOR ) *
  private static boolean AnnotationNamespace_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnnotationNamespace_1_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!AnnotationNamespace_1_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "AnnotationNamespace_1_0", c)) break;
    }
    return true;
  }

  // ANNOTATION_NAMESPACE_NAME ANNOTATION_NAMESPACE_OPERATOR
  private static boolean AnnotationNamespace_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnnotationNamespace_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ANNOTATION_NAMESPACE_NAME, ANNOTATION_NAMESPACE_OPERATOR);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Annotation | SURROUNDING_CODE +
  public static boolean AnnotationWrapper(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnnotationWrapper")) return false;
    if (!nextTokenIs(b, "<annotation wrapper>", ANNOTATION_AT, SURROUNDING_CODE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATION_WRAPPER, "<annotation wrapper>");
    r = Annotation(b, l + 1);
    if (!r) r = AnnotationWrapper_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SURROUNDING_CODE +
  private static boolean AnnotationWrapper_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnnotationWrapper_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SURROUNDING_CODE);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, SURROUNDING_CODE)) break;
      if (!empty_element_parsed_guard_(b, "AnnotationWrapper_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACES [ ArrayEntry [ ( COMMA ArrayEntry ) * ] ] [ COMMA ] RBRACES
  public static boolean Array(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Array")) return false;
    if (!nextTokenIs(b, LBRACES)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACES);
    r = r && Array_1(b, l + 1);
    r = r && Array_2(b, l + 1);
    r = r && consumeToken(b, RBRACES);
    exit_section_(b, m, ARRAY, r);
    return r;
  }

  // [ ArrayEntry [ ( COMMA ArrayEntry ) * ] ]
  private static boolean Array_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Array_1")) return false;
    Array_1_0(b, l + 1);
    return true;
  }

  // ArrayEntry [ ( COMMA ArrayEntry ) * ]
  private static boolean Array_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Array_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ArrayEntry(b, l + 1);
    r = r && Array_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ ( COMMA ArrayEntry ) * ]
  private static boolean Array_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Array_1_0_1")) return false;
    Array_1_0_1_0(b, l + 1);
    return true;
  }

  // ( COMMA ArrayEntry ) *
  private static boolean Array_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Array_1_0_1_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Array_1_0_1_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Array_1_0_1_0", c)) break;
    }
    return true;
  }

  // COMMA ArrayEntry
  private static boolean Array_1_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Array_1_0_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && ArrayEntry(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ COMMA ]
  private static boolean Array_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Array_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // PlainValue | KeyValuePair
  public static boolean ArrayEntry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayEntry")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARRAY_ENTRY, "<array entry>");
    r = PlainValue(b, l + 1);
    if (!r) r = KeyValuePair(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // BOOL_TRUE_KEYWORD | BOOL_FALSE_KEYWORD
  public static boolean Boolean(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Boolean")) return false;
    if (!nextTokenIs(b, "<boolean>", BOOL_FALSE_KEYWORD, BOOL_TRUE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BOOLEAN, "<boolean>");
    r = consumeToken(b, BOOL_TRUE_KEYWORD);
    if (!r) r = consumeToken(b, BOOL_FALSE_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // [ ClassNamespace ] CLASS
  public static boolean ClassName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassName")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CLASS_NAME, "<class name>");
    r = ClassName_0(b, l + 1);
    r = r && consumeToken(b, CLASS);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ ClassNamespace ]
  private static boolean ClassName_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassName_0")) return false;
    ClassNamespace(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // [ CLASS_NAMESPACE_OPERATOR ] [ ( CLASS_NAMESPACE_NAME CLASS_NAMESPACE_OPERATOR ) * ]
  public static boolean ClassNamespace(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassNamespace")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CLASS_NAMESPACE, "<class namespace>");
    r = ClassNamespace_0(b, l + 1);
    r = r && ClassNamespace_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ CLASS_NAMESPACE_OPERATOR ]
  private static boolean ClassNamespace_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassNamespace_0")) return false;
    consumeToken(b, CLASS_NAMESPACE_OPERATOR);
    return true;
  }

  // [ ( CLASS_NAMESPACE_NAME CLASS_NAMESPACE_OPERATOR ) * ]
  private static boolean ClassNamespace_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassNamespace_1")) return false;
    ClassNamespace_1_0(b, l + 1);
    return true;
  }

  // ( CLASS_NAMESPACE_NAME CLASS_NAMESPACE_OPERATOR ) *
  private static boolean ClassNamespace_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassNamespace_1_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ClassNamespace_1_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ClassNamespace_1_0", c)) break;
    }
    return true;
  }

  // CLASS_NAMESPACE_NAME CLASS_NAMESPACE_OPERATOR
  private static boolean ClassNamespace_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClassNamespace_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, CLASS_NAMESPACE_NAME, CLASS_NAMESPACE_OPERATOR);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // CLASS_FIELD EQUAL_OPERATOR PlainValue
  public static boolean FieldAssignment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldAssignment")) return false;
    if (!nextTokenIs(b, CLASS_FIELD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, CLASS_FIELD, EQUAL_OPERATOR);
    r = r && PlainValue(b, l + 1);
    exit_section_(b, m, FIELD_ASSIGNMENT, r);
    return r;
  }

  /* ********************************************************** */
  // NUMBER "." NUMBER
  public static boolean Float(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Float")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    r = r && consumeToken(b, ".");
    r = r && consumeToken(b, NUMBER);
    exit_section_(b, m, FLOAT, r);
    return r;
  }

  /* ********************************************************** */
  // NUMBER
  public static boolean Integer(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Integer")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    exit_section_(b, m, INTEGER, r);
    return r;
  }

  /* ********************************************************** */
  // CLASS_FIELD ( EQUAL_OPERATOR | KEY_VALUE_ASSIGN ) ( PlainValue )
  public static boolean KeyValuePair(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "KeyValuePair")) return false;
    if (!nextTokenIs(b, CLASS_FIELD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CLASS_FIELD);
    r = r && KeyValuePair_1(b, l + 1);
    r = r && KeyValuePair_2(b, l + 1);
    exit_section_(b, m, KEY_VALUE_PAIR, r);
    return r;
  }

  // EQUAL_OPERATOR | KEY_VALUE_ASSIGN
  private static boolean KeyValuePair_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "KeyValuePair_1")) return false;
    boolean r;
    r = consumeToken(b, EQUAL_OPERATOR);
    if (!r) r = consumeToken(b, KEY_VALUE_ASSIGN);
    return r;
  }

  // ( PlainValue )
  private static boolean KeyValuePair_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "KeyValuePair_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PlainValue(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // STRING | Float | Integer | Boolean | Array | Annotation | ( ClassName STATIC_ACCESS_OPERATOR ( CONSTANT | CLASS_KEYWORD ) ) | NULL_KEYWORD | IDENTIFIER
  public static boolean PlainValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PlainValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PLAIN_VALUE, "<plain value>");
    r = consumeToken(b, STRING);
    if (!r) r = Float(b, l + 1);
    if (!r) r = Integer(b, l + 1);
    if (!r) r = Boolean(b, l + 1);
    if (!r) r = Array(b, l + 1);
    if (!r) r = Annotation(b, l + 1);
    if (!r) r = PlainValue_6(b, l + 1);
    if (!r) r = consumeToken(b, NULL_KEYWORD);
    if (!r) r = consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ClassName STATIC_ACCESS_OPERATOR ( CONSTANT | CLASS_KEYWORD )
  private static boolean PlainValue_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PlainValue_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ClassName(b, l + 1);
    r = r && consumeToken(b, STATIC_ACCESS_OPERATOR);
    r = r && PlainValue_6_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // CONSTANT | CLASS_KEYWORD
  private static boolean PlainValue_6_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PlainValue_6_2")) return false;
    boolean r;
    r = consumeToken(b, CONSTANT);
    if (!r) r = consumeToken(b, CLASS_KEYWORD);
    return r;
  }

  /* ********************************************************** */
  // PlainValue | FieldAssignment
  public static boolean Value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VALUE, "<value>");
    r = PlainValue(b, l + 1);
    if (!r) r = FieldAssignment(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ( Array | Value ) [ ( COMMA ( Array | Value ) ) * ] [ COMMA ]
  public static boolean Values(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Values")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VALUES, "<values>");
    r = Values_0(b, l + 1);
    r = r && Values_1(b, l + 1);
    r = r && Values_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Array | Value
  private static boolean Values_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Values_0")) return false;
    boolean r;
    r = Array(b, l + 1);
    if (!r) r = Value(b, l + 1);
    return r;
  }

  // [ ( COMMA ( Array | Value ) ) * ]
  private static boolean Values_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Values_1")) return false;
    Values_1_0(b, l + 1);
    return true;
  }

  // ( COMMA ( Array | Value ) ) *
  private static boolean Values_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Values_1_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Values_1_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Values_1_0", c)) break;
    }
    return true;
  }

  // COMMA ( Array | Value )
  private static boolean Values_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Values_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Values_1_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Array | Value
  private static boolean Values_1_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Values_1_0_0_1")) return false;
    boolean r;
    r = Array(b, l + 1);
    if (!r) r = Value(b, l + 1);
    return r;
  }

  // [ COMMA ]
  private static boolean Values_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Values_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // [ SURROUNDING_CODE * ] ( AnnotationWrapper * ) [ SURROUNDING_CODE * ]
  static boolean root_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_expr")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = root_expr_0(b, l + 1);
    r = r && root_expr_1(b, l + 1);
    r = r && root_expr_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ SURROUNDING_CODE * ]
  private static boolean root_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_expr_0")) return false;
    root_expr_0_0(b, l + 1);
    return true;
  }

  // SURROUNDING_CODE *
  private static boolean root_expr_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_expr_0_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SURROUNDING_CODE)) break;
      if (!empty_element_parsed_guard_(b, "root_expr_0_0", c)) break;
    }
    return true;
  }

  // AnnotationWrapper *
  private static boolean root_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_expr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!AnnotationWrapper(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "root_expr_1", c)) break;
    }
    return true;
  }

  // [ SURROUNDING_CODE * ]
  private static boolean root_expr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_expr_2")) return false;
    root_expr_2_0(b, l + 1);
    return true;
  }

  // SURROUNDING_CODE *
  private static boolean root_expr_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_expr_2_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, SURROUNDING_CODE)) break;
      if (!empty_element_parsed_guard_(b, "root_expr_2_0", c)) break;
    }
    return true;
  }

}
