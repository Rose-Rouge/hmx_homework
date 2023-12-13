package com.roserouge;
/*    */ import java.awt.Font;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JOptionPane;
/*    */ import javax.swing.JTextArea;
/*    */ 
/*    */ public class TheiCardGUI {
/*  8 */   private static final JFrame frame = new JFrame("THEi DIT4103/DMT4711 Assignment");
/*    */   
/* 10 */   private static final JTextArea textArea = new JTextArea(50, 25);
/*    */   
/*    */   static {
/* 12 */     frame.setDefaultCloseOperation(3);
/* 13 */     textArea.setEditable(false);
/* 14 */     textArea.setLineWrap(true);
/*    */     Font font;
/* 20 */     textArea.setFont(font = new Font("Monospaced", 0, 16));
/* 25 */     frame.add(textArea);
/* 26 */     frame.setSize(600, 600);
/* 27 */     frame.setLocation(150, 150);
/* 28 */     frame.setVisible(true);
/*    */   }
/*    */   
/*    */   public static void clearScreen() {
/* 32 */     textArea.setText("");
/*    */   }
/*    */   
/*    */   public static void printf(String paramString, Object... paramVarArgs) {
/* 36 */     String str = String.format(paramString, paramVarArgs);
/* 37 */     textArea.append(str);
/*    */   }
/*    */   
/*    */   public static void print(Object paramObject) {
/* 41 */     textArea.append(paramObject.toString());
/*    */   }
/*    */   
/*    */   public static void println() {
/* 45 */     printf("%n", new Object[0]);
/*    */   }
/*    */   
/*    */   public static void println(Object paramObject) {
/* 49 */     print(paramObject);
/* 50 */     println();
/*    */   }
/*    */   
/*    */   public static String getInputString(String paramString) {
/*    */     String str;
/*    */     do {
/* 56 */       str = JOptionPane.showInputDialog(paramString);
/* 57 */     } while (str == null);
/* 59 */     return str;
/*    */   }
/*    */   
/*    */   public static void showMessage(String paramString) {
/* 63 */     JOptionPane.showMessageDialog(null, paramString, "", -1);
/*    */   }
/*    */ }


/* Location:              C:\Users\ajiaj\Downloads\Lab10_ans\ans\!\TheiCardGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */