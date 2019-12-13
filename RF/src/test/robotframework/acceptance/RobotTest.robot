***Settings***
Documentation       Любое описание? Оно вызывается в коде? Для чего?

***Variables***
${HELLOMESSAGE}=    -Hello World-
@{OPERATION}=       Additions    Substractions    Multiplication    Division
${NUM1}=            1
${NUM2}=            2.0


*** Test Cases ***    Expression

[Tag] Ass in progress
Additions             Evaluate  ${NUM1} + ${NUM2}

Substractions         Evaluate  ${NUM1} - ${NUM2}

Multiplication        Evaluate  ${NUM1} * ${NUM2}

Division              Evaluate  ${NUM1} / ${NUM2}