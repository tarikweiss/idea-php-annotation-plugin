package de.espend.idea.php.annotation.highlighting.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static de.espend.idea.php.annotation.highlighting.language.psi.AnnotationPhpTypes.*;

%%

%{
  public _AnnotationPhpLexer() {
    this((java.io.Reader)null);
  }

  public int nesting = 0;
%}

%public
%class _AnnotationPhpLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

STRING=('([^'\\]|\\.)*'|\"([^\"\\]|\\\"|\\'|\\)*\")
NUMBER=[0-9]+
DOC_BLOCK_LEADING_ASTERISK=\*
IDENTIFIER=[a-zA-Z_\\][a-zA-Z_0-9\\:]*
ANNOTATION_CLASS_NAME=[a-zA-Z_][a-zA-Z0-9_]*
ANNOTATION_NAMESPACE_HELPER=([a-zA-Z_][a-zA-Z0-9_]*)?\\
ANNOTATION_NAMESPACE=[a-zA-Z_][a-zA-Z0-9_]*
NAMESPACE_OPERATOR=\\
CLASS_FIELD_HELPER=[a-zA-Z_][a-zA-Z0-9_]*
CLASS_FIELD=[a-zA-Z_][a-zA-Z0-9_]*

CLASS_NAME_HELPER=[a-zA-Z0-9_\\]+\:\:[a-zA-Z_][a-zA-Z0-9_]*
CLASS_NAME=[a-zA-Z_][a-zA-Z0-9_]*
CLASS_NAMESPACE_HELPER=([a-zA-Z_][a-zA-Z0-9_]*)?\\
CLASS_NAMESPACE=[a-zA-Z_][a-zA-Z0-9_]*
STATIC_ACCESS_OPERATOR=\:\:

%state ANNOTATION_CLASS_STATE
%state ANNOTATION_CLASS_NAMESPACE_STATE
%state ANNOTATION_SPLITTER_STATE
%state ANNOTATION_BODY_STATE
%state FIELD_ASSIGNMENT_STATE
%state FIELD_ASSIGNMENT_VALUE_STATE
%state CLASS_NAMESPACE_STATE
%state CLASS_NAMESPACE_SPLITTER_STATE
%state CLASS_STATE
%state CONSTANT_STATE

%%

{WHITE_SPACE}                       { return WHITE_SPACE; }

<ANNOTATION_CLASS_STATE> {
  {ANNOTATION_CLASS_NAME}           { yybegin(ANNOTATION_BODY_STATE); return ANNOTATION_CLASS; }
}

<ANNOTATION_CLASS_NAMESPACE_STATE> {
  {ANNOTATION_NAMESPACE_HELPER}     { yypushback(yytext().length()); yybegin(ANNOTATION_SPLITTER_STATE); }
  [^]                               { yybegin(ANNOTATION_CLASS_STATE); }
}

<ANNOTATION_SPLITTER_STATE> {
  {ANNOTATION_NAMESPACE}            { return ANNOTATION_NAMESPACE_NAME; }
  {NAMESPACE_OPERATOR}              { yybegin(ANNOTATION_CLASS_NAMESPACE_STATE); return ANNOTATION_NAMESPACE_OPERATOR; }
  [^]                               { yybegin(ANNOTATION_CLASS_NAMESPACE_STATE); }
}

"@"                                 { yybegin(ANNOTATION_CLASS_NAMESPACE_STATE); return ANNOTATION_AT; }
{DOC_BLOCK_LEADING_ASTERISK}        { return DOC_BLOCK_LEADING_ASTERISK; }

