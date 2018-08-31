# asmtool Java 字节码汇编和反汇编器

> Jasm/Jdis - an assembler language that provides a Java-like declaration of member signatures, while providing Java VM specification compliant mnemonics for byte-code instructions. Jasm also provides high-level syntax for constructs often found within classfile attributes.   Jasm encoded tests are useful for sequencing byte codes in a way that Javac compiled code might not normally sequence byte-codes.

> jdis is a disassembler that accepts a .class file, and prints the plain-text translation of jasm source file to the standard output.

> jasm is an assembler that accepts a text file based on the JASM Specification, and produces a .class file for use with a Java Virtual Machine.
  Usage:

```sh
java -cp asmtools.jar org.openjdk.asmtools.jdis.Main JMHSample_10_ConstantFold.class
```

[openjdk asmtools](https://wiki.openjdk.java.net/display/CodeTools/asmtools)
[Using the AsmTools](https://wiki.openjdk.java.net/display/CodeTools/Chapter+2#Chapter2-Jdis)


