/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Scanner.java
 * Copyright (C) 2015 University of Waikato, Hamilton, New Zealand
 */

 package weka.core.expressionlanguage.parser;
 
 import java_cup.runtime.*;
 
 import weka.core.expressionlanguage.core.SyntaxException;
 
/**
 * A lexical scanner for an expression language.
 * 
 * It emerged as a superset of the weka.core.mathematicalexpression package,
 * the weka.filers.unsupervised.instance.subsetbyexpression package and the
 * weka.core.AttributeExpression class.
 *
 * Warning: This file (Scanner.java) has been auto generated by jflex.
 *
 * @author Benjamin Weber ( benweber at student dot ethz dot ch )
 * @version $Revision: 1000 $
 */
%%
 
// options & declarations 

%cup

%public

%class Scanner

%{
  private StringBuilder string = new StringBuilder();
  
  private Symbol symbol(int type) {
    return new Symbol(type);
  }
  
  private Symbol symbol(int type, Object obj) {
    return new Symbol(type, obj);
  }
%}

%scanerror SyntaxException

%state STRING1
%state STRING2

%%

// lexical rules

<YYINITIAL> {

  // float
  [0-9]+\.?[0-9]*
    { return symbol(sym.FLOAT, Double.valueOf(yytext())); }
 
  // boolean
  "true"
    { return symbol(sym.BOOLEAN, true); }
  "false"
    { return symbol(sym.BOOLEAN, false); }
 
  // string
  \"
    { yybegin(STRING1); string.setLength(0); }
  \'
    { yybegin(STRING2); string.setLength(0); }

 // parenthesis
  "("
    { return symbol(sym.LPAREN); }
  ")"
    { return symbol(sym.RPAREN); }
  
  // comma
  ","
    { return symbol(sym.COMMA); }
  
  // numeric operators
  "+"
    { return symbol(sym.PLUS); }
  "-"
    { return symbol(sym.MINUS); }
  "*"
    { return symbol(sym.TIMES); }
  "/"
    { return symbol(sym.DIVISION); }
  "^"
    { return symbol(sym.POW); }
  
  // boolean operators
  "&"|"and"
    { return symbol(sym.AND); }

  "|"|"or"
    { return symbol(sym.OR); }

  "!"|"not"
    { return symbol(sym.NOT); }
  
  // comparison operators
  "="
    { return symbol(sym.EQUAL); }

  "<"
    { return symbol(sym.LT); }

  "<="
    { return symbol(sym.LE); }

  ">"
    { return symbol(sym.GT); }

  ">="
    { return symbol(sym.GE); }
    
  "is"
    { return symbol(sym.IS); }
  
  "regexp"
    { return symbol(sym.REGEXP); }

  // identifier
  [a-zA-Z_][a-zA-Z0-9_]*
    { return symbol(sym.IDENTIFIER, yytext()); }
  
  // whitespace
  \s 
    { /* ignore */ }
  
}

<STRING1> {
  \"
    { yybegin(YYINITIAL); return symbol(sym.STRING, string.toString()); }
}

<STRING2> {
  \'
    { yybegin(YYINITIAL); return symbol(sym.STRING, string.toString()); }
}

// escape sequences that are shared by both types of strings
<STRING1, STRING2> {

  \\b
    { string.append('\b'); }

  \\f
    { string.append('\f'); }

  \\n
    { string.append('\n'); }

  \\r
    { string.append('\r'); }

  \\t
    { string.append('\t'); }

  \\\"
    { string.append('\"'); }

  \\\'
    { string.append('\''); }

  \\\\
    { string.append('\\'); }
  
  \\.
    { throw new SyntaxException("Invalid escape sequence '" + yytext() + "'!"); }
  .
    { string.append(yytext()); }

}

// syntax error
[^]
  {
    throw new SyntaxException("Illegal character " + yytext() + "!");
  }