<ANNOTATION_BODY_STATE> {
  "("                               { nesting++; return LPARANTHESES_CLASS_BODY; }
  ")"                               {
          if (nesting > 0) {
              nesting--;
          }
          if (nesting == 0) {
              yybegin(YYINITIAL);
          }
          return RPARANTHESES_CLASS_BODY;
      }
  ","                               { if (nesting > 0) return COMMA; }
  "{"                               { if (nesting > 0) return LBRACES; }
  "}"                               { if (nesting > 0) return RBRACES; }
  ":"                               { if (nesting > 0) return KEY_VALUE_ASSIGN; }
  "null"                            { if (nesting > 0) return NULL_KEYWORD; }
  "false"                           { if (nesting > 0) return BOOL_FALSE_KEYWORD; }
  "true"                            { if (nesting > 0) return BOOL_TRUE_KEYWORD; }

  {STRING}                          { if (nesting > 0) return STRING; }
  {NUMBER}                          { if (nesting > 0) return NUMBER; }
  {CLASS_FIELD_HELPER}              { if (nesting > 0) { yypushback(yytext().length()); yybegin(FIELD_ASSIGNMENT_STATE); } }
  {IDENTIFIER}                      { if (nesting > 0) return IDENTIFIER; }

  [^]                               { if (nesting > 0) return BAD_CHARACTER; yybegin(YYINITIAL); }
}

<FIELD_ASSIGNMENT_STATE> {
  {CLASS_FIELD}                     { if (nesting > 0) return CLASS_FIELD; }
  "="                               { if (nesting > 0) yybegin(FIELD_ASSIGNMENT_VALUE_STATE); return EQUAL_OPERATOR; }
  ","                               { if (nesting > 0) return COMMA; }
  ")"                               { if (nesting > 0) yypushback(yytext().length()); yybegin(ANNOTATION_BODY_STATE); }
  [^]                               { if (nesting > 0) yypushback(yytext().length()); yybegin(ANNOTATION_BODY_STATE); }
}

<FIELD_ASSIGNMENT_VALUE_STATE> {
  {STRING}                          { if (nesting > 0) return STRING; }
  {CLASS_NAME_HELPER}               { if (nesting > 0) yypushback(yytext().length()); yybegin(CLASS_NAMESPACE_STATE); }
  {IDENTIFIER}                      { if (nesting > 0) yybegin(FIELD_ASSIGNMENT_STATE); return IDENTIFIER; }
  [^]                               { if (nesting > 0) yypushback(yytext().length()); yybegin(FIELD_ASSIGNMENT_STATE); }
}

<CLASS_STATE> {
  {STATIC_ACCESS_OPERATOR}          { if (nesting > 0) yybegin(CONSTANT_STATE); return STATIC_ACCESS_OPERATOR; }
  {CLASS_NAME}                      { if (nesting > 0) return CLASS; }
  [^]                               { if (nesting > 0) yypushback(yytext().length()); yybegin(FIELD_ASSIGNMENT_VALUE_STATE); }
}

<CONSTANT_STATE> {
  "class"                           { if (nesting > 0) return CLASS_KEYWORD; }
  {IDENTIFIER}                      { if (nesting > 0) return CONSTANT; }
  [^]                               { if (nesting > 0) yypushback(yytext().length()); yybegin(CLASS_STATE); }
}

<CLASS_NAMESPACE_STATE> {
  {CLASS_NAMESPACE_HELPER}          { yypushback(yytext().length()); yybegin(CLASS_NAMESPACE_SPLITTER_STATE); }
  [^]                               { yybegin(CLASS_STATE); }
}

<CLASS_NAMESPACE_SPLITTER_STATE> {
  {CLASS_NAMESPACE}                 { return CLASS_NAMESPACE_NAME; }
  {NAMESPACE_OPERATOR}              { yybegin(CLASS_NAMESPACE_STATE); return CLASS_NAMESPACE_OPERATOR; }
  [^]                               { yybegin(CLASS_NAMESPACE_STATE); }
}

//[\$\(\)\[\]&§?{}\\´`~#|\^°_*+\-%;,:\\./<!>=\"'][:letter:][:digit:] { return SURROUNDING_CODE; }
[^] { return SURROUNDING_CODE; }