# README

Sample project to illustrate problem with JPMS. Trying to compile the project will result in these errors:

```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.10.1:compile (default-compile) on project my-project: Compilation failure: Compilation failure:
[ERROR] the unnamed module reads package dev.langchain4j.store.embedding from both langchain4j and langchain4j.core
[ERROR] the unnamed module reads package dev.langchain4j.retriever from both langchain4j and langchain4j.core
[ERROR] the unnamed module reads package dev.langchain4j.model.output from both langchain4j and langchain4j.core
[ERROR] the unnamed module reads package dev.langchain4j.data.document from both langchain4j and langchain4j.core
[ERROR] the unnamed module reads package dev.langchain4j.code from both langchain4j and langchain4j.core
[ERROR] the unnamed module reads package dev.langchain4j.classification from both langchain4j and langchain4j.core
[ERROR] the unnamed module reads package dev.langchain4j.chain from both langchain4j and langchain4j.core
[ERROR] the unnamed module reads package dev.langchain4j.agent.tool from both langchain4j and langchain4j.core
[ERROR] module langchain4j.core reads package dev.langchain4j.store.embedding from both langchain4j and langchain4j.core
[ERROR] module langchain4j.core reads package dev.langchain4j.retriever from both langchain4j and langchain4j.core
[ERROR] module langchain4j.core reads package dev.langchain4j.model.output from both langchain4j and langchain4j.core
[ERROR] module langchain4j.core reads package dev.langchain4j.data.document from both langchain4j and langchain4j.core
[ERROR] module langchain4j.core reads package dev.langchain4j.code from both langchain4j and langchain4j.core
[ERROR] module langchain4j.core reads package dev.langchain4j.classification from both langchain4j and langchain4j.core
[ERROR] module langchain4j.core reads package dev.langchain4j.chain from both langchain4j and langchain4j.core
[ERROR] module langchain4j.core reads package dev.langchain4j.agent.tool from both langchain4j and langchain4j.core
[ERROR] module langchain4j reads package dev.langchain4j.store.embedding from both langchain4j.core and langchain4j
[ERROR] module langchain4j reads package dev.langchain4j.retriever from both langchain4j.core and langchain4j
[ERROR] module langchain4j reads package dev.langchain4j.model.output from both langchain4j.core and langchain4j
[ERROR] module langchain4j reads package dev.langchain4j.data.document from both langchain4j.core and langchain4j
[ERROR] module langchain4j reads package dev.langchain4j.code from both langchain4j.core and langchain4j
[ERROR] module langchain4j reads package dev.langchain4j.classification from both langchain4j.core and langchain4j
[ERROR] module langchain4j reads package dev.langchain4j.chain from both langchain4j.core and langchain4j
[ERROR] module langchain4j reads package dev.langchain4j.agent.tool from both langchain4j.core and langchain4j
```

and if you try to use one of `langchain4j` or `langchain4j.core` in `module-info.java` you will get another series of errors. What gives? How to fix this?