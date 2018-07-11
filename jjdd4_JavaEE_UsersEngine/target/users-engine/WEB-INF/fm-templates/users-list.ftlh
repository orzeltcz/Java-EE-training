<!DOCTYPE html>
<html>
<head>

</head>
<body>

<#list users>
    <#items as user>
    <#if user.age < 20> <h1 style="color:blue;">
    <#elseif user.age gt 20 && user.age lt 40> <h1 style="color:green;">
    <#else> <h1 style="color:red;">
        Imie: ${user.name}<br/>
        Login: ${user.login}<br/>
        Wiek: ${user.age}<br/>
        Plec: ${user.gender}<br/>
    <#-- to jest tajemniczy komentarz-->
    </#if>
    <#sep>--------------------------------------</#sep><br/>
    </#items>
<h1>
</#list>
</body>
</html>