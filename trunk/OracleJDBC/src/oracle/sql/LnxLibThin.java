// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc
// Source File Name: LnxLibThin.java

package oracle.sql;

import java.sql.SQLException;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import oracle.core.lmx.CoreException;

// Referenced classes of package oracle.sql:
// NUMBER, LnxLibThinFormat, LnxLib, LxMetaData

class LnxLibThin implements LnxLib {
    private static final byte lnxqone[] = { -63, 2 };
    private static final byte lnxqtwo[] = { -63, 3 };
    private static final int LNXQACOS = 0;
    private static final int LNXQASIN = 1;
    private static final int LNXQATAN = 2;
    private static final int LNXQCOS = 3;
    private static final int LNXQSIN = 4;
    private static final int LNXQTAN = 5;
    private static final int LNXQCSH = 6;
    private static final int LNXQSNH = 7;
    private static final int LNXQTNH = 8;
    private static final int LNXQEXP = 9;
    private static final int LNXM_NUM = 22;
    private static final int LNXDIGS = 20;
    private static final int LNXSGNBT = 128;
    private static final int LNXEXPMX = 127;
    private static final int LNXEXPMN = 0;
    private static final int LNXEXPBS = 64;
    private static final int LNXBASE = 100;
    private static final int LNXMXFMT = 64;
    private static final int LNXMXOUT = 40;
    private static final int LNXDIV_LNXBASE_SQUARED = 10000;
    private static final int MINUB1MAXVAL = 255;
    private static final double ORANUM_FBASE = 100D;
    private final int LNXQNOSGN = 127;
    private final char LNXNFT_COMMA = ',';
    private final int LNXBYTEMASK = 255;
    private final int LNXSHORTMASK = 65535;
    private static byte LnxqFirstDigit[] = { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
    private static byte LnxqNegate[] = { 0, 101, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89,
            88, 87, 86, 85, 84, 83, 82, 81, 80, 79, 78, 77, 76, 75, 74, 73, 72, 71, 70, 69, 68, 67,
            66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48, 47, 46, 45,
            44, 43, 42, 41, 40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23,
            22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
    private static byte LnxqTruncate_P[] = { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 11, 11, 11, 11, 11,
            11, 11, 11, 11, 11, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 31, 31, 31, 31, 31, 31, 31,
            31, 31, 31, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 51, 51, 51, 51, 51, 51, 51, 51, 51,
            51, 61, 61, 61, 61, 61, 61, 61, 61, 61, 61, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 81,
            81, 81, 81, 81, 81, 81, 81, 81, 81, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91 };
    private static byte LnxqTruncate_N[] = { 0, 0, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 21, 21,
            21, 21, 21, 21, 21, 21, 21, 21, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 41, 41, 41, 41,
            41, 41, 41, 41, 41, 41, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 61, 61, 61, 61, 61, 61,
            61, 61, 61, 61, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 81, 81, 81, 81, 81, 81, 81, 81,
            81, 81, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91, 101, 101, 101, 101, 101, 101, 101, 101,
            101, 101 };
    private static byte LnxqRound_P[] = { 0, 1, 1, 1, 1, 1, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
            21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 41, 41,
            41, 41, 41, 41, 41, 41, 41, 41, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 61, 61, 61, 61,
            61, 61, 61, 61, 61, 61, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 81, 81, 81, 81, 81, 81,
            81, 81, 81, 81, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91, 101, 101, 101, 101, 101 };
    private static byte LnxqRound_N[] = { 0, 0, 1, 1, 1, 1, 1, 11, 11, 11, 11, 11, 11, 11, 11, 11,
            11, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 41,
            41, 41, 41, 41, 41, 41, 41, 41, 41, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 61, 61, 61,
            61, 61, 61, 61, 61, 61, 61, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 81, 81, 81, 81, 81,
            81, 81, 81, 81, 81, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91, 101, 101, 101, 101, 101 };
    private static byte LnxqComponents_P[][] = { { 0, 0 }, { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 },
            { 0, 4 }, { 0, 5 }, { 0, 6 }, { 0, 7 }, { 0, 8 }, { 0, 9 }, { 1, 0 }, { 1, 1 },
            { 1, 2 }, { 1, 3 }, { 1, 4 }, { 1, 5 }, { 1, 6 }, { 1, 7 }, { 1, 8 }, { 1, 9 },
            { 2, 0 }, { 2, 1 }, { 2, 2 }, { 2, 3 }, { 2, 4 }, { 2, 5 }, { 2, 6 }, { 2, 7 },
            { 2, 8 }, { 2, 9 }, { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 3 }, { 3, 4 }, { 3, 5 },
            { 3, 6 }, { 3, 7 }, { 3, 8 }, { 3, 9 }, { 4, 0 }, { 4, 1 }, { 4, 2 }, { 4, 3 },
            { 4, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 4, 8 }, { 4, 9 }, { 5, 0 }, { 5, 1 },
            { 5, 2 }, { 5, 3 }, { 5, 4 }, { 5, 5 }, { 5, 6 }, { 5, 7 }, { 5, 8 }, { 5, 9 },
            { 6, 0 }, { 6, 1 }, { 6, 2 }, { 6, 3 }, { 6, 4 }, { 6, 5 }, { 6, 6 }, { 6, 7 },
            { 6, 8 }, { 6, 9 }, { 7, 0 }, { 7, 1 }, { 7, 2 }, { 7, 3 }, { 7, 4 }, { 7, 5 },
            { 7, 6 }, { 7, 7 }, { 7, 8 }, { 7, 9 }, { 8, 0 }, { 8, 1 }, { 8, 2 }, { 8, 3 },
            { 8, 4 }, { 8, 5 }, { 8, 6 }, { 8, 7 }, { 8, 8 }, { 8, 9 }, { 9, 0 }, { 9, 1 },
            { 9, 2 }, { 9, 3 }, { 9, 4 }, { 9, 5 }, { 9, 6 }, { 9, 7 }, { 9, 8 }, { 9, 9 } };
    private static byte LnxqComponents_N[][] = { { 0, 0 }, { 0, 0 }, { 9, 9 }, { 9, 8 }, { 9, 7 },
            { 9, 6 }, { 9, 5 }, { 9, 4 }, { 9, 3 }, { 9, 2 }, { 9, 1 }, { 9, 0 }, { 8, 9 },
            { 8, 8 }, { 8, 7 }, { 8, 6 }, { 8, 5 }, { 8, 4 }, { 8, 3 }, { 8, 2 }, { 8, 1 },
            { 8, 0 }, { 7, 9 }, { 7, 8 }, { 7, 7 }, { 7, 6 }, { 7, 5 }, { 7, 4 }, { 7, 3 },
            { 7, 2 }, { 7, 1 }, { 7, 0 }, { 6, 9 }, { 6, 8 }, { 6, 7 }, { 6, 6 }, { 6, 5 },
            { 6, 4 }, { 6, 3 }, { 6, 2 }, { 6, 1 }, { 6, 0 }, { 5, 9 }, { 5, 8 }, { 5, 7 },
            { 5, 6 }, { 5, 5 }, { 5, 4 }, { 5, 3 }, { 5, 2 }, { 5, 1 }, { 5, 0 }, { 4, 9 },
            { 4, 8 }, { 4, 7 }, { 4, 6 }, { 4, 5 }, { 4, 4 }, { 4, 3 }, { 4, 2 }, { 4, 1 },
            { 4, 0 }, { 3, 9 }, { 3, 8 }, { 3, 7 }, { 3, 6 }, { 3, 5 }, { 3, 4 }, { 3, 3 },
            { 3, 2 }, { 3, 1 }, { 3, 0 }, { 2, 9 }, { 2, 8 }, { 2, 7 }, { 2, 6 }, { 2, 5 },
            { 2, 4 }, { 2, 3 }, { 2, 2 }, { 2, 1 }, { 2, 0 }, { 1, 9 }, { 1, 8 }, { 1, 7 },
            { 1, 6 }, { 1, 5 }, { 1, 4 }, { 1, 3 }, { 1, 2 }, { 1, 1 }, { 1, 0 }, { 0, 9 },
            { 0, 8 }, { 0, 7 }, { 0, 6 }, { 0, 5 }, { 0, 4 }, { 0, 3 }, { 0, 2 }, { 0, 1 },
            { 0, 0 } };
    private static byte LnxqAdd_PPP[][] = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 2, 0 }, { 3, 0 },
            { 4, 0 }, { 5, 0 }, { 6, 0 }, { 7, 0 }, { 8, 0 }, { 9, 0 }, { 10, 0 }, { 11, 0 },
            { 12, 0 }, { 13, 0 }, { 14, 0 }, { 15, 0 }, { 16, 0 }, { 17, 0 }, { 18, 0 }, { 19, 0 },
            { 20, 0 }, { 21, 0 }, { 22, 0 }, { 23, 0 }, { 24, 0 }, { 25, 0 }, { 26, 0 }, { 27, 0 },
            { 28, 0 }, { 29, 0 }, { 30, 0 }, { 31, 0 }, { 32, 0 }, { 33, 0 }, { 34, 0 }, { 35, 0 },
            { 36, 0 }, { 37, 0 }, { 38, 0 }, { 39, 0 }, { 40, 0 }, { 41, 0 }, { 42, 0 }, { 43, 0 },
            { 44, 0 }, { 45, 0 }, { 46, 0 }, { 47, 0 }, { 48, 0 }, { 49, 0 }, { 50, 0 }, { 51, 0 },
            { 52, 0 }, { 53, 0 }, { 54, 0 }, { 55, 0 }, { 56, 0 }, { 57, 0 }, { 58, 0 }, { 59, 0 },
            { 60, 0 }, { 61, 0 }, { 62, 0 }, { 63, 0 }, { 64, 0 }, { 65, 0 }, { 66, 0 }, { 67, 0 },
            { 68, 0 }, { 69, 0 }, { 70, 0 }, { 71, 0 }, { 72, 0 }, { 73, 0 }, { 74, 0 }, { 75, 0 },
            { 76, 0 }, { 77, 0 }, { 78, 0 }, { 79, 0 }, { 80, 0 }, { 81, 0 }, { 82, 0 }, { 83, 0 },
            { 84, 0 }, { 85, 0 }, { 86, 0 }, { 87, 0 }, { 88, 0 }, { 89, 0 }, { 90, 0 }, { 91, 0 },
            { 92, 0 }, { 93, 0 }, { 94, 0 }, { 95, 0 }, { 96, 0 }, { 97, 0 }, { 98, 0 }, { 99, 0 },
            { 100, 0 }, { 1, 1 }, { 2, 1 }, { 3, 1 }, { 4, 1 }, { 5, 1 }, { 6, 1 }, { 7, 1 },
            { 8, 1 }, { 9, 1 }, { 10, 1 }, { 11, 1 }, { 12, 1 }, { 13, 1 }, { 14, 1 }, { 15, 1 },
            { 16, 1 }, { 17, 1 }, { 18, 1 }, { 19, 1 }, { 20, 1 }, { 21, 1 }, { 22, 1 }, { 23, 1 },
            { 24, 1 }, { 25, 1 }, { 26, 1 }, { 27, 1 }, { 28, 1 }, { 29, 1 }, { 30, 1 }, { 31, 1 },
            { 32, 1 }, { 33, 1 }, { 34, 1 }, { 35, 1 }, { 36, 1 }, { 37, 1 }, { 38, 1 }, { 39, 1 },
            { 40, 1 }, { 41, 1 }, { 42, 1 }, { 43, 1 }, { 44, 1 }, { 45, 1 }, { 46, 1 }, { 47, 1 },
            { 48, 1 }, { 49, 1 }, { 50, 1 }, { 51, 1 }, { 52, 1 }, { 53, 1 }, { 54, 1 }, { 55, 1 },
            { 56, 1 }, { 57, 1 }, { 58, 1 }, { 59, 1 }, { 60, 1 }, { 61, 1 }, { 62, 1 }, { 63, 1 },
            { 64, 1 }, { 65, 1 }, { 66, 1 }, { 67, 1 }, { 68, 1 }, { 69, 1 }, { 70, 1 }, { 71, 1 },
            { 72, 1 }, { 73, 1 }, { 74, 1 }, { 75, 1 }, { 76, 1 }, { 77, 1 }, { 78, 1 }, { 79, 1 },
            { 80, 1 }, { 81, 1 }, { 82, 1 }, { 83, 1 }, { 84, 1 }, { 85, 1 }, { 86, 1 }, { 87, 1 },
            { 88, 1 }, { 89, 1 }, { 90, 1 }, { 91, 1 }, { 92, 1 }, { 93, 1 }, { 94, 1 }, { 95, 1 },
            { 96, 1 }, { 97, 1 }, { 98, 1 }, { 99, 1 }, { 100, 1 } };
    private static byte LnxqAdd_NNN[][] = { { 0, 2 }, { 0, 1 }, { 0, 0 }, { 0, 0 }, { 0, 0 },
            { 2, 1 }, { 3, 1 }, { 4, 1 }, { 5, 1 }, { 6, 1 }, { 7, 1 }, { 8, 1 }, { 9, 1 },
            { 10, 1 }, { 11, 1 }, { 12, 1 }, { 13, 1 }, { 14, 1 }, { 15, 1 }, { 16, 1 }, { 17, 1 },
            { 18, 1 }, { 19, 1 }, { 20, 1 }, { 21, 1 }, { 22, 1 }, { 23, 1 }, { 24, 1 }, { 25, 1 },
            { 26, 1 }, { 27, 1 }, { 28, 1 }, { 29, 1 }, { 30, 1 }, { 31, 1 }, { 32, 1 }, { 33, 1 },
            { 34, 1 }, { 35, 1 }, { 36, 1 }, { 37, 1 }, { 38, 1 }, { 39, 1 }, { 40, 1 }, { 41, 1 },
            { 42, 1 }, { 43, 1 }, { 44, 1 }, { 45, 1 }, { 46, 1 }, { 47, 1 }, { 48, 1 }, { 49, 1 },
            { 50, 1 }, { 51, 1 }, { 52, 1 }, { 53, 1 }, { 54, 1 }, { 55, 1 }, { 56, 1 }, { 57, 1 },
            { 58, 1 }, { 59, 1 }, { 60, 1 }, { 61, 1 }, { 62, 1 }, { 63, 1 }, { 64, 1 }, { 65, 1 },
            { 66, 1 }, { 67, 1 }, { 68, 1 }, { 69, 1 }, { 70, 1 }, { 71, 1 }, { 72, 1 }, { 73, 1 },
            { 74, 1 }, { 75, 1 }, { 76, 1 }, { 77, 1 }, { 78, 1 }, { 79, 1 }, { 80, 1 }, { 81, 1 },
            { 82, 1 }, { 83, 1 }, { 84, 1 }, { 85, 1 }, { 86, 1 }, { 87, 1 }, { 88, 1 }, { 89, 1 },
            { 90, 1 }, { 91, 1 }, { 92, 1 }, { 93, 1 }, { 94, 1 }, { 95, 1 }, { 96, 1 }, { 97, 1 },
            { 98, 1 }, { 99, 1 }, { 100, 1 }, { 101, 1 }, { 2, 2 }, { 3, 2 }, { 4, 2 }, { 5, 2 },
            { 6, 2 }, { 7, 2 }, { 8, 2 }, { 9, 2 }, { 10, 2 }, { 11, 2 }, { 12, 2 }, { 13, 2 },
            { 14, 2 }, { 15, 2 }, { 16, 2 }, { 17, 2 }, { 18, 2 }, { 19, 2 }, { 20, 2 }, { 21, 2 },
            { 22, 2 }, { 23, 2 }, { 24, 2 }, { 25, 2 }, { 26, 2 }, { 27, 2 }, { 28, 2 }, { 29, 2 },
            { 30, 2 }, { 31, 2 }, { 32, 2 }, { 33, 2 }, { 34, 2 }, { 35, 2 }, { 36, 2 }, { 37, 2 },
            { 38, 2 }, { 39, 2 }, { 40, 2 }, { 41, 2 }, { 42, 2 }, { 43, 2 }, { 44, 2 }, { 45, 2 },
            { 46, 2 }, { 47, 2 }, { 48, 2 }, { 49, 2 }, { 50, 2 }, { 51, 2 }, { 52, 2 }, { 53, 2 },
            { 54, 2 }, { 55, 2 }, { 56, 2 }, { 57, 2 }, { 58, 2 }, { 59, 2 }, { 60, 2 }, { 61, 2 },
            { 62, 2 }, { 63, 2 }, { 64, 2 }, { 65, 2 }, { 66, 2 }, { 67, 2 }, { 68, 2 }, { 69, 2 },
            { 70, 2 }, { 71, 2 }, { 72, 2 }, { 73, 2 }, { 74, 2 }, { 75, 2 }, { 76, 2 }, { 77, 2 },
            { 78, 2 }, { 79, 2 }, { 80, 2 }, { 81, 2 }, { 82, 2 }, { 83, 2 }, { 84, 2 }, { 85, 2 },
            { 86, 2 }, { 87, 2 }, { 88, 2 }, { 89, 2 }, { 90, 2 }, { 91, 2 }, { 92, 2 }, { 93, 2 },
            { 94, 2 }, { 95, 2 }, { 96, 2 }, { 97, 2 }, { 98, 2 }, { 99, 2 }, { 100, 2 },
            { 101, 2 } };
    private static byte LnxqAdd_PNP[][] = { { 0, 2 }, { 0, 1 }, { 0, 0 }, { 0, 0 }, { 1, 1 },
            { 2, 1 }, { 3, 1 }, { 4, 1 }, { 5, 1 }, { 6, 1 }, { 7, 1 }, { 8, 1 }, { 9, 1 },
            { 10, 1 }, { 11, 1 }, { 12, 1 }, { 13, 1 }, { 14, 1 }, { 15, 1 }, { 16, 1 }, { 17, 1 },
            { 18, 1 }, { 19, 1 }, { 20, 1 }, { 21, 1 }, { 22, 1 }, { 23, 1 }, { 24, 1 }, { 25, 1 },
            { 26, 1 }, { 27, 1 }, { 28, 1 }, { 29, 1 }, { 30, 1 }, { 31, 1 }, { 32, 1 }, { 33, 1 },
            { 34, 1 }, { 35, 1 }, { 36, 1 }, { 37, 1 }, { 38, 1 }, { 39, 1 }, { 40, 1 }, { 41, 1 },
            { 42, 1 }, { 43, 1 }, { 44, 1 }, { 45, 1 }, { 46, 1 }, { 47, 1 }, { 48, 1 }, { 49, 1 },
            { 50, 1 }, { 51, 1 }, { 52, 1 }, { 53, 1 }, { 54, 1 }, { 55, 1 }, { 56, 1 }, { 57, 1 },
            { 58, 1 }, { 59, 1 }, { 60, 1 }, { 61, 1 }, { 62, 1 }, { 63, 1 }, { 64, 1 }, { 65, 1 },
            { 66, 1 }, { 67, 1 }, { 68, 1 }, { 69, 1 }, { 70, 1 }, { 71, 1 }, { 72, 1 }, { 73, 1 },
            { 74, 1 }, { 75, 1 }, { 76, 1 }, { 77, 1 }, { 78, 1 }, { 79, 1 }, { 80, 1 }, { 81, 1 },
            { 82, 1 }, { 83, 1 }, { 84, 1 }, { 85, 1 }, { 86, 1 }, { 87, 1 }, { 88, 1 }, { 89, 1 },
            { 90, 1 }, { 91, 1 }, { 92, 1 }, { 93, 1 }, { 94, 1 }, { 95, 1 }, { 96, 1 }, { 97, 1 },
            { 98, 1 }, { 99, 1 }, { 100, 1 }, { 1, 2 }, { 2, 2 }, { 3, 2 }, { 4, 2 }, { 5, 2 },
            { 6, 2 }, { 7, 2 }, { 8, 2 }, { 9, 2 }, { 10, 2 }, { 11, 2 }, { 12, 2 }, { 13, 2 },
            { 14, 2 }, { 15, 2 }, { 16, 2 }, { 17, 2 }, { 18, 2 }, { 19, 2 }, { 20, 2 }, { 21, 2 },
            { 22, 2 }, { 23, 2 }, { 24, 2 }, { 25, 2 }, { 26, 2 }, { 27, 2 }, { 28, 2 }, { 29, 2 },
            { 30, 2 }, { 31, 2 }, { 32, 2 }, { 33, 2 }, { 34, 2 }, { 35, 2 }, { 36, 2 }, { 37, 2 },
            { 38, 2 }, { 39, 2 }, { 40, 2 }, { 41, 2 }, { 42, 2 }, { 43, 2 }, { 44, 2 }, { 45, 2 },
            { 46, 2 }, { 47, 2 }, { 48, 2 }, { 49, 2 }, { 50, 2 }, { 51, 2 }, { 52, 2 }, { 53, 2 },
            { 54, 2 }, { 55, 2 }, { 56, 2 }, { 57, 2 }, { 58, 2 }, { 59, 2 }, { 60, 2 }, { 61, 2 },
            { 62, 2 }, { 63, 2 }, { 64, 2 }, { 65, 2 }, { 66, 2 }, { 67, 2 }, { 68, 2 }, { 69, 2 },
            { 70, 2 }, { 71, 2 }, { 72, 2 }, { 73, 2 }, { 74, 2 }, { 75, 2 }, { 76, 2 }, { 77, 2 },
            { 78, 2 }, { 79, 2 }, { 80, 2 }, { 81, 2 }, { 82, 2 }, { 83, 2 }, { 84, 2 }, { 85, 2 },
            { 86, 2 }, { 87, 2 }, { 88, 2 }, { 89, 2 }, { 90, 2 }, { 91, 2 }, { 92, 2 }, { 93, 2 },
            { 94, 2 }, { 95, 2 }, { 96, 2 }, { 97, 2 }, { 98, 2 }, { 99, 2 }, { 100, 2 } };
    private static byte LnxqAdd_PNN[][] = { { 0, 0 }, { 0, 1 }, { 0, 0 }, { 2, 0 }, { 3, 0 },
            { 4, 0 }, { 5, 0 }, { 6, 0 }, { 7, 0 }, { 8, 0 }, { 9, 0 }, { 10, 0 }, { 11, 0 },
            { 12, 0 }, { 13, 0 }, { 14, 0 }, { 15, 0 }, { 16, 0 }, { 17, 0 }, { 18, 0 }, { 19, 0 },
            { 20, 0 }, { 21, 0 }, { 22, 0 }, { 23, 0 }, { 24, 0 }, { 25, 0 }, { 26, 0 }, { 27, 0 },
            { 28, 0 }, { 29, 0 }, { 30, 0 }, { 31, 0 }, { 32, 0 }, { 33, 0 }, { 34, 0 }, { 35, 0 },
            { 36, 0 }, { 37, 0 }, { 38, 0 }, { 39, 0 }, { 40, 0 }, { 41, 0 }, { 42, 0 }, { 43, 0 },
            { 44, 0 }, { 45, 0 }, { 46, 0 }, { 47, 0 }, { 48, 0 }, { 49, 0 }, { 50, 0 }, { 51, 0 },
            { 52, 0 }, { 53, 0 }, { 54, 0 }, { 55, 0 }, { 56, 0 }, { 57, 0 }, { 58, 0 }, { 59, 0 },
            { 60, 0 }, { 61, 0 }, { 62, 0 }, { 63, 0 }, { 64, 0 }, { 65, 0 }, { 66, 0 }, { 67, 0 },
            { 68, 0 }, { 69, 0 }, { 70, 0 }, { 71, 0 }, { 72, 0 }, { 73, 0 }, { 74, 0 }, { 75, 0 },
            { 76, 0 }, { 77, 0 }, { 78, 0 }, { 79, 0 }, { 80, 0 }, { 81, 0 }, { 82, 0 }, { 83, 0 },
            { 84, 0 }, { 85, 0 }, { 86, 0 }, { 87, 0 }, { 88, 0 }, { 89, 0 }, { 90, 0 }, { 91, 0 },
            { 92, 0 }, { 93, 0 }, { 94, 0 }, { 95, 0 }, { 96, 0 }, { 97, 0 }, { 98, 0 }, { 99, 0 },
            { 100, 0 }, { 101, 0 }, { 2, 1 }, { 3, 1 }, { 4, 1 }, { 5, 1 }, { 6, 1 }, { 7, 1 },
            { 8, 1 }, { 9, 1 }, { 10, 1 }, { 11, 1 }, { 12, 1 }, { 13, 1 }, { 14, 1 }, { 15, 1 },
            { 16, 1 }, { 17, 1 }, { 18, 1 }, { 19, 1 }, { 20, 1 }, { 21, 1 }, { 22, 1 }, { 23, 1 },
            { 24, 1 }, { 25, 1 }, { 26, 1 }, { 27, 1 }, { 28, 1 }, { 29, 1 }, { 30, 1 }, { 31, 1 },
            { 32, 1 }, { 33, 1 }, { 34, 1 }, { 35, 1 }, { 36, 1 }, { 37, 1 }, { 38, 1 }, { 39, 1 },
            { 40, 1 }, { 41, 1 }, { 42, 1 }, { 43, 1 }, { 44, 1 }, { 45, 1 }, { 46, 1 }, { 47, 1 },
            { 48, 1 }, { 49, 1 }, { 50, 1 }, { 51, 1 }, { 52, 1 }, { 53, 1 }, { 54, 1 }, { 55, 1 },
            { 56, 1 }, { 57, 1 }, { 58, 1 }, { 59, 1 }, { 60, 1 }, { 61, 1 }, { 62, 1 }, { 63, 1 },
            { 64, 1 }, { 65, 1 }, { 66, 1 }, { 67, 1 }, { 68, 1 }, { 69, 1 }, { 70, 1 }, { 71, 1 },
            { 72, 1 }, { 73, 1 }, { 74, 1 }, { 75, 1 }, { 76, 1 }, { 77, 1 }, { 78, 1 }, { 79, 1 },
            { 80, 1 }, { 81, 1 }, { 82, 1 }, { 83, 1 }, { 84, 1 }, { 85, 1 }, { 86, 1 }, { 87, 1 },
            { 88, 1 }, { 89, 1 }, { 90, 1 }, { 91, 1 }, { 92, 1 }, { 93, 1 }, { 94, 1 }, { 95, 1 },
            { 96, 1 }, { 97, 1 }, { 98, 1 }, { 99, 1 }, { 100, 1 }, { 101, 1 } };
    private static byte LnxsubIdentity[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
            38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59,
            60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81,
            82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101 };
    private static byte LnxqDigit_P[][] = { { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
            { 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 }, { 21, 22, 23, 24, 25, 26, 27, 28, 29, 30 },
            { 31, 32, 33, 34, 35, 36, 37, 38, 39, 40 }, { 41, 42, 43, 44, 45, 46, 47, 48, 49, 50 },
            { 51, 52, 53, 54, 55, 56, 57, 58, 59, 60 }, { 61, 62, 63, 64, 65, 66, 67, 68, 69, 70 },
            { 71, 72, 73, 74, 75, 76, 77, 78, 79, 80 }, { 81, 82, 83, 84, 85, 86, 87, 88, 89, 90 },
            { 91, 92, 93, 94, 95, 96, 97, 98, 99, 100 } };
    private static byte LnxqDigit_N[][] = { { 101, 100, 99, 98, 97, 96, 95, 94, 93, 92 },
            { 91, 90, 89, 88, 87, 86, 85, 84, 83, 82 }, { 81, 80, 79, 78, 77, 76, 75, 74, 73, 72 },
            { 71, 70, 69, 68, 67, 66, 65, 64, 63, 62 }, { 61, 60, 59, 58, 57, 56, 55, 54, 53, 52 },
            { 51, 50, 49, 48, 47, 46, 45, 44, 43, 42 }, { 41, 40, 39, 38, 37, 36, 35, 34, 33, 32 },
            { 31, 30, 29, 28, 27, 26, 25, 24, 23, 22 }, { 21, 20, 19, 18, 17, 16, 15, 14, 13, 12 },
            { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 } };
    private static final double powerTable[][] = { { 128D, 1E+256D, 9.9999999999999998E-257D },
            { 64D, 1.0000000000000001E+128D, 1.0000000000000001E-128D },
            { 32D, 1E+064D, 9.9999999999999997E-065D },
            { 16D, 1.0000000000000001E+032D, 1.0000000000000001E-032D },
            { 8D, 10000000000000000D, 9.9999999999999998E-017D }, { 4D, 100000000D, 1E-008D },
            { 2D, 10000D, 0.0001D }, { 1.0D, 100D, 0.01D } };
    private static final double factorTable[][] = { { 15D, 1E+030D, 1.0000000000000001E-030D },
            { 14D, 9.9999999999999996E+027D, 9.9999999999999997E-029D }, { 13D, 1E+026D, 1E-026D },
            { 12D, 9.9999999999999998E+023D, 9.9999999999999992E-025D }, { 11D, 1E+022D, 1E-022D },
            { 10D, 1E+020D, 9.9999999999999995E-021D }, { 9D, 1E+018D, 1.0000000000000001E-018D },
            { 8D, 10000000000000000D, 9.9999999999999998E-017D },
            { 7D, 100000000000000D, 1E-014D }, { 6D, 1000000000000D, 9.9999999999999998E-013D },
            { 5D, 10000000000D, 1E-010D }, { 4D, 100000000D, 1E-008D },
            { 3D, 1000000D, 9.9999999999999995E-007D }, { 2D, 10000D, 0.0001D },
            { 1.0D, 100D, 0.01D }, { 0.0D, 1.0D, 1.0D }, { -1D, 0.01D, 100D },
            { -2D, 0.0001D, 10000D }, { -3D, 9.9999999999999995E-007D, 1000000D },
            { -4D, 1E-008D, 100000000D }, { -5D, 1E-010D, 10000000000D },
            { -6D, 9.9999999999999998E-013D, 1000000000000D }, { -7D, 1E-014D, 100000000000000D },
            { -8D, 9.9999999999999998E-017D, 10000000000000000D },
            { -9D, 1.0000000000000001E-018D, 1E+018D },
            { -10D, 9.9999999999999995E-021D, 1E+020D }, { -11D, 1E-022D, 1E+022D },
            { -12D, 9.9999999999999992E-025D, 9.9999999999999998E+023D },
            { -13D, 1E-026D, 1E+026D },
            { -14D, 9.9999999999999997E-029D, 9.9999999999999996E+027D },
            { -15D, 1.0000000000000001E-030D, 1E+030D },
            { -16D, 1.0000000000000001E-032D, 1.0000000000000001E+032D },
            { -17D, 9.9999999999999993E-035D, 9.9999999999999995E+033D },
            { -18D, 9.9999999999999994E-037D, 1E+036D },
            { -19D, 9.9999999999999996E-039D, 9.9999999999999998E+037D },
            { -20D, 9.9999999999999993E-041D, 1E+040D }, { -21D, 1E-042D, 1E+042D },
            { -22D, 9.9999999999999995E-045D, 1.0000000000000001E+044D },
            { -23D, 1E-046D, 9.9999999999999999E+045D },
            { -24D, 9.9999999999999997E-049D, 1E+048D },
            { -25D, 1E-050D, 1.0000000000000001E+050D },
            { -26D, 1E-052D, 9.9999999999999999E+051D },
            { -27D, 1E-054D, 1.0000000000000001E+054D },
            { -28D, 1E-056D, 1.0000000000000001E+056D },
            { -29D, 1E-058D, 9.9999999999999994E+057D },
            { -30D, 9.9999999999999997E-061D, 9.9999999999999995E+059D },
            { -31D, 1E-062D, 1E+062D }, { -32D, 9.9999999999999997E-065D, 1E+064D },
            { -33D, 9.9999999999999998E-067D, 9.9999999999999995E+065D },
            { -34D, 1.0000000000000001E-068D, 9.9999999999999995E+067D } };
    private char lnx_chars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', ' ',
            '.', ',', '$', '<', '>', '(', ')', '#', '~', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'i',
            'l', 'm', 'p', 'r', 's', 't', 'v', 'A', 'B', 'C', 'D', 'E', 'F', 'I', 'L', 'M', 'P',
            'R', 'S', 'T' };

