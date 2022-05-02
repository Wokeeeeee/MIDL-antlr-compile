grammar MIDL;

tokens{MODULE,
STRUCT,
BOOLEAN,
SHORT,
LONG,
UNSIGNED,
INT8,
INT16,
INT32,
INT64,
UINT8,
UINT16,
UINT32,
UINT64,
CHAR,
STRING,
FLOAT,
DOUBLE,
TRUE,
FALSE
}


/**parse rules**/
specification: (definition)+;

definition: type_decl ';' | module ';';

module: 'module' ID '{' definition+ '}';

type_decl: struct_type | 'struct' ID;

struct_type: 'struct' ID '{'   member_list '}';

member_list: (type_spec declarators ';')*;

type_spec: scoped_name | base_type_spec | struct_type;

scoped_name: ('::')? ID ('::' ID )*;

base_type_spec: floating_pt_type|integer_type|'char'|'string'|'boolean';

floating_pt_type : 'float' | 'double' | 'long double';

integer_type : signed_int | unsigned_int;

signed_int:('short'|'int16')
|('long'|'int32')
|('long' 'long' |'int64')
|'int8';

unsigned_int: ('unsigned' 'short'| 'uint16')
   | ('unsigned' 'long'| 'uint32')
   | ('unsigned' 'long' 'long' | 'uint64')
   | 'uint8';

declarators : declarator (',' declarator )*;

declarator : simple_declarator | array_declarator;

simple_declarator : ID ('=' or_expr)?;

array_declarator : ID '[' or_expr ']' ('=' exp_list )?;

exp_list : '[' or_expr ( ',' or_expr )* ']';

or_expr : xor_expr ('|' xor_expr )*;

xor_expr : and_expr ('^' and_expr )*;

and_expr : shift_expr ('&' shift_expr )*;

shift_expr : add_expr (('>>'|'<<') add_expr )*;

add_expr : mult_expr ( ('+' | '-') mult_expr )*;

mult_expr : unary_expr ( ('*' |'/'|'%') unary_expr )*;

unary_expr : ('-'| '+' | '~')? literal;

literal : INTEGER | FLOATING_PT | CHAR | STRING | BOOLEAN;


/**lexer rules**/
fragment LETTER : [a-z]|[A-Z];

fragment DIGIT : [0-9];

fragment UNDERLINE: '_';

fragment INTEGER_TYPE_SUFFIX :   'l' | 'L';

fragment EXPONENT:( 'e' | 'E') ( '+' | '-' )? [0-9]+;

fragment FLOAT_TYPE_SUFFIX:  'f' | 'F' | 'd' | 'D';

fragment ESCAPE_SEQUENCE :  '\\'( 'b' | 't' | 'n' | 'f' | 'r' | '"' | '\'' | '\\');

INTEGER : ('0' | [1-9] [0-9]*) INTEGER_TYPE_SUFFIX?;

FLOATING_PT:    [0-9]+'.'[0-9]*  EXPONENT?  FLOAT_TYPE_SUFFIX?
   				|  '.'[0-9]+  EXPONENT?  FLOAT_TYPE_SUFFIX?
   				|  [0-9]+  EXPONENT  FLOAT_TYPE_SUFFIX?
   				|  [0-9]+  EXPONENT?  FLOAT_TYPE_SUFFIX
   				;

CHAR: '\''(ESCAPE_SEQUENCE |  ~('\\' | '\'') ) '\'';

STRING :    '"' (ESCAPE_SEQUENCE |  ~('\\' | '"') )* '"';

BOOLEAN :  'TRUE' | 'true' | 'FALSE' | 'false';

ID :  LETTER ((UNDERLINE)?( LETTER | DIGIT))* ;  //问号表示非贪婪模式

WS : ( ' ' | '\t' | '\n' | '\r' ) +{skip();} ;





