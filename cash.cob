       >>SOURCE FORMAT FREE
       IDENTIFICATION DIVISION.
       PROGRAM-ID. Cash.
       AUTHOR.	fan
       DATE-WRITTEN.	2025/5/26


       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01 Arg1     PIC X(100). *>参数字符串长度注意
       01 C_C        PIC 9(4) VALUE 0. *>人民币
       01 J_C        PIC 9(6)V99 VALUE 0. *>日元
       01 PI       PIC 9(1)V99 VALUE 5.1. *>汇率

       PROCEDURE DIVISION.
           ACCEPT Arg1 FROM COMMAND-LINE
           MOVE FUNCTION NUMVAL(Arg1) TO J_C.
           COMPUTE C_C = J_C / PI.
           DISPLAY " "C_C.
           STOP RUN.




