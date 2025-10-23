package Parse;
import ErrorMsg.ErrorMsg;


class Yylex implements Lexer {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final char YYEOF = '\uFFFF';

private void newline() {
  errorMsg.newline(yychar);
}
private void err(int pos, String s) {
  errorMsg.error(pos,s);
}
private void err(String s) {
  err(yychar,s);
}
private java_cup.runtime.Symbol tok(int kind) {
    return tok(kind, null);
}
private java_cup.runtime.Symbol tok(int kind, Object value) {
    return new java_cup.runtime.Symbol(kind, yychar, yychar+yylength(), value);
}
private ErrorMsg errorMsg;
private int nested = 0;
Yylex(java.io.InputStream s, ErrorMsg e) {
  this(s);
  errorMsg=e;
}
StringBuffer s= new StringBuffer();
java_cup.runtime.Symbol tok = null;

  private int comment_count = 0;

    private StringBuilder sb = new StringBuilder();
    private char cb;
    private boolean isError = false;
    private int hexToDec;
    private int octToDec;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int OCT_ERROR = 10;
	private final int OCT_DEC = 9;
	private final int HEX_ERROR = 8;
	private final int ESCAPE_STRING = 5;
	private final int HEX_CHAR = 3;
	private final int STRING = 4;
	private final int HEX_DEC = 7;
	private final int YYINITIAL = 0;
	private final int ESCAPE_CHAR = 2;
	private final int HEX = 6;
	private final int COMMENT = 11;
	private final int CHAR = 1;
	private final int yy_state_dtrans[] = {
		0,
		139,
		91,
		141,
		145,
		109,
		147,
		151,
		-1,
		-1,
		-1,
		153
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private char yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YYEOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YYEOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_start () {
		++yychar;
		++yy_buffer_start;
	}
	private void yy_pushback () {
		--yy_buffer_end;
	}
	private void yy_mark_start () {
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
private int [][] unpackFromString(int size1, int size2, String st)
    {
      int colonIndex = -1;
      String lengthString;
      int sequenceLength = 0;
      int sequenceInteger = 0;
      int commaIndex;
      String workString;
      int res[][] = new int[size1][size2];
      for (int i= 0; i < size1; i++)
	for (int j= 0; j < size2; j++)
	  {
	    if (sequenceLength == 0) 
	      {	
		commaIndex = st.indexOf(',');
		if (commaIndex == -1)
		  workString = st;
		else
		  workString = st.substring(0, commaIndex);
		st = st.substring(commaIndex+1);
		colonIndex = workString.indexOf(':');
		if (colonIndex == -1)
		  {
		    res[i][j] = Integer.parseInt(workString);
		  }
		else 
		  {
		    lengthString = workString.substring(colonIndex+1);  
		    sequenceLength = Integer.parseInt(lengthString);
		    workString = workString.substring(0,colonIndex);
		    sequenceInteger = Integer.parseInt(workString);
		    res[i][j] = sequenceInteger;
		    sequenceLength--;
		  }
	      }
	    else 
	      {
		res[i][j] = sequenceInteger;
		sequenceLength--;
	      }
	  }
      return res;
    }
	private int yy_acpt[] = {
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR
	};
	private int yy_cmap[] = {
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 1, 2, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		3, 4, 5, 0, 6, 7, 8, 9,
		10, 11, 12, 13, 14, 15, 16, 17,
		18, 19, 19, 19, 19, 19, 19, 19,
		20, 20, 21, 22, 23, 24, 25, 26,
		0, 27, 27, 27, 27, 27, 27, 28,
		28, 28, 28, 28, 28, 28, 28, 28,
		28, 28, 28, 28, 28, 28, 28, 28,
		29, 28, 28, 30, 31, 32, 33, 34,
		0, 35, 36, 37, 38, 39, 40, 41,
		42, 43, 27, 44, 45, 46, 47, 48,
		49, 27, 50, 51, 52, 53, 54, 55,
		56, 57, 58, 59, 60, 61, 62, 0
		
	};
	private int yy_rmap[] = {
		0, 1, 1, 2, 1, 3, 4, 5,
		6, 1, 1, 7, 8, 1, 9, 10,
		11, 12, 1, 1, 13, 14, 15, 1,
		1, 1, 16, 1, 17, 1, 1, 1,
		1, 1, 1, 1, 1, 1, 1, 1,
		1, 1, 1, 1, 18, 1, 1, 1,
		19, 1, 20, 3, 1, 1, 1, 1,
		1, 3, 3, 3, 3, 1, 3, 21,
		3, 3, 3, 3, 3, 3, 3, 3,
		3, 3, 3, 3, 3, 3, 3, 3,
		3, 3, 3, 3, 3, 3, 3, 22,
		1, 1, 1, 23, 1, 1, 1, 1,
		1, 1, 1, 1, 1, 1, 1, 1,
		1, 24, 1, 1, 1, 25, 1, 1,
		1, 1, 1, 1, 1, 1, 1, 1,
		1, 1, 1, 1, 1, 26, 1, 1,
		1, 27, 28, 29, 30, 31, 32, 33,
		34, 35, 36, 37, 38, 39, 40, 41,
		42, 43, 44, 45, 46, 47, 48, 49,
		50, 51, 52, 53, 54, 55, 56, 57,
		58, 59, 60, 61, 62, 63, 64, 65,
		66, 67, 68, 69, 70, 71, 72, 73,
		74, 75, 76, 77, 78, 79, 80, 81,
		82, 83, 84, 85, 86, 87, 88, 89,
		90, 91, 92, 93, 94, 95, 96, 97,
		98, 99, 100, 101, 102, 103, 104, 105,
		106, 107, 108, 109, 110, 111, 112, 113,
		114, 115, 116, 117, 118, 119, 120, 121,
		122, 123, 124, 125, 126, 127, 128, 129,
		130, 131, 132, 133, 134, 135, 136, 137,
		138, 139, 140, 141, 142, 143, 144, 145,
		146, 147, 148, 149, 150, 151, 152, 153
		
	};
	private int yy_nxt[][] = unpackFromString(154,63,
"-1,1,2,1,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,131:2,18,19,20,21,22,23,5:3,24,-1,25,26,5,202,225,203,130,204,175,205,5,136,5,206,5:4,239,226,245,227,176,228,5:3,27,28,29,30,-1:87,31,-1:44,5,-1:11,5:3,-1:6,5:3,-1:4,5:25,-1:28,32,-1:46,33,-1:15,34,-1:65,129:3,-1:5,129:24,-1:28,35,-1:51,36,-1:10,37,-1:53,38,-1:8,39,40,-1:53,135,-1:58,41,-1:11,42,-1:56,131:3,-1:8,43,-1:26,43,-1:29,44,45,-1:62,46,-1:62,47,48,-1:61,49,-1:62,52,-1:35,53,-1:26,55,-1:62,56,-1:44,5,-1:11,5:3,-1:6,5:3,-1:4,5:19,213,5:5,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,248,5:24,-1:4,87:2,-1,87:6,-1,87:21,-1,87:31,92:2,-1,92:2,93,92:3,94,92:8,132:2,92:9,95,92,96,92:3,97,98,92:3,99,92:6,100,92:2,101,92,102,92,103,92,95,92:6,105:2,-1,105:2,-1,105:25,-1,105:31,110:2,-1,110:2,111,110:3,112,110:8,133:2,110:9,113,110,114,110:3,115,116,110:3,117,110:6,118,110:2,119,110,120,110,121,110,113,110:6,-1:18,125:3,-1:6,125:3,-1:5,125:24,-1:22,137:3,-1:48,5,-1:11,5:3,-1:6,5:3,-1:4,5:5,255,5:8,50,5:10,-1:22,131:3,-1:60,132:2,-1:61,133:2,-1:60,128,-1:61,54,-1:52,5,-1:11,5:3,-1:6,5:3,-1:4,5:6,51,5:6,142,5:11,-1:13,61,-1:59,5,-1:11,5:3,-1:6,5:3,-1:4,5:16,57,5:8,-1:4,87:2,88,87:6,89,87:21,90,87:31,-1:6,5,-1:11,5:3,-1:6,5:3,-1:4,5:13,58,5:11,-1:22,143:3,-1:6,143:3,-1:5,143:24,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:18,59,5:6,-1:22,104:3,-1:6,104:3,-1:5,104:24,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:16,60,5:8,-1:4,105:2,106,105:2,107,105:25,108,105:31,-1:6,5,-1:11,5:3,-1:6,5:3,-1:4,5:14,62,5:10,-1:22,149:3,-1:6,149,-1:7,149:24,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:16,63,5:8,-1:22,122:3,-1:6,122,-1:7,122:24,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:5,64,5:19,-1:6,123,124,-1:14,125:3,-1:6,125:3,-1:5,125:24,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:12,65,5:12,-1:4,126:2,127,126:9,134,126:50,-1:6,5,-1:11,5:3,-1:6,5:3,-1:4,5:14,66,5:10,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:7,67,5:17,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:4,68,5:20,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:10,69,5:14,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:18,70,5:6,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:18,71,5:6,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:18,72,5:6,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:13,73,5:11,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:5,74,5:19,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:5,75,5:19,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:13,76,5:11,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:13,77,5:11,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:6,78,5:18,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:3,79,5:21,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:18,80,5:6,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:6,81,5:18,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:5,82,5:19,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:16,83,5:8,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:5,84,5:19,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:11,85,5:13,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:11,86,5:13,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:11,209,5:2,138,5:4,140,5:5,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5,144,5:12,183,5:10,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:18,146,5:6,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5,148,5:23,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:17,150,5:7,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:19,152,5:5,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:18,154,5:6,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:13,155,5:11,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:9,156,5,242,5:13,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5,157,5:23,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:17,158,234,5:6,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5,159,5:23,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:16,160,5:8,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:14,161,5:10,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:11,162,5:13,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:11,163,5:13,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:16,164,5:8,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:16,165,5:8,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:14,166,5:10,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:9,167,5:15,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:3,168,5:21,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:5,169,5:19,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:19,170,5:5,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:5,171,5:19,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:11,172,5:13,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5,173,5:23,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5,174,5:23,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:19,177,5:5,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:8,178,5:5,208,5:10,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:11,179,5,180,5:8,229,5:2,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:14,181,5:10,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:14,182,5:10,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:5,184,5:19,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:13,185,5:11,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:14,186,5:10,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:14,187,5:10,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:9,188,5:15,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:9,189,5:15,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:2,190,5:22,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:5,191,5:19,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:19,192,5:5,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:5,193,5:19,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:18,194,5:6,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:19,195,5:5,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:4,196,5:20,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:13,197,5:11,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:18,198,5:6,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:9,199,5:15,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:16,200,5:8,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:16,201,5:8,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:16,207,5:8,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:8,210,231,5:8,232,5:6,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:13,211,5:11,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:8,212,5:16,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:18,214,5:6,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:7,241,5:10,215,5:6,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:24,216,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5,217,5:14,218,5:8,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:5,219,5:19,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:9,220,5:15,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:17,221,5:7,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:18,222,5:6,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:5,223,5:19,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:5,224,5:19,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:5,230,5:19,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:15,233,5:9,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:9,235,5:15,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5,236,5:23,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:18,237,5:6,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:18,238,5:6,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:23,240,5,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:9,243,5:15,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:9,244,5:15,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:11,246,5:13,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:11,247,5:13,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,249,5:24,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:11,250,5:13,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5,251,5:23,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:12,252,5:12,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:9,253,5:15,-1:10,5,-1:11,5:3,-1:6,5:3,-1:4,5:3,254,5:21,-1:4");
	public java_cup.runtime.Symbol nextToken ()
		throws java.io.IOException {
		char yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			if (YYEOF != yy_lookahead) {
				yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YYEOF == yy_lookahead && true == yy_initial) {

    if (yy_lexical_state == STRING || yy_lexical_state == ESCAPE_STRING) {           // currently inside string
        //System.out.println("END OF FILE");
        err("Unterminated string literal at EOF");
        yybegin(YYINITIAL);
        return tok(sym.error, null);
    }
    if (yy_lexical_state == CHAR || yy_lexical_state == ESCAPE_CHAR) {           // currently inside string
        //System.out.println("END OF FILE");
        err("Unterminated char literal at EOF");
        yybegin(YYINITIAL);
        return tok(sym.error, null);
    }
    return tok(sym.EOF, null);                  // normal EOF
				}
				else if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_to_mark();
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_pushback();
					}
					if (0 != (YY_START & yy_anchor)) {
						yy_move_start();
					}
					switch (yy_last_accept_state) {
					case 1:
						{}
					case -2:
						break;
					case 2:
						{newline();}
					case -3:
						break;
					case 3:
						{ return tok(sym.NOT); }
					case -4:
						break;
					case 4:
						{ 
    isError = false;
    yybegin(STRING); 
    sb.setLength(0);      // reset string builder
}
					case -5:
						break;
					case 5:
						{ return tok(sym.ID, yytext()); }
					case -6:
						break;
					case 6:
						{ return tok(sym.MODULUS); }
					case -7:
						break;
					case 7:
						{ return tok(sym.BITWISEAND); }
					case -8:
						break;
					case 8:
						{
    isError = false;
    yybegin(CHAR);
    cb = '\0';
}
					case -9:
						break;
					case 9:
						{ return tok(sym.LPAREN); }
					case -10:
						break;
					case 10:
						{ return tok(sym.RPAREN); }
					case -11:
						break;
					case 11:
						{ return tok(sym.TIMES); }
					case -12:
						break;
					case 12:
						{ return tok(sym.PLUS); }
					case -13:
						break;
					case 13:
						{return tok(sym.COMMA);}
					case -14:
						break;
					case 14:
						{ return tok(sym.MINUS); }
					case -15:
						break;
					case 15:
						{ return tok(sym.PERIOD); }
					case -16:
						break;
					case 16:
						{ return tok(sym.DIVIDE); }
					case -17:
						break;
					case 17:
						{ 
    if(yytext().charAt(0) == '0' && yytext().length() > 1) {
        //System.out.println(yytext());
        String oct_temp = yytext();
        int value = 0;
        for(int i = 0; i < oct_temp.length(); i++) {
            int digit;
            char curChar = oct_temp.charAt(i);
            if (curChar >= '0' && curChar <= '7') {
                digit = curChar - '0';
                value = value * 8 + (curChar - '0');
            } else {
                yybegin(YYINITIAL);
                err("Invalid Octal");
                return tok(sym.error, null);
            }
        }
        octToDec =  value;
        yybegin(YYINITIAL);
        return tok(sym.DECIMAL_LITERAL, octToDec);
    } else {
        //System.out.println(yytext());
        String dec_temp = yytext();
        int value = 0;
        for(int i = 0; i < dec_temp.length(); i++) {
            int digit;
            char curChar = dec_temp.charAt(i);
            if (curChar >= '0' && curChar <= '9') {
            } else {
                yybegin(YYINITIAL);
                err("Invalid Decimal");
                return tok(sym.error, null);
            }
        }
        return tok(sym.DECIMAL_LITERAL, Integer.valueOf(yytext()));
        }
}
					case -18:
						break;
					case 18:
						{ return tok(sym.COLON); }
					case -19:
						break;
					case 19:
						{ return tok(sym.SEMICOLON); }
					case -20:
						break;
					case 20:
						{ return tok(sym.LT); }
					case -21:
						break;
					case 21:
						{ return tok(sym.ASSIGN); }
					case -22:
						break;
					case 22:
						{ return tok(sym.GT); }
					case -23:
						break;
					case 23:
						{ return tok(sym.QUESTION); }
					case -24:
						break;
					case 24:
						{ return tok(sym.LBRACK); }
					case -25:
						break;
					case 25:
						{ return tok(sym.RBRACK); }
					case -26:
						break;
					case 26:
						{ return tok(sym.BWISEXOR); }
					case -27:
						break;
					case 27:
						{ return tok(sym.LBRACE); }
					case -28:
						break;
					case 28:
						{ return tok(sym.BWISEOR); }
					case -29:
						break;
					case 29:
						{ return tok(sym.RBRACE); }
					case -30:
						break;
					case 30:
						{ return tok(sym.TILDE); }
					case -31:
						break;
					case 31:
						{ return tok(sym.NEQ); }
					case -32:
						break;
					case 32:
						{ return tok(sym.MODASSIGN); }
					case -33:
						break;
					case 33:
						{ return tok(sym.AND); }
					case -34:
						break;
					case 34:
						{ return tok(sym.BWISEANDASSIGN); }
					case -35:
						break;
					case 35:
						{ return tok(sym.MULASSIGN); }
					case -36:
						break;
					case 36:
						{ return tok(sym.INCREMENT); }
					case -37:
						break;
					case 37:
						{ return tok(sym.ADDASSIGN); }
					case -38:
						break;
					case 38:
						{ return tok(sym.DECREMENT); }
					case -39:
						break;
					case 39:
						{ return tok(sym.SUBASSIGN); }
					case -40:
						break;
					case 40:
						{ return tok(sym.ARROW); }
					case -41:
						break;
					case 41:
						{ yybegin(COMMENT); }
					case -42:
						break;
					case 42:
						{ return tok(sym.DIVASSIGN); }
					case -43:
						break;
					case 43:
						{
  //CHATGBT, hex
  yybegin(HEX_DEC);
}
					case -44:
						break;
					case 44:
						{ return tok(sym.LSHIFT); }
					case -45:
						break;
					case 45:
						{ return tok(sym.LE); }
					case -46:
						break;
					case 46:
						{ return tok(sym.EQ); }
					case -47:
						break;
					case 47:
						{ return tok(sym.GE); }
					case -48:
						break;
					case 48:
						{ return tok(sym.RSHIFT); }
					case -49:
						break;
					case 49:
						{ return tok(sym.BWISEXORASSIGN); }
					case -50:
						break;
					case 50:
						{ return tok(sym.DO); }
					case -51:
						break;
					case 51:
						{ return tok(sym.IF); }
					case -52:
						break;
					case 52:
						{ return tok(sym.BWISEORASSIGN); }
					case -53:
						break;
					case 53:
						{ return tok(sym.OR); }
					case -54:
						break;
					case 54:
						{ return tok(sym.ELIPSES); }
					case -55:
						break;
					case 55:
						{ return tok(sym.LSHIFTASSIGN); }
					case -56:
						break;
					case 56:
						{ return tok(sym.RSHIFTASSIGN); }
					case -57:
						break;
					case 57:
						{ return tok(sym.FOR); }
					case -58:
						break;
					case 58:
						{ return tok(sym.FUN); }
					case -59:
						break;
					case 59:
						{ return tok(sym.INT); }
					case -60:
						break;
					case 60:
						{ return tok(sym.VAR); }
					case -61:
						break;
					case 61:
						{
  return tok(sym.CHAR_LITERAL, yytext().charAt(1));
}
					case -62:
						break;
					case 62:
						{ return tok(sym.AUTO); }
					case -63:
						break;
					case 63:
						{ return tok(sym.CHAR); }
					case -64:
						break;
					case 64:
						{ return tok(sym.ELSE); }
					case -65:
						break;
					case 65:
						{ return tok(sym.ENUM); }
					case -66:
						break;
					case 66:
						{ return tok(sym.GOTO); }
					case -67:
						break;
					case 67:
						{ return tok(sym.LONG); }
					case -68:
						break;
					case 68:
						{ return tok(sym.VOID); }
					case -69:
						break;
					case 69:
						{ return tok(sym.BREAK); }
					case -70:
						break;
					case 70:
						{ return tok(sym.CONST); }
					case -71:
						break;
					case 71:
						{ return tok(sym.FLOAT); }
					case -72:
						break;
					case 72:
						{return tok(sym.SHORT);}
					case -73:
						break;
					case 73:
						{ return tok(sym.UNION); }
					case -74:
						break;
					case 74:
						{ return tok(sym.WHILE); }
					case -75:
						break;
					case 75:
						{ return tok(sym.DOUBLE); }
					case -76:
						break;
					case 76:
						{ return tok(sym.EXTERN); }
					case -77:
						break;
					case 77:
						{ return tok(sym.RETURN); }
					case -78:
						break;
					case 78:
						{ return tok(sym.SIZEOF); }
					case -79:
						break;
					case 79:
						{ return tok(sym.STATIC); }
					case -80:
						break;
					case 80:
						{ return tok(sym.STRUCT); }
					case -81:
						break;
					case 81:
						{ return tok(sym.TYPEDEF); }
					case -82:
						break;
					case 82:
						{ return tok(sym.CONTINUE); }
					case -83:
						break;
					case 83:
						{ return tok(sym.REGISTER); }
					case -84:
						break;
					case 84:
						{ return tok(sym.VOLATILE); }
					case -85:
						break;
					case 85:
						{ return tok(sym.CHAR_LITERAL);}
					case -86:
						break;
					case 86:
						{ return tok(sym.DECIMAL_LITERAL); }
					case -87:
						break;
					case 87:
						{
    if(yytext().length() > 1) {
        err("Character literal too long: " + yytext());
        isError = true;
        return tok(sym.error, null);
    } else {
        cb = yytext().charAt(0);
    }
}
					case -88:
						break;
					case 88:
						{ err("Unterminated char literal"); yybegin(YYINITIAL); return tok(sym.error, null); }
					case -89:
						break;
					case 89:
						{ 
    if(isError) {
        yybegin(YYINITIAL);
    } else {
        yybegin(YYINITIAL); 
        return tok(sym.CHAR_LITERAL, cb);
    }
}
					case -90:
						break;
					case 90:
						{ yybegin(ESCAPE_CHAR); }
					case -91:
						break;
					case 91:
						{
    int value = 0;
    int i = 0;
    String raw = yytext();
    //System.out.println(raw);
    for(i = 0; i < 3; i++) {
        char curChar = raw.charAt(i);
        int digit;
        if (curChar >= '0' && curChar <= '7') {
            value = value * 8 + (curChar - '0');
        }
    }
    cb = (char) value;
    yybegin(CHAR); // go back to string state
}
					case -92:
						break;
					case 92:
						{ 
    err("Invalid ESCAPE sequence: \\" + yytext()); 
    isError = true;
    yybegin(CHAR); 
    return tok(sym.error, null);
}
					case -93:
						break;
					case 93:
						{ cb = '"'; yybegin(CHAR);}
					case -94:
						break;
					case 94:
						{ cb = '\''; yybegin(CHAR);}
					case -95:
						break;
					case 95:
						{ yybegin(HEX_CHAR); }
					case -96:
						break;
					case 96:
						{ cb = '\\'; yybegin(CHAR);}
					case -97:
						break;
					case 97:
						{ cb = '\u0007'; yybegin(CHAR);}
					case -98:
						break;
					case 98:
						{ cb = '\b'; yybegin(CHAR);}
					case -99:
						break;
					case 99:
						{ cb = '\f'; yybegin(CHAR);}
					case -100:
						break;
					case 100:
						{ cb = '\n'; yybegin(CHAR); }
					case -101:
						break;
					case 101:
						{ cb = '\r'; yybegin(CHAR);}
					case -102:
						break;
					case 102:
						{ cb = '\t'; yybegin(CHAR);}
					case -103:
						break;
					case 103:
						{ cb = '\u000B'; yybegin(CHAR);}
					case -104:
						break;
					case 104:
						{
    int value = 0;
    int i = 0;
    String raw = yytext();
    for(i = 0; i < 2; i++) {
        char curChar = raw.charAt(i);
        int digit;
        if (curChar >= '0' && curChar <= '9') {
            digit = curChar - '0';
        } else if (curChar >= 'A' && curChar <= 'F') {
            digit = 10 + (curChar - 'A');
        } else if (curChar >= 'a' && curChar <= 'f') {
            digit = 10 + (curChar - 'a');
        } else {
            err("Invalid hex escape");
            yybegin(CHAR);
            isError = true;
            return tok(sym.error, null);
        }
        value = value * 16 + digit;
    }
    cb = (char) value;
    yybegin(CHAR); // go back to string state
}
					case -105:
						break;
					case 105:
						{ sb.append(yytext()); }
					case -106:
						break;
					case 106:
						{ err("Unterminated string literal"); yybegin(YYINITIAL); return tok(sym.error, null); }
					case -107:
						break;
					case 107:
						{
    if(isError) {
        yybegin(YYINITIAL);
    } else {
        yybegin(YYINITIAL); 
        return tok(sym.STRING_LITERAL, sb.toString()); 
    } 
}
					case -108:
						break;
					case 108:
						{ yybegin(ESCAPE_STRING); }
					case -109:
						break;
					case 109:
						{
    int value = 0;
    int i = 0;
    String raw = yytext();
    //System.out.println(raw);
    for(i = 0; i < 3; i++) {
        char curChar = raw.charAt(i);
        int digit;
        if (curChar >= '0' && curChar <= '7') {
            value = value * 8 + (curChar - '0');
        }
    }
    sb.append((char) value);
    yybegin(STRING); // go back to string state
}
					case -110:
						break;
					case 110:
						{ 
    err("Invalid ESCAPE sequence: \\" + yytext()); 
    isError = true; 
    yybegin(STRING); 
    return tok(sym.error, null);
}
					case -111:
						break;
					case 111:
						{ sb.append('"'); yybegin(STRING); }
					case -112:
						break;
					case 112:
						{ sb.append('\''); yybegin(STRING); }
					case -113:
						break;
					case 113:
						{ yybegin(HEX); }
					case -114:
						break;
					case 114:
						{ sb.append('\\'); yybegin(STRING); }
					case -115:
						break;
					case 115:
						{ sb.append('\u0007'); yybegin(STRING); }
					case -116:
						break;
					case 116:
						{ sb.append('\b'); yybegin(STRING); }
					case -117:
						break;
					case 117:
						{ sb.append('\f'); yybegin(STRING); }
					case -118:
						break;
					case 118:
						{ sb.append('\n'); yybegin(STRING); }
					case -119:
						break;
					case 119:
						{ sb.append('\r'); yybegin(STRING); }
					case -120:
						break;
					case 120:
						{ sb.append('\t'); yybegin(STRING); }
					case -121:
						break;
					case 121:
						{ sb.append('\u000B'); yybegin(STRING); }
					case -122:
						break;
					case 122:
						{
    int value = 0;
    int i = 0;
    String raw = yytext();
    for(i = 0; i < 2; i++) {
        char curChar = raw.charAt(i);
        int digit;
        if (curChar >= '0' && curChar <= '9') {
            digit = curChar - '0';
        } else if (curChar >= 'A' && curChar <= 'F') {
            digit = 10 + (curChar - 'A');
        } else if (curChar >= 'a' && curChar <= 'f') {
            digit = 10 + (curChar - 'a');
        } else {
            err("Invalid hex escape");
            yybegin(STRING);
            isError = true;
            return tok(sym.error, null);
        }
        value = value * 16 + digit;
    }
    sb.append((char) value);
    yybegin(STRING); // go back to string state
}
					case -123:
						break;
					case 123:
						{
    err("Invalid Hexadecimal");
    yybegin(YYINITIAL);
    return tok(sym.error, null);
}
					case -124:
						break;
					case 124:
						{
    err("Invalid Hexadecimal");
    yybegin(YYINITIAL);
    return tok(sym.error, null);
}
					case -125:
						break;
					case 125:
						{
    String hex_temp = yytext();
    int value = 0;
    for(int i = 0; i < hex_temp.length(); i++) {
        int digit;
        char curChar = hex_temp.charAt(i);
        if (curChar >= '0' && curChar <= '9') {
            digit = curChar - '0';
        } else if (curChar >= 'A' && curChar <= 'F') {
            digit = 10 + (curChar - 'A');
        } else if (curChar >= 'a' && curChar <= 'f') {
            digit = 10 + (curChar - 'a');
        } else {
            yybegin(YYINITIAL);
            err("Invalid Hexadecimal");
            return tok(sym.error, null);
        }
        value = value * 16 + digit;
    }
    hexToDec =  value;
    yybegin(YYINITIAL);
    return tok(sym.DECIMAL_LITERAL, hexToDec);   
}
					case -126:
						break;
					case 126:
						{ }
					case -127:
						break;
					case 127:
						{ newline(); }
					case -128:
						break;
					case 128:
						{ yybegin(YYINITIAL); }
					case -129:
						break;
					case 130:
						{ return tok(sym.ID, yytext()); }
					case -130:
						break;
					case 131:
						{ 
    if(yytext().charAt(0) == '0' && yytext().length() > 1) {
        //System.out.println(yytext());
        String oct_temp = yytext();
        int value = 0;
        for(int i = 0; i < oct_temp.length(); i++) {
            int digit;
            char curChar = oct_temp.charAt(i);
            if (curChar >= '0' && curChar <= '7') {
                digit = curChar - '0';
                value = value * 8 + (curChar - '0');
            } else {
                yybegin(YYINITIAL);
                err("Invalid Octal");
                return tok(sym.error, null);
            }
        }
        octToDec =  value;
        yybegin(YYINITIAL);
        return tok(sym.DECIMAL_LITERAL, octToDec);
    } else {
        //System.out.println(yytext());
        String dec_temp = yytext();
        int value = 0;
        for(int i = 0; i < dec_temp.length(); i++) {
            int digit;
            char curChar = dec_temp.charAt(i);
            if (curChar >= '0' && curChar <= '9') {
            } else {
                yybegin(YYINITIAL);
                err("Invalid Decimal");
                return tok(sym.error, null);
            }
        }
        return tok(sym.DECIMAL_LITERAL, Integer.valueOf(yytext()));
        }
}
					case -131:
						break;
					case 132:
						{
    int value = 0;
    int i = 0;
    String raw = yytext();
    //System.out.println(raw);
    for(i = 0; i < 3; i++) {
        char curChar = raw.charAt(i);
        int digit;
        if (curChar >= '0' && curChar <= '7') {
            value = value * 8 + (curChar - '0');
        }
    }
    cb = (char) value;
    yybegin(CHAR); // go back to string state
}
					case -132:
						break;
					case 133:
						{
    int value = 0;
    int i = 0;
    String raw = yytext();
    //System.out.println(raw);
    for(i = 0; i < 3; i++) {
        char curChar = raw.charAt(i);
        int digit;
        if (curChar >= '0' && curChar <= '7') {
            value = value * 8 + (curChar - '0');
        }
    }
    sb.append((char) value);
    yybegin(STRING); // go back to string state
}
					case -133:
						break;
					case 134:
						{ }
					case -134:
						break;
					case 136:
						{ return tok(sym.ID, yytext()); }
					case -135:
						break;
					case 138:
						{ return tok(sym.ID, yytext()); }
					case -136:
						break;
					case 140:
						{ return tok(sym.ID, yytext()); }
					case -137:
						break;
					case 142:
						{ return tok(sym.ID, yytext()); }
					case -138:
						break;
					case 144:
						{ return tok(sym.ID, yytext()); }
					case -139:
						break;
					case 146:
						{ return tok(sym.ID, yytext()); }
					case -140:
						break;
					case 148:
						{ return tok(sym.ID, yytext()); }
					case -141:
						break;
					case 150:
						{ return tok(sym.ID, yytext()); }
					case -142:
						break;
					case 152:
						{ return tok(sym.ID, yytext()); }
					case -143:
						break;
					case 154:
						{ return tok(sym.ID, yytext()); }
					case -144:
						break;
					case 155:
						{ return tok(sym.ID, yytext()); }
					case -145:
						break;
					case 156:
						{ return tok(sym.ID, yytext()); }
					case -146:
						break;
					case 157:
						{ return tok(sym.ID, yytext()); }
					case -147:
						break;
					case 158:
						{ return tok(sym.ID, yytext()); }
					case -148:
						break;
					case 159:
						{ return tok(sym.ID, yytext()); }
					case -149:
						break;
					case 160:
						{ return tok(sym.ID, yytext()); }
					case -150:
						break;
					case 161:
						{ return tok(sym.ID, yytext()); }
					case -151:
						break;
					case 162:
						{ return tok(sym.ID, yytext()); }
					case -152:
						break;
					case 163:
						{ return tok(sym.ID, yytext()); }
					case -153:
						break;
					case 164:
						{ return tok(sym.ID, yytext()); }
					case -154:
						break;
					case 165:
						{ return tok(sym.ID, yytext()); }
					case -155:
						break;
					case 166:
						{ return tok(sym.ID, yytext()); }
					case -156:
						break;
					case 167:
						{ return tok(sym.ID, yytext()); }
					case -157:
						break;
					case 168:
						{ return tok(sym.ID, yytext()); }
					case -158:
						break;
					case 169:
						{ return tok(sym.ID, yytext()); }
					case -159:
						break;
					case 170:
						{ return tok(sym.ID, yytext()); }
					case -160:
						break;
					case 171:
						{ return tok(sym.ID, yytext()); }
					case -161:
						break;
					case 172:
						{ return tok(sym.ID, yytext()); }
					case -162:
						break;
					case 173:
						{ return tok(sym.ID, yytext()); }
					case -163:
						break;
					case 174:
						{ return tok(sym.ID, yytext()); }
					case -164:
						break;
					case 175:
						{ return tok(sym.ID, yytext()); }
					case -165:
						break;
					case 176:
						{ return tok(sym.ID, yytext()); }
					case -166:
						break;
					case 177:
						{ return tok(sym.ID, yytext()); }
					case -167:
						break;
					case 178:
						{ return tok(sym.ID, yytext()); }
					case -168:
						break;
					case 179:
						{ return tok(sym.ID, yytext()); }
					case -169:
						break;
					case 180:
						{ return tok(sym.ID, yytext()); }
					case -170:
						break;
					case 181:
						{ return tok(sym.ID, yytext()); }
					case -171:
						break;
					case 182:
						{ return tok(sym.ID, yytext()); }
					case -172:
						break;
					case 183:
						{ return tok(sym.ID, yytext()); }
					case -173:
						break;
					case 184:
						{ return tok(sym.ID, yytext()); }
					case -174:
						break;
					case 185:
						{ return tok(sym.ID, yytext()); }
					case -175:
						break;
					case 186:
						{ return tok(sym.ID, yytext()); }
					case -176:
						break;
					case 187:
						{ return tok(sym.ID, yytext()); }
					case -177:
						break;
					case 188:
						{ return tok(sym.ID, yytext()); }
					case -178:
						break;
					case 189:
						{ return tok(sym.ID, yytext()); }
					case -179:
						break;
					case 190:
						{ return tok(sym.ID, yytext()); }
					case -180:
						break;
					case 191:
						{ return tok(sym.ID, yytext()); }
					case -181:
						break;
					case 192:
						{ return tok(sym.ID, yytext()); }
					case -182:
						break;
					case 193:
						{ return tok(sym.ID, yytext()); }
					case -183:
						break;
					case 194:
						{ return tok(sym.ID, yytext()); }
					case -184:
						break;
					case 195:
						{ return tok(sym.ID, yytext()); }
					case -185:
						break;
					case 196:
						{ return tok(sym.ID, yytext()); }
					case -186:
						break;
					case 197:
						{ return tok(sym.ID, yytext()); }
					case -187:
						break;
					case 198:
						{ return tok(sym.ID, yytext()); }
					case -188:
						break;
					case 199:
						{ return tok(sym.ID, yytext()); }
					case -189:
						break;
					case 200:
						{ return tok(sym.ID, yytext()); }
					case -190:
						break;
					case 201:
						{ return tok(sym.ID, yytext()); }
					case -191:
						break;
					case 202:
						{ return tok(sym.ID, yytext()); }
					case -192:
						break;
					case 203:
						{ return tok(sym.ID, yytext()); }
					case -193:
						break;
					case 204:
						{ return tok(sym.ID, yytext()); }
					case -194:
						break;
					case 205:
						{ return tok(sym.ID, yytext()); }
					case -195:
						break;
					case 206:
						{ return tok(sym.ID, yytext()); }
					case -196:
						break;
					case 207:
						{ return tok(sym.ID, yytext()); }
					case -197:
						break;
					case 208:
						{ return tok(sym.ID, yytext()); }
					case -198:
						break;
					case 209:
						{ return tok(sym.ID, yytext()); }
					case -199:
						break;
					case 210:
						{ return tok(sym.ID, yytext()); }
					case -200:
						break;
					case 211:
						{ return tok(sym.ID, yytext()); }
					case -201:
						break;
					case 212:
						{ return tok(sym.ID, yytext()); }
					case -202:
						break;
					case 213:
						{ return tok(sym.ID, yytext()); }
					case -203:
						break;
					case 214:
						{ return tok(sym.ID, yytext()); }
					case -204:
						break;
					case 215:
						{ return tok(sym.ID, yytext()); }
					case -205:
						break;
					case 216:
						{ return tok(sym.ID, yytext()); }
					case -206:
						break;
					case 217:
						{ return tok(sym.ID, yytext()); }
					case -207:
						break;
					case 218:
						{ return tok(sym.ID, yytext()); }
					case -208:
						break;
					case 219:
						{ return tok(sym.ID, yytext()); }
					case -209:
						break;
					case 220:
						{ return tok(sym.ID, yytext()); }
					case -210:
						break;
					case 221:
						{ return tok(sym.ID, yytext()); }
					case -211:
						break;
					case 222:
						{ return tok(sym.ID, yytext()); }
					case -212:
						break;
					case 223:
						{ return tok(sym.ID, yytext()); }
					case -213:
						break;
					case 224:
						{ return tok(sym.ID, yytext()); }
					case -214:
						break;
					case 225:
						{ return tok(sym.ID, yytext()); }
					case -215:
						break;
					case 226:
						{ return tok(sym.ID, yytext()); }
					case -216:
						break;
					case 227:
						{ return tok(sym.ID, yytext()); }
					case -217:
						break;
					case 228:
						{ return tok(sym.ID, yytext()); }
					case -218:
						break;
					case 229:
						{ return tok(sym.ID, yytext()); }
					case -219:
						break;
					case 230:
						{ return tok(sym.ID, yytext()); }
					case -220:
						break;
					case 231:
						{ return tok(sym.ID, yytext()); }
					case -221:
						break;
					case 232:
						{ return tok(sym.ID, yytext()); }
					case -222:
						break;
					case 233:
						{ return tok(sym.ID, yytext()); }
					case -223:
						break;
					case 234:
						{ return tok(sym.ID, yytext()); }
					case -224:
						break;
					case 235:
						{ return tok(sym.ID, yytext()); }
					case -225:
						break;
					case 236:
						{ return tok(sym.ID, yytext()); }
					case -226:
						break;
					case 237:
						{ return tok(sym.ID, yytext()); }
					case -227:
						break;
					case 238:
						{ return tok(sym.ID, yytext()); }
					case -228:
						break;
					case 239:
						{ return tok(sym.ID, yytext()); }
					case -229:
						break;
					case 240:
						{ return tok(sym.ID, yytext()); }
					case -230:
						break;
					case 241:
						{ return tok(sym.ID, yytext()); }
					case -231:
						break;
					case 242:
						{ return tok(sym.ID, yytext()); }
					case -232:
						break;
					case 243:
						{ return tok(sym.ID, yytext()); }
					case -233:
						break;
					case 244:
						{ return tok(sym.ID, yytext()); }
					case -234:
						break;
					case 245:
						{ return tok(sym.ID, yytext()); }
					case -235:
						break;
					case 246:
						{ return tok(sym.ID, yytext()); }
					case -236:
						break;
					case 247:
						{ return tok(sym.ID, yytext()); }
					case -237:
						break;
					case 248:
						{ return tok(sym.ID, yytext()); }
					case -238:
						break;
					case 249:
						{ return tok(sym.ID, yytext()); }
					case -239:
						break;
					case 250:
						{ return tok(sym.ID, yytext()); }
					case -240:
						break;
					case 251:
						{ return tok(sym.ID, yytext()); }
					case -241:
						break;
					case 252:
						{ return tok(sym.ID, yytext()); }
					case -242:
						break;
					case 253:
						{ return tok(sym.ID, yytext()); }
					case -243:
						break;
					case 254:
						{ return tok(sym.ID, yytext()); }
					case -244:
						break;
					case 255:
						{ return tok(sym.ID, yytext()); }
					case -245:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
					}
				}
			}
		}
	}
}
