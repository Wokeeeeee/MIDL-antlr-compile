// Generated from MIDL.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MIDLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		INTEGER=39, FLOATING_PT=40, CHAR=41, STRING=42, BOOLEAN=43, ID=44, WS=45, 
		MODULE=46, STRUCT=47, SHORT=48, LONG=49, UNSIGNED=50, INT8=51, INT16=52, 
		INT32=53, INT64=54, UINT8=55, UINT16=56, UINT32=57, UINT64=58, FLOAT=59, 
		DOUBLE=60, TRUE=61, FALSE=62;
	public static final int
		RULE_specification = 0, RULE_definition = 1, RULE_module = 2, RULE_type_decl = 3, 
		RULE_struct_type = 4, RULE_member_list = 5, RULE_type_spec = 6, RULE_scoped_name = 7, 
		RULE_base_type_spec = 8, RULE_floating_pt_type = 9, RULE_integer_type = 10, 
		RULE_signed_int = 11, RULE_unsigned_int = 12, RULE_declarators = 13, RULE_declarator = 14, 
		RULE_simple_declarator = 15, RULE_array_declarator = 16, RULE_exp_list = 17, 
		RULE_or_expr = 18, RULE_xor_expr = 19, RULE_and_expr = 20, RULE_shift_expr = 21, 
		RULE_add_expr = 22, RULE_mult_expr = 23, RULE_unary_expr = 24, RULE_literal = 25;
	private static String[] makeRuleNames() {
		return new String[] {
			"specification", "definition", "module", "type_decl", "struct_type", 
			"member_list", "type_spec", "scoped_name", "base_type_spec", "floating_pt_type", 
			"integer_type", "signed_int", "unsigned_int", "declarators", "declarator", 
			"simple_declarator", "array_declarator", "exp_list", "or_expr", "xor_expr", 
			"and_expr", "shift_expr", "add_expr", "mult_expr", "unary_expr", "literal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'module'", "'{'", "'}'", "'struct'", "'::'", "'char'", 
			"'string'", "'boolean'", "'float'", "'double'", "'long double'", "'short'", 
			"'int16'", "'long'", "'int32'", "'int64'", "'int8'", "'unsigned'", "'uint16'", 
			"'uint32'", "'uint64'", "'uint8'", "','", "'='", "'['", "']'", "'|'", 
			"'^'", "'&'", "'>>'", "'<<'", "'+'", "'-'", "'*'", "'/'", "'%'", "'~'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "INTEGER", "FLOATING_PT", "CHAR", "STRING", "BOOLEAN", 
			"ID", "WS", "MODULE", "STRUCT", "SHORT", "LONG", "UNSIGNED", "INT8", 
			"INT16", "INT32", "INT64", "UINT8", "UINT16", "UINT32", "UINT64", "FLOAT", 
			"DOUBLE", "TRUE", "FALSE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MIDL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MIDLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SpecificationContext extends ParserRuleContext {
		public List<DefinitionContext> definition() {
			return getRuleContexts(DefinitionContext.class);
		}
		public DefinitionContext definition(int i) {
			return getRuleContext(DefinitionContext.class,i);
		}
		public SpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitSpecification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitSpecification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpecificationContext specification() throws RecognitionException {
		SpecificationContext _localctx = new SpecificationContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_specification);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(52);
				definition();
				}
				}
				setState(55); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__1 || _la==T__4 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefinitionContext extends ParserRuleContext {
		public Type_declContext type_decl() {
			return getRuleContext(Type_declContext.class,0);
		}
		public ModuleContext module() {
			return getRuleContext(ModuleContext.class,0);
		}
		public DefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefinitionContext definition() throws RecognitionException {
		DefinitionContext _localctx = new DefinitionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_definition);
		try {
			setState(63);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				type_decl();
				setState(58);
				match(T__0);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				module();
				setState(61);
				match(T__0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModuleContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MIDLParser.ID, 0); }
		public List<DefinitionContext> definition() {
			return getRuleContexts(DefinitionContext.class);
		}
		public DefinitionContext definition(int i) {
			return getRuleContext(DefinitionContext.class,i);
		}
		public ModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterModule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitModule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitModule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleContext module() throws RecognitionException {
		ModuleContext _localctx = new ModuleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_module);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(T__1);
			setState(66);
			match(ID);
			setState(67);
			match(T__2);
			setState(69); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(68);
				definition();
				}
				}
				setState(71); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__1 || _la==T__4 );
			setState(73);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_declContext extends ParserRuleContext {
		public Struct_typeContext struct_type() {
			return getRuleContext(Struct_typeContext.class,0);
		}
		public TerminalNode ID() { return getToken(MIDLParser.ID, 0); }
		public Type_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterType_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitType_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitType_decl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_declContext type_decl() throws RecognitionException {
		Type_declContext _localctx = new Type_declContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type_decl);
		try {
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				struct_type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				match(T__4);
				setState(77);
				match(ID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Struct_typeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MIDLParser.ID, 0); }
		public Member_listContext member_list() {
			return getRuleContext(Member_listContext.class,0);
		}
		public Struct_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterStruct_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitStruct_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitStruct_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Struct_typeContext struct_type() throws RecognitionException {
		Struct_typeContext _localctx = new Struct_typeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_struct_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__4);
			setState(81);
			match(ID);
			setState(82);
			match(T__2);
			setState(83);
			member_list();
			setState(84);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Member_listContext extends ParserRuleContext {
		public List<Type_specContext> type_spec() {
			return getRuleContexts(Type_specContext.class);
		}
		public Type_specContext type_spec(int i) {
			return getRuleContext(Type_specContext.class,i);
		}
		public List<DeclaratorsContext> declarators() {
			return getRuleContexts(DeclaratorsContext.class);
		}
		public DeclaratorsContext declarators(int i) {
			return getRuleContext(DeclaratorsContext.class,i);
		}
		public Member_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterMember_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitMember_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitMember_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Member_listContext member_list() throws RecognitionException {
		Member_listContext _localctx = new Member_listContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_member_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << ID))) != 0)) {
				{
				{
				setState(86);
				type_spec();
				setState(87);
				declarators();
				setState(88);
				match(T__0);
				}
				}
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_specContext extends ParserRuleContext {
		public Scoped_nameContext scoped_name() {
			return getRuleContext(Scoped_nameContext.class,0);
		}
		public Base_type_specContext base_type_spec() {
			return getRuleContext(Base_type_specContext.class,0);
		}
		public Struct_typeContext struct_type() {
			return getRuleContext(Struct_typeContext.class,0);
		}
		public Type_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterType_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitType_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitType_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_specContext type_spec() throws RecognitionException {
		Type_specContext _localctx = new Type_specContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_type_spec);
		try {
			setState(98);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				scoped_name();
				}
				break;
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
				enterOuterAlt(_localctx, 2);
				{
				setState(96);
				base_type_spec();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(97);
				struct_type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Scoped_nameContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(MIDLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(MIDLParser.ID, i);
		}
		public Scoped_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scoped_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterScoped_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitScoped_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitScoped_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Scoped_nameContext scoped_name() throws RecognitionException {
		Scoped_nameContext _localctx = new Scoped_nameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_scoped_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(100);
				match(T__5);
				}
			}

			setState(103);
			match(ID);
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(104);
				match(T__5);
				setState(105);
				match(ID);
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_type_specContext extends ParserRuleContext {
		public Floating_pt_typeContext floating_pt_type() {
			return getRuleContext(Floating_pt_typeContext.class,0);
		}
		public Integer_typeContext integer_type() {
			return getRuleContext(Integer_typeContext.class,0);
		}
		public Base_type_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_type_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterBase_type_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitBase_type_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitBase_type_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Base_type_specContext base_type_spec() throws RecognitionException {
		Base_type_specContext _localctx = new Base_type_specContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_base_type_spec);
		try {
			setState(116);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
			case T__10:
			case T__11:
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				floating_pt_type();
				}
				break;
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				integer_type();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 3);
				{
				setState(113);
				match(T__6);
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 4);
				{
				setState(114);
				match(T__7);
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 5);
				{
				setState(115);
				match(T__8);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Floating_pt_typeContext extends ParserRuleContext {
		public Floating_pt_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floating_pt_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterFloating_pt_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitFloating_pt_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitFloating_pt_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Floating_pt_typeContext floating_pt_type() throws RecognitionException {
		Floating_pt_typeContext _localctx = new Floating_pt_typeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_floating_pt_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Integer_typeContext extends ParserRuleContext {
		public Signed_intContext signed_int() {
			return getRuleContext(Signed_intContext.class,0);
		}
		public Unsigned_intContext unsigned_int() {
			return getRuleContext(Unsigned_intContext.class,0);
		}
		public Integer_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterInteger_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitInteger_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitInteger_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Integer_typeContext integer_type() throws RecognitionException {
		Integer_typeContext _localctx = new Integer_typeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_integer_type);
		try {
			setState(122);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
				enterOuterAlt(_localctx, 1);
				{
				setState(120);
				signed_int();
				}
				break;
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
				enterOuterAlt(_localctx, 2);
				{
				setState(121);
				unsigned_int();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Signed_intContext extends ParserRuleContext {
		public Signed_intContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signed_int; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterSigned_int(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitSigned_int(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitSigned_int(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Signed_intContext signed_int() throws RecognitionException {
		Signed_intContext _localctx = new Signed_intContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_signed_int);
		int _la;
		try {
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				_la = _input.LA(1);
				if ( !(_la==T__12 || _la==T__13) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(125);
				_la = _input.LA(1);
				if ( !(_la==T__14 || _la==T__15) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(129);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__14:
					{
					setState(126);
					match(T__14);
					setState(127);
					match(T__14);
					}
					break;
				case T__16:
					{
					setState(128);
					match(T__16);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(131);
				match(T__17);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Unsigned_intContext extends ParserRuleContext {
		public Unsigned_intContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unsigned_int; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterUnsigned_int(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitUnsigned_int(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitUnsigned_int(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unsigned_intContext unsigned_int() throws RecognitionException {
		Unsigned_intContext _localctx = new Unsigned_intContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_unsigned_int);
		try {
			setState(151);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__18:
					{
					setState(134);
					match(T__18);
					setState(135);
					match(T__12);
					}
					break;
				case T__19:
					{
					setState(136);
					match(T__19);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__18:
					{
					setState(139);
					match(T__18);
					setState(140);
					match(T__14);
					}
					break;
				case T__20:
					{
					setState(141);
					match(T__20);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(148);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__18:
					{
					setState(144);
					match(T__18);
					setState(145);
					match(T__14);
					setState(146);
					match(T__14);
					}
					break;
				case T__21:
					{
					setState(147);
					match(T__21);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(150);
				match(T__22);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaratorsContext extends ParserRuleContext {
		public List<DeclaratorContext> declarator() {
			return getRuleContexts(DeclaratorContext.class);
		}
		public DeclaratorContext declarator(int i) {
			return getRuleContext(DeclaratorContext.class,i);
		}
		public DeclaratorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarators; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterDeclarators(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitDeclarators(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitDeclarators(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaratorsContext declarators() throws RecognitionException {
		DeclaratorsContext _localctx = new DeclaratorsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_declarators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			declarator();
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__23) {
				{
				{
				setState(154);
				match(T__23);
				setState(155);
				declarator();
				}
				}
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaratorContext extends ParserRuleContext {
		public Simple_declaratorContext simple_declarator() {
			return getRuleContext(Simple_declaratorContext.class,0);
		}
		public Array_declaratorContext array_declarator() {
			return getRuleContext(Array_declaratorContext.class,0);
		}
		public DeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitDeclarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitDeclarator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaratorContext declarator() throws RecognitionException {
		DeclaratorContext _localctx = new DeclaratorContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_declarator);
		try {
			setState(163);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(161);
				simple_declarator();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(162);
				array_declarator();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Simple_declaratorContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MIDLParser.ID, 0); }
		public Or_exprContext or_expr() {
			return getRuleContext(Or_exprContext.class,0);
		}
		public Simple_declaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterSimple_declarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitSimple_declarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitSimple_declarator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_declaratorContext simple_declarator() throws RecognitionException {
		Simple_declaratorContext _localctx = new Simple_declaratorContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_simple_declarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			match(ID);
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__24) {
				{
				setState(166);
				match(T__24);
				setState(167);
				or_expr();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Array_declaratorContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MIDLParser.ID, 0); }
		public Or_exprContext or_expr() {
			return getRuleContext(Or_exprContext.class,0);
		}
		public Exp_listContext exp_list() {
			return getRuleContext(Exp_listContext.class,0);
		}
		public Array_declaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterArray_declarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitArray_declarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitArray_declarator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Array_declaratorContext array_declarator() throws RecognitionException {
		Array_declaratorContext _localctx = new Array_declaratorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_array_declarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(ID);
			setState(171);
			match(T__25);
			setState(172);
			or_expr();
			setState(173);
			match(T__26);
			setState(176);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__24) {
				{
				setState(174);
				match(T__24);
				setState(175);
				exp_list();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Exp_listContext extends ParserRuleContext {
		public List<Or_exprContext> or_expr() {
			return getRuleContexts(Or_exprContext.class);
		}
		public Or_exprContext or_expr(int i) {
			return getRuleContext(Or_exprContext.class,i);
		}
		public Exp_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterExp_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitExp_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitExp_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exp_listContext exp_list() throws RecognitionException {
		Exp_listContext _localctx = new Exp_listContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_exp_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(T__25);
			setState(179);
			or_expr();
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__23) {
				{
				{
				setState(180);
				match(T__23);
				setState(181);
				or_expr();
				}
				}
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(187);
			match(T__26);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Or_exprContext extends ParserRuleContext {
		public List<Xor_exprContext> xor_expr() {
			return getRuleContexts(Xor_exprContext.class);
		}
		public Xor_exprContext xor_expr(int i) {
			return getRuleContext(Xor_exprContext.class,i);
		}
		public Or_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterOr_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitOr_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitOr_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Or_exprContext or_expr() throws RecognitionException {
		Or_exprContext _localctx = new Or_exprContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_or_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			xor_expr();
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__27) {
				{
				{
				setState(190);
				match(T__27);
				setState(191);
				xor_expr();
				}
				}
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Xor_exprContext extends ParserRuleContext {
		public List<And_exprContext> and_expr() {
			return getRuleContexts(And_exprContext.class);
		}
		public And_exprContext and_expr(int i) {
			return getRuleContext(And_exprContext.class,i);
		}
		public Xor_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xor_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterXor_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitXor_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitXor_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Xor_exprContext xor_expr() throws RecognitionException {
		Xor_exprContext _localctx = new Xor_exprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_xor_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			and_expr();
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(198);
				match(T__28);
				setState(199);
				and_expr();
				}
				}
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class And_exprContext extends ParserRuleContext {
		public List<Shift_exprContext> shift_expr() {
			return getRuleContexts(Shift_exprContext.class);
		}
		public Shift_exprContext shift_expr(int i) {
			return getRuleContext(Shift_exprContext.class,i);
		}
		public And_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterAnd_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitAnd_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitAnd_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final And_exprContext and_expr() throws RecognitionException {
		And_exprContext _localctx = new And_exprContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_and_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			shift_expr();
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__29) {
				{
				{
				setState(206);
				match(T__29);
				setState(207);
				shift_expr();
				}
				}
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Shift_exprContext extends ParserRuleContext {
		public List<Add_exprContext> add_expr() {
			return getRuleContexts(Add_exprContext.class);
		}
		public Add_exprContext add_expr(int i) {
			return getRuleContext(Add_exprContext.class,i);
		}
		public Shift_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shift_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterShift_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitShift_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitShift_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Shift_exprContext shift_expr() throws RecognitionException {
		Shift_exprContext _localctx = new Shift_exprContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_shift_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			add_expr();
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__30 || _la==T__31) {
				{
				{
				setState(214);
				_la = _input.LA(1);
				if ( !(_la==T__30 || _la==T__31) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(215);
				add_expr();
				}
				}
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Add_exprContext extends ParserRuleContext {
		public List<Mult_exprContext> mult_expr() {
			return getRuleContexts(Mult_exprContext.class);
		}
		public Mult_exprContext mult_expr(int i) {
			return getRuleContext(Mult_exprContext.class,i);
		}
		public Add_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_add_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterAdd_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitAdd_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitAdd_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Add_exprContext add_expr() throws RecognitionException {
		Add_exprContext _localctx = new Add_exprContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_add_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			mult_expr();
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__32 || _la==T__33) {
				{
				{
				setState(222);
				_la = _input.LA(1);
				if ( !(_la==T__32 || _la==T__33) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(223);
				mult_expr();
				}
				}
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Mult_exprContext extends ParserRuleContext {
		public List<Unary_exprContext> unary_expr() {
			return getRuleContexts(Unary_exprContext.class);
		}
		public Unary_exprContext unary_expr(int i) {
			return getRuleContext(Unary_exprContext.class,i);
		}
		public Mult_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mult_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterMult_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitMult_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitMult_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Mult_exprContext mult_expr() throws RecognitionException {
		Mult_exprContext _localctx = new Mult_exprContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_mult_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			unary_expr();
			setState(234);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__34) | (1L << T__35) | (1L << T__36))) != 0)) {
				{
				{
				setState(230);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__34) | (1L << T__35) | (1L << T__36))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(231);
				unary_expr();
				}
				}
				setState(236);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Unary_exprContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public Unary_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterUnary_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitUnary_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitUnary_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unary_exprContext unary_expr() throws RecognitionException {
		Unary_exprContext _localctx = new Unary_exprContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_unary_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__32) | (1L << T__33) | (1L << T__37))) != 0)) {
				{
				setState(237);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__32) | (1L << T__33) | (1L << T__37))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(240);
			literal();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(MIDLParser.INTEGER, 0); }
		public TerminalNode FLOATING_PT() { return getToken(MIDLParser.FLOATING_PT, 0); }
		public TerminalNode CHAR() { return getToken(MIDLParser.CHAR, 0); }
		public TerminalNode STRING() { return getToken(MIDLParser.STRING, 0); }
		public TerminalNode BOOLEAN() { return getToken(MIDLParser.BOOLEAN, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MIDLListener ) ((MIDLListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MIDLVisitor ) return ((MIDLVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << FLOATING_PT) | (1L << CHAR) | (1L << STRING) | (1L << BOOLEAN))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001>\u00f5\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0001\u0000\u0004\u00006\b\u0000\u000b\u0000"+
		"\f\u00007\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001@\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0004\u0002F\b\u0002\u000b\u0002\f\u0002G\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003O\b\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005[\b\u0005\n\u0005"+
		"\f\u0005^\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006c\b\u0006"+
		"\u0001\u0007\u0003\u0007f\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0005\u0007k\b\u0007\n\u0007\f\u0007n\t\u0007\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0003\bu\b\b\u0001\t\u0001\t\u0001\n\u0001\n\u0003\n"+
		"{\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u0082\b\u000b\u0001\u000b\u0003\u000b\u0085\b\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u008a\b\f\u0001\f\u0001\f\u0001\f\u0003\f\u008f\b\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u0095\b\f\u0001\f\u0003\f\u0098"+
		"\b\f\u0001\r\u0001\r\u0001\r\u0005\r\u009d\b\r\n\r\f\r\u00a0\t\r\u0001"+
		"\u000e\u0001\u000e\u0003\u000e\u00a4\b\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0003\u000f\u00a9\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00b1\b\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u00b7\b\u0011\n\u0011\f\u0011"+
		"\u00ba\t\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0005\u0012\u00c1\b\u0012\n\u0012\f\u0012\u00c4\t\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0005\u0013\u00c9\b\u0013\n\u0013\f\u0013\u00cc\t\u0013"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u00d1\b\u0014\n\u0014"+
		"\f\u0014\u00d4\t\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015"+
		"\u00d9\b\u0015\n\u0015\f\u0015\u00dc\t\u0015\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0005\u0016\u00e1\b\u0016\n\u0016\f\u0016\u00e4\t\u0016\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0005\u0017\u00e9\b\u0017\n\u0017\f\u0017\u00ec"+
		"\t\u0017\u0001\u0018\u0003\u0018\u00ef\b\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0000\u0000\u001a\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \""+
		"$&(*,.02\u0000\b\u0001\u0000\n\f\u0001\u0000\r\u000e\u0001\u0000\u000f"+
		"\u0010\u0001\u0000\u001f \u0001\u0000!\"\u0001\u0000#%\u0002\u0000!\""+
		"&&\u0001\u0000\'+\u00fe\u00005\u0001\u0000\u0000\u0000\u0002?\u0001\u0000"+
		"\u0000\u0000\u0004A\u0001\u0000\u0000\u0000\u0006N\u0001\u0000\u0000\u0000"+
		"\bP\u0001\u0000\u0000\u0000\n\\\u0001\u0000\u0000\u0000\fb\u0001\u0000"+
		"\u0000\u0000\u000ee\u0001\u0000\u0000\u0000\u0010t\u0001\u0000\u0000\u0000"+
		"\u0012v\u0001\u0000\u0000\u0000\u0014z\u0001\u0000\u0000\u0000\u0016\u0084"+
		"\u0001\u0000\u0000\u0000\u0018\u0097\u0001\u0000\u0000\u0000\u001a\u0099"+
		"\u0001\u0000\u0000\u0000\u001c\u00a3\u0001\u0000\u0000\u0000\u001e\u00a5"+
		"\u0001\u0000\u0000\u0000 \u00aa\u0001\u0000\u0000\u0000\"\u00b2\u0001"+
		"\u0000\u0000\u0000$\u00bd\u0001\u0000\u0000\u0000&\u00c5\u0001\u0000\u0000"+
		"\u0000(\u00cd\u0001\u0000\u0000\u0000*\u00d5\u0001\u0000\u0000\u0000,"+
		"\u00dd\u0001\u0000\u0000\u0000.\u00e5\u0001\u0000\u0000\u00000\u00ee\u0001"+
		"\u0000\u0000\u00002\u00f2\u0001\u0000\u0000\u000046\u0003\u0002\u0001"+
		"\u000054\u0001\u0000\u0000\u000067\u0001\u0000\u0000\u000075\u0001\u0000"+
		"\u0000\u000078\u0001\u0000\u0000\u00008\u0001\u0001\u0000\u0000\u0000"+
		"9:\u0003\u0006\u0003\u0000:;\u0005\u0001\u0000\u0000;@\u0001\u0000\u0000"+
		"\u0000<=\u0003\u0004\u0002\u0000=>\u0005\u0001\u0000\u0000>@\u0001\u0000"+
		"\u0000\u0000?9\u0001\u0000\u0000\u0000?<\u0001\u0000\u0000\u0000@\u0003"+
		"\u0001\u0000\u0000\u0000AB\u0005\u0002\u0000\u0000BC\u0005,\u0000\u0000"+
		"CE\u0005\u0003\u0000\u0000DF\u0003\u0002\u0001\u0000ED\u0001\u0000\u0000"+
		"\u0000FG\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000GH\u0001\u0000"+
		"\u0000\u0000HI\u0001\u0000\u0000\u0000IJ\u0005\u0004\u0000\u0000J\u0005"+
		"\u0001\u0000\u0000\u0000KO\u0003\b\u0004\u0000LM\u0005\u0005\u0000\u0000"+
		"MO\u0005,\u0000\u0000NK\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000"+
		"O\u0007\u0001\u0000\u0000\u0000PQ\u0005\u0005\u0000\u0000QR\u0005,\u0000"+
		"\u0000RS\u0005\u0003\u0000\u0000ST\u0003\n\u0005\u0000TU\u0005\u0004\u0000"+
		"\u0000U\t\u0001\u0000\u0000\u0000VW\u0003\f\u0006\u0000WX\u0003\u001a"+
		"\r\u0000XY\u0005\u0001\u0000\u0000Y[\u0001\u0000\u0000\u0000ZV\u0001\u0000"+
		"\u0000\u0000[^\u0001\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000\\]\u0001"+
		"\u0000\u0000\u0000]\u000b\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000"+
		"\u0000_c\u0003\u000e\u0007\u0000`c\u0003\u0010\b\u0000ac\u0003\b\u0004"+
		"\u0000b_\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000ba\u0001\u0000"+
		"\u0000\u0000c\r\u0001\u0000\u0000\u0000df\u0005\u0006\u0000\u0000ed\u0001"+
		"\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000"+
		"gl\u0005,\u0000\u0000hi\u0005\u0006\u0000\u0000ik\u0005,\u0000\u0000j"+
		"h\u0001\u0000\u0000\u0000kn\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000"+
		"\u0000lm\u0001\u0000\u0000\u0000m\u000f\u0001\u0000\u0000\u0000nl\u0001"+
		"\u0000\u0000\u0000ou\u0003\u0012\t\u0000pu\u0003\u0014\n\u0000qu\u0005"+
		"\u0007\u0000\u0000ru\u0005\b\u0000\u0000su\u0005\t\u0000\u0000to\u0001"+
		"\u0000\u0000\u0000tp\u0001\u0000\u0000\u0000tq\u0001\u0000\u0000\u0000"+
		"tr\u0001\u0000\u0000\u0000ts\u0001\u0000\u0000\u0000u\u0011\u0001\u0000"+
		"\u0000\u0000vw\u0007\u0000\u0000\u0000w\u0013\u0001\u0000\u0000\u0000"+
		"x{\u0003\u0016\u000b\u0000y{\u0003\u0018\f\u0000zx\u0001\u0000\u0000\u0000"+
		"zy\u0001\u0000\u0000\u0000{\u0015\u0001\u0000\u0000\u0000|\u0085\u0007"+
		"\u0001\u0000\u0000}\u0085\u0007\u0002\u0000\u0000~\u007f\u0005\u000f\u0000"+
		"\u0000\u007f\u0082\u0005\u000f\u0000\u0000\u0080\u0082\u0005\u0011\u0000"+
		"\u0000\u0081~\u0001\u0000\u0000\u0000\u0081\u0080\u0001\u0000\u0000\u0000"+
		"\u0082\u0085\u0001\u0000\u0000\u0000\u0083\u0085\u0005\u0012\u0000\u0000"+
		"\u0084|\u0001\u0000\u0000\u0000\u0084}\u0001\u0000\u0000\u0000\u0084\u0081"+
		"\u0001\u0000\u0000\u0000\u0084\u0083\u0001\u0000\u0000\u0000\u0085\u0017"+
		"\u0001\u0000\u0000\u0000\u0086\u0087\u0005\u0013\u0000\u0000\u0087\u008a"+
		"\u0005\r\u0000\u0000\u0088\u008a\u0005\u0014\u0000\u0000\u0089\u0086\u0001"+
		"\u0000\u0000\u0000\u0089\u0088\u0001\u0000\u0000\u0000\u008a\u0098\u0001"+
		"\u0000\u0000\u0000\u008b\u008c\u0005\u0013\u0000\u0000\u008c\u008f\u0005"+
		"\u000f\u0000\u0000\u008d\u008f\u0005\u0015\u0000\u0000\u008e\u008b\u0001"+
		"\u0000\u0000\u0000\u008e\u008d\u0001\u0000\u0000\u0000\u008f\u0098\u0001"+
		"\u0000\u0000\u0000\u0090\u0091\u0005\u0013\u0000\u0000\u0091\u0092\u0005"+
		"\u000f\u0000\u0000\u0092\u0095\u0005\u000f\u0000\u0000\u0093\u0095\u0005"+
		"\u0016\u0000\u0000\u0094\u0090\u0001\u0000\u0000\u0000\u0094\u0093\u0001"+
		"\u0000\u0000\u0000\u0095\u0098\u0001\u0000\u0000\u0000\u0096\u0098\u0005"+
		"\u0017\u0000\u0000\u0097\u0089\u0001\u0000\u0000\u0000\u0097\u008e\u0001"+
		"\u0000\u0000\u0000\u0097\u0094\u0001\u0000\u0000\u0000\u0097\u0096\u0001"+
		"\u0000\u0000\u0000\u0098\u0019\u0001\u0000\u0000\u0000\u0099\u009e\u0003"+
		"\u001c\u000e\u0000\u009a\u009b\u0005\u0018\u0000\u0000\u009b\u009d\u0003"+
		"\u001c\u000e\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009d\u00a0\u0001"+
		"\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009e\u009f\u0001"+
		"\u0000\u0000\u0000\u009f\u001b\u0001\u0000\u0000\u0000\u00a0\u009e\u0001"+
		"\u0000\u0000\u0000\u00a1\u00a4\u0003\u001e\u000f\u0000\u00a2\u00a4\u0003"+
		" \u0010\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000\u00a3\u00a2\u0001\u0000"+
		"\u0000\u0000\u00a4\u001d\u0001\u0000\u0000\u0000\u00a5\u00a8\u0005,\u0000"+
		"\u0000\u00a6\u00a7\u0005\u0019\u0000\u0000\u00a7\u00a9\u0003$\u0012\u0000"+
		"\u00a8\u00a6\u0001\u0000\u0000\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000"+
		"\u00a9\u001f\u0001\u0000\u0000\u0000\u00aa\u00ab\u0005,\u0000\u0000\u00ab"+
		"\u00ac\u0005\u001a\u0000\u0000\u00ac\u00ad\u0003$\u0012\u0000\u00ad\u00b0"+
		"\u0005\u001b\u0000\u0000\u00ae\u00af\u0005\u0019\u0000\u0000\u00af\u00b1"+
		"\u0003\"\u0011\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001"+
		"\u0000\u0000\u0000\u00b1!\u0001\u0000\u0000\u0000\u00b2\u00b3\u0005\u001a"+
		"\u0000\u0000\u00b3\u00b8\u0003$\u0012\u0000\u00b4\u00b5\u0005\u0018\u0000"+
		"\u0000\u00b5\u00b7\u0003$\u0012\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000"+
		"\u00b7\u00ba\u0001\u0000\u0000\u0000\u00b8\u00b6\u0001\u0000\u0000\u0000"+
		"\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00bb\u0001\u0000\u0000\u0000"+
		"\u00ba\u00b8\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005\u001b\u0000\u0000"+
		"\u00bc#\u0001\u0000\u0000\u0000\u00bd\u00c2\u0003&\u0013\u0000\u00be\u00bf"+
		"\u0005\u001c\u0000\u0000\u00bf\u00c1\u0003&\u0013\u0000\u00c0\u00be\u0001"+
		"\u0000\u0000\u0000\u00c1\u00c4\u0001\u0000\u0000\u0000\u00c2\u00c0\u0001"+
		"\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3%\u0001\u0000"+
		"\u0000\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c5\u00ca\u0003(\u0014"+
		"\u0000\u00c6\u00c7\u0005\u001d\u0000\u0000\u00c7\u00c9\u0003(\u0014\u0000"+
		"\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c9\u00cc\u0001\u0000\u0000\u0000"+
		"\u00ca\u00c8\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000"+
		"\u00cb\'\u0001\u0000\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cd"+
		"\u00d2\u0003*\u0015\u0000\u00ce\u00cf\u0005\u001e\u0000\u0000\u00cf\u00d1"+
		"\u0003*\u0015\u0000\u00d0\u00ce\u0001\u0000\u0000\u0000\u00d1\u00d4\u0001"+
		"\u0000\u0000\u0000\u00d2\u00d0\u0001\u0000\u0000\u0000\u00d2\u00d3\u0001"+
		"\u0000\u0000\u0000\u00d3)\u0001\u0000\u0000\u0000\u00d4\u00d2\u0001\u0000"+
		"\u0000\u0000\u00d5\u00da\u0003,\u0016\u0000\u00d6\u00d7\u0007\u0003\u0000"+
		"\u0000\u00d7\u00d9\u0003,\u0016\u0000\u00d8\u00d6\u0001\u0000\u0000\u0000"+
		"\u00d9\u00dc\u0001\u0000\u0000\u0000\u00da\u00d8\u0001\u0000\u0000\u0000"+
		"\u00da\u00db\u0001\u0000\u0000\u0000\u00db+\u0001\u0000\u0000\u0000\u00dc"+
		"\u00da\u0001\u0000\u0000\u0000\u00dd\u00e2\u0003.\u0017\u0000\u00de\u00df"+
		"\u0007\u0004\u0000\u0000\u00df\u00e1\u0003.\u0017\u0000\u00e0\u00de\u0001"+
		"\u0000\u0000\u0000\u00e1\u00e4\u0001\u0000\u0000\u0000\u00e2\u00e0\u0001"+
		"\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3-\u0001\u0000"+
		"\u0000\u0000\u00e4\u00e2\u0001\u0000\u0000\u0000\u00e5\u00ea\u00030\u0018"+
		"\u0000\u00e6\u00e7\u0007\u0005\u0000\u0000\u00e7\u00e9\u00030\u0018\u0000"+
		"\u00e8\u00e6\u0001\u0000\u0000\u0000\u00e9\u00ec\u0001\u0000\u0000\u0000"+
		"\u00ea\u00e8\u0001\u0000\u0000\u0000\u00ea\u00eb\u0001\u0000\u0000\u0000"+
		"\u00eb/\u0001\u0000\u0000\u0000\u00ec\u00ea\u0001\u0000\u0000\u0000\u00ed"+
		"\u00ef\u0007\u0006\u0000\u0000\u00ee\u00ed\u0001\u0000\u0000\u0000\u00ee"+
		"\u00ef\u0001\u0000\u0000\u0000\u00ef\u00f0\u0001\u0000\u0000\u0000\u00f0"+
		"\u00f1\u00032\u0019\u0000\u00f11\u0001\u0000\u0000\u0000\u00f2\u00f3\u0007"+
		"\u0007\u0000\u0000\u00f33\u0001\u0000\u0000\u0000\u001c7?GN\\beltz\u0081"+
		"\u0084\u0089\u008e\u0094\u0097\u009e\u00a3\u00a8\u00b0\u00b8\u00c2\u00ca"+
		"\u00d2\u00da\u00e2\u00ea\u00ee";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}