    public byte[] lnxabs(byte abyte0[]) throws SQLException {
        byte abyte1[] = new byte[abyte0.length];
        if (NUMBER._isPositive(abyte0)) {
            System.arraycopy(abyte0, 0, abyte1, 0, abyte0.length);
            return abyte1;
        }
        if (NUMBER._isNegInf(abyte0)) {
            return NUMBER.posInf().shareBytes();
        }
        int i = abyte0.length;
        if (abyte0[i - 1] == 102) {
            i--;
        }
        System.arraycopy(abyte0, 0, abyte1, 0, i);
        _negateNumber(abyte1);
        return _setLength(abyte1, i);
    }

    public byte[] lnxacos(byte abyte0[]) throws SQLException {
        return lnxqtri(abyte0, 0);
    }

    public byte[] lnxadd(byte abyte0[], byte abyte1[]) throws SQLException {
        int i = abyte0.length;
        int j = 0;
        int k = abyte1.length;
        int l = 0;
        byte abyte2[] = new byte[41];
        int i1 = 0;
        int i2 = 0;
        int j2 = 0;
        int l4 = 0;
        int i5 = 0;
        int j5 = 0;
        int l2 = i1 + 1;
        boolean flag1 = NUMBER._isPositive(abyte0);
        int l5 = abyte0[0] >= 0 ? 255 - abyte0[0] : 256 + abyte0[0];
        if (!flag1 && abyte0[i - 1] == 102) {
            i--;
        }
        int k5 = i - 1;
        boolean flag2 = NUMBER._isPositive(abyte1);
        int j6 = abyte1[0] >= 0 ? 255 - abyte1[0] : 256 + abyte1[0];
        if (!flag2 && abyte1[k - 1] == 102) {
            k--;
        }
        int i6 = k - 1;
        if (l5 == 255 && (k5 == 0 || abyte0[1] == 101)) {
            boolean flag3 = flag1;
            if (flag3) {
                return NUMBER._makePosInf();
            } else {
                return NUMBER._makeNegInf();
            }
        }
        if (j6 == 255 && (i6 == 0 || abyte1[1] == 101)) {
            boolean flag4 = flag2;
            if (flag4) {
                return NUMBER._makePosInf();
            } else {
                return NUMBER._makeNegInf();
            }
        }
        if (l5 == 128 && k5 == 0) {
            abyte2 = new byte[k];
            System.arraycopy(abyte1, 0, abyte2, 0, k);
            boolean flag5 = flag2;
            int j7 = k;
            return _setLength(abyte2, j7);
        }
        if (j6 == 128 && i6 == 0) {
            abyte2 = new byte[i];
            System.arraycopy(abyte0, 0, abyte2, 0, i);
            boolean flag6 = flag1;
            int k7 = i;
            return _setLength(abyte2, k7);
        }
        int k4 = l5 - j6;
        byte abyte3[][];
        boolean flag7;
        int i7;
        byte byte2;
        if (flag1 == flag2) {
            flag7 = flag1;
            if (flag7) {
                abyte3 = LnxqAdd_PPP;
                i7 = 1;
                byte2 = 1;
            } else {
                abyte3 = LnxqAdd_NNN;
                i7 = 101;
                byte2 = -1;
            }
        } else {
            int k9 = k4;
            if (k9 == 0) {
                int i8 = j + 1;
                int l8 = l + 1;
                int j9;
                for (j9 = j + (k5 >= i6 ? i6 : k5); i8 <= j9 && abyte0[i8] + abyte1[l8] == 102; l8++) {
                    i8++;
                }

                if (i8 <= j9) {
                    k9 = flag1 ? (abyte0[i8] + abyte1[l8]) - 102 : 102 - (abyte0[i8] + abyte1[l8]);
                } else {
                    k9 = k5 - i6;
                }
            }
            if (k9 == 0) {
                return NUMBER._makeZero();
            }
            flag7 = k9 <= 0 ? flag2 : flag1;
            if (flag7) {
                abyte3 = LnxqAdd_PNP;
                i7 = 1;
                byte2 = -1;
            } else {
                abyte3 = LnxqAdd_PNN;
                i7 = 101;
                byte2 = 1;
            }
        }
        int l1;
        byte byte0;
        int k2;
        byte byte1;
        boolean flag;
        int k6;
        int l6;
        if (k4 >= 0) {
            k6 = l5;
            if (k4 + i6 <= k5) {
                l4 = k4;
                i5 = i6;
                j5 = k5 - (k4 + i6);
                l1 = j + l4;
                byte0 = 1;
                i2 = l1 + i5;
                j2 = l + i6;
                k2 = j + k5;
                byte1 = 1;
                l6 = k5;
                flag = j5 != 0 && flag1 != flag7;
            } else if (k4 < k5) {
                l4 = k4;
                i5 = k5 - k4;
                j5 = i6 - i5;
                l1 = j + l4;
                byte0 = 1;
                i2 = j + k5;
                j2 = l + i5;
                k2 = l + i6;
                byte1 = 2;
                l6 = k4 + i6;
                flag = flag2 != flag7;
            } else {
                l4 = k5;
                i5 = -(k4 - k5);
                j5 = i6;
                l1 = j + k5;
                byte0 = 1;
                k2 = l + i6;
                byte1 = 2;
                l6 = k4 + i6;
                flag = flag2 != flag7;
            }
        } else {
            k6 = j6;
            k4 = -k4;
            if (k4 + k5 <= i6) {
                l4 = k4;
                i5 = k5;
                j5 = i6 - (k4 + k5);
                l1 = l + l4;
                byte0 = 2;
                i2 = j + k5;
                j2 = l1 + i5;
                k2 = l + i6;
                byte1 = 2;
                l6 = i6;
                flag = j5 != 0 && flag2 != flag7;
            } else if (k4 < i6) {
                l4 = k4;
                i5 = i6 - k4;
                j5 = k5 - i5;
                l1 = l + l4;
                byte0 = 2;
                i2 = j + i5;
                j2 = l + i6;
                k2 = j + k5;
                byte1 = 1;
                l6 = k4 + k5;
                flag = flag1 != flag7;
            } else {
                l4 = i6;
                i5 = -(k4 - i6);
                j5 = k5;
                l1 = l + i6;
                byte0 = 2;
                k2 = j + k5;
                byte1 = 1;
                l6 = k4 + k5;
                flag = flag1 != flag7;
            }
        }
        if (l6 > 20) {
            if (k4 > 20) {
                i5 = 0;
                j5 = 0;
                l6 = l4;
                flag = false;
            } else {
                l2 = 1;
            }
        }
        int i3 = l2 + (l6 - 1);
        int j3 = i3;
        if (j5 != 0) {
            int k3 = j3 - j5;
            if (byte1 == 1) {
                abyte2[j3] = abyte0[k2];
            } else {
                abyte2[j3] = abyte1[k2];
            }
            k2--;
            j3--;
            if (flag) {
                for (; j3 > k3; j3--) {
                    if (byte1 == 1) {
                        abyte2[j3] = (byte) (abyte0[k2] + byte2);
                    } else {
                        abyte2[j3] = (byte) (abyte1[k2] + byte2);
                    }
                    k2--;
                }

            } else {
                for (; j3 > k3; j3--) {
                    if (byte1 == 1) {
                        abyte2[j3] = abyte0[k2];
                    } else {
                        abyte2[j3] = abyte1[k2];
                    }
                    k2--;
                }

            }
        }
        if (i5 > 0) {
            int l3 = j3 - i5;
            int j1 = 0;
            int k1 = flag ? j1 + 1 : j1;
            do {
                k1 = j1 + abyte0[i2] + abyte1[j2] + abyte3[k1][1];
                abyte2[j3] = abyte3[k1][0];
                i2--;
                j2--;
            } while (--j3 > l3);
            flag = (abyte3[k1][1] & 1) != 0;
        } else {
            int j8 = flag ? byte2 != 1 ? 100 : 2 : i7;
            for (int i4 = j3 + i5; j3 > i4; j3--) {
                abyte2[j3] = (byte) j8;
            }

        }
        if (l4 != 0) {
            int j4 = j3 - l4;
            if (flag) {
                int k8 = (byte2 != 1 ? 1 : 100) + (flag7 ? 0 : 1);
                int i9 = (byte2 != 1 ? 100 : 1) + (flag7 ? 0 : 1);
                do {
                    if (byte0 == 1) {
                        flag = abyte0[l1] == k8;
                        abyte2[j3] = (byte) (flag ? i9 : abyte0[l1] + byte2);
                    } else {
                        flag = abyte1[l1] == k8;
                        abyte2[j3] = (byte) (flag ? i9 : abyte1[l1] + byte2);
                    }
                    l1--;
                    j3--;
                } while (flag && j3 > j4);
            }
            for (; j3 > j4; j3--) {
                if (byte0 == 1) {
                    abyte2[j3] = abyte0[l1];
                } else {
                    abyte2[j3] = abyte1[l1];
                }
                l1--;
            }

        }
        if (flag) {
            if (k6 == 255) {
                if (flag7) {
                    return NUMBER._makePosInf();
                } else {
                    return NUMBER._makeNegInf();
                }
            }
            l2--;
            abyte2[l2] = (byte) (flag7 ? 2 : 100);
            k6++;
            l6++;
        }
        if (abyte2[l2] == i7) {
            do {
                l2++;
                k6--;
                l6--;
            } while (abyte2[l2] == i7);
            if (k6 < 128) {
                return NUMBER._makeZero();
            }
        }
        if (l6 > 20) {
            i3 = l2 + 19;
            l6 = 20;
            if ((flag7 ? abyte2[i3 + 1] : LnxqNegate[abyte2[i3 + 1]]) > 50) {
                byte byte3 = ((byte) (flag7 ? 100 : 2));
                if (!flag) {
                    abyte2[l2 - 1] = (byte) i7;
                }
                while (abyte2[i3] == byte3) {
                    i3--;
                    l6--;
                }
                if (i3 < l2) {
                    if (k6 == 255) {
                        if (flag7) {
                            return NUMBER._makePosInf();
                        } else {
                            return NUMBER._makeNegInf();
                        }
                    }
                    l2--;
                    k6++;
                    l6 = 1;
                }
                abyte2[i3] += ((byte) (flag7 ? 1 : -1));
            }
        }
        while (abyte2[i3] == i7) {
            i3--;
            l6--;
        }
        if (l2 != 1) {
            byte abyte4[] = new byte[41];
            System.arraycopy(abyte2, l2, abyte4, 1, l6);
            System.arraycopy(abyte4, 1, abyte2, 1, l6);
        }
        int l7 = l6 + 1;
        if (!flag7 && l7 <= 20) {
            abyte2[l7] = 102;
            l7++;
        }
        abyte2[i1] = (byte) (flag7 ? k6 - 256 : 255 - k6);
        return _setLength(abyte2, l7);
    }

