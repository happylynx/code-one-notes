# What's new in Java 12

## Switch expresion

* standard vs. `->`
* new keyword `yield` to prevent from falling to the next clause
* `,` separator for multiple cases
* just a preview
  * needs to be enabled compiler and runtime

## Multiline string literals

* a.k.a. text blocks
* `"""` + *new line*
* indentation trimming algorithm
* normalized line endings
* preview

## String API extended

* preview
  * added as `@Deprecated`
  * requires
    * `--enable-preview --source 13` for `javac`
    * `--enable-preview` for `java`
* `stripIndent()`
* `translateEscapes()`
* `formatted()`


## Simplified CDS archives creation

* Class Data Sharing
* `-XX:ArchiveClassesAtExit=CDS.jsa`
  * application archive creation
* `-XX:SharedArchiveFile=CDS.jsa`
  * use of application archive
* class data sharing for JDK classes enable by default since Java 12
  * `-Xshare:off` to turn off
* *https://blog.codefx.org/java/application-class-data-sharing/*

## Other

* Socket API reimplemented
* `ByteBuffer` API extended
* ZGC can return memory to operating system

---
References:  
* https://www.mkyong.com/java/what-is-new-in-java-13/