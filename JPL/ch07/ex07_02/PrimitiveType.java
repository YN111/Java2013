/*
 * byte：
 * byte型の範囲内ならばint型のリテラル形式が利用可能
 * float, double, long型のリテラル形式は利用不可
 * 
 * short：
 * short型の範囲内ならばint型のリテラル形式が利用が可能
 * float, double, long型のリテラル形式は利用不可
 * 
 * int：
 * float, double, long型のリテラル形式は利用不可
 * 
 * long：
 * int型の範囲内ならばint型のリテラル形式が利用可能
 * long型の範囲内ならばlong型のリテラル形式が利用可能
 * float, double型のリテラル形式は利用不可
 * 
 * float:
 * int型の範囲内ならばint型のリテラル形式が利用可能
 * long型の範囲内ならばlong型のリテラル形式が利用可能
 * float型の範囲内ならばfloat型のリテラル形式が利用可能
 * double型のリテラル形式は利用不可
 * 
 * double
 * int型の範囲内ならばint型のリテラル形式が利用可能
 * long型の範囲内ならばlong型のリテラル形式が利用可能
 * float型の範囲内ならばfloat型のリテラル形式が利用可能
 * double型の範囲内ならばdouble型のリテラル形式が利用可能
 * 
 */

public class PrimitiveType {

	// byte
	byte b1 = 0; // OK
	byte b2 = 128; // エラー：byteの範囲外のintリテラルを指定
	byte b3 = 3.0f; // エラー：floatリテラルを指定
	byte b4 = 3.0d; // エラー：doubleリテラルを指定
	byte b5 = 3L; // エラー：longリテラルを指定

	// short
	short s1 = 0; // OK
	short s2 = 32768; // エラー：shortの範囲外のintリテラルを指定
	short s3 = 3.0f; // エラー：floatリテラルを指定
	short s4 = 3.0d; // エラー：doubleリテラルを指定
	short s5 = 3L; // エラー：longリテラルを指定

	// int
	int i1 = 0; // OK
	int i2 = 2147483648; // エラー：intの範囲外のintリテラルを指定
	int i3 = 3.0f; // エラー：floatリテラルを指定
	int i4 = 3.0d; // エラー：doubleリテラルを指定
	int i5 = 3L; // エラー：longリテラルを指定

	// long
	long l1 = 0; // OK
	long l2 = 2147483648; // エラー：intの範囲外のintリテラルを指定
	long l3 = 2147483648L; // OK
	long l4 = 9223372036854775808L; // エラー：longの範囲外のlongリテラルを指定
	long l5 = 3.0f; // エラー：floatリテラルを指定
	long l6 = 3.0d; // エラー：doubleリテラルを指定

	// float
	float f1 = 0; // OK
	float f2 = 2147483648; // エラー：intの範囲外のintリテラルを指定
	float f3 = 9223372036854775807L; // OK
	float f4 = 9223372036854775808L; // エラー：longの範囲外のlongリテラルを指定
	float f5 = 3.0f; // OK
	float f6 = 1.40239846E-46f; // エラー：floatの範囲外のfloatリテラルを指定
	float f7 = 3.0d; // エラー：doubleリテラルを指定

	// double
	double d1 = 0; // OK
	double d2 = 2147483648; // エラー：intの範囲外のintリテラルを指定
	double d3 = 9223372036854775807L; // OK
	double d4 = 9223372036854775808L; // エラー：longの範囲外のlongリテラルを指定
	double d5 = 3.0f; // OK
	double d6 = 1.40239846E-46f; // エラー：floatの範囲外のfloatリテラルを指定
	double d7 = 3.0d; // OK
	double d8 = 4.94065645841246544E-325d; // エラー：doubleの範囲外のdoubleリテラルを指定

}