    public byte[] lnxasin(byte abyte0[]) throws SQLException {
        return lnxqtri(abyte0, 1);
    }

    public byte[] lnxatan(byte abyte0[]) throws SQLException {
        return lnxqtri(abyte0, 2);
    }

    public byte[] lnxatan2(byte abyte0[], byte abyte1[]) throws SQLException {
        if (NUMBER._isZero(abyte0) && NUMBER._isZero(abyte1)) {
            throw new SQLException(CoreException.getMessage((byte) 11));
        }
        byte abyte2[] = lnxdiv(abyte0, abyte1);
        abyte2 = lnxatan(abyte2);
        if (NUMBER._isPositive(abyte1)) {
            return abyte2;
        }
        byte abyte3[] = NUMBER.pi().shareBytes();
        if (NUMBER._isPositive(abyte0)) {
            return lnxadd(abyte2, abyte3);
        } else {
            return lnxsub(abyte2, abyte3);
        }
    }

    public byte[] lnxbex(byte abyte0[], byte abyte1[]) throws SQLException {
        switch (lnxsgn(abyte0)) {
        case 1: // '\001'
            byte abyte2[] = lnxln(abyte0);
            abyte2 = lnxmul(abyte1, abyte2);
            return lnxexp(abyte2);

        case 0: // '\0'
            if (NUMBER._isZero(abyte1)) {
                byte abyte3[] = new byte[lnxqone.length];
                System.arraycopy(lnxqone, 0, abyte3, 0, lnxqone.length);
                return abyte3;
            } else {
                return NUMBER._makeZero();
            }

        case -1:
            if ((new NUMBER(abyte1)).isInt()) {
                byte abyte4[] = lnxneg(abyte0);
                abyte4 = lnxln(abyte4);
                abyte4 = lnxmul(abyte1, abyte4);
                abyte4 = lnxexp(abyte4);
                if (!NUMBER._isZero(lnxmod(abyte1, lnxqtwo))) {
                    abyte4 = lnxneg(abyte4);
                }
                return abyte4;
            } else {
                return NUMBER._makePosInf();
            }
        }
        return null;
    }

    public byte[] lnxcos(byte abyte0[]) throws SQLException {
        return lnxqtra(abyte0, 3);
    }

    public byte[] lnxcsh(byte abyte0[]) throws SQLException {
        return lnxqtra(abyte0, 6);
    }

    public byte[] lnxdec(byte abyte0[]) throws SQLException {
        int l = abyte0.length;
        byte abyte1[] = new byte[22];
        System.arraycopy(abyte0, 0, abyte1, 0, l);
        if (NUMBER._isPositive(abyte1)) {
            int i = (byte) ((abyte1[0] & 0xffffff7f) - 65);
            if (i >= 0 && i <= 18) {
                int j = i + 1;
                int k = l - 1;
                if (j <= k) {
                    abyte1[j]--;
                    if (abyte1[j] == 1 && j == k && --l == 1) {
                        return NUMBER._makeZero();
                    }
                } else {
                    abyte1[k]--;
                    for (; j > k; j--) {
                        abyte1[j] = 100;
                    }

                    if (abyte1[1] == 1) {
                        for (int i1 = 1; i1 <= i; i1++) {
                            abyte1[i1] = abyte1[i1 + 1];
                        }

                        i--;
                    }
                    l = i + 2;
                }
                abyte1[0] = (byte) (i + 128 + 64 + 1);
                byte abyte2[] = new byte[l];
                System.arraycopy(abyte1, 0, abyte2, 0, l);
                return abyte2;
            }
        }
        return NUMBER._makeZero();
    }

    public byte[] lnxdiv(byte abyte0[], byte abyte1[]) throws SQLException {
        byte abyte2[] = abyte0;
        int i = abyte2.length;
        byte abyte3[] = abyte1;
        int j = abyte3.length;
        byte abyte4[] = new byte[22];
        int k = abyte4.length;
        int ai[] = new int[22];
        int ai1[] = new int[10];
        byte abyte5[] = new byte[41];
        int ai2[] = new int[13];
        boolean flag = abyte2[0] >> 7 != 0;
        byte byte0 = abyte2[0];
        if (!flag) {
            byte0 = (byte) (~byte0);
            if (abyte2[i - 1] == 102) {
                i--;
            }
        }
        boolean flag1 = abyte3[0] >> 7 != 0;
        byte byte1 = abyte3[0];
        if (!flag1) {
            byte1 = (byte) (~byte1);
            if (abyte3[j - 1] == 102) {
                j--;
            }
        }
        if ((byte1 & 0xff) == 128 && j == 1) {
            if (flag == flag1) {
                return NUMBER._makePosInf();
            } else {
                return NUMBER._makeNegInf();
            }
        }
        if ((byte0 & 0xff) == 128 && i == 1) {
            return NUMBER._makeZero();
        }
        int i6;
        if (i == 1) {
            i6 = 0;
        } else {
            i6 = 1;
        }
        if ((byte0 & 0xff) == 255 && (i == 2 || abyte2[i6] == 101) || i == 1 && abyte2[0] == 0) {
            if (flag == flag1) {
                return NUMBER._makePosInf();
            } else {
                return NUMBER._makeNegInf();
            }
        }
        if (j == 1) {
            i6 = 0;
        } else {
            i6 = 1;
        }
        if ((byte1 & 0xff) == 255 && (j == 2 || abyte3[i6] == 101) || j == 1 && abyte3[0] == 0) {
            return NUMBER._makeZero();
        }
        int j1 = i / 2 - 1;
        int k1 = 21;
        int l = i - 2;
        for (; k1 > j1; k1--) {
            ai[k1] = 0;
        }

        if (flag) {
            if ((i & 1) == 0) {
                ai[k1] = abyte2[l + 1] * 100 - 100;
                l--;
                k1--;
            }
            while (l > 0) {
                ai[k1] = (abyte2[l] * 100 + abyte2[l + 1]) - 101;
                l -= 2;
                k1--;
            }
        } else {
            if ((i & 1) == 0) {
                ai[k1] = 10100 - abyte2[l + 1] * 100;
                l--;
                k1--;
            }
            while (l > 0) {
                ai[k1] = 10201 - (abyte2[l] * 100 + abyte2[l + 1]);
                l -= 2;
                k1--;
            }
        }
        int k2 = j / 2 - 1;
        int l2 = k2;
        int i1 = j - 2;
        if (flag1) {
            if ((j & 1) == 0) {
                ai1[l2] = abyte3[i1 + 1] * 100 - 100;
                i1--;
                l2--;
            }
            while (i1 > 0) {
                ai1[l2] = (abyte3[i1] * 100 + abyte3[i1 + 1]) - 101;
                i1 -= 2;
                l2--;
            }
        } else {
            if ((j & 1) == 0) {
                ai1[l2] = 10100 - abyte3[i1 + 1] * 100;
                i1--;
                l2--;
            }
            while (i1 > 0) {
                ai1[l2] = 10201 - (abyte3[i1] * 100 + abyte3[i1 + 1]);
                i1 -= 2;
                l2--;
            }
        }
        int j4 = 0;
        int k4 = -1;
        if (j <= 3) {
            int l1 = 0;
            int j6 = ai[0];
            int i7 = ai1[0];
            do {
                int k7 = j6 / i7;
                l1++;
                j6 -= k7 * i7;
                j6 = j6 * 10000 + ai[l1];
                k4++;
                ai2[k4] = k7;
            } while ((j6 != 0 || l1 < j1) && k4 < 10 + (ai2[0] != 0 ? 1 : 2));
        } else {
            int i8 = 0;
            int j8 = k2;
            double d = (double) (ai[i8] * 10000) + (double) ai[i8 + 1];
            double d1 = (double) (ai1[0] * 10000) + (double) ai1[1];
            do {
                int l7 = (int) (d / d1);
                if (l7 != 0) {
                    int i2 = i8 + 2;
                    for (int i3 = 2; i2 <= j8; i3++) {
                        ai[i2] -= l7 * ai1[i3];
                        i2++;
                    }

                }
                d -= (double) l7 * d1;
                d = d * 10000D + (double) ai[i8 + 2];
                if (l7 >= 10000) {
                    int l4;
                    for (l4 = k4; ai2[l4] == 9999; l4--) {
                        ai2[l4] = 0;
                    }

                    ai2[l4]++;
                    l7 -= 10000;
                }
                if (l7 < 0) {
                    int i5;
                    for (i5 = k4; ai2[i5] == 0; i5--) {
                        ai2[i5] = 9999;
                    }

                    ai2[i5]--;
                    l7 += 10000;
                }
                k4++;
                ai2[k4] = l7;
                if (i8 >= j1 && (d >= 0.0D ? d : -d) < 0.10000000000000001D) {
                    int j2;
                    for (j2 = i8 + 2; j2 <= j8 && ai[j2] == 0; j2++) {
                    }
                    if (j2 > j8) {
                        break;
                    }
                }
                i8++;
                j8++;
            } while (k4 < 10 + (ai2[0] != 0 ? 1 : 2));
        }
        if (ai2[0] == 0) {
            j4++;
        }
        for (; ai2[k4] == 0; k4--) {
        }
        int k5 = ai2[j4] < 100 ? 0 : 1;
        int l5 = ai2[k4] % 100 == 0 ? 0 : 1;
        int l3 = 2 * (k4 - j4) + k5 + l5;
        if (l3 > 20) {
            if (k5 > 0) {
                k4 = j4 + 9;
                ai2[k4] += ai2[k4 + 1] < 5000 ? 0 : 1;
            } else {
                k4 = j4 + 10;
                ai2[k4] = ((ai2[k4] + 50) / 100) * 100;
            }
            if (ai2[k4] == 10000) {
                do {
                    k4--;
                } while (ai2[k4] == 9999);
                ai2[k4]++;
            }
            if (ai2[0] != 0) {
                j4 = 0;
            }
            for (; ai2[k4] == 0; k4--) {
            }
            k5 = ai2[j4] < 100 ? 0 : 1;
            l5 = ai2[k4] % 100 == 0 ? 0 : 1;
            l3 = 2 * (k4 - j4) + k5 + l5;
        }
        int j3 = ((byte0 & 0xff) - (byte1 & 0xff) - (ai2[0] != 0 ? 0 : 1)) + 193;
        if (j3 > 255) {
            if (flag == flag1) {
                return NUMBER._makePosInf();
            } else {
                return NUMBER._makeNegInf();
            }
        }
        if (j3 < 128) {
            return NUMBER._makeZero();
        }
        int k3 = l3 + 1;
        abyte4 = new byte[k3];
        int i4 = l3;
        int j5 = k4;
        if (l5 == 0) {
            abyte4[i4] = (byte) (ai2[j5] / 100 + 1);
            i4--;
            j5--;
        }
        while (i4 > 1) {
            int k6 = ai2[j5] / 100;
            int j7 = ai2[j5] - k6 * 100;
            abyte4[i4] = (byte) (j7 + 1);
            i4--;
            abyte4[i4] = (byte) (k6 + 1);
            i4--;
            j5--;
        }
        if (k5 == 0) {
            abyte4[i4] = (byte) (ai2[j5] + 1);
        }
        abyte4[0] = (byte) j3;
        if (flag != flag1) {
            byte abyte6[];
            if (++k3 > 20) {
                abyte6 = new byte[21];
                k3 = 21;
            } else {
                abyte6 = new byte[k3];
            }
            abyte6[0] = (byte) (~j3);
            int l6;
            for (l6 = 0; l6 < k3 - 2; l6++) {
                abyte6[l6 + 1] = (byte) (102 - abyte4[l6 + 1]);
            }

            if (k3 <= 20) {
                abyte6[k3 - 1] = 102;
            } else if (abyte4.length == 20) {
                abyte6[k3 - 1] = 102;
            } else {
                abyte6[l6 + 1] = (byte) (102 - abyte4[l6 + 1]);
            }
            return abyte6;
        } else {
            return abyte4;
        }
    }

    public byte[] lnxexp(byte abyte0[]) throws SQLException {
        return lnxqtra(abyte0, 9);
    }

    public byte[] lnxflo(byte abyte0[]) throws SQLException {
        byte abyte1[] = lnxtru(abyte0, 0);
        if (NUMBER.compareBytes(abyte1, abyte0) != 0 && !NUMBER._isPositive(abyte0)) {
            abyte1 = lnxsub(abyte1, lnxqone);
        }
        return abyte1;
    }

    public byte[] lnxceil(byte abyte0[]) throws SQLException {
        byte abyte1[] = lnxtru(abyte0, 0);
        if (NUMBER.compareBytes(abyte1, abyte0) != 0 && NUMBER._isPositive(abyte0)) {
            abyte1 = lnxadd(abyte1, lnxqone);
        }
        return abyte1;
    }

    public byte[] lnxfpr(byte abyte0[], int i) throws SQLException {
        int k = abyte0.length;
        if (NUMBER._isZero(abyte0)) {
            return NUMBER._makeZero();
        }
        if (NUMBER._isNegInf(abyte0)) {
            return NUMBER._makeNegInf();
        }
        if (NUMBER._isPosInf(abyte0)) {
            return NUMBER._makePosInf();
        }
        if (i < 0) {
            return NUMBER._makeZero();
        }
        boolean flag;
        int j;
        boolean flag1;
        byte byte2;
        byte byte3;
        byte byte4;
        if (flag = NUMBER._isPositive(abyte0)) {
            i += (abyte0[1] & 0xff) >= 11 ? 1 : 2;
            j = i >> 1;
            flag1 = (i & 1) == 1;
            byte2 = 1;
            byte3 = 100;
            byte4 = 1;
        } else {
            i += (abyte0[1] & 0xff) <= 91 ? 1 : 2;
            j = i >> 1;
            flag1 = (i & 1) == 1;
            byte2 = 101;
            byte3 = 2;
            byte4 = -1;
            k -= (abyte0[k - 1] & 0xff) != 102 ? 0 : 1;
        }
        byte abyte1[] = new byte[k];
        System.arraycopy(abyte0, 0, abyte1, 0, k);
        if (j > k - 1 || j == k - 1 && (flag1 || LnxqFirstDigit[abyte0[j]] == 1)) {
            return _setLength(abyte0, k);
        }
        if (j == 0 && (!flag1 || (flag ? abyte0[1] < 51 : abyte0[1] > 51)) || j == 1 && !flag1
                && (flag ? abyte0[1] < 6 : abyte0[1] > 96)) {
            return NUMBER._makeZero();
        }
        if (j == 0) {
            if (NUMBER._isInf(abyte0)) {
                if (flag) {
                    return NUMBER._makePosInf();
                } else {
                    return NUMBER._makeNegInf();
                }
            } else {
                abyte1[0] = (byte) (abyte0[0] + byte4);
                abyte1[1] = (byte) (byte2 + byte4);
                return _setLength(abyte1, 2);
            }
        }
        byte byte1;
        byte byte0 = byte1 = (byte) j;
        if (flag1) {
            if (flag ? abyte0[byte1 + 1] > 50 : abyte0[byte1 + 1] < 52) {
                abyte1[byte0] = (byte) (abyte0[byte1] + byte4);
            } else {
                abyte1[byte0] = abyte0[byte1];
            }
        } else {
            abyte1[byte0] = flag ? LnxqRound_P[abyte0[byte1]] : LnxqRound_N[abyte0[byte1]];
        }
        byte1--;
        int l;
        if (abyte1[byte0] == byte3 + byte4) {
            for (; byte1 > 0 && abyte0[byte1] == byte3; byte1--) {
            }
            if (byte1 == 0) {
                if (NUMBER._isInf(abyte0)) {
                    if (flag) {
                        return NUMBER._makePosInf();
                    } else {
                        return NUMBER._makeNegInf();
                    }
                } else {
                    abyte1[0] = (byte) (abyte0[0] + byte4);
                    abyte1[1] = (byte) (byte2 + byte4);
                    return _setLength(abyte1, 2);
                }
            }
            abyte1[byte1] = (byte) (abyte0[byte1] + byte4);
            l = byte1 + 1;
            byte1--;
        } else if (abyte1[byte0] == byte2) {
            for (; abyte0[byte1] == byte2; byte1--) {
            }
            l = byte1 + 1;
        } else {
            l = j + 1;
        }
        return _setLength(abyte1, l);
    }

    public byte[] lnxinc(byte abyte0[]) throws SQLException {
        int k = abyte0.length;
        byte abyte1[] = new byte[22];
        System.arraycopy(abyte0, 0, abyte1, 0, k);
        int i = 0;
        int j = (byte) ((abyte1[0] & 0xffffff7f) - 65);
        if (j >= 0 && j <= 18) {
            byte byte0 = (byte) (j + 1);
            byte byte1 = (byte) (k - 1);
            if (byte0 <= byte1) {
                if (abyte1[byte0] < 100) {
                    abyte1[byte0]++;
                } else {
                    abyte1[i] = 0;
                    do {
                        byte0--;
                    } while (abyte1[byte0] == 100);
                    if (byte0 > i) {
                        abyte1[byte0]++;
                    } else {
                        j++;
                        byte0++;
                        abyte1[byte0] = 2;
                    }
                    abyte1[i] = (byte) (j + 128 + 64 + 1);
                    k = (byte0 - i) + 1;
                }
            } else {
                abyte1[byte0] = 2;
                for (byte0--; byte0 > byte1; byte0--) {
                    abyte1[byte0] = 1;
                }

                k = j + 2;
            }
        } else {
            abyte1[0] = -63;
            abyte1[1] = 2;
            k = 2;
        }
        byte abyte2[] = new byte[k];
        System.arraycopy(abyte1, 0, abyte2, 0, k);
        return abyte2;
    }

    public byte[] lnxln(byte abyte0[]) throws SQLException {
        if (lnxsgn(abyte0) <= 0) {
            return NUMBER._makeNegInf();
        }
        if (NUMBER._isPosInf(abyte0)) {
            return NUMBER._makePosInf();
        }
        byte abyte1[] = new byte[abyte0.length];
        System.arraycopy(abyte0, 0, abyte1, 0, abyte0.length);
        int i = (abyte1[0] & 0xff) - 193;
        abyte1[0] = -63;
        double d = lnxnur(abyte1);
        double d1 = Math.log(d);
        byte abyte2[] = NUMBER.toBytes(d1);
        byte abyte3[] = lnxexp(abyte2);
        byte abyte4[] = lnxdiv(abyte1, abyte3);
        abyte4 = lnxsub(abyte4, lnxqone);
        byte abyte5[] = new byte[abyte4.length];
        System.arraycopy(abyte4, 0, abyte5, 0, abyte4.length);
        byte abyte6[] = lnxmul(abyte4, abyte4);
        int j = 1;
        for (; (abyte6[0] & 0xff) > 172; abyte6 = lnxmul(abyte4, abyte6)) {
            j++;
            abyte3 = lnxqIDiv(abyte6, j);
            abyte5 = lnxsub(abyte5, abyte3);
            abyte6 = lnxmul(abyte4, abyte6);
            j++;
            abyte3 = lnxqIDiv(abyte6, j);
            abyte5 = lnxadd(abyte5, abyte3);
        }

        i *= 2;
        byte abyte7[] = NUMBER.ln10().shareBytes();
        abyte4 = lnxmin(i);
        abyte3 = lnxmul(abyte4, abyte7);
        abyte3 = lnxadd(abyte3, abyte2);
        return lnxadd(abyte3, abyte5);
    }

