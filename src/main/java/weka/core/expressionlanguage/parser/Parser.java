
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20141202 (SVN rev 60)
//----------------------------------------------------

package weka.core.expressionlanguage.parser;

import java.util.List;
import java.util.ArrayList;
import java.io.StringReader;
import java_cup.runtime.Symbol;
import weka.core.expressionlanguage.core.Node;
import weka.core.expressionlanguage.core.VariableDeclarations;
import weka.core.expressionlanguage.core.SemanticException;
import weka.core.expressionlanguage.core.SyntaxException;
import weka.core.expressionlanguage.core.MacroDeclarations;
import weka.core.expressionlanguage.common.Operators;
import weka.core.expressionlanguage.common.NoVariables;
import weka.core.expressionlanguage.common.NoMacros;
import weka.core.expressionlanguage.common.Primitives.DoubleConstant;
import weka.core.expressionlanguage.common.Primitives.BooleanConstant;
import weka.core.expressionlanguage.common.Primitives.StringConstant;
import weka.core.expressionlanguage.parser.Scanner;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20141202 (SVN rev 60) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class Parser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  public Parser() {super();}

  /** Constructor which sets the default scanner. */
  public Parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\035\000\002\002\004\000\002\002\003\000\002\005" +
    "\005\000\002\005\003\000\002\004\003\000\002\004\002" +
    "\000\002\003\005\000\002\003\006\000\002\003\003\000" +
    "\002\003\003\000\002\003\003\000\002\003\003\000\002" +
    "\003\004\000\002\003\004\000\002\003\005\000\002\003" +
    "\005\000\002\003\005\000\002\003\005\000\002\003\005" +
    "\000\002\003\005\000\002\003\005\000\002\003\004\000" +
    "\002\003\005\000\002\003\005\000\002\003\005\000\002" +
    "\003\005\000\002\003\005\000\002\003\005\000\002\003" +
    "\005" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\064\000\022\004\010\005\015\006\007\007\005\010" +
    "\014\013\006\014\004\024\013\001\002\000\022\004\010" +
    "\005\015\006\007\007\005\010\014\013\006\014\004\024" +
    "\013\001\002\000\044\002\ufff7\011\ufff7\012\ufff7\013\ufff7" +
    "\014\ufff7\015\ufff7\016\ufff7\017\ufff7\022\ufff7\023\ufff7\025" +
    "\ufff7\026\ufff7\027\ufff7\030\ufff7\031\ufff7\032\ufff7\033\ufff7" +
    "\001\002\000\022\004\010\005\015\006\007\007\005\010" +
    "\014\013\006\014\004\024\013\001\002\000\044\002\ufff6" +
    "\011\ufff6\012\ufff6\013\ufff6\014\ufff6\015\ufff6\016\ufff6\017" +
    "\ufff6\022\ufff6\023\ufff6\025\ufff6\026\ufff6\027\ufff6\030\ufff6" +
    "\031\ufff6\032\ufff6\033\ufff6\001\002\000\046\002\ufff9\010" +
    "\056\011\ufff9\012\ufff9\013\ufff9\014\ufff9\015\ufff9\016\ufff9" +
    "\017\ufff9\022\ufff9\023\ufff9\025\ufff9\026\ufff9\027\ufff9\030" +
    "\ufff9\031\ufff9\032\ufff9\033\ufff9\001\002\000\004\002\055" +
    "\001\002\000\040\002\000\013\024\014\017\015\032\016" +
    "\031\017\021\022\020\023\034\025\023\026\033\027\025" +
    "\030\027\031\022\032\035\033\026\001\002\000\022\004" +
    "\010\005\015\006\007\007\005\010\014\013\006\014\004" +
    "\024\013\001\002\000\022\004\010\005\015\006\007\007" +
    "\005\010\014\013\006\014\004\024\013\001\002\000\044" +
    "\002\ufff8\011\ufff8\012\ufff8\013\ufff8\014\ufff8\015\ufff8\016" +
    "\ufff8\017\ufff8\022\ufff8\023\ufff8\025\ufff8\026\ufff8\027\ufff8" +
    "\030\ufff8\031\ufff8\032\ufff8\033\ufff8\001\002\000\040\011" +
    "\030\013\024\014\017\015\032\016\031\017\021\022\020" +
    "\023\034\025\023\026\033\027\025\030\027\031\022\032" +
    "\035\033\026\001\002\000\022\004\010\005\015\006\007" +
    "\007\005\010\014\013\006\014\004\024\013\001\002\000" +
    "\022\004\010\005\015\006\007\007\005\010\014\013\006" +
    "\014\004\024\013\001\002\000\022\004\010\005\015\006" +
    "\007\007\005\010\014\013\006\014\004\024\013\001\002" +
    "\000\022\004\010\005\015\006\007\007\005\010\014\013" +
    "\006\014\004\024\013\001\002\000\022\004\010\005\015" +
    "\006\007\007\005\010\014\013\006\014\004\024\013\001" +
    "\002\000\022\004\010\005\015\006\007\007\005\010\014" +
    "\013\006\014\004\024\013\001\002\000\022\004\010\005" +
    "\015\006\007\007\005\010\014\013\006\014\004\024\013" +
    "\001\002\000\022\004\010\005\015\006\007\007\005\010" +
    "\014\013\006\014\004\024\013\001\002\000\022\004\010" +
    "\005\015\006\007\007\005\010\014\013\006\014\004\024" +
    "\013\001\002\000\044\002\ufffb\011\ufffb\012\ufffb\013\ufffb" +
    "\014\ufffb\015\ufffb\016\ufffb\017\ufffb\022\ufffb\023\ufffb\025" +
    "\ufffb\026\ufffb\027\ufffb\030\ufffb\031\ufffb\032\ufffb\033\ufffb" +
    "\001\002\000\022\004\010\005\015\006\007\007\005\010" +
    "\014\013\006\014\004\024\013\001\002\000\022\004\010" +
    "\005\015\006\007\007\005\010\014\013\006\014\004\024" +
    "\013\001\002\000\022\004\010\005\015\006\007\007\005" +
    "\010\014\013\006\014\004\024\013\001\002\000\022\004" +
    "\010\005\015\006\007\007\005\010\014\013\006\014\004" +
    "\024\013\001\002\000\022\004\010\005\015\006\007\007" +
    "\005\010\014\013\006\014\004\024\013\001\002\000\042" +
    "\002\uffe6\011\uffe6\012\uffe6\013\024\014\017\015\032\016" +
    "\031\017\021\022\uffe6\023\uffe6\025\023\026\033\027\025" +
    "\030\027\031\022\033\026\001\002\000\044\002\uffed\011" +
    "\uffed\012\uffed\013\024\014\017\015\032\016\031\017\021" +
    "\022\020\023\uffed\025\023\026\033\027\025\030\027\031" +
    "\022\032\035\033\026\001\002\000\032\002\uffea\011\uffea" +
    "\012\uffea\013\024\014\017\015\032\016\031\017\021\022" +
    "\uffea\023\uffea\025\uffea\032\uffea\001\002\000\044\002\ufff0" +
    "\011\ufff0\012\ufff0\013\ufff0\014\ufff0\015\ufff0\016\ufff0\017" +
    "\021\022\ufff0\023\ufff0\025\ufff0\026\ufff0\027\ufff0\030\ufff0" +
    "\031\ufff0\032\ufff0\033\ufff0\001\002\000\044\002\uffef\011" +
    "\uffef\012\uffef\013\uffef\014\uffef\015\uffef\016\uffef\017\021" +
    "\022\uffef\023\uffef\025\uffef\026\uffef\027\uffef\030\uffef\031" +
    "\uffef\032\uffef\033\uffef\001\002\000\032\002\uffe8\011\uffe8" +
    "\012\uffe8\013\024\014\017\015\032\016\031\017\021\022" +
    "\uffe8\023\uffe8\025\uffe8\032\uffe8\001\002\000\032\002\uffe5" +
    "\011\uffe5\012\uffe5\013\024\014\017\015\032\016\031\017" +
    "\021\022\uffe5\023\uffe5\025\uffe5\032\uffe5\001\002\000\032" +
    "\002\uffe9\011\uffe9\012\uffe9\013\024\014\017\015\032\016" +
    "\031\017\021\022\uffe9\023\uffe9\025\uffe9\032\uffe9\001\002" +
    "\000\044\002\ufff2\011\ufff2\012\ufff2\013\ufff2\014\ufff2\015" +
    "\032\016\031\017\021\022\ufff2\023\ufff2\025\ufff2\026\ufff2" +
    "\027\ufff2\030\ufff2\031\ufff2\032\ufff2\033\ufff2\001\002\000" +
    "\042\002\uffeb\011\uffeb\012\uffeb\013\024\014\017\015\032" +
    "\016\031\017\021\022\uffeb\023\uffeb\026\033\027\025\030" +
    "\027\031\022\032\uffeb\033\026\001\002\000\032\002\uffe7" +
    "\011\uffe7\012\uffe7\013\024\014\017\015\032\016\031\017" +
    "\021\022\uffe7\023\uffe7\025\uffe7\032\uffe7\001\002\000\044" +
    "\002\ufff3\011\ufff3\012\ufff3\013\ufff3\014\ufff3\015\ufff3\016" +
    "\ufff3\017\021\022\ufff3\023\ufff3\025\ufff3\026\ufff3\027\ufff3" +
    "\030\ufff3\031\ufff3\032\ufff3\033\ufff3\001\002\000\044\002" +
    "\uffee\011\uffee\012\uffee\013\024\014\017\015\032\016\031" +
    "\017\021\022\uffee\023\uffee\025\023\026\033\027\025\030" +
    "\027\031\022\032\035\033\026\001\002\000\044\002\ufff1" +
    "\011\ufff1\012\ufff1\013\ufff1\014\ufff1\015\032\016\031\017" +
    "\021\022\ufff1\023\ufff1\025\ufff1\026\ufff1\027\ufff1\030\ufff1" +
    "\031\ufff1\032\ufff1\033\ufff1\001\002\000\044\002\uffec\011" +
    "\uffec\012\uffec\013\uffec\014\uffec\015\uffec\016\uffec\017\021" +
    "\022\uffec\023\uffec\025\uffec\026\uffec\027\uffec\030\uffec\031" +
    "\uffec\032\uffec\033\uffec\001\002\000\004\002\001\001\002" +
    "\000\024\004\010\005\015\006\007\007\005\010\014\011" +
    "\ufffc\013\006\014\004\024\013\001\002\000\006\011\ufffd" +
    "\012\063\001\002\000\004\011\062\001\002\000\042\011" +
    "\ufffe\012\ufffe\013\024\014\017\015\032\016\031\017\021" +
    "\022\020\023\034\025\023\026\033\027\025\030\027\031" +
    "\022\032\035\033\026\001\002\000\044\002\ufffa\011\ufffa" +
    "\012\ufffa\013\ufffa\014\ufffa\015\ufffa\016\ufffa\017\ufffa\022" +
    "\ufffa\023\ufffa\025\ufffa\026\ufffa\027\ufffa\030\ufffa\031\ufffa" +
    "\032\ufffa\033\ufffa\001\002\000\022\004\010\005\015\006" +
    "\007\007\005\010\014\013\006\014\004\024\013\001\002" +
    "\000\042\011\uffff\012\uffff\013\024\014\017\015\032\016" +
    "\031\017\021\022\020\023\034\025\023\026\033\027\025" +
    "\030\027\031\022\032\035\033\026\001\002\000\044\002" +
    "\ufff5\011\ufff5\012\ufff5\013\ufff5\014\ufff5\015\ufff5\016\ufff5" +
    "\017\021\022\ufff5\023\ufff5\025\ufff5\026\ufff5\027\ufff5\030" +
    "\ufff5\031\ufff5\032\ufff5\033\ufff5\001\002\000\044\002\ufff4" +
    "\011\ufff4\012\ufff4\013\ufff4\014\ufff4\015\ufff4\016\ufff4\017" +
    "\021\022\ufff4\023\ufff4\025\ufff4\026\ufff4\027\ufff4\030\ufff4" +
    "\031\ufff4\032\ufff4\033\ufff4\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\064\000\006\002\010\003\011\001\001\000\004\003" +
    "\065\001\001\000\002\001\001\000\004\003\064\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\004\003\053\001\001\000\004\003\015" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\003" +
    "\052\001\001\000\004\003\051\001\001\000\004\003\050" +
    "\001\001\000\004\003\047\001\001\000\004\003\046\001" +
    "\001\000\004\003\045\001\001\000\004\003\044\001\001" +
    "\000\004\003\043\001\001\000\004\003\042\001\001\000" +
    "\002\001\001\000\004\003\041\001\001\000\004\003\040" +
    "\001\001\000\004\003\037\001\001\000\004\003\036\001" +
    "\001\000\004\003\035\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\010\003\060\004\057\005" +
    "\056\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\004\003\063\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



  /** the variables available to the program */
  private VariableDeclarations variables = new NoVariables();

  /** the macros available to the program */
  private MacroDeclarations macros = new NoMacros();
  
  /** the root node */
  private Node root;
  
  /**
   * Sets the variable declarations for the program
   *
   * @param variables the variables that should be exposed to the program
   */
  public void setVariables(VariableDeclarations variables) {
    this.variables = variables;
  }
  
  /**
   * Sets the macro declarations for the program
   *
   * @param macros the macros that should be exposed to the program
   */
  public void setMacros(MacroDeclarations macros) {
    this.macros = macros;
  }
  
  /**
   * Sets the root node
   */
  private void setRoot(Node root) {
    this.root = root;
  }
  
  /**
   * Returns the root node of the program
   *
   * @return the root node
   */
  public Node getRoot() {
    return root;
  }
  
  /**
   * Tries to parse and compile a program from the textual representation in
   * expr while exposing the variables and marcos
   *
   * @param expr the expression to be compiled in textual form
   * @param variables the variables exposed to the program
   * @param macros the macros exposed to the program
   * @return the root node of the compiled program
   * @throws Exception if an error occurs during compilation
   */
  public static Node parse(String expr, VariableDeclarations variables,
    MacroDeclarations macros) throws Exception {
  
    Parser parser = new Parser(new Scanner(new StringReader(expr)));
    parser.setVariables(variables);
    parser.setMacros(macros);
    parser.parse();
    return parser.getRoot();
  }
  
  public void unrecovered_syntax_error(Symbol token) throws SyntaxException {
    throw new SyntaxException("Syntax error at token '"
      + sym.terminalNames[token.sym] + "'!");
  }


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$Parser$actions {
  private final Parser parser;

  /** Constructor */
  CUP$Parser$actions(Parser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action_part00000000(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Parser$result;

      /* select the action based on the action number */
      switch (CUP$Parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= unit EOF 
            {
              Object RESULT =null;
		Node start_val = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		RESULT = start_val;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("$START",0, RESULT);
            }
          /* ACCEPT */
          CUP$Parser$parser.done_parsing();
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // unit ::= expr 
            {
              Node RESULT =null;
		Node e = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = e;
                      setRoot(RESULT);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("unit",0, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // paramlist ::= paramlist COMMA expr 
            {
              List<Node> RESULT =null;
		List<Node> l = (List<Node>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		Node e = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                       l.add(e);
                       RESULT = l;
                     
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("paramlist",3, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // paramlist ::= expr 
            {
              List<Node> RESULT =null;
		Node e = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                       List<Node> l = new ArrayList<Node>();
                       l.add(e);
                       RESULT = l;
                     
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("paramlist",3, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // paramlistOpt ::= paramlist 
            {
              List<Node> RESULT =null;
		List<Node> l = (List<Node>)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                       RESULT = l;
                     
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("paramlistOpt",2, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // paramlistOpt ::= 
            {
              List<Node> RESULT =null;
		
                       RESULT = new ArrayList<Node>();
                     
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("paramlistOpt",2, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // expr ::= LPAREN expr RPAREN 
            {
              Node RESULT =null;
		Node e = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		
                       RESULT = e;
                     
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // expr ::= IDENTIFIER LPAREN paramlistOpt RPAREN 
            {
              Node RESULT =null;
		String m = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		List<Node> p = (List<Node>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		
                      if (!macros.hasMacro(m))
                        throw new SemanticException("Macro '" + m + "' is undefined!");
                      RESULT = macros.getMacro(m).evaluate(p.toArray(new Node[0]));
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // expr ::= IDENTIFIER 
            {
              Node RESULT =null;
		String v = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      if (!variables.hasVariable(v))
                        throw new SemanticException("Variable '" + v + "' is undefined!");
                      RESULT = variables.getVariable(v);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // expr ::= FLOAT 
            {
              Node RESULT =null;
		Double f = (Double)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = new DoubleConstant(f);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // expr ::= BOOLEAN 
            {
              Node RESULT =null;
		Boolean b = (Boolean)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = new BooleanConstant(b);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // expr ::= STRING 
            {
              Node RESULT =null;
		String s = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = new StringConstant(s);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // expr ::= PLUS expr 
            {
              Node RESULT =null;
		Node e = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = Operators.uplus(e);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // expr ::= MINUS expr 
            {
              Node RESULT =null;
		Node e = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = Operators.uminus(e);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // expr ::= expr POW expr 
            {
              Node RESULT =null;
		Node l = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		Node r = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = Operators.pow(l, r);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // expr ::= expr PLUS expr 
            {
              Node RESULT =null;
		Node l = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		Node r = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = Operators.plus(l, r);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // expr ::= expr MINUS expr 
            {
              Node RESULT =null;
		Node l = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		Node r = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = Operators.minus(l, r);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // expr ::= expr TIMES expr 
            {
              Node RESULT =null;
		Node l = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		Node r = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = Operators.times(l, r);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // expr ::= expr DIVISION expr 
            {
              Node RESULT =null;
		Node l = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		Node r = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = Operators.division(l, r);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // expr ::= expr AND expr 
            {
              Node RESULT =null;
		Node l = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		Node r = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = Operators.and(l, r);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // expr ::= expr OR expr 
            {
              Node RESULT =null;
		Node l = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		Node r = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = Operators.or(l, r);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // expr ::= NOT expr 
            {
              Node RESULT =null;
		Node e = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = Operators.not(e);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // expr ::= expr EQUAL expr 
            {
              Node RESULT =null;
		Node l = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		Node r = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = Operators.equal(l, r);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // expr ::= expr LT expr 
            {
              Node RESULT =null;
		Node l = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		Node r = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = Operators.lessThan(l, r);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // expr ::= expr LE expr 
            {
              Node RESULT =null;
		Node l = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		Node r = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = Operators.lessEqual(l, r);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // expr ::= expr GT expr 
            {
              Node RESULT =null;
		Node l = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		Node r = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = Operators.greaterThan(l, r);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // expr ::= expr GE expr 
            {
              Node RESULT =null;
		Node l = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		Node r = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = Operators.greaterEqual(l, r);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // expr ::= expr IS expr 
            {
              Node RESULT =null;
		Node l = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		Node r = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = Operators.is(l, r);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28: // expr ::= expr REGEXP expr 
            {
              Node RESULT =null;
		Node l = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		Node r = (Node)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = Operators.regexp(l, r);
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",1, RESULT);
            }
          return CUP$Parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$Parser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
              return CUP$Parser$do_action_part00000000(
                               CUP$Parser$act_num,
                               CUP$Parser$parser,
                               CUP$Parser$stack,
                               CUP$Parser$top);
    }
}

}
