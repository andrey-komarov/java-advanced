#!/bin/sh
javac ./ru/ifmo/ctddev/komarov/implementor/*.java
jar -cmf ./ru/ifmo/ctddev/komarov/implementor/Manifest.txt implementor.jar ./ru/ifmo/ctddev/komarov/implementor/Implementor.class ./ru/ifmo/ctddev/komarov/implementor/MyMethod.class