    public byte[] lnxlog(byte abyte0[], byte abyte1[]) throws SQLException {
        double d = NUMBER.toDouble(abyte1);
        if (d > 0.0D) {
            if (d == 10D) {
                byte abyte2[] = lnxln(abyte0);
                byte abyte5[] = NUMBER.ln10().shareBytes();
                return lnxdiv(abyte2, abyte5);
            } else {
                byte abyte3[] = lnxln(abyte0);
                byte abyte4[] = lnxln(abyte1);
                return lnxdiv(abyte3, abyte4);
            }
        } else {
            return NUMBER._makeNegInf();
        }
    }

    public byte[] lnxmod(byte abyte0[], byte abyte1[]) throws SQLException {
        byte abyte2[] = lnxdiv(abyte0, abyte1);
        byte abyte3[] = lnxtru(abyte2, 0);
        abyte2 = lnxmul(abyte1, abyte3);
        byte abyte4[] = lnxsub(abyte0, abyte2);
        return abyte4;
    }

    public byte[] lnxmul(byte abyte0[], byte abyte1[]) throws SQLException {
        byte abyte2[] = abyte0;
        int i = abyte2.length;
        byte abyte3[] = abyte1;
        int j = abyte3.length;
        byte abyte4[] = new byte[22];
        int k = abyte4.length;
        int ai[] = new int[10];
        int ai1[] = new int[10];
        short word0 = 0;
        byte abyte5[] = new byte[41];
        int j3 = 0;
        int i4 = 0;
        boolean flag2 = false;
        boolean flag = abyte2[0] >> 7 != 0;
        byte byte0 = abyte2[0];
        if (!flag) {
            byte0 = (byte) (~byte0);
            if (abyte2[i - 1] == 102) {
                i--;
            }
        }
        boolean flag1 = abyte3[0] >> 7 != 0;
        byte byte1 = abyte3[0];
        if (!flag1) {
            byte1 = (byte) (~byte1);
            if (abyte3[j - 1] == 102) {
                j--;
            }
        }
        if (-byte0 == 128 && i == 1) {
            abyte4 = NUMBER._makeZero();
            return abyte4;
        }
        if (-byte1 == 128 && j == 1) {
            abyte4 = NUMBER._makeZero();
            return abyte4;
        }
        if ((byte0 & 0xff) == 255 && (i == 1 || abyte2[1] == 101)) {
            if (flag == flag1) {
                abyte4 = NUMBER._makePosInf();
            } else {
                abyte4 = NUMBER._makeNegInf();
            }
            return abyte4;
        }
        if ((byte1 & 0xff) == 255 && (j == 1 || abyte3[1] == 101)) {
            if (flag == flag1) {
                abyte4 = NUMBER._makePosInf();
            } else {
                abyte4 = NUMBER._makeNegInf();
            }
            return abyte4;
        }
        if (i > j) {
            byte abyte6[] = abyte2;
            abyte2 = abyte3;
            abyte3 = abyte6;
            int l4 = i;
            i = j;
            j = l4;
            boolean flag3 = flag;
            flag = flag1;
            flag1 = flag3;
        }
        int j1 = i / 2 - 1;
        int k1 = j1;
        int l = i - 2;
        if (flag) {
            if ((i & 1) == 0) {
                ai[k1] = abyte2[l + 1] * 100 - 100;
                l--;
                k1--;
            }
            while (l > 0) {
                ai[k1] = (abyte2[l] * 100 + abyte2[l + 1]) - 101;
                l -= 2;
                k1--;
            }
        } else {
            if ((i & 1) == 0) {
                ai[k1] = 10100 - abyte2[l + 1] * 100;
                l--;
                k1--;
            }
            while (l > 0) {
                ai[k1] = 10201 - (abyte2[l] * 100 + abyte2[l + 1]);
                l -= 2;
                k1--;
            }
        }
        int i2 = j / 2 - 1;
        int j2 = i2;
        int i1 = j - 2;
        if (flag1) {
            if ((j & 1) == 0) {
                ai1[j2] = abyte3[i1 + 1] * 100 - 100;
                i1--;
                j2--;
            }
            while (i1 > 0) {
                ai1[j2] = (abyte3[i1] * 100 + abyte3[i1 + 1]) - 101;
                i1 -= 2;
                j2--;
            }
        } else {
            if ((j & 1) == 0) {
                ai1[j2] = 10100 - abyte3[i1 + 1] * 100;
                i1--;
                j2--;
            }
            while (i1 > 0) {
                ai1[j2] = 10201 - (abyte3[i1] * 100 + abyte3[i1 + 1]);
                i1 -= 2;
                j2--;
            }
        }
        int i3;
        if (ai[0] * ai1[0] < 0xf4240) {
            word0 = (short) (((byte0 & 0xff) + (byte1 & 0xff)) - 193);
            i3 = (i & 0xfe) + (j & 0xfe);
        } else {
            word0 = (short) (((byte0 & 0xff) + (byte1 & 0xff)) - 192);
            i3 = (i & 0xfe) + (j & 0xfe) + 1;
        }
        j3 = 1;
        int k3 = i3;
        if (i <= 3) {
            i4 = ai[0] * ai1[i2];
            i4 = LnxmulSetDigit1(abyte5, k3, i4);
            k3 -= 2;
            for (int k2 = i2 - 1; k2 >= 0; k2--) {
                i4 += ai[0] * ai1[k2];
                i4 = LnxmulSetDigit1(abyte5, k3, i4);
                k3 -= 2;
            }

            LnxmulSetDigit2(abyte5, k3, i4);
            k3 -= 2;
        } else {
            i4 += ai[j1] * ai1[i2];
            i4 = LnxmulSetDigit1(abyte5, k3, i4);
            k3 -= 2;
            int l2;
            for (l2 = i2 - 1; l2 > i2 - (i / 2 - 1); l2--) {
                switch ((i2 - l2) + 1) {
                case 9: // '\t'
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 8, i4);
                    // fall through

                case 8: // '\b'
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 7, i4);
                    // fall through

                case 7: // '\007'
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 6, i4);
                    // fall through

                case 6: // '\006'
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 5, i4);
                    // fall through

                case 5: // '\005'
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 4, i4);
                    // fall through

                case 4: // '\004'
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 3, i4);
                    // fall through

                case 3: // '\003'
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 2, i4);
                    // fall through

                case 2: // '\002'
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 1, i4);
                    // fall through

                default:
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 0, i4);
                    break;
                }
                i4 = LnxmulSetDigit1(abyte5, k3, i4);
                k3 -= 2;
            }

            do {
                switch (i / 2) {
                case 10: // '\n'
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 9, i4);
                    // fall through

                case 9: // '\t'
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 8, i4);
                    // fall through

                case 8: // '\b'
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 7, i4);
                    // fall through

                case 7: // '\007'
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 6, i4);
                    // fall through

                case 6: // '\006'
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 5, i4);
                    // fall through

                case 5: // '\005'
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 4, i4);
                    // fall through

                case 4: // '\004'
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 3, i4);
                    // fall through

                case 3: // '\003'
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 2, i4);
                    // fall through

                case 2: // '\002'
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 1, i4);
                    // fall through

                default:
                    i4 = LnxmulSetSum(ai, ai1, j1, l2, 0, i4);
                    break;
                }
                i4 = LnxmulSetDigit1(abyte5, k3, i4);
                k3 -= 2;
            } while (--l2 >= 0);
            for (int l1 = j1 - 1; l1 > 0; l1--) {
                switch (l1 + 1) {
                case 9: // '\t'
                    i4 = LnxmulSetSum(ai, ai1, l1, 0, 8, i4);
                    // fall through

                case 8: // '\b'
                    i4 = LnxmulSetSum(ai, ai1, l1, 0, 7, i4);
                    // fall through

                case 7: // '\007'
                    i4 = LnxmulSetSum(ai, ai1, l1, 0, 6, i4);
                    // fall through

                case 6: // '\006'
                    i4 = LnxmulSetSum(ai, ai1, l1, 0, 5, i4);
                    // fall through

                case 5: // '\005'
                    i4 = LnxmulSetSum(ai, ai1, l1, 0, 4, i4);
                    // fall through

                case 4: // '\004'
                    i4 = LnxmulSetSum(ai, ai1, l1, 0, 3, i4);
                    // fall through

                case 3: // '\003'
                    i4 = LnxmulSetSum(ai, ai1, l1, 0, 2, i4);
                    // fall through

                case 2: // '\002'
                    i4 = LnxmulSetSum(ai, ai1, l1, 0, 1, i4);
                    // fall through

                default:
                    i4 = LnxmulSetSum(ai, ai1, l1, 0, 0, i4);
                    break;
                }
                i4 = LnxmulSetDigit1(abyte5, k3, i4);
                k3 -= 2;
            }

            i4 += ai[0] * ai1[0];
            i4 = LnxmulSetDigit1(abyte5, k3, i4);
            k3 -= 2;
            LnxmulSetDigit2(abyte5, k3, i4);
            k3 -= 2;
        }
        if ((i3 & 1) == 0 && abyte5[k3] != 1) {
            word0++;
            i3++;
            j3--;
        }
        for (; abyte5[(j3 + i3) - 2] == 1; i3--) {
        }
        if (i3 > 21) {
            int l3 = j3 + 19;
            i3 = 21;
            if (abyte5[l3 + 1] > 50) {
                while (abyte5[l3] == 100) {
                    l3--;
                    i3--;
                }
                if (l3 < j3) {
                    abyte5[j3] = 2;
                    word0++;
                    i3++;
                }
                abyte5[l3]++;
            } else {
                for (; abyte5[(j3 + i3) - 2] == 1; i3--) {
                }
            }
        }
        if ((word0 & 0xffff) > 255) {
            if (flag == flag1) {
                return NUMBER._makePosInf();
            } else {
                return NUMBER._makeNegInf();
            }
        }
        if ((word0 & 0xffff) < 128) {
            return NUMBER._makeZero();
        }
        if (flag != flag1) {
            abyte4 = new byte[++i3];
            abyte4[0] = (byte) (~word0);
            for (int j4 = 0; j4 < i3 - 1; j4++) {
                abyte4[j4 + 1] = (byte) (102 - abyte5[j3 + j4]);
            }

            abyte4[i3 - 1] = 102;
        } else {
            abyte4 = new byte[i3];
            abyte4[0] = (byte) word0;
            for (int k4 = 0; k4 < i3 - 1; k4++) {
                abyte4[k4 + 1] = abyte5[j3 + k4];
            }

        }
        return abyte4;
    }

    private static int LnxmulSetSum(int ai[], int ai1[], int i, int j, int k, int l) {
        return l + ai[i - k] * ai1[j + k];
    }

    private static int LnxmulSetDigit1(byte abyte0[], int i, int j) {
        int k = j / 100;
        int l = j / 10000;
        i -= 2;
        abyte0[i + 1] = (byte) ((j - k * 100) + 1);
        abyte0[i] = (byte) ((k - l * 100) + 1);
        return l;
    }

    private static void LnxmulSetDigit2(byte abyte0[], int i, int j) {
        int k = j / 100;
        i -= 2;
        abyte0[i] = (byte) (k + 1);
        abyte0[i + 1] = (byte) ((j - k * 100) + 1);
    }

    public byte[] lnxneg(byte abyte0[]) throws SQLException {
        if (NUMBER._isZero(abyte0)) {
            return NUMBER._makeZero();
        }
        if (NUMBER._isPosInf(abyte0)) {
            return NUMBER._makeNegInf();
        }
        if (NUMBER._isNegInf(abyte0)) {
            return NUMBER._makePosInf();
        }
        int i = abyte0.length;
        if (!NUMBER._isPositive(abyte0) && abyte0[i - 1] == 102) {
            i--;
        }
        byte abyte1[] = new byte[i];
        System.arraycopy(abyte0, 0, abyte1, 0, i);
        _negateNumber(abyte1);
        return _setLength(abyte1, i);
    }

    public byte[] lnxpow(byte abyte0[], int i) throws SQLException {
        byte abyte1[];
        if (i >= 0) {
            abyte1 = new byte[abyte0.length];
            System.arraycopy(abyte0, 0, abyte1, 0, abyte0.length);
        } else {
            int j = 0x80000000;
            if (i == j) {
                abyte1 = lnxpow(abyte0, j + 1);
                return lnxdiv(abyte1, abyte0);
            }
            i = -i;
            abyte1 = lnxdiv(lnxqone, abyte0);
        }
        byte abyte2[] = lnxqone;
        do {
            if (i <= 0) {
                break;
            }
            if ((i & 1) == 1) {
                abyte2 = lnxmul(abyte2, abyte1);
            }
            if ((i >>= 1) > 0) {
                abyte1 = lnxmul(abyte1, abyte1);
            }
        } while (true);
        return abyte2;
    }

    public byte[] lnxrou(byte abyte0[], int i) throws SQLException {
        int j = abyte0.length;
        int k = 0;
        if (j == 1) {
            if (abyte0[k] == -128) {
                return NUMBER._makeZero();
            } else {
                return NUMBER._makeNegInf();
            }
        }
        if (j == 2 && abyte0[0] == -1 && abyte0[1] == 101) {
            return NUMBER._makePosInf();
        }
        int j1 = abyte0[0] >= 0 ? ((int) (abyte0[0])) : 256 + abyte0[0];
        boolean flag;
        byte byte1;
        byte byte2;
        byte byte3;
        int l;
        boolean flag1;
        if (flag = NUMBER._isPositive(abyte0)) {
            if (i >= 0) {
                l = (j1 + (i + 1 >> 1)) - 192;
                flag1 = (i & 1) != 0;
            } else {
                i = -i;
                l = j1 - (i >> 1) - 192;
                flag1 = (i & 1) != 0;
            }
            byte1 = 1;
            byte2 = 100;
            byte3 = 1;
        } else {
            if (i >= 0) {
                l = (63 + (i + 1 >> 1)) - j1;
                flag1 = (i & 1) != 0;
            } else {
                i = -i;
                l = 63 - (i >> 1) - j1;
                flag1 = (i & 1) != 0;
            }
            byte1 = 101;
            byte2 = 2;
            byte3 = -1;
            j -= abyte0[j - 1] != 102 ? 0 : 1;
        }
        byte abyte1[] = new byte[j];
        System.arraycopy(abyte0, 0, abyte1, 0, j);
        if (l > j - 1 || l == j - 1 && (!flag1 || LnxqFirstDigit[abyte0[l]] == 1)) {
            return _setLength(abyte0, j);
        }
        if (l < 0 || l == 0 && (flag1 || (flag ? abyte0[1] < 51 : abyte0[1] > 51)) || l == 1
                && flag1 && (flag ? abyte0[1] < 6 : abyte0[1] > 96)) {
            return NUMBER._makeZero();
        }
        if (l == 0) {
            if (flag ? abyte0[k] == -1 : abyte0[k] == 0) {
                if (flag) {
                    return NUMBER._makePosInf();
                } else {
                    return NUMBER._makeNegInf();
                }
            } else {
                abyte1[0] = (byte) (abyte0[k] + byte3);
                abyte1[1] = (byte) (byte1 + byte3);
                return _setLength(abyte1, 2);
            }
        }
        byte byte4;
        byte byte0 = byte4 = (byte) l;
        if (flag1) {
            abyte1[byte4] = flag ? LnxqRound_P[abyte0[byte0]] : LnxqRound_N[abyte0[byte0]];
        } else if (flag ? abyte0[byte0 + 1] > 50 : abyte0[byte0 + 1] < 52) {
            abyte1[byte4] = (byte) (abyte0[byte0] + byte3);
        } else {
            abyte1[byte4] = abyte0[byte0];
        }
        byte0--;
        int i1;
        if (abyte1[byte4] == byte2 + byte3) {
            for (; byte0 > k && abyte0[byte0] == byte2; byte0--) {
            }
            if (byte0 == k) {
                if (flag ? abyte0[k] == -1 : abyte0[k] == 0) {
                    if (flag) {
                        return NUMBER._makePosInf();
                    } else {
                        return NUMBER._makeNegInf();
                    }
                } else {
                    abyte1[0] = (byte) (abyte0[k] + byte3);
                    abyte1[1] = (byte) (byte1 + byte3);
                    return _setLength(abyte1, 2);
                }
            }
            abyte1[byte0 - k] = (byte) (abyte0[byte0] + byte3);
            i1 = (byte0 - k) + 1;
            byte0--;
        } else if (abyte1[byte4] == byte1) {
            for (; abyte0[byte0] == byte1; byte0--) {
            }
            i1 = (byte0 - k) + 1;
        } else {
            i1 = l + 1;
        }
        return _setLength(abyte1, i1);
    }

    public byte[] lnxsca(byte abyte0[], int i, int j, boolean aflag[]) throws SQLException {
        int k = abyte0.length;
        if (k != 1) {
            byte byte0;
            byte byte1;
            byte byte2;
            if (NUMBER._isPositive(abyte0)) {
                byte0 = (byte) ((abyte0[0] & 0xffffff7f) - 65);
                byte1 = abyte0[1];
                byte2 = abyte0[k - 1];
            } else {
                k--;
                byte0 = (byte) ((~abyte0[0] & 0xffffff7f) - 65);
                byte1 = LnxqNegate[abyte0[1]];
                byte2 = LnxqNegate[abyte0[k - 1]];
            }
            if (2 * (k - byte0) - (byte2 % 10 != 1 ? 0 : 1) > j) {
                byte abyte1[] = lnxrou(abyte0, j);
                if (NUMBER._isPositive(abyte1)) {
                    byte0 = (byte) ((abyte1[0] & 0xffffff7f) - 65);
                    byte1 = abyte1.length == 1 ? 1 : abyte1[1];
                } else {
                    byte0 = (byte) ((~abyte1[0] & 0xffffff7f) - 65);
                    byte1 = LnxqNegate[abyte1[1]];
                }
                aflag[0] = 2 * (byte0 + 1) - (byte1 >= 11 ? 0 : 1) > i;
                return abyte1;
            } else {
                int l = abyte0.length;
                byte abyte2[] = new byte[l];
                System.arraycopy(abyte0, 0, abyte2, 0, l);
                aflag[0] = 2 * (byte0 + 1) - (byte1 >= 11 ? 0 : 1) > i;
                return abyte2;
            }
        } else {
            aflag[0] = false;
            return NUMBER._makeZero();
        }
    }

    public byte[] lnxshift(byte abyte0[], int i) throws SQLException {
        byte abyte1[] = abyte0;
        int j = abyte1.length;
        byte abyte6[] = new byte[22];
        int k = abyte6.length;
        boolean flag = false;
        byte abyte9[] = new byte[22];
        boolean flag3 = false;
        int k2;
        if (j == 1) {
            k2 = 0;
        } else {
            k2 = 1;
        }
        if ((abyte1[0] & 0xff) == 128 && j == 1 || j == 2 && (abyte1[0] & 0xff) == 255
                && abyte1[k2] == 101 || j == 1 && abyte1[0] == 0) {
            byte abyte2[] = new byte[j];
            for (int l2 = 0; l2 < j; l2++) {
                abyte2[l2] = abyte1[l2];
            }

            return abyte2;
        }
        boolean flag1 = abyte1[0] >> 7 == 0;
        int j1 = flag1 ? 255 - abyte1[0] & 0xff : abyte1[0] & 0xff;
        int k1 = j;
        if ((i & 1) > 0) {
            byte abyte7[][];
            byte abyte8[][];
            byte byte0;
            if (flag1) {
                if (abyte1[k1 - 1] == 102) {
                    k1--;
                }
                abyte7 = LnxqComponents_N;
                abyte8 = LnxqDigit_N;
                byte0 = 101;
            } else {
                abyte7 = LnxqComponents_P;
                abyte8 = LnxqDigit_P;
                byte0 = 1;
            }
            if (abyte7[abyte1[1]][0] != 0) {
                j1 = i < 0 ? j1 - -i / 2 : j1 + (i / 2 + 1);
                int l = k1 - 2;
                int l1 = k1 - 1;
                boolean flag2;
                if (k1 > 20) {
                    flag2 = abyte7[abyte1[l + 1]][1] >= 5;
                } else {
                    abyte6[l1 + 1] = abyte8[abyte7[abyte1[l + 1]][1]][0];
                    k1++;
                    flag2 = false;
                }
                while (l > 0) {
                    abyte6[l1] = abyte8[abyte7[abyte1[l + 0]][1]][abyte7[abyte1[l + 1]][0]];
                    l--;
                    l1--;
                }
                abyte6[1] = abyte8[0][abyte7[abyte1[l + 1]][0]];
                if (flag2) {
                    byte byte1 = ((byte) (flag1 ? 2 : 100));
                    byte byte2 = ((byte) (flag1 ? -1 : 1));
                    int i2;
                    for (i2 = 20; abyte6[i2] == byte1;) {
                        i2--;
                        k1--;
                    }

                    abyte6[i2] += byte2;
                }
            } else {
                j1 = i < 0 ? j1 - (-i / 2 + 1) : j1 + i / 2;
                int i1 = 1;
                int j2;
                for (j2 = 1; j2 < k1 - 1; j2++) {
                    abyte6[j2] = abyte8[abyte7[abyte1[i1 + 0]][1]][abyte7[abyte1[i1 + 1]][0]];
                    i1++;
                }

                abyte6[j2] = abyte8[abyte7[abyte1[i1 + 0]][1]][0];
            }
            for (; abyte6[k1 - 1] == byte0; k1--) {
            }
            if (flag1) {
                k1++;
                abyte6[k1 - 1] = 102;
            }
        } else {
            j1 = i < 0 ? j1 - -i / 2 : j1 + i / 2;
            for (int i3 = 1; i3 < k1; i3++) {
                abyte6[i3] = abyte1[i3];
            }

        }
        if (j1 > 255) {
            byte abyte3[];
            if (flag1) {
                abyte3 = new byte[1];
                abyte3[0] = 0;
            } else {
                abyte3 = new byte[2];
                abyte3[0] = -1;
                abyte3[1] = 101;
            }
            return abyte3;
        }
        if (j1 < 128) {
            byte abyte4[] = new byte[1];
            abyte4[0] = -128;
            return abyte4;
        }
        abyte6[0] = flag1 ? (byte) (255 - j1) : (byte) j1;
        byte abyte5[] = new byte[k1];
        for (int j3 = 0; j3 < k1; j3++) {
            abyte5[j3] = abyte6[j3];
        }

        return abyte5;
    }

    public byte[] lnxsin(byte abyte0[]) throws SQLException {
        return lnxqtra(abyte0, 4);
    }

    public byte[] lnxsnh(byte abyte0[]) throws SQLException {
        return lnxqtra(abyte0, 7);
    }

    public byte[] lnxsqr(byte abyte0[]) throws SQLException {
        int i = abyte0.length;
        int ai[] = new int[29];
        int ai1[] = new int[29];
        if (!NUMBER._isPositive(abyte0)) {
            return NUMBER._makeNegInf();
        }
        if (NUMBER._isPosInf(abyte0)) {
            return NUMBER._makePosInf();
        }
        if (NUMBER._isZero(abyte0)) {
            return NUMBER._makeZero();
        }
        int j3 = (abyte0[0] & 0xff) - 193;
        for (int k3 = 1; k3 < i; k3++) {
            ai[k3] = abyte0[k3] - 1;
        }

        int i2 = 1;
        int j2 = i2 + 20 + 3;
        int k;
        if ((j3 + 128 & 1) != 0) {
            k = ((ai[i2] * 100 + ai[i2 + 1]) * 100 + ai[i2 + 2]) * 100 + ai[i2 + 3];
            i2 += 3;
        } else {
            k = (ai[i2] * 100 + ai[i2 + 1]) * 100 + ai[i2 + 2];
            i2 += 2;
        }
        int j = (int) (Math.sqrt(k) * 100D);
        ai1[1] = j / 10000;
        ai1[2] = (j / 100) % 100;
        ai1[3] = j % 100;
        k -= ai1[1] * j;
        k = k * 100 + ai[i2 + 1];
        k -= ai1[2] * j;
        k = k * 100 + ai[i2 + 2];
        k -= ai1[3] * j;
        i2 += 3;
        j *= 2;
        byte byte0 = 3;
        int i1;
        for (i1 = byte0 + 1; i2 < j2; i1++) {
            k = k * 100 + ai[i2];
            int l = k / j;
            k -= l * j;
            ai1[i1] = l;
            int j1 = byte0 + (j2 - i2) >= i1 ? i1 : byte0 + (j2 - i2);
            if (l != 0) {
                int k2 = i2 + 1;
                for (int i3 = byte0 + 1; i3 < j1; i3++) {
                    ai[k2] -= 2 * l * ai1[i3];
                    k2++;
                }

                if (k2 < j2) {
                    ai[k2] -= l * l;
                }
            } else if (k == 0) {
                int l2;
                for (l2 = i2 + 1; l2 < j2 && ai[l2] == 0; l2++) {
                }
                if (l2 == j2) {
                    break;
                }
            }
            i2++;
        }

        int k1 = i1;
        i1--;
        ai1[0] = 0;
        for (; i1 > 0; i1--) {
            for (; ai1[i1] > 99; ai1[i1 - 1]++) {
                ai1[i1] -= 100;
            }

            for (; ai1[i1] < 0; ai1[i1 - 1]--) {
                ai1[i1] += 100;
            }

        }

        j3 = (j3 - (j3 + 128 & 1)) / 2 + 1;
        while (ai1[i1] == 0) {
            i1++;
            if (--j3 < -65) {
                return NUMBER._makeZero();
            }
        }
        do {
            k1--;
        } while (ai1[k1] == 0);
        int l1 = (k1 - i1) + 2;
        if (l1 > 21) {
            k1 = i1 + 20;
            if (ai1[k1] >= 50) {
                do {
                    k1--;
                } while (ai1[k1] == 99);
                ai1[k1]++;
            } else {
                do {
                    k1--;
                } while (ai1[k1] == 0);
            }
            if (k1 < i1) {
                i1 = k1;
                if (++j3 > 62) {
                    return NUMBER._makePosInf();
                }
            }
            l1 = (k1 - i1) + 2;
        }
        byte abyte1[] = new byte[l1];
        abyte1[0] = (byte) (j3 - 63);
        for (int l3 = i1; l3 <= k1; l3++) {
            abyte1[l3 - (i1 - 1)] = (byte) (ai1[l3] + 1);
        }

        return abyte1;
    }

    public byte[] lnxsub(byte abyte0[], byte abyte1[]) throws SQLException {
        return lnxadd(abyte0, lnxneg(abyte1));
    }

    public byte[] lnxtan(byte abyte0[]) throws SQLException {
        return lnxqtra(abyte0, 5);
    }

    public byte[] lnxtnh(byte abyte0[]) throws SQLException {
        return lnxqtra(abyte0, 8);
    }

    public byte[] lnxtru(byte abyte0[], int i) throws SQLException {
        int k = abyte0.length;
        if (NUMBER._isZero(abyte0)) {
            return NUMBER._makeZero();
        }
        if (NUMBER._isNegInf(abyte0)) {
            return NUMBER._makeNegInf();
        }
        if (NUMBER._isPosInf(abyte0)) {
            return NUMBER._makePosInf();
        }
        int i1 = abyte0[0] >= 0 ? ((int) (abyte0[0])) : 256 + abyte0[0];
        boolean flag;
        int j;
        boolean flag1;
        byte byte2;
        if (flag = NUMBER._isPositive(abyte0)) {
            if (i >= 0) {
                j = (i1 + (i + 1 >> 1)) - 192;
                flag1 = (i & 1) == 1;
            } else {
                i = -i;
                j = i1 - (i >> 1) - 192;
                flag1 = (i & 1) == 1;
            }
            byte2 = 1;
        } else {
            if (i >= 0) {
                j = (63 + (i + 1 >> 1)) - i1;
                flag1 = (i & 1) == 1;
            } else {
                i = -i;
                j = 63 - (i >> 1) - i1;
                flag1 = (i & 1) == 1;
            }
            byte2 = 101;
            if (abyte0[k - 1] == 102) {
                k--;
            }
        }
        byte abyte1[] = new byte[k];
        System.arraycopy(abyte0, 0, abyte1, 0, k);
        if (j > k - 1 || j == k - 1 && (flag1 || LnxqFirstDigit[abyte0[j]] == 1)) {
            return _setLength(abyte0, k);
        }
        if (j <= 0 || j == 1 && flag1 && (flag ? abyte0[1] < 11 : abyte0[1] > 91)) {
            return NUMBER._makeZero();
        }
        byte byte1;
        byte byte0 = byte1 = (byte) j;
        if (flag1) {
            if (flag) {
                abyte1[byte0] = LnxqTruncate_P[abyte0[byte1]];
            } else {
                abyte1[byte0] = LnxqTruncate_N[abyte0[byte1]];
            }
        } else {
            abyte1[byte0] = abyte0[byte1];
        }
        byte1--;
        int l;
        if (abyte1[byte0] == byte2) {
            for (; abyte0[byte1] == byte2; byte1--) {
            }
            l = byte1 + 1;
        } else {
            l = j + 1;
        }
        return _setLength(abyte1, l);
    }

    public byte[] lnxcpn(String s, boolean flag, int i, boolean flag1, int j, String s1)
            throws SQLException {
        int i2 = 0;
        int j3 = 0;
        boolean flag2 = false;
        boolean flag3 = false;
        int k3 = 0;
        int l3 = 0;
        int i4 = 0;
        int j4 = 0;
        boolean flag4 = false;
        int l4 = 0;
        boolean flag5 = false;
        boolean flag6 = false;
        boolean flag7 = false;
        boolean flag8 = false;
        boolean flag10 = false;
        Locale locale;
        if (s1 != null) {
            int k6 = s1.indexOf("_");
            if (k6 == -1) {
                locale = LxMetaData.getJavaLocale(s1, "");
            } else {
                String s2 = s1.substring(0, k6);
                String s3 = s1.substring(k6 + 1);
                locale = LxMetaData.getJavaLocale(s2, s3);
            }
            if (locale == null) {
                locale = Locale.getDefault();
            }
        } else {
            locale = Locale.getDefault();
        }
        DecimalFormatSymbols decimalformatsymbols = new DecimalFormatSymbols(locale);
        char c = decimalformatsymbols.getDecimalSeparator();
        char c1 = decimalformatsymbols.getMinusSign();
        char ac[] = s.toCharArray();
        int j1 = 0;
        int k1;
        for (k1 = ac.length - 1; j1 <= k1 && Character.isSpaceChar(ac[j1]); j1++) {
        }
        if (j1 <= k1 && (ac[j1] == c1 || ac[j1] == '+')) {
            flag2 = ac[j1] == c1;
            j1++;
        }
        int k = j1;
        for (; j1 <= k1 && ac[j1] == '0'; j1++) {
        }
        int l1 = j1;
        for (; j1 <= k1 && Character.isDigit(ac[j1]); j1++) {
        }
        k3 = j1 - k;
        l3 = j1 - l1;
        if (j1 <= k1 && ac[j1] == c) {
            i2 = ++j1;
            for (; j1 <= k1 && Character.isDigit(ac[j1]); j1++) {
            }
            i4 = j1 - i2;
            int l = j1;
            while (--j1 >= i2 && ac[j1] == '0')
                ;
            j4 = (j1 - i2) + 1;
            j1 = l;
        }
        if (k3 + i4 != 0) {
            if (j1 <= k1 && (ac[j1] == 'E' || ac[j1] == 'e')) {
                if (++j1 <= k1 && (ac[j1] == c1 || ac[j1] == '+')) {
                    flag3 = ac[j1] == c1;
                    j1++;
                }
                int i1 = j1;
                for (; j1 <= k1 && ac[j1] == '0'; j1++) {
                }
                int j2 = j1;
                for (; j1 <= k1 && Character.isDigit(ac[j1]); j1++) {
                    j3 = j3 * 10 + (ac[j1] - 48);
                }

                int k4 = j1 - i1;
                l4 = j1 - j2;
                if (k4 != 0 && flag3) {
                    j3 = -j3;
                }
            }
            for (; j1 <= k1 && Character.isSpaceChar(ac[j1]); j1++) {
            }
        }
        int i3;
        int k5;
        int l5;
        if (l3 != 0) {
            if (j4 != 0) {
                j3 += l3 - 1;
                k5 = l3;
                l5 = j4;
                i3 = l1;
            } else {
                j3 += l3 - 1;
                for (j1 = l1 + (l3 - 1); ac[j1] == '0'; j1--) {
                }
                k5 = (j1 - l1) + 1;
                l5 = 0;
                i3 = l1;
            }
        } else if (j4 != 0) {
            for (j1 = i2; ac[j1] == '0'; j1++) {
            }
            j3 -= (j1 - i2) + 1;
            k5 = j4 - (j1 - i2);
            l5 = 0;
            i3 = j1;
        } else {
            return NUMBER._makeZero();
        }
        byte byte0 = ((byte) ((j3 & 1) != 1 ? 39 : 40));
        int i5 = k5 + l5;
        int j5;
        if (flag || flag1) {
            if (!flag) {
                i = 0x7fffffff;
            }
            if (!flag1) {
                j = 0;
            }
            j5 = j3 + 1 + j;
        } else {
            j5 = i5;
        }
        j5 = Math.min(j5, byte0);
        if (j5 < 0 || j5 == 0 && ac[i3] < '5') {
            return NUMBER._makeZero();
        }
        boolean flag9 = false;
        if (j5 < i5) {
            j1 = i3 + j5 + (j5 >= k5 ? 1 : 0);
            if (ac[j1] < '5') {
                while (--j1 >= i3 && (ac[j1] == '0' || ac[j1] == c))
                    ;
            } else {
                while (--j1 >= i3 && (ac[j1] == '9' || ac[j1] == c))
                    ;
                flag9 = true;
            }
            if (j1 < i3) {
                ac[1] = '1';
                ac[2] = '0';
                i3 = 1;
                j3++;
                flag5 = false;
                flag7 = (j3 & 1) != 1;
                flag8 = false;
                k5 = (j3 & 1) != 1 ? 0 : 2;
                l5 = 0;
                flag9 = false;
                flag10 = true;
            } else if (l5 != 0) {
                if (j1 - i3 < k5) {
                    k5 = (j1 - i3) + 1;
                    l5 = 0;
                } else {
                    l5 = j1 - i3 - k5;
                }
            } else {
                k5 = (j1 - i3) + 1;
            }
        }
        if (!flag10) {
            if (l5 != 0) {
                flag5 = true;
                flag6 = (j3 & 1) == (k5 & 1);
                flag7 = (j3 & 1) != 1;
                flag8 = flag6 != ((l5 & 1) == 1);
                k5 -= (flag7 ? 1 : 0) + (flag6 ? 1 : 0);
                l5 -= (flag6 ? 1 : 0) + (flag8 ? 1 : 0);
            } else {
                flag5 = false;
                flag7 = (j3 & 1) != 1;
                flag8 = (j3 & 1) == (k5 & 1);
                k5 -= (flag7 ? 1 : 0) + (flag8 ? 1 : 0);
            }
        }
        if (!flag3 && (l4 > 3 || j3 > 125)) {
            if (flag2) {
                return NUMBER._makeNegInf();
            } else {
                return NUMBER._makePosInf();
            }
        }
        if (flag3 && (l4 > 3 || j3 < -130)) {
            return NUMBER._makeZero();
        }
        byte abyte0[] = new byte[22];
        int j6 = 1;
        j1 = i3;
        if (flag7) {
            abyte0[j6] = digitPtr(0, lnxqctn(ac[j1]), flag2);
            j6++;
            j1++;
        }
        if (k5 != 0) {
            for (int k2 = j1 + k5; j1 < k2; j1 += 2) {
                abyte0[j6] = digitPtr(lnxqctn(ac[j1]), lnxqctn(ac[j1 + 1]), flag2);
                j6++;
            }

        }
        if (flag5) {
            if (flag6) {
                abyte0[j6] = digitPtr(lnxqctn(ac[j1]), lnxqctn(ac[j1 + 2]), flag2);
                j6++;
                j1 += 3;
            } else {
                j1++;
            }
        }
        if (l5 != 0) {
            for (int l2 = j1 + l5; j1 < l2; j1 += 2) {
                abyte0[j6] = digitPtr(lnxqctn(ac[j1]), lnxqctn(ac[j1 + 1]), flag2);
                j6++;
            }

        }
        if (flag8) {
            abyte0[j6] = digitPtr(lnxqctn(ac[j1]), 0, flag2);
            j6++;
            j1++;
        }
        if (flag9) {
            abyte0[j6 - 1] += (flag2 ? -1 : 1) * (flag8 ? 10 : 1);
        }
        byte byte1;
        if (j3 < 0) {
            byte1 = (byte) (193 - (1 - j3) / 2);
        } else {
            byte1 = (byte) (193 + j3 / 2);
        }
        int i6 = j6;
        abyte0[0] = (byte) (flag2 ? ~byte1 : byte1);
        return _setLength(abyte0, i6);
    }

    private static byte digitPtr(int i, int j, boolean flag) {
        return flag ? LnxqDigit_N[i][j] : LnxqDigit_P[i][j];
    }

    private static int lnxqctn(char c) {
        return Character.digit(c, 10);
    }

    //    public byte[] lnxfcn(String s, String s1, String s2) throws SQLException {
    //        LnxLibThinFormat lnxlibthinformat;
    //        int i;
    //        int j;
    //        int l;
    //        char ac[];
    //        int i1;
    //        int j1;
    //        char ac1[];
    //        int l1;
    //        byte abyte0[];
    //        int k2;
    //        int l2;
    //        boolean flag;
    //        l0: {
    //            if (s2 != null && !s2.equals("AMERICAN_AMERICAN")) {
    //                throw new SQLException(CoreException.getMessage((byte) 12));
    //            }
    //            lnxlibthinformat = new LnxLibThinFormat();
    //            lnxlibthinformat.parseFormat(s1);
    //            lnxlibthinformat.LNXNFRDX = true;
    //            i = lnxlibthinformat.lnxnflhd;
    //            j = lnxlibthinformat.lnxnfrhd;
    //            int k = lnxlibthinformat.lnxnfsiz;
    //            l = lnxlibthinformat.lnxnfzld;
    //            if (lnxlibthinformat.LNXNFFDA | lnxlibthinformat.LNXNFFED | lnxlibthinformat.LNXNFFRN
    //                    | lnxlibthinformat.LNXNFFTM) {
    //                throw new SQLException(CoreException.getMessage((byte) 5));
    //            }
    //            if (lnxlibthinformat.LNXNFFRC | lnxlibthinformat.LNXNFFCH | lnxlibthinformat.LNXNFFCT) {
    //                throw new SQLException(CoreException.getMessage((byte) 12));
    //            }
    //            ac = s.toCharArray();
    //            i1 = 0;
    //            j1 = ac.length - 1;
    //            int k1;
    //            for (k1 = 0; i1 <= j1 && Character.isSpaceChar(ac[i1]); k1++) {
    //                i1++;
    //            }
    //
    //            if (lnxlibthinformat.LNXNFFBL && k1 == k && k1 == ac.length) {
    //                return NUMBER._makeZero();
    //            }
    //            if (i1 > j1) {
    //                throw new SQLException(CoreException.getMessage((byte) 14));
    //            }
    //            ac1 = new char[255];
    //            l1 = 0;
    //            if (lnxlibthinformat.LNXNFFSH) {
    //                if (ac[i1] != '-' && ac[i1] != '+') {
    //                    throw new SQLException(CoreException.getMessage((byte) 14));
    //                }
    //                ac1[l1++] = ac[i1++];
    //            } else if (lnxlibthinformat.LNXNFFPR) {
    //                if (ac[i1] == '<') {
    //                    ac1[l1++] = '-';
    //                    i1++;
    //                } else {
    //                    ac1[l1++] = '+';
    //                }
    //            } else if (lnxlibthinformat.LNXNFFPT) {
    //                if (ac[i1] == '(') {
    //                    ac1[l1++] = '-';
    //                    i1++;
    //                } else {
    //                    ac1[l1++] = '+';
    //                }
    //            } else if (!lnxlibthinformat.LNXNFFMI && !lnxlibthinformat.LNXNFFST) {
    //                if (ac[i1] == '-') {
    //                    ac1[l1++] = ac[i1++];
    //                }
    //            } else {
    //                l1++;
    //            }
    //            if (i1 > j1) {
    //                throw new SQLException(CoreException.getMessage((byte) 14));
    //            }
    //            if (lnxlibthinformat.LNXNFFDS) {
    //                if (ac[i1] != '$') {
    //                    throw new SQLException(CoreException.getMessage((byte) 14));
    //                }
    //                if (++i1 > j1) {
    //                    throw new SQLException(CoreException.getMessage((byte) 14));
    //                }
    //            } else if (lnxlibthinformat.LNXNFFCH) {
    //                throw new SQLException(CoreException.getMessage((byte) 12));
    //            }
    //            abyte0 = new byte[40];
    //            int i2 = 0;
    //            k2 = 0;
    //            l2 = 0;
    //            flag = false;
    //            do {
    //                if (i1 > j1) {
    //                    break label0;
    //                }
    //                if (Character.isDigit(ac[i1]) || lnxlibthinformat.LNXNFFHX
    //                        && (ac[i1] >= 'a' && ac[i1] <= 'f' || ac[i1] >= 'A' && ac[i1] <= 'F')) {
    //                    ac1[l1++] = ac[i1++];
    //                    l2++;
    //                    continue;
    //                }
    //                if (!flag) {
    //                    if (lnxlibthinformat.LNXNFRDX) {
    //                        if (ac[i1] == ',') {
    //                            i1++;
    //                            abyte0[i2++] = (byte) l2;
    //                            continue;
    //                        }
    //                        if (ac[i1] == '.') {
    //                            if (l2 > i || l2 < l) {
    //                                throw new SQLException(CoreException.getMessage((byte) 14));
    //                            }
    //                            l2 = i - l2;
    //                            i2 = 0;
    //                            for (; lnxlibthinformat.lnxnfgps[k2] != 0; k2++) {
    //                                byte byte0 = (byte) (lnxlibthinformat.lnxnfgps[k2] & 0x7f);
    //                                if (byte0 <= l2) {
    //                                    continue;
    //                                }
    //                                if (abyte0[i2] != byte0 - l2) {
    //                                    throw new SQLException(CoreException.getMessage((byte) 14));
    //                                }
    //                                i2++;
    //                            }
    //
    //                            l2 = 0;
    //                            flag = true;
    //                            ac1[l1] = '.';
    //                            l1++;
    //                            i1++;
    //                            continue;
    //                        }
    //                    } else {
    //                        throw new SQLException(CoreException.getMessage((byte) 12));
    //                    }
    //                }
    //                if (ac[i1] != 'E' && ac[i1] != 'e' || !lnxlibthinformat.LNXNFFSN) {
    //                    break;
    //                }
    //                if (l2 <= 0 && !flag) {
    //                    throw new SQLException(CoreException.getMessage((byte) 14));
    //                }
    //                ac1[l1++] = ac[i1++];
    //                if (i1 > j1) {
    //                    throw new SQLException(CoreException.getMessage((byte) 14));
    //                }
    //                if (ac[i1] == '+' || ac[i1] == '-') {
    //                    ac1[l1++] = ac[i1++];
    //                }
    //                int i3 = i1;
    //                for (; i1 <= j1 && Character.isDigit(ac[i1]); ac1[l1++] = ac[i1++]) {
    //                }
    //                if (i3 == i1) {
    //                    throw new SQLException(CoreException.getMessage((byte) 14));
    //                }
    //            } while (true);
    //            if (lnxlibthinformat.LNXNFFRC) {
    //                throw new SQLException(CoreException.getMessage((byte) 12));
    //            }
    //        }
    //        if (!flag) {
    //            int j3 = i - l2;
    //            int j2 = 0;
    //            for (; lnxlibthinformat.lnxnfgps[k2] != 0; k2++) {
    //                byte byte1 = (byte) (lnxlibthinformat.lnxnfgps[k2] & 0x7f);
    //                if (byte1 <= j3) {
    //                    continue;
    //                }
    //                if (abyte0[j2] != byte1 - j3) {
    //                    throw new SQLException(CoreException.getMessage((byte) 14));
    //                }
    //                j2++;
    //            }
    //
    //        }
    //        if (lnxlibthinformat.LNXNFFCT) {
    //            throw new SQLException(CoreException.getMessage((byte) 12));
    //        }
    //        if (lnxlibthinformat.LNXNFFST) {
    //            if (i1 > j1) {
    //                throw new SQLException(CoreException.getMessage((byte) 14));
    //            }
    //            if (ac[i1] != '-' && ac[i1] != '+') {
    //                throw new SQLException(CoreException.getMessage((byte) 14));
    //            }
    //            ac1[0] = ac[i1];
    //            i1++;
    //        } else if (lnxlibthinformat.LNXNFFMI) {
    //            if (i1 > j1) {
    //                if (lnxlibthinformat.LNXNFFIL || i1 != ac.length) {
    //                    throw new SQLException(CoreException.getMessage((byte) 14));
    //                }
    //                ac1[0] = '+';
    //            } else {
    //                ac1[0] = ac[i1] != '-' ? '+' : '-';
    //                i1++;
    //            }
    //        } else if (lnxlibthinformat.LNXNFFPR) {
    //            if (ac[i1] == '>' && ac1[0] == '-') {
    //                i1++;
    //            }
    //        } else if (lnxlibthinformat.LNXNFFPT && i1 < ac.length && ac[i1] == ')' && ac1[0] == '-') {
    //            i1++;
    //        }
    //        if (i1 <= j1) {
    //            throw new SQLException(CoreException.getMessage((byte) 14));
    //        }
    //        if (flag) {
    //            if (l2 > j) {
    //                throw new SQLException(CoreException.getMessage((byte) 14));
    //            }
    //        } else {
    //            if (l2 > i) {
    //                throw new SQLException(CoreException.getMessage((byte) 14));
    //            }
    //            if (l2 < l) {
    //                throw new SQLException(CoreException.getMessage((byte) 14));
    //            }
    //        }
    //        if (lnxlibthinformat.LNXNFF05 && (flag && l2 == j || j == 0)) {
    //            l1--;
    //            if (ac1[l1] != '0' && ac1[l1] != '5') {
    //                throw new SQLException(CoreException.getMessage((byte) 14));
    //            }
    //            l1++;
    //        }
    //        if (lnxlibthinformat.LNXNFFHX) {
    //            return lnxqh2n(ac1);
    //        } else {
    //            return lnxcpn(new String(ac1), false, 0, false, 0, s2);
    //        }
    //    }

    public byte[] lnxfcn(String paramString1, String paramString2, String paramString3)
            throws SQLException {
        if ((paramString3 != null) && (!paramString3.equals("AMERICAN_AMERICAN"))) {
            throw new SQLException(CoreException.getMessage((byte) 12));
        }

        LnxLibThinFormat localLnxLibThinFormat = new LnxLibThinFormat();
        localLnxLibThinFormat.parseFormat(paramString2);

        localLnxLibThinFormat.LNXNFRDX = true;

        int i = localLnxLibThinFormat.lnxnflhd;
        int j = localLnxLibThinFormat.lnxnfrhd;
        int k = localLnxLibThinFormat.lnxnfsiz;
        int m = localLnxLibThinFormat.lnxnfzld;

        if ((localLnxLibThinFormat.LNXNFFDA | localLnxLibThinFormat.LNXNFFED
                | localLnxLibThinFormat.LNXNFFRN | localLnxLibThinFormat.LNXNFFTM)) {
            throw new SQLException(CoreException.getMessage((byte)5));
        }

        if ((localLnxLibThinFormat.LNXNFFRC | localLnxLibThinFormat.LNXNFFCH | localLnxLibThinFormat.LNXNFFCT)) {
            throw new SQLException(CoreException.getMessage((byte)12));
        }

        char[] arrayOfChar1 = paramString1.toCharArray();
        int n = 0;
        int i1 = arrayOfChar1.length - 1;

        int i2 = 0;

        while ((n <= i1) && (Character.isSpaceChar(arrayOfChar1[n]))) {
            n++;
            i2++;
        }

        if ((localLnxLibThinFormat.LNXNFFBL) && (i2 == k) && (i2 == arrayOfChar1.length)) {
            return NUMBER._makeZero();
        }

        if (n > i1) {
            throw new SQLException(CoreException.getMessage((byte)14));
        }

        char[] arrayOfChar2 = new char['\u00FF'];
        int i3 = 0;

        if (localLnxLibThinFormat.LNXNFFSH) {
            if ((arrayOfChar1[n] != '-') && (arrayOfChar1[n] != '+')) {
                throw new SQLException(CoreException.getMessage((byte)14));
            }

            arrayOfChar2[(i3++)] = arrayOfChar1[(n++)];
        } else if (localLnxLibThinFormat.LNXNFFPR) {
            if (arrayOfChar1[n] == '<') {
                arrayOfChar2[(i3++)] = '-';
                n++;
            } else {
                arrayOfChar2[(i3++)] = '+';
            }
        } else if (localLnxLibThinFormat.LNXNFFPT) {
            if (arrayOfChar1[n] == '(') {
                arrayOfChar2[(i3++)] = '-';
                n++;
            } else {
                arrayOfChar2[(i3++)] = '+';
            }
        } else if ((!localLnxLibThinFormat.LNXNFFMI) && (!localLnxLibThinFormat.LNXNFFST)) {
            if (arrayOfChar1[n] == '-') {
                arrayOfChar2[(i3++)] = arrayOfChar1[(n++)];
            }

        } else {
            i3++;
        }

        if (n > i1) {
            throw new SQLException(CoreException.getMessage((byte)14));
        }

        if (localLnxLibThinFormat.LNXNFFDS) {
            if (arrayOfChar1[n] != '$') {
                throw new SQLException(CoreException.getMessage((byte)14));
            }

            n++;

            if (n > i1) {
                throw new SQLException(CoreException.getMessage((byte)14));
            }

        } else if (localLnxLibThinFormat.LNXNFFCH) {
            throw new SQLException(CoreException.getMessage((byte)12));
        }
        byte[] arrayOfByte = new byte[40];
        int i4 = 0;

        int i5 = 0;

        int i6 = 0;
        int i7 = 0;
        int i8;
        label1046: while (true) {
            if (n > i1)
                break label1046;
            if ((Character.isDigit(arrayOfChar1[n]))
                    || ((localLnxLibThinFormat.LNXNFFHX) && (((arrayOfChar1[n] >= 'a') && (arrayOfChar1[n] <= 'f')) || ((arrayOfChar1[n] >= 'A') && (arrayOfChar1[n] <= 'F'))))) {
                arrayOfChar2[(i3++)] = arrayOfChar1[(n++)];
                i6++;
                continue;
            }
            if (i7 == 0) {
                if (localLnxLibThinFormat.LNXNFRDX) {
                    if (arrayOfChar1[n] == ',') {
                        n++;
                        arrayOfByte[(i4++)] = (byte) i6;
                        continue;
                    }
                    if (arrayOfChar1[n] == '.') {
                        if ((i6 > i) || (i6 < m)) {
                            throw new SQLException(CoreException.getMessage((byte)14));
                        }

                        i6 = i - i6;

                        i4 = 0;
                        while (localLnxLibThinFormat.lnxnfgps[i5] != 0) {
                            i8 = (byte) (localLnxLibThinFormat.lnxnfgps[i5] & 0x7F);

                            if (i8 > i6) {
                                if (arrayOfByte[i4] != i8 - i6) {
                                    throw new SQLException(CoreException.getMessage((byte)14));
                                }

                                i4++;
                            }

                            i5++;
                        }

                        i6 = 0;
                        i7 = 1;
                        arrayOfChar2[i3] = '.';
                        i3++;
                        n++;
                        continue;
                    }

                } else {
                    throw new SQLException(CoreException.getMessage((byte)12));
                }

            }

            if (((arrayOfChar1[n] != 'E') && (arrayOfChar1[n] != 'e'))
                    || (!localLnxLibThinFormat.LNXNFFSN)) {
                break;
            }
            if ((i6 <= 0) && (i7 == 0)) {
                throw new SQLException(CoreException.getMessage((byte)14));
            }

            arrayOfChar2[(i3++)] = arrayOfChar1[(n++)];
            if (n > i1) {
                throw new SQLException(CoreException.getMessage((byte)14));
            }

            if ((arrayOfChar1[n] == '+') || (arrayOfChar1[n] == '-')) {
                arrayOfChar2[(i3++)] = arrayOfChar1[(n++)];
            }
            i8 = n;
            while ((n <= i1) && (Character.isDigit(arrayOfChar1[n]))) {
                arrayOfChar2[(i3++)] = arrayOfChar1[(n++)];
            }

            if (i8 != n)
                continue;
            throw new SQLException(CoreException.getMessage((byte)14));
        }

        if (localLnxLibThinFormat.LNXNFFRC) {
            throw new SQLException(CoreException.getMessage((byte)12));
        }

        if (i7 == 0) {
            i8 = i - i6;
            i4 = 0;

            while (localLnxLibThinFormat.lnxnfgps[i5] != 0) {
                int i9 = (byte) (localLnxLibThinFormat.lnxnfgps[i5] & 0x7F);
                if (i9 > i8) {
                    if (arrayOfByte[i4] != i9 - i8) {
                        throw new SQLException(CoreException.getMessage((byte)14));
                    }

                    i4++;
                }
                i5++;
            }

        }

        if (localLnxLibThinFormat.LNXNFFCT) {
            throw new SQLException(CoreException.getMessage((byte)12));
        }

        if (localLnxLibThinFormat.LNXNFFST) {
            if (n > i1) {
                throw new SQLException(CoreException.getMessage((byte)14));
            }

            if ((arrayOfChar1[n] != '-') && (arrayOfChar1[n] != '+')) {
                throw new SQLException(CoreException.getMessage((byte)14));
            }

            arrayOfChar2[0] = arrayOfChar1[n];
            n++;
        } else if (localLnxLibThinFormat.LNXNFFMI) {
            if (n > i1) {
                if ((localLnxLibThinFormat.LNXNFFIL) || (n != arrayOfChar1.length)) {
                    throw new SQLException(CoreException.getMessage((byte)14));
                }

                arrayOfChar2[0] = '+';
            } else {
                arrayOfChar2[0] = (arrayOfChar1[n] == '-' ? 45 : '+');
                n++;
            }
        } else if (localLnxLibThinFormat.LNXNFFPR) {
            if ((arrayOfChar1[n] == '>') && (arrayOfChar2[0] == '-')) {
                n++;
            }
        } else if (localLnxLibThinFormat.LNXNFFPT) {
            if ((n < arrayOfChar1.length) && (arrayOfChar1[n] == ')') && (arrayOfChar2[0] == '-')) {
                n++;
            }

        }

        if (n <= i1) {
            throw new SQLException(CoreException.getMessage((byte) 14));
        }

        if (i7 != 0) {
            if (i6 > j) {
                throw new SQLException(CoreException.getMessage((byte) 14));
            }

        } else {
            if (i6 > i) {
                throw new SQLException(CoreException.getMessage((byte) 14));
            }

            if (i6 < m) {
                throw new SQLException(CoreException.getMessage((byte) 14));
            }

        }

        if ((localLnxLibThinFormat.LNXNFF05) && (((i7 != 0) && (i6 == j)) || (j == 0))) {
            i3--;
            if ((arrayOfChar2[i3] != '0') && (arrayOfChar2[i3] != '5')) {
                throw new SQLException(CoreException.getMessage((byte) 14));
            }

            i3++;
        }

        if (localLnxLibThinFormat.LNXNFFHX) {
            return lnxqh2n(arrayOfChar2);
        }

        return lnxcpn(new String(arrayOfChar2), false, 0, false, 0, paramString3);
    }

    public String lnxnfn(byte abyte0[], String s, String s1) throws SQLException {
        char ac[] = null;
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        int k1 = 0;
        int l1 = 0;
        int i2 = 0;
        int j2 = 0;
        int k2 = 0;
        int l2 = 0;
        int i3 = 0;
        int j3 = 0;
        int l3 = 0;
        int i4 = 0;
        int j4 = 0;
        boolean flag = false;
        byte abyte2[] = null;
        boolean aflag[] = new boolean[1];
        boolean flag1 = false;
        boolean flag2 = true;
        boolean flag5 = true;
        String s2 = null;
        LnxLibThinFormat lnxlibthinformat = new LnxLibThinFormat();
        lnxlibthinformat.parseFormat(s);
        i = lnxlibthinformat.lnxnflhd;
        j = lnxlibthinformat.lnxnfrhd;
        k = lnxlibthinformat.lnxnfsiz;
        l = lnxlibthinformat.lnxnfzld;
        i1 = lnxlibthinformat.lnxnfztr;
        if (lnxlibthinformat.LNXNFFRN || lnxlibthinformat.LNXNFFHX) {
            if (lnxlibthinformat.LNXNFFRN) {
                throw new SQLException(CoreException.getMessage((byte) 1));
            } else {
                throw new SQLException(CoreException.getMessage((byte) 1));
            }
        }
        if (lnxlibthinformat.LNXNFFTM) {
            int i5 = abyte0.length;
            int j8;
            int k8;
            int l8;
            if (!NUMBER._isZero(abyte0)) {
                if (NUMBER._isPositive(abyte0)) {
                    int i7 = i5 - 1;
                    j8 = 2 * ((abyte0[0] & 0xff) - 193) + ((abyte0[1] & 0xff) <= 10 ? 0 : 1);
                    k8 = 2 * i7 - ((abyte0[1] & 0xff) >= 11 ? 0 : 1) - LnxqFirstDigit[abyte0[i7]];
                    l8 = 0;
                } else {
                    if ((abyte0[i5 - 1] & 0xff) == 102) {
                        i5--;
                    }
                    int j7 = i5 - 1;
                    j8 = 2 * (62 - abyte0[0]) + ((abyte0[1] & 0xff) >= 92 ? 0 : 1);
                    k8 = 2 * j7 - ((abyte0[1] & 0xff) <= 91 ? 0 : 1) - LnxqFirstDigit[abyte0[j7]];
                    l8 = 1;
                }
            } else {
                j8 = 0;
                k8 = 1;
                l8 = 0;
            }
            if (j8 >= 0) {
                l8 += k8 <= j8 + 1 ? j8 + 1 : k8 + 1;
            } else {
                l8 += -(j8 + 1) + k8 + 1;
            }
            if (!lnxlibthinformat.LNXNFFSN && l8 > 64) {
                lnxlibthinformat.LNXNFFSN = true;
            }
            if (lnxlibthinformat.LNXNFFSN) {
                i = 1;
                j = k8 - 1;
                k = k8 <= 1 ? 7 : k8 + 7;
            } else {
                i = j8 + 1 <= 0 ? 0 : j8 + 1;
                j = k8 - (j8 + 1) <= 0 ? 0 : k8 - (j8 + 1);
                k = j == 0 ? i + 1 : i + j + 2;
            }
            if (j == 0) {
                lnxlibthinformat.LNXNFNRD = true;
            }
        }
        boolean flag6 = (abyte0[0] & 0xff) >= 128;
        byte abyte1[];
        if (lnxlibthinformat.LNXNFFSN) {
            abyte1 = lnxfpr(abyte0, j + i);
        } else {
            abyte1 = lnxsca(abyte0, i, j, aflag);
            if (aflag[0]) {
                throw new SQLException(CoreException.getMessage((byte) 4));
            }
        }
        ac = new char[64];
        j2 = 0;
        if (NUMBER._isZero(abyte1)) {
            if (lnxlibthinformat.LNXNFFBL) {
                if (lnxlibthinformat.LNXNFFIL) {
                    ac = new char[k];
                    for (int j5 = 0; j5 < k; j5++) {
                        ac[j5] = ' ';
                    }

                    return new String(ac);
                } else {
                    return null;
                }
            }
            flag2 = flag6;
            flag1 = false;
            k1 = 0;
            i2 = 0;
            j1 = k1;
            l1 = i <= 0 || i1 != 0 ? 0 : 1;
        } else {
            if (NUMBER._isNegInf(abyte1) || NUMBER._isPosInf(abyte1)) {
                throw new SQLException(CoreException.getMessage((byte) 4));
            }
            i2 = abyte1.length - 1;
            flag2 = (abyte1[0] & 0x80) != 0;
            if (!flag2) {
                abyte2 = new byte[i2];
                if (abyte1[i2] == 102) {
                    i2--;
                }
                for (int k5 = 1; k5 <= i2; k5++) {
                    abyte2[k5] = (byte) (102 - abyte1[k5]);
                }

                abyte2[0] = (byte) (~abyte1[0]);
            } else {
                abyte2 = abyte1;
            }
            boolean flag3 = (abyte2[i2] & 0xff) % 10 == 1;
            j1 = 2 * ((abyte2[0] & 0xff) - 192);
            k2 = 1;
            if (flag5 = (abyte2[k2] & 0xff) < 11) {
                l3 = ((abyte2[k2] & 0xff) - 1) / 10;
            }
            if (lnxlibthinformat.LNXNFFSN) {
                k1 = 2 * i2 - (flag5 ? 1 : 0) - (flag3 ? 1 : 0) - (l1 = 1);
                j1 -= (flag5 ? 1 : 0) + 1;
                if (flag1 = j1 < 0) {
                    j1 = -j1;
                }
                if (j1 < 100 && lnxlibthinformat.LNXNFFIL) {
                    ac[j2] = ' ';
                    j2++;
                }
            } else {
                k1 = 2 * i2 - j1 - (flag3 ? 1 : 0);
                l1 = j1 - (flag5 ? 1 : 0);
                if (lnxlibthinformat.LNXNFF05 && (j == 0 || k1 == j)) {
                    int l5 = (k2 + i2) - 1;
                    int k7;
                    if (!flag3) {
                        k7 = (abyte2[l5] & 0xff) % 10;
                        k7 = k7 == 0 ? 9 : k7 - 1;
                        if (k7 <= 2) {
                            abyte2[l5] = (byte) ((abyte2[l5] & 0xff) - k7);
                        } else if (k7 <= 7) {
                            abyte2[l5] = (byte) ((abyte2[l5] & 0xff) + (5 - k7));
                        }
                    } else {
                        k7 = (abyte2[l5] & 0xff) / 10;
                        if (k7 <= 2) {
                            abyte2[l5] = 1;
                        } else if (k7 <= 7) {
                            abyte2[l5] = 51;
                        }
                    }
                    if (k7 > 7) {
                        k2--;
                        abyte2 = lnxrou(abyte2, k1 - 1);
                        i2 = abyte2.length - 1;
                        boolean flag4 = (abyte2[k2] & 0xff) % 10 == 1;
                        j1 = 2 * ((abyte2[k2] & 0xff) - 192);
                        k2++;
                        if (flag5 = (abyte2[k2] & 0xff) < 11) {
                            l3 = ((abyte2[k2] & 0xff) - 1) / 10;
                        }
                        k1 = 2 * i2 - j1 - (flag4 ? 1 : 0);
                        l1 = j1 - (flag5 ? 1 : 0);
                        if (l1 > i) {
                            throw new SQLException(CoreException.getMessage((byte) 4));
                        }
                    }
                }
            }
        }
        i4 = i - (l <= l1 ? l1 : l);
        if (i4 != 0 && lnxlibthinformat.LNXNFFIL) {
            int i6 = i4 + j2;
            for (int l7 = 0; l7 < i6;) {
                ac[l7] = ' ';
                l7++;
                j2++;
            }

        }
        if (!lnxlibthinformat.LNXNFFMI && !lnxlibthinformat.LNXNFFST) {
            char c;
            if (flag2) {
                c = lnxlibthinformat.LNXNFFSH ? '+' : ' ';
            } else {
                c = lnxlibthinformat.LNXNFFPR ? '<' : ((char) (lnxlibthinformat.LNXNFFPT ? '('
                        : '-'));
            }
            if (lnxlibthinformat.LNXNFFIL || c != ' ') {
                ac[j2] = c;
                j2++;
            }
        }
        if (lnxlibthinformat.LNXNFFIC) {
            s2 = new String("USD");
        } else if (lnxlibthinformat.LNXNFFUN) {
            s2 = new String("$");
        } else {
            s2 = new String("$");
        }
        if (lnxlibthinformat.LNXNFFDS) {
            ac[j2] = '$';
            j2++;
        } else if (lnxlibthinformat.LNXNFFCH) {
            for (int j6 = 0; j6 < s2.length();) {
                ac[j2] = s2.charAt(j6);
                j6++;
                j2++;
            }

        }
        for (l2 = 0; (i3 = lnxlibthinformat.lnxnfgps[l2] & 0x7f) != 0 && i3 <= i4; l2++) {
        }
        if ((j3 = l - (l1 <= 0 ? 0 : l1)) > 0) {
            for (; j3 > 0; j3--) {
                ac[j2] = '0';
                j2++;
                for (i4++; i4 == i3; i3 = lnxlibthinformat.lnxnfgps[l2] & 0x7f) {
                    ac[j2] = ',';
                    j2++;
                    l2++;
                }

            }

        }
        if (l1 > 0) {
            while (l1 > 0 && i2 != 0) {
                int k4;
                if (flag5) {
                    k4 = (abyte2[k2] & 0xff) - 1 - l3 * 10;
                    k2++;
                    i2--;
                } else {
                    k4 = ((abyte2[k2] & 0xff) - 1) / 10;
                    l3 = k4;
                }
                ac[j2] = lnx_chars[k4];
                j2++;
                for (i4++; i4 == i3; i3 = lnxlibthinformat.lnxnfgps[l2] & 0x7f) {
                    ac[j2] = ',';
                    j2++;
                    l2++;
                }

                l1--;
                flag5 = !flag5;
            }
            for (; l1 > 0; l1--) {
                ac[j2] = '0';
                j2++;
                for (i4++; i4 == i3; i3 = lnxlibthinformat.lnxnfgps[l2] & 0x7f) {
                    ac[j2] = ',';
                    j2++;
                    l2++;
                }

            }

        }
        if (!lnxlibthinformat.LNXNFNRD) {
            if (lnxlibthinformat.LNXNFRDX) {
                ac[j2] = '.';
                j2++;
            } else if (lnxlibthinformat.LNXNFFRC) {
                for (int k6 = 0; k6 < s2.length();) {
                    ac[j2] = s2.charAt(k6);
                    k6++;
                    j2++;
                }

            } else {
                ac[j2] = '.';
                j2++;
            }
        }
        i4 = 0;
        if ((j4 = i1 - (k1 <= 0 ? 0 : k1)) < 0) {
            j4 = 0;
        }
        if (l1 != 0) {
            l1 = -l1;
            k1 -= l1;
            for (; l1 != 0; l1--) {
                ac[j2] = '0';
                j2++;
            }

        }
        while (i2 != 0 && k1 != 0) {
            int l4;
            if (flag5) {
                l4 = (abyte2[k2] & 0xff) - 1 - l3 * 10;
                k2++;
                i2--;
            } else {
                l4 = ((abyte2[k2] & 0xff) - 1) / 10;
                l3 = l4;
            }
            ac[j2] = lnx_chars[l4];
            j2++;
            k1--;
            flag5 = !flag5;
        }
        for (; j4 != 0; j4--) {
            ac[j2] = '0';
            j2++;
        }

        if (lnxlibthinformat.LNXNFFSN) {
            ac[j2] = 'E';
            j2++;
            ac[j2] = flag1 ? '-' : '+';
            j2++;
            if (j1 > 99) {
                ac[j2] = '1';
                j2++;
                j1 -= 100;
            }
            ac[j2] = lnx_chars[j1 / 10];
            j2++;
            ac[j2] = lnx_chars[j1 % 10];
            j2++;
        }
        if (lnxlibthinformat.LNXNFFCT) {
            for (int l6 = 0; l6 < s2.length();) {
                ac[j2] = s2.charAt(l6);
                l6++;
                j2++;
            }

        }
        if (flag2) {
            if (lnxlibthinformat.LNXNFFST) {
                ac[j2] = '+';
                j2++;
            } else if ((lnxlibthinformat.LNXNFFPR || lnxlibthinformat.LNXNFFMI || lnxlibthinformat.LNXNFFPT)
                    && lnxlibthinformat.LNXNFFIL) {
                ac[j2] = ' ';
                j2++;
            }
        } else if (lnxlibthinformat.LNXNFFPR) {
            ac[j2] = '>';
            j2++;
        } else if (lnxlibthinformat.LNXNFFPT) {
            ac[j2] = ')';
            j2++;
        } else if (lnxlibthinformat.LNXNFFMI || lnxlibthinformat.LNXNFFST) {
            ac[j2] = '-';
            j2++;
        }
        j4 = j2;
        if (lnxlibthinformat.LNXNFFIL && j4 != k) {
            int k3 = k - j4;
            char ac1[] = new char[k3];
            for (int i8 = 0; i8 < k3; i8++) {
                ac1[i8] = ' ';
            }

            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append(ac1);
            stringbuffer.append(ac, 0, j4);
            return stringbuffer.toString();
        } else {
            return new String(ac, 0, j4);
        }
    }

    public String lnxnuc(byte abyte0[], int i, String s) throws SQLException {
        byte abyte2[] = new byte[22];
        boolean flag4 = false;
        if (s != null) {
            throw new SQLException(CoreException.getMessage((byte) 12));
        }
        char ac[] = lnx_chars;
        char c = '.';
        if (i == 0) {
            throw new SQLException(CoreException.getMessage((byte) 13));
        }
        boolean flag;
        if (!(flag = i >= 0)) {
            i = -i;
        }
        char ac1[] = new char[i];
        byte abyte1[][];
        int i4;
        boolean flag2;
        if (flag2 = !NUMBER._isPositive(abyte0)) {
            abyte1 = LnxqComponents_N;
            i4 = i - 1;
        } else {
            abyte1 = LnxqComponents_P;
            i4 = i;
        }
        boolean flag7 = true;
        do {
            if (!flag7) {
                break;
            }
            boolean flag1 = flag;
            int i8 = abyte0.length;
            if (i8 == 1) {
                if ((abyte0[0] & 0xff) == 128) {
                    int i3;
                    if (flag1) {
                        i3 = i - 1;
                        ac1[i3] = ac[0];
                    } else {
                        if (i < 5) {
                            throw new SQLException(CoreException.getMessage((byte) 13));
                        }
                        i3 = i - 5;
                        ac1[i3] = ac[0];
                        ac1[i3 + 1] = ac[41];
                        ac1[i3 + 2] = ac[10];
                        ac1[i3 + 3] = ac[0];
                        ac1[i3 + 4] = ac[0];
                    }
                    if (i3 != 0) {
                        for (int j6 = 0; j6 < i3; j6++) {
                            ac1[j6] = ac[12];
                        }

                    }
                    return new String(ac1);
                }
                if (i < 2) {
                    throw new SQLException(CoreException.getMessage((byte) 13));
                }
                int j3 = i - 2;
                ac1[j3] = ac[11];
                ac1[j3 + 1] = ac[21];
                if (j3 != 0) {
                    for (int k6 = 0; k6 < j3; k6++) {
                        ac1[k6] = ac[12];
                    }

                }
                return new String(ac1);
            }
            if (i8 == 2 && (abyte0[0] & 0xff) == 255 && (abyte0[1] & 0xff) == 101) {
                int k3 = i - 1;
                ac1[k3] = ac[21];
                if (k3 != 0) {
                    for (int l6 = 0; l6 < k3; l6++) {
                        ac1[l6] = ac[12];
                    }

                }
                return new String(ac1);
            }
            int l2;
            int j4;
            if (flag2) {
                if (abyte0[i8 - 1] == 102) {
                    i8--;
                }
                int j2 = i8 - 1;
                j4 = 2 * (62 - (abyte0[0] & 0xff)) + ((abyte0[1] & 0xff) >= 92 ? 0 : 1);
                l2 = 2 * j2 - ((abyte0[1] & 0xff) <= 91 ? 0 : 1) - LnxqFirstDigit[abyte0[j2]];
            } else {
                int k2 = i8 - 1;
                j4 = 2 * ((abyte0[0] & 0xff) - 193) + ((abyte0[1] & 0xff) <= 10 ? 0 : 1);
                l2 = 2 * k2 - ((abyte0[1] & 0xff) >= 11 ? 0 : 1) - LnxqFirstDigit[abyte0[k2]];
            }
            if (flag1) {
                if (j4 >= 0) {
                    flag1 = j4 < i4;
                } else {
                    flag1 = j4 >= -5 || l2 - j4 <= i4 || i4 <= 6;
                }
            }
            int i2;
            boolean flag3;
            boolean flag5;
            boolean flag6;
            int l5;
            int i6;
            if (flag1) {
                int k4;
                if (j4 >= 0) {
                    k4 = i4 > j4 + 1 ? i4 - 1 : i4;
                } else {
                    k4 = i4 + j4;
                }
                if (k4 < l2) {
                    abyte0 = lnxfpr(abyte0, k4);
                    continue;
                }
                flag7 = false;
                int l3;
                if (j4 >= 0) {
                    if (l2 > j4 + 1) {
                        l3 = i4 - (l2 + 1);
                        flag3 = true;
                        flag4 = false;
                        flag5 = (j4 & 1) <= 0;
                        flag6 = (l2 - j4 & 1) <= 0;
                        int j8 = 0x7fffffff;
                        l5 = j4 + 1 & j8 - 1;
                        i6 = l2 - (j4 + 1) & j8 - 1;
                        i2 = l3;
                    } else {
                        l3 = i4 - (j4 + 1);
                        int j5 = (j4 + 1) - l2;
                        flag3 = false;
                        flag5 = (j4 & 1) <= 0;
                        flag6 = (j5 & 1) > 0;
                        l5 = l2 - (flag5 ? 1 : 0) - (flag6 ? 1 : 0);
                        i6 = 0;
                        if (j5 != 0) {
                            int i1 = l3 + (flag2 ? 1 : 0) + l2;
                            for (int i7 = 0; i7 < j5; i7++) {
                                ac1[i1 + i7] = ac[0];
                            }

                        }
                        i2 = l3;
                    }
                } else {
                    l3 = i4 - (l2 - j4);
                    int i5 = -(j4 + 1);
                    flag3 = false;
                    flag5 = (i5 & 1) > 0;
                    flag6 = (i5 + l2 & 1) > 0;
                    l5 = 0;
                    i6 = l2 - (flag5 ? 1 : 0) - (flag6 ? 1 : 0);
                    int j1 = l3;
                    if (flag2) {
                        ac1[j1] = ac[11];
                        j1++;
                        flag2 = false;
                    }
                    ac1[j1] = c;
                    j1++;
                    if (i5 != 0) {
                        for (int j7 = 0; j7 < i5; j7++) {
                            ac1[j1 + j7] = ac[0];
                        }

                        j1 += i5;
                    }
                    i2 = j1;
                }
                if (l3 != 0) {
                    for (int k7 = 0; k7 < l3; k7++) {
                        ac1[k7] = ac[12];
                    }

                }
            } else {
                int l4 = i4 - (j4 <= 99 && j4 >= -99 ? 5 : 6);
                if (l4 < 2) {
                    throw new SQLException(CoreException.getMessage((byte) 13));
                }
                if (l4 < l2) {
                    abyte0 = lnxfpr(abyte0, l4);
                    continue;
                }
                flag7 = false;
                int k5;
                if (l2 == 1) {
                    k5 = l4 - 1;
                    flag3 = true;
                    flag4 = (j4 & 1) > 0;
                    flag5 = !flag4;
                    flag6 = false;
                    l5 = 0;
                    i6 = 0;
                } else {
                    k5 = l4 - l2;
                    flag3 = true;
                    flag4 = (j4 & 1) > 0;
                    flag5 = !flag4;
                    flag6 = flag4 == ((l2 & 1) > 0);
                    l5 = 0;
                    i6 = l2 - (flag5 ? 1 : 2) - (flag6 ? 1 : 0);
                }
                int k1 = (flag2 ? 1 : 0) + 1 + l2;
                if (k5 != 0) {
                    for (int l7 = 0; l7 < k5; l7++) {
                        ac1[k1 + l7] = ac[0];
                    }

                    k1 += k5;
                }
                if (j4 < 0) {
                    j4 = -j4;
                    ac1[k1] = ac[41];
                    ac1[k1 + 1] = ac[11];
                } else {
                    ac1[k1] = ac[41];
                    ac1[k1 + 1] = ac[10];
                }
                if (j4 > 99) {
                    ac1[k1 + 2] = ac[1];
                    abyte2[0] = (byte) (j4 - 99);
                    ac1[k1 + 3] = ac[LnxqComponents_P[abyte2[0] & 0xff][0]];
                    ac1[k1 + 4] = ac[LnxqComponents_P[abyte2[0] & 0xff][1]];
                } else {
                    abyte2[0] = (byte) (j4 + 1);
                    ac1[k1 + 2] = ac[LnxqComponents_P[abyte2[0] & 0xff][0]];
                    ac1[k1 + 3] = ac[LnxqComponents_P[abyte2[0] & 0xff][1]];
                }
                i2 = 0;
            }
            int j = 1;
            int l1 = i2;
            if (flag2) {
                ac1[l1] = ac[11];
                l1++;
            }
            if (flag5) {
                ac1[l1] = ac[abyte1[abyte0[j]][1]];
                l1++;
                j++;
            }
            if (l5 != 0) {
                for (int k = l1 + l5; l1 < k;) {
                    ac1[l1] = ac[abyte1[abyte0[j]][0]];
                    l1++;
                    ac1[l1] = ac[abyte1[abyte0[j]][1]];
                    l1++;
                    j++;
                }

            }
            if (flag3) {
                if (flag4) {
                    ac1[l1] = ac[abyte1[abyte0[j]][0]];
                    l1++;
                    ac1[l1] = c;
                    l1++;
                    ac1[l1] = ac[abyte1[abyte0[j]][1]];
                    l1++;
                    j++;
                } else {
                    ac1[l1] = c;
                    l1++;
                }
            }
            if (i6 != 0) {
                for (int l = l1 + i6; l1 < l;) {
                    ac1[l1] = ac[abyte1[abyte0[j]][0]];
                    l1++;
                    ac1[l1] = ac[abyte1[abyte0[j]][1]];
                    l1++;
                    j++;
                }

            }
            if (flag6) {
                ac1[l1] = ac[abyte1[abyte0[j]][0]];
                l1++;
                j++;
            }
        } while (true);
        return new String(ac1);
    }

    public byte[] lnxren(double d) throws SQLException {
        byte abyte0[] = new byte[20];
        int i = 0;
        boolean flag1 = d >= 0.0D;
        d = Math.abs(d);
        if (d < 1.0D) {
            for (int l = 0; l < 8; l++) {
                if (powerTable[l][2] >= d) {
                    i -= (int) powerTable[l][0];
                    d *= powerTable[l][1];
                }
            }

            if (d < 1.0D) {
                i--;
                d *= 100D;
            }
        } else {
            for (int i1 = 0; i1 < 8; i1++) {
                if (powerTable[i1][1] <= d) {
                    i += (int) powerTable[i1][0];
                    d *= powerTable[i1][2];
                }
            }

        }
        if (i > 62) {
            throw new SQLException(CoreException.getMessage((byte) 3));
        }
        if (i < -65) {
            throw new SQLException(CoreException.getMessage((byte) 2));
        }
        boolean flag = d < 10D;
        byte byte0 = 8;
        int j = 0;
        byte byte1 = (byte) (int) d;
        for (; j < byte0; j++) {
            abyte0[j] = byte1;
            d = (d - (double) byte1) * 100D;
            byte1 = (byte) (int) d;
        }

        j = 7;
        if (flag) {
            if (byte1 >= 50) {
                abyte0[j]++;
            }
        } else {
            abyte0[j] = (byte) (((abyte0[j] + 5) / 10) * 10);
        }
        do {
            if (abyte0[j] != 100) {
                break;
            }
            if (j == 0) {
                i++;
                abyte0[j] = 1;
                break;
            }
            abyte0[j] = 0;
            j--;
            abyte0[j]++;
        } while (true);
        for (int k = 7; k != 0 && abyte0[k] == 0; k--) {
            byte0--;
        }

        byte abyte1[] = new byte[byte0 + 1];
        abyte1[0] = (byte) i;
        System.arraycopy(abyte0, 0, abyte1, 1, byte0);
        return NUMBER._toLnxFmt(abyte1, flag1);
    }

    public byte[] lnxmin(long l) {
        byte abyte0[] = new byte[20];
        byte abyte1[] = new byte[20];
        byte byte1 = 0;
        if (l == 0L) {
            return NUMBER._makeZero();
        }
        boolean flag = l >= 0L;
        int i;
        for (i = 0; l != 0L; i++) {
            abyte0[i] = (byte) (int) Math.abs(l % 100L);
            l /= 100L;
        }

        byte byte0 = (byte) (--i);
        for (int j = byte0; byte1 <= byte0; j--) {
            abyte1[byte1] = abyte0[j];
            byte1++;
        }

        while (i > 0 && abyte1[i--] == 0) {
            byte1--;
        }
        byte abyte2[] = new byte[byte1 + 1];
        abyte2[0] = byte0;
        System.arraycopy(abyte1, 0, abyte2, 1, byte1);
        return NUMBER._toLnxFmt(abyte2, flag);
    }

    public double lnxnur(byte abyte0[]) {
        double d = 0.0D;
        int j = 1;
        boolean flag = false;
        int l2 = factorTable.length;
        byte abyte1[] = NUMBER._fromLnxFmt(abyte0);
        boolean flag1 = abyte1[1] < 10;
        double d5 = factorTable[0][0];
        double d6 = factorTable[0][0] - (double) (l2 - 20);
        int i;
        int k2;
        if ((double) abyte1[0] > d5 || (double) abyte1[0] < d6) {
            if ((double) abyte1[0] > d5) {
                i = -1;
                k2 = (int) ((double) abyte1[0] - d5);
            } else {
                i = -1 + (l2 - 20);
                k2 = (int) ((double) abyte1[0] - d6);
            }
        } else {
            i = -1 + (int) (d5 - (double) abyte1[0]);
            k2 = 0;
        }
        int j2 = abyte1.length - 1;
        if (flag1 ? j2 > 8 : j2 >= 8) {
            j2 = 8;
            flag = true;
        }
        switch (j2 % 4) {
        case 3: // '\003'
            int l = (abyte1[1] * 100 + abyte1[2]) * 100 + abyte1[3];
            i += 3;
            double d1 = factorTable[i][1];
            if (d1 < 1.0D) {
                d = (double) l / factorTable[i][2];
            } else {
                d = (double) l * factorTable[i][1];
            }
            j += 3;
            j2 -= 3;
            break;

        case 2: // '\002'
            int i1 = abyte1[1] * 100 + abyte1[2];
            i += 2;
            double d2 = factorTable[i][1];
            if (d2 < 1.0D) {
                d = (double) i1 / factorTable[i][2];
            } else {
                d = (double) i1 * factorTable[i][1];
            }
            j += 2;
            j2 -= 2;
            break;

        case 1: // '\001'
            int j1 = abyte1[1];
            i++;
            double d3 = factorTable[i][1];
            if (d3 < 1.0D) {
                d = (double) j1 / factorTable[i][2];
            } else {
                d = (double) j1 * factorTable[i][1];
            }
            j++;
            j2--;
            break;

        default:
            d = 0.0D;
            break;
        }
        for (; j2 > 0; j2 -= 4) {
            int k1 = ((abyte1[j] * 100 + abyte1[j + 1]) * 100 + abyte1[j + 2]) * 100
                    + abyte1[j + 3];
            i += 4;
            double d4 = factorTable[i][1];
            if (d4 < 1.0D) {
                d += (double) k1 / factorTable[i][2];
            } else {
                d += (double) k1 * factorTable[i][1];
            }
            j += 4;
        }

        if (flag) {
            if (flag1) {
                if (abyte1[j] > 50) {
                    int l1 = 1;
                    d += (double) l1 * factorTable[i][1];
                }
            } else {
                j--;
                int i2;
                if (abyte1[j] % 10 >= 5) {
                    i2 = (abyte1[j] / 10 + 1) * 10;
                } else {
                    i2 = (abyte1[j] / 10) * 10;
                }
                i2 -= abyte1[j];
                d += (double) i2 * factorTable[i][1];
            }
        }
        if (k2 != 0) {
            int k;
            for (k = 0; k2 > 0; k++) {
                if ((int) powerTable[k][0] <= k2) {
                    k2 -= (int) powerTable[k][0];
                    d *= powerTable[k][1];
                }
            }

            while (k2 < 0) {
                if ((int) powerTable[k][0] <= -k2) {
                    k2 += (int) powerTable[k][0];
                    d *= powerTable[k][2];
                }
                k++;
            }
        }
        return NUMBER._isPositive(abyte0) ? d : -d;
    }

    public long lnxsni(byte abyte0[]) throws SQLException {
        long l = 0L;
        byte abyte1[] = NUMBER._fromLnxFmt(abyte0);
        byte byte0 = abyte1[0];
        byte byte1 = (byte) (abyte1.length - 1);
        int i = byte1 <= byte0 + 1 ? ((int) (byte1)) : byte0 + 1;
        for (int j = 0; j < i; j++) {
            l = l * 100L + (long) abyte1[j + 1];
        }

        for (int k = byte0 - byte1; k >= 0; k--) {
            l *= 100L;
        }

        return NUMBER._isPositive(abyte0) ? l : -l;
    }

    private byte[] lnxqh2n(char ac[]) {
        int i = 0;
        int j = ac.length;
        long al[] = new long[14];
        int k = 13;
        byte byte0 = 13;
        byte abyte0[] = new byte[42];
        int j1 = 1;
        for (; j != 0 && ac[j - 1] == 0; j--) {
        }
        for (; j != 0 && ac[i] == '0'; j--) {
            i++;
        }

        if (j == 0) {
            return NUMBER._makeZero();
        }
        al[byte0] = 0L;
        switch (j % 3) {
        case 0: // '\0'
            al[byte0] = LNXQH2N_DIGIT(ac[i], 8, al[byte0]);
            i++;
            j--;
            // fall through

        case 2: // '\002'
            al[byte0] = LNXQH2N_DIGIT(ac[i], 4, al[byte0]);
            i++;
            j--;
            // fall through

        case 1: // '\001'
            al[byte0] = LNXQH2N_DIGIT(ac[i], 0, al[byte0]);
            i++;
            j--;
            break;
        }
        for (; j != 0; j -= 3) {
            long l2 = 0L;
            l2 = LNXQH2N_DIGIT(ac[i], 8, l2);
            l2 = LNXQH2N_DIGIT(ac[i + 1], 4, l2);
            l2 = LNXQH2N_DIGIT(ac[i + 2], 0, l2);
            for (int l = byte0; l >= k; l--) {
                l2 += al[l] << 12;
                al[l] = l2 % 0xf4240L;
                l2 /= 0xf4240L;
            }

            if (l2 != 0L) {
                k--;
                al[k] = l2;
            }
            i += 3;
        }

        int k2 = 3 * (byte0 - k) + 1;
        k2 += al[k] < 100L ? 0 : 1;
        k2 += al[k] < 10000L ? 0 : 1;
        byte abyte2[] = new byte[22];
        int i3 = 0;
        abyte2[i3] = (byte) (k2 + 192);
        int k1;
        byte abyte1[];
        int j2;
        if (k2 > 20) {
            abyte1 = abyte0;
            k1 = j1;
            j2 = 21;
        } else {
            abyte1 = abyte2;
            k1 = i3 + 1;
            j2 = k2 + 1;
        }
        int i1;
        switch (k2 % 3) {
        case 0: // '\0'
            abyte1[k1] = (byte) (int) (al[k] / 10000L + 1L);
            k1++;
            // fall through

        case 2: // '\002'
            abyte1[k1] = (byte) (int) ((al[k] % 10000L) / 100L + 1L);
            k1++;
            // fall through

        case 1: // '\001'
            abyte1[k1] = (byte) (int) (al[k] % 100L + 1L);
            k1++;
            // fall through

        default:
            i1 = k + 1;
            break;
        }
        for (; i1 <= byte0; i1++) {
            abyte1[k1] = (byte) (int) (al[i1] / 10000L + 1L);
            abyte1[k1 + 1] = (byte) (int) ((al[i1] % 10000L) / 100L + 1L);
            abyte1[k1 + 2] = (byte) (int) (al[i1] % 100L + 1L);
            k1 += 3;
        }

        if (k2 > 20) {
            int l1 = j1 + 20;
            if (abyte0[l1] > 50) {
                abyte0[j1 - 1] = 1;
                for (l1--; abyte0[l1] == 100;) {
                    l1--;
                    j2--;
                }

                abyte0[l1]++;
                if (l1 < j1) {
                    j1--;
                    abyte2[i3]++;
                    j2 = 2;
                }
            }
            for (int j3 = 0; j3 < j2; j3++) {
                abyte2[i3 + 1 + j3] = abyte0[j1 + j3];
            }

        }
        for (int i2 = i3 + (j2 - 1); abyte1[i2] == 1;) {
            i2--;
            j2--;
        }

        byte abyte3[] = new byte[j2];
        System.arraycopy(abyte2, 0, abyte3, 0, j2);
        return abyte3;
    }

    private long LNXQH2N_DIGIT(char c, int i, long l) {
        if (c >= 'a' && c <= 'f') {
            long l1 = l + (long) ((c - 97) + 10 << i);
            return l1;
        }
        if (c >= 'A' && c <= 'F') {
            long l2 = l + (long) ((c - 65) + 10 << i);
            return l2;
        } else {
            long l3 = l + (long) (c - 48 << i);
            return l3;
        }
    }

    private byte[] lnxqtra(byte abyte0[], int i) throws SQLException {
        byte abyte1[] = null;
        byte abyte3[] = null;
        Object obj = null;
        byte abyte8[] = NUMBER.pi().shareBytes();
        byte abyte9[] = lnxmin(-1L);
        long l = 0L;
        if (i == 3 || i == 4 || i == 5) {
            byte abyte4[] = lnxmul(lnxqtwo, abyte8);
            abyte1 = lnxabs(abyte0);
            abyte1 = lnxmod(abyte1, abyte4);
            if (lnxcmp(abyte1, abyte8) > 0) {
                abyte1 = lnxsub(abyte1, abyte4);
            }
            if (lnxsgn(abyte0) == -1) {
                abyte1 = lnxneg(abyte1);
            }
            abyte3 = lnxmul(abyte1, abyte1);
        } else if (i == 9) {
            abyte1 = lnxmod(abyte0, lnxqone);
            byte abyte5[] = lnxsub(abyte0, abyte1);
            if ((abyte5[0] & 0xff) < 60) {
                return NUMBER._makeZero();
            }
            if ((abyte5[0] & 0xff) > 195) {
                return NUMBER._makePosInf();
            }
            l = lnxsni(abyte5);
            abyte3 = lnxmul(abyte1, abyte1);
        } else {
            abyte1 = new byte[abyte0.length];
            System.arraycopy(abyte0, 0, abyte1, 0, abyte0.length);
            abyte3 = lnxmul(abyte1, abyte1);
        }
        byte abyte10[] = null;
        byte abyte11[] = null;
        if (i != 4 && i != 7) {
            byte abyte6[] = lnxqone;
            abyte10 = lnxqone;
            abyte11 = NUMBER._makeZero();
            int i1 = 0;
            do {
                abyte6 = lnxmul(abyte3, abyte6);
                int j = (i1 + 1) * (i1 + 2);
                i1 += 2;
                abyte6 = lnxqIDiv(abyte6, j);
                abyte11 = lnxadd(abyte11, abyte6);
                abyte6 = lnxmul(abyte3, abyte6);
                j = (i1 + 1) * (i1 + 2);
                i1 += 2;
                abyte6 = lnxqIDiv(abyte6, j);
                abyte10 = lnxadd(abyte10, abyte6);
            } while ((abyte6[0] & 0xff) + 20 >= (abyte10[0] & 0xff) && (abyte11[0] & 0xff) != 255);
        }
        byte abyte12[] = null;
        byte abyte13[] = null;
        if (i != 3 && i != 6) {
            byte abyte7[] = new byte[abyte1.length];
            System.arraycopy(abyte1, 0, abyte7, 0, abyte1.length);
            abyte12 = new byte[abyte1.length];
            System.arraycopy(abyte1, 0, abyte12, 0, abyte1.length);
            abyte13 = NUMBER._makeZero();
            int j1 = 1;
            do {
                abyte7 = lnxmul(abyte3, abyte7);
                int k = (j1 + 1) * (j1 + 2);
                j1 += 2;
                abyte7 = lnxqIDiv(abyte7, k);
                abyte13 = lnxadd(abyte13, abyte7);
                abyte7 = lnxmul(abyte3, abyte7);
                k = (j1 + 1) * (j1 + 2);
                j1 += 2;
                abyte7 = lnxqIDiv(abyte7, k);
                abyte12 = lnxadd(abyte12, abyte7);
            } while (((abyte7[0] & 0xff) != 128 || abyte7.length != 1)
                    && ((abyte7[0] & 0xff) < 128 || (abyte7[0] & 0xff) + 20 >= (abyte12[0] & 0xff))
                    && ((abyte7[0] & 0xff) >= 128 || (abyte7[0] & 0xff) <= (abyte12[0] & 0xff) + 20)
                    && (abyte13[0] & 0xff) != 255 && (abyte13[0] & 0xff) != 0);
        }
        byte abyte14[] = null;
        byte abyte15[] = null;
        if (i == 3 || i == 4 || i == 5) {
            if (i == 3 || i == 5) {
                abyte14 = lnxsub(abyte10, abyte11);
                if (lnxcmp(abyte14, lnxqone) > 0) {
                    abyte14 = lnxqone;
                } else if (lnxcmp(abyte14, abyte9) < 0) {
                    abyte14 = abyte9;
                }
            }
            if (i == 3) {
                return abyte14;
            }
            abyte15 = lnxsub(abyte12, abyte13);
            if (lnxcmp(abyte15, lnxqone) > 0) {
                abyte15 = lnxqone;
            } else if (lnxcmp(abyte15, abyte9) < 0) {
                abyte15 = abyte9;
            }
            if (i == 4) {
                return abyte15;
            } else {
                return lnxdiv(abyte15, abyte14);
            }
        }
        if (i == 6) {
            return lnxadd(abyte10, abyte11);
        }
        if (i == 7) {
            return lnxadd(abyte12, abyte13);
        }
        abyte15 = lnxadd(abyte12, abyte13);
        abyte14 = lnxadd(abyte10, abyte11);
        if (i == 8) {
            return lnxdiv(abyte15, abyte14);
        } else {
            byte abyte16[] = NUMBER.e().shareBytes();
            byte abyte17[] = lnxadd(abyte14, abyte15);
            byte abyte2[] = lnxpow(abyte16, (int) l);
            abyte17 = lnxmul(abyte17, abyte2);
            return abyte17;
        }
    }

    private byte[] lnxqtri(byte abyte0[], int i) throws SQLException {
        byte abyte1[] = null;
        Object obj = null;
        Object obj1 = null;
        byte abyte5[] = NUMBER.pi().shareBytes();
        byte abyte6[] = lnxdiv(abyte5, lnxqtwo);
        if (i == 2) {
            if (NUMBER._isPosInf(abyte0)) {
                return abyte6;
            }
            if (NUMBER._isNegInf(abyte0)) {
                return lnxneg(abyte6);
            }
        }
        byte abyte7[] = lnxabs(abyte0);
        if (i == 1 || i == 0) {
            if (lnxcmp(abyte7, lnxqone) > 0) {
                throw new SQLException(CoreException.getMessage((byte) 11));
            }
            if ((abyte7[0] & 0xff) <= 183) {
                if (i == 1) {
                    byte abyte8[] = new byte[abyte0.length];
                    System.arraycopy(abyte0, 0, abyte8, 0, abyte0.length);
                    return abyte8;
                } else {
                    return lnxsub(abyte6, abyte0);
                }
            }
            abyte1 = lnxsub(lnxqone, abyte7);
            byte abyte2[] = lnxadd(lnxqone, abyte7);
            abyte7 = lnxdiv(abyte1, abyte2);
            abyte7 = lnxsqr(abyte7);
        }
        int j;
        if ((j = lnxcmp(abyte7, lnxqone)) > 0) {
            abyte7 = lnxdiv(lnxqone, abyte7);
        }
        abyte1 = new byte[abyte7.length];
        System.arraycopy(abyte7, 0, abyte1, 0, abyte7.length);
        int k = 1;
        do {
            byte abyte3[] = lnxtan(abyte1);
            byte abyte4[] = lnxsub(abyte7, abyte3);
            abyte3 = lnxmul(abyte3, abyte3);
            abyte3 = lnxadd(abyte3, lnxqone);
            abyte3 = lnxdiv(abyte4, abyte3);
            int l = (abyte3[0] & 0xff) < 128 ? 62 - (abyte3[0] & 0xff) : (abyte3[0] & 0xff) - 193;
            int i1 = (abyte1[0] & 0xff) < 128 ? 62 - (abyte1[0] & 0xff) : (abyte1[0] & 0xff) - 193;
            if ((abyte3[0] & 0xff) == 128 && abyte3.length == 1 || (l & 0xff) + 15 < (i1 & 0xff)
                    || k > 15) {
                break;
            }
            abyte1 = lnxadd(abyte1, abyte3);
            k++;
        } while (true);
        if (j > 0) {
            abyte1 = lnxsub(abyte6, abyte1);
        }
        if ((abyte1[0] & 0xff) < 128) {
            abyte1 = NUMBER._makeZero();
        }
        if (lnxcmp(abyte1, abyte6) > 0) {
            abyte1 = abyte6;
        }
        if (i == 1 || i == 0) {
            abyte1 = lnxmul(abyte1, lnxqtwo);
        }
        switch (i) {
        case 1: // '\001'
            if (NUMBER._isPositive(abyte0)) {
                return lnxsub(abyte6, abyte1);
            } else {
                return lnxsub(abyte1, abyte6);
            }

        case 0: // '\0'
            if (NUMBER._isPositive(abyte0)) {
                return abyte1;
            } else {
                return lnxsub(abyte5, abyte1);
            }

        case 2: // '\002'
            if (NUMBER._isPositive(abyte0)) {
                return abyte1;
            } else {
                return lnxneg(abyte1);
            }
        }
        throw new SQLException(CoreException.getMessage((byte) 11));
    }

    private int lnxcmp(byte abyte0[], byte abyte1[]) {
        return NUMBER.compareBytes(abyte0, abyte1);
    }

    private int lnxsgn(byte abyte0[]) {
        if (NUMBER._isZero(abyte0)) {
            return 0;
        }
        return !NUMBER._isPositive(abyte0) ? -1 : 1;
    }

    private byte[] lnxqIDiv(byte abyte0[], int i) throws SQLException {
        byte abyte1[] = lnxmin(i);
        return lnxdiv(abyte0, abyte1);
    }

    private static void _negateNumber(byte abyte0[]) {
        for (int i = abyte0.length - 1; i > 0; i--) {
            abyte0[i] = LnxqNegate[abyte0[i]];
        }

        abyte0[0] = (byte) (~abyte0[0]);
    }

    private static byte[] _setLength(byte abyte0[], int i) {
        boolean flag = NUMBER._isPositive(abyte0);
        byte abyte1[];
        if (flag) {
            abyte1 = new byte[i];
        } else if (i <= 20 && abyte0[i - 1] != 102) {
            abyte1 = new byte[i + 1];
            abyte1[i] = 102;
        } else {
            abyte1 = new byte[i];
        }
        System.arraycopy(abyte0, 0, abyte1, 0, i);
        return abyte1;
    }

    private class lnxqc {
        static final int LNXQC0 = 0;
        static final int LNXQC1 = 1;
        static final int LNXQC2 = 2;
        static final int LNXQC3 = 3;
        static final int LNXQC4 = 4;
        static final int LNXQC5 = 5;
        static final int LNXQC6 = 6;
        static final int LNXQC7 = 7;
        static final int LNXQC8 = 8;
        static final int LNXQC9 = 9;
        static final int LNXQCPLUS = 10;
        static final int LNXQCMINUS = 11;
        static final int LNXQCSPACE = 12;
        static final int LNXQCDOT = 13;
        static final int LNXQCCOMMA = 14;
        static final int LNXQCDOLLR = 15;
        static final int LNXQCLT = 16;
        static final int LNXQCGRT = 17;
        static final int LNXQCLPT = 18;
        static final int LNXQCRPT = 19;
        static final int LNXQCHASH = 20;
        static final int LNXQCTILDE = 21;
        static final int LNXQCASML = 22;
        static final int LNXQCBSML = 23;
        static final int LNXQCCSML = 24;
        static final int LNXQCDSML = 25;
        static final int LNXQCESML = 26;
        static final int LNXQCFSML = 27;
        static final int LNXQCGSML = 28;
        static final int LNXQCISML = 29;
        static final int LNXQCLSML = 30;
        static final int LNXQCMSML = 31;
        static final int LNXQCPSML = 32;
        static final int LNXQCRSML = 33;
        static final int LNXQCSSML = 34;
        static final int LNXQCTSML = 35;
        static final int LNXQCVSML = 36;
        static final int LNXQCALRG = 37;
        static final int LNXQCBLRG = 38;
        static final int LNXQCCLRG = 39;
        static final int LNXQCDLRG = 40;
        static final int LNXQCELRG = 41;
        static final int LNXQCFLRG = 42;
        static final int LNXQCILRG = 43;
        static final int LNXQCLLRG = 44;
        static final int LNXQCMLRG = 45;
        static final int LNXQCPLRG = 46;
        static final int LNXQCRLRG = 47;
        static final int LNXQCSLRG = 48;
        static final int LNXQCTLRG = 49;

        private lnxqc() {
        }
    }